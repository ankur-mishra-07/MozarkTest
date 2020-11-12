package com.test.mozarktest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.mozarktest.R
import com.test.mozarktest.common.Loading
import com.test.mozarktest.common.NetworkError
import com.test.mozarktest.common.Success
import com.test.mozarktest.common.ViewState
import com.test.mozarktest.model.UserListResponse
import com.test.mozarktest.model.UserResult
import com.test.mozarktest.ui.adapter.UserDataRecyclerViewAdapter
import com.test.mozarktest.util.CustomProgressDialog
import com.test.mozarktest.viewmodel.FetcherViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

class UserListFragment : Fragment(), UserDataRecyclerViewAdapter.onUserClicked {

    private var columnCount = 2
    private var page = 10
    private lateinit var viewModel: FetcherViewModel
    private val progressDialog = CustomProgressDialog()
    private var mSavedResponse: ArrayList<UserResult> = arrayListOf<UserResult>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mAdapter: UserDataRecyclerViewAdapter

    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        mAdapter = UserDataRecyclerViewAdapter(this)
        with(userList) {
            layoutManager = GridLayoutManager(context, columnCount)
            adapter = mAdapter
        }
        userList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val gridLayoutManager =
                    recyclerView.layoutManager as GridLayoutManager?
                if (!isLoading) {
                    if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == mSavedResponse.size - 1) {
                        //bottom of list!
                        page += 10
                        viewModel.getUserList().removeObservers(viewLifecycleOwner)
                        checkUsers()
                        isLoading = true
                    }
                }
            }
        })
        checkUsers()
    }

    private fun checkUsers() {
        viewModel.fetchUserList(page)
        viewModel.getUserList().observe(viewLifecycleOwner, { onUserList(it) })
    }

    private fun onUserList(viewState: ViewState<UserListResponse>?) {
        when (viewState) {
            is Loading -> {
                setProgress(true)
            }
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message!!)
            }
            is Success -> {
                setProgress(false)
                onUserListReceived(viewState.mData)
            }
        }

    }

    private fun onUserListReceived(mData: UserListResponse) {
        if (mData != null) {
            if (mData.results.isNotEmpty()) {
                if (mSavedResponse.isEmpty()) {
                    mSavedResponse.addAll(mData.results)
                } else {
                    if (!mSavedResponse.containsAll(mData.results)) {
                        mSavedResponse.addAll(mData.results)
                    }
                }
                mAdapter.setRefreshData(mSavedResponse, requireContext())
                isLoading = false
            } else {
                Toast.makeText(requireContext(), getString(R.string.noUser), Toast.LENGTH_LONG)
                    .show()
            }
        }
        setProgress(false)
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    private fun showError(message: String) {
        if (isAdded)
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        if (isAdded) {
            progressDialog.show(requireContext(), getString(R.string.loading))
        }
    }

    private fun hideProgress() {
        if (isAdded)
            progressDialog.dialog.dismiss()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(FetcherViewModel::class.java)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            UserListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    private lateinit var mDialogView: View

    override fun onClicked(mUSer: UserResult) {
        viewModel.getSelectedUser().postValue(mUSer)
    }
}

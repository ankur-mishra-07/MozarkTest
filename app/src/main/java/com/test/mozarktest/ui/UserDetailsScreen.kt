package com.test.mozarktest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.mozarktest.R
import com.test.mozarktest.model.UserResult
import com.test.mozarktest.util.CustomProgressDialog
import com.test.mozarktest.viewmodel.FetcherViewModel
import javax.inject.Inject

class UserDetailsScreen: Fragment() {


    private lateinit var viewModel: FetcherViewModel
    private val progressDialog = CustomProgressDialog()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mUser: UserResult

    private lateinit var mActivityContext: Context

    private var userId = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    companion object {
        private val userId: String = "userId"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment MovieDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(iMdbId: String) = UserDetailsScreen().apply {
            arguments = Bundle().apply {
                putString(userId, iMdbId)
            }
        }

    }
}
package com.example.github.views

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.github.R
import com.example.github.viewmodel.ReadMeViewModel
import java.util.*

class RepositoryReadMeFragment: Fragment() {
    val viewModel: ReadMeViewModel by activityViewModels()
    private val args : RepositoryReadMeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailed_repo, container, false)
        return view;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchReadMe(args.login, args.repoName)

        viewModel.readMeModel.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                (view.findViewById(R.id.webReadMe) as WebView).loadUrl(it.readMeURL!!)
        })

        (view.findViewById(R.id.txtName) as TextView).text = args.repoName
        (view.findViewById(R.id.txtIssues) as TextView).text = args.issues.toString()
        (view.findViewById(R.id.txtLanguage) as TextView).text = args.language
    }
}
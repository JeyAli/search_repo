package com.example.github.views

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.github.R
import com.example.github.network.Network
import com.example.github.repository.ReadMeRepository
import com.example.github.viewmodel.ReadMeViewModel

class RepositoryReadMeFragment: Fragment() {
    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onActivityCreated, which we
     * do in this Fragment.
     */
    private val viewModel: ReadMeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, ReadMeViewModel
            .ReadMeViewModelFactory(ReadMeRepository(Network.gitHubService), activity.application))
            .get(ReadMeViewModel::class.java)
    }
    private val args : RepositoryReadMeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_repo, container, false);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = view.findViewById(R.id.webReadMe) as WebView
        val progressBar = view.findViewById(R.id.pBar) as ProgressBar

        progressBar.visibility = View.VISIBLE
        webView.visibility = View.INVISIBLE

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(v: WebView?, url: String?) {
                super.onPageFinished(v, url)
                progressBar.visibility = View.INVISIBLE
                webView.visibility = View.VISIBLE
            }
        }

        viewModel.fetchReadMe(args.login, args.repoName)

        viewModel.readMeModel.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            webView.loadUrl(it.readMeURL!!)
        })

        (view.findViewById(R.id.txtName) as TextView).text = args.repoName
        (view.findViewById(R.id.txtIssues) as TextView).text = args.issues.toString()
        (view.findViewById(R.id.txtLanguage) as TextView).text = args.language
    }
}
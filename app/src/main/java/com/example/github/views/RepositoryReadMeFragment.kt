package com.example.github.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.github.R
import com.example.github.viewmodel.ReadMeViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchReadMe(args.login, args.repoName)
        val readMe = viewModel.readMeModel

        (view.findViewById(R.id.txtName) as TextView).text = args.repoName
        (view.findViewById(R.id.txtIssues) as TextView).text = args.issues.toString()
        (view.findViewById(R.id.txtLanguage) as TextView).text = args.language
        (view.findViewById(R.id.txtReadMe) as TextView).text = ""
    }
}
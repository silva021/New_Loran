package com.silva021.newloran.ui.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.silva021.newloran.databinding.FragmentWebViewBinding
import com.silva021.newloran.ui.list.ContactListFragment
import com.silva021.toolkit.extension.gone
import com.silva021.toolkit.extension.onBackPressed
import com.silva021.toolkit.extension.visible

class WebViewFragment : Fragment() {
    private val binding by lazy {
        FragmentWebViewBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setupListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ContactListFragment.PROFILE_PAGE_LINK)?.let {
            setupWebView(it)
        }
    }

    private fun setupListener() {
        with(binding) {
            toolbar.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setupWebView(url: String) {
        binding.webView.apply {
            loadUrl(url.replace("http:", "https:"))
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    binding.loading.gone()
                    binding.webView.visible()
                }
            }
        }
    }
}
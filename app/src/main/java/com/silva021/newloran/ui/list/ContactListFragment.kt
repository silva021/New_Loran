package com.silva021.newloran.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.silva021.newloran.R
import com.silva021.newloran.databinding.FragmentContactListBinding
import com.silva021.newloran.data.model.Contact
import com.silva021.newloran.ui.list.adapter.ListContactAdapter
import com.silva021.toolkit.extension.gone
import com.silva021.toolkit.extension.navigate
import com.silva021.toolkit.extension.visible
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ContactListFragment : Fragment() {

    private lateinit var binding: FragmentContactListBinding
    private val viewModel: ContactListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContactListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        refreshScreen()
        setupErrorScreen()
        loadGetContact()
    }

    private fun loadGetContact() {
        with(binding) {
            containerSuccess.visible()
            errorContainer.gone()
        }
        viewModel.loadGetContact()
    }

    private fun refreshScreen() = with(binding) {
        containerSwipe.setOnRefreshListener {
            recycler.gone()
            loading.visible()
            loadGetContact()
            containerSwipe.isRefreshing = false
        }
    }

    private fun setupErrorScreen() {
        with(binding) {
            button.setOnClickListener {
                loadGetContact()
            }
        }
    }

    private fun setupObserver() {
        viewModel.listContact.observe(viewLifecycleOwner) {
            setupScreen(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            with(binding) {
                containerSuccess.gone()
                errorContainer.visible()
            }
        }
    }

    private fun setupScreen(list: List<Contact>) {
        with(binding) {
            recycler.visible()
            loading.gone()
            recycler.adapter = ListContactAdapter(
                list,
                onClickImageListener,
                onClickLinkListener
            )
        }
    }

    private val onClickImageListener: (String) -> Unit = {
        navigate(R.id.contactImageDetails, bundleOf(PROFILE_IMAGE_LINK to it))
    }

    private val onClickLinkListener: (String) -> Unit = {
        navigate(R.id.contactWebView, bundleOf(PROFILE_PAGE_LINK to it))
    }

    companion object {
        const val PROFILE_IMAGE_LINK = "profileImageUrl"
        const val PROFILE_PAGE_LINK = "profilePageUrl"
    }
}
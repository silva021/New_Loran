package com.silva021.newloran.ui.photodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.silva021.newloran.databinding.FragmentPhotoDetailsBinding
import com.silva021.newloran.ui.list.ContactListFragment
import com.silva021.toolkit.extension.gone
import com.silva021.toolkit.extension.load
import com.silva021.toolkit.extension.onBackPressed
import com.silva021.toolkit.extension.visible

class PhotoDetailsFragment : Fragment() {

    private val binding by lazy {
        FragmentPhotoDetailsBinding.inflate(layoutInflater)
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
        arguments?.getString(ContactListFragment.PROFILE_IMAGE_LINK)?.let {
            setupImageProfile(it)
        }
    }

    private fun setupListener() {
        with(binding) {
            toolbar.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setupImageProfile(url: String) {
        with(binding) {
            profileImage.load(
                url = url,
                onSuccess = {
                    loading.gone()
                    profileImage.visible()
                },
                onError = {
                    loading.gone()
                    profileImage.visible()
                    Snackbar.make(binding.root,
                        "Erro ao carregar a imagem de perfil",
                        Snackbar.LENGTH_LONG).show()
                }
            )
        }
    }
}


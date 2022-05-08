package com.silva021.newloran.ui.list.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.silva021.newloran.databinding.ContactItemBinding
import com.silva021.newloran.data.model.Contact
import com.silva021.newloran.data.model.ContactStatus
import com.silva021.toolkit.extension.load
import com.silva021.toolkit.extension.setValueOrDefault

class ListContactAdapter(
    private val contactList: List<Contact>,
    private val onClickImageListener: (String) -> Unit,
    private val onClickLinkListener: (String) -> Unit,
) : RecyclerView.Adapter<ListContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListContactViewHolder {
        return ListContactViewHolder(
            ContactItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickImageListener,
            onClickLinkListener
        )
    }

    override fun onBindViewHolder(holder: ListContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size
}

class ListContactViewHolder(
    private val binding: ContactItemBinding,
    private val onClickImageListener: (String) -> Unit,
    private val onClickLinkListener: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(contact: Contact) {
        with(binding) {
            textStatus.setStatus(contact.status)
            contactImage.load(contact.profileImage, onClickImageListener)
            textContactName.setValueOrDefault(contact.name, "Nome: não informado")
            textContactEmail.setValueOrDefault(contact.email, "Email: não informado")
            textContactPhone.setValueOrDefault(contact.phone, "Número: não informado")
            textContactLink.setValueOrDefault(contact.profileLink, "Página: não informada")
                .setOnClickListener {
                    contact.profileLink?.let {
                        onClickLinkListener(
                            it
                        )
                    }
                }
        }
    }

    private fun TextView.setStatus(status: ContactStatus) {
        when (status) {
            ContactStatus.ACTIVE -> {
                this.setTextColor(Color.GREEN)
                this.text = "On-line"
            }
            ContactStatus.DISABLED -> {
                this.text = "Off-line"
            }
        }
    }
}
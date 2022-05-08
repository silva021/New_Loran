package com.silva021.newloran.data.model

data class Contact(
    val status: ContactStatus,
    val profileImage: String?,
    val profileLink: String?,
    val phone: String?,
    val email: String?,
    val name: String?,
    val id: String?,
)

enum class ContactStatus {
    ACTIVE,
    DISABLED
}

data class ContactList(
    val customers: List<Contact>,
)
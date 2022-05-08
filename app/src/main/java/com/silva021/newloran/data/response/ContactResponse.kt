package com.silva021.newloran.data.response

data class ContactResponse(
    val status: String,
    val profileImage: String?,
    val profileLink: String?,
    val phone: String?,
    val email: String?,
    val name: String?,
    val id: String?
)

data class ContactListResponse(
    val customers: List<ContactResponse>
)
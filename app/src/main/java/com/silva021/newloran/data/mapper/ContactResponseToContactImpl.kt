package com.silva021.newloran.data.mapper

import com.silva021.newloran.data.response.ContactResponse
import com.silva021.newloran.data.model.Contact
import com.silva021.newloran.data.model.ContactStatus
import com.silva021.toolkit.extension.valueOf

class ContactResponseToContactImpl : ContactResponseToContact {
    override fun toContactModel(response: ContactResponse) =
        Contact(
            status = valueOf(response.status, ContactStatus.ACTIVE),
            profileLink = response.profileLink,
            profileImage = response.profileImage,
            phone = response.phone,
            email = response.email,
            name = response.name,
            id = response.id
        )
}
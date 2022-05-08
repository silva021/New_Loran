package com.silva021.newloran.data.mapper

import com.silva021.newloran.data.response.ContactResponse
import com.silva021.newloran.data.model.Contact

interface ContactResponseToContact {
    fun toContactModel(
        response: ContactResponse,
    ): Contact
}
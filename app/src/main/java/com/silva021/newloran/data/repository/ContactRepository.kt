package com.silva021.newloran.data.repository

import com.silva021.newloran.data.api.ContactApi
import com.silva021.toolkit.network.ApiResponse
import com.silva021.newloran.data.mapper.ContactResponseToContact
import com.silva021.newloran.data.model.Contact
import java.lang.Exception

class ContactRepositoryImpl(
    private val contactApi: ContactApi,
    private val mapper: ContactResponseToContact,
) : ContactRepository {
    override suspend fun getListContact(): ApiResponse<List<Contact>> {
        return try {
            val contacts = contactApi.fetchContacts()
            ApiResponse.Success(
                contacts.customers.map {
                    mapper.toContactModel(it)
                }
            )
        } catch (e: Exception) {
            ApiResponse.Error(exception = e)
        }
    }
}

interface ContactRepository {
    suspend fun getListContact(): ApiResponse<List<Contact>>
}
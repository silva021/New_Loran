package com.silva021.newloran.data.api

import com.silva021.newloran.data.response.ContactListResponse
import retrofit2.http.GET

interface ContactApi {
    @GET("newloran2/testApp/main/service.json")
    suspend fun fetchContacts(): ContactListResponse
}
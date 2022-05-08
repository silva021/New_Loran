package com.silva021.newloran.di

import com.silva021.newloran.data.api.ContactApi
import com.silva021.newloran.data.repository.ContactRepository
import com.silva021.newloran.data.repository.ContactRepositoryImpl
import com.silva021.toolkit.network.createApi
import com.silva021.newloran.data.mapper.ContactResponseToContact
import com.silva021.newloran.data.mapper.ContactResponseToContactImpl
import com.silva021.newloran.ui.list.ContactListViewModel
import org.koin.dsl.module

val appModule = module {
    single { ContactListViewModel(get()) }
    single<ContactRepository> { ContactRepositoryImpl(get(), get()) }
    factory<ContactResponseToContact> { ContactResponseToContactImpl() }
    single { createApi<ContactApi>() }
}
package com.silva021.newloran.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.newloran.data.repository.ContactRepository
import com.silva021.toolkit.network.ApiResponse
import com.silva021.newloran.data.model.Contact
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val repository: ContactRepository,
) : ViewModel() {
    private val _listContact = MutableLiveData<List<Contact>>()
    val listContact = _listContact as LiveData<List<Contact>>

    private val _error = MutableLiveData<Exception>()
    val error = _error as LiveData<Exception>

    fun loadGetContact() {
        viewModelScope.launch {
            when (val contactList = repository.getListContact()) {
                is ApiResponse.Error -> _error.postValue(contactList.exception)
                is ApiResponse.Success -> {
                    contactList.data.let {
                        _listContact.postValue(it)
                    }
                }

            }
        }
    }
}
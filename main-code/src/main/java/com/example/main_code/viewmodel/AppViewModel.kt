package com.example.main_code.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.main_code.di.Network
import com.example.main_code.network.Repository
import com.example.main_code.network.RepositoryImpl
import com.example.main_code.network.model.RelatedTopic
import com.example.main_code.util.AppState
import com.example.main_code.util.ApplicationType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel(
    private val repository: Repository = RepositoryImpl(
        Network.serviceApi
    ),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var prevData: List<RelatedTopic>? = null

    var charactersType: ApplicationType? = null

    var charSelected: RelatedTopic? = null

    private val _chars: MutableLiveData<AppState<List<RelatedTopic>>> = MutableLiveData(AppState.Loading)
    val chars: LiveData<AppState<List<RelatedTopic>>> get() = _chars

    private val _search: MutableLiveData<List<RelatedTopic>?> = MutableLiveData(null)
    val search: LiveData<List<RelatedTopic>?> get() = _search

    fun getCharacters() {
        charactersType?.let {
            viewModelScope.launch(ioDispatcher) {
                repository.getCharacters(it).collect {
                    _chars.postValue(it)
                }
            }
        }
    }

    fun searchItem(string: String) {
        if (string.isNotBlank()) {
            prevData?.let {
                _search.value = it.filter { char ->
                    char.text?.contains(string, ignoreCase = true) ?: false
                }
            }
        } else {
            _search.value = prevData
        }
    }
}
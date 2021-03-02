package com.and1ss.firebasetestapp.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.and1ss.firebasetestapp.domain.LoadingState
import com.and1ss.firebasetestapp.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val messageRepository: MessageRepository,
    application: Application
) : AndroidViewModel(application) {
    var messageToSend = ""

    private var _messagesState: MutableLiveData<LoadingState<String>> = MutableLiveData()
    val messagesState: LiveData<LoadingState<String>>
        get() = _messagesState

    fun saveMessage(path: String, message: String) =
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.saveMessage(path, message)
            messageRepository.getMessage(path).collect {
                _messagesState.postValue(it)
            }
        }
}
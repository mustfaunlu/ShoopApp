package com.mustafaunlu.shoopapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.common.UserUiData
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.domain.entity.UserResponseEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductBaseMapper
import com.mustafaunlu.shoopapp.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userUseCase: UserUseCase, private val mapper: ProductBaseMapper<UserResponseEntity, UserUiData>) : ViewModel() {
    private val _loginState = MutableLiveData<NetworkResponseState<UserUiData>>()
    val loginState: LiveData<NetworkResponseState<UserUiData>> get() = _loginState

    fun login(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userUseCase(user).collect {
                when (it) {
                    is NetworkResponseState.Error -> _loginState.postValue(NetworkResponseState.Error(it.exception))
                    is NetworkResponseState.Loading -> _loginState.postValue(NetworkResponseState.Loading)
                    is NetworkResponseState.Success -> _loginState.postValue(NetworkResponseState.Success(mapper.map(it.result)))
                }
            }
        }
    }
}

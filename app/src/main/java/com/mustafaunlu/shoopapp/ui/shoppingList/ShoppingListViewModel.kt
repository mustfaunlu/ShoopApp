package com.mustafaunlu.shoopapp.ui.shoppingList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.common.ScreenState
import com.mustafaunlu.shoopapp.common.UserCartUiData
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity
import com.mustafaunlu.shoopapp.domain.mapper.ProductBaseMapper
import com.mustafaunlu.shoopapp.domain.mapper.ProductListMapper
import com.mustafaunlu.shoopapp.domain.usecase.cart.CartUseCase
import com.mustafaunlu.shoopapp.domain.usecase.cart.delete_cart.DeleteUserCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val cartUseCase: CartUseCase,
    private val deleteCartUseCase: DeleteUserCartUseCase,
    private val mapper: ProductListMapper<UserCartEntity, UserCartUiData>,
    private val singleMapper: ProductBaseMapper<UserCartUiData, UserCartEntity>,
) : ViewModel() {
    private val _userCarts = MutableLiveData<ScreenState<List<UserCartUiData>>>()
    val userCarts: LiveData<ScreenState<List<UserCartUiData>>> get() = _userCarts

    fun getCartsByUserId(userId: Int) {
        viewModelScope.launch {
            cartUseCase(userId).collect {
                when (it) {
                    is NetworkResponseState.Error -> _userCarts.postValue(ScreenState.Error(it.exception.message!!))
                    is NetworkResponseState.Loading -> _userCarts.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _userCarts.postValue(ScreenState.Success(mapper.map(it.result)))
                }
            }
        }
    }
    fun deleteUserCartItem(userCartUiData: UserCartUiData) {
        viewModelScope.launch() {
            deleteCartUseCase(singleMapper.map(userCartUiData))
        }
    }
}

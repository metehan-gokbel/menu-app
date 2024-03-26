package com.metehan.authentication.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.metehan.authentication.domain.models.MerchantList

class SharedViewModel : ViewModel() {
    private val _merchantList = MutableLiveData<MerchantList>()
    val merchantList: LiveData<MerchantList> = _merchantList

    fun setMerchantList(list: MerchantList) {
        if (_merchantList.value != list) {
            _merchantList.value = list
            println("Merchant list is updated.")
        }
    }
}

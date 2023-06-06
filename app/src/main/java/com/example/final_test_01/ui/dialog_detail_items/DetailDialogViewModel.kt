package com.example.final_test_01.ui.dialog_detail_items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.product_dto.ProductsItemsDto
import com.example.final_test_01.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailDialogViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _itemID = MutableLiveData<List<ProductsItemsDto>>()
    val itemID: LiveData<List<ProductsItemsDto>> = _itemID

    fun getItemsIdsProducts(id: Int) {
        viewModelScope.launch {
            _itemID.postValue(repository.getItemsIdsProducts(id))
        }
    }
}
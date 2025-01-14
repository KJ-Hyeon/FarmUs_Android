package com.example.farmus_application.viewmodel.farm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmus_application.model.farm.detail.DetailRes
import com.example.farmus_application.model.reserve.request.ReserveRequestReq
import com.example.farmus_application.model.reserve.request.ReserveRequestRes
import com.example.farmus_application.repository.farm.FarmRepository
import com.example.farmus_application.repository.reserve.ReserveRepository
import kotlinx.coroutines.launch

class FarmDetailViewModel(): ViewModel() {

    private val farmRepo = FarmRepository()

    private var _farmDetail = MutableLiveData<DetailRes>()
    var farmDetail: LiveData<DetailRes> = _farmDetail

    fun getFarmDetail(farmId: Int) {
        viewModelScope.launch {
            try {
                val response = farmRepo.getFarmDetail(farmId)
                if (response.isSuccessful) {
                    response.body()?.let { detailRes ->
                        _farmDetail.postValue(detailRes)
                    }
                } else {
                    Log.e("getFarmDetailResponseCode:", response.body().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
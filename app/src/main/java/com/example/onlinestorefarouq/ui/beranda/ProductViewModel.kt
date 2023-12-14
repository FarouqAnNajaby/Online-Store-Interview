package com.example.onlinestorefarouq.ui.beranda

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinestorefarouq.repository.data.model.Product
import com.example.onlinestorefarouq.repository.data.response.ResponseListProduct
import com.example.technical_test_farouq.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private val products = MutableLiveData<List<Product>?>()

    fun getListProducts(): MutableLiveData<List<Product>?> {
        return products
    }

    fun getListProductsRepo(token: String,page:Int,per_page: Int,search:String,order_direction: String){
        val data = ApiConfig.getApiService().getListProduct(token,page,per_page,search,order_direction)
        data.enqueue(object: Callback<ResponseListProduct> {
            override fun onResponse(
                call: Call<ResponseListProduct>,
                response: Response<ResponseListProduct>
            ) {
                if (response.isSuccessful){
                    products.postValue(response.body()?.data?.items as List<Product>?)
                }else{
                    Log.e("error boy", "onResponse: $response.message", )
                }
            }

            override fun onFailure(call: Call<ResponseListProduct>, t: Throwable) {
                Log.e("error boy", "onFailure: $t.message", )
            }

        })
    }
}
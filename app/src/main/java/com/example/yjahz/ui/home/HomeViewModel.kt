package com.example.yjahz.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agel.common.utils.Resource
import com.example.yjahz.data.model.Categories
import com.example.yjahz.data.model.Popular
import com.example.yjahz.data.model.TrendingProduct
import com.example.yjahz.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel(){
   val popularProductLiveData :LiveData<Resource<Popular>>get() = _popularProductLiveData
    val categoriesLiveData :LiveData<Resource<Categories>>get() = _categoriesLiveData
    val trendingProductLiveData :LiveData<Resource<TrendingProduct>>get() = _trendingProductLiveData
   private var _popularProductLiveData = MutableLiveData<Resource<Popular>>()
  private  var _categoriesLiveData = MutableLiveData<Resource<Categories>>()
    private  var _trendingProductLiveData = MutableLiveData<Resource<TrendingProduct>>()
    init {
        getCategories()
        getPopularProduct()
        getTrendingProduct()
    }
    private fun getCategories() {
        lateinit var response : Response<Categories>
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    response = homeRepository.getCategories()
                    if (response.isSuccessful) {
                        response.body()?.let {
                           _categoriesLiveData.postValue(Resource.success(it))
                        }
                    } else {
                        val errorMsg = response.errorBody()?.string()?.let {
                            JSONObject(it).getString("message")
                        }
                        Log.d("error", errorMsg.toString())
                        _categoriesLiveData.postValue(
                            Resource.error(
                                data = null,
                                message = errorMsg.toString()
                            )
                        )

                    }
                } catch (e: Exception) {
                    _categoriesLiveData.postValue(
                        Resource.error(
                            data = null,
                            message = ""
                        )
                    )
                    Log.d("categories_error", e.toString())
                }
            }
    }
   private fun getPopularProduct()  {
        lateinit var response : Response<Popular>
        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getPopularProduct()
                if (response.isSuccessful) {
                    response.body()?.let {
                       _popularProductLiveData.postValue(Resource.success(it))
                    }
                } else {
                    val errorMsg = response.errorBody()?.string()?.let {
                        JSONObject(it).getString("message")
                    }
                    Log.d("error", errorMsg.toString())
                    _popularProductLiveData.postValue(
                        Resource.error(
                            data = null,
                            message = errorMsg.toString()
                        )
                    )

                }
            } catch (e: Exception) {
                _popularProductLiveData.postValue(
                    Resource.error(
                        data = null,
                        message = ""
                    )
                )
                Log.d("categories_error", e.toString())
            }
        }
    }
  private  fun getTrendingProduct()  {
        lateinit var response : Response<TrendingProduct>
        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getTrendingProduct()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _trendingProductLiveData.postValue(Resource.success(it))
                    }
                } else {
                    val errorMsg = response.errorBody()?.string()?.let {
                        JSONObject(it).getString("message")
                    }
                    Log.d("error", errorMsg.toString())
                    _trendingProductLiveData.postValue(
                        Resource.error(
                            data = null,
                            message = errorMsg.toString()
                        )
                    )

                }
            } catch (e: Exception) {
                _trendingProductLiveData.postValue(
                    Resource.error(
                        data = null,
                        message = ""
                    )
                )
                Log.d("categories_error", e.toString())
            }
        }
    }
}
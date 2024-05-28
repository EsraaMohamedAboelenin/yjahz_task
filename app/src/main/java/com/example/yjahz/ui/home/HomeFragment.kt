package com.example.yjahz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.agel.common.utils.Status
import com.example.yjahz.common.extension.showToast
import com.example.yjahz.databinding.FragmentHomeBinding
import com.example.yjahz.ui.auth.login.LoginViewModel
import com.example.yjahz.ui.home.adapter.CategoryAdapter
import com.example.yjahz.ui.home.adapter.PopularAdapter
import com.example.yjahz.ui.home.adapter.TrendingProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment  : Fragment(){
    private  var binding : FragmentHomeBinding?= null
    private val  homeViewModel:HomeViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
         requireActivity().finish()
        }
        getCategories()
        getPopularData()
        getTrendingData()
    }

       private fun getCategories(){

            homeViewModel.categoriesLiveData.observe(viewLifecycleOwner){
                    resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {
                            val categoryAdapter = CategoryAdapter(it.data.categories)
                            binding?.categoryAdapter = categoryAdapter

                        }

                    }

                    Status.LOADING -> {}
                    Status.ERROR -> {
                        if (resource.message?.isNotEmpty() == true)
                            showToast(resource.message.toString())
                    }

                }
            }

            }

   private fun getPopularData(){

        homeViewModel.popularProductLiveData.observe(viewLifecycleOwner){
                resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let {
                        val popularAdapter = PopularAdapter(it.data.popularList)
                        binding?.popularAdapter = popularAdapter

                    }

                }

                Status.LOADING -> {}
                Status.ERROR -> {
                    if (resource.message?.isNotEmpty() == true)
                        showToast(resource.message.toString())
                }

            }
        }

    }

    private fun getTrendingData(){
        homeViewModel.trendingProductLiveData.observe(viewLifecycleOwner){
                resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let {
                        val trendingProductAdapter = TrendingProductAdapter(it.data.tendingList)
                        binding?.trendingAdapter = trendingProductAdapter

                    }
                }

                Status.LOADING -> {}
                Status.ERROR -> {
                    if (resource.message?.isNotEmpty() == true)
                        showToast(resource.message.toString())
                }

            }
        }

    }
        }



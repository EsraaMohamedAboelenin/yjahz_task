package com.example.yjahz.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.agel.common.utils.Status
import com.example.yjahz.R
import com.example.yjahz.common.extension.showToast
import com.example.yjahz.data.model.LoginRequest
import com.example.yjahz.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment  : Fragment() {
    private  var binding : FragmentLoginBinding?= null
    private val  loginViewModel : LoginViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this
        binding?.loginViewModel = loginViewModel
        binding?.loginFragment = this

    }
    val login = fun() {
        val email =  binding?.emailEditText?.text.toString()
        val password = binding?.passwordEditText?.text.toString()
        loginViewModel.login(LoginRequest( email, password)).observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let {
                        if(it.data.token.isNotEmpty())
                            goToHome()
                        else showToast(it.message)
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
    val signup = fun(){
        view?.let { Navigation.findNavController(it).navigate(R.id.signup_action) }

    }
  private  fun goToHome(){

        view?.let {
            Navigation.findNavController(it).navigate(R.id.home_action)
        }
    }
}
package com.example.yjahz.ui.auth.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.agel.common.utils.Status
import com.example.yjahz.R
import com.example.yjahz.common.extension.showToast
import com.example.yjahz.data.model.RegistrationRequest
import com.example.yjahz.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment  : Fragment() {
    private  var binding : FragmentRegistrationBinding?= null
    private val  registrationViewModel:RegistrationViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        binding = FragmentRegistrationBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this
        binding?.registrationViewModel = registrationViewModel
        binding?.registrationFragment = this

    }
    val registration = fun() {
        val name  = binding?.nameEditText?.text.toString()
        val email =  binding?.emailEditText?.text.toString()
        val password = binding?.passwordEditText?.text.toString()
        val phone = binding?.phoneEditText?.text.toString()
        registrationViewModel.register(RegistrationRequest(name, email, password, phone),binding?.confirmPasswordEditText?.text.toString()).observe(this) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let {
                    if(it.data.token.isNotEmpty())
                        goToHome()
                    else showToast(it.message)

                }}

                Status.LOADING -> {}
                Status.ERROR -> {
                    if (resource.message?.isNotEmpty() == true)
                        showToast(resource.message.toString())
                }

            }
        }

    }
    val login  = fun(){
        view?.let { Navigation.findNavController(it).navigate(R.id.login_action) }

    }
    private  fun goToHome(){

        view?.let {
            Navigation.findNavController(it).navigate(R.id.home_action)
        }
    }
}
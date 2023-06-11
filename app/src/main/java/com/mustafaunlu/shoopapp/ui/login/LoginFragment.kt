package com.mustafaunlu.shoopapp.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.shoopapp.R
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_CHECKBOX_KEY
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_USERID_KEY
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_USERNAME_DEF
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_USERNAME_KEY
import com.mustafaunlu.shoopapp.common.ScreenState
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.databinding.FragmentLoginBinding
import com.mustafaunlu.shoopapp.utils.gone
import com.mustafaunlu.shoopapp.utils.safeNavigate
import com.mustafaunlu.shoopapp.utils.showToast
import com.mustafaunlu.shoopapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setupSavedUsername()
        setupRememberCheckbox()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginButton()
    }

    private fun setupSavedUsername() {
        val savedUsername = sharedPref.getString(SHARED_PREF_USERNAME_KEY, SHARED_PREF_USERNAME_DEF)
        if (savedUsername != null) {
            binding.username.setText(savedUsername)
        }
    }

    private fun setupRememberCheckbox() {
        val checkBoxState = sharedPref.getBoolean(SHARED_PREF_CHECKBOX_KEY, false)
        binding.rememberCheckbox.isChecked = checkBoxState

        binding.rememberCheckbox.setOnCheckedChangeListener { _, isChecked ->
            sharedPref.edit().putBoolean(SHARED_PREF_CHECKBOX_KEY, isChecked).apply()
            if (isChecked) {
                sharedPref.edit()
                    .putString(SHARED_PREF_USERNAME_KEY, binding.username.text.toString().trim())
                    .apply()
            } else {
                sharedPref.edit().remove(SHARED_PREF_USERNAME_KEY).apply()
            }
        }
    }

    private fun setupLoginButton() {
        binding.loginBtn.setOnClickListener { loginLogic() }
    }

    private fun loginLogic() {
        val username = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (username.isBlank() || password.isBlank()) {
            requireView().showToast(getString(R.string.please_not_blanks))
            return
        }

        val user = User(username, password)
        viewModel.login(user)

        viewModel.loginState.observe(viewLifecycleOwner) { loginState ->
            when (loginState) {
                is ScreenState.Loading -> {
                    binding.loading.visible()
                    binding.loginBtn.isEnabled = false
                }

                is ScreenState.Success -> {
                    binding.loading.gone()
                    binding.loginBtn.isEnabled = true
                    findNavController().safeNavigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    requireView().showToast("Welcome ${loginState.uiData.username}")
                    sharedPref.edit()
                        .putString(SHARED_PREF_USERID_KEY, loginState.uiData.id.toString())
                        .apply()
                }

                is ScreenState.Error -> {
                    binding.loading.gone()
                    binding.loginBtn.isEnabled = true
                    requireView().showToast(getString(R.string.check_username_pass))
                }

                else -> {}
            }
        }
    }
}

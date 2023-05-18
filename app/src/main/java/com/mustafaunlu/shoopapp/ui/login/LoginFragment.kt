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
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_DEF
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_USERID_KEY
import com.mustafaunlu.shoopapp.common.Constants.SHARED_PREF_USERNAME_KEY
import com.mustafaunlu.shoopapp.common.NetworkResponseState
import com.mustafaunlu.shoopapp.data.dto.User
import com.mustafaunlu.shoopapp.databinding.FragmentLoginBinding
import com.mustafaunlu.shoopapp.utils.safeNavigate
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
        binding = FragmentLoginBinding.bind(inflater.inflate(R.layout.fragment_login, container, false))
        val savedUsername = sharedPref.getString(SHARED_PREF_USERNAME_KEY, SHARED_PREF_DEF)
        if (savedUsername != null) {
            binding.username.setText(savedUsername)
        }

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener { loginLogic() }
    }

    private fun loginLogic() {
        val username = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (username.isBlank() || password.isBlank()) {
            Toast.makeText(requireContext(), "Please not blanks!", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val user = User(username, password)
        viewModel.login(user)

        viewModel.loginState.observe(viewLifecycleOwner) { loginState ->
            when (loginState) {
                is NetworkResponseState.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                    binding.loginBtn.isEnabled = false
                }

                is NetworkResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    binding.loginBtn.isEnabled = true
                    findNavController().safeNavigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    Toast.makeText(
                        requireContext(),
                        "Welcome ${loginState.result.username}",
                        Toast.LENGTH_SHORT,
                    ).show()
                    sharedPref.edit()
                        .putString(SHARED_PREF_USERID_KEY, loginState.result.id.toString())
                        .apply()
                }

                is NetworkResponseState.Error -> {
                    binding.loading.visibility = View.GONE
                    binding.loginBtn.isEnabled = true
                    Toast.makeText(
                        requireContext(),
                        "${loginState.exception.message}",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }
    }
}

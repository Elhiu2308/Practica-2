package com.example.practica2

import android.content.res.Configuration
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.practica2.databinding.FragmentLoginBinding
import java.util.Locale


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var VALID_USER: String
    private lateinit var VALID_PASS: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        VALID_USER = getString(R.string.usuario_valido)
        VALID_PASS = getString(R.string.contrasena_valida)

        setLoginButtonState(false)

        binding.etCorreoElectronico.addTextChangedListener {
            binding.tilCorreoElectronico.error = null
            setLoginButtonState(validaFields())
        }

        binding.etContrasena.addTextChangedListener {
            binding.tilContrasena.error = null
            setLoginButtonState(validaFields())
        }

        binding.btnIngresar.setOnClickListener {
            if (validaFields()) {
                val email = binding.etCorreoElectronico.text.toString().trim()

                val password = binding.etContrasena.text.toString()

                if (email == VALID_USER && password == VALID_PASS) {

                    val bienvenidoFragment = BienvenidoFragment().apply {

                            arguments = Bundle().apply {
                                putString("usuario", email)
                            }
                        }

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, bienvenidoFragment)
                        .addToBackStack(null)
                        .commit()

                } else {
                    if (email != VALID_USER) {
                        binding.tilCorreoElectronico.error = getString(R.string.error_correo_invalido)
                    }

                    if (password != VALID_PASS) {
                        binding.tilContrasena.error = getString(R.string.error_contrasena_invalida)
                    }

                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_credenciales_invalidas), Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnCambiarIdioma.setOnClickListener {
            val currentLocale = resources.configuration.locales[0]

            val newLocale = if (currentLocale.language == "es") {
                    Locale("en", "US")
                } else {
                    Locale("es", "ES")
                }
            setLocale(newLocale)
        }
    }
    private fun validaFields(): Boolean {
        val email = binding.etCorreoElectronico.text.toString().trim()

        val password = binding.etContrasena.text.toString()

        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isNotEmpty()

    }
    private fun setLoginButtonState(enabled: Boolean) {

        binding.btnIngresar.isEnabled = enabled

        val colorRes =
            if (enabled) {
                R.color.verde
            } else {
                R.color.azul
            }

        binding.btnIngresar.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), colorRes)
    }



    private fun setLocale(locale: Locale) {

        val config = Configuration(resources.configuration)
        config.setLocale(locale)

        resources.updateConfiguration(config, resources.displayMetrics)

        Locale.setDefault(locale)

        requireActivity().recreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
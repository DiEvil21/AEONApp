package ru.dievil.aeonapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ru.dievil.aeonapp.R

import androidx.lifecycle.ViewModelProvider
import ru.dievil.aeonapp.ViewModel.AuthViewModel
import ru.dievil.aeonapp.model.LoginRequest

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Инициализация
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        editTextUsername = findViewById(R.id.et_login)
        editTextPassword = findViewById(R.id.et_password)
        loginButton = findViewById(R.id.btn_login)

        // Слушатель на кнопку входа
        loginButton.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            // Проверка ввели ли данные
            if (username.isNotEmpty() && password.isNotEmpty()) {
                val loginRequest = LoginRequest(username, password)
                Log.d("retrofit", loginRequest.toString())
                // Вызов метода для входа
                viewModel.login(loginRequest).observe(this) { token ->
                    if (token.isNotEmpty()) {
                        Log.d("retrofit", token)
                        val intent = Intent(this, PaymentsActivity::class.java)
                        // Передаем токен в PaymentsActivity
                        intent.putExtra("TOKEN", token)
                        startActivity(intent)
                    } else {
                        // Неудачный вход
                        Toast.makeText(this, "Неудачный вход", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Пустые данные
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


package ru.dievil.aeonapp.View

import android.content.Context
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

    private val sharedPreferences by lazy {
        getSharedPreferences("login_preferences", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Инициализация
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        editTextUsername = findViewById(R.id.et_login)
        editTextPassword = findViewById(R.id.et_password)
        loginButton = findViewById(R.id.btn_login)

        // Попытка автоматического входа
        if (isLoggedIn()) {
            val savedUsername = sharedPreferences.getString("username", "")
            val savedPassword = sharedPreferences.getString("password", "")

            editTextUsername.setText(savedUsername)
            editTextPassword.setText(savedPassword)

            // Выполняем вход
            if (savedUsername!= null && savedPassword != null) {
                login(savedUsername, savedPassword)
            }
        }

        // Слушатель на кнопку входа
        loginButton.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            // Проверка ввели ли данные
            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Сохраняем логин и пароль
                saveCredentials(username, password)

                // Вызов метода для входа
                login(username, password)
            } else {
                // Пустые данные
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun saveCredentials(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    private fun login(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)
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
    }
}



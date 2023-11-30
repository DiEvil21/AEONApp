package ru.dievil.aeonapp.View

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

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        // Инициализация полей ввода и кнопки
        editTextUsername = findViewById(R.id.et_login)
        editTextPassword = findViewById(R.id.et_password)
        loginButton = findViewById(R.id.btn_login)

        // Добавление слушателя на кнопку входа
        loginButton.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            // Проверка наличия введенных данных
            if (username.isNotEmpty() && password.isNotEmpty()) {
                val loginRequest = LoginRequest(username, password)
                Log.d("retrofit", loginRequest.toString())
                // Вызов метода для входа
                viewModel.login(loginRequest).observe(this) { token ->
                    if (token.isNotEmpty()) {
                        Log.d("retrofit", token)
                        // Вход успешен, вызов метода для получения данных о платежах
                        /*viewModel.getPayments(token).observe(this) { payments ->
                            // Обработка полученных данных о платежах
                            // Можете, например, перейти на другую активность с отображением платежей
                            Log.d("payments", payments.toString())
                        }*/
                    } else {
                        // Обработка неудачного входа
                        Toast.makeText(this, "Неудачный вход", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Пользователь не ввел данные
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


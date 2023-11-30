package ru.dievil.aeonapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.dievil.aeonapp.R
import androidx.lifecycle.ViewModelProvider
import ru.dievil.aeonapp.ViewModel.AuthViewModel
import ru.dievil.aeonapp.model.Payment

class PaymentsActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)
        val token = intent.getStringExtra("TOKEN")
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        if (token != null) {
            viewModel.getPayments(token).observe(this) {payments ->
                Log.d("payments", payments.toString())

            }
        }
        //val paymentRequest = Payment(token)
        // Здесь добавьте логику для отображения списка платежей
    }
}

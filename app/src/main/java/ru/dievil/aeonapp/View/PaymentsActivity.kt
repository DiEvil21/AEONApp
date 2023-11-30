package ru.dievil.aeonapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.dievil.aeonapp.R
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dievil.aeonapp.ViewModel.AuthViewModel

class PaymentsActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PaymentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)

        val token = intent.getStringExtra("TOKEN")
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PaymentsAdapter(emptyList())
        recyclerView.adapter = adapter

        if (token != null) {
            viewModel.getPayments(token).observe(this) { payments ->
                Log.d("payments", payments.toString())
                if (payments != null) {
                    adapter = PaymentsAdapter(payments)
                }
                recyclerView.adapter = adapter
            }
        }
    }
}


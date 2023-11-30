package ru.dievil.aeonapp.View

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.dievil.aeonapp.R
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.dievil.aeonapp.ViewModel.AuthViewModel

class PaymentsActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PaymentsAdapter
    private lateinit var fabLogout: FloatingActionButton

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
        fabLogout = findViewById(R.id.fabLogout)
        fabLogout.setOnClickListener {
            logout()
        }
    }
    private fun logout() {
        clearCredentials()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun clearCredentials() {
        val sharedPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}


package ru.dievil.aeonapp.View

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dievil.aeonapp.R
import ru.dievil.aeonapp.model.Payment

class PaymentsAdapter(private val payments: List<Payment>) : RecyclerView.Adapter<PaymentsAdapter.PaymentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_payment, parent, false)
        return PaymentViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = payments[position]
        holder.bind(payment)
    }

    override fun getItemCount(): Int {
        return payments.size
    }

    class PaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.tvPaymentId)
        private val titleTextView: TextView = itemView.findViewById(R.id.tvPaymentTitle)
        private val amountTextView: TextView = itemView.findViewById(R.id.tvPaymentAmount)
        private val createdTextView: TextView = itemView.findViewById(R.id.tvPaymentCreated)

        @SuppressLint("SetTextI18n")
        fun bind(payment: Payment) {
            idTextView.text = "ID: ${payment.id}"
            titleTextView.text = "Title: ${payment.title}"
            amountTextView.text = "Amount: ${payment.amount}"
            createdTextView.text = "Created: ${payment.created}"
        }
    }
}

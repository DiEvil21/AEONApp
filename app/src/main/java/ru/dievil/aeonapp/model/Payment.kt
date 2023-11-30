package ru.dievil.aeonapp.model

data class Payment(
    val id: Int,
    val title: String,
    val amount: Any,
    val created: Long
)

data class PaymentsResponse(
    val success: String,
    val response: List<Payment>?
)



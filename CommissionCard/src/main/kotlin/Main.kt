package org.example

fun main() {
    val cardType =
        //"Visa"
        "MasterCard"
        //"Мир"
    val transfer = 140_000
    val transferCount = 75_000
    val dailyTotal = 0
    val monthlyTotal = 300_000

    limitTransfer(dailyTotal, monthlyTotal, cardType, transfer, transferCount)
}

fun limitTransfer(dailiTotal: Int, monthlyTotal: Int, cardType: String, transfer: Int, transferCount: Int) {
    val dailyLimit = 150_000
    val monthlyLimit = 600_000
    if (dailiTotal + transfer > dailyLimit) {
        return println("Превышен суточный лимит")
    }
    if (monthlyTotal + transfer > monthlyLimit) {
        return println("Превышен месячный лимит")
    }
    val transferCount = transferCount + transfer
    val commis = when {
        (cardType == "MasterCard") -> if (transfer > 75_000 || transferCount > 75_000) {
            (transferCount - 75_000) * 0.006 + 20} else 0
        (cardType == "Visa") -> if (transfer * 0.0075 < 35) {35} else {0.0075 * transfer}
        else -> 0
    }
    return println("Перевод успешно выполнен. \nКомиссия составила: $commis")
}
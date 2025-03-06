package org.example

import kotlin.math.max


val scan = java.util.Scanner(System.`in`)
val dailyLimit = 150_000
val monthlyLimit = 600_000
val excessRateVisa = 0.0075
val excessRateMastercard = 0.006
val transferCount = 75_000
val minCommisVisa = 35
val minCommisMastercard = 20

fun main() {

    println("Введите тип карты")
    println("1 - Мир")
    println("2 - Visa")
    println("3 - MasterCard")
    val cardType = scan.nextInt()
    println("Введите сумму перевода")
    val transfer = scan.nextInt()

    printCommis(calculateCommis(cardType, transfer, transferCount))
}

fun calculateCommis(cardType: Int, transfer: Int, transferCount: Int): Int {

    if (transfer > dailyLimit) return -1 // Превышение суточного лимита
    if (transfer > monthlyLimit) return -2 // Превышение месячного лимита

    return when (cardType) {
        1 -> 0 // Комиссия за карту МИР
        2 -> max(minCommisVisa, (transfer * excessRateVisa).toInt()) // Комиссия за карту Visa
        3 -> { // Комиссия за карту MasterCard
            when {
                transfer <= transferCount -> 0
                else -> {
                    val excessAmount = transfer - transferCount
                    val commis = (excessAmount * excessRateMastercard) + minCommisMastercard
                    commis.toInt()
                }
            }
        }

        else -> -3 // Ошибка ввода типа карты
    }
}

fun printCommis(commis: Int) {
    if (commis == -1) println("Превышение суточного лимита")
    else if (commis == -2) println("Превышение месячного лимита")
    else if (commis == -3) println("Ошибка ввода типа карты")
    else println("Перевод успешно выполнен. \nКомиссия составила: $commis")
}


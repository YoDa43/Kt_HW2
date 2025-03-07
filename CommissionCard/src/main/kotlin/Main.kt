import kotlin.math.max

val dailyLimit = 150_000
val monthlyLimit = 600_000
val excessRateVisa = 0.0075
val excessRateMastercard = 0.006
val limitTransferCount = 75_000
val minCommisVisa = 35
val minCommisMastercard = 20


fun main() {

    println("Введите тип карты")
    println("1 - МИР")
    println("2 - Visa")
    println("3 - MasterCard")
    val cardType = readln()
    println("Введите сумму перевода")
    val transfer = readln().toInt()

    printCommis(calculateCommis(cardType = cardType, transfer))

}

fun calculateCommis(cardType: String = "МИР", transfer: Int, transferCount: Int = 0): Int {
    var transferCount = 0
    transferCount += transfer
    if (transferCount > dailyLimit) {
        return -1 // Превышение суточного лимита>)
    }
    if (transferCount > monthlyLimit) {
        return -2 // Превышение месячного лимита
    }

    return when (cardType) {
        "МИР", "1", null.toString(), "" -> 0// Комиссия за карту МИР
        "Visa", "2" -> max(minCommisVisa, (transfer * excessRateVisa).toInt()) // Комиссия за карту Visa
        "MasterCard", "3" -> { // Комиссия за карту MasterCard
            when {
                transferCount <= limitTransferCount -> 0
                else -> {
                    val excessAmount = transferCount - limitTransferCount
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


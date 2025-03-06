import kotlin.math.max

val dailyLimit = 150_000
val monthlyLimit = 600_000
val excessRateVisa = 0.0075
val excessRateMastercard = 0.006
val limitTransferCount = 75_000
val minCommisVisa = 35
val minCommisMastercard = 20
var transferCount = 0

fun main() {

    println("Введите тип карты")
    println("1 - МИР")
    println("2 - Visa")
    println("3 - MasterCard")
    val cardType = readln()
    println("Введите сумму перевода")
    val transfer = readln().toInt()

    printCommis(calculateCommis(cardType = cardType, transfer, transferCount = transferCount))
    transferCount += transfer
}

fun calculateCommis(cardType: String = "МИР", transfer: Int, transferCount: Int = 0): Int {
    if (transfer > dailyLimit) {
        return -1 // Превышение суточного лимита>)
    }
    if (transferCount > monthlyLimit) {
        return -2 // Превышение месячного лимита
    }

    return when (cardType) {
        (cardType.equals("МИР") || cardType.equals("1") || cardType.equals(null)).toString() -> 0 // Комиссия за карту МИР
        (cardType.equals("Visa") || cardType.equals("2")).toString() -> max(minCommisVisa, (transfer * excessRateVisa).toInt()) // Комиссия за карту Visa
        (cardType.equals("MasterCard") || cardType.equals("3")).toString() -> { // Комиссия за карту MasterCard
            when {
                transferCount <= limitTransferCount -> 0
                else -> {
                    val excessAmount = transfer - limitTransferCount
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


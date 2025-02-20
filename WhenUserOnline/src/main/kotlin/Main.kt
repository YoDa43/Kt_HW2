package org.example


fun main() {
    val curentTime = 25929

    val formating = if (curentTime < 3600) {
        formatToMinute(curentTime / 60)
    } else {
        formatToHour(curentTime)
    }

    println(agoToText(curentTime, formating))
}

fun agoToText(curentTime: Int, text: String): String {
    when {
        (0 < curentTime && curentTime < 60) -> return "был(а) только что"
        (59 < curentTime && curentTime < 3600) -> return "был(а) ${curentTime / 60} $text назад"
        (3599 < curentTime && curentTime < 86400) -> return "был(а) ${curentTime / 60 / 60} $text назад"
        (86399 < curentTime && curentTime < 172800) -> return "был(а) вчера"
        (172799 < curentTime && curentTime < 259200) -> return "был(а) позавчера"
        else -> return " был(а) давно"
    }
}

fun formatToMinute(curentTime: Int): String {
    when {
        (curentTime == 1 || 20 < curentTime && curentTime % 10 == 1) -> {
            return "минуту"
        }

        (curentTime == 2 ||
                curentTime == 3 ||
                curentTime == 4 ||
                20 < curentTime && curentTime % 10 == 2 ||
                20 < curentTime && curentTime % 10 == 3 ||
                20 < curentTime && curentTime % 10 == 4
                ) -> {
            return "минуты"
        }

        else -> {
            return "минут"
        }
    }
}

fun formatToHour(curentTime: Int): String {
    when {
        (curentTime > 3599 && curentTime < 7200 || curentTime > 75601 && curentTime < 79280) -> {
            return "час"
        }

        (curentTime > 7199 && curentTime < 18000 || curentTime > 75599 && curentTime < 89999) -> {
            return "часа"
        }

        else -> {
            return "часов"
        }
    }
}
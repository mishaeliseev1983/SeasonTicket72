package com.melyseev.seasonticket72.utils

import com.google.gson.Gson
import com.melyseev.seasonticket72.data.seasonticket.SeasonTicketEntity
import com.melyseev.seasonticket72.data.user.UserEntity
import com.melyseev.seasonticket72.screens.seasonticket.model.OneExercise
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.graphics.Color
import com.melyseev.seasonticket72.theme.baseLightPalette
import com.melyseev.seasonticket72.theme.blueDarkPalette
import com.melyseev.seasonticket72.theme.greenDarkPalette
import com.melyseev.seasonticket72.theme.redDarkPalette


const val FORMAT_DATE = "dd.MM.yyyy"
const val MAX_SHOW_DAYS_IN_ROW = 4

fun String.fromStringToDate(): Date = SimpleDateFormat(FORMAT_DATE).parse(this)

fun Date.fromDateToString(): String = FORMAT_DATE.format(this)

fun getTitleForADayOnlyMonthYear(currentDate: Date): String {
    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    val dateFormat = SimpleDateFormat("MMM yyyy", Locale.getDefault())
    return dateFormat.format(currentDate)
}

fun getTitleForADay(currentDate: Date): String {
    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    /*
    val difference = Calendar.getInstance().timeInMillis - calendar.timeInMillis
    val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())

    return when (TimeUnit.MILLISECONDS.toDays(difference)) {
        0L -> "Today"
        1L -> "Yesterday"
        else -> dateFormat.format(currentDate)
    }

     */
    return dateFormat.format(currentDate)
}


fun getTitleForADay(currentDateString: String): String {
    val currentDate = currentDateString.fromStringToDate()
    val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    return dateFormat.format(currentDate)
}

fun getTitleDayOnlyDay(currentDateString: String): String {
    val currentDate = currentDateString.fromStringToDate()
    val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
    return dateFormat.format(currentDate)
}


fun getAllDaysSold(beginDate: Date, countSold: Int): MutableList<OneExercise> {
    val calendar = Calendar.getInstance()
    calendar.time = beginDate
    val returnList = mutableListOf<OneExercise>()

    while (returnList.size != countSold) {
        //tuesday or friday
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            val oneExercise = OneExercise(
                date = toSimpleString(calendar.time),
                index = returnList.size)
            returnList.add(oneExercise)
        }
        calendar.add(Calendar.DATE, 1)
    }
    return returnList
}

fun getAllDaysExercises(month: Date): MutableList<OneExercise> {
    val calendar = Calendar.getInstance()
    calendar.time = month
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH))

    val returnList = mutableListOf<OneExercise>()
    val daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    var i = 1
    while (i < daysOfMonth) {
        //tuesday or friday
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            val oneExercise = OneExercise(date = toSimpleString(calendar.time))
            returnList.add(oneExercise)
        }
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        i++
    }
    return returnList
}


fun toSimpleString(date: Date): String {
    val format = SimpleDateFormat(FORMAT_DATE)
    return format.format(date)
}

fun getNameSurname(users: List<UserEntity>, id: Long): String {
    return users.find { it.id == id }?.let { "${it.name} ${it.surname}" } ?: "no name"

}



fun mapStringExercisesToListOneExercises(stringExercises: String): List<OneExercise> {
    val gson = Gson()
    return gson.fromJson(stringExercises, Array<OneExercise>::class.java)
        .toMutableList()
}

fun getColorCircle(status: Int, openStatus: Boolean?): Color{
    var colorStatus =  baseLightPalette.primaryBackground

    if(openStatus == null) return colorStatus
//    if(!openStatus) return  colorStatus

    if(status == 1)
        colorStatus = greenDarkPalette.tintColor
    else
        if(status == 2)
            colorStatus =  redDarkPalette.tintColor
    return colorStatus
}

fun getBackgroundColorCard(openStatus: Boolean?) : Color{
    var color = baseLightPalette.primaryBackground

    if(openStatus == null) return color
    if(openStatus == false) return  baseLightPalette.secondaryText

    return blueDarkPalette.tintColor
}


fun getNewStatusExercise(status: Int): Int {
    var newStatus  = 0
    if(status == 0) newStatus=status+1
    if(status == 1) newStatus=status+1
    if(status == 2) newStatus=0
    return newStatus
}

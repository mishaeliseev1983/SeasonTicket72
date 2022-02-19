package com.melyseev.seasonticket72.main

import androidx.annotation.StringRes
import com.melyseev.seasonticket72.R

sealed class MainBottomScreen(val route: String, @StringRes val resourceId: Int) {
    object SeasonTicket : MainBottomScreen("seasonticketflow", R.string.title_season_ticket)
    object UserList : MainBottomScreen("userlistflow", R.string.title_users)
    object SellTicket : MainBottomScreen("sellticketflow", R.string.sell_ticket)
    //object AddUser : Screen("adduser", R.string.add_user)
}
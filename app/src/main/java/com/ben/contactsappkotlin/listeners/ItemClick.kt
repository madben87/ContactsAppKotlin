package com.ben.contactsappkotlin.listeners

import android.view.View

interface ItemClick {

    fun onItemClick(view: View, position: Int)
}
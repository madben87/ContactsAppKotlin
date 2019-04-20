package com.ben.contactsappkotlin.ui.components

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ben.contactsappkotlin.R
import com.ben.contactsappkotlin.data.Person
import com.ben.contactsappkotlin.databinding.PersoneItemBinding
import com.ben.contactsappkotlin.listeners.ItemClick

class PersonListAdapter : RecyclerView.Adapter<PersonListAdapter.PersonHolder>() {

    private val persons: ArrayList<Person> = arrayListOf()
    private var listener: ItemClick? = null

    fun setData(persons: List<Person>) {
        this.persons.clear()
        this.persons.addAll(persons)
        notifyDataSetChanged()
    }

    fun setListener(listener: ItemClick) {
        this.listener = listener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PersonHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.persone_item, p0, false)
        return PersonHolder(view)
    }

    override fun onBindViewHolder(holder: PersonHolder, p1: Int) {
        val item: Person = persons[p1]
        holder.binding?.person = item
        holder.binding!!.root.setOnClickListener {
            if (it != null) {
                listener?.onItemClick(it, p1)
            }
        }
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    class PersonHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: PersoneItemBinding? = DataBindingUtil.bind(view)
    }
}
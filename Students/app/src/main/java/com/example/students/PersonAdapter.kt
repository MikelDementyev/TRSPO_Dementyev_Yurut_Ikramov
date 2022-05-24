package com.example.students

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_person.view.*

class PersonAdapter(
    val persons: List<PersonModel>,
    val context: Context
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val fio = view.fio_text
        val flg = view.flg_text
        val v_kori = view.v_kori_text
        val rv_kori = view.rv_kori_text
        val covid = view.covid_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_person, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.fio.text = persons[position].fio
        viewHolder.flg.text = "ФЛГ: " + persons[position].flg.toString()
        viewHolder.v_kori.text = "в кори: " + persons[position].vKori.toString()
        viewHolder.rv_kori.text = "рв кори: " + persons[position].rvKori.toString()
        viewHolder.covid.text = "COVID-19: " + persons[position].covid.toString()
    }

    override fun getItemCount(): Int = persons.size
}
package com.example.appteste1


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MyAdapter(var ctx:Context, var ressource: Int, var Item: ArrayList<Procedimento>):
    ArrayAdapter<Procedimento>(ctx, ressource, Item) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(ressource, null)

        //Get items
        val dataProced: TextView = view.findViewById<TextView>(R.id.listaDataHora)
        val nomeProced: TextView = view.findViewById<TextView>(R.id.listaEspecialidade2)
        val infoProced: ImageView = view.findViewById<ImageView>(R.id.listInfo)
        val editProced: ImageView = view.findViewById<ImageView>(R.id.listEdit)
        val delProced: ImageView = view.findViewById<ImageView>(R.id.listDelete)

        //Set items
        dataProced.text = Item[position].dataHora
        nomeProced.text = Item[position].procedim
        infoProced.setImageDrawable(ctx.resources.getDrawable(Item[position].infoId))
        editProced.setImageDrawable(ctx.resources.getDrawable(Item[position].editId))
        delProced.setImageDrawable(ctx.resources.getDrawable(Item[position].delId))



        return view
    }
}
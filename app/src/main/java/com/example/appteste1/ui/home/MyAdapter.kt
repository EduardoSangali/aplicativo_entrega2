package com.example.appteste1.ui.home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.appteste1.R

class MyAdapter(var ctx:Context, var ressource: Int, var Item: ArrayList<Procedimento_>, var hasInfo: Boolean, var hasEdit: Boolean, var hasDel: Boolean):
                ArrayAdapter<Procedimento_>(ctx, ressource, Item) {


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

        if(hasInfo) {
            infoProced.setOnClickListener(){
                Toast.makeText(ctx, Item[position].id, Toast.LENGTH_SHORT).show()
            }
        }

        if(hasEdit) {
            editProced.setOnClickListener(){
                Toast.makeText(ctx, Item[position].id, Toast.LENGTH_SHORT).show()
            }
        }

        if(hasDel) {
            delProced.setOnClickListener(){
                Toast.makeText(ctx, Item[position].id, Toast.LENGTH_SHORT).show()
            }
        }

        infoProced.setImageDrawable(ctx.resources.getDrawable(Item[position].infoId))
        editProced.setImageDrawable(Item[position].editId?.let { ctx.resources.getDrawable(it) })
        delProced.setImageDrawable(Item[position].delId?.let { ctx.resources.getDrawable(it) })


        return view
    }
}
package fi.centria.tki.kertaus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class NettiAdapteri(private val context: Context?, private val dataSource:TiedotLuokka) :BaseAdapter() {
    private val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private class ViewHolder{
        lateinit var nimi: TextView
        lateinit var osoite: TextView
        lateinit var kuva: ImageView
    }

    override fun getCount(): Int {
        return dataSource.formit.size
    }

    override fun getItem(p0: Int): Any {
        return dataSource.formit.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View
        val holder: ViewHolder
        if (p1 == null){
            view = inflater.inflate(R.layout.nettilista, p2, false)
            holder = ViewHolder()
            holder.nimi = view.findViewById(R.id.lista_nimi)
            holder.osoite = view.findViewById(R.id.lista_osoite)
            holder.kuva = view.findViewById(R.id.lista_kuva)
            view.tag = holder
        }
        else{
            view = p1
            holder = p1.tag as ViewHolder
        }
        holder.nimi.text = dataSource.formit.get(p0).nimi
        holder.osoite.text = dataSource.formit.get(p0).osoite
        if (p0 % 2 == 0){
            holder.kuva.setImageResource(R.drawable.centria)
        }
        else{
            holder.kuva.setImageResource(R.drawable.kuva2)
        }

        return view
    }
}
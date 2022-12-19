package fi.centria.tki.kertaus

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NettiFragmentti.newInstance] factory method to
 * create an instance of this fragment.
 */
class NettiFragmentti : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var haku_nappi: Button
    lateinit var netti_lista:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_netti_fragmentti, container, false)
        haku_nappi = view.findViewById(R.id.aloita_haku)
        netti_lista = view.findViewById(R.id.netti_lista)
        haku_nappi.setOnClickListener({
            lifecycleScope.launch {
                var teksti = httpGet("https://api.nobelprize.org/2.1/laureates")
                Log.d("teksti",teksti.toString())
                val gson = Gson()
                var nobelType = object: TypeToken<Nobel>() {}.type
                var nobels: Nobel = gson.fromJson(teksti, nobelType)
                Log.d("nobels",nobels.toString())
                var lista: ArrayList<FormiLuokka> = ArrayList()
                for (x in 0 until nobels.laureates.size){
                    Log.d("one winner",nobels.laureates.get(x).birth.place.cityNow.en)
                    var formi: FormiLuokka = FormiLuokka()
                    formi.nimi = nobels.laureates.get(x).knownName.en
                    formi.osoite = nobels.laureates.get(x).birth.place.cityNow.en
                    lista.add(formi)

                }

                TiedotLuokka.tiedot.formit = lista
                val adapteri: NettiAdapteri
                adapteri = NettiAdapteri(context, TiedotLuokka.hae())
                netti_lista.adapter = adapteri
            }
        })
        return view
    }

    suspend fun httpGet(urli: String?): String?{
        val tulos = withContext(Dispatchers.IO){
            val inputStream: InputStream
            var url: URL = URL(urli)
            val yhteys: HttpsURLConnection = url.openConnection() as HttpsURLConnection
            yhteys.connect()
            inputStream = yhteys.inputStream
            if (inputStream != null){
                convertInputStreamToString(inputStream)
            }
            else{
                "virhe"
            }
        }
        return tulos.toString()
    }

    private fun convertInputStreamToString(inputStream: InputStream): String {
        val bufferedReader: BufferedReader? = BufferedReader(InputStreamReader(inputStream))
        var line:String? = bufferedReader?.readLine()
        var result:String = ""
        while (line != null) {
            result += line
            line = bufferedReader?.readLine()
        }
        inputStream.close()
        Log.d("input",result.toString())
        return result
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NettiFragmentti.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NettiFragmentti().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
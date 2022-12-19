package fi.centria.tki.kertaus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PeliFragmentti.newInstance] factory method to
 * create an instance of this fragment.
 */
class PeliFragmentti : Fragment(), OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var pelaaja1 = true
    var painallusnumero = 0
    var peliloppui = false
    var tarkista = arrayOf("0","1","2","3","4","5","6","7","8")

    lateinit var pelinappi1: Button
    lateinit var pelinappi2: Button
    lateinit var pelinappi3: Button
    lateinit var pelinappi4: Button
    lateinit var pelinappi5: Button
    lateinit var pelinappi6: Button
    lateinit var pelinappi7: Button
    lateinit var pelinappi8: Button
    lateinit var pelinappi9: Button
    lateinit var vuoroteksti: TextView

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
        var view = inflater.inflate(R.layout.fragment_peli_fragmentti, container, false)
        vuoroteksti = view.findViewById(R.id.pelaajan_vuoro)
        vuoroteksti.text = "Pelaaja 1"
        pelinappi1 = view.findViewById(R.id.pelinappi1)
        pelinappi2 = view.findViewById(R.id.pelinappi2)
        pelinappi3 = view.findViewById(R.id.pelinappi3)
        pelinappi4 = view.findViewById(R.id.pelinappi4)
        pelinappi5 = view.findViewById(R.id.pelinappi5)
        pelinappi6 = view.findViewById(R.id.pelinappi6)
        pelinappi7 = view.findViewById(R.id.pelinappi7)
        pelinappi8 = view.findViewById(R.id.pelinappi8)
        pelinappi9 = view.findViewById(R.id.pelinappi9)
        pelinappi1.setOnClickListener(this)
        pelinappi2.setOnClickListener(this)
        pelinappi3.setOnClickListener(this)
        pelinappi4.setOnClickListener(this)
        pelinappi5.setOnClickListener(this)
        pelinappi6.setOnClickListener(this)
        pelinappi7.setOnClickListener(this)
        pelinappi8.setOnClickListener(this)
        pelinappi9.setOnClickListener(this)
        pelaaja1 = true
        tarkista = arrayOf("0","1","2","3","4","5","6","7","8")
        painallusnumero = 0
        peliloppui = false
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PeliFragmentti.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PeliFragmentti().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.pelinappi1, R.id.pelinappi2,R.id.pelinappi3,R.id.pelinappi4,R.id.pelinappi5,R.id.pelinappi5, R.id.pelinappi6,R.id.pelinappi7,R.id.pelinappi8,R.id.pelinappi9 -> {
                val nappi = p0 as Button
                pelaa(nappi)
            }
        }
    }

    fun pelaa(nappi:Button){
        val tag = nappi.tag.toString()
        if (pelaaja1){
            nappi.text = "X"
            pelaaja1 = !pelaaja1
            vuoroteksti.text = "Pelaaja 2"
            tarkista[tag.toInt()] = "X"
        }
        else{
            nappi.text = "O"
            pelaaja1 = !pelaaja1
            vuoroteksti.text = "Pelaaja 1"
            tarkista[tag.toInt()] = "O"
        }
        nappi.isEnabled = false
        tarkistaVoitto(tarkista)
    }

    fun tarkistaVoitto(data: Array<String>){
        val ehto1 = data[0] == data[1] && data[0] == data[2]
        val ehto2 = data[3] == data[4] && data[3] == data[5]
        val ehto3 = data[6] == data[7] && data[6] == data[8]
        val ehto4 = data[0] == data[3] && data[0] == data[6]
        val ehto5 = data[1] == data[4] && data[1] == data[7]
        val ehto6 = data[2] == data[5] && data[2] == data[8]
        val ehto7 = data[0] == data[4] && data[0] == data[8]
        val ehto8 = data[2] == data[4] && data[2] == data[6]
        val ehtoLista: Array<Boolean> = arrayOf(ehto1, ehto2, ehto3, ehto4, ehto5, ehto6, ehto7, ehto8)
        for (i in ehtoLista) if (i){
            peliloppu(false)
            peliloppui = true
            return
        }
        if (painallusnumero == 8 && !peliloppui){
            peliloppu(true)
        }
        else{
            painallusnumero = painallusnumero + 1
        }
    }

    fun peliloppu(tasapeli:Boolean){
        if (tasapeli){
            Toast.makeText(this.context, "Peli loppui, tasapeli",Toast.LENGTH_LONG).show()
        }
        else{
            if (pelaaja1){
                Toast.makeText(this.context, "Peli loppui, pelaaja 2 voitti", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this.context, "Peli loppui, pelaaja 1 voitti", Toast.LENGTH_LONG).show()
            }
        }

        var fragmentTransaction = this.parentFragmentManager.beginTransaction()
        val peli = parentFragmentManager.findFragmentByTag("pelifragmentti")
        fragmentTransaction.detach(peli!!).commit()
        fragmentTransaction = this.parentFragmentManager.beginTransaction()
        fragmentTransaction.attach(peli).commit()
    }
}
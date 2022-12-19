package fi.centria.tki.kertaus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OhjausFragmentti.newInstance] factory method to
 * create an instance of this fragment.
 */
class OhjausFragmentti : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var laskuri_nappi:Button
    lateinit var formi_nappi:Button
    lateinit var lista_nappi:Button
    lateinit var peli_nappi:Button
    lateinit var netti_nappi:Button

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
        var view = inflater.inflate(R.layout.fragment_ohjaus_fragmentti, container, false)
        laskuri_nappi = view.findViewById(R.id.laskurinappi)
        formi_nappi = view.findViewById(R.id.forminappi)
        lista_nappi = view.findViewById(R.id.listanappi)
        peli_nappi = view.findViewById(R.id.pelinappi)
        netti_nappi = view.findViewById(R.id.nettinappi)

        laskuri_nappi.setOnClickListener({
            var fragmentTransaction = this.parentFragmentManager.beginTransaction()
            var laskuriFragmentti = LaskuriFragmentti()
            fragmentTransaction.replace(R.id.ylaosa, laskuriFragmentti)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        })
        formi_nappi.setOnClickListener({
            var fragmentTransaction = this.parentFragmentManager.beginTransaction()
            var formiFragmentti = FormiFragmentti()
            fragmentTransaction.replace(R.id.ylaosa, formiFragmentti)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        })
        lista_nappi.setOnClickListener({
            var fragmentTransaction = this.parentFragmentManager.beginTransaction()
            var listaFragmentti = ListaFragmentti()
            fragmentTransaction.replace(R.id.ylaosa, listaFragmentti)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        })
        peli_nappi.setOnClickListener({
            var fragmentTransaction = this.parentFragmentManager.beginTransaction()
            var peliFragmentti = PeliFragmentti()
            fragmentTransaction.replace(R.id.ylaosa, peliFragmentti, "pelifragmentti")
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        })
        netti_nappi.setOnClickListener({
            var fragmentTransaction = this.parentFragmentManager.beginTransaction()
            var nettiFragmentti = NettiFragmentti()
            fragmentTransaction.replace(R.id.ylaosa, nettiFragmentti)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OhjausFragmentti.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OhjausFragmentti().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
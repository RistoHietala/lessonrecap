package fi.centria.tki.kertaus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FormiFragmentti.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormiFragmentti : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var nimi: EditText
    lateinit var osoite: EditText
    lateinit var lupa: CheckBox
    lateinit var tallenna: Button
    var formi = FormiLuokka.hae()

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
        var view = inflater.inflate(R.layout.fragment_formi_fragmentti, container, false)
        nimi = view.findViewById(R.id.nimi_edit)
        osoite = view.findViewById(R.id.osoite_edit)
        lupa = view.findViewById(R.id.lupa_boksi)
        tallenna = view.findViewById(R.id.tallenna_nappi)
        nimi.setText(formi.nimi)
        osoite.setText(formi.osoite)
        lupa.isChecked = formi.lupa

        lupa.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked){
                formi.nimi = nimi.text.toString()
                formi.osoite = osoite.text.toString()
                formi.lupa = lupa.isChecked
            }
            else{
                FormiLuokka.nollaa()
            }
        })
        tallenna.setOnClickListener({
            var this_formi = FormiLuokka()
            this_formi.nimi = nimi.text.toString()
            this_formi.osoite = osoite.text.toString()
            this_formi.lupa = lupa.isChecked
            TiedotLuokka.tiedot.formit.add(this_formi)
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
         * @return A new instance of fragment FormiFragmentti.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormiFragmentti().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
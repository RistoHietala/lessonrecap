package fi.centria.tki.kertaus

class FormiLuokka {
    var nimi: String = ""
    var osoite: String = ""
    var lupa: Boolean = false

    companion object FormiMuisti{
        var formi = FormiLuokka()
        fun nollaa(): FormiLuokka{
            formi = FormiLuokka()
            return formi
        }
        fun hae(): FormiLuokka{
            return formi
        }
    }
}
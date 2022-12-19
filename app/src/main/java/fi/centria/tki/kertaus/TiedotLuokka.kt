package fi.centria.tki.kertaus

class TiedotLuokka {
    var formit: ArrayList<FormiLuokka> = arrayListOf()
    var laskurit: LaskuriLuokka = LaskuriLuokka.hae()

    companion object TiedotMuisti{
        var tiedot: TiedotLuokka = TiedotLuokka()
        fun nollaa():TiedotLuokka{
            tiedot = TiedotLuokka()
            return tiedot
        }
        fun hae(): TiedotLuokka{
            return tiedot
        }
    }
}
package fi.centria.tki.kertaus

class LaskuriLuokka {
    var laskuri1: Int = 0
    var laskuri2: Int = 0

    companion object LaskuriMuisti{
        var laskuri: LaskuriLuokka = LaskuriLuokka()
        fun nollaa(): LaskuriLuokka{
            laskuri = LaskuriLuokka()
            return laskuri
        }

        fun hae(): LaskuriLuokka{
            return laskuri
        }
    }
}
package fi.centria.tki.kertaus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fragmentti1= LaskuriFragmentti()
        var fragmentti2= OhjausFragmentti()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.alaosa, fragmentti2)
            add(R.id.ylaosa,fragmentti1)
        }
    }
}
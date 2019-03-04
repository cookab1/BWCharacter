package andyr.bascomC.bwcharacter

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_initialization.*

class InitializationActivity : andyr.bascomC.bwcharacter.BaseActivity() {

    lateinit var character: andyr.bascomC.bwcharacter.Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.bascomC.bwcharacter.R.layout.activity_initialization)



        statsButton.setOnClickListener { initializeStats() }
    }

    private fun initializeStats() {

    }
}
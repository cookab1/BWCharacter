package andyr.bascomC.bwcharacter

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_initialization.*

class InitializationActivity : BaseActivity() {

    lateinit var character: Character
    lateinit var initializer : Initializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.bascomC.bwcharacter.R.layout.activity_initialization)

        character = CharacterManager.instance.getCharacter()
        initializer = Initializer(character)

        gearButton.setOnClickListener { initializer.initializeStats(initializer.statIteration, layoutInflater, this) {} }
        notesButton.setOnClickListener { initializer.initializeAttributes(initializer.attributeIteration, layoutInflater, this) {} }
        skillsButton.setOnClickListener { initializer.initializeSkills(layoutInflater, this) {} }
        returnToCharacter.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }
    }
}
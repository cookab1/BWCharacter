package andyr.bascomC.bwcharacter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView
import android.widget.Toast
import andyr.bascomC.bwcharacter.Utilities.ATTRIBUTE_NAME
import andyr.bascomC.bwcharacter.Utilities.STAT_NAME
import com.bascomC.andyr.bwcharacter.Skill
import kotlinx.android.synthetic.main.activity_initialization.*
import kotlinx.android.synthetic.main.skill_initialization_dialogue.*

class InitializationActivity : BaseActivity() {

    lateinit var character: Character
    lateinit var initializer : Initializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.bascomC.bwcharacter.R.layout.activity_initialization)

        character = CharacterManager.instance.getCharacter()
        initializer = Initializer(character)

        statsButton.setOnClickListener { initializer.initializeStats(initializer.statIteration, layoutInflater, this) }
        attributesButton.setOnClickListener { initializer.initializeAttributes(initializer.attributeIteration, layoutInflater, this) }
        skillsButton.setOnClickListener { initializer.initializeSkills(layoutInflater, this) }
        returnToCharacter.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }
    }
}
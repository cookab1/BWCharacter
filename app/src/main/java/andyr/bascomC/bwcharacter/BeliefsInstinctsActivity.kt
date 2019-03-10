package andyr.bascomC.bwcharacter

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.bascomC.bwcharacter.R
import kotlinx.android.synthetic.main.activity_beliefs_instincts.*

class BeliefsInstinctsActivity: BaseActivity() {

    private lateinit var character : Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beliefs_instincts)

        character = CharacterManager.instance.getCharacter()

        initializeBeliefsInstincts()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        infoToggleButton.setOnClickListener {
            saveBeliefsInstincts()
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializeBeliefsInstincts() {
        belief1.setText(character.beliefs[0], TextView.BufferType.EDITABLE)
        belief2.setText(character.beliefs[1], TextView.BufferType.EDITABLE)
        belief3.setText(character.beliefs[2], TextView.BufferType.EDITABLE)

        instinct1.setText(character.instincts[0], TextView.BufferType.EDITABLE)
        instinct2.setText(character.instincts[1], TextView.BufferType.EDITABLE)
        instinct3.setText(character.instincts[2], TextView.BufferType.EDITABLE)
    }

    private fun saveBeliefsInstincts() {
        character.beliefs[0] = belief1.text.toString()
        character.beliefs[1] = belief2.text.toString()
        character.beliefs[2] = belief3.text.toString()

        character.instincts[0] = instinct1.text.toString()
        character.instincts[1] = instinct2.text.toString()
        character.instincts[2] = instinct3.text.toString()

        CharacterManager.instance.saveCharacter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var initializer = Initializer(character)
        return when(item.itemId) {
            R.id.action_descriptions -> {
                val intent = Intent(this, SkillDescriptionsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_pro_tips -> {
                val viewDialog = AlertDialog.Builder(this)
                viewDialog.setView(R.layout.help_dialogue)
                viewDialog.create().show()
                true
            }
            R.id.redo -> {
                val viewDialog = AlertDialog.Builder(this)
                viewDialog.setMessage("Are you sure you want to redo your initialization? This will erase all your current data.")
                viewDialog.setPositiveButton("Yes") { _, _ ->  }
                viewDialog.setNegativeButton("No") { _, _ ->  }
                viewDialog.setCancelable(false)
                val customDialog = viewDialog.create()
                customDialog.show()
                customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    character.stats.forEach {
                        it.mExponent = 0
                        it.mTests = 0
                    }
                    character.attributes.forEach {
                        it.mExponent = 0
                        it.mTests = 0
                    }
                    character.skills.clear()
                    character.learning.clear()
                    character.notes.clear()
                    val intent = Intent(this, InitializationActivity::class.java)
                    startActivity(intent)
                    customDialog.dismiss()
                }
                true
            }
            R.id.redoStats -> {
                val viewDialog = AlertDialog.Builder(this)
                viewDialog.setMessage("Are you sure you want to redo your Stats' initialization? This will erase your current Stats data.")
                viewDialog.setPositiveButton("Yes") { _, _ ->  }
                viewDialog.setNegativeButton("No") { _, _ ->  }
                viewDialog.setCancelable(false)
                val customDialog = viewDialog.create()
                customDialog.show()
                customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    character.stats.forEach {
                        it.mExponent = 0
                        it.mTests = 0
                    }
                    initializer.initializeStats(0, layoutInflater, this) {}
                    customDialog.dismiss()
                }
                true
            }
            R.id.redoAttributes -> {
                val viewDialog = AlertDialog.Builder(this)
                viewDialog.setMessage("Are you sure you want to redo your Attributes' initialization? This will erase your current Attribute data.")
                viewDialog.setPositiveButton("Yes") { _, _ ->  }
                viewDialog.setNegativeButton("No") { _, _ ->  }
                viewDialog.setCancelable(false)
                val customDialog = viewDialog.create()
                customDialog.show()
                customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    character.attributes.forEach {
                        it.mExponent = 0
                        it.mTests = 0
                    }
                    initializer.initializeAttributes(0, layoutInflater, this) {}
                    customDialog.dismiss()
                }
                true
            }
            R.id.redoSkills -> {
                val viewDialog = AlertDialog.Builder(this)
                viewDialog.setMessage("Are you sure you want to redo your Skills' initialization? This will erase your current Skills data.")
                viewDialog.setPositiveButton("Yes") { _, _ ->  }
                viewDialog.setNegativeButton("No") { _, _ ->  }
                viewDialog.setCancelable(false)
                val customDialog = viewDialog.create()
                customDialog.show()
                customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    character.skills.clear()
                    character.learning.clear()
                    initializer.initializeSkills(layoutInflater, this) {}
                    customDialog.dismiss()
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
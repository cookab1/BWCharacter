package andyr.bascomC.bwcharacter

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bascomC.andyr.bwcharacter.Skill
import com.bascomC.bwcharacter.R
import kotlinx.android.synthetic.main.activity_artha.*

class ArthaActivity: BaseActivity() {

    private lateinit var character : Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artha)

        character = CharacterManager.instance.getCharacter()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        skillsRecyclerView.layoutManager = LinearLayoutManager(this)
        setList(character.skillsWithArtha)

        infoToggleButton.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }

        //Button Listeners
        beliefs_instincts.setOnClickListener {
            CharacterManager.instance.saveCharacter()
            val intent = Intent(this, BeliefsInstinctsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }

    private fun setList(list: ArrayList<Skill>) {
        skillsRecyclerView.adapter = ArthaListAdapter(
            list,
            this,
            object : ClickListener {
                override fun onPositionClicked(position: Int, view: View) {
                    handleSkillClick(position, view)
                }

                override fun onLongClicked(position: Int, view: View) {
                    handleLongSkillClick(position, view)
                }
            })
    }

    fun handleSkillClick(position: Int, view: View) {

    }

    fun handleLongSkillClick(position: Int, view: View) {

    }


    ///////////////////////// Create OptionsMenu /////////////////////////

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
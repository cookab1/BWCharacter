package andyr.bascomC.bwcharacter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bascomC.andyr.bwcharacter.Skill
import andyr.bascomC.bwcharacter.Utilities.TEST_TYPE
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_stats.*
import kotlinx.android.synthetic.main.skill_layout.*
import kotlinx.android.synthetic.main.skill_list_item.*
import kotlinx.android.synthetic.main.test_click_dialogue.*

class StatsActivity : andyr.bascomC.bwcharacter.BaseActivity() {

    private lateinit var character: andyr.bascomC.bwcharacter.Character
    private var editting = false
    private var firstTimeUser = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.bascomC.bwcharacter.R.layout.activity_stats)

        andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.setPrefs(getPreferences(Context.MODE_PRIVATE))
        andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.setPrefsEditor()

        //reset preferences to original character
//        character = CharacterManager.instance.getCharacter()
//        CharacterManager.instance.saveCharacter()

        character = loadCharacter()
        andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.setCharacter(character)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setEditting(editting)

        if (firstTimeUser) {
            firstTimeUser = false
            val intent = Intent(this, andyr.bascomC.bwcharacter.InitializationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        statsRecyclerView.layoutManager = LinearLayoutManager(this)
        statsRecyclerView.adapter = andyr.bascomC.bwcharacter.ListAdapter(
            character.stats,
            this,
            object : andyr.bascomC.bwcharacter.ClickListener {
                override fun onPositionClicked(position: Int, view: View) {
                    handleStatClick(position, view)
                }

                override fun onLongClicked(position: Int, view: View) {
                    handleLongStatClick(position, view)
                }
            })

        //Button Listeners
        returnToCharacter.setOnClickListener {
            val intent = Intent(this, andyr.bascomC.bwcharacter.SkillsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        notes1.setOnClickListener {
            val intent = Intent(this, andyr.bascomC.bwcharacter.NotesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        statsEdittingSaveButton.setOnClickListener { setEditting(false) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.bascomC.bwcharacter.R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.bascomC.bwcharacter.R.id.action_descriptions -> {
                val intent = Intent(this, andyr.bascomC.bwcharacter.SkillDescriptionsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun loadCharacter(): andyr.bascomC.bwcharacter.Character {
        val gson = Gson()
        val json = andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.getPrefs().getString("Character", "")
        if (gson.fromJson<andyr.bascomC.bwcharacter.Character>(
                json,
                andyr.bascomC.bwcharacter.Character::class.java
            ) == null
        ) {
            firstTimeUser = true
            return andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
        }
        return gson.fromJson<andyr.bascomC.bwcharacter.Character>(json, andyr.bascomC.bwcharacter.Character::class.java)
    }

    private fun AddStat() {
        character.stats.add(Skill("new Stat", 3))
        andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
        statsRecyclerView.adapter?.notifyDataSetChanged()
    }

    fun handleStatClick(position: Int, view: View) {
        if (editting) {
            val editDialog = AlertDialog.Builder(view.context)
            editDialog.setView(layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.skill_layout, null))
            val customDialog = editDialog.create()
            customDialog.show()
            customDialog.edit_exp.text = character.stats[position].mExponent.toString()
            customDialog.edit_name.setText(character.stats[position].mName, TextView.BufferType.EDITABLE)
            customDialog.editSkillDeleteButton.visibility = View.GONE
            customDialog.inc.setOnClickListener {
                var newExp = 0
                if (character.stats[position].mExponent < 10) {
                    newExp = ++character.stats[position].mExponent
                    andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                    customDialog.edit_exp.text = newExp.toString()
                } else {
                    Toast.makeText(view.context, "Cannot Set Exponent greater than 10", Toast.LENGTH_SHORT).show()
                }
            }
            customDialog.dec.setOnClickListener {
                var newExp = 0
                if (character.stats[position].mExponent > 0) {
                    newExp = --character.stats[position].mExponent
                    andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                    customDialog.edit_exp.text = newExp.toString()
                } else {
                    Toast.makeText(view.context, "Cannot Set Exponent less than 0", Toast.LENGTH_SHORT).show()
                }
            }
            customDialog.editSkillSaveButton.setOnClickListener {
                character.stats[position].mName = customDialog.edit_name.text.toString()
                andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                statsRecyclerView.adapter?.notifyItemChanged(position)
                customDialog.dismiss()
            }
        } else if (view.id == testsButton.id) {
            val testDialog = AlertDialog.Builder(view.context)
            testDialog.setView(layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.test_click_dialogue, null))
            val customDialog = testDialog.create()
            customDialog.show()
            customDialog.routineButton.visibility = View.GONE
            customDialog.difficultButton.setOnClickListener {
                if (canTest(character.stats[position].mTests, TEST_TYPE[1])) {
                    character.stats[position].mTests += 10
                    andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                    statsRecyclerView.adapter?.notifyItemChanged(position)
                } else {
                    Toast.makeText(view.context, "Cannot make Difficult Test", Toast.LENGTH_SHORT).show()
                }
                customDialog.dismiss()
            }
            customDialog.challengingButton.setOnClickListener {
                if (canTest(character.stats[position].mTests, TEST_TYPE[2])) {
                    character.stats[position].mTests += 1
                    andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                    statsRecyclerView.adapter?.notifyItemChanged(position)
                } else {
                    Toast.makeText(view.context, "Cannot make Challenging Test", Toast.LENGTH_SHORT).show()
                }
                customDialog.dismiss()
            }
        } else if (view.id == fpdButton.id) {
        } else {
            showSkillDescription(character.stats[position].mName, view.context)
        }
    }

    private fun canTest(tests: Int, testType: String): Boolean {
        when (testType) {
            TEST_TYPE[0] -> return false
            TEST_TYPE[1] ->
                if (((tests % 100) / 10) < 4) {
                    return true
                }
            TEST_TYPE[2] ->
                if ((tests % 10) < 3) {
                    return true
                }
        }
        return false
    }


    private fun handleLongStatClick(position: Int, view: View) {
        if (!editting) {
            setEditting(true)
        }
//        Toast.makeText(view.context, "Entering Editting Mode", Toast.LENGTH_SHORT).show()
    }

    private fun setEditting(setEdit: Boolean) {
        editting = setEdit
        if (setEdit) {
            statsEdittingBanner.visibility = View.VISIBLE
        } else {
            statsEdittingBanner.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (editting) {
            setEditting(false)
        } else {
            super.onBackPressed()
        }
    }
}

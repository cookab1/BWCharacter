package andyr.bascomC.bwcharacter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bascomC.andyr.bwcharacter.Skill
import Utilities.*
import com.bascomC.bwcharacter.R
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_stats.*
import kotlinx.android.synthetic.main.skill_layout.*
import kotlinx.android.synthetic.main.skill_list_item.*
import kotlinx.android.synthetic.main.test_click_dialogue.*

class StatsActivity : BaseActivity() {

    private lateinit var character: Character
    private var editting = false
    private var firstTimeUser = false
    private var onStatsTab = true

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.bascomC.bwcharacter.R.layout.activity_stats)

        CharacterManager.instance.setPrefs(getPreferences(Context.MODE_PRIVATE))
        CharacterManager.instance.setPrefsEditor()

        //reset preferences to original character
//        character = CharacterManager.instance.getCharacter()
//        CharacterManager.instance.saveCharacter()

        character = loadCharacter()
        CharacterManager.instance.setCharacter(character)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setEditting(editting)

        if (firstTimeUser) {
            firstTimeUser = false
            val intent = Intent(this, InitializationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        statsRecyclerView.layoutManager = LinearLayoutManager(this)
        setList(character.stats)

        //Button Listeners
        returnToCharacter.setOnClickListener {
            val intent = Intent(this, SkillsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        notes1.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        infoToggleButton.setOnClickListener {
            val intent = Intent(this, BeliefsInstinctsActivity::class.java)
            startActivity(intent)
        }

        statsEdittingSaveButton.setOnClickListener { setEditting(false) }

        statsButton.setOnClickListener {
            headerStats.visibility = View.VISIBLE
            headerAttributes.visibility = View.INVISIBLE
            if(!onStatsTab) {
                onStatsTab = true
                setList(character.stats)
            }
        }
        attributesButton.setOnClickListener {
            headerStats.visibility = View.INVISIBLE
            headerAttributes.visibility = View.VISIBLE
            if(onStatsTab) {
                onStatsTab = false
                setList(character.attributes)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.bascomC.bwcharacter.R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var initializer = Initializer(character)
        return when (item.itemId) {
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
                    initializer.initializeStats(0, layoutInflater, this) {
                        if (onStatsTab) {
                            statsRecyclerView.adapter?.notifyDataSetChanged()
                        }
                    }
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
                    initializer.initializeAttributes(0, layoutInflater, this) {
                        if (!onStatsTab) {
                            statsRecyclerView.adapter?.notifyDataSetChanged()
                        }
                    }
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
                    statsRecyclerView.adapter?.notifyDataSetChanged()
                    customDialog.dismiss()
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun loadCharacter(): Character {
        val gson = Gson()
        val json = CharacterManager.instance.getPrefs().getString("Character", "")
        if (gson.fromJson<Character>(
                json,
                Character::class.java
            ) == null
        ) {
            firstTimeUser = true
            return CharacterManager.instance.saveCharacter()
        }
        return gson.fromJson<Character>(json, Character::class.java)
    }

    fun handleStatClick(position: Int, view: View) {
        var list = character.stats
        if (!onStatsTab) {
            list = character.attributes
        }
        if (editting) {
            val editDialog = AlertDialog.Builder(view.context)
            editDialog.setView(layoutInflater.inflate(R.layout.skill_layout, null))
            val customDialog = editDialog.create()
            customDialog.show()
            customDialog.edit_exp.text = list[position].mExponent.toString()
            customDialog.edit_name.setText(list[position].mName, TextView.BufferType.EDITABLE)
            customDialog.editSkillDeleteButton.visibility = View.GONE
            customDialog.edit_name.isEnabled = false
            customDialog.inc.setOnClickListener {
                var newExp = 0
                var maxExp = 10
                if (list[position].mName == "Mortal Wound") {
                    maxExp = 16
                }
                if (list[position].mExponent < maxExp) {
                    newExp = ++list[position].mExponent
                    if (onStatsTab) {
                        character.stats = list
                    } else {
                        character.attributes = list
                    }
                    CharacterManager.instance.saveCharacter()
                    customDialog.edit_exp.text = newExp.toString()
                } else {
                    Toast.makeText(view.context, "Cannot Set Exponent greater than 10", Toast.LENGTH_SHORT).show()
                }
            }
            customDialog.dec.setOnClickListener {
                var newExp = 0
                if (list[position].mExponent > 0) {
                    newExp = --list[position].mExponent
                    if (onStatsTab) {
                        character.stats = list
                    } else {
                        character.attributes = list
                    }
                    CharacterManager.instance.saveCharacter()
                    customDialog.edit_exp.text = newExp.toString()
                } else {
                    Toast.makeText(view.context, "Cannot Set Exponent less than 0", Toast.LENGTH_SHORT).show()
                }
            }
            customDialog.editSkillSaveButton.setOnClickListener {
                list[position].mName = customDialog.edit_name.text.toString()
                if (onStatsTab) {
                    character.stats = list
                } else {
                    character.attributes = list
                }
                CharacterManager.instance.saveCharacter()
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
                if (canTest(list[position].mTests, TEST_TYPE[1])) {
                    list[position].mTests += 10
                    if (onStatsTab) {
                        character.stats = list
                    } else {
                        character.attributes = list
                    }
                    CharacterManager.instance.saveCharacter()
                    statsRecyclerView.adapter?.notifyItemChanged(position)
                } else {
                    Toast.makeText(view.context, "Cannot make Difficult Test", Toast.LENGTH_SHORT).show()
                }
                customDialog.dismiss()
            }
            customDialog.challengingButton.setOnClickListener {
                if (canTest(list[position].mTests, TEST_TYPE[2])) {
                    list[position].mTests += 1
                    if (onStatsTab) {
                        character.stats = list
                    } else {
                        character.attributes = list
                    }
                    CharacterManager.instance.saveCharacter()
                    statsRecyclerView.adapter?.notifyItemChanged(position)
                } else {
                    Toast.makeText(view.context, "Cannot make Challenging Test", Toast.LENGTH_SHORT).show()
                }
                customDialog.dismiss()
            }
        } else if (view.id == fpdButton.id) {
        } else {
            showSkillDescription(list[position].mName, view.context)
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

    private fun setList(list: ArrayList<Skill>) {statsRecyclerView.adapter = ListAdapter(list as ArrayList<SkillObject>,this,
        object : ClickListener {
            override fun onPositionClicked(position: Int, view: View) {
                handleStatClick(position, view)
            }

            override fun onLongClicked(position: Int, view: View) {
                handleLongStatClick(position, view)
            }
        })
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

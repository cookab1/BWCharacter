package andyr.bascomC.bwcharacter

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
import kotlinx.android.synthetic.main.activity_skills.*
import kotlinx.android.synthetic.main.skill_layout.*
import kotlinx.android.synthetic.main.skill_list_item.*
import kotlinx.android.synthetic.main.test_click_dialogue.*

class SkillsActivity: andyr.bascomC.bwcharacter.BaseActivity() {

    private val TAG = "SkillsActivity_Logging"
    private lateinit var character: andyr.bascomC.bwcharacter.Character
    private var editting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.bascomC.bwcharacter.R.layout.activity_skills)

        character = andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.getCharacter()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (character.skills.size == 0) {
            setEditting(true)
        } else {
            setEditting(editting)
        }

        skillsRecyclerView.layoutManager = LinearLayoutManager(this)
        skillsRecyclerView.adapter = andyr.bascomC.bwcharacter.ListAdapter(
            character.skills,
            this,
            object : andyr.bascomC.bwcharacter.ClickListener {
                override fun onPositionClicked(position: Int, view: View) {
                    handleSkillClick(position, view)
                }

                override fun onLongClicked(position: Int, view: View) {
                    handleLongSkillClick(position, view)
                }
            })

        //Button Listeners
        stats2.setOnClickListener {
            val intent = Intent(this, andyr.bascomC.bwcharacter.StatsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        notes2.setOnClickListener {
            val intent = Intent(this, andyr.bascomC.bwcharacter.NotesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        skillsEdittingSaveButton.setOnClickListener { setEditting(false) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.bascomC.bwcharacter.R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
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

    fun addSkill(position: Int) {
        var newExponent = 0

        //Create Skill Dialogue
        val editDialog = AlertDialog.Builder(this)
        editDialog.setView(layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.skill_layout, null))
        val customDialog = editDialog.create()
        customDialog.show()
        customDialog.edit_exp.text = newExponent.toString()
        customDialog.editSkillDeleteButton.visibility = View.GONE
        customDialog.inc.setOnClickListener {
            if (newExponent < 10) {
                ++newExponent
                customDialog.edit_exp.text = newExponent.toString()
            } else {
                Toast.makeText(this, "Cannot Set Exponent greater than 10", Toast.LENGTH_SHORT).show()
            }
        }
        customDialog.dec.setOnClickListener {
            if (newExponent > 0) {
                --newExponent
                customDialog.edit_exp.text = newExponent.toString()
            } else {
                Toast.makeText(this, "Cannot Set Exponent less than 0", Toast.LENGTH_SHORT).show()
            }
        }
        customDialog.editSkillSaveButton.setOnClickListener {
            character.skills.add(position, Skill(customDialog.edit_name.text.toString(), newExponent))
            andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
            skillsRecyclerView.adapter?.notifyDataSetChanged()
            customDialog.dismiss()
        }
    }

    fun handleSkillClick(position: Int, view: View) {
        if (editting) {
            if (position == character.skills.size - 1) {
                addSkill(position)
            } else {
                val editDialog = AlertDialog.Builder(view.context)
                editDialog.setView(layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.skill_layout, null))
                val customDialog = editDialog.create()
                customDialog.show()
                customDialog.edit_exp.text = character.skills[position].mExponent.toString()
                customDialog.edit_name.setText(character.skills[position].mName, TextView.BufferType.EDITABLE)
                customDialog.inc.setOnClickListener {
                    var newExp = 0
                    if (character.skills[position].mExponent < 10) {
                        newExp = ++character.skills[position].mExponent
                        andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                        customDialog.edit_exp.text = newExp.toString()
                    } else {
                        Toast.makeText(view.context, "Cannot Set Exponent greater than 10", Toast.LENGTH_SHORT).show()
                    }
                }
                customDialog.dec.setOnClickListener {
                    var newExp = 0
                    if (character.skills[position].mExponent > 0) {
                        newExp = --character.skills[position].mExponent
                        andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                        customDialog.edit_exp.text = newExp.toString()
                    } else {
                        Toast.makeText(view.context, "Cannot Set Exponent less than 0", Toast.LENGTH_SHORT).show()
                    }
                }
                customDialog.editSkillDeleteButton.setOnClickListener {
                    character.skills.removeAt(position)
                    andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyDataSetChanged()
                }
                customDialog.editSkillSaveButton.setOnClickListener {
                    character.skills[position].mName = customDialog.edit_name.text.toString()
                    andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyItemChanged(position)
                    customDialog.dismiss()
                }
            }
        } else if (view.id == testsButton.id) {
            val testDialog = AlertDialog.Builder(view.context)
            testDialog.setView(layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.test_click_dialogue, null))
            val customDialog = testDialog.create()
            customDialog.show()
            customDialog.routineButton.setOnClickListener {
                if (canTest(character.skills[position].mTests, TEST_TYPE[0])) {
                    character.skills[position].mTests += 100
                    andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyItemChanged(position)
                } else {
                    Toast.makeText(view.context, "Cannot make Routine Test", Toast.LENGTH_SHORT).show()
                }
                customDialog.dismiss()
            }
            customDialog.difficultButton.setOnClickListener {
                if (canTest(character.skills[position].mTests, TEST_TYPE[1])) {
                    character.skills[position].mTests += 10
                    andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyItemChanged(position)
                } else {
                    Toast.makeText(view.context, "Cannot make Difficult Test", Toast.LENGTH_SHORT).show()
                }
                customDialog.dismiss()
            }
            customDialog.challengingButton.setOnClickListener {
                if (canTest(character.skills[position].mTests, TEST_TYPE[2])) {
                    character.skills[position].mTests += 1
                    andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyItemChanged(position)
                } else {
                    Toast.makeText(view.context, "Cannot make Challenging Test", Toast.LENGTH_SHORT).show()
                }
                customDialog.dismiss()
            }
        } else if (view.id == fpdButton.id) {
        } else {
            showSkillDescription(character.skills[position].mName, view.context)
        }
    }

    private fun canTest(tests: Int, testType: String) : Boolean {
        when (testType) {
            TEST_TYPE[0] ->
                if ((tests / 100) < 4) {
                    return true
                }
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

    fun handleLongSkillClick(position: Int, view: View) {
        if (!editting) {
            setEditting(true)
        } else {
            val viewDialog = AlertDialog.Builder(view.context)
            viewDialog.setMessage("Delete this Skill?")
            viewDialog.setPositiveButton("Yes") { _, _ ->  }
            viewDialog.setNegativeButton("No") { _, _ ->  }
            viewDialog.setCancelable(false)
            val confirmDialog = viewDialog.create()
            confirmDialog.show()
            confirmDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                character.skills.removeAt(position)
                andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                skillsRecyclerView.adapter?.notifyDataSetChanged()
                confirmDialog.dismiss()
            }
        }
//        Toast.makeText(view.context, "Entering Editting Mode", Toast.LENGTH_SHORT).show()
    }

    private fun setEditting(setEdit : Boolean) {
        val skillsSize = character.skills.size
        if (setEdit) {
            character.skills.add(Skill("AddItem", -1, 1))
            skillsRecyclerView.adapter?.notifyDataSetChanged()
            skillsEdittingBanner.visibility = View.VISIBLE
            editting = setEdit
        } else {
            val viewType = character.skills[skillsSize - 1].viewType
            if (viewType == 1 && skillsSize > 1) {
//            cleanSkills()
                character.skills.removeAt(character.skills.size - 1)
                skillsRecyclerView.adapter?.notifyDataSetChanged()
                skillsEdittingBanner.visibility = View.GONE
                editting = setEdit
            }
        }
    }

//    private fun cleanSkills() {
//        for (item in character.skills) {
//            if (item.viewType == 1) {
//                character.skills.remove(item)
//            }
//        }
//    }

    override fun onBackPressed() {
        if (editting) {
            setEditting(false)
        } else {
            super.onBackPressed()
        }
    }
}
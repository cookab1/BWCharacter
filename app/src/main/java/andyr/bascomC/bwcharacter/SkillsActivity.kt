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
import com.bascomC.bwcharacter.R
import kotlinx.android.synthetic.main.activity_skills.*
import kotlinx.android.synthetic.main.skill_layout.*
import kotlinx.android.synthetic.main.skill_list_item.*
import kotlinx.android.synthetic.main.test_click_dialogue.*

class SkillsActivity: andyr.bascomC.bwcharacter.BaseActivity() {

    private val TAG = "SkillsActivity_Logging"
    private lateinit var character: andyr.bascomC.bwcharacter.Character
    private var editting = false
    private var onSkillsTab = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.bascomC.bwcharacter.R.layout.activity_skills)

        character = CharacterManager.instance.getCharacter()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (character.skills.isEmpty()) {
            setEditting(true)
        } else {
            setEditting(editting)
        }

        skillsRecyclerView.layoutManager = LinearLayoutManager(this)
        setList(character.skills)

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
        skillsButton.setOnClickListener {
            if(!onSkillsTab) {
                onSkillsTab = true
                if(character.skills.isEmpty()) {
                    setEditting(true)
                }
                setList(character.skills)
            }
        }
        learningButton.setOnClickListener {
            if(onSkillsTab) {
                onSkillsTab = false
                if(character.learning.isEmpty()) {
                    setEditting(true)
                }
                setList(character.learning as ArrayList<Skill>)
            }
        }
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
        editDialog.setView(layoutInflater.inflate(R.layout.skill_layout, null))
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
            if (onSkillsTab) {
                character.skills.add(position, Skill(customDialog.edit_name.text.toString(), newExponent))
            } else {
                character.learning.add(position, Skill(customDialog.edit_name.text.toString(), newExponent))
            }
            CharacterManager.instance.saveCharacter()
            skillsRecyclerView.adapter?.notifyDataSetChanged()
            customDialog.dismiss()
        }
    }

    fun handleSkillClick(position: Int, view: View) {
        var list = character.skills
        if (!onSkillsTab) {
            list = character.learning
        }
        
        if (editting) {
            if (position == list.size - 1) {
                addSkill(position)
            } else {
                val editDialog = AlertDialog.Builder(view.context)
                editDialog.setView(layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.skill_layout, null))
                val customDialog = editDialog.create()
                customDialog.show()
                customDialog.edit_exp.text = list[position].mExponent.toString()
                customDialog.edit_name.setText(list[position].mName, TextView.BufferType.EDITABLE)
                customDialog.inc.setOnClickListener {
                    var newExp = 0
                    if (list[position].mExponent < 10) {
                        newExp = ++list[position].mExponent
                        if (onSkillsTab) {
                            character.skills = list
                        } else {
                            character.learning = list
                        }
                        andyr.bascomC.bwcharacter.CharacterManager.instance.saveCharacter()
                        customDialog.edit_exp.text = newExp.toString()
                    } else {
                        Toast.makeText(view.context, "Cannot Set Exponent greater than 10", Toast.LENGTH_SHORT).show()
                    }
                }
                customDialog.dec.setOnClickListener {
                    var newExp = 0
                    if (list[position].mExponent > 0) {
                        newExp = --list[position].mExponent
                        if (onSkillsTab) {
                            character.skills = list
                        } else {
                            character.learning = list
                        }
                        andyr.bascomC.bwcharacter.CharacterManager.instance.saveCharacter()
                        customDialog.edit_exp.text = newExp.toString()
                    } else {
                        Toast.makeText(view.context, "Cannot Set Exponent less than 0", Toast.LENGTH_SHORT).show()
                    }
                }
                customDialog.editSkillDeleteButton.setOnClickListener {
                    list.removeAt(position)
                    if (onSkillsTab) {
                        character.skills = list
                    } else {
                        character.learning = list
                    }
                    andyr.bascomC.bwcharacter.CharacterManager.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyDataSetChanged()
                    customDialog.dismiss()
                }
                customDialog.editSkillSaveButton.setOnClickListener {
                    list[position].mName = customDialog.edit_name.text.toString()
                    if (onSkillsTab) {
                        character.skills = list
                    } else {
                        character.learning = list
                    }
                    andyr.bascomC.bwcharacter.CharacterManager.instance.saveCharacter()
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
                if (canTest(list[position].mTests, TEST_TYPE[0])) {
                    list[position].mTests += 100
                    if (onSkillsTab) {
                        character.skills = list
                    } else {
                        character.learning = list
                    }
                    andyr.bascomC.bwcharacter.CharacterManager.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyItemChanged(position)
                } else {
                    Toast.makeText(view.context, "Cannot make Routine Test", Toast.LENGTH_SHORT).show()
                }
                customDialog.dismiss()
            }
            customDialog.difficultButton.setOnClickListener {
                if (canTest(list[position].mTests, TEST_TYPE[1])) {
                    list[position].mTests += 10
                    if (onSkillsTab) {
                        character.skills = list
                    } else {
                        character.learning = list
                    }
                    andyr.bascomC.bwcharacter.CharacterManager.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyItemChanged(position)
                } else {
                    Toast.makeText(view.context, "Cannot make Difficult Test", Toast.LENGTH_SHORT).show()
                }
                customDialog.dismiss()
            }
            customDialog.challengingButton.setOnClickListener {
                if (canTest(list[position].mTests, TEST_TYPE[2])) {
                    list[position].mTests += 1
                    if (onSkillsTab) {
                        character.skills = list
                    } else {
                        character.learning = list
                    }
                    andyr.bascomC.bwcharacter.CharacterManager.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyItemChanged(position)
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
                andyr.bascomC.bwcharacter.CharacterManager.instance.saveCharacter()
                skillsRecyclerView.adapter?.notifyDataSetChanged()
                confirmDialog.dismiss()
            }
        }
    }

    private fun setEditting(setEdit : Boolean) {
        val skillsSize = character.skills.size
        if (setEdit) {
            character.skills.add(Skill("AddItem", -1, 1))
            character.learning.add(Skill("AddItem", -1, 1))
            skillsRecyclerView.adapter?.notifyDataSetChanged()
            skillsEdittingBanner.visibility = View.VISIBLE
            editting = setEdit
        } else {
            val viewType = character.skills[skillsSize - 1].viewType
            if (viewType == 1 && skillsSize > 1) {
//            cleanSkills()
                character.skills.removeAt(character.skills.size - 1)
                character.learning.removeAt(character.learning.size - 1)
                skillsRecyclerView.adapter?.notifyDataSetChanged()
                skillsEdittingBanner.visibility = View.GONE
                editting = setEdit
            }
        }
    }

    private fun setList(list: ArrayList<Skill>) {
        skillsRecyclerView.adapter = ListAdapter(
            list as ArrayList<SkillObject>,
            this,
            object : andyr.bascomC.bwcharacter.ClickListener {
                override fun onPositionClicked(position: Int, view: View) {
                    handleSkillClick(position, view)
                }

                override fun onLongClicked(position: Int, view: View) {
                    handleLongSkillClick(position, view)
                }
            })
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
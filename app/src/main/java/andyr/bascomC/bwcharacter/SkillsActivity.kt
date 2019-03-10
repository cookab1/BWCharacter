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

class SkillsActivity : BaseActivity() {

    private val TAG = "SkillsActivity_Logging"
    private lateinit var character: Character
    private var editting = false
    private var onSkillsTab = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skills)

        character = CharacterManager.instance.getCharacter()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (character.skills.isEmpty()) {
            setEditting(true)
        } else {
            setEditting(false)
        }

        headerBeingLearned.visibility = View.GONE
        headerAptitude.visibility = View.GONE
        onSkillsTab = true

        skillsRecyclerView.layoutManager = LinearLayoutManager(this)
        setList(character.skills)

        //Button Listeners
        stats2.setOnClickListener {
            if (editting) {
                edittingElements(false)
            }
            CharacterManager.instance.saveCharacter()
            val intent = Intent(this, StatsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        notes2.setOnClickListener {
            if (editting) {
                edittingElements(false)
            }
            CharacterManager.instance.saveCharacter()
            val intent = Intent(this, NotesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        infoToggleButton.setOnClickListener {
            if (editting) {
                edittingElements(false)
            }
            CharacterManager.instance.saveCharacter()
            val intent = Intent(this, BeliefsInstinctsActivity::class.java)
            startActivity(intent)
        }

        skillsEdittingSaveButton.setOnClickListener { setEditting(false) }
        skillsButton.setOnClickListener {
            if (!onSkillsTab) {
                headerBeingLearned.visibility = View.GONE
                headerAptitude.visibility = View.GONE
                onSkillsTab = true
                if (character.skills.isEmpty()) {
                    setEditting(true)
                } else {
                    setEditting(false)
                }
                setList(character.skills)
            }
        }
        learningButton.setOnClickListener {
            if (onSkillsTab) {
                headerBeingLearned.visibility = View.VISIBLE
                headerAptitude.visibility = View.VISIBLE
                onSkillsTab = false
                if (character.learning.isEmpty()) {
                    setEditting(true)
                } else {
                    setEditting(false)
                }
                setList(character.learning as ArrayList<Skill>)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
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
                    initializer.initializeSkills(layoutInflater, this) {
                        skillsRecyclerView.adapter?.notifyDataSetChanged()
                    }
                    customDialog.dismiss()
                }
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
        if (!onSkillsTab) {
            customDialog.edit_exp.visibility = View.GONE
            customDialog.inc.visibility = View.GONE
            customDialog.dec.visibility = View.GONE
        }
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
            var skillName = customDialog.edit_name.text.toString()
            if (skillName != "") {
                if (onSkillsTab) {
                    character.skills.add(position, Skill(skillName, newExponent))
                } else {
                    var aptitude = calculateAptitude(skillName)
                    if (aptitude == -1) {
                        aptitude = 0
                        Toast.makeText(this, "RootInt couldn't be found", Toast.LENGTH_SHORT).show()
                    } else if (aptitude == -2) {
                        aptitude = 0
                        Toast.makeText(this, "Skill description couldn't be found", Toast.LENGTH_SHORT).show()
                    }
                    character.learning.add(position, LearningSkill(skillName, aptitude))
                }
                CharacterManager.instance.saveCharacter()
                skillsRecyclerView.adapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Empty Skill Name.", Toast.LENGTH_SHORT).show()
            }
            customDialog.dismiss()
        }
    }

    fun handleSkillClick(position: Int, view: View) {
        var list: ArrayList<SkillObject> = character.skills as ArrayList<SkillObject>
        if (!onSkillsTab) {
            list = character.learning as ArrayList<SkillObject>
        }

        if (editting) {
            if (position == list.size - 1) {
                addSkill(position)
            } else {
                val editDialog = AlertDialog.Builder(view.context)
                editDialog.setView(layoutInflater.inflate(R.layout.skill_layout, null))
                val customDialog = editDialog.create()
                customDialog.show()
                if (!onSkillsTab) {
                    customDialog.edit_exp.visibility = View.GONE
                    customDialog.inc.visibility = View.GONE
                    customDialog.dec.visibility = View.GONE
                }
                customDialog.edit_exp.text = list[position].mExponent.toString()
                customDialog.edit_name.setText(list[position].mName, TextView.BufferType.EDITABLE)
                customDialog.inc.setOnClickListener {
                    var newExp = 0
                    if (list[position].mExponent < 10) {
                        newExp = ++list[position].mExponent
                        if (onSkillsTab) {
                            character.skills = list as ArrayList<Skill>
                        } else {
                            character.learning = list as ArrayList<LearningSkill>
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
                        if (onSkillsTab) {
                            character.skills = list as ArrayList<Skill>
                        } else {
                            character.learning = list as ArrayList<LearningSkill>
                        }
                        CharacterManager.instance.saveCharacter()
                        customDialog.edit_exp.text = newExp.toString()
                    } else {
                        Toast.makeText(view.context, "Cannot Set Exponent less than 0", Toast.LENGTH_SHORT).show()
                    }
                }
                customDialog.editSkillDeleteButton.setOnClickListener {
                    list.removeAt(position)
                    if (onSkillsTab) {
                        character.skills = list as ArrayList<Skill>
                    } else {
                        character.learning = list as ArrayList<LearningSkill>
                    }
                    CharacterManager.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyDataSetChanged()
                    customDialog.dismiss()
                }
                customDialog.editSkillSaveButton.setOnClickListener {
                    list[position].mName = customDialog.edit_name.text.toString()
                    if (onSkillsTab) {
                        character.skills = list as ArrayList<Skill>
                    } else {
                        character.learning = list as ArrayList<LearningSkill>
                    }
                    CharacterManager.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyItemChanged(position)
                    customDialog.dismiss()
                }
            }
        } else if (view.id == testsButton.id) {
            val testDialog = AlertDialog.Builder(view.context)
            testDialog.setView(layoutInflater.inflate(R.layout.test_click_dialogue, null))
            val customDialog = testDialog.create()
            customDialog.show()
            customDialog.routineButton.setOnClickListener {
                if (canTest(list[position].mTests, TEST_TYPE[0])) {
                    list[position].mTests += 100
                    if (onSkillsTab) {
                        character.skills = list as ArrayList<Skill>
                    } else {
                        character.learning = list as ArrayList<LearningSkill>
                    }
                    CharacterManager.instance.saveCharacter()
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
                        character.skills = list as ArrayList<Skill>
                    } else {
                        character.learning = list as ArrayList<LearningSkill>
                    }
                    CharacterManager.instance.saveCharacter()
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
                        character.skills = list as ArrayList<Skill>
                    } else {
                        character.learning = list as ArrayList<LearningSkill>
                    }
                    CharacterManager.instance.saveCharacter()
                    skillsRecyclerView.adapter?.notifyItemChanged(position)
                } else {
                    Toast.makeText(view.context, "Cannot make Challenging Test", Toast.LENGTH_SHORT).show()
                }
                customDialog.dismiss()
            }
            if (!onSkillsTab) {
                customDialog.test_click_title.text = "Test a Learning Skill"
                customDialog.routineButton.text = "Remove Test"
                customDialog.challengingButton.text = "Add Test"
                customDialog.difficultButton.visibility = View.GONE
                customDialog.routineButton.setOnClickListener {
                    if (list[position].mTests > 0) {
                        list[position].mTests -= 1
                        character.learning = list as ArrayList<LearningSkill>
                        CharacterManager.instance.saveCharacter()
                        skillsRecyclerView.adapter?.notifyItemChanged(position)
                    } else {
                        Toast.makeText(view.context, "Cannot make Remove Test", Toast.LENGTH_SHORT).show()
                    }
                    customDialog.dismiss()
                }
                customDialog.challengingButton.setOnClickListener {
                    if (list[position].mTests < 10) {
                        list[position].mTests += 1
                        character.learning = list as ArrayList<LearningSkill>
                        CharacterManager.instance.saveCharacter()
                        skillsRecyclerView.adapter?.notifyItemChanged(position)
                    } else {
                        Toast.makeText(view.context, "Cannot make Add Test", Toast.LENGTH_SHORT).show()
                    }
                    customDialog.dismiss()
                }
            }
        } else if (view.id == fpdButton.id) {
        } else {
            showSkillDescription(list[position].mName, view.context)
        }
    }

    private fun canTest(tests: Int, testType: String): Boolean {
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
            viewDialog.setPositiveButton("Yes") { _, _ -> }
            viewDialog.setNegativeButton("No") { _, _ -> }
            viewDialog.setCancelable(false)
            val confirmDialog = viewDialog.create()
            confirmDialog.show()
            confirmDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                character.skills.removeAt(position)
                CharacterManager.instance.saveCharacter()
                skillsRecyclerView.adapter?.notifyDataSetChanged()
                confirmDialog.dismiss()
            }
        }
    }

    private fun setEditting(setEdit: Boolean) {
        val skillsSize = character.skills.size
        val learningSize = character.learning.size
        if (setEdit) {
            edittingElements(true)
            skillsRecyclerView.adapter?.notifyDataSetChanged()
            skillsEdittingBanner.visibility = View.VISIBLE
            editting = setEdit
        } else {
            val viewType = character.skills[skillsSize - 1].viewType
            if (viewType == -1 && ((onSkillsTab && skillsSize > 1) || (!onSkillsTab && learningSize > 1))) {
//            cleanSkills()
                edittingElements(false)
                skillsRecyclerView.adapter?.notifyDataSetChanged()
                skillsEdittingBanner.visibility = View.GONE
                editting = setEdit
            }
        }
    }

    private fun edittingElements(add : Boolean) {
        if (add) {
            character.skills.add(Skill("AddItem", -1, -1))
            character.learning.add(LearningSkill("AddItem", -1, -1))
        } else {
            if (character.skills.size > 0) {
                character.skills.removeAt(character.skills.size - 1)
            }
            if (character.learning.size > 0) {
                character.learning.removeAt(character.learning.size - 1)
            }
        }
    }

    private fun setList(list: ArrayList<Skill>) {
        skillsRecyclerView.adapter = ListAdapter(
            list as ArrayList<SkillObject>,
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
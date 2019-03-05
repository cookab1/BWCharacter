package andyr.bascomC.bwcharacter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import andyr.bascomC.bwcharacter.Utilities.ATTRIBUTE_NAME
import andyr.bascomC.bwcharacter.Utilities.STAT_NAME
import com.bascomC.andyr.bwcharacter.Skill
import com.bascomC.bwcharacter.R
import kotlinx.android.synthetic.main.skill_initialization_dialogue.*

class Initializer(var character: Character, var statIteration: Int = 0, var attributeIteration: Int = 0) {


    fun initializeStats(iteration: Int, inflater: LayoutInflater, context: Context) {
        if(iteration > 5 || iteration < 0) {
            return
        }
        statIteration = iteration
        var newExponent = 0

        val testDialog = AlertDialog.Builder(context)
        testDialog.setView(inflater.inflate(R.layout.skill_initialization_dialogue, null))
        val customDialog = testDialog.create()
        customDialog.show()
        customDialog.skillName.setText(STAT_NAME[iteration], TextView.BufferType.EDITABLE)
        customDialog.skillExp.text = character.stats[iteration].mExponent.toString()
        customDialog.skillName.isEnabled = false
        customDialog.inc.setOnClickListener {
            if (newExponent < 10) {
                ++newExponent
                character.stats[iteration].mExponent = newExponent
                customDialog.skillExp.text = newExponent.toString()
            } else {
                Toast.makeText(context, "Cannot Set Exponent greater than 10", Toast.LENGTH_SHORT).show()
            }
        }
        customDialog.dec.setOnClickListener {
            if (newExponent > 0) {
                --newExponent
                character.stats[iteration].mExponent = newExponent
                customDialog.skillExp.text = newExponent.toString()
            } else {
                Toast.makeText(context, "Cannot Set Exponent less than 0", Toast.LENGTH_SHORT).show()
            }
        }
        customDialog.prevButton.setOnClickListener {
            CharacterManager.instance.saveCharacter()
            customDialog.dismiss()
            initializeStats(iteration - 1, inflater, context)
        }
        customDialog.nextButton.setOnClickListener {
            CharacterManager.instance.saveCharacter()
            customDialog.dismiss()
            initializeStats(iteration + 1, inflater, context)
        }
    }

    fun initializeAttributes(iteration: Int, inflater: LayoutInflater, context: Context) {
        if(iteration > 5 || iteration < 0) {
            return
        }
        attributeIteration = iteration
        var newExponent = 0

        val testDialog = AlertDialog.Builder(context)
        testDialog.setView(inflater.inflate(R.layout.skill_initialization_dialogue, null))
        val customDialog = testDialog.create()
        customDialog.show()
        customDialog.skillName.setText(ATTRIBUTE_NAME[iteration], TextView.BufferType.EDITABLE)
        customDialog.skillExp.text = character.attributes[iteration].mExponent.toString()
        customDialog.skillName.isEnabled = false
        customDialog.inc.setOnClickListener {
            if (newExponent < 10) {
                ++newExponent
                character.attributes[iteration].mExponent = newExponent
                customDialog.skillExp.text = newExponent.toString()
            } else {
                Toast.makeText(context, "Cannot Set Exponent greater than 10", Toast.LENGTH_SHORT).show()
            }
        }
        customDialog.dec.setOnClickListener {
            if (newExponent > 0) {
                --newExponent
                character.attributes[iteration].mExponent = newExponent
                customDialog.skillExp.text = newExponent.toString()
            } else {
                Toast.makeText(context, "Cannot Set Exponent less than 0", Toast.LENGTH_SHORT).show()
            }
        }
        customDialog.prevButton.setOnClickListener {
            character.attributes[iteration].mExponent = newExponent
            CharacterManager.instance.saveCharacter()
            customDialog.dismiss()
            initializeAttributes(iteration - 1, inflater, context)
        }
        customDialog.nextButton.setOnClickListener {
            character.attributes[iteration].mExponent = newExponent
            CharacterManager.instance.saveCharacter()
            customDialog.dismiss()
            initializeAttributes(iteration + 1, inflater, context)
        }
    }

    @SuppressLint("NewApi")
    fun initializeSkills( inflater: LayoutInflater, context: Context) {
        var newExponent = 0

        val testDialog = AlertDialog.Builder(context)
        testDialog.setView(inflater.inflate(R.layout.skill_initialization_dialogue, null))
        val customDialog = testDialog.create()
        customDialog.show()
        customDialog.setCancelable(false)
        customDialog.skillName.requestFocus()
        customDialog.skillName.showSoftInputOnFocus
        customDialog.nextButton.text = "Add"
        customDialog.prevButton.visibility = View.GONE
        customDialog.doneButton.visibility = View.VISIBLE
        customDialog.inc.setOnClickListener {
            if (newExponent < 10) {
                ++newExponent
                customDialog.skillExp.text = newExponent.toString()
            } else {
                Toast.makeText(context, "Cannot Set Exponent greater than 10", Toast.LENGTH_SHORT).show()
            }
        }
        customDialog.dec.setOnClickListener {
            if (newExponent > 0) {
                --newExponent
                customDialog.skillExp.text = newExponent.toString()
            } else {
                Toast.makeText(context, "Cannot Set Exponent less than 0", Toast.LENGTH_SHORT).show()
            }
        }
        customDialog.doneButton.setOnClickListener {
            character.skills.add(Skill(customDialog.skillName.text.toString(), newExponent))
            CharacterManager.instance.saveCharacter()
            customDialog.dismiss()

        }
        customDialog.nextButton.setOnClickListener {
            character.skills.add(Skill(customDialog.skillName.text.toString(), newExponent))
            CharacterManager.instance.saveCharacter()
            customDialog.dismiss()
            initializeSkills(inflater, context)
        }
    }

}
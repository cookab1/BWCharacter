package andyr.bascomC.bwcharacter

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import andyr.bascomC.bwcharacter.Utilities.*
import kotlinx.android.synthetic.main.skill_description_list_item.*


open class BaseActivity : AppCompatActivity() {

    private val TAG = "LifeCycle_Logging"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "On Create")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "On Resume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "On Restart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "On Destroy")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "On Stop")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "On Pause")
        overridePendingTransition(0,0)
    }

    fun showSkillDescription(name: String, context: Context) {
        val description = DESCRIPTIONS[name]
        if (description != null) {
            val testDialog = AlertDialog.Builder(this)
            testDialog.setView(layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.skill_description_list_item, null))
            val customDialog = testDialog.create()
            customDialog.show()
            val underlinedName = SpannableString(name)
            val underlinedRoot = SpannableString(description.root)
            underlinedName.setSpan(UnderlineSpan(), 0, name.length, 0)
            underlinedRoot.setSpan(UnderlineSpan(), 0, description.root.length, 0)
            customDialog.skillName.text = underlinedName
            customDialog.skillRoot.text = underlinedRoot
            customDialog.skillDescription.text = Html.fromHtml(compileDescription(description, context))
        } else {
//            val testDialog = AlertDialog.Builder(this)
//            testDialog.setTitle("Invalid Skill")
//            testDialog.setMessage("It could be that you misspelled the skill.\nPlease make sure all capitals are correct and there are no extra spaces.\nOr it could be that $name's description has not been added yet.\nCheck the skill descriptions page to see if it has.")
//            testDialog.create().show()
            val testDialog = AlertDialog.Builder(this)
            testDialog.setTitle(name)
            testDialog.setMessage("This will be the description of $name straight from the book!")
            testDialog.create().show()
        }
    }

    private fun compileDescription(skill: Descriptions, context: Context) : String {
        var description: String = skill.description + "<br/><br/>"

        for ((key, value) in skill.extras) {
            when (key) {
                OBSTACLES -> description += "<b>" + context.getString(com.bascomC.bwcharacter.R.string.obstacles) + "</b>"
                FORKS -> description += "<b>" + context.getString(com.bascomC.bwcharacter.R.string.forks) + "</b>"
                SKILLTYPE -> description += "<b>" + context.getString(com.bascomC.bwcharacter.R.string.skilltype) + "</b>"
                TOOLS -> description += "<b>" + context.getString(com.bascomC.bwcharacter.R.string.tools) + "</b>"
                RESTRICTIONS -> description += "<b>" + context.getString(com.bascomC.bwcharacter.R.string.restrictions) + "</b>"
            }
            if (value != "") {
                description += ": "
                description += value
            }
            description += "<br/>"
        }

        return description
    }

    //helper functions for all activities

//    fun confirmDialogue(confirmationMessage: String, context: Context) : Boolean {
//        var active = true
//        var ret = false
//        val viewDialog = AlertDialog.Builder(context)
//        viewDialog.setMessage(confirmationMessage)
//        viewDialog.setPositiveButton("Yes", { dialogInterface, i ->  })
//        viewDialog.setNegativeButton("No", { dialogInterface, i ->  })
//        viewDialog.setCancelable(false)
//        val customDialog = viewDialog.create()
//        customDialog.show()
//        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
//            ret = true
//            active = false
//            customDialog.dismiss()
//        }
//        customDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener {
//            active = false
//            customDialog.dismiss()
//        }
//
//        while (active) {}
//        return ret
//    }
}
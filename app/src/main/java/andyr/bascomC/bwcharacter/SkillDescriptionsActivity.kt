package andyr.bascomC.bwcharacter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import Utilities.DESCRIPTIONS
import android.content.Context
import android.os.SystemClock
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_skill_description.*

class SkillDescriptionsActivity : BaseActivity() {

    private val descriptionListFull: ArrayList<Descriptions> = createDescriptionList(DESCRIPTIONS)
    private var descriptionList: ArrayList<Descriptions> = descriptionListFull

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.bascomC.bwcharacter.R.layout.activity_skill_description)

//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)

        skillDescriptionRecyclerView.layoutManager = LinearLayoutManager(this)
        skillDescriptionRecyclerView.adapter =
            SkillDescriptionListAdapter(descriptionList, this)

        returnToCharacter.setOnClickListener{
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }

        searchButton.setOnClickListener {
            if (skillSearchBar.visibility == View.GONE) {
                skillSearchBar.visibility = View.VISIBLE
                skillSearchBar.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN , 0f, 0f, 0))
                skillSearchBar.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP , 0f, 0f, 0))
            } else {
                skillSearchBar.setText("", TextView.BufferType.EDITABLE)
                skillSearchBar.visibility = View.GONE
            }
        }
        skillSearchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(filter: CharSequence, start: Int, before: Int, count: Int) {
                if (before > count) {
                    resetDescriptionList()
                    filterDescriptions(filter)
                } else if (before < count){
                    filterDescriptions(filter)
                }
            }
        })
    }

    private fun createDescriptionList(descriptions: Map<String, Descriptions>) : ArrayList<Descriptions> {
        var retDescriptions : ArrayList<Descriptions> = arrayListOf()
        for ((name, description) in descriptions) {
            if(name == "Abbey-wise") {
                description.name = "\n$name"
            } else {
                description.name = name
            }
            if (name.contains("wise", true)) {
                retDescriptions.add(Descriptions("", "Perception", mapOf(), description.name))
            } else {
                retDescriptions.add(description)
            }
        }
        val progress = descriptions.size
        retDescriptions.add(
            Descriptions(
                "This is as far as Andy has gotten. There are over 400 skills that he is typing in manually. He's only up to $progress. Thank you for your patience",
                "Will",
                mapOf(),
                "Patience"
            )
        )
        return retDescriptions
    }

    private fun filterDescriptions(filter: CharSequence) {
        var descriptionFilter : ArrayList<Descriptions> = arrayListOf()
        if (filter != "") {
            for (description in descriptionList) {
                if (!description.name.contains(filter, true)) {
                    descriptionFilter.add(description)
                }
            }
            descriptionList.removeAll(descriptionFilter)
        }
        skillDescriptionRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun resetDescriptionList() {
        descriptionList.clear()
        descriptionList.addAll(createDescriptionList(DESCRIPTIONS))
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when(item.itemId) {
//            R.id.action_descriptions -> {
//                val intent = Intent(this, StatsActivity::class.java)
//                startActivity(intent)
//                true
//            }
//            else -> {
//                super.onOptionsItemSelected(item)
//            }
//        }
//    }

}
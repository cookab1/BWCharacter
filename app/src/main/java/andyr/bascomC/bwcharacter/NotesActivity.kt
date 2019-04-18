package andyr.bascomC.bwcharacter

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.bascomC.bwcharacter.R
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.note_layout.*
import kotlinx.android.synthetic.main.note_list_item.*

class NotesActivity: BaseActivity() {

    private val notes = arrayOf("Note1", "Note2", "Note3", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
    private lateinit var character: Character
    private var onGearTab = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        character = CharacterManager.instance.getCharacter()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        notesRecyclerView.layoutManager = LinearLayoutManager(this)
        setList(character.gear)

        // Button Listeners
        stats3.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        skills3.setOnClickListener {
            val intent = Intent(this, SkillsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        infoToggleButton.setOnClickListener {
            val intent = Intent(this, BeliefsInstinctsActivity::class.java)
            startActivity(intent)
        }

        gearButton.setOnClickListener {
            headerGear.visibility = View.VISIBLE
            headerNotes.visibility = View.INVISIBLE
            if(!onGearTab) {
                onGearTab = true
                setList(character.gear)
            }
        }
        notesButton.setOnClickListener {
            headerGear.visibility = View.INVISIBLE
            headerNotes.visibility = View.VISIBLE
            if(onGearTab) {
                onGearTab = false
                setList(character.notes)
            }
        }

        noteFab.setOnClickListener { addRecord() }
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

    private fun addRecord() {
        val noteDialog = AlertDialog.Builder(this)
        noteDialog.setView(layoutInflater.inflate(R.layout.note_layout, null))
        val customDialog = noteDialog.create()
        customDialog.show()
//        customDialog.noteEditView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN , 0f, 0f, 0))
//        customDialog.noteEditView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP , 0f, 0f, 0))
        customDialog.noteDoneButton.setOnClickListener {
            if(customDialog.noteEditView.text.isNotEmpty()) {
                if (onGearTab) {
                    character.gear.add(customDialog.noteEditView.text.toString())
                } else {
                    character.notes.add(customDialog.noteEditView.text.toString())
                }
                CharacterManager.instance.saveCharacter()
                notesRecyclerView.adapter?.notifyDataSetChanged()
            }
            customDialog.dismiss()
        }
    }

    private fun setList(list: ArrayList<String>) {
        notesRecyclerView.adapter = NoteListAdapter(
            list,
            object : ClickListener {
                override fun onPositionClicked(position: Int, view: View) {
                    handleNoteClick(position, view)
                }

                override fun onLongClicked(position: Int, view: View) {
                    handleLongNoteClick(position, view)
                }
            })
    }

    private fun handleNoteClick(position: Int, view: View) {
        var list = character.gear
        if (!onGearTab) {
            list = character.notes
        }
        if (view.id == noteDeleteButton.id) {
            val viewDialog = AlertDialog.Builder(view.context)
            viewDialog.setMessage("Are you sure you want to delete this note?")
            viewDialog.setPositiveButton("Yes") { _, _ ->  }
            viewDialog.setNegativeButton("No") { _, _ ->  }
            viewDialog.setCancelable(false)
            val customDialog = viewDialog.create()
            customDialog.show()
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                list.removeAt(position)
                if (onGearTab) {
                    character.gear = list
                } else {
                    character.notes = list
                }
                CharacterManager.instance.saveCharacter()
                notesRecyclerView.adapter?.notifyDataSetChanged()
                customDialog.dismiss()
            }
        } else {
            val editDialog = AlertDialog.Builder(view.context)
            editDialog.setView(layoutInflater.inflate(R.layout.note_layout, null))
            val customDialog = editDialog.create()
            customDialog.show()
            customDialog.noteEditView.setText(list[position], TextView.BufferType.EDITABLE)
            customDialog.noteDoneButton.setOnClickListener {
                if(customDialog.noteEditView.text.isNotEmpty()) {
                    list[position] = customDialog.noteEditView.text.toString()
                    if (onGearTab) {
                        character.gear = list
                    } else {
                        character.notes = list
                    }
                    CharacterManager.instance.saveCharacter()
                    notesRecyclerView.adapter?.notifyItemChanged(position)
                }
                customDialog.dismiss()
            }
        }
    }

    private fun handleLongNoteClick(position: Int, view: View) {
        if (view.id == noteDeleteButton.id) {
            if (onGearTab) {
                character.gear.removeAt(position)
            } else {
                character.notes.removeAt(position)
            }
            CharacterManager.instance.saveCharacter()
            notesRecyclerView.adapter?.notifyDataSetChanged()
        }
    }
}
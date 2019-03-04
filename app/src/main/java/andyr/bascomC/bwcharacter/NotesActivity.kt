package andyr.bascomC.bwcharacter

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.note_layout.*
import kotlinx.android.synthetic.main.note_list_item.*

class NotesActivity: andyr.bascomC.bwcharacter.BaseActivity() {

    private val notes = arrayOf("Note1", "Note2", "Note3", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
    private lateinit var character: andyr.bascomC.bwcharacter.Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.bascomC.bwcharacter.R.layout.activity_notes)

        character = andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.getCharacter()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        notesRecyclerView.layoutManager = LinearLayoutManager(this)
        notesRecyclerView.adapter = andyr.bascomC.bwcharacter.NoteListAdapter(
            character.notes,
            object : andyr.bascomC.bwcharacter.ClickListener {
                override fun onPositionClicked(position: Int, view: View) {
                    handleNoteClick(position, view)
                }

                override fun onLongClicked(position: Int, view: View) {
                    handleLongNoteClick(position, view)
                }
            })

        // Button Listeners
        stats3.setOnClickListener {
            val intent = Intent(this, andyr.bascomC.bwcharacter.StatsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        skills3.setOnClickListener {
            val intent = Intent(this, andyr.bascomC.bwcharacter.SkillsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        noteFab.setOnClickListener { addNote() }
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

    private fun addNote() {
        val noteDialog = AlertDialog.Builder(this)
        noteDialog.setView(layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.note_layout, null))
        val customDialog = noteDialog.create()
        customDialog.show()
        customDialog.noteDoneButton.setOnClickListener {
            if(customDialog.noteEditView.text.isNotEmpty()) {
                character.notes.add(customDialog.noteEditView.text.toString())
                andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                notesRecyclerView.adapter?.notifyDataSetChanged()
            }
            customDialog.dismiss()
        }
    }

    private fun handleNoteClick(position: Int, view: View) {
        if (view.id == noteDeleteButton.id) {
            val viewDialog = AlertDialog.Builder(view.context)
            viewDialog.setMessage("Are you sure you want to delete this note?")
            viewDialog.setPositiveButton("Yes") { _, _ ->  }
            viewDialog.setNegativeButton("No") { _, _ ->  }
            viewDialog.setCancelable(false)
            val customDialog = viewDialog.create()
            customDialog.show()
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                character.notes.removeAt(position)
                andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                notesRecyclerView.adapter?.notifyDataSetChanged()
                customDialog.dismiss()
            }
        } else {
            val editDialog = AlertDialog.Builder(view.context)
            editDialog.setView(layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.note_layout, null))
            val customDialog = editDialog.create()
            customDialog.show()
            customDialog.noteEditView.setText(character.notes[position], TextView.BufferType.EDITABLE)
            customDialog.noteDoneButton.setOnClickListener {
                if(customDialog.noteEditView.text.isNotEmpty()) {
                    character.notes[position] = customDialog.noteEditView.text.toString()
                    andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
                    notesRecyclerView.adapter?.notifyItemChanged(position)
                }
                customDialog.dismiss()
            }
        }
    }

    private fun handleLongNoteClick(position: Int, view: View) {
        if (view.id == noteDeleteButton.id) {
            character.notes.removeAt(position)
            andyr.bascomC.bwcharacter.CharacterManager.Companion.instance.saveCharacter()
            notesRecyclerView.adapter?.notifyDataSetChanged()
        }
    }
}
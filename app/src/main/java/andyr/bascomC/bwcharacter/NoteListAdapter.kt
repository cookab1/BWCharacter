package andyr.bascomC.bwcharacter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.lang.ref.WeakReference

class NoteListAdapter(val list: ArrayList<String>, clickListener: andyr.bascomC.bwcharacter.ClickListener): RecyclerView.Adapter<andyr.bascomC.bwcharacter.NoteListAdapter.NoteViewHolder>() {

    val listener = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): andyr.bascomC.bwcharacter.NoteListAdapter.NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.note_list_item, parent, false)
        return NoteViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: andyr.bascomC.bwcharacter.NoteListAdapter.NoteViewHolder, position: Int) {
        holder.note.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class NoteViewHolder(itemview: View, listener: andyr.bascomC.bwcharacter.ClickListener): RecyclerView.ViewHolder(itemview),
        View.OnClickListener, View.OnLongClickListener {

        val listenerRef = WeakReference(listener)
        val note = itemView.findViewById<TextView>(com.bascomC.bwcharacter.R.id.noteTextView)
        val delete = itemView.findViewById<ImageView>(com.bascomC.bwcharacter.R.id.noteDeleteButton)

        init {
            itemView.setOnClickListener(this)
//            itemView.setOnLongClickListener(this)
            delete.setOnClickListener(this)
            delete.setOnLongClickListener(this)
        }

        override fun onClick(v: View) {

            if (v.id == note.id) {
            } else {
//                Toast.makeText(v.context, "ROW PRESSED = $adapterPosition", Toast.LENGTH_SHORT).show()
            }

            listenerRef.get()?.onPositionClicked(adapterPosition, v)
        }

        override fun onLongClick(v: View): Boolean {
//            Toast.makeText(v.context, "LongClick PRESSED = $adapterPosition", Toast.LENGTH_SHORT).show()
//            val builder = AlertDialog.Builder(v.context)
//            builder.setTitle("Hello Dialog")
//                .setMessage("LONG CLICK DIALOG WINDOW FOR ICON $adapterPosition")
//                .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> })
//
//            builder.create().show()
            listenerRef.get()?.onLongClicked(adapterPosition, v)
            return true
        }
    }
}
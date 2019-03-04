package andyr.bascomC.bwcharacter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.bascomC.andyr.bwcharacter.Skill
import java.lang.ref.WeakReference


class ListAdapter(var list: ArrayList<Skill>, parentContext: Context, clickListener: andyr.bascomC.bwcharacter.ClickListener): RecyclerView.Adapter<andyr.bascomC.bwcharacter.ListAdapter.StatViewHolder>() {

    val context = parentContext
    val listener = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): andyr.bascomC.bwcharacter.ListAdapter.StatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.skill_list_item, parent, false)
        if (viewType == 1) {
            itemView = layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.add_item_layout, parent, false)
        }
        return StatViewHolder(itemView, listener)
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }

    override fun onBindViewHolder(holder: andyr.bascomC.bwcharacter.ListAdapter.StatViewHolder, position: Int) {
        holder.bindStat(list[position], context)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class StatViewHolder(itemView: View, listener: andyr.bascomC.bwcharacter.ClickListener) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {

        private val listenerRef = WeakReference(listener)
        private val statName = itemView.findViewById<TextView>(com.bascomC.bwcharacter.R.id.statName)
        private val statExp = itemView.findViewById<TextView>(com.bascomC.bwcharacter.R.id.statExponent)
        private val artha = itemView.findViewById<ImageButton>(com.bascomC.bwcharacter.R.id.fpdButton)
        private val tests = itemView.findViewById<ImageButton>(com.bascomC.bwcharacter.R.id.testsButton)
        private val addButton = itemView.findViewById<ImageButton>(com.bascomC.bwcharacter.R.id.addItemButton)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
            if (addButton == null) {
                tests.setOnClickListener(this)
                artha.setOnClickListener(this)
            } else {
                addButton.setOnClickListener(this)
            }
        }

        fun bindStat(stat: Skill, context: Context) {
            if (tests != null) {
                statName.text = stat.mName
                statExp.text = stat.mExponent.toString()
                tests.setImageDrawable(context.getDrawable(stat.getTestsImage(stat.mTests)))
            }
        }

        // onClick Listener for view
        override fun onClick(v: View) {

//            if (v.id == tests.id) {
//            } else if (v.id == editSkill.id) {
//            } else {
//                Toast.makeText(v.context, "ROW PRESSED = $adapterPosition", Toast.LENGTH_SHORT).show()
//            }

            listenerRef.get()?.onPositionClicked(adapterPosition, v)
        }


        //onLongClickListener for view
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
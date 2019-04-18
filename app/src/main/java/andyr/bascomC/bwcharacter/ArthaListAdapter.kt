package andyr.bascomC.bwcharacter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.bascomC.andyr.bwcharacter.Skill
import com.bascomC.bwcharacter.R
import java.lang.ref.WeakReference


class ArthaListAdapter(var list: ArrayList<Skill>, parentContext: Context, clickListener: ClickListener): RecyclerView.Adapter<ArthaListAdapter.StatViewHolder>() {

    val context = parentContext
    val listener = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArthaListAdapter.StatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(R.layout.skill_list_item, parent, false)
        return StatViewHolder(itemView, listener)
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }

    override fun onBindViewHolder(holder: ArthaListAdapter.StatViewHolder, position: Int) {
        holder.bind(list[position], context)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class StatViewHolder(itemView: View, listener: ClickListener) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {

        private val listenerRef = WeakReference(listener)
        private val statName = itemView.findViewById<TextView>(R.id.statName)
        private val statExp = itemView.findViewById<TextView>(R.id.statExponent)
        private val artha = itemView.findViewById<ImageButton>(R.id.fpdButton)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
            artha.setOnClickListener(this)
        }

        fun bind(stat: Skill, context: Context) {
            statName.text = stat.mName
            statExp.text = stat.mExponent.toString()
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
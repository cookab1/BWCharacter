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
import kotlinx.android.synthetic.main.skill_list_item.view.*
import java.lang.ref.WeakReference


class ListAdapter(var list: ArrayList<SkillObject>, parentContext: Context, clickListener: ClickListener): RecyclerView.Adapter<ListAdapter.StatViewHolder>() {

    val context = parentContext
    val listener = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.StatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(R.layout.skill_list_item, parent, false)
        when (viewType) {
            -1 -> {
                itemView = layoutInflater.inflate(R.layout.add_item_layout, parent, false)
            }
            1 -> {
                itemView = layoutInflater.inflate(R.layout.skill_list_item, parent, false)
                itemView.aptitude.visibility = View.VISIBLE
                itemView.statExponent.visibility = View.GONE
                itemView.imageView.visibility = View.GONE
            }
            2 -> {
                itemView.routineCover.visibility = View.VISIBLE
            }
            3 -> {
                itemView.testsButton.visibility = View.INVISIBLE
            }
            else -> {
                itemView.aptitude.visibility = View.GONE
                itemView.statExponent.visibility = View.VISIBLE
                itemView.imageView.visibility = View.VISIBLE
            }
        }
        return StatViewHolder(itemView, listener)
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }

    override fun onBindViewHolder(holder: ListAdapter.StatViewHolder, position: Int) {
        if (list[position].isLearning) {
            val learningList = list as ArrayList<LearningSkill>
            holder.bindLearning(learningList[position], context)
        } else {
            val skillList = list as ArrayList<Skill>
            holder.bindStat(skillList[position], context)
        }
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
        private val tests = itemView.findViewById<ImageButton>(R.id.testsButton)
        private val addButton = itemView.findViewById<ImageButton>(R.id.addItemButton)
        private val aptitude = itemView.findViewById<TextView>(R.id.aptitude)

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

        fun bindLearning(stat: LearningSkill, context: Context) {
            if (tests != null) {
                statName.text = stat.mName
                statExp.text = stat.mExponent.toString()
                tests.setImageDrawable(context.getDrawable(stat.getTestsImage(stat.mTests)))
                aptitude.text = stat.aptitude.toString()
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
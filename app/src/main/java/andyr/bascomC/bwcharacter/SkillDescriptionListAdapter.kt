package andyr.bascomC.bwcharacter

import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.text.style.UnderlineSpan
import android.text.SpannableString
import android.text.Spanned
import Utilities.*


class SkillDescriptionListAdapter(var list: ArrayList<andyr.bascomC.bwcharacter.Descriptions>, parentContext: Context) : RecyclerView.Adapter<andyr.bascomC.bwcharacter.SkillDescriptionListAdapter.SkillDescriptionViewHolder>() {

    var context = parentContext

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): andyr.bascomC.bwcharacter.SkillDescriptionListAdapter.SkillDescriptionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.skill_description_list_item, parent, false)
        if (position == list.size - 1) {
            itemView = layoutInflater.inflate(com.bascomC.bwcharacter.R.layout.add_item_layout, parent, false)
        }
        return SkillDescriptionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: andyr.bascomC.bwcharacter.SkillDescriptionListAdapter.SkillDescriptionViewHolder, position: Int) {
        holder.bindDescription(list[position])
    }


    inner class SkillDescriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameView = itemView.findViewById<TextView>(com.bascomC.bwcharacter.R.id.skillName)
        private val rootView = itemView.findViewById<TextView>(com.bascomC.bwcharacter.R.id.skillRoot)
        private val descriptionView = itemView.findViewById<TextView>(com.bascomC.bwcharacter.R.id.skillDescription)

        fun bindDescription(item: andyr.bascomC.bwcharacter.Descriptions) {
            val underlinedName = SpannableString(item.name)
            val underlinedRoot = SpannableString(item.root)
            underlinedName.setSpan(UnderlineSpan(), 0, item.name.length, 0)
            underlinedRoot.setSpan(UnderlineSpan(), 0, item.root.length, 0)
            nameView.text = underlinedName
            rootView.text = underlinedRoot
            descriptionView.text = Html.fromHtml(compileDescription(item))
        }

        private fun compileDescription(skill: andyr.bascomC.bwcharacter.Descriptions) : String {
            var space = "<br/><br/>"
            if (skill.name.contains("wise", true)) {
                space = ""
            }
            var description: String = skill.description + space

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

        private fun getSpannedText(text: String): Spanned? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(text)
            }
        }
    }
}
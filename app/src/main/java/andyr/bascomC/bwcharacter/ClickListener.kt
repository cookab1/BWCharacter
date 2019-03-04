package andyr.bascomC.bwcharacter

import android.view.View

interface ClickListener {

    fun onPositionClicked(position: Int, view: View)
    fun onLongClicked(position: Int, view: View)

}
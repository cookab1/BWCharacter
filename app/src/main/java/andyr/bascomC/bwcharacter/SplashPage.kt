package andyr.bascomC.bwcharacter

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Window
import kotlinx.android.synthetic.main.splash_page.*

class SplashPage : AppCompatActivity() {

    val SPLASH_TIME_OUT = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(com.bascomC.bwcharacter.R.layout.splash_page)

        Handler().postDelayed({
            val intent = Intent(this, andyr.bascomC.bwcharacter.StatsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }, SPLASH_TIME_OUT.toLong())

        splashButton.setOnClickListener {
            val intent = Intent(this, andyr.bascomC.bwcharacter.StatsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }
    }
}
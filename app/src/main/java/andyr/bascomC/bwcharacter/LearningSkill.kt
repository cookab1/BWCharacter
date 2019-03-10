package andyr.bascomC.bwcharacter

import com.bascomC.bwcharacter.R

class LearningSkill(mName: String, var aptitude: Int = 0, viewType: Int = 1, mShade: Int = 0, mTests: Int = 0) : SkillObject(mName, mapOf(
    0 to R.drawable.test0, 1 to R.drawable.test100, 2 to R.drawable.test200, 3 to R.drawable.test300,
    4 to R.drawable.test400, 5 to R.drawable.test410, 6 to R.drawable.test420, 7 to R.drawable.test430,
    8 to R.drawable.test440, 9 to R.drawable.test441, 10 to R.drawable.test442
), 0, viewType, mShade, mTests, true) {

    //Helper functions
    fun canLearn(): Boolean {
        if (mTests >= aptitude) {
            return true
        }
        return false
    }
}
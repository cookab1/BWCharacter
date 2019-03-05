package andyr.bascomC.bwcharacter

open class SkillObject(var mName : String, var testsToImage : Map<Int, Int>, var mExponent: Int, var viewType: Int, var mShade: Int, var mTests: Int) {

    //Helper functions
    open fun getTestsImage(tests: Int): Int {
        return testsToImage.getValue(tests)
    }
}
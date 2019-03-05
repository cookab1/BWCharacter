package com.bascomC.andyr.bwcharacter

import andyr.bascomC.bwcharacter.SkillObject
import com.bascomC.bwcharacter.R
import java.util.*

/**
 * Created by andyr on 2/16/2019.
 */
class Skill(mName: String, mExponent: Int = 0, viewType: Int = 0, mShade: Int = 0, mTests: Int = 0) : SkillObject(mName, mapOf(
    0 to R.drawable.test0, 1 to R.drawable.test1, 2 to R.drawable.test2, 3 to R.drawable.test3,
    10 to R.drawable.test10, 11 to R.drawable.test11, 12 to R.drawable.test12, 13 to R.drawable.test13,
    20 to R.drawable.test20, 21 to R.drawable.test21, 22 to R.drawable.test22, 23 to R.drawable.test23,
    30 to R.drawable.test30, 31 to R.drawable.test31, 32 to R.drawable.test32, 33 to R.drawable.test33,
    40 to R.drawable.test40, 41 to R.drawable.test41, 42 to R.drawable.test42, 43 to R.drawable.test43,
    100 to R.drawable.test100, 101 to R.drawable.test101, 102 to R.drawable.test102, 103 to R.drawable.test103,
    110 to R.drawable.test110, 111 to R.drawable.test111, 112 to R.drawable.test112, 113 to R.drawable.test113,
    120 to R.drawable.test120, 121 to R.drawable.test121, 122 to R.drawable.test122, 123 to R.drawable.test123,
    130 to R.drawable.test130, 131 to R.drawable.test131, 132 to R.drawable.test132, 133 to R.drawable.test133,
    140 to R.drawable.test140, 141 to R.drawable.test141, 142 to R.drawable.test142, 143 to R.drawable.test143,
    200 to R.drawable.test200, 201 to R.drawable.test201, 202 to R.drawable.test202, 203 to R.drawable.test203,
    210 to R.drawable.test210, 211 to R.drawable.test211, 212 to R.drawable.test212, 213 to R.drawable.test213,
    220 to R.drawable.test220, 221 to R.drawable.test221, 222 to R.drawable.test222, 223 to R.drawable.test223,
    230 to R.drawable.test230, 231 to R.drawable.test231, 232 to R.drawable.test232, 233 to R.drawable.test233,
    240 to R.drawable.test240, 241 to R.drawable.test241, 242 to R.drawable.test242, 243 to R.drawable.test243,
    300 to R.drawable.test300, 301 to R.drawable.test301, 302 to R.drawable.test302, 303 to R.drawable.test303,
    310 to R.drawable.test310, 311 to R.drawable.test311, 312 to R.drawable.test312, 313 to R.drawable.test313,
    320 to R.drawable.test320, 321 to R.drawable.test321, 322 to R.drawable.test322, 323 to R.drawable.test323,
    330 to R.drawable.test330, 331 to R.drawable.test331, 332 to R.drawable.test332, 333 to R.drawable.test333,
    340 to R.drawable.test340, 341 to R.drawable.test341, 342 to R.drawable.test342, 343 to R.drawable.test343,
    400 to R.drawable.test400, 401 to R.drawable.test401, 402 to R.drawable.test402, 403 to R.drawable.test403,
    410 to R.drawable.test410, 411 to R.drawable.test411, 412 to R.drawable.test412, 413 to R.drawable.test413,
    420 to R.drawable.test420, 421 to R.drawable.test421, 422 to R.drawable.test422, 423 to R.drawable.test423,
    430 to R.drawable.test430, 431 to R.drawable.test431, 432 to R.drawable.test432, 433 to R.drawable.test433,
    440 to R.drawable.test440, 441 to R.drawable.test441, 442 to R.drawable.test442, 443 to R.drawable.test443), mExponent, viewType, mShade, mTests) {
    private val requiredTests: Array<IntArray> = arrayOf(
        intArrayOf(0), intArrayOf(110, 101), intArrayOf(210, 201), intArrayOf(320, 301), intArrayOf(420, 401),
        intArrayOf(31), intArrayOf(32), intArrayOf(42), intArrayOf(43), intArrayOf(53))

//    val mID: UUID = UUID.randomUUID()

    //Helper functions
    fun canUpgrade(): Boolean {
        val req: IntArray = requiredTests[mExponent]

        for (reqTests in req) {
            if (compareTests(mTests, reqTests, true)) {
                return true
            }
        }
        return false
    }

    fun compareTests(tests: Int, req: Int, boolTracker: Boolean): Boolean {
        //recursive function that tests the two values in the appropriate way
        if (tests == 0) {
            return true
        } else if (tests % 10 >= req % 10) {
            return compareTests((tests / 10), (req / 10), true)
        } else {
            return false
        }
    }
}
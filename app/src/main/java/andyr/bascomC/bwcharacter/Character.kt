package andyr.bascomC.bwcharacter

import com.bascomC.andyr.bwcharacter.Skill

class Character {
    var stats : ArrayList<Skill> = arrayListOf(Skill("Will", 0, 2), Skill("Power", 0, 2), Skill("Agility", 0, 2), Skill("Perception", 0, 2), Skill("Forte", 0, 2), Skill("Speed", 0, 2))
    var attributes : ArrayList<Skill> = arrayListOf(Skill("Health"), Skill("Steel"), Skill("Reflexes", 0, 3), Skill("Mortal Wound", 0, 3), Skill("Circles"), Skill("Resources"))
    var skills : ArrayList<Skill> = arrayListOf()
    var learning : ArrayList<LearningSkill> = arrayListOf()
    var skillsWithArtha : ArrayList<Skill> = arrayListOf()
    var artha : ArrayList<Int> = arrayListOf(0, 0, 0)
    var beliefs : ArrayList<String> = arrayListOf("", "", "")
    var instincts : ArrayList<String> = arrayListOf("", "", "")
    var traits : ArrayList<Trait> = arrayListOf()
    var Relationships : ArrayList<Relationship> = arrayListOf()
    var gear : ArrayList<String> = arrayListOf()
    var notes : ArrayList<String> = arrayListOf()
    var posessions : ArrayList<String> = arrayListOf()
    var practice : ArrayList<String> = arrayListOf()
    var injury : Int = 0
}
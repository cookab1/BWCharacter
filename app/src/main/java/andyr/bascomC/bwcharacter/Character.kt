package andyr.bascomC.bwcharacter

import com.bascomC.andyr.bwcharacter.Skill

class Character {
    var stats : ArrayList<Skill> = arrayListOf(Skill("Will"), Skill("Power"), Skill("Agility"), Skill("Perception"), Skill("Forte"), Skill("Speed"))
    var attributes : ArrayList<Skill> = arrayListOf(Skill("Health"), Skill("Steel"), Skill("Reflexes"), Skill("Mortal Wound"), Skill("Circles"), Skill("Resources"))
    var skills : ArrayList<Skill> = arrayListOf()
    var learning : ArrayList<Skill> = arrayListOf()
    var notes : ArrayList<String> = arrayListOf()
    var artha : ArrayList<Int> = arrayListOf(0, 0, 0)
    var beliefs : ArrayList<String> = arrayListOf("", "", "")
    var instincts : ArrayList<String> = arrayListOf("", "", "")
    var traits : ArrayList<andyr.bascomC.bwcharacter.Trait> = arrayListOf()
    var Relationships : ArrayList<andyr.bascomC.bwcharacter.Relationship> = arrayListOf()
    var gear : ArrayList<String> = arrayListOf()
    var posessions : ArrayList<String> = arrayListOf()
    var practice : ArrayList<String> = arrayListOf()
    var injury : Int = 0
}
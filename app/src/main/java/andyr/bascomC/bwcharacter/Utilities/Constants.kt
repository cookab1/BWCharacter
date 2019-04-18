package Utilities

import android.util.EventLogTags
import andyr.bascomC.bwcharacter.Descriptions
import java.util.*

private const val wiseDescription: String = "A wise is a skill through which a character can call upon the knowledge of various details of the game world--knowledge of \"who's who\" or \"what's what\" for the areas encompassed by his wises. For the most part, wises are fairly self-explanatory--their name says it all."
private const val perception = "Perception"
private const val will = "Will"
private const val power = "Power"
private const val agility = "Agility"
private const val forte = "Forte"
private const val speed = "Speed"
private const val per_agi = "Per/Agility"
private const val will_agility = "Will/Agility"
private const val per_power = "Per/Power"
private const val will_per = "Will/Per"
private const val power_agility = "Power/Agility"
private const val will_power = "Will/Power"
private const val will_forte = "Will/Forte"
private const val power_speed = "Power/Speed"

const val EXTRA_CHARACTER = "character"

val STAT_NAME = listOf("Will", "Power", "Agility", "Perception", "Forte", "Speed")
val ATTRIBUTE_NAME = listOf("Health", "Steel", "Reflexes", "Mortal Wound", "Circles", "Resources")
val TEST_TYPE = listOf("Routine", "Difficult", "Challenging")
val TRAIT_TYPE = listOf("Char", "DT", "Call-On")
val RELATIONSHIP_TYPE = listOf("Relationships", "Circles", "Named Circles", "Enemy Circles")
val INJURYS = listOf("", "SU", "LI", "MI", "SE", "TR", "MO")
val ROOT_TO_Int = mapOf(will to 0, power to 1, agility to 2, perception to 3, forte to 4, speed to 5, per_agi to 32, per_power to 31, will_per to 30, will_agility to 20, will_power to 10, power_speed to 15, power_agility to 12)

const val OBSTACLES = "Obstacles"
const val FORKS = "FoRKs"
const val SKILLTYPE = "Skill Type"
const val TOOLS = "Tools"
const val RESTRICTIONS = "Restrictions"

val DESCRIPTIONS : Map<String, Descriptions> = mapOf(

    // Section A
    "Abbey-wise" to Descriptions(wiseDescription, perception, mapOf()),
    "Accounting" to Descriptions(
        "Accountants keep track of expenses, income, stocks and taxes. This skill is primarily used to recover taxed Resources",
        perception, mapOf(
            OBSTACLES to "Calculating taxes for a single income, single residence individual, Ob 1.<br/>Maintaining simple personal finances, Ob 2.<br/>Maintaining a family's income, Ob 3.<br/>A small business' books, Ob 4.<br/>An estate's books, Ob 5.<br/>A large business' books, Ob 6.<br/>A town's books, Ob 7.<br/>A city's books, Ob 8.<br/>A province's boks, Ob 9.<br/>A nation's books, Ob 10.",
            FORKS to "Research, Reading, Administration, Estate Management",
            SKILLTYPE to "Academic",
            TOOLS to "Yes"
        )
    ),
    "Acting" to Descriptions(
        "The skill of imitation and recitation used to effect a performance.",
        perception, mapOf(
            OBSTACLES to "Mummery, Ob 1.<br/>Morality, Ob 2.<br/>Religious miracle, Ob 3.<br/>Religious mystery, Ob 4.<br/>Classics, Ob 5.",
            FORKS to "Conspicuous, Falsehood",
            SKILLTYPE to "Special",
            TOOLS to "No"
        )
    ),
    "Administration" to Descriptions(
        "Administration is the skill of running a business or organization. This skill is primarily used to recover taxed Resources.",
        perception, mapOf(
            OBSTACLES to "Just you, Ob 1.<br/>A shop, Ob 2.<br/>Confraternity, Ob 3.<br/>Small commune, Ob 4.<br/>Small guild, Ob 5.<br/>Small secret society, Ob 6.<br/>Large secret society, Ob 7.<br/>Small trading concern, Ob 8.<br/>Large guild, Ob 9.<br/>Giant trading concern, Ob 10.",
            SKILLTYPE to "Special",
            TOOLS to "No"
        )
    ),
    "Ages of the Etharch" to Descriptions(
        "This is a specialized History skill relating to the ages of the Etharchs.",
        will_per, mapOf(
            OBSTACLES to "Etharchs of the current age, Ob 1.<br/>Etharchs of the Third Age, Ob 2.<br/>&nbsp;&nbsp;of the Second Age, Ob 3.<br/>&nbsp;&nbsp;of the First Age, Ob 4.<br/>&nbsp;&nbsp;of the Years of the Sun, Ob 5.<br/>Before the first Etharchs, Ob 6.",
            SKILLTYPE to "Academic",
            TOOLS to "No",
            RESTRICTIONS to "Elves only"
        )
    ),
    "Alchemy" to Descriptions(
        "Alchemy is the distillation of materials in order to divine their essence. Also, Alchemists can create mixtures of arcane substances to generate a specific effect.",
        perception, mapOf(
            OBSTACLES to "Distilling the essential components of earth, Ob 1.<br/>&nbsp;&nbsp;of water or liquid, Ob 2.<br/>&nbsp;&nbsp;of metal, Ob 3.<br/>&nbsp;&nbsp;of blood, Ob 4.",
            FORKS to "Enchanting, Herbalism, Apothecary, Munitions, Poisons",
            SKILLTYPE to "Sorcerous",
            TOOLS to "Expendable",
            RESTRICTIONS to "Humans only"
        )
    ),
    "Almanac" to Descriptions(
        "This particular and peculiar skill comprises a deep traditional and historical knowledge of the seasons, tides, weather, lunar phases and other climatic phenomena. A character with the Almanac skill can always tell the time of year, nearly to the day. In addition, he can make accurate predictions about this season's weather.",
        perception, mapOf(
            OBSTACLES to "General seasonal weather, Ob 1.<br/>Planting and harvest times, Ob 2.<br/>Reasonably accurate weather prediction, Ob 3.<br/>Festivals and holidays, Ob 4.",
            SKILLTYPE to "Peasant",
            TOOLS to "No"
        )
    ),
    "Althing-wise" to Descriptions(wiseDescription, perception, mapOf()),
    "Ambition-wise" to Descriptions(wiseDescription, perception, mapOf()),
    "Amercement" to Descriptions(
        "Amercement is the knowledge of fees and criminal fines given as judicial punishment. Such amercements are almost always offered in place of corporal punishment, e.g. \"Ye may take 30 days in the stocks or ye may pay the court a fee.\" Using this skill the character amy set the Resources obstacle for a fine. The skill test obstacles indicate how accurate and just the amount is according to the law.",
        perception, mapOf(
            OBSTACLES to "Low justice (where the punishment is only a fine), Ob 2.<br/>Middle justice (where the punishment would be amputation or branding or the amercement), Ob 3.<br/>High justice (crimes punishable by death--liek murder, adultery, or grand larceny), Ob 5.",
            SKILLTYPE to "School of Thought",
            TOOLS to "No"
        )
    ),
    "Ancient Languages" to Descriptions(
        "Through this skill, the character knows defunct languages appropriate to the game setting. Use the mechanics for Foreign Languages.",
        perception, mapOf(
            SKILLTYPE to "Academic",
            TOOLS to "No"
        )
    ),
    "Anatomy" to Descriptions(
        "Anatomy is the study of the human body's internal functions and structure.",
        perception, mapOf(
            OBSTACLES to "Dog anatomy, Ob 1<br/>Superficial human anatomy, Ob 2.<br/>Mayor human organs, Ob 3.<br/>Circulatory system, Ob 4.<br/>Proper human dissection, Ob 5.<br/>",
            FORKS to "Surgery, Field Dressing",
            TOOLS to "No"
        )
    ),
    "Animal Husbandry" to Descriptions(
        "Animal Husbandry involves raising, care and breeding of animals.",
        will, mapOf(
            OBSTACLES to "Chickens, Ob 1.<br/>Pigs and goats, Ob 2.<br/>Cows and horses, Ob 3.<br/>Exotic animals, Ob 4.<br/>Tending to wounds and illness, use the Field Dressing or Apothecary skill obstacles.",
            SKILLTYPE to "Peasant",
            TOOLS to "Yes"
        )
    ),
    "Antiphon Union Training" to Descriptions(
        "This specialized training allows the Elven artisan to blend his crafts or arts with spell songs. Any of the items or materials he creates via a skill song can be imbued with the power of a spell song.<br/>An artisan who wishes to enchant an item, must first create it with a skill song like Jewelcraft, Riddle of Steel or Weaving Way. Once the item is created, the spell song must be sung. The singer has one chance to transfer his power. The song is sung according to its rules, but the effect generated is imbued into the item, and active whenever the item is used.<br/>No enchantment will hold unless the structure is of highest quality. Any item created by any skill song can be used. However, whether it's a shoe or a crown, the base obstacle to create the item is 5. If the obstacle would naturally be higher, use that instead. If the item is cheap or shoddy, it simply will not hold the spell.",
        "$will*", mapOf(
            SKILLTYPE to "Artisan",
            TOOLS to "No, but see skill song.",
            RESTRICTIONS to "Elves only",
            "*Training Root is for Aptitude only." to ""
        )
    ),
    "Apothecary" to Descriptions(
        "Apothecaries use herbs, roots, minerals and animalia to cure and prevent common and chronic ailments. Apothecaries may alleviate the symptoms of infection and illness; they may also alleviate wound die penalties",
        perception, mapOf(
            OBSTACLES to "Stop itching, Ob 2.<br/>Cure warts, Ob 3.<br/>Medicine to alleviate +1 Ob of pain, Ob 3.<br/>Medicine to numb an area, Ob 4: This reduces the wounded die penalty by one but increases all obstacles by one and lasts four hours.",
            FORKS to "Herbalism",
            SKILLTYPE to "Medicinal",
            TOOLS to "Yes. Expendable"
        )
    ),
    "Appraisal" to Descriptions(
        "This skill si used to judge the value or worth of certain items such as jewelry, gems, artwork and antiquities. The Appraisal skill can be used by a player before testing his Resources to ensure that he is getting an accurate price.",
        perception, mapOf(
            OBSTACLES to "Coin and precious metals, Ob 1.<br/>Gems, Ob 2.<br/>Tapestries, Ob 3.<br/>Armaments, Ob 3.<br/>Antiquities, Ob 4.<br/>Art, Ob 5.<br/>Ephemera--wine, drugs, candles, Ob 6.<br/>If something is magical, add +1 Ob penalty to appraisal.",
            FORKS to "Appropriate wises, academic, artisan or craftsman skills",
            SKILLTYPE to "Special",
            TOOLS to "No"
        )
    ),
    "Appropriate Weapons" to Descriptions(
        "This blanket entry is provided so the player may choose any and all of the weapons appropriate to his lifepaths, character concept and game setting. See the listing for the individual weapons obstacles and roots.",
        "", mapOf()
    ),
    "Archcraft" to Descriptions(
        "Archcraft is a comprehensive discipline teaching the principles of both architecture and engineering. Using this brad skill, Elven artisans create halls of sweeping beauty and depth. Combined with Antiphon Union and various spell songs, this skill can be used to create enchanted dwellings for the Elves. The following are but a few examples:<br/>Using Song of Arbors, buildings can be constructed to weave in and out of ancient trees; with Alarm, certain gates will warn of intruders; with Weathersong, an atrium can be built so it predicts the coming weather.",
        will_agility, mapOf(
            OBSTACLES to "Simple arch, Ob 1.<br/>Small gate, Ob 2.<br/>Large gate, Ob 3.<br/>Chamber, Ob 4.<br/>Domed hall, Ob 5.",
            FORKS to "Woodcraft, Stoncraft",
            SKILLTYPE to "Artisan",
            TOOLS to "Workshop",
            RESTRICTIONS to "Elves Only"
        )
    ),
    "Architect" to Descriptions(
        "Architects possess knowledge of the design and construction of complex structures, such as buildings, arches and bridges. This skill can be used in game to draw accurate plans of a proposed structure, attempt to draw structural plans for an extant building or use existing plans and designs to navigate buildings and structures.",
        perception, mapOf(
            OBSTACLES to "Simple plans like a cottage, Ob 1.<br/>A longhouse, Ob 2.<br/>Two story structure, Ob 4.<br/>Tower, Ob 5.<br/>Complex or weird designs like temples, Ob 6.<br/>Non-Euclidean Geometric Designs of the Outer Gods, Ob 10.",
            FORKS to "Engineer, Mason, Carpenter",
            SKILLTYPE to "Academic",
            TOOLS to "Workshop"
        )
    ),
    "Armor Training" to Descriptions(
        "To the unaccustomed, armor is heavy, hot and uncomfortable. Characters without Armor Training who wear light mail suffer +1 Ob penalty to all tests. Wearing heavy mail or heavier without Armor Training incurs a +2 Ob penalty. Armor Training mitigates these penalties completely. Clumsy Weight penalties still apply.",
        "$power_speed*", mapOf(
            SKILLTYPE to "Martial Training",
            TOOLS to "No",
            "*Training Root is for Aptitude only" to ""
        )
    ),
    "Armorer" to Descriptions(
        "This specialized and dedicated craft is used to manufacture personal protection worn by soldiers in battle. The skill’s knowledge base is diverse, encompassing aspects of a blacksmith, a tailor and a tanner.",
        per_agi, mapOf(
            OBSTACLES to "Gambeson, skull cap or leather hood: Ob 1.<br/>Reinforced leather armor, light helmet, pot helm: Ob 2.<br/>Light mail, spangenhelm: Ob 3.<br/>Heavy mail, open-faced bascinet, barbute: Ob 4.<br/>Plated mail, sallet, closed bascinet: Ob 5.<br/>Full plate, great helm: Ob 6.<br/>Target, Ob 1. Buckler, Ob 2. Great Shield, Ob 3. Kite, Ob 4.",
            FORKS to "Blacksmith, Sewing, Tanner",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop"
        )
    ),
    "Army-wise" to Descriptions(wiseDescription, perception, mapOf()),
    "Arson" to Descriptions(
        "This skill allows the character to use common household items to quickly set a building on fire.",
        per_agi, mapOf(
            OBSTACLES to "Thatched cottage, Ob 1.<br/>Munitions dump or powder room, Ob 2.<br/>House, Ob 3.<br/>Manor, Ob 4.<br/>Fortress, Ob 5.<br/>Prison, Ob 6.<br/>Catacombs, Ob 7.<br/>Castle, Ob 8.",
            SKILLTYPE to "Special",
            TOOLS to "Yes. Expendable",
            RESTRICTIONS to "Men and Roden only in character burning"
        )
    ),
    "Artificer-wise" to Descriptions(wiseDescription, perception, mapOf()),
    "Artillerist" to Descriptions(
        "Artillerists use basic physics and mathematics topped with some healthy guesswork to lob indirect-fire projectiles from war machines. They also know the designs for constructing various war engines. Actual construction requires teams of laborers and at least one carpenter.",
        perception, mapOf(
            OBSTACLES to "Carving shot from stone: Ob 1.<br/>Scavenging suitable shot, Ob 2.<br/>Building a small traction trebuchet: Ob 2.<br/>Building a large traction trebuchet, Ob 3.<br/>Building a small catapult, Ob 4.<br/>Building a counterweight trebuchet or siege crossbow, Ob 5.<br/>Building a large catapult, Ob 6.<br/>Ranging shots (aka, hitting the broadside of a barn): Siege crossbow, Ob 2: Small catapult or traction trebuchet, Ob 3: Counterweight trebuchet, Ob 3: Catapult, Ob 4.",
            FORKS to "Engineer, Architect",
            SKILLTYPE to "Military",
            TOOLS to "Workshop"
        )
    ),
    "Artillery Hand" to Descriptions(
        "Dwarven artillerists and engineers are always in short supply. Therefore, when the Host is on the march, Arbalesters are assigned to crew the army's war engines. This skill allows the character to assemble and dismantle artillery pieces, as well as fire direct-fire type mechanisms like siege crossbows or ballistae. Effectively operating indirect-fire artillery requires the Artillerist skill.",
        per_agi, mapOf(
            FORKS to "Artillerist",
            SKILLTYPE to "Special",
            TOOLS to "Yes",
            RESTRICTIONS to "Dwarves only in character burning"
        )
    ),
    "Astrology" to Descriptions(
        "The study of the stars, the planets and the zodiac—astrology is a deep and ancient science that describes the relations of the celestial sphere to the terrestrial. Astrology may be FoRKed with any skill except those of martial or physical type. However, the Astrology FoRK die is different from other FoRKs: The die is open-ended. But unlike standard open-ended dice, it open-ends both ways. 6s are rerolled as per the normal open-end rules, but 1s are open-ended as well. If a 1 is rolled, reroll the die. If the second roll is a failure, then a success is subtracted from the result. For a less risky roll, use this skill as a linked test toward any endeavor or gambit. In this case do not use the open-ended die.",
        perception, mapOf(
            OBSTACLES to "Naming the stars and constellations, Ob 1.<br/>Constructing a person's horoscope, Ob 2.<br/>Determining the auspices of beginning an enterprise, Ob 3.<br/>Calling upon the stars to answer a question, Ob 4.<br/>Interpreting celestial phenomena or omens, Ob 5.",
            FORKS to "Doctrine, Symbology",
            SKILLTYPE to "Academic",
            TOOLS to "Yes",
            RESTRICTIONS to "Humans Only"
        )
    ),
    "Atilliator" to Descriptions(
        "The manufacture and maintenance of crossbows.",
        per_agi, mapOf(
            OBSTACLES to "Crossbow, Ob2.<br/>Heavy crossbow, Ob 3.<br/>Hand crossbow, Ob 4.<br/>Siege crossbow, Ob 5",
            FORKS to "Bowyer, Blacksmith, Carpenter",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop"
        )
    ),
    "Aura Reading" to Descriptions(
        "This is the talent to read an aura and see within it the present, future and past. Aura readers can decipher gossamer veils of emotion, deception, clarity and purpose. This skill does not grant the character the ability to see auras. That ability must be acquired through a trait, prayer or spell.",
        will_per, mapOf(
            OBSTACLES to "Detecting if the subject is alive, dead, from this plane or another, Ob 1.<br/>Reading an aura trait (like Aura of Fear), Ob 1.<br/>Reading mood (angry, calm, etc.), Ob 2.<br/>Seeing a character trait, Ob 3.<br/>Seeing a die or call-on trait, Ob 4.<br/>Sensing a person's intent (to deceive, for bascomC), Ob 4.<br/>Seeing an Instinct, Ob 6.<br/>Seeing a Belief, Ob 7.<br/>Seeing a character's past, Ob 8.<br/>Seeing a character's future, Ob 9.<br/>Reading an object to see if it is magical or mundane, Ob 1.<br/>Reading a school of magic, Ob 2.<br/>Deciphering a facet of a spell or enchantment (impetus, trigger, effect, bredth, duration etc.), Ob 3.<br/>Naming a spell as it is being cast, Ob 6.<br/>Detecting the presence and nature of a spirit, Ob is 10 minus spirit's Strength",
            SKILLTYPE to "Sorcerous",
            TOOLS to "No"
        )
    ),
    "Axe" to Descriptions(
        "This skill teaches the character how to use one- and two-handed axes to chop off arms, legs and heads in battle, The Axe skill can be used to make blade strikes with the weapon as well as strikes with the haft.",
        power_agility, mapOf(
            FORKS to "Brawling, Martial Arts or appropriate melee weapon skills",
            SKILLTYPE to "Martial",
            TOOLS to "An axe"
        )
    ),

    // Section B
    "Back Alley-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Bad End-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Baking" to Descriptions(
        "His bread, flatcakes and pastries make the baker an important fixture in any civilized town.",
        per_agi, mapOf(
            OBSTACLES to "Peasant bread, Ob 1.<br/>Flatcakes, Ob 2.<br/>Nice bread, Ob 2.<br/>Cream-filled buns, Ob 3.<br/>Advanced Pastry Mechanics, Ob 4.",
            FORKS to "Cooking",
            SKILLTYPE to "Peasant",
            TOOLS to "Workshop"
        )
    ),
    "Ballad of History" to Descriptions(
        "Elves learn their history and that of many others in mnemonic songs.",
        perception, mapOf(
            OBSTACLES to "Events of the current age, Ob 1.<br/>of the Third Age, Ob 2.<br/>of the Second Age, Ob 3.<br/>of the First Age, Ob 4.<br/>of the Years of the Sun, Ob 5.<br/>of the Creation and before time, Ob 6.",
            FORKS to "Any appropriate history or Wise skill or song",
            SKILLTYPE to "Academic",
            TOOLS to "No",
            RESTRICTIONS to "Elves only"
        )
    ),
    "Banner-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Bannerman-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Bat-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Bastions of Hatred" to Descriptions(
        "Channeling his mind-numbing rage, the GateForger is able to create terrifying edifices. This skill Blends Architect and Engineer for the purpose of creating the looming tower fortresses in which the orcs dwell. Also, using this skill, Orcs may corrupt or adopt caves or pre-existing towers and turn them into something more suitable to the horde's taste. All tests are open-ended.",
        "Hatred", mapOf(
            OBSTACLES to "Trench, Ob 1.<br/>Battlefield fortifications, Ob 2.<br/>Palisade wall, Ob 3.<br/>Smalltower, Ob 4.<br/>Convert Mannish structures to something more Orc-like, Ob 1.<br/>Convert Dwarven structures, Ob 2.<br/>Defile Elven structures, Ob 3.",
            FORKS to "Siege Engineer",
            TOOLS to "Workshop",
            RESTRICTIONS to "Orcs only"
        )
    ),
    "Beast of Burden-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Beer Appraisal" to Descriptions(
        "Years of training are required to fine-tune the sensitive taste of a discerning Dwarf. But once honed, said taste can determine the type of grain used, the type and length of the brewing, howlong it has been in the cask and even the wood of the barrel.",
        will_per, mapOf(
            OBSTACLES to "Dwarven brews, Ob 1.<br/>Mannish swill, Ob 2.<br/>Rat wine, Ob 3.<br/>Elven crap, Ob 4.<br/>Orcish puke, Ob 5.",
            FORKS to "Grain Appraisal, Nogger",
            SKILLTYPE to "Craftsman",
            TOOLS to "No",
            RESTRICTIONS to "Dwarves only in character burning"
        )
    ),
    "Beer-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Begging" to Descriptions(
        "Begging can elicit a few coins from the victim (1D of cash). It may also be used to replenish taxed Resources.",
        will, mapOf(
            OBSTACLES to "Typically, obstacles are equal to the victim's Will. Award advantage dice to a convincing beggar.",
            FORKS to "Persuasion, Falsehood, Soothing Platitudes.",
            SKILLTYPE to "Social",
            TOOLS to "No"
        )
    ),
    "Bird Husbandry" to Descriptions(
        "Bird Husbandry is the art of raising and caring for our avian friends.",
        will, mapOf(
            OBSTACLES to "Chickens, Ob 1.<br/>Pigeons, Ob 2.<br/>Raptors, Ob3.<br/>Exotic birdies, Ob 4.<br/>Tending to wounds and illness, use the Field Dressing or Apothecary skill obstacles.",
            SKILLTYPE to "Forester",
            TOOLS to "Yes"
        )
    ),
    "Black Legion-wise" to Descriptions(
        "Orcs with this skill know the power structure and inner workings of their clan's legion and other legions in the region.",
        perception, mapOf(
            FORKS to "Orc-wise",
            SKILLTYPE to "Wise",
            TOOLS to "Yes"
        )
    ),
    "Black Metal Artifice" to Descriptions(
        "This is the skill with which Dwarves create all their mundane and household metals. However, the Artificers know a secret or two and may imbue even their pots and horseshoes with intense quality. Using the obstacles listed below, a Dwarf can create various tool kits. If he so desires, he may increase his obstacles by +2 Ob and grant the final product a special +1D bonus to all skill tests in which the tools are used.",
        will_agility, mapOf(
            OBSTACLES to "Horseshoes, Ob 1.<br/>Mason, Stone Art, Cooking, Blacksmith, Black Metal Art tools: Ob 2.<br/>Armorer, Weaponsmith, War Art tools: Ob 3.<br/>Khirurgeon's tools, Ob 4.",
            FORKS to "White Metal Artifice, War Art",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop",
            RESTRICTIONS to "Dwarves only"
        )
    ),
    "Blacksmith" to Descriptions(
        "A blacksmith forges iron and steel tools, implements and weapons. This is an arduous and time-consuming task, requiring tools and a workshop. Blacksmith can also be used to replenish taxed Resources if the character can find a suitable place to ply his trade.",
        power_agility, mapOf(
            OBSTACLES to "Nails, Ob 1.<br/>Horseshoes, knives, stirrups, arrowheads: Ob 2.<br/>Spear tips, plow: Ob 3.<br/>Simple tools (Cooking, Blacksmith), Ob 3.<br/>Swords, Ob 4.<br/>Complex tools (Surgery, Astrology), Ob 5.",
            FORKS to "Armorer, Weaponsmith",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop"
        )
    ),
    "Bloodletting" to Descriptions(
        "Bloodletters believe that when a person is ill, his blood is contaminated with disease and impurities. By draining blood from the system, the bloodletter seeks to purify him.",
        perception, mapOf(
            OBSTACLES to "See the Anatomy of Injury chapter",
            FORKS to "Apothecary, Surgery",
            SKILLTYPE to "Medicinal",
            TOOLS to "Yes"
        )
    ),
    "Boatwright" to Descriptions(
        "This skill allows the character to construct small watercraft, including canoes, dugouts, barges and planked and tarred longboats.",
        per_agi, mapOf(
            OBSTACLES to "Repair or retarring, Ob 1.<br/>Dugout, skiff or raft, Ob 2.<br/>Rowboat or small barge, Ob 3.<br/>Large barge or longboat, Ob 4.<br/>Small sailing vessel, Ob 5.",
            FORKS to "Carpenter",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop"
        )
    ),
    "Boss-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Bow" to Descriptions(
        "The bow is a simple, effective, tension-drawn weapon that launches thin shafts at high velocity. This skill allows characters to use said device as a weapon in battle.",
        agility, mapOf(
            OBSTACLES to "Rules for hitting targets with bows are detailed in the Range and Cover and Fight chapters.",
            SKILLTYPE to "Martial",
            TOOLS to "A bow."
        )
    ),
    "Bowcraft" to Descriptions(
        "An Elven bowman learns more than just how to fell an opponent with a shaft. For years he trains to discover the very heart and soul of the bow. When he joins the ranks of his fellow protectors, the Bowyer comes forth with his own personally crafted weapon--a product of a decade of learning and labor. This skill counts as Bowyer and Fletcher.",
        will_agility, mapOf(
            OBSTACLES to "Hunting bow, Ob 2.<br/>Great bow, Ob 3.<br/>Horse bow, Ob 4.<br/>Elven Bow, Ob 6.<br/>Arrows and flights, Ob 2.",
            FORKS to "Woodcraft",
            SKILLTYPE to "Craftsman",
            TOOLS to "Yes. Expendable.",
            RESTRICTIONS to "Elves only"
        )
    ),
    "Bowyer" to Descriptions(
        "A Bowyer creates bows and arrows from suitable wood. He also knows how to make bowstrings from gut.",
        per_agi, mapOf(
            OBSTACLES to "Making arrows, Ob 1.<br/>Making a hunting bow, Ob 2.<br/>Making longbow or great bow, Ob 3.",
            FORKS to "Carpenter, Carving",
            SKILLTYPE to "Craftsman",
            TOOLS to "Yes. Expendable."
        )
    ),
    "Boxing (Martial Arts)" to Descriptions(
        "This is the blanket term used to describe all trained, unarmed 'martial arts.' Boxing and Martial Arts skills allow a character to use all attack, defense, basic and special actions. Use the skill in place of Power for Push and Lock.",
        power_agility, mapOf(
            OBSTACLES to "The obstacles for Boxing are described in the Fight and Weapons chapters.",
            FORKS to "Brawling or an appropriate melee weapon skill.",
            SKILLTYPE to "Martial",
            TOOLS to "No."
        )
    ),
    "Brawling" to Descriptions(
        "Brawling is the 'undisciplined' side of bare-fisted combat. Brawling grants access to attack, defense and basic actions, but not special actions. Using the Brawling skill in place of Power for the Lock and Push actions. Brawlers may also use 'found weapons.' Found weapons include: clubs, brooms, lamps, rocks, sharp pieves of glass, towels and pencils. These count as melee weapons but rely on the Brawling skill rather than a weapons skill.",
        power, mapOf(
            OBSTACLES to "The obstacles for Brawling are described in the Fight and Weapons chapters.",
            FORKS to "Boxing, or melee weapon skill.",
            SKILLTYPE to "Martial",
            TOOLS to "No."
        )
    ),
    "Brazen Horn of Despair" to Descriptions(
        "Cavernous horns are sounded before the Orcs join battle. The horns strike one note: Despair. If the Despair Shouters exceed the mean Will of their opponents, their margin of success is added to their opponents' hesitation for the duration of the conflict. Brazen Horn tests are open-ended.",
        "Hatred", mapOf(
            SKILLTYPE to "Musical",
            TOOLS to "Yes, the brazen horn!",
            RESTRICTIONS to "Orcs only"
        )
    ),
    "Brewer" to Descriptions(
        "A Brewer is a specialized miller who grinds grain to ferment into alcohol. Obviously this practice requires a mill (or at least some ground grain) and a vat, if not an actual brewery.",
        perception, mapOf(
            OBSTACLES to "Peasant brew, Ob 1.<br/>Priest's brew, Ob 2.<br/>Merchant's brew, Ob 3.<br/>Nobles' brew, Ob 4.<br/>Amount: for home, no penalty; for a small hostel, +1 Ob; for a tavern or pub, +2 Ob; for a brewery, +3 Ob.",
            FORKS to "Miller",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop"
        )
    ),
    "Bribe-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Brutal Intimidation" to Descriptions(
        "The most cunning and brutal of Orcs learn how to channel their own inner fires to cow their brethren. Brutal Intimidation is a special Intimidation skill, rooted from Hatred. All tests are open-ended.",
        "Hatred", mapOf(
            FORKS to "Torture (when applicable), Interrogation",
            SKILLTYPE to "Social",
            TOOLS to "No.",
            RESTRICTIONS to "Orcs only"
        )
    ),
    "Burden of the Crown-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Bureaucracy" to Descriptions(
        "Bureaucracy is the knowledge of the hierarchies within a codified government of officials (or bureaux). This skill allows characters to navigate said strcutures in order to accomplish certain tasks or impel the bureaucracy to move in a certain direction. Often it involves filling out lots of forms, researching obscure laws and paying bribes to people who are very bored with their jobs.",
        will, mapOf(
            OBSTACLES to "Locating a bureau or office, Ob 1.<br/>Filing forms, Ob 3.<br/>Forming a committee, Ob 4.<br/>Finding a coherent policy, Ob 5.<br/>Locating a mid-level bureaucrat, Ob 6.<br/>Obtaining useful information in a timely manner, Ob 7.<br/>Finding Lord Julius, Ob 8.",
            FORKS to "Research, Etiquette, Soothing Platitudes, Falsehood",
            SKILLTYPE to "Social",
            TOOLS to "No."
        )
    ),
    "Butchery" to Descriptions(
        "Butchers know how to kill, cut and carve an animal or carcass into edible portions.",
        per_agi, mapOf(
            OBSTACLES to "Fowl, small game: Ob 1.<br/>Pig, lamb, goat, Ob 2.<br/>Cow, deer, horse, Ob 3.<br/>Exotic animal, Ob 4<br/>Monster, Ob 5.<br/>Human, Ob 6.",
            SKILLTYPE to "Peasant",
            TOOLS to "Yes."
        )
    ),

    // Section C
    "Cadence-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Call of the Wild" to Descriptions(
        "Call of the Wild obeys the same rules as the Hunting skill.",
        per_agi, mapOf(
            FORKS to "Song of Paths and Ways, Rhyme of Rules",
            SKILLTYPE to "Peasant",
            TOOLS to "Yes.",
            RESTRICTIONS to "Elves only"
        )
    ),
    "Calligraphy" to Descriptions(
        "Calligraphy is a formal, stylized handwriting that is required for communicating with religious, royal, or governmental bodies.",
        per_agi, mapOf(
            OBSTACLES to "Signature or identifying mark, Ob 1.<br/>Formal letter, Ob 2.<br/>Sacred texts, Ob 3.<br/>Imitating writing style, Ob 4.<br/>Imitating sacred style, Ob 5.",
            FORKS to "Write",
            SKILLTYPE to "Academic",
            TOOLS to "Yes."
        )
    ),
    "Campain-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Cargo-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Carpentry" to Descriptions(
        "A carpenter can select suitable trees, cut the lumber and use it to build structures, implements and furniture. Carpentry is a great way to replenish taxed Resources.",
        per_agi, mapOf(
            OBSTACLES to "Simple box or shelf, Ob 1.<br/>Cup, bowl, stool, ladder: Ob 2.<br/> Wall, table, chair, cabinets: Ob 3.<br/>A slatted floor, Ob 4.<br/>A wooden staircase, Ob 5.",
            FORKS to "Engineer, Carving",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop"
        )
    ),
    "Cartography" to Descriptions(
        "Cartography is the school of map-making. This skill imparts a hand for illustration and an eye for detail. And since Cartography is somewhat a language of its own, this skill also allows the interpretation of other maps.",
        per_agi, mapOf(
            OBSTACLES to "Simple local, rural area maps, Ob 1.<br/>Detailed rural area maps, Ob 2.<br/>Simple urban maps, Ob 3.<br/>Detailed urban maps, Ob 4.<br/>Simple sea charts, Ob 5.<br/>Detailed sea charts, Ob 6.<br/>Topographical maps, Ob 7.",
            FORKS to "Calligraphy, Illumination",
            SKILLTYPE to "Academic",
            TOOLS to "Yes. Expendable."
        )
    ),
    "Cartwright" to Descriptions(
        "Cartwrights build wheeled conveyances to be hauled by animals.",
        per_agi, mapOf(
            OBSTACLES to "Wheelbarrow or pushcart, Ob 1.<br/>Rickshaw or two-wheeled cart, Ob 2.<br/>Four-wheeled wagon, Ob 3.<br/>Jitney, Ob 4.<br/>Carriage, Ob 5.",
            FORKS to "Carpentry",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop"
        )
    ),
    "Carving" to Descriptions(
        "Carving is the careful shaping and engraving of wood.",
        per_agi, mapOf(
            OBSTACLES to "Simple carving or shape, Ob 1.<br/>Woodblocks with pictograms, Ob 2.<br/>Complex carving or shape, Ob 3.",
            FORKS to "Carpentry, Etching, Engraving",
            SKILLTYPE to "Craftsman",
            TOOLS to "Traveling Gear"
        )
    ),
    "Cave-In-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Cave-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Champion-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Chandler" to Descriptions(
        "The Chandler skill allows characters to make candles from wax and tallow.",
        per_agi, mapOf(
            OBSTACLES to "Single use candle from fat, Ob 1.<br/>Single use candle from wax, Ob 2.<br/>Multi-use candle, Ob 3.<br/>Candle of hours, Ob 4.<br/>Pope candles, Ob 5.",
            SKILLTYPE to "Craftsman",
            TOOLS to "Yes. Expendable."
        )
    ),
    "Chattel-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Cheating-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Child-Rearing" to Descriptions(
        "Child-Rearing is used to raise children with the proper values and manners. The skill can also be used as Instruction, Cooking and Field Dressing, but only for the benefit of children.",
        will, mapOf(
            OBSTACLES to "Will of the child or as appropriate skill.",
            SKILLTYPE to "Social",
            TOOLS to "No."
        )
    ),
    "Chronology of Kings" to Descriptions(
        "A ballad of history, telling the story of all the great Dwarven kings, their deeds and their riches.",
        perception, mapOf(
            OBSTACLES to "Your clan's noble lineage, Ob 1.<br/>Your current king or prince, Ob 2.<br/>Princes and kings of old, Ob 3.<br/>Original kings, Ob 4.<br/>Oathbreaking kings and princes, Ob 5.<br/>Cowardly princes and kings, Ob 6.<br/>Adventurer princes and kings, Ob 7.",
            FORKS to "Ancient and Obscure History",
            SKILLTYPE to "Academic",
            TOOLS to "No."
        )
    ),
    "Church Law" to Descriptions(
        "This is a specialized legal skill dealing only with ecclesiastical law. Church Law is quite different than civil or courtly law. The punishments for crimes are religious in nature, not monetary or corporal.",
        perception, mapOf(
            OBSTACLES to "Jurisdiction, Ob 1.<br/>Sentencing, Ob 2.<br/>Calling a council or issuing edict or bull, Ob 3.<br/>System of tithe, Ob 4.<br/>Interpreting scripture, Ob 5.",
            SKILLTYPE to "School of Thought",
            TOOLS to "No."
        )
    ),
    "Citadel-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "City-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Clan History" to Descriptions(
        "This Dwarf knows the long and sordid past of the varous clans--their oaths, grudges, great works of art and the probably size of their hoards and halls.",
        perception, mapOf(
            OBSTACLES to "General history and well-known names, Ob 1.<br/>Grudges, Ob 2.<br/>Oaths, Ob 3.<br/>Obscure deeds or names, Ob 4.",
            SKILLTYPE to "Academic",
            TOOLS to "No.",
            RESTRICTIONS to "Dwarves only in character burning"
        )
    ),
    "Clan-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Climbing" to Descriptions(
        "This skill allows the character to navigate sheer surfaces using rope, harnesses and really string finger muscles. In addition, rougher surfaces can be scaled with bare hands.",
        speed, mapOf(
            OBSTACLES to "Easy climb (a rocky hill, a tree or a fence), Ob 1.<br/>Moderate climb (inclined rock wall, a treacherous tree), Ob 2.<br/>Difficult climb (straight rock wall), Ob 3.<br/>Dangerous climb (sheer rock wall), Ob 4.<br/>Impossible climb (ice climbing), Ob 5.<br/>Suicidal climb (bad conditions, overhangs, ice), Ob 7.",
            FORKS to "Knots, Rigging",
            SKILLTYPE to "Physical",
            TOOLS to "Yes. Expendable"
        )
    ),
    "Cloth Dyeing" to Descriptions(
        "The fine art of dyeing cloth and making the color keep.",
        per_agi, mapOf(
            OBSTACLES to "Red, orange, yellow, green, brown, gray: Ob 1.<br/>Blue and purple, Ob 2.<br/>Crimson and scarlet, Ob 3.<br/>Black, Ob 4.<br/>Royal purple, Ob 5.",
            FORKS to "Herbalism, Vintner",
            SKILLTYPE to "Craftsman",
            TOOLS to "Yes"
        )
    ),
    "Coarse Persuasion" to Descriptions(
        "This bizarre social art is apparently a method of coercion and persuasion among Dwarvenkind and consists of a log of backslapping, grinning, and reassurances: 'Come on! You'll love it.' That other folk often find it charming and endearing is one of the wonders of the world.",
        will, mapOf(
            OBSTACLES to "Obstacle is equal to the victim's Will. Obstacle increases for really outlandish requests and proposals.",
            FORKS to "Intimidation, Interrogation, Ugly Truth",
            SKILLTYPE to "Social",
            TOOLS to "No.",
            RESTRICTIONS to "Dwarves only"
        )
    ),
    "Cobbler" to Descriptions(
        "Shoes, glorious shoes. The Cobbler makes all types of foot coverings, from the utilitarian to the fashionable.",
        per_agi, mapOf(
            OBSTACLES to "Sandals or sabots, Ob 1.<br/>Shoes, Ob 2.<br/>Boots or fashionable shoes, Ob 3.<br/>Extravagant courtly fashion, Ob 4.",
            SKILLTYPE to "Craftsman",
            TOOLS to "Yes. Expendable."
        )
    ),
    "Code of Citadels" to Descriptions(
        "This is a specialized natural magic skill that is similar to Etiquette. The Elves' long years allow them to delve deep into the culture of a place and people. When dealing with any Citadel-born Elf, Code of the Citadels may be used in place of Etiquette.",
        will_per, mapOf(
            SKILLTYPE to "Social",
            TOOLS to "No.",
            RESTRICTIONS to "Elves only"
        )
    ),
    "Command" to Descriptions(
        "Command is the ability to deliver curt and effective orders on the field of battle. A character's 'command radius' only scretches as far as the sound of his voice--a fairly short distance on the battlefield. But to those around them, a commander can be a great boon.",
        will, mapOf(
            OBSTACLES to "Command can be used to negate hesitation of characters in battle. The obstacle is the amount of hesitation. Margin of success is subtracted from all hesitation within the Commanding character's presence. Obviously, the Commanding character can't be hesitating during this. In the Range and Colver mechanics, a Command test vs the highest Will on your team may count as a linked test for the team members' field maneuvers.",
            FORKS to "Oratory, Conspicuous",
            SKILLTYPE to "Social",
            TOOLS to "No."
        )
    ),
    "Composition" to Descriptions(
        "Composition is the descipline of formally arranging one's ideas in written form. Using this skiull, a character may compose lengthy books or pen erudite letters.",
        will_per, mapOf(
            OBSTACLES to "Letter, Ob 1.<br/>Essay, Ob 2.<br/>Editorial, Ob 3.<br/>Book, Ob 4.",
            FORKS to "Write, Research and skills applicable to the content",
            TOOLS to "Yes. Expendable."
        )
    ),

    //Amanda's Section
    "Conspicuous" to Descriptions(
        "Characters with the Conspicuous skill can make themselves noticed above all in a crowd or scene.",
        will, mapOf(
            OBSTACLES to "Attracting attention to oneself in a crowded room, Ob 1.<br/>In a busy shop, Ob 2.<br/>On a busy street, Ob 3.<br/>In a massive, gathered crowd, Ob 4.<br/>In a pitched battle, Ob 5.",
            FORKS to "Command, Oratory",
            SKILLTYPE to "Social",
            TOOLS to "No"
        )
    ),
    "Contract-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Cooking" to Descriptions(
        "Preparing meat, veggies and grains for yummy consumption (or not).",
        perception, mapOf(
            OBSTACLES to "Simple meal for one, Ob 1.<br/>Simple meal for the group, Ob 2.<br/>Whipping up a meal from inadequate ingredients, Ob 3.<br/>Cooking something special or fancy, Ob 4.<br/>Cooking something exotic and important, Ob 5.",
            FORKS to "Herbalism, Apothecary, Baking",
            SKILLTYPE to "Peasant",
            TOOLS to "Traveling Gear"
        )
    ),
    "Cooper" to Descriptions(
        "A Cooper uses iron, wood and wax to make barrels to store wine, grain and other sundries necessary for the survival of the village.",
        per_agi, mapOf(
            OBSTACLES to "Barrel staves, Ob 1.<br/>Barrel hoops, Ob 2.<br/>Barrel lids, Ob 3.<br/>Watertight barrels, Ob 4.",
            FORKS to "Carpenter",
            SKILLTYPE to "Peasant",
            TOOLS to "Workshop"
        )
    ),
    "Coppersmith" to Descriptions(
        "Similar to a Blacksmith or Whitesmith, a Coppersmith specializes in the use of copper.",
        per_agi, mapOf(
            OBSTACLES to "Roof tiles, Ob 1.<br/>Bowls and cups, Ob 2.<br/>Coins, Ob 3.<br/>Cooking tools, Ob 3.",
            FORKS to "Blacksmith, Whitesmith",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop"
        )
    ),
    "Counterfeiting" to Descriptions(
        "Counterfeiting is the time-honored art of illegally duplicating money - either clipping coins or cutting dies to replicate printed material.",
        per_agi, mapOf(
            OBSTACLES to "Coin clipping, Ob 1.<br/>Die stamps, Ob 3.<br/>Coins, Ob 4.<br/>Printed material, Ob 5-10 depending on complexity.",
            FORKS to "Forgery, Whitesmith, Alchemy, Etching",
            SKILLTYPE to "Craftsman",
            TOOLS to "Yes. Expendable."
        )
    ),
    "Countryside-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Crop-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Crossbow" to Descriptions(
        "The crossbow is a mechanical, tension-drawn missile weapon. The most basic crossbow uses a " +
                "hook system to hold the drawn bow string so the weapon can be loaded. More complex " +
                "examples use a stirrup, lever or winch to draw the bow. They are very powerful weapons; " +
                "however, they are slow to load and difficult to manufacture. The Crossbow skill allows the " +
                "character to use this weapon in battle situations. Consult the Range and Cover and Fight " +
                "chapters.",
        agility, mapOf(
            SKILLTYPE to "Martial",
            TOOLS to "Yes, a crossbow"
        )
    ),
    "Crowd-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Cryptography" to Descriptions(
        "Cryptography is the art of creating and deciphering codes.",
        perception, mapOf(
            OBSTACLES to "Cryptography successes set the obstacle to decipher with Research or Cryptography.",
            FORKS to "Symbology, Astrology, Obscure History",
            SKILLTYPE to "Academic",
            TOOLS to "No."
        )
    ),
    "Cudgel" to Descriptions(
        "This skill allows the character to use short, single-handed and long, double-handed blunt implements (aka clubs and staffs) as weapons.",
        agility, mapOf(
            OBSTACLES to "See the Fight chapter.",
            FORKS to "Brawling, Martial Arts, Boxing, or any melee weapon skill",
            SKILLTYPE to "Martial",
            TOOLS to "Yes, a big stick."
        )
    ),
    // Section D
    "Daily Bread-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Dance" to Descriptions(
        "The art of moving the body in time with rhythm in an expressive manner.",
        speed, mapOf(
            OBSTACLES to "Simple folk dance, Ob 1.<br/>Proper, polite dance, Ob 2.<br/>Formal or seductive dance, Ob 3.<br/>Complex or ritualistic dance, Ob 4.<br/>Arcane or cabalistic dance, Ob 5.",
            FORKS to "Acting, Seduction",
            SKILLTYPE to "Physical",
            TOOLS to "No."
        )
    ),
    "Darkened Streets-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Darkness-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Demonology" to Descriptions(
        "Demonology is the study of the history and behavior of demons and devils. This is an academic skill that does not necessarily involve the act of summoning.",
        perception, mapOf(
            OBSTACLES to "Identifying spirit activity as opposed to magic or a prank, Ob 1.<br/>Identifying type of spirit (named, " +
                    "unnamed, lesser, greater, dead, deity, etc.): Ob 2.<br/>Identifying type of summoning (circle, blood " +
                    "calling, binding, unwanted, etc.): Ob 3.<br/>Identifying a spirit’s price, Ob 4.<br/>Identifying a spirit’s " +
                    "powers or abilities, Ob 5.",
            FORKS to "Ancient and Obscure History, Summoning, Doctrine",
            SKILLTYPE to "Academic",
            TOOLS to "No."
        )
    ),
    "Dignity of the Wilderlands" to Descriptions(
        "Dignity of the Wilderlands is an exploration of the culture and ritual of the Elves of the Wilderlands. This skill song may be used in place of Etiquette when dealing with Wilder-born Elves.",
        will_per, mapOf(
            SKILLTYPE to "Social",
            TOOLS to "No.",
            RESTRICTIONS to "Elves only"
        )
    ),
    "Disguise" to Descriptions(
        "Disguise is the art of changing one’s face or appearance to look like another person or ethnicity.",
        per_agi, mapOf(
            OBSTACLES to "Disguise gender, Ob 1.<br/><i>Disguise social class</i>: City dweller, Ob 1; Merchant or priest, Ob 2. " +
                    "Peasant, Ob 3. Noble or wizard, Ob 4.<br/><i>Disguise stock</i>: Man/Orc, Ob 2; Dwarf/Man, Orc/Troll, " +
                    "Orc/Roden, Ob 3; Roden/Man, Elf/Man, Ob 4; Dwarf/Roden/Troll, Ob 5. Elf/Roden/Troll, Ob 6. " +
                    "Orc/Elf, Ob 7. Roden/Wolf, Ob 8. Other/Wolf, Ob 9. Any/Spider, Ob 10.",
            FORKS to "Acting, Theatrics",
            SKILLTYPE to "Special",
            TOOLS to "Yes. Expendable."
        )
    ),
    "Ditch Digging" to Descriptions(
        "The fine and rarefied art of putting holes in the ground.",
        power, mapOf(
            OBSTACLES to "Grave, Ob 1.<br/>Latrine, Ob 2.<br/>Trench, Ob 3.<br/>Mass grave or pit, Ob 4.<br/>Moat, Ob 5.",
            SKILLTYPE to "Peasant",
            TOOLS to "Yes."
        )
    ),
    "Doctrine" to Descriptions(
        "Doctrine describes the tenets and beliefs of a particular religious faith. A character may only take the following variations if they were available on his lifepaths or through general skill points:<br/>" +
                "<i>Cult Doctrine</i> is the application of a belief system to a non-standard, unapproved or independent school of thought.<br/>" +
                "<i>Foreign Doctrine</i> is the knowledge or study of an accepted popular religion from another land or culture.<br/>" +
                "<i>Heretical Doctrine</i> is the understanding and knowledge of a set of beliefs and tenets that are an offshoot of the popular religion but are not commonly accepted by the religious power structure.",
        perception, mapOf(
            OBSTACLES to "Common, popular beliefs, Ob 1.<br/>Citing relevant passages from common religious texts, Ob 2.<br/>Naming an obscure saint, Ob 3.",
            FORKS to "Philosophy",
            SKILLTYPE to "School of Thought",
            TOOLS to "No."
        )
    ),
    "Doctrine of Night's Blood" to Descriptions(
        "The Doctrine of Night’s Blood contains the terrible laws of the Servant’s Cult – whom they serve and why.",
        will_per, mapOf(
            OBSTACLES to "Destruction, Ob 1.<br/>Madness, Ob 2.<br/>The Powers of the Master, Ob 3.<br/>The Void, Ob 4.<br/>Self-Abnegation, Ob 5.",
            FORKS to "Servant-wise",
            SKILLTYPE to "School of Thought",
            TOOLS to "No.",
            RESTRICTIONS to "Orcs only in character burning."
        )
    ),
    "Drama-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Drinking" to Descriptions(
        "Drinking is skill of getting drunk and not showing it.",
        forte, mapOf(
            OBSTACLES to "This skill is best used as a versus test between drinkers.",
            SKILLTYPE to "Special",
            TOOLS to "No."
        )
    ),
    "Driving" to Descriptions(
        "Motivating a team of oxen or horses is an arduous task that requires skill and a certain delicacy. The Driving skill is used for maneuvering carts and carriages over roads.",
        will_agility, mapOf(
            OBSTACLES to "Use this skill in place of Riding when determining pursuit or the celerity of travel. It would also be used in the rare case of field maneuvers involving carts and chariots.",
            SKILLTYPE to "Peasant",
            TOOLS to "No."
        )
    ),
    "Drum Maker" to Descriptions(
        "The Drum Maker skill involves the curing of skins, carving of wood and tuning of hollows for the manufacture of percussion instruments.",
        per_agi, mapOf(
            OBSTACLES to "Small hand drum, Ob 1.<br/>Bhadràn or tabor, Ob 2.<br/>Timbrel or tambourine, Ob 3.<br/>Kettle drum, Ob 4.",
            FORKS to "Carpentry",
            SKILLTYPE to "Craftsman",
            TOOLS to "No."
        )
    ),
    "Drunkard-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Drunking" to Descriptions(
        "Dwarves can really put it away. They don’t try to hide their inebriation, either. They drink to get " +
                "roaring drunk. And they let everyone know it. Use this skill when a Dwarf needs to get drunk and " +
                "loud quickly. The more successes, the faster he gets drunk. This is not meant to imply that he " +
                "needs less alcohol than another, less skilled drinker. On the contrary, he needs more, in bigger " +
                "cups, faster!",
        will_forte, mapOf(
            OBSTACLES to "Make a versus test between you and your friends for getting drunk the fastest. And Drunking vs Perception for appearing sober to your wife, superior, or the guards.",
            FORKS to "Beer-wise",
            SKILLTYPE to "Special",
            TOOLS to "No. (Unless you count beer.)",
            RESTRICTIONS to "Dwarves only"
        )
    ),
    "Dwarf-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Dwarven Art-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Dwarven Heroes-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Dwarven Rune Script" to Descriptions(
        "Dwarven Society is a literate one. Dwarven children are taught to decipher and inscribe runes from a very young age. This skill counts as both Read and Write for Dwarves.",
        perception, mapOf(
            OBSTACLES to "As for Read and Write",
            FORKS to "History skills, Chronology of Kings",
            SKILLTYPE to "Academic",
            TOOLS to "Yes, writing only. Expendable.",
            RESTRICTIONS to "Dwarves only in character burning"
        )
    ),
    "Dye Manufacture" to Descriptions(
        "Dye Manufacture teaches where to find materials and how to convert natural substances into dyes.",
        per_agi, mapOf(
            OBSTACLES to "Common colors, Ob 1.<br/>Exotic colors, Ob 2.<br/>Royal colors, Ob 3.",
            SKILLTYPE to "Craftsman",
            TOOLS to "Yes. Expendable."
        )
    ),
    // Section E
    "Earth-wise" to Descriptions(
        "A Dwarf with this skill can tell the type of earth and stone, where it came from and generally how old it is.",
        perception, mapOf(
            SKILLTYPE to "wise",
            TOOLS to "No"
        )
    ),
    "Elf-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Elven Artifact-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Elven Art-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Elven Blade-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Elven Citadel-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),
    "Elven Ranger-wise" to Descriptions(
        wiseDescription, perception, mapOf()
    ),


    "Falconry" to Descriptions(
        "A falconer trains hunting hawks. Falconry is a very popular and expensive sport among nobility of all stripes.",
        will_per, mapOf(
            OBSTACLES to "Falconry contests can be resolved with a versus test between two falconers. Otherwise use the following:<br/>Capturing small game, Ob 1.<br/>Spotting or signaling, Ob 2.<br/>Dispatching a message, Ob 3.",
            SKILLTYPE to "Forester",
            TOOLS to "Yes and a falcon"
        )
    ),
    "Observation" to Descriptions(
        "Observation is the skill used to spot the hidden--characters, traps, ghosts, etc. Test this skill in versus tests against Inconspicuous, Stealthy, Sleight of Hand or Trapper.<br/>Note: Do not use Observation for standard Perception tests--to spot a weakness in armor, to notice details of a flag or to look for an escape tunnel. Some spells and traits allow Perception to act as Observation.",
        perception, mapOf(
            OBSTACLES to "Observation is almost always a versus test",
            SKILLTYPE to "Forester",
            TOOLS to "No"
        )
    ),
    "Orienterring" to Descriptions(
        "Orienteering is the ability to navigate across land both with and without maps. Maps do, however, provide an advantage die to Orienteering tests. A successful Orienteering test indicates the character has found his way without delay. A failed test indicates the character and his companions have gotten lost. This result either causes the characters to miss any appointments they were trying to keep (but still arrive at their destination) or causes them to arrive in an unintended location.",
        perception, mapOf(
            OBSTACLES to "Determining your compass directions during the day in familiar land, Ob 1.<br/>At night in familiar land, Ob 2.<br/>In unfamiliar land, Ob 3.<br/>In unfamiliar land at night, Ob 4.<br/>In familiar land during a storm, Ob 5.<br/>During a storm at night, Ob 6.",
            FORKS to "Navigation",
            SKILLTYPE to "Forester",
            TOOLS to "No"
        )
    )

)
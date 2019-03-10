package andyr.bascomC.bwcharacter.Utilities

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

val DESCRIPTIONS : Map<String, andyr.bascomC.bwcharacter.Descriptions> = mapOf(
    "Abbey-wise" to andyr.bascomC.bwcharacter.Descriptions(wiseDescription, perception, mapOf()),
    "Accounting" to andyr.bascomC.bwcharacter.Descriptions(
        "Accountants keep track of expenses, income, stocks and taxes. This skill is primarily used to recover taxed Resources",
        perception, mapOf(
            OBSTACLES to "Calculating taxes for a single income, single residence individual, Ob 1.<br/>Maintaining simple personal finances, Ob 2.<br/>Maintaining a family's income, Ob 3.<br/>A small business' books, Ob 4.<br/>An estate's books, Ob 5.<br/>A large business' books, Ob 6.<br/>A town's books, Ob 7.<br/>A city's books, Ob 8.<br/>A province's boks, Ob 9.<br/>A nation's books, Ob 10.",
            FORKS to "Research, Reading, Administration, Estate Management",
            SKILLTYPE to "Academic",
            TOOLS to "Yes"
        )
    ),
    "Acting" to andyr.bascomC.bwcharacter.Descriptions(
        "The skill of imitation and recitation used to effect a performance.",
        perception, mapOf(
            OBSTACLES to "Mummery, Ob 1.<br/>Morality, Ob 2.<br/>Religious miracle, Ob 3.<br/>Religious mystery, Ob 4.<br/>Classics, Ob 5.",
            FORKS to "Conspicuous, Falsehood",
            SKILLTYPE to "Special",
            TOOLS to "No"
        )
    ),
    "Administration" to andyr.bascomC.bwcharacter.Descriptions(
        "Administration is the skill of running a business or organization. This skill is primarily used to recover taxed Resources.",
        perception, mapOf(
            OBSTACLES to "Just you, Ob 1.<br/>A shop, Ob 2.<br/>Confraternity, Ob 3.<br/>Small commune, Ob 4.<br/>Small guild, Ob 5.<br/>Small secret society, Ob 6.<br/>Large secret society, Ob 7.<br/>Small trading concern, Ob 8.<br/>Large guild, Ob 9.<br/>Giant trading concern, Ob 10.",
            SKILLTYPE to "Special",
            TOOLS to "No"
        )
    ),
    "Ages of the Etharch" to andyr.bascomC.bwcharacter.Descriptions(
        "This is a specialized History skill relating to the ages of the Etharchs.",
        will_per, mapOf(
            OBSTACLES to "Etharchs of the current age, Ob 1.<br/>Etharchs of the Third Age, Ob 2.<br/>&nbsp;&nbsp;of the Second Age, Ob 3.<br/>&nbsp;&nbsp;of the First Age, Ob 4.<br/>&nbsp;&nbsp;of the Years of the Sun, Ob 5.<br/>Before the first Etharchs, Ob 6.",
            SKILLTYPE to "Academic",
            TOOLS to "No",
            RESTRICTIONS to "Elves only"
        )
    ),
    "Alchemy" to andyr.bascomC.bwcharacter.Descriptions(
        "Alchemy is the distillation of materials in order to divine their essence. Also, Alchemists can create mixtures of arcane substances to generate a specific effect.",
        perception, mapOf(
            OBSTACLES to "Distilling the essential components of earth, Ob 1.<br/>&nbsp;&nbsp;of water or liquid, Ob 2.<br/>&nbsp;&nbsp;of metal, Ob 3.<br/>&nbsp;&nbsp;of blood, Ob 4.",
            FORKS to "Enchanting, Herbalism, Apothecary, Munitions, Poisons",
            SKILLTYPE to "Sorcerous",
            TOOLS to "Expendable",
            RESTRICTIONS to "Humans only"
        )
    ),
    "Almanac" to andyr.bascomC.bwcharacter.Descriptions(
        "This particular and peculiar skill comprises a deep traditional and historical knowledge of the seasons, tides, weather, lunar phases and other climatic phenomena. A character with the Almanac skill can always tell the time of year, nearly to the day. In addition, he can make accurate predictions about this season's weather.",
        perception, mapOf(
            OBSTACLES to "General seasonal weather, Ob 1.<br/>Planting and harvest times, Ob 2.<br/>Reasonably accurate weather prediction, Ob 3.<br/>Festivals and holidays, Ob 4.",
            SKILLTYPE to "Peasant",
            TOOLS to "No"
        )
    ),
    "Althing-wise" to andyr.bascomC.bwcharacter.Descriptions(wiseDescription, perception, mapOf()),
    "Ambition-wise" to andyr.bascomC.bwcharacter.Descriptions(wiseDescription, perception, mapOf()),
    "Amercement" to andyr.bascomC.bwcharacter.Descriptions(
        "Amercement is the knowledge of fees and criminal fines given as judicial punishment. Such amercements are almost always offered in place of corporal punishment, e.g. \"Ye may take 30 days in the stocks or ye may pay the court a fee.\" Using this skill the character amy set the Resources obstacle for a fine. The skill test obstacles indicate how accurate and just the amount is according to the law.",
        perception, mapOf(
            OBSTACLES to "Low justice (where the punishment is only a fine), Ob 2.<br/>Middle justice (where the punishment would be amputation or branding or the amercement), Ob 3.<br/>High justice (crimes punishable by death--liek murder, adultery, or grand larceny), Ob 5.",
            SKILLTYPE to "School of Thought",
            TOOLS to "No"
        )
    ),
    "Ancient Languages" to andyr.bascomC.bwcharacter.Descriptions(
        "Through this skill, the character knows defunct languages appropriate to the game setting. Use the mechanics for Foreign Languages.",
        perception, mapOf(
            SKILLTYPE to "Academic",
            TOOLS to "No"
        )
    ),
    "Anatomy" to andyr.bascomC.bwcharacter.Descriptions(
        "Anatomy is the study of the human body's internal functions and structure.",
        perception, mapOf(
            OBSTACLES to "Dog anatomy, Ob 1<br/>Superficial human anatomy, Ob 2.<br/>Mayor human organs, Ob 3.<br/>Circulatory system, Ob 4.<br/>Proper human dissection, Ob 5.<br/>",
            FORKS to "Surgery, Field Dressing",
            TOOLS to "No"
        )
    ),
    "Animal Husbandry" to andyr.bascomC.bwcharacter.Descriptions(
        "Animal Husbandry involves raising, care and breeding of animals.",
        will, mapOf(
            OBSTACLES to "Chickens, Ob 1.<br/>Pigs and goats, Ob 2.<br/>Cows and horses, Ob 3.<br/>Exotic animals, Ob 4.<br/>Tending to wounds and illness, use the Field Dressing or Apothecary skill obstacles.",
            SKILLTYPE to "Peasant",
            TOOLS to "Yes"
        )
    ),
    "Antiphon Union Training" to andyr.bascomC.bwcharacter.Descriptions(
        "This specialized training allows the Elven artisan to blend his crafts or arts with spell songs. Any of the items or materials he creates via a skill song can be imbued with the power of a spell song.<br/>An artisan who wishes to enchant an item, must first create it with a skill song like Jewelcraft, Riddle of Steel or Weaving Way. Once the item is created, the spell song must be sung. The singer has one chance to transfer his power. The song is sung according to its rules, but the effect generated is imbued into the item, and active whenever the item is used.<br/>No enchantment will hold unless the structure is of highest quality. Any item created by any skill song can be used. However, whether it's a shoe or a crown, the base obstacle to create the item is 5. If the obstacle would naturally be higher, use that instead. If the item is cheap or shoddy, it simply will not hold the spell.",
        "$will*", mapOf(
            SKILLTYPE to "Artisan",
            TOOLS to "No, but see skill song.",
            RESTRICTIONS to "Elves only",
            "*Training Root is for Aptitude only." to ""
        )
    ),
    "Apothecary" to andyr.bascomC.bwcharacter.Descriptions(
        "Apothecaries use herbs, roots, minerals and animalia to cure and prevent common and chronic ailments. Apothecaries may alleviate the symptoms of infection and illness; they may also alleviate wound die penalties",
        perception, mapOf(
            OBSTACLES to "Stop itching, Ob 2.<br/>Cure warts, Ob 3.<br/>Medicine to alleviate +1 Ob of pain, Ob 3.<br/>Medicine to numb an area, Ob 4: This reduces the wounded die penalty by one but increases all obstacles by one and lasts four hours.",
            FORKS to "Herbalism",
            SKILLTYPE to "Medicinal",
            TOOLS to "Yes. Expendable"
        )
    ),
    "Appraisal" to andyr.bascomC.bwcharacter.Descriptions(
        "This skill si used to judge the value or worth of certain items such as jewelry, gems, artwork and antiquities. The Appraisal skill can be used by a player before testing his Resources to ensure that he is getting an accurate price.",
        perception, mapOf(
            OBSTACLES to "Coin and precious metals, Ob 1.<br/>Gems, Ob 2.<br/>Tapestries, Ob 3.<br/>Armaments, Ob 3.<br/>Antiquities, Ob 4.<br/>Art, Ob 5.<br/>Ephemera--wine, drugs, candles, Ob 6.<br/>If something is magical, add +1 Ob penalty to appraisal.",
            FORKS to "Appropriate wises, academic, artisan or craftsman skills",
            SKILLTYPE to "Special",
            TOOLS to "No"
        )
    ),
    "Appropriate Weapons" to andyr.bascomC.bwcharacter.Descriptions(
        "This blanket entry is provided so the player may choose any and all of the weapons appropriate to his lifepaths, character concept and game setting. See the listing for the individual weapons obstacles and roots.",
        "", mapOf()
    ),
    "Archcraft" to andyr.bascomC.bwcharacter.Descriptions(
        "Archcraft is a comprehensive discipline teaching the principles of both architecture and engineering. Using this brad skill, Elven artisans create halls of sweeping beauty and depth. Combined with Antiphon Union and various spell songs, this skill can be used to create enchanted dwellings for the Elves. The following are but a few examples:<br/>Using Song of Arbors, buildings can be constructed to weave in and out of ancient trees; with Alarm, certain gates will warn of intruders; with Weathersong, an atrium can be built so it predicts the coming weather.",
        will_agility, mapOf(
            OBSTACLES to "Simple arch, Ob 1.<br/>Small gate, Ob 2.<br/>Large gate, Ob 3.<br/>Chamber, Ob 4.<br/>Domed hall, Ob 5.",
            FORKS to "Woodcraft, Stoncraft",
            SKILLTYPE to "Artisan",
            TOOLS to "Workshop",
            RESTRICTIONS to "Elves Only"
        )
    ),
    "Architect" to andyr.bascomC.bwcharacter.Descriptions(
        "Architects possess knowledge of the design and construction of complex structures, such as buildings, arches and bridges. This skill can be used in game to draw accurate plans of a proposed structure, attempt to draw structural plans for an extant building or use existing plans and designs to navigate buildings and structures.",
        perception, mapOf(
            OBSTACLES to "Simple plans like a cottage, Ob 1.<br/>A longhouse, Ob 2.<br/>Two story structure, Ob 4.<br/>Tower, Ob 5.<br/>Complex or weird designs like temples, Ob 6.<br/>Non-Euclidean Geometric Designs of the Outer Gods, Ob 10.",
            FORKS to "Engineer, Mason, Carpenter",
            SKILLTYPE to "Academic",
            TOOLS to "Workshop"
        )
    ),
    "Armor Training" to andyr.bascomC.bwcharacter.Descriptions(
        "To the unaccustomed, armor is heavy, hot and uncomfortable. Characters without Armor Training who wear light mail suffer +1 Ob penalty to all tests. Wearing heavy mail or heavier without Armor Training incurs a +2 Ob penalty. Armor Training mitigates these penalties completely. Clumsy Weight penalties still apply.",
        "$power_speed*", mapOf(
            SKILLTYPE to "Martial Training",
            TOOLS to "No",
            "*Training Root is for Aptitude only" to ""
        )
    ),
    "Armorer" to andyr.bascomC.bwcharacter.Descriptions(
        "This specialized and dedicated craft is used to manufacture personal protection worn by soldiers in battle. The skill’s knowledge base is diverse, encompassing aspects of a blacksmith, a tailor and a tanner.",
        per_agi, mapOf(
            OBSTACLES to "Gambeson, skull cap or leather hood: Ob 1.<br/>Reinforced leather armor, light helmet, pot helm: Ob 2.<br/>Light mail, spangenhelm: Ob 3.<br/>Heavy mail, open-faced bascinet, barbute: Ob 4.<br/>Plated mail, sallet, closed bascinet: Ob 5.<br/>Full plate, great helm: Ob 6.<br/>Target, Ob 1. Buckler, Ob 2. Great Shield, Ob 3. Kite, Ob 4.",
            FORKS to "Blacksmith, Sewing, Tanner",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop"
        )
    ),
    "Army-wise" to andyr.bascomC.bwcharacter.Descriptions(wiseDescription, perception, mapOf()),
    "Arson" to andyr.bascomC.bwcharacter.Descriptions(
        "This skill allows the character to use common household items to quickly set a building on fire.",
        per_agi, mapOf(
            OBSTACLES to "Thatched cottage, Ob 1.<br/>Munitions dump or powder room, Ob 2.<br/>House, Ob 3.<br/>Manor, Ob 4.<br/>Fortress, Ob 5.<br/>Prison, Ob 6.<br/>Catacombs, Ob 7.<br/>Castle, Ob 8.",
            SKILLTYPE to "Special",
            TOOLS to "Yes. Expendable",
            RESTRICTIONS to "Men and Roden only in character burning"
        )
    ),
    "Artificer-wise" to andyr.bascomC.bwcharacter.Descriptions(wiseDescription, perception, mapOf()),
    "Artillerist" to andyr.bascomC.bwcharacter.Descriptions(
        "Artillerists use basic physics and mathematics topped with some healthy guesswork to lob indirect-fire projectiles from war machines. They also know the designs for constructing various war engines. Actual construction requires teams of laborers and at least one carpenter.",
        perception, mapOf(
            OBSTACLES to "Carving shot from stone: Ob 1.<br/>Scavenging suitable shot, Ob 2.<br/>Building a small traction trebuchet: Ob 2.<br/>Building a large traction trebuchet, Ob 3.<br/>Building a small catapult, Ob 4.<br/>Building a counterweight trebuchet or siege crossbow, Ob 5.<br/>Building a large catapult, Ob 6.<br/>Ranging shots (aka, hitting the broadside of a barn): Siege crossbow, Ob 2: Small catapult or traction trebuchet, Ob 3: Counterweight trebuchet, Ob 3: Catapult, Ob 4.",
            FORKS to "Engineer, Architect",
            SKILLTYPE to "Military",
            TOOLS to "Workshop"
        )
    ),
    "Artillery Hand" to andyr.bascomC.bwcharacter.Descriptions(
        "Dwarven artillerists and engineers are always in short supply. Therefore, when the Host is on the march, Arbalesters are assigned to crew the army's war engines. This skill allows the character to assemble and dismantle artillery pieces, as well as fire direct-fire type mechanisms like siege crossbows or ballistae. Effectively operating indirect-fire artillery requires the Artillerist skill.",
        per_agi, mapOf(
            FORKS to "Artillerist",
            SKILLTYPE to "Special",
            TOOLS to "Yes",
            RESTRICTIONS to "Dwarves only in character burning"
        )
    ),
    "Astrology" to andyr.bascomC.bwcharacter.Descriptions(
        "The study of the stars, the planets and the zodiac—astrology is a deep and ancient science that describes the relations of the celestial sphere to the terrestrial. Astrology may be FoRKed with any skill except those of martial or physical type. However, the Astrology FoRK die is different from other FoRKs: The die is open-ended. But unlike standard open-ended dice, it open-ends both ways. 6s are rerolled as per the normal open-end rules, but 1s are open-ended as well. If a 1 is rolled, reroll the die. If the second roll is a failure, then a success is subtracted from the result. For a less risky roll, use this skill as a linked test toward any endeavor or gambit. In this case do not use the open-ended die.",
        perception, mapOf(
            OBSTACLES to "Naming the stars and constellations, Ob 1.<br/>Constructing a person's horoscope, Ob 2.<br/>Determining the auspices of beginning an enterprise, Ob 3.<br/>Calling upon the stars to answer a question, Ob 4.<br/>Interpreting celestial phenomena or omens, Ob 5.",
            FORKS to "Doctrine, Symbology",
            SKILLTYPE to "Academic",
            TOOLS to "Yes",
            RESTRICTIONS to "Humans Only"
        )
    ),
    "Atilliator" to andyr.bascomC.bwcharacter.Descriptions(
        "The manufacture and maintenance of crossbows.",
        per_agi, mapOf(
            OBSTACLES to "Crossbow, Ob2.<br/>Heavy crossbow, Ob 3.<br/>Hand crossbow, Ob 4.<br/>Siege crossbow, Ob 5",
            FORKS to "Bowyer, Blacksmith, Carpenter",
            SKILLTYPE to "Craftsman",
            TOOLS to "Workshop"
        )
    ),
    "Aura Reading" to andyr.bascomC.bwcharacter.Descriptions(
        "This is the talent to read an aura and see within it the present, future and past. Aura readers can decipher gossamer veils of emotion, deception, clarity and purpose. This skill does not grant the character the ability to see auras. That ability must be acquired through a trait, prayer or spell.",
        will_per, mapOf(
            OBSTACLES to "Detecting if the subject is alive, dead, from this plane or another, Ob 1.<br/>Reading an aura trait (like Aura of Fear), Ob 1.<br/>Reading mood (angry, calm, etc.), Ob 2.<br/>Seeing a character trait, Ob 3.<br/>Seeing a die or call-on trait, Ob 4.<br/>Sensing a person's intent (to deceive, for bascomC), Ob 4.<br/>Seeing an Instinct, Ob 6.<br/>Seeing a Belief, Ob 7.<br/>Seeing a character's past, Ob 8.<br/>Seeing a character's future, Ob 9.<br/>Reading an object to see if it is magical or mundane, Ob 1.<br/>Reading a school of magic, Ob 2.<br/>Deciphering a facet of a spell or enchantment (impetus, trigger, effect, bredth, duration etc.), Ob 3.<br/>Naming a spell as it is being cast, Ob 6.<br/>Detecting the presence and nature of a spirit, Ob is 10 minus spirit's Strength",
            SKILLTYPE to "Sorcerous",
            TOOLS to "No"
        )
    ),
    "Axe" to andyr.bascomC.bwcharacter.Descriptions(
        "This skill teaches the character how to use one- and two-handed axes to chop off arms, legs and heads in battle, The Axe skill can be used to make blade strikes with the weapon as well as strikes with the haft.",
        power_agility, mapOf(
            FORKS to "Brawling, Martial Arts or appropriate melee weapon skills",
            SKILLTYPE to "Martial",
            TOOLS to "An axe"
        )
    )
)
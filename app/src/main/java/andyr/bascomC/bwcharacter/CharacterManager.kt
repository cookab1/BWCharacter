package andyr.bascomC.bwcharacter

import android.content.SharedPreferences
import com.google.gson.Gson

class CharacterManager private constructor() {
    private var character : andyr.bascomC.bwcharacter.Character = andyr.bascomC.bwcharacter.Character()
    private lateinit var mPrefs : SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor
    
    private object Holder { val INSTANCE = andyr.bascomC.bwcharacter.CharacterManager() }

    companion object {
        val instance: andyr.bascomC.bwcharacter.CharacterManager by lazy { andyr.bascomC.bwcharacter.CharacterManager.Holder.INSTANCE }
    }

    fun getCharacter() : andyr.bascomC.bwcharacter.Character {
        return character
    }

    fun setCharacter(sentCharacter: andyr.bascomC.bwcharacter.Character) {
        character = sentCharacter
    }

    fun getPrefs(): SharedPreferences {
        return mPrefs
    }

    fun setPrefs(prefs: SharedPreferences) {
        mPrefs = prefs
    }

    fun setPrefsEditor() {
        prefsEditor = mPrefs.edit()
    }

    fun saveCharacter() : andyr.bascomC.bwcharacter.Character {
        val gson = Gson()
        val json = gson.toJson(character)
        prefsEditor.putString("Character", json)
        prefsEditor.commit()
        return character
    }
}
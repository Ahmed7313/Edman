package com.aramex.mypos.Data.Preference

import com.daman.edman.data.remote.DTO.OTPResponse.User

interface Preferences {

    fun putToken(token: String)

    fun getToken(): String

    fun clearPreferences()

    fun putLanguage(language: String)

    fun getLanguage(): String

    fun getAuthLogin(): String

    fun putGrantType(granType: String)

    fun getGrantType(): String

    fun putScope (scope : String)

    fun getScope (): String

    fun putUserId (userId: Int)

    fun getUserId() : Int

    fun putUser(user: User)

    fun getUser(): User

    fun putTokenExpirationTime(time: Long)

    fun putTokenType(tokenType: String)

    fun getTokenExpirationTime(): Long

    fun getTokenType(): String

    fun deleteTokenInfo()

    fun putSignedInState(isSignedIn: Boolean)

    fun getSignedInState(): Boolean

    fun putFireBaseToken(token: String)

    fun getFireBaseToken(): String

    fun putLanguageChosen(chosed: Boolean)

    fun getLanguageChosen(): Boolean

    fun putUserLocationId(locationId: Int)

    fun getUserLocationId(): Int

}
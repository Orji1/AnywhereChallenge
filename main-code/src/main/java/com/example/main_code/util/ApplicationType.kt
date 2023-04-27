package com.example.main_code.util

sealed class ApplicationType(
    val endPoint: String
) : java.io.Serializable {
    data class SIMPSONS(val endPointS: String = "simpsons characters") : ApplicationType(endPointS)
    data class THEWIRE(val endPointW: String = "the wire characters") : ApplicationType(endPointW)
}

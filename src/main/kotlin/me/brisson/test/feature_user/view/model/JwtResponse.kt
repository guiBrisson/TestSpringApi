package me.brisson.test.feature_user.view.model

data class JwtResponse(
    val token: String,
    val type: String = "Bearer",
    val id: String,
    val username: String,
    val email: String,
//    val roles: List<String>
)

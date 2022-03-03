package me.brisson.test.feature_user.shared

import me.brisson.test.feature_user.model.Role
import me.brisson.test.feature_user.model.User

data class UserDto(
    val id: String,
    val email: String,
    val username: String,
    val password: String,
//    val role: Role
) {
    constructor(user: User) : this(
        id = user.id,
        email = user.email,
        username = user.username,
        password = user.password,
//        role = user.role
    )
}

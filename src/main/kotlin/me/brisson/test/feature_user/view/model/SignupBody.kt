package me.brisson.test.feature_user.view.model

import me.brisson.test.feature_user.model.ERole
import me.brisson.test.feature_user.model.Role
import org.bson.types.ObjectId

data class SignupBody(
    val id: String = ObjectId.get().toString(),
    val username: String,
    val email: String,
    val password: String,
//    val roles: Role = Role(ObjectId.get().toString(), "", ERole.ROLE_USER)
)
package me.brisson.test.feature_user.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
data class User(
    @Id
    val id: String,
    val email: String,
    val username: String,
    val password: String,
//    val role: Role
)

package me.brisson.test.feature_user.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("role")
data class Role(
    @Id
    val id: String,
    val name: String,
    val role: ERole
)

enum class ERole {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN
}



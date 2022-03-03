package me.brisson.test.feature_user.repository

import me.brisson.test.feature_user.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String> {

    fun findByUsername(username: String) : Optional<User>

    fun existsByUsername(username: String) : Boolean

    fun existsByEmail(email: String) : Boolean
}
package me.brisson.test.feature_user.repository

import me.brisson.test.feature_user.model.ERole
import me.brisson.test.feature_user.model.Role
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : MongoRepository<Role, String> {

    fun findByName(name: ERole): Optional<Role>
}
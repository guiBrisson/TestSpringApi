package me.brisson.test.feature_tv.repository

import me.brisson.test.feature_tv.model.Television
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TelevisionRepository : MongoRepository<Television, String>
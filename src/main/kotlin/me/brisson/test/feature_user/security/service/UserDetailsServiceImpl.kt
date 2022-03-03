package me.brisson.test.feature_user.security.service

import me.brisson.test.feature_user.model.User
import me.brisson.test.feature_user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserDetailsServiceImpl(
    private val repo: UserRepository
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = repo.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User not found with username: $username") }
        return UserDetailsImpl(user)
    }
}
package me.brisson.test.feature_user.security.service

import com.fasterxml.jackson.annotation.JsonIgnore
import me.brisson.test.feature_user.model.ERole
import me.brisson.test.feature_user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class UserDetailsImpl() : UserDetails {

//    private val serialVersionUID = 1L
    private var id: String = ""
    private var username: String = ""
    private var email: String = ""
    @JsonIgnore
    private var password: String = ""

    private val authorities = ArrayList<SimpleGrantedAuthority>().apply {
        this.add(SimpleGrantedAuthority(ERole.ROLE_USER.name))
    }

    constructor(user: User) : this() {
        this.id = user.id
        this.username = user.username
        this.email = user.email
        this.password = user.password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities

    fun getId(): String = id

    fun getEmail(): String = email

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other == null || javaClass != other.javaClass) return false
//        val user = other as UserDetailsImpl
//        return Objects.equals(id, user.id)
//    }
}


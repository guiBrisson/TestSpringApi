package me.brisson.test.feature_user.view.controller

import me.brisson.test.feature_user.model.User
import me.brisson.test.feature_user.repository.UserRepository
import me.brisson.test.feature_user.security.jwt.JwtUtils
import me.brisson.test.feature_user.security.service.UserDetailsImpl
import me.brisson.test.feature_user.view.model.JwtResponse
import me.brisson.test.feature_user.view.model.LoginBody
import me.brisson.test.feature_user.view.model.MessageResponse
import me.brisson.test.feature_user.view.model.SignupBody
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userRepo: UserRepository,
//    private val roleRepo: RoleRepository,
    private val encoder: PasswordEncoder,
    private val jwtUtils: JwtUtils
) {

    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginBody: LoginBody): ResponseEntity<JwtResponse> {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginBody.username, loginBody.password)
        val authentication: Authentication = authenticationManager.authenticate(authenticationToken).apply {
            SecurityContextHolder.getContext().authentication = this
        }
        val jwt = jwtUtils.generateJwtToken(authentication)
        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities.stream()
            .map { item: GrantedAuthority? -> item!!.authority }
            .collect(Collectors.toList())

        return ResponseEntity.ok(
            JwtResponse(
                token = jwt,
                id = userDetails.getId(),
                username = userDetails.username,
                email = userDetails.getEmail(),
//                roles = roles
            )
        )
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signupBody: SignupBody): ResponseEntity<MessageResponse> {
        if (userRepo.existsByUsername(signupBody.username)) {
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Username is already taken!"))
        }

        if (userRepo.existsByEmail(signupBody.email)) {
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Email is already in use!"))
        }

        val user = User(
            id = ObjectId.get().toString(),
            email = signupBody.email,
            username = signupBody.username,
            password = encoder.encode(signupBody.password)
        )
        userRepo.save(user)
        return ResponseEntity.ok(MessageResponse("User registered successfully"))
    }
}
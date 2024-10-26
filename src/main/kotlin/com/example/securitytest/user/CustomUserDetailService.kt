package com.example.securitytest.user

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import kotlin.NoSuchElementException

@Service
class CustomUserDetailService : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = hashMapOf<String, String>()

        user["admin"] = "1234"
        user["user"] = "1234"

        // 매개변수로 받은 username을 통해 map에서 검색한 다음에, 해당 username을 가진 키-밸류 쌍을
        // UserDetails 형식으로 감싸서 리턴
        val customUserDetail =
            if (user.containsKey(username)) {
            user[username]?.let { CustomUserDetail(username, it) }
                ?: throw NoSuchElementException()

        } else throw NoSuchElementException()

        return customUserDetail
    }

}
package com.web.devvy.application.service

import com.web.devvy.infrastructure.persistence.repository.ImportedUserJpaRepository
import com.web.devvy.domain.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors


@Component("userDetailsService")
class CustomUserDetailsService(private val importedUserJpaRepository: ImportedUserJpaRepository) : UserDetailsService {
   @Transactional
   override fun loadUserByUsername(username: String): UserDetails {
      return importedUserJpaRepository.findOneWithAuthoritiesByUsername(username)?.let{
         createUser(username, it)
      } ?: throw UsernameNotFoundException("$username -> 데이터베이스에서 찾을 수 없습니다.")
   }

   private fun createUser(username: String, user: User): org.springframework.security.core.userdetails.User {
      if (user.isDeleted.value) {
         throw RuntimeException("$username -> 활성화되어 있지 않습니다.")
      }
      val grantedAuthorities: List<GrantedAuthority> = user.authorities.value.stream()
         .map { authority -> SimpleGrantedAuthority(authority.authorityName) }
         .collect(Collectors.toList())
      return org.springframework.security.core.userdetails.User(
         user.username.value,
         user.password.value,
         grantedAuthorities
      )
   }
}
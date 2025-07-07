package com.bloomnote.user.application.login.service

import com.bloomnote.jwt.service.JwtTokenProvider
import com.bloomnote.jwt.service.RefreshTokenProvider
import com.bloomnote.user.application.login.mapper.LoginApiMapper
import com.bloomnote.user.application.login.usecase.LoginUseCase
import com.bloomnote.user.application.login.usecase.LoginUserResult
import com.bloomnote.user.application.login.usecase.PostLoginQuery
import com.bloomnote.user.domain.login.repository.LoginRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class LoginService(
    private val loginRepository: LoginRepository,
    private val jwtAccessTokenProvider: JwtTokenProvider,
    private val jwtRefreshTokenProvider: RefreshTokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
) : LoginUseCase {
    override fun execute(postLoginQuery: PostLoginQuery): LoginUserResult {
        try {
            val authenticationToken = UsernamePasswordAuthenticationToken(
                postLoginQuery.userEmail,
                postLoginQuery.userPassword
            )

            val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

            val user = loginRepository.findByUserEmail(
                userEmail = postLoginQuery.userEmail
            )

            val accessToken = jwtAccessTokenProvider.createAccessToken(authentication = authentication).accessToken
            val refreshToken = jwtRefreshTokenProvider.createRefreshToken(authentication = authentication)

            return LoginApiMapper.toResult(
                users = user,
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        } catch (e: BadCredentialsException) {
            // 비밀번호가 틀렸을 때의 처리
            throw Exception("잘못된 아이디 혹은 비밀번호입니다.")
        } catch (e: AuthenticationException) {
            // 그 외 인증 관련 예외 처리
            throw Exception("인증 과정에서 오류가 발생했습니다.")
        }
    }
}
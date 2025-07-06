package com.bloomnote.user.application.join.service

import com.bloomnote.user.application.join.mapper.JoinApiMapper
import com.bloomnote.user.application.join.usecase.JoinUseCase
import com.bloomnote.user.application.join.usecase.JoinUserResult
import com.bloomnote.user.application.join.usecase.PostJoinQuery
import com.bloomnote.user.domain.join.repository.JoinRepository
import com.bloomnote.user.infrastructure.join.entity.Users
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JoinService(
    private val joinRepository: JoinRepository,
    private val passwordEncoder: PasswordEncoder
) : JoinUseCase {
    @Transactional
    override fun execute(postJoinQuery: PostJoinQuery): JoinUserResult {
        return joinRepository.save(
            Users(
                userEmail = postJoinQuery.userEmail,
                userPassword = passwordEncoder.encode(postJoinQuery.userPassword),
                userName = postJoinQuery.userName,
                userNickname = postJoinQuery.userNickname,
                isBaby = postJoinQuery.isBaby,
                userBirth = postJoinQuery.birth,
                userGender = postJoinQuery.gender,
                relationship = postJoinQuery.relation,
                profileImg = postJoinQuery.profileImg
            )
        ).let { JoinApiMapper.toResult(users = it) }
    }
}
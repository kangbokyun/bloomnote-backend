package com.bloomnote.login

import com.bloomnote.user.application.login.usecase.LoginUseCase
import com.bloomnote.user.application.login.usecase.PostLoginQuery
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import jakarta.transaction.Transactional
import org.springframework.boot.test.context.SpringBootTest

@Transactional
@SpringBootTest
class LoginTest(
    private val loginUseCase: LoginUseCase
) : BehaviorSpec({
    extensions(SpringTestExtension(SpringTestLifecycleMode.Root))
    Given("1") {
        val postLoginQuery = PostLoginQuery(
            userEmail = "q@q.q",
            userPassword = "qqqqqq"
        )
        When("login") {
            val loginResult = loginUseCase.execute(postLoginQuery = postLoginQuery)
            Then("return success") {
                println("loginResult : ${loginResult.toString()}")
            }
        }
    }
})
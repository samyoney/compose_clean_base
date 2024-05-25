package com.example.compose_clean_base.data.usecase.login

import com.example.compose_clean_base.data.repository.AccountRepository
import javax.inject.Inject

class CheckLoggedInUseCase @Inject constructor(private val repository: AccountRepository) {
    operator fun invoke(): Boolean = repository.username.isEmpty() || repository.password.isEmpty()
}
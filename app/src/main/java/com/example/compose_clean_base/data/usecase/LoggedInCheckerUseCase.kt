package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.repository.StudentRepository
import javax.inject.Inject

class LoggedInCheckerUseCase @Inject constructor(private val repository: StudentRepository) {
    operator fun invoke(): Boolean = repository.username.isEmpty() || repository.password.isEmpty()
}
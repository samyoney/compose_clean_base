package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.repository.StudentRepository
import javax.inject.Inject

class GetUsernameUseCase @Inject constructor(private val repository: StudentRepository) {
    operator fun invoke(): String = repository.username
}
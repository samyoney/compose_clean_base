package com.example.compose_clean_base.data.usecase.enroll

import com.example.compose_clean_base.data.model.local.CourseEntity
import com.example.compose_clean_base.data.model.remote.response.CourseResponse
import com.example.compose_clean_base.data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn

class SaveCoursesUseCase @Inject constructor(private val courseRepository: CourseRepository) {

    operator fun invoke(courses: List<CourseResponse.Course>): Flow<Unit> = flow {
        val listCourseEntity = courses.map {
            CourseEntity(id = it.id, name = it.name, instructor = it.instructor, topics = it.topics)
        }
        courseRepository.insertListCourse(listCourseEntity)
        emit(Unit)
    }.flowOn(Dispatchers.IO)
}
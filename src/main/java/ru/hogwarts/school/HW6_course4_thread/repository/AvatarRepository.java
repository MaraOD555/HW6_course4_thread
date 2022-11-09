package ru.hogwarts.school.HW6_course4_thread.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.HW6_course4_thread.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> { //расширяет PagingAndSortingRepository
     Optional<Avatar> findAvatarByStudentId (long studentId);
}

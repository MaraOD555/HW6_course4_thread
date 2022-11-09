package ru.hogwarts.school.HW6_course4_thread.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.HW6_course4_thread.model.Faculty;

import java.util.List;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColor(String color);

    List<Faculty> findByNameIgnoreCase (String name);

    List<Faculty> findByColorIgnoreCase(String color);

    }

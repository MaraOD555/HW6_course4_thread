package ru.hogwarts.school.HW6_course4_thread.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.HW6_course4_thread.exception.FacultyNotFoundException;
import ru.hogwarts.school.HW6_course4_thread.model.Faculty;
import ru.hogwarts.school.HW6_course4_thread.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;

@Service
public class FacultyService {


    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElse(null);
    }
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id){
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> findFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByNameIgnoreCase(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public Collection<Faculty> findByColorIgnoreCase(String color) {      ;
        return facultyRepository.findByColorIgnoreCase(color);
    }
    public Collection<Faculty> getAllFaculty() { return facultyRepository.findAll();
    }

    public String findTheLongestFacultyName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))// вернет максимальное значение согласно правилу
                // в компараторе (по длине)
                .orElseThrow(FacultyNotFoundException::new);
    }
}

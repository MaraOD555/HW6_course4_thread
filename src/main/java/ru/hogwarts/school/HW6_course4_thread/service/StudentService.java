package ru.hogwarts.school.HW6_course4_thread.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.HW6_course4_thread.exception.StudentNotFoundException;
import ru.hogwarts.school.HW6_course4_thread.model.Student;
import ru.hogwarts.school.HW6_course4_thread.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Stream;


@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class); // конструкция добавлена, чтобы добавить логгер в класс
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }
    public Student findStudent(long id) {
        logger.debug("Was invoked method for find student");
        return studentRepository.findById(id).orElse(null);
    }
    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }
    public void deleteStudents(long id){
        logger.debug("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for findByAge");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int from, int to) {
        logger.info("Was invoked method for findByAgeBetween");
        return studentRepository.findByAgeBetween(from, to);
    }

    public Stream<String> findAllStudentsStartedWithBigA() { //stream используется здесь для практики в стримах, на сомо деле так не делают, а используют SQL
        return studentRepository.findAll().stream()
                .map(Student::getName) //перебираем у студентов имена, в потоке строки
                .map(String::toUpperCase) //все строки в поток со строками в верхнем регистре(передана функция toUpperCase)
                .filter(s -> s.startsWith("A"))// новый поток из имен с "А"
                .sorted(); // только после этого уже сортировка в обычном ранге
    }

   /* public double findStudentsAverageAge() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()//Возвращает значение OptionalDouble,
                // описывающее среднее арифметическое элементов этого потока
                .orElseThrow(StudentNotFoundException::new);
    }

  public void parallelStreamImplementation() { // с параллельным в данном случае практически
        // также расчет выполняется, даже чуть дольше
        Long sum = Stream.iterate(1L, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0L, Long::sum); //это операция выполняется в разных потока, она простая и быстрая
        // и в этом конкретном случае использование паралельных потоков не оправдано,
        // т.к. мы тратим больше времени на переключение между потоками
    }*/
}


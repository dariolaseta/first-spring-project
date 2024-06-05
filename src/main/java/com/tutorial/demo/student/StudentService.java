package com.tutorial.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {

        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {

            throw new IllegalStateException("Email già presente");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {

        boolean studentExists = studentRepository.existsById(studentId);

        if (!studentExists) {

            throw new IllegalStateException("Studente con id " + studentId + " non esiste.");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Studente con id " + studentId + " non esiste."));

        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {

            student.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {

            Optional<Student> studentEmail = studentRepository.findStudentByEmail(email);

            if (studentEmail.isPresent()) {

                throw new IllegalStateException("L'email " + email + " è già associata ad un account.");
            }

            student.setEmail(email);
        }
    }
}

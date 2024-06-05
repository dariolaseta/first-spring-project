package com.tutorial.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getStudents() {

        return studentService.getStudents();
    }

    @PostMapping("/create")
    public ResponseEntity<Long> registerNewStudent(@RequestBody Student student) {

        Long newStudentId = studentService.addNewStudent(student);

        return ResponseEntity.ok(newStudentId);
    }

    @DeleteMapping("delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {

        studentService.deleteStudent(studentId);
    }

    @PutMapping("updateStudent/{studentId}")
    public Optional<Student> updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {

         return studentService.updateStudent(studentId, name, email);
    }
}

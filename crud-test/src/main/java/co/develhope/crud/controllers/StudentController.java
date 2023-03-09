package co.develhope.crud.controllers;

import co.develhope.crud.entities.Student;
import co.develhope.crud.repositories.StudentRepository;
import co.develhope.crud.services.StudentService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {


   @Autowired
    StudentService studentService;

   @PostMapping("")
   public Student createStudent(@RequestBody Student student){
       return studentService.createStudent(student);
   }

   @GetMapping("")
    public List<Student> getAll(){
       return studentService.getAllStudent();
   }

   @GetMapping("/{id}")
    public Optional<Student> findOne(@PathVariable Long id){
       try {
           return studentService.getOne(id);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

   @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestParam Long newId, @RequestParam String name,
                                 @RequestParam String surname, @RequestParam boolean isWorking){
       return studentService.updateStudent(id, newId, name, surname, isWorking);
   }

   @PutMapping("/update_working/{id}")
   public Student updateWorking(@PathVariable Long id, @RequestParam boolean isWorking){
      return studentService.setStudentIsWorkingStatus(id,isWorking);
   }

   @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id){
       studentService.deleteOne(id);
   }



}


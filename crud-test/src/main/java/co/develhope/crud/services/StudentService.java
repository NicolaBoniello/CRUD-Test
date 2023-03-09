package co.develhope.crud.services;

import co.develhope.crud.entities.Student;
import co.develhope.crud.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

   public Student createStudent(Student student){
      student.setWorking(true);
       return studentRepository.save(student);
   }

   public List<Student> getAllStudent(){

       return studentRepository.findAll();
   }

   public Optional<Student> getOne(Long id) throws Exception {
      Optional<Student> student = studentRepository.findById(id);
      if (student.isPresent()) {
          return student;
      }
      throw new Exception("Not found!");
   }

   public Student updateStudent(Long id, Long newId, String name, String surname, boolean isWorking) {

       Student student;
       if (studentRepository.existsById(id)){
           student = studentRepository.getById(id);
           student.setId(newId);
           student.setName(name);
           student.setSurname(surname);
           student.setWorking(isWorking);
           student = studentRepository.save(student);
       } else {
           student = new Student();
       }
       return student;
   }



   public void deleteAll(){
       studentRepository.deleteAll();
   }

   public void deleteOne(Long id){
       if (studentRepository.existsById(id)){
           studentRepository.deleteById(id);
       }


   }
   public Student setStudentIsWorkingStatus(Long id, boolean isWorking){
        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent()) return null;
        student.get().setWorking(isWorking);
        return studentRepository.save(student.get());
    }
}

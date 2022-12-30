package com.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Student;
import com.app.exception.StudentNotFoundException;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/v1/api/student")
public class StudentRestController {

	@Autowired
	private IStudentService service;
	
	//1. Get all Students
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents(){
		return ResponseEntity.ok(service.getAllStudents());
	}
	
	//2. Create one Student
	@PostMapping("/create")
	public ResponseEntity<String> saveStudent(
			@RequestBody Student student){
		Integer id = service.saveStudent(student);
		return new ResponseEntity<String>("Student '"+ id +"' created successfully", HttpStatus.CREATED);
	}
	
	//3. Fetch one student
	@GetMapping("/fetch/{id}")
	public ResponseEntity<Student> getOneStudent(
			@PathVariable("id") Integer id){
		ResponseEntity<Student> response = null;
		try {
			Student s = service.getOneStudent(id);
			response = ResponseEntity.ok(s);
		}catch(StudentNotFoundException snfe) {
			snfe.printStackTrace();
			throw snfe;
		}
		return response;
	}
	
	//4. Delete Student by Id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(
			@PathVariable("id") Integer id){
		ResponseEntity<String> response = null;
		try {
			service.deleteStudent(id);
			response = ResponseEntity.ok("Student '"+id+"' deleted successfully");
		} catch(StudentNotFoundException snfe) {
			snfe.printStackTrace();
			throw snfe;
		}
		return response;
	}
	
	//5. Update Student 
	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(
			@RequestBody Student student){
		ResponseEntity<String> response = null;
		try {
			service.updateStudent(student);
			response = ResponseEntity.ok("Student '"+student.getStdId()+"' updated successfully");
		} catch(StudentNotFoundException snfe) {
			snfe.printStackTrace();
			throw snfe;
		}
		return response;
	}
	
}

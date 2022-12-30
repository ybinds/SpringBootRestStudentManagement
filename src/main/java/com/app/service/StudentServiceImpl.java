package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Student;
import com.app.exception.StudentNotFoundException;
import com.app.repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepository repo;
	
	public List<Student> getAllStudents() {
		return repo.findAll();
	}

	public Student getOneStudent(Integer id) {
		return repo.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("STUDENT '"+ id +"' DOES NOT EXIST"));
	}

	public Integer saveStudent(Student s) {
		return repo.save(s).getStdId();
	}

	public void deleteStudent(Integer id) {
		repo.delete(getOneStudent(id));
	}

	public void updateStudent(Student s) {
		if(s.getStdId()==null || !repo.existsById(s.getStdId()))
			throw new StudentNotFoundException("STUDENT '"+s.getStdId()+"' DOES NOT EXIST");
		else
			repo.save(s);
	}

}

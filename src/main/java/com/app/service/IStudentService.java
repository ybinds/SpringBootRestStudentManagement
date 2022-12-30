package com.app.service;

import java.util.List;

import com.app.entity.Student;

public interface IStudentService {

	List<Student> getAllStudents();
	Student getOneStudent(Integer id);
	Integer saveStudent(Student s);
	void deleteStudent(Integer id);
	void updateStudent(Student s);
}

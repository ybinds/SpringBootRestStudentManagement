package com.app.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name="students")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer stdId;
	@Column(name="name")
	private String stdName;
	@Column(name="gender")
	private String stdGender;
	@Column(name="email")
	private String stdEmail;
	@Column(name="phone")
	private Long stdPhone;
	@Column(name="address")
	private String stdAddress;
	
	@Column(name="dob")
	@JsonFormat(pattern="MM/dd/yyyy")
	private LocalDate stdDob;
	
}

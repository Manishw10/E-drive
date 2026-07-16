 	package com.springSecurity.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity.Entity.Student;
import com.springSecurity.Service.Services;


@RestController
public class SpringController {
	
	@Autowired
	private Services services;

	@GetMapping("/student")
	public List<Student> test() {
		return services.getStudent();
	}
	
	@PostMapping("/add_student")
	public Student addStudent(@RequestBody Student s) {
		return services.addBook(s);
	}
	
	@GetMapping("/deleteAll")
	public ResponseEntity<String> deleteStudent() {
		 services.deletedata();
		 return new ResponseEntity<String>("Data deleted", HttpStatus.OK);
		 
	}
}	

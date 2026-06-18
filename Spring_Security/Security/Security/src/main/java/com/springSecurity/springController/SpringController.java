package com.springSecurity.springController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@ResponseBody
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
}	

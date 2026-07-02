package com.springSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springSecurity.Dao.DaoInterface;
import com.springSecurity.Entity.Student;
import java.util.List;

@Service
public class Services  {
	
	@Autowired
	private DaoInterface studentDao;
	
	public Student addBook(Student s) {
		System.out.println(s);
		return studentDao.save(s);
	}

	public List<Student> getStudent() {
		// TODO Auto-generated method stub
		return (List<Student>) studentDao.findAll();
	}

}

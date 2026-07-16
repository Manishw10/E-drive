package com.springSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springSecurity.Dao.DaoInterface;
import com.springSecurity.Entity.Student;
import com.springSecurity.ExceptionHandling.DataNotFoundException;

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
		List<Student> list = (List<Student>) studentDao.findAll();
		if(list.isEmpty()){
			throw new DataNotFoundException("Student list is empty");
		}
		else {
			return  list;
		}
	}

	public void deletedata() {
			studentDao.deleteAll();
		
	}

}

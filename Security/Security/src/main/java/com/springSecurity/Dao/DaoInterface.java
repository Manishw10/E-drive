package com.springSecurity.Dao;

import org.springframework.data.repository.CrudRepository;

import com.springSecurity.Entity.Student;

public interface DaoInterface extends CrudRepository<Student, Integer> {

}

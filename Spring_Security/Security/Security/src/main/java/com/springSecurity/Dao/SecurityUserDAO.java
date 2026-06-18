package com.springSecurity.Dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springSecurity.Entity.Users;

public interface SecurityUserDAO extends JpaRepository<Users,Integer>{
	Optional< Users > findByUserName(String uname);
}

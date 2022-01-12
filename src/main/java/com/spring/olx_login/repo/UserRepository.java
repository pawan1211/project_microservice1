package com.spring.olx_login.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.olx_login.entity.UserEntity;


@Repository


public interface UserRepository extends JpaRepository<UserEntity,Integer>{

	List<UserEntity> findByUsername(String username);
}

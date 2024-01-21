package com.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.entity.Roles;

public interface RoleRepo extends JpaRepository<Roles,Integer >{

}

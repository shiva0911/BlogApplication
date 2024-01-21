package com.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart.entity.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

}

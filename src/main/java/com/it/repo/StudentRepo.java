package com.it.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it.entity.StudentEntity;
@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Integer> {

}

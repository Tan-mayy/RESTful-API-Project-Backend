package com.apirest.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.entities.Course;

public interface Repo extends JpaRepository<Course, Serializable> {

}

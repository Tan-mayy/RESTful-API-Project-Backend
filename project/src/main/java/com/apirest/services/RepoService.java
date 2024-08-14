package com.apirest.services;

import java.util.List;

import com.apirest.entities.Course;

public interface RepoService {
	
	public String upsert(Course course, Integer id);
	public String upsert(Course course);
	public Course getById(Integer id);
	public List<Course> getAllCourses();
	public boolean deleteById(Integer id);

	
}

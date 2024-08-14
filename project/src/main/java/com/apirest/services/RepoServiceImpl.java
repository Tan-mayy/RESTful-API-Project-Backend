package com.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.entities.Course;
import com.apirest.repositories.Repo;

@Service
public class RepoServiceImpl implements RepoService {

	@Autowired
	private Repo rep;

	@Override
	public String upsert(Course course, Integer id) {
		System.err.println(course.getName()+"hhhhh");
		System.err.println(course.getPrice()+"ggggg");
		rep.save(course);
		return "successhhhhhh";
	}
	public String upsert(Course course) {
		System.err.println(course.getName());
		System.err.println(course.getPrice());
		rep.save(course);
		return "successhhhhhh";
	}

	@Override
	public Course getById(Integer id) {
		Optional<Course> findById = rep.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public List<Course> getAllCourses() {
		return rep.findAll();
	}

	@Override
	public boolean deleteById(Integer id) {
		if (rep.existsById(id)) {
			rep.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}

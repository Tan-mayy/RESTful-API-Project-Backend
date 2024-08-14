package com.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.entities.Course;
import com.apirest.entities.ErrorResp;
import com.apirest.services.RepoService;

@RestController
public class RestCtrl {

	@Autowired
	private RepoService repoService;

	@PostMapping("/course")
	public ResponseEntity<String> createCourse(@RequestBody Course course) {
		System.err.println(course.getName() + "hhhhh");
		System.err.println(course.getPrice() + "ggggg");

		String status = repoService.upsert(course);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

	@GetMapping("/course/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable Integer id) {
		Course course = this.repoService.getById(id);
		if (course != null) {
			return ResponseEntity.ok(course);

		} else {
			return ResponseEntity.notFound().build();

		}
	}

	

	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> allCourses = repoService.getAllCourses();
		
		if(!allCourses.isEmpty()) {
			return ResponseEntity.ok(allCourses);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/course/{id}")
	public ResponseEntity<?> updateCourse(@ModelAttribute Course course, @PathVariable Integer id) {
		// Check if the course with the given id exists
		Optional<Course> existingCourse = Optional.ofNullable(this.repoService.getById(id));

		if (existingCourse.isPresent()) {
			try {
				// Update the course using the repoService upsert method
				this.repoService.upsert(course, id);
				return ResponseEntity.ok().body(course);

			} catch (Exception e) {
				e.printStackTrace();
				ErrorResp errresp = new ErrorResp(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while updating the course.");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errresp);
			}
		} else {
			// If course with given id does not exist, return 404 Not Found
			ErrorResp errorResp = new ErrorResp(HttpStatus.NOT_FOUND.value(), "Course doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResp);
		}
	}

	@DeleteMapping("/course/{id}")
	public ResponseEntity<ErrorResp> deleteCourse(@PathVariable Integer id) {
		boolean status = repoService.deleteById(id);
		
		if(status == true) {
			ErrorResp errorResp = new ErrorResp(HttpStatus.OK.value(), "Course Omitted Successfully !");
			return new ResponseEntity<ErrorResp>(errorResp, HttpStatus.OK);
		}
		else {
			ErrorResp errorResp = new ErrorResp(HttpStatus.NOT_FOUND.value(), "Course not found");
            return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);

		}
	}

}

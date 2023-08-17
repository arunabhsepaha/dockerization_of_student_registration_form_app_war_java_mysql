package com.it.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.it.binding.Student;
import com.it.entity.StudentEntity;
import com.it.repo.StudentRepo;

@Controller
public class StudentController {

	@Autowired
	private StudentRepo repo;

	// method to load student form
	// http://localhost:8080/
	//http://13.127.106.232:8080/
	@GetMapping("/")
	public String loadForm(Model model) {

		loadFormData(model);

		return "index";
	}

	private void loadFormData(Model model) {
		List<String> coursesList = new ArrayList<>();
		coursesList.add("Java");
		coursesList.add("DevOps");
		coursesList.add("AWS");
		coursesList.add("Python");

		List<String> timingsList = new ArrayList<>();
		timingsList.add("Morning");
		timingsList.add("Afternoon");
		timingsList.add("Evening");

		Student student = new Student();

		model.addAttribute("courses", coursesList);
		model.addAttribute("timings", timingsList);
		model.addAttribute("student", student);
	}

	// method to save student form data
	// http://localhost:8080/save
	@PostMapping("/save")
	public String handleSubmit(Student s, Model model) {
		System.out.println(s);
		// logic to save

		StudentEntity entity = new StudentEntity();

		// copy data from binding obj to entity obj
		BeanUtils.copyProperties(s, entity);

		entity.setTimings(Arrays.toString(s.getTimings()));

		repo.save(entity);

		model.addAttribute("msg", "Student Data Saved");

		loadFormData(model);

		return "index";
	}

	// method to display saved students data

	@GetMapping("/viewStudents")
	public String getStudentsData(Model model) {

		// logic to fetch and send students data

		List<StudentEntity> studentsList = repo.findAll();

		model.addAttribute("students", studentsList);

		return "data";
	}

}

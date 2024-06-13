package com.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@RequestMapping("/insert")
	public String insert() {
		return "employee_insert";
	}
	@RequestMapping("/delete")
	public String delete() {
		return "employee_delete";
	}
	@RequestMapping("/update")
	public String update() {
		return "employee_update";
	}
}

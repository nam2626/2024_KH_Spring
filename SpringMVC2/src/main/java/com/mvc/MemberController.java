package com.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
	@RequestMapping("/insert")
	public String insert() {
		return "member_insert";
	}
	@RequestMapping("/delete")
	public String delete() {
		return "member_delete";
	}
	@RequestMapping("/update")
	public String update() {
		return "member_update";
	}
}

package com.flat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flat.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	// Mapping for the index page
		@GetMapping("/")
		public String index() {
			return "index"; // Maps to index.html in the templates folder
		}
	
	// Display login form
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		return "login"; // Maps to login.html in the templates folder
	}

	@PostMapping("/validate")
	public String validateUser(@RequestParam String username, @RequestParam String password, Model model) {
	    System.out.println("Username: " + username);
	    System.out.println("Password: " + password);
	    
	    boolean isValid = loginService.validateUser(username, password);
	    if (isValid) {
	        String role = loginService.findRoleByUsername(username);
	        System.out.println("Role: " + role);
	        switch (role.toLowerCase()) {
	            case "manager":
	                return "managerhome";
	            case "admin":
	                return "adminhome";
	            
	            default:
	                model.addAttribute("error", "Unknown role");
	                return "login";
	        }
	    } else {
	        model.addAttribute("error", "Invalid Credentials");
	        return "login";
	    }
	}

	
}

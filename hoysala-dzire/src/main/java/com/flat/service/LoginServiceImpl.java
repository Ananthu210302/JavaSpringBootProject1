package com.flat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flat.entity.Login;
import com.flat.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public Login authenticateUser(String username, String password) {
		Login login = loginRepository.findByUsername(username);
		if (login != null && login.getPassword().equals(password)) {
			return login; // 
		}
		return null; // Invalid credentials
	}

	@Override
	public boolean validateUser(String username, String password) {
		return authenticateUser(username, password) != null;
	}

	@Override
	public Login findByUsername(String username) {
		return loginRepository.findByUsername(username);
	}

	@Override
	public String findRoleByUsername(String username) {
	    Login login = loginRepository.findByUsername(username);
	    if (login == null) {
	        throw new IllegalArgumentException("No user found with username : " + username);
	    }
	    return login.getRole();
	}

}

package com.example.ApiTeste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiTeste.model.ApiUser;
import com.example.ApiTeste.model.LoginUser;
import com.example.ApiTeste.repository.UserRepository;

@RestController
public class LoginController {
	
	@Autowired
	UserRepository userDAO;
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody LoginUser user){
		
		ApiUser uu = new ApiUser();
		uu.setUsername(user.getUsername());
		uu.setPassword(new BCryptPasswordEncoder().encode("lucas").toString());
		
		ApiUser u = userDAO.findByUsername(user.getUsername());
		if(u != null && u.getPassword().equals(new BCryptPasswordEncoder().encode(user.getPassword()).toString())){
			return new ResponseEntity<>("Autenticado ! user: "+ u.getUsername(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Acessou o método login Sem autenticação: "+ uu.getUsername() +
					"\n"+ uu.getPassword(), HttpStatus.OK);
		}
		
	}
}

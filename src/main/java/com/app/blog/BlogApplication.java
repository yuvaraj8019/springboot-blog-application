package com.app.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.blog.entities.Role;
import com.app.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		Role admin = new Role(101, "ROLE_ADMIN");
		Role dev = new Role(102, "ROLE_DEVELOPER");
		Role test = new Role(103, "ROLE_TEST");
		List<Role> roles = List.of(admin,dev,test);
		roleRepo.saveAll(roles);
	}
    
    

}

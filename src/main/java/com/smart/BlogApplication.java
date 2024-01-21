package com.smart;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.smart.config.AppConstants;
import com.smart.dao.RoleRepo;
import com.smart.entity.Roles;

@SpringBootApplication(scanBasePackages = "com.smart")

public class BlogApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
		
	}
	
	public void run(String... args) {
        try {
            System.out.println(this.passwordEncoder.encode("xyz"));

            Roles role1 = new Roles();
            role1.setId(AppConstants.ADMIN_USER);
            role1.setName("ROLE_ADMIN");

            Roles role2 = new Roles();
            role2.setId(AppConstants.NORMAL_USER);
            role2.setName("ROLE_NORMAL");

            List<Roles> roles = List.of(role1, role2);
            List<Roles> result = this.roleRepo.saveAll(roles);

            result.forEach(r -> System.out.println(r.getName()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
 
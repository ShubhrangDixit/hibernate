package com.lti.test;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.lti.dao.UserDao;
import com.lti.entity.User;


public class UserDaoTest {
	
	@Test
	public void addUser() {
		User u = new User();
		
	
		u.setId(4);
		u.setName("Abhishek");
		u.setEmail("abhi@abc.com");
		u.setPassword("abhi123");
		u.setMobNo(12345);
		u.setDateOfBirth(LocalDate.of(1998, 3, 20));
		
		UserDao ud = new UserDao();
		ud.store(u);
		
		//assert is missing for now
	}
	
	@Test
	public void findUser() {
		UserDao ud = new UserDao();
		User u = ud.retrieve(1);
		
		System.out.println(u.getId()+":"+u.getName()+":"+u.getEmail()+":"+ u.getPassword()+":"+ u.getMobNo()+":"+ u.getDateOfBirth());
	//Syso is not a good practice for testing, as mostly testing is automated and there is no point of Syso output then. Assert is used instead.
	}
	
	@Test
	public void updateUser() {
		UserDao ud = new UserDao();
		User u = ud.retrieve(1);
		
		u.setPassword("shubh321");
		
		ud.update(u);
		
	}
	@Test
	public void findUserList() {
		UserDao ud = new UserDao();
		List<User> list = ud.retrieve("h");
		
		for(User u : list) {
			System.out.println(u.getId()+":"+u.getName()+":"+u.getEmail()+":"+ u.getPassword()+":"+ u.getMobNo()+":"+ u.getDateOfBirth());
		}
			
		
	}
	
	@Test
	public void findUserByBirthDate() {
		UserDao ud = new UserDao();
		List<User> list = ud.retrieveByBirthDate(2);
		
		for(User u : list) {
			System.out.println(u.getId()+":"+u.getName()+":"+u.getEmail()+":"+ u.getPassword()+":"+ u.getMobNo()+":"+ u.getDateOfBirth());
		}
			
		
	}
	
	
	

}

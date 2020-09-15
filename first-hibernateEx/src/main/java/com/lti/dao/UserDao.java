package com.lti.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.User;

//this class contains DB interaction code

public class UserDao {
	
	public void store(User user) {
		//Step 1. Create/obtain EntityManagerFactory Object
		//During this step META-INF/persistence.xml will be read
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("first-hibernateEx"); // <persistence-unit name = ".."/>
		//Step 2. Create/Obtain EntityManager Object
		EntityManager em = emf.createEntityManager();
		//Step 3. Start/Participate in a transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//Now we can perform any insert/update/delete/select SQL operation
		em.persist(user); //persist method generates insert query
		
		tx.commit();
		
		//should be in finally class
		em.close();
		emf.close();
	}
	
	public User retrieve(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("first-hibernateEx");
		EntityManager em = emf.createEntityManager();
		
		User u = em.find(User.class, id); //find method generate select * from table where id = ?
		
		em.close();
		emf.close();
		
		return u;
	}
	
	public List<User> retrieve(String name){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("first-hibernateEx");
		EntityManager em = emf.createEntityManager();
		
		//SQL --> select * from tbl_user where name like '%a%';
		String jpql = "select u from User u where u.name like :nm"; //:nm -> placeholder
		Query q = em.createQuery(jpql);
		q.setParameter("nm", "%"+name+"%");
		List<User> list = q.getResultList();
		
		em.close();
		emf.close();
		
		return list;
	}
	//return all users whose birth day in given month
	public List<User> retrieveByBirthDate(int month){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("first-hibernateEx");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "select u from User u where month(u.dateOfBirth) = :month"; //internally month func converts into extract func
		Query q = em.createQuery(jpql);
		q.setParameter("month", month);
		List<User> list = q.getResultList();
		
		em.close();
		emf.close();
		
		return list;
	}
	
	public void update(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("first-hibernateEx"); 
		EntityManager em = emf.createEntityManager();
					
		EntityTransaction tx = em.getTransaction();
					
		tx.begin();
					
		em.merge(user); //merge is a 2in1 method, can be used for insert and update both
 					
		tx.commit();
					
				
		em.close();
		emf.close();
			
	}
	

}

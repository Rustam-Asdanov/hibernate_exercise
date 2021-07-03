package com.hibernate.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.example.entity.Human;

public class CRUDoperations {
	
	static Session session;
	static SessionFactory factory;
	static BufferedReader reader;
	
	public static void start() {
		factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Human.class)
								.buildSessionFactory();
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void closeAll() {
		factory.close();
		try {
			reader.close();
		} catch(IOException ex) {
			System.out.println("Problem with close");
		}
	}
	
	public static void create() {
		System.out.println("Create operation is running...\n");
		
		try {
			while(true) {
				System.out.print("Input first name: ");
				String name = reader.readLine();
				
				System.out.print("Input last name: ");
				String surname = reader.readLine();
				
				System.out.print("Input company name: ");
				String company = reader.readLine();
				
				session.save(new Human(name,surname,company));
				
				System.out.print("Do you want contionue? (y/n)");
				if(reader.readLine().equals("n")) break;
			}
			
		} 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			session.getTransaction().commit();			
		}
	}
	
	
	public static void getData() {
		System.out.println("Get data oparation is running...\n");
		
		List<Human> theHumans = session.createQuery("from Human")
										.getResultList();
		
		for(Human human : theHumans) {
			System.out.println(human);
		}
		
	}
	
	public static void updateData() {
		System.out.println("Updating human table is running...\n");
		
		System.out.print("Write id of human, which company column you want to update: ");
		int humanId;
		try {
			humanId = Integer.parseInt(reader.readLine());
		
			Human human = session.get(Human.class, humanId);
			
			System.out.print("Input company name for this person: ");
			human.setCompany(reader.readLine());
			
			session.getTransaction().commit();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public static void deleteData() {
		System.out.print("Delete function is running...\n");
		
		System.out.print("Which person do you want to remove from table? Input id: ");
		try{
			session.createQuery("delete from Human where id="+
		
							Integer.parseInt(reader.readLine())).executeUpdate();
			
			session.getTransaction().commit();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}

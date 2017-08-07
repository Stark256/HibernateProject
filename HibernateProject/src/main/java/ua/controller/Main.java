package ua.controller;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {

		Scanner cin = new Scanner(System.in);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		ConsolMenu cm = new ConsolMenu(em, cin);
		cm.menu();
		
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
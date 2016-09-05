package com.practice.self.project.hibernate.annotations;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class ManageDog {
	
	public static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		try {
			sessionFactory = new AnnotationConfiguration().addAnnotatedClass(Dog.class).configure().buildSessionFactory();
		} catch(Exception ex) {
			System.out.println("Failed to create session factory object " + ex);
			throw new ExceptionInInitializerError();
		}
		ManageDog md = new ManageDog();
		Integer dog1 = md.addDog("Pug", "Blowman");
		md.addDog("Dobberman", "Mr. Nice");
		Integer dog3 = md.addDog("German Shepherd", "Bloggy");
		md.listDogs();
		md.updateDog(dog1, "Hercules");
		md.deleteDog(dog3);
		md.listDogs();
		
	}

	private Integer addDog(String breed, String name) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer dogId = null;
		try {
			tx = session.beginTransaction();
			Dog dog = new Dog();
			dog.setBreed(breed);
			dog.setName(name);
			dogId = (Integer) session.save(dog);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
		} finally {
			session.close();
		}
		return dogId;
	}
	
	private void listDogs() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Dog> dogs = session.createQuery("FROM Dog").list();
			for (Iterator<Dog> it = dogs.iterator(); it.hasNext();) {
				Dog dog = it.next();
				System.out.println("Name -> " + dog.getName() + ". Breed -> " + dog.getBreed());
			}
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
		} finally {
			session.close();
		}
	}
	
	private void updateDog(Integer id, String name) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Dog dog = (Dog)session.get(Dog.class, id);
			dog.setName(name);
			session.update(dog);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
		} finally {
			session.close();
		}
	}
	
	private void deleteDog(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Dog dog = (Dog)session.get(Dog.class, id);
			session.delete(dog);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
		} finally {
			session.close();
		}
	}
}

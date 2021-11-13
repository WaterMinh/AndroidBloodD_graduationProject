package com.bkap.service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
	private static SessionFactory sf;
	
	public static SessionFactory getSessionFactory() {
		if (sf == null) {
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			Metadata mt = new MetadataSources(registry).buildMetadata();
			sf = mt.buildSessionFactory();
		}
		return sf;
		
	}

}

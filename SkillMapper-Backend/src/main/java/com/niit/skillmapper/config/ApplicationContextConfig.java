package com.niit.skillmapper.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.niit.skillmapper.model.Certification;
import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.model.Role;
import com.niit.skillmapper.model.Skill;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.niit.skillmapper")
@EnableWebMvc
public class ApplicationContextConfig {

	/*
	 * Define the bean for DataSource. In our application, we are using MySQL as the dataSource.
	 * To create the DataSource bean, we need to know:
	 * 1. Driver class name
	 * 2. Database URL
	 * 3. Username
	 * 4. Password
	 */
	
	@Bean(name = "dataSource" )
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/skillmapper");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	private Properties getHibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.hbm2ddl.auto","update");
		return properties;
	}
	
	/*
	 * Define the bean for SessionFactory. Hibernate SessionFactory is the factory class 
	 * through which we get sessions and perform database operations. 
	 */
	@Autowired
	@Bean(name = "sessionFactory" )
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Employee.class);
		sessionBuilder.addAnnotatedClass(Role.class);
		sessionBuilder.addAnnotatedClass(Certification.class);
		sessionBuilder.addAnnotatedClass(Skill.class);
		return sessionBuilder.buildSessionFactory();
		
	}

	
	
	/*
	 * Define the bean for Transaction Manager. HibernateTransactionManager handles transaction 
	 * in Spring. The application that uses single hibernate session factory for database transaction
	 * has good choice to use HibernateTransactionManager. HibernateTransactionManager can work with 
	 * plain JDBC too. HibernateTransactionManager allows bulk update and bulk insert and ensures 
	 * data integrity.   
	 */
	
	@Autowired
	@Bean(name = "transactionManager" )
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}


}

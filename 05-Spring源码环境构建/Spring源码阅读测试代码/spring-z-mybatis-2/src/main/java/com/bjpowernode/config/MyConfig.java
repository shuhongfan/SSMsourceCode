package com.bjpowernode.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@PropertySource(value = {"classpath:jdbc.properties"})
@MapperScan(value = "com.bjpowernode.mapper", annotationClass = Mapper.class) //mybatis-spring 这个项目提供的注解
@ComponentScan(basePackages = "com.bjpowernode")
@Configuration
public class MyConfig {

	//  @MapperScan  @Mapper 注解

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${jdbc.url}")
	private String url;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return sessionFactory.getObject();
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
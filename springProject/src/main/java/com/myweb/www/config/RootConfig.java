package com.myweb.www.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = {"com.myweb.www.repository"})
@ComponentScan(basePackages = {"com.myweb.www.service"})
public class RootConfig {
	//DB설정
	//전과 달라진 부분 log4jdbc-log4j2 사용
	//hikariCP 사용 
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSource() {
		
		HikariConfig hikariConfig = new HikariConfig();
		// log4jdbc-log4j2의 드라이버 클래스 url 사용
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springdb");
		hikariConfig.setUsername("springUser");
		hikariConfig.setPassword("mysql");
		
		hikariConfig.setMaximumPoolSize(5); //최대 커넥션 개수
		hikariConfig.setMinimumIdle(5); //최소 유효 커넥션 개수
		hikariConfig.setConnectionTestQuery("SELECT now()"); //test 쿼리
		hikariConfig.setPoolName("springHikariCP");
		
		//config의 추가 설정
		
		//cache 사용 여부 설정 true
		
		hikariConfig.addDataSourceProperty("dataSource.cachPrepStmts", "true");
		
		//mysql 드라이버가 연결당 cache statement의 수에 관한 설정 기본값이 25 하지만 250~500권장
		
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		
		//mysql connection당 캐싱할 preparedStatement의 개수 지정 옵션 기본값이 256 하지만 2500까지 설정가능 
		
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true"); //기본값 쓸꺼면 true
		
		//mysql 서버에서 최신 이슈가 있을경우 지원을 받는 설정 server-side wldnjs tjfwjd ㅅ겯
		
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		return hikariDataSource;	
		
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		
		return (SqlSessionFactory)sqlFactoryBean.getObject();
		
		
	}
}

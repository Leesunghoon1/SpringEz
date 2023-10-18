package com.myweb.www.config;

import javax.servlet.Filter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		// encoding filter 설정
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		
		encodingFilter.setEncoding("utf-8");
		encodingFilter.setForceEncoding(true); //외부에서 나가는 데이터도 인코딩 설정(깨질수 있기 때문)
		return new Filter[] {encodingFilter};
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		// 그외 기타 등등 사용자 설정
		// 사용자 지정 익셉션 설정을 할 것인지 처리|
		registration.setInitParameter("throwExceptionIfNotHandlerFound", "true"); // (사용자 지정 핸들러 찾아라) 설정
		
	}
	
	

}

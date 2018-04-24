package cn.edu.gdaib;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.gdaib.domain.Securityroles;
import cn.edu.gdaib.mapper.SecurityrolesMapper;

public class SecurityrolesTest {

	private ApplicationContext applicationContext;
	
	@Before
	public void init(){
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	
	@Test
	public void insertSecurity(){
		SecurityrolesMapper mapper = applicationContext.getBean(SecurityrolesMapper.class);
		Securityroles securityroles = new Securityroles();
		securityroles.setName("С��");
		securityroles.setPhone("13640224674");
		//0��ʾ8-5 1��ʾ5-12 2��ʾ12-8
		securityroles.setDuty_time("0");
		mapper.insertSecurity(securityroles);
	}
	
	@Test
	public void selectSecurity(){
		SecurityrolesMapper mapper = applicationContext.getBean(SecurityrolesMapper.class);
		List<Securityroles> securityrolesList = mapper.getSecurityrolesList("0");
		for (Securityroles securityroles : securityrolesList) {
			System.out.println(securityroles);
		}
	}
}

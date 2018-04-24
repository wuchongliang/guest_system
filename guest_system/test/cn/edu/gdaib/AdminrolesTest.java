package cn.edu.gdaib;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.gdaib.domain.Adminroles;
import cn.edu.gdaib.mapper.AdminrolesMapper;

public class AdminrolesTest {
	
	private ApplicationContext applicationContext;
	
	@Before
	public void init(){
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	
	@Test
	public void insertAdmin(){
		AdminrolesMapper mapper = applicationContext.getBean(AdminrolesMapper.class);
		Adminroles adminroles = new Adminroles();
		adminroles.setName("Эѕзм");
		adminroles.setPhone("13312345678");
		mapper.insertAdmin(adminroles);
	}
}

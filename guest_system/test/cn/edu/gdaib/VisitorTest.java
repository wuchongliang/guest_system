package cn.edu.gdaib;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.gdaib.domain.Visitor;
import cn.edu.gdaib.mapper.VisitorMapper;

public class VisitorTest {

	private ApplicationContext applicationContext;

	@Before
	public void init(){
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	
	//查询所有访客信息
	@Test
	public void testGetVisitorList(){
		VisitorMapper mapper = applicationContext.getBean(VisitorMapper.class);
		List<Visitor> list = mapper.getVisitorList();
		for (Visitor visitor : list) {
			System.out.println(visitor);
		}
	}
	
	//根据访客身份证号查询访客信息
	@Test
	public void testGetVisitorById(){
		VisitorMapper mapper = applicationContext.getBean(VisitorMapper.class);
		Visitor visitor = mapper.getVisitorById(440582199508015970L);
		System.out.println(visitor);
	}
	
	//增加访客信息
	@Test
	public void testInsertVisitor(){
		//先判断访客是否存在，如果存在的话，将访客次数加一，如果不存在，就创建访客信息
		VisitorMapper mapper = applicationContext.getBean(VisitorMapper.class);
		Visitor visitor = mapper.getVisitorById(440582199508015971L);
		if(visitor == null){
			Visitor v1 = new Visitor();
			v1.setId(440582199508015971L);
			v1.setTimes(1);
			mapper.insertVisitor(v1);
		}else{
			Visitor v2 = new Visitor();
			v2.setId(visitor.getId());
			v2.setTimes(visitor.getTimes()+1);
			mapper.updateVisitor(v2);
		}
	}
	
	//删除访客信息
	@Test
	public void testDeleteVisitor(){
		VisitorMapper mapper = applicationContext.getBean(VisitorMapper.class);
		mapper.deleteVisitor(440892199702125689L);
	}
}

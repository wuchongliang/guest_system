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
	
	//��ѯ���зÿ���Ϣ
	@Test
	public void testGetVisitorList(){
		VisitorMapper mapper = applicationContext.getBean(VisitorMapper.class);
		List<Visitor> list = mapper.getVisitorList();
		for (Visitor visitor : list) {
			System.out.println(visitor);
		}
	}
	
	//���ݷÿ����֤�Ų�ѯ�ÿ���Ϣ
	@Test
	public void testGetVisitorById(){
		VisitorMapper mapper = applicationContext.getBean(VisitorMapper.class);
		Visitor visitor = mapper.getVisitorById(440582199508015970L);
		System.out.println(visitor);
	}
	
	//���ӷÿ���Ϣ
	@Test
	public void testInsertVisitor(){
		//���жϷÿ��Ƿ���ڣ�������ڵĻ������ÿʹ�����һ����������ڣ��ʹ����ÿ���Ϣ
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
	
	//ɾ���ÿ���Ϣ
	@Test
	public void testDeleteVisitor(){
		VisitorMapper mapper = applicationContext.getBean(VisitorMapper.class);
		mapper.deleteVisitor(440892199702125689L);
	}
}

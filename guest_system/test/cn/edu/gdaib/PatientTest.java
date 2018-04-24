package cn.edu.gdaib;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.gdaib.domain.Patient;
import cn.edu.gdaib.mapper.PatientMapper;

public class PatientTest {
	
	private ApplicationContext applicationContext;
	
	@Before
	public void init(){
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	
	//测试查询病人信息
	@Test
	public void testGetPatientList(){
		PatientMapper mapper = applicationContext.getBean(PatientMapper.class);
		List<Patient> list = mapper.getPatientList();
		for (Patient patient : list) {
			System.out.println(patient); 
		}
	}
	
	//测试添加病人信息
	@Test
	public void testInsertPatient(){
		PatientMapper mapper = applicationContext.getBean(PatientMapper.class);
		Patient patient = new Patient();
		patient.setName("王五");
		patient.setPhone("1351234578");
		mapper.insertPatient(patient);
	}
	
	//测试根据病人姓名进行模糊查询
	@Test
	public void testGetPatientByName(){
		PatientMapper mapper = applicationContext.getBean(PatientMapper.class);
		List<Patient> list = mapper.getPatientByName("李");
		for (Patient patient : list) {
			System.out.println(patient);
		}
	}

	//根据id修改病人信息
	@Test
	public void testUpdatePatientById(){
		PatientMapper mapper = applicationContext.getBean(PatientMapper.class);
		Patient patient = new Patient();
		patient.setId(1);
		patient.setName("刘备");
		patient.setPhone("13912345678");
		mapper.updatePatientById(patient);
	}
	
	//删除病人信息
	@Test
	public void testDelectPatientById(){
		PatientMapper mapper = applicationContext.getBean(PatientMapper.class);
		mapper.delectPatientById(4);
	}
}

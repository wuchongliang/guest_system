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
	
	//���Բ�ѯ������Ϣ
	@Test
	public void testGetPatientList(){
		PatientMapper mapper = applicationContext.getBean(PatientMapper.class);
		List<Patient> list = mapper.getPatientList();
		for (Patient patient : list) {
			System.out.println(patient); 
		}
	}
	
	//������Ӳ�����Ϣ
	@Test
	public void testInsertPatient(){
		PatientMapper mapper = applicationContext.getBean(PatientMapper.class);
		Patient patient = new Patient();
		patient.setName("����");
		patient.setPhone("1351234578");
		mapper.insertPatient(patient);
	}
	
	//���Ը��ݲ�����������ģ����ѯ
	@Test
	public void testGetPatientByName(){
		PatientMapper mapper = applicationContext.getBean(PatientMapper.class);
		List<Patient> list = mapper.getPatientByName("��");
		for (Patient patient : list) {
			System.out.println(patient);
		}
	}

	//����id�޸Ĳ�����Ϣ
	@Test
	public void testUpdatePatientById(){
		PatientMapper mapper = applicationContext.getBean(PatientMapper.class);
		Patient patient = new Patient();
		patient.setId(1);
		patient.setName("����");
		patient.setPhone("13912345678");
		mapper.updatePatientById(patient);
	}
	
	//ɾ��������Ϣ
	@Test
	public void testDelectPatientById(){
		PatientMapper mapper = applicationContext.getBean(PatientMapper.class);
		mapper.delectPatientById(4);
	}
}

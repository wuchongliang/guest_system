package cn.edu.gdaib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse.SmsSendDetailDTO;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import cn.edu.gdaib.domain.Securityroles;
import cn.edu.gdaib.mapper.SecurityrolesMapper;

public class SmsTest {
	
	public static void main(String[] args) throws Exception {
		smsTest();
		//searchSms();
		Date date = new Date();
		System.out.println(date);
		System.out.println(date.getHours());
	}
	
	
	
	public static void smsTest() throws Exception{
			
		//��ʼ��ascClient��Ҫ�ļ�������
		final String product = "Dysmsapi";//����API��Ʒ����(���Ų�Ʒ���ƹ̶��������޸�)
		final String domain = "dysmsapi.aliyuncs.com";//����API��Ʒ����(�ӿڵ�ַ�̶��������޸�)
		//���accessKeyId
		final String accessKeyId = "LTAISkKTzU1x4bHa";
		//���accessKeySecret
		final String accessKeySecret = "HmeqYM5jFbHPBDEUvnkzjQUXBDhxoo";
		//��ʼ��ascClient
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou" , product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		//��װ�������
		SendSmsRequest request = new SendSmsRequest();
		//ʹ��post�ύ
		request.setMethod(MethodType.POST);
		//����:�������ֻ��š�֧���Զ��ŷָ�����ʽ�����������ã���������Ϊ1000���ֻ�����,������������ڵ������ü�ʱ�������ӳ�,
		//��֤�����͵Ķ����Ƽ�ʹ�õ������õķ�ʽ
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		SecurityrolesMapper mapper = applicationContext.getBean(SecurityrolesMapper.class);
		Date date = new Date();
		List<Securityroles> list = null;
		if(date.getHours()>=8&&date.getHours()<=17){
			 list = mapper.getSecurityrolesList("0");
		}else if(date.getHours() >17 && date.getHours() <=23){
			 list = mapper.getSecurityrolesList("1");
		}else if(date.getHours() >0 && date.getHours() <8){
			 list = mapper.getSecurityrolesList("3");
		}
		
		for (Securityroles securityroles : list) {
			request.setPhoneNumbers(securityroles.getPhone());
			//����:����ǩ��-���ڶ��ſ���̨���ҵ�
			request.setSignName("�����");
			//����:����ģ��-���ڶ��ſ���̨���ҵ�
			request.setTemplateCode("SMS_132400429");
			//��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
			//������ʾ:���JSON����Ҫ�����з�,����ձ�׼��JSONЭ��Ի��з���Ҫ��,������������а���\r\n�������JSON����Ҫ��ʾ��\\r\\n,����ᵼ��JSON�ڷ���˽���ʧ��
			String name = securityroles.getName();
			String place = "�����¥��¥���ֱ�¥��";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��");
			
			String strDate = simpleDateFormat.format(date);
				
			request.setTemplateParam("{\"name\":\""+name+"\", \"time\":\""+strDate+"\",\"place\":\""+place+"\"}");
			//��ѡ-���ж�����չ��(��չ���ֶο�����7λ�����£������������û�����Դ��ֶ�)
			//request.setSmsUpExtendCode("90997");
			//��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
			//request.setOutId("yourOutId");
			//����ʧ���������ClientException�쳣
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			String code = sendSmsResponse.getCode();
			System.out.println(code);
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			//����ɹ�
				System.out.println("���ͳɹ�");
			}
		}
	}

	public static void searchSms() throws Exception{
		//��ͨ�Ų�Ʒ-����API�����Ʒ���ƣ����Ų�Ʒ���̶��������޸ģ�
        final String product = "Dysmsapi";
        //��ͨ�Ų�Ʒ-����API�����Ʒ�������ӿڵ�ַ�̶��������޸ģ�
        final String domain = "dysmsapi.aliyuncs.com";
        //�˴���Ҫ�滻�ɿ������Լ���AK��Ϣ
        
        final String accessKeyId = "LTAISkKTzU1x4bHa";
        final String accessKeySecret = "HmeqYM5jFbHPBDEUvnkzjQUXBDhxoo";
        //��ʼ��ascClient
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //��װ�������
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //����-����
        request.setPhoneNumber("13640224674");
        //��ѡ-���÷��Ͷ��Žӿ�ʱ���ص�BizId
        //request.setBizId("1234567^8901234");
        //����-���ŷ��͵����� ֧��30���ڼ�¼��ѯ���ɲ�����һ��ķ������ݣ�����ʽyyyyMMdd
        request.setSendDate("20180418");
        //����-ҳ��С
        request.setPageSize(10L);
        //����-��ǰҳ���1��ʼ����
        request.setCurrentPage(1L);
        //hint �˴����ܻ��׳��쳣��ע��catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
        //��ȡ���ؽ��
        List<SmsSendDetailDTO> smsSendDetailDTOs = querySendDetailsResponse.getSmsSendDetailDTOs();
        for (SmsSendDetailDTO smsSendDetailDTO : smsSendDetailDTOs) {
        	System.out.println(smsSendDetailDTO.getContent());
        }
       if(querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")){
        System.out.println("����ɹ�");
       }
	}
}

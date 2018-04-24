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
		//smsTest();
		//searchSms();
		Date date = new Date();
		System.out.println(date);
		System.out.println(date.getHours());  
	}
	
	
	
	public static void smsTest() throws Exception{
			
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称(短信产品名称固定，无需修改)
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名(接口地址固定，无需修改)
		//你的accessKeyId
		final String accessKeyId = "LTAISkKTzU1x4bHa";
		//你的accessKeySecret
		final String accessKeySecret = "HmeqYM5jFbHPBDEUvnkzjQUXBDhxoo";
		//初始化ascClient
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou" , product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		//组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		//使用post提交
		request.setMethod(MethodType.POST);
		//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,
		//验证码类型的短信推荐使用单条调用的方式
		
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
			//必填:短信签名-可在短信控制台中找到
			request.setSignName("吴崇亮");
			//必填:短信模板-可在短信控制台中找到
			request.setTemplateCode("SMS_132400429");
			//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			//友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
			String name = securityroles.getName();
			String place = "门诊大楼三楼左手边楼梯";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
			
			String strDate = simpleDateFormat.format(date);
				
			request.setTemplateParam("{\"name\":\""+name+"\", \"time\":\""+strDate+"\",\"place\":\""+place+"\"}");
			//可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
			//request.setSmsUpExtendCode("90997");
			//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			//request.setOutId("yourOutId");
			//请求失败这里会抛ClientException异常
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			String code = sendSmsResponse.getCode();
			System.out.println(code);
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			//请求成功
				System.out.println("发送成功");
			}
		}
	}

	public static void searchSms() throws Exception{
		//云通信产品-短信API服务产品名称（短信产品名固定，无需修改）
        final String product = "Dysmsapi";
        //云通信产品-短信API服务产品域名（接口地址固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";
        //此处需要替换成开发者自己的AK信息
        
        final String accessKeyId = "LTAISkKTzU1x4bHa";
        final String accessKeySecret = "HmeqYM5jFbHPBDEUvnkzjQUXBDhxoo";
        //初始化ascClient
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber("13640224674");
        //可选-调用发送短信接口时返回的BizId
        //request.setBizId("1234567^8901234");
        //必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
        request.setSendDate("20180418");
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);
        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
        //获取返回结果
        List<SmsSendDetailDTO> smsSendDetailDTOs = querySendDetailsResponse.getSmsSendDetailDTOs();
        for (SmsSendDetailDTO smsSendDetailDTO : smsSendDetailDTOs) {
        	System.out.println(smsSendDetailDTO.getContent());
        }
       if(querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")){
        System.out.println("请求成功");
       }
	}
}

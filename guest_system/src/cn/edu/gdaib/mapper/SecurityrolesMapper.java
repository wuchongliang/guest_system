package cn.edu.gdaib.mapper;

import java.util.List;

import cn.edu.gdaib.domain.Securityroles;

public interface SecurityrolesMapper {

	void insertSecurity(Securityroles securityroles);
	List<Securityroles> getSecurityrolesList(String duty_time);
}

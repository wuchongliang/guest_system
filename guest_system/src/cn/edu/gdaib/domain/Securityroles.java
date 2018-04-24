package cn.edu.gdaib.domain;

/**
 * 安保人员信息
 * @author Administrator
 *
 */
public class Securityroles {
	private long id;
	private String name;
	private String phone;
	private String duty_time;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDuty_time() {
		return duty_time;
	}
	public void setDuty_time(String duty_time) {
		this.duty_time = duty_time;
	}
	@Override
	public String toString() {
		return "Securityroles [id=" + id + ", name=" + name + ", phone=" + phone + ", duty_time=" + duty_time + "]";
	}
	
}

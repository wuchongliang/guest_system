package cn.edu.gdaib.domain;

/**
 * ≤°»À–≈œ¢pojo
 * @author Administrator
 *
 */
public class Patient {
	private long id;
	private String name;
	private String phone;
	private String number;
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
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", phone=" + phone + "]";
	}
	
	
}

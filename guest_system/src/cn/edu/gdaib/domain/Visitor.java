package cn.edu.gdaib.domain;

/**
 * ·Ã¿ÍÐÅÏ¢
 * @author Administrator
 *
 */
public class Visitor {
	
	private long id;
	private Integer times;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	@Override
	public String toString() {
		return "Visitor [id=" + id + ", times=" + times + "]";
	}
	
	
}

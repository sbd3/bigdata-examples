package spark.examples.vo;

import java.sql.Date;

public class DOB {

	private String name;
	private int id;
	private Date dob;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return "DOB [name=" + name + ", id=" + id + ", dob=" + dob + "]";
	}
}

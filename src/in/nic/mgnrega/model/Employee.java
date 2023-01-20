package in.nic.mgnrega.model;

import java.sql.Date;


public class Employee {

	private int eid;
	private int egpid;
	private int epid;
	private String ename;
	private String address;
	private Date date_joined;
	private int wage;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int eid, int egpid, int epid, String ename, String address, Date date_joined, int wage) {
		super();
		this.eid = eid;
		this.egpid = egpid;
		this.epid = epid;
		this.ename = ename;
		this.address = address;
		this.date_joined = date_joined;
		this.wage = wage;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public int getEgpid() {
		return egpid;
	}

	public void setEgpid(int egpid) {
		this.egpid = egpid;
	}

	public int getEpid() {
		return epid;
	}

	public void setEpid(int epid) {
		this.epid = epid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate_joined() {
		return date_joined;
	}

	public void setDate_joined(Date date_joined) {
		this.date_joined = date_joined;
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", egpid=" + egpid + ", epid=" + epid + ", ename=" + ename + ", address="
				+ address + ", date_joined=" + date_joined + ", wage=" + wage + "]";
	}
	
	
	
}

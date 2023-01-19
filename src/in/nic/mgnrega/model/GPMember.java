package in.nic.mgnrega.model;

public class GPMember {
	private int gpid;
	private String gname;
	private String gpanchayat;
	private String email;
	private String password;
	
	public GPMember() {
		// TODO Auto-generated constructor stub
	}

	public GPMember(int gpid, String gname, String gpanchayat, String email, String password) {
		super();
		this.gpid = gpid;
		this.gname = gname;
		this.gpanchayat = gpanchayat;
		this.email = email;
		this.password = password;
	}

	public int getGpid() {
		return gpid;
	}

	public void setGpid(int gpid) {
		this.gpid = gpid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGpanchayat() {
		return gpanchayat;
	}

	public void setGpanchayat(String gpanchayat) {
		this.gpanchayat = gpanchayat;
	}

	public String getEmail() {
		return email;
	}

	public void setPhone(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "GPMember [gpid=" + gpid + ", gname=" + gname + ", gpanchayat=" + gpanchayat + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	
}

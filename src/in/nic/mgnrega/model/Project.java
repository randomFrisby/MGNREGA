package in.nic.mgnrega.model;

public class Project {

	private int pid;
	private int pgpid;
	private String pname;
	private int budget;
	private int duration;
	
	public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project(int pid, int pgpid, String pname, int budget, int duration) {
		super();
		this.pid = pid;
		this.pgpid = pgpid;
		this.pname = pname;
		this.budget = budget;
		this.duration = duration;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPgpid() {
		return pgpid;
	}

	public void setPgpid(int pgpid) {
		this.pgpid = pgpid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Project [pid=" + pid + ", pgpid=" + pgpid + ", pname=" + pname + ", budget=" + budget + ", duration="
				+ duration + "]";
	}

	
	
	
	
}

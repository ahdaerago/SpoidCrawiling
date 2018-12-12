package dto;

public class PeopleDTO {
	private String movieCd;
	private String dept;
	private String pcode;
	private String profile;
	private String pname;
	private String eng_name;
	private String part;
	private String role;
	
	public PeopleDTO(){
		
	}
	
	public PeopleDTO(String movieCd, String dept, String pcode, String profile, String pname, String eng_name, String part,
			String role) {
		super();
		this.movieCd = movieCd;
		this.dept = dept;
		this.pcode = pcode;
		this.profile = profile;
		this.pname = pname;
		this.eng_name = eng_name;
		this.part = part;
		this.role = role;
	}
	
	public String getMovieCd() {
		return movieCd;
	}

	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}

	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getEng_name() {
		return eng_name;
	}
	public void setEng_name(String eng_name) {
		this.eng_name = eng_name;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "PeopleDTO [movieCd=" + movieCd + ", dept=" + dept + ", pcode=" + pcode + ", profile=" + profile
				+ ", pname=" + pname + ", eng_name=" + eng_name + ", part=" + part + ", role=" + role + "]";
	}

	
	
	
}

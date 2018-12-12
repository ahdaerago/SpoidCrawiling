package dto;


import org.jsoup.select.Elements;

public class DetailDTO {
	private String movieCd;
	private String kor_tit;
	private String eng_tit;
	private String poster;
	private String genre;
	private String nation;
	private String openDt;
	private String firstOpen;
	private String director;
	private String lead_role;
	private String grade;
	private String story;

	
	public DetailDTO() {
		
	}


	public DetailDTO(String movieCd, String kor_tit, String eng_tit, String poster, String genre, String nation,
			String openDt, String firstOpen, String director, String lead_role, String grade, String story) {
		super();
		this.movieCd = movieCd;
		this.kor_tit = kor_tit;
		this.eng_tit = eng_tit;
		this.poster = poster;
		this.genre = genre;
		this.nation = nation;
		this.openDt = openDt;
		this.firstOpen = firstOpen;
		this.director = director;
		this.lead_role = lead_role;
		this.grade = grade;
		this.story = story;
	}


	public String getMovieCd() {
		return movieCd;
	}


	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}


	public String getKor_tit() {
		return kor_tit;
	}


	public void setKor_tit(String kor_tit) {
		this.kor_tit = kor_tit;
	}


	public String getEng_tit() {
		return eng_tit;
	}


	public void setEng_tit(String eng_tit) {
		this.eng_tit = eng_tit;
	}


	public String getPoster() {
		return poster;
	}


	public void setPoster(String poster) {
		this.poster = poster;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getNation() {
		return nation;
	}


	public void setNation(String nation) {
		this.nation = nation;
	}


	public String getOpenDt() {
		return openDt;
	}


	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}


	public String getFirstOpen() {
		return firstOpen;
	}


	public void setFirstOpen(String firstOpen) {
		this.firstOpen = firstOpen;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getLead_role() {
		return lead_role;
	}


	public void setLead_role(String lead_role) {
		this.lead_role = lead_role;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getStory() {
		return story;
	}


	public void setStory(String story) {
		this.story = story;
	}


	@Override
	public String toString() {
		return "DetailDTO [movieCd=" + movieCd + ", kor_tit=" + kor_tit + ", eng_tit=" + eng_tit + ", poster=" + poster
				+ ", genre=" + genre + ", nation=" + nation + ", openDt=" + openDt + ", firstOpen=" + firstOpen
				+ ", director=" + director + ", lead_role=" + lead_role + ", grade=" + grade + ", story=" + story + "]";
	}



}

package dto;

public class BestDTO {
	private String movieCd;
	private String rcode;
	private String score;
	private String content;
	private String writer;
	private String goodcnt;
	private String regdate;
	
	public BestDTO() {
		
	}
	
	public BestDTO(String movieCd, String rcode, String score, String content, String writer,
			String goodcnt, String regdate) {
		super();
		this.movieCd = movieCd;
		this.rcode = rcode;
		this.score = score;
		this.content = content;
		this.writer = writer;
		this.goodcnt = goodcnt;
		this.regdate = regdate;
	}

	public String getMovieCd() {
		return movieCd;
	}

	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getGoodcnt() {
		return goodcnt;
	}

	public void setGoodcnt(String goodcnt) {
		this.goodcnt = goodcnt;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "bestDTO [movieCd=" + movieCd + ", rcode=" + rcode + ", score=" + score
				+ ", content=" + content + ", writer=" + writer + ", goodcnt=" + goodcnt + ", regdate=" + regdate + "]";
	}
	
	
	
	
	
	
	
	
}

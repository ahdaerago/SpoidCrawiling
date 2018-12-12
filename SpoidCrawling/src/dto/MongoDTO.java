package dto;

public class MongoDTO {
	private String code;
	private String review_code;
	private String title;
	private String review;
	private int score;
	private String writer;
	private String rdate;
	
	public MongoDTO() {
		super();
	}
	
	public MongoDTO(String code, String review_code, String title, String review, int score, String writer,
	String rdate) {
		super();
		this.code = code;
		this.review_code = review_code;
		this.title = title;
		this.review = review;
		this.score = score;
		this.writer = writer;
		this.rdate = rdate;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getReview_code() {
		return review_code;
	}
	
	public void setReview_code(String review_code) {
		this.review_code = review_code;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getReview() {
		return review;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getrdate() {
		return rdate;
	}
	
	public void setrdate(String rdate) {
		this.rdate = rdate;
	}
	
	@Override
	public String toString() {
	return "Mongo_DTO [code=" + code + ", review_code=" + review_code + ", title=" + title + ", review=" + review
	+ ", score=" + score + ", writer=" + writer + ", rdate=" + rdate + "]";
}




}

package dto;
public class BoxOfficeDTO {
	private  String showRange,
					targetDt,
					movieNm,
				  	rank,
				  	rankInten,
				  	rankOldAndNew,
				  	movieCd,
				  	openDt,
				  	salesAmt,
				  	salesShare,
				  	salesInten,
				  	salesChange,
				  	salesAcc,
				  	audiCnt,
				  	audiChange,
				 	audiAcc,
				 	yearWeekTime;
	public BoxOfficeDTO() {
		// TODO Auto-generated constructor stub
	}

	

	
	
	public BoxOfficeDTO(String showRange, String targetDt, String movieNm, String rank, String rankInten,
			String rankOldAndNew, String movieCd, String openDt, String salesAmt, String salesShare, String salesInten,
			String salesChange, String salesAcc, String audiCnt, String audiChange, String audiAcc) {
		super();
		this.showRange = showRange;
		this.targetDt = targetDt;
		this.movieNm = movieNm;
		this.rank = rank;
		this.rankInten = rankInten;
		this.rankOldAndNew = rankOldAndNew;
		this.movieCd = movieCd;
		this.openDt = openDt;
		this.salesAmt = salesAmt;
		this.salesShare = salesShare;
		this.salesInten = salesInten;
		this.salesChange = salesChange;
		this.salesAcc = salesAcc;
		this.audiCnt = audiCnt;
		this.audiChange = audiChange;
		this.audiAcc = audiAcc;
	}





	public BoxOfficeDTO(String showRange, String targetDt, String movieNm, String rank, String rankInten,
			String rankOldAndNew, String movieCd, String openDt, String salesAmt, String salesShare, String salesInten,
			String salesChange, String salesAcc, String audiCnt, String audiChange, String audiAcc,
			String yearWeekTime) {
		super();
		this.showRange = showRange;
		this.targetDt = targetDt;
		this.movieNm = movieNm;
		this.rank = rank;
		this.rankInten = rankInten;
		this.rankOldAndNew = rankOldAndNew;
		this.movieCd = movieCd;
		this.openDt = openDt;
		this.salesAmt = salesAmt;
		this.salesShare = salesShare;
		this.salesInten = salesInten;
		this.salesChange = salesChange;
		this.salesAcc = salesAcc;
		this.audiCnt = audiCnt;
		this.audiChange = audiChange;
		this.audiAcc = audiAcc;
		this.yearWeekTime = yearWeekTime;
	}





	public String getShowRange() {
		return showRange;
	}





	public void setShowRange(String showRange) {
		this.showRange = showRange;
	}





	public String gettargetDt() {
		return targetDt;
	}

	public void settargetDt(String targetDt) {
		this.targetDt = targetDt;
	}

	public String getMovieNm() {
		return movieNm;
	}
	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getRankInten() {
		return rankInten;
	}
	public void setRankInten(String rankInten) {
		this.rankInten = rankInten;
	}
	public String getRankOldAndNew() {
		return rankOldAndNew;
	}
	public void setRankOldAndNew(String rankOldAndNew) {
		this.rankOldAndNew = rankOldAndNew;
	}
	public String getMovieCd() {
		return movieCd;
	}
	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}
	public String getOpenDt() {
		return openDt;
	}
	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}
	public String getSalesAmt() {
		return salesAmt;
	}
	public void setSalesAmt(String salesAmt) {
		this.salesAmt = salesAmt;
	}
	public String getSalesShare() {
		return salesShare;
	}
	public void setSalesShare(String salesShare) {
		this.salesShare = salesShare;
	}
	public String getSalesInten() {
		return salesInten;
	}
	public void setSalesInten(String salesInten) {
		this.salesInten = salesInten;
	}
	public String getSalesChange() {
		return salesChange;
	}
	public void setSalesChange(String salesChange) {
		this.salesChange = salesChange;
	}
	public String getSalesAcc() {
		return salesAcc;
	}
	public void setSalesAcc(String salesAcc) {
		this.salesAcc = salesAcc;
	}
	public String getAudiCnt() {
		return audiCnt;
	}
	public void setAudiCnt(String audiCnt) {
		this.audiCnt = audiCnt;
	}
	public String getAudiChange() {
		return audiChange;
	}
	public void setAudiChange(String audiChange) {
		this.audiChange = audiChange;
	}
	public String getAudiAcc() {
		return audiAcc;
	}
	public void setAudiAcc(String audiAcc) {
		this.audiAcc = audiAcc;
	}

	public String getTargetDt() {
		return targetDt;
	}

	public void setTargetDt(String targetDt) {
		this.targetDt = targetDt;
	}

	public String getYearWeekTime() {
		return yearWeekTime;
	}

	public void setYearWeekTime(String yearWeekTime) {
		this.yearWeekTime = yearWeekTime;
	}
	
}

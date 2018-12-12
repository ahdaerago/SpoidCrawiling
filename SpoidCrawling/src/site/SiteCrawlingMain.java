package site;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* 제작자 : 장소희
 * 설명 : 네이버 및 다음 영화 정보 및 리뷰를 가지고 와서 mongodb에 저장하는 프로그램입니다.
 * 영화 정보는 네이버에서만 가지고 왔고, 2018년 영화만 가져오게 설정하였습니다.
 * 네이버에서는 영화정보와 리뷰를 가지고 왔고,
 * 다음 영화 사이트에서는 현재 상영작 및 박스오피스만 조회가 가능하고 나머지 영화는 검색을 해야 볼 수 있기 때문에
 * 다음 검색 api 를 이용하여 네이버에 있는 영화들을 조회하고 영화링크에 해당하는 url을 찾아
 * 접속하여 평점 및 기사정보를 수집하도록 하였습니다.
 * 
 */
public class SiteCrawlingMain {
	public static void main(String[] args) throws ParseException, IOException, org.json.simple.parser.ParseException {

		BoxOfficeParsing bop = new BoxOfficeParsing();
		// bop.startParsing();
		NaverMovie nm = new NaverMovie();
		nm.startCrawling();
	}
}

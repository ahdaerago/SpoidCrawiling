package crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dao.MongoDAO;
import dto.MongoDTO;

public class CrawlingMain {

	public static void main(String[] args) throws IOException {
		
		MongoDAO mDao1 = new MongoDAO();
		
		//mDao1.dropmongo();
		
		String url1 = "https://movie.naver.com/movie/running/current.nhn";
		Document naver_movies = Jsoup.connect(url1).get();

		Elements naver_url = naver_movies.select(".lst_detail_t1>li>.thumb>a");

		String dd = naver_url.text();
		System.out.println(dd+"?");
	
		for (Element element : naver_url) {
			String url = "https://movie.naver.com/" + element.attr("href");
			String code = url.substring(url.indexOf("=") + 1, url.length());
			System.out.println(code);
			String newest = mDao1.checkdnewest(code);
			String base_url = "https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=" + code
					+ "&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page=";

			// 1. url 검증단계

			String title_key = base_url.substring(64, 70);
			String title_url = "https://movie.naver.com/movie/bi/mi/point.nhn?code=" + code;
			Document title_doc = Jsoup.connect(title_url).get();
			Elements title_el = title_doc.select(".mv_info_area > .mv_info > h3 > a:first-child");
			String title = title_el.text();
			// 3. [관람객] 리뷰 데이터 수집
		
			movieCrawler(title, base_url, code, newest);
		}

	}

	// 메인메서드 끝
	public static void movieCrawler(String title, String base_url, String code,String newest) throws IOException {

		int page = 1;
		int i = 0;
		int sum = 0;
		int flag = 0; // 가장 마지막으로 가져왔던 값과 현재 코드를 비교하는 flag 0: 같지 않음 1: 같은게 있음
		String complete_url = base_url + page;
		
		
		
		while (true) {
			if(flag != 0) {
				break; // 같은게 있으면 해당 영화의 댓글 수집 정지!
			}
			Document doc = Jsoup.connect(complete_url).get();
			Elements reply_list = doc.select(".score_result > ul >li");
			Elements next_page = doc.select(".paging > div > a:last-child");

			String next = next_page.text();
			
			for (Element element : reply_list) {
				String grade = element.select(".star_score>em").text();
				String review = element.select(".score_reple>p").text();
				
				
				/*if (review2.length() <= 3) {
					continue;
				}*/
				/*if (review2.equals("관람객")) {
					review2 = review2.substring(0,2);
				}
				String review = review2.substring(0);*/
				
				int score = Integer.parseInt(grade);

				String writer = element.select(".score_reple>dl>dt>em:first-child").text();
				String rdate = element.select(".score_reple>dl>dt>em").last().text();
				
	
				
				
				Elements reviewcode1 = element.select(".score_reple em>a");
				String href_src = reviewcode1.attr("onclick");
				
				String review_code = href_src.substring(href_src.indexOf("(")+1, href_src.indexOf(","));
				if(newest.equals(review_code)) {
					System.out.println("마지막 수집날짜까지 왔습니다. 수집을 중단합니다.");
					break;
				}
				System.out.println("newest"+newest+"review_code"+review_code);	
				  System.out.println("============No" + i + "==============");
				  System.out.println("코드번호:" + code); 
				  System.out.println("댓글코드 : "+review_code);
				  System.out.println("제목:" + title);
				  System.out.println("리뷰:" + review);
				  System.out.println("별점:" + score);
				  System.out.println("작성자:" + writer); 
				  System.out.println("작성일자:" + rdate);
				 
				 MongoDTO mDto = new MongoDTO(code, review_code, title, review_code, score, writer, rdate);
				 MongoDAO mDao = new MongoDAO();
				 mDao.insetmongo(mDto);
				 
				 
				 
				 
				sum += score;
				i++;
			}
			if (!next.equals("다음")) {
				double avg = (sum) / (i);
				System.out.println("총" + page + "페이지의 " + i + "개의 데이터 DB저장");
				System.out.println(avg);
				System.out.println("평점평균: " + String.format("%.1f", avg));
				break;
			}
			page++;
			complete_url = base_url + page;
			
		}
	}
}
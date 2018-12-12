package site;

 

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dao.DaumDAO;
import dao.DaumDBDAO;
import dto.ReviewDTO;

 

public class DaumMovie {




	
	String base_url = "https://movie.daum.net/data/movie/search/v2/movie.json?size=20&searchText=";



	public void searchMoive(String title, String firstOpen,String mv_code) {

		// TODO Auto-generated method stub
		boolean exists = false;
		try {
			String query = URLEncoder.encode(title, "UTF-8");
			base_url = "https://movie.daum.net/data/movie/search/v2/movie.json?size=20&searchText="+query+"&start=1&sortType=acc";
			URL url = new URL(base_url);
		
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
	
			int movieIdult = conn.getResponseCode();
		
			BufferedReader br;
			if(movieIdult == 200) { // 정상호출
				System.out.println("정상적으로 호출됨");
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
				String s_json = br.readLine();
				System.out.println(s_json);
				JSONParser parser = new JSONParser();
				try {
					JSONObject jObj = (JSONObject)parser.parse(s_json);
					JSONArray jarr = (JSONArray)jObj.get("data");
					long movieId = 0;
					int num = 0;
					for (int i=0; i<jarr.size(); i++) {
						
						JSONObject jObj2 = (JSONObject)jarr.get(i);
						movieId = (long)jObj2.get("movieId");		
				
						exists = reviewCrawling(movieId,firstOpen,mv_code,title);
		
						if(exists == true) {
							System.out.println("해당 영화 수집 완료!");
							break;
						}
						
					}
	
					
			
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				System.out.println("실패");
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}

		

	}
	// 다음 영화 사이트 접속 및 리뷰 수집
	public boolean reviewCrawling(long movieId, String firstOpen,String movieCd,String title){
		// TODO Auto-generated method stub
		String url = "https://movie.daum.net/moviedb/main?movieId="+movieId;
		boolean exist = false; // 네이버와 다음의 영화가 둘 다 존재하는지에 대한 여부 false : 존재x true : 존재 
		int page = 1;
		int num = 0; // 댓글 카운트
		try {
			Document daum_doc = Jsoup.connect(url).get(); // 영화 상세 페이지로 연결
			
			Elements summary = daum_doc.select(".list_movie > dd.txt_main");
			System.out.println("서머리 : "+summary.size());
			if(summary.size()>1) {
				Element dt = summary.get(1); // 개봉날짜 가져오기
				String open = dt.text();
				open = open.substring(0,open.length()-3);
			
				if(open.contains(firstOpen)) { // 개봉 날짜와 네이버 개봉 날짜가 같으면 크롤링 시작
					
					String review_url = "https://movie.daum.net/moviedb/grade?movieId="+movieId+"&type=netizen&page="+page;
					
			
			
					dloop : while(true) { // 페이지가 끝일 때까지 돈다!
						Document review_doc = Jsoup.connect(review_url).get();
						Elements r_lst = review_doc.select(".list_review > li");
						if(r_lst.size()<=0) { // 리뷰 댓글 수가 한 페이지에 10개씩이므로 하나도 없으면 페이지가 끝난 것!
							System.out.println("총 "+ num+"개 댓글 수집완료");
							break; //빠져나감
						}
						//리뷰 댓글 내용 뽑기
						for (Element element : r_lst) {
							Elements rhref= element.select(".link_delete");
							String rcode = rhref.attr("onclick");
							rcode = rcode.substring(rcode.indexOf("(")+1, rcode.indexOf(")"));
							
							String score = element.select(".emph_grade").text();
							String content = element.select(".desc_review").text();
							String writer = element.select(".link_profile").text();
							String regdate = element.select(".info_append").text();
							
							ReviewDTO rDto = new ReviewDTO(movieCd, movieId, rcode, score, content, writer, regdate);
							DaumDAO dDao = new DaumDAO();
							boolean isexist =dDao.insertReview(rDto); //댓글 중복 수집하는가?
							if(isexist == true) {
								break dloop; // 리뷰 수집 빠져나가기
							}
							System.out.println("영화코드 : "+movieCd);
	 						System.out.println("영화아이디 : "+movieId);
							System.out.println(rcode+"댓글코드");
							System.out.println(score+"점");
							System.out.println(content+"내용");
							System.out.println(writer+"글쓴이");
							System.out.println(regdate+"등록");
							
							
						}
						page++; 
						review_url = "https://movie.daum.net/moviedb/grade?movieId="+movieId+"&type=netizen&page="+page;
					}
					
				}
			
			
			
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return exist;

	}


}


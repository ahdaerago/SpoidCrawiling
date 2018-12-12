package site;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dao.BoxOfficeDAO;
import dao.BoxOfficeDBDAO;
import dto.BoxOfficeDTO;

public class BoxOfficeParsing
{
  String json;
  public final static String KEY = "9629044bdff0a7b355154e5728062e99";
  Date date = new Date();
  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

  String yesterday = sdf.format(date.getTime() - 86400000L);
  
  public void startParsing() throws java.text.ParseException, IOException, ParseException {
		int flag = 0; // flag가 0이면 일일 , 1이면 주중
	    SimpleDateFormat original = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat new_format = new SimpleDateFormat("yyyyMMdd");
	    Date starts = new_format.parse("20180101"); 
	    Date today = new Date(); //오늘날짜
	    System.out.println(today);
	    Calendar start = Calendar.getInstance(); // 2018-01-01
	    Calendar end = Calendar.getInstance(); // 어제 날짜까지의 정보를 수집!
	    
	    start.setTime(starts);
	    
	    end.setTime(today);
	    end.add(Calendar.DAY_OF_MONTH, -1);
	    System.out.println("starts" +new_format.format(start.getTime()));
	    //20180101~현재까지의 박스오피스 1~10위까지 정보 적재 소스
	    while(end.compareTo(start)>=0) { // 1월1일까지
	    	flag = 0;
	    	String endDt = new_format.format(end.getTime());
	    	System.out.println("수집날짜====>"+endDt);
	    	boolean isExist = boxOfficeprasing(flag,endDt); // 
	    	end.add(Calendar.DAY_OF_MONTH, -1); // 하루씩 감소
	    /*	System.out.println(start.getTime());
	    	System.out.println("적재결과 : "+isExist);
	    	if(isExist == true) {
	    		break;
	    	}
	    */	
	    }
	    
	    end.setTime(today); // 2018-01-01로 초기화!
	    while(end.compareTo(start)>=0) { // 오늘날짜가 1월1일보다 크거나 작을 때까지 
	    	flag = 1;
	    	String endDt = new_format.format(end.getTime());
	    	System.out.println("수집날짜====>"+endDt);
	    	boolean isExist = boxOfficeprasing(flag,endDt); // 
	    	
	    	end.add(Calendar.DAY_OF_MONTH, -7); // 일주일씩 감소
	    	if(isExist == true) {
	    		break;
	    	}
	    }
  }

	public boolean boxOfficeprasing(int flag,String targetDt)
	    throws IOException, ParseException
	  {
		boolean isExist = false;
		String address = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=" + KEY + "&targetDt=" +targetDt;
	    String protocol = "GET";
	    String item_name = "dailyBoxOfficeList"; //0 : dailyBoxOfficeList 1 : weeklyBoxOfficeList
	    if(flag == 1) {
	    	address = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key="+ KEY + "&targetDt="+targetDt+"&weekGb=0";
	    	item_name = "weeklyBoxOfficeList";
	    }
	
	    
	    URL url = new URL(address);
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod(protocol);
	
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    
	    this.json = br.readLine();
	    System.out.println(this.json);
	
	    JSONParser parser = new JSONParser();
	    JSONObject obj = (JSONObject)parser.parse(json);
	    JSONObject channel = (JSONObject)obj.get("boxOfficeResult");
	    
	    JSONArray item = (JSONArray)channel.get(item_name);
	    String showRange = (String)channel.get("showRange");
	 
	    for (int i = 0; i < item.size();i++)
	    {
	  
	
	    	System.out.println(i+"번째 영화");
	    	JSONObject tmp = (JSONObject)item.get(i);
	    	String movieNm = (String)tmp.get("movieNm");
	    	System.out.println("조회날짜 ===>"+targetDt);
			String rank = (String)tmp.get("rank");
			String rankInten = (String)tmp.get("rankInten");
			String rankOldAndNew = (String)tmp.get("rankOldAndNew");
			String movieCd = (String)tmp.get("movieCd");
			String openDt = (String)tmp.get("openDt");
			String salesAmt = (String)tmp.get("salesAmt");
			String salesShare = (String)tmp.get("salesShare");
			String salesInten = (String)tmp.get("salesInten");
			String salesChange = (String)tmp.get("salesChange");
			String salesAcc= (String)tmp.get("salesAcc");
			String audiCnt= (String)tmp.get("audiCnt");
			String audiChange = (String)tmp.get("audiChange");
			String audiAcc = (String)tmp.get("audiAcc");
			
			BoxOfficeDBDAO bdb = new BoxOfficeDBDAO();
			
			if(flag == 0) {
				BoxOfficeDTO bDto = new BoxOfficeDTO(showRange, targetDt, movieNm, rank, rankInten, rankOldAndNew, movieCd, openDt, salesAmt, salesShare, salesInten, salesChange, salesAcc, audiCnt, audiChange, audiAcc);
				isExist=bdb.insertTrend(bDto);
				
			}else {
				String yearWeekTime = (String)channel.get("yearWeekTime");
				BoxOfficeDTO bDto = new BoxOfficeDTO(showRange, targetDt, movieNm, rank, rankInten, rankOldAndNew, movieCd, openDt, salesAmt, salesShare, salesInten, salesChange, salesAcc, audiCnt, audiChange, audiAcc, yearWeekTime);
				isExist=bdb.insertWeekly(bDto);
			}
			
			if(isExist == true) {
				break;
			}
			
			
			
	
	    }
	    return isExist;
	    
	    
	  }
  


}

package dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dto.BoxOfficeDTO;


public class BoxOfficeDAO {
	MongoClient mClient = new MongoClient("localhost",27017);
	public void insertTrend(BoxOfficeDTO mDto) {
		try {
			MongoDatabase trendDB = mClient.getDatabase("movie");
			MongoCollection<Document> collection = trendDB.getCollection("dailybox");
			Document doc = new Document("showRange",mDto.getShowRange())
					.append("targetDt",mDto.gettargetDt())
					.append("movieNm",mDto.getMovieNm())
					.append("rank", mDto.getRank())
					.append("rankInten",mDto.getRankInten())
					.append("rankOldAndNew",mDto.getRankOldAndNew())
					.append("movieCd",mDto.getMovieCd())
					.append("openDt", mDto.getOpenDt())
					.append("salesAmt", mDto.getSalesAmt())
					.append("salesShare",mDto.getSalesShare())
					.append("salesInten", mDto.getSalesInten())
					.append("salesChange", mDto.getSalesChange())
					.append("salesAcc",mDto.getSalesAcc())
					.append("audiCnt",mDto.getAudiCnt())
					.append("audiChange", mDto.getAudiChange())
					.append("audiAcc", mDto.getAudiAcc());
			collection.insertOne(doc);
			System.out.println("일일"+mDto.gettargetDt());			
		}catch (MongoWriteException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		mClient.close(); //연결해제 자원 반납
	}

	public void insertWeekly(BoxOfficeDTO mDto) {
		// TODO Auto-generated method stub
		MongoClient mClient = new MongoClient("localhost",27017);
		MongoDatabase trendDB = mClient.getDatabase("movie");
		MongoCollection<Document> collection = trendDB.getCollection("weeklybox");
		Document doc = new Document("showRange",mDto.getShowRange())
				.append("targetDt",mDto.gettargetDt())
				.append("yearWeekTime", mDto.getYearWeekTime())
				.append("movieNm",mDto.getMovieNm())
				.append("rank", mDto.getRank())
				.append("rankInten",mDto.getRankInten())
				.append("rankOldAndNew",mDto.getRankOldAndNew())
				.append("movieCd",mDto.getMovieCd())
				.append("openDt", mDto.getOpenDt())
				.append("salesAmt", mDto.getSalesAmt())
				.append("salesShare",mDto.getSalesShare())
				.append("salesInten", mDto.getSalesInten())
				.append("salesChange", mDto.getSalesChange())
				.append("salesAcc",mDto.getSalesAcc())
				.append("audiCnt",mDto.getAudiCnt())
				.append("audiChange", mDto.getAudiChange())
				.append("audiAcc", mDto.getAudiAcc());
		collection.insertOne(doc);
		System.out.println("주차"+mDto.gettargetDt());
		mClient.close(); //연결해제 자원 반납
	}
}

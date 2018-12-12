package dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dto.ReviewDTO;

public class DaumDAO {
	public boolean insertReview(ReviewDTO rDto) {
		boolean isExist = false;
		MongoClient mClient = new MongoClient("localhost",27017);
		try {
			MongoDatabase reviewDB = mClient.getDatabase("movie");
			MongoCollection<Document> collection = reviewDB.getCollection("daumreview");
			Document doc = new Document("movieCd",rDto.getMovieCd())
					.append("movieId",rDto.getMovieId())
					.append("rcode",rDto.getRcode())
					.append("score", rDto.getScore())
					.append("content",rDto.getContent())
					.append("writer",rDto.getWriter())
					.append("regdate",rDto.getRegdate());
			collection.insertOne(doc);
			System.out.println("movieId"+rDto.getMovieId()+rDto.toString());
		}catch (MongoWriteException e) { // 중복에러 
			// TODO: handle exception
			System.out.println("=====다음 댓글 수집 중복! 수집 중지=====");
			isExist = true;
			e.printStackTrace();
		}catch (MongoException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			mClient.close(); //연결해제 자원 반납		
			System.out.println("중지");
		}
		return isExist;
	}
}

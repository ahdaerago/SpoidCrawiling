package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import Common.DBManager;
import dto.DetailDTO;
import dto.PeopleDTO;
import dto.ReviewDTO;
import dto.BestDTO;
import site.NaverMovie;

public class NaverDAO {
	//상세정보 저장
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ArrayList<DetailDTO> list = new ArrayList<>();
	boolean isExist = false;
	public boolean insertDetail(DetailDTO dDto) {
		try {

			conn = DBManager.getConnection();
			String sql = "INSERT INTO detailMovie VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dDto.getMovieCd());
			pstmt.setString(2, dDto.getKor_tit());
			pstmt.setString(3, dDto.getEng_tit());
			pstmt.setString(4, dDto.getPoster());
			pstmt.setString(5, dDto.getGenre());
			pstmt.setString(6, dDto.getNation());
			pstmt.setString(7,dDto.getOpenDt());
			pstmt.setString(8, dDto.getFirstOpen());
			pstmt.setString(9, dDto.getDirector());
			pstmt.setString(10, dDto.getLead_role());
			pstmt.setString(11, dDto.getGrade());
			pstmt.setString(12, dDto.getStory());
			int result = pstmt.executeUpdate();
		}catch (SQLIntegrityConstraintViolationException e) { // 제약 조건 위배
			// TODO: handle exception
			System.out.println("세부정보 이미 수집 끝");
			isExist = true; //존재한다고 바꿔줌
			e.printStackTrace();	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isExist;
	}
	//사람 정보 저장
	public void insertPeople(PeopleDTO pDto) {
		
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO moviepeople VALUES(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pDto.getMovieCd());
			pstmt.setString(2, pDto.getDept());
			pstmt.setString(3, pDto.getPcode());
			pstmt.setString(4, pDto.getProfile());
			pstmt.setString(5, pDto.getPname());
			pstmt.setString(6, pDto.getEng_name());
			pstmt.setString(7, pDto.getPart());
			pstmt.setString(8, pDto.getRole());
			int result = pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	//리뷰 정보 저장
	public boolean insertReview(ReviewDTO rDto) {
		MongoClient mClient = new MongoClient("localhost",27017);
		
		try {
	
			MongoDatabase reviewDB = mClient.getDatabase("movie");
			MongoCollection<Document> collection = reviewDB.getCollection("naverreview");
			Document doc = new Document("movieCd",rDto.getMovieCd())
					.append("rcode",rDto.getRcode())
					.append("score", rDto.getScore())
					.append("content",rDto.getContent())
					.append("writer",rDto.getWriter())
					.append("regdate",rDto.getRegdate());
			collection.insertOne(doc);
			System.out.println("일일"+rDto.toString());			
		}catch (MongoWriteException e) { //중복값이 있으면 isExist를 true;
			// TODO: handle exception
			System.out.println("네이버 댓글 중복!!!! 댓글 크롤링 중지");
			isExist = true; // 댓글이 존재
			e.printStackTrace();
		}catch(MongoException e){
			System.out.println("몽고에러!!!");
			e.printStackTrace();
		}finally {
	
			mClient.close(); 
		}
	
		return isExist; //중복값 여부
	}
	

	
	// 베스트리뷰 정보 저장
	public void insertBest(BestDTO bstDto) {
		try {
			System.out.println("베스트 댓글 : " + bstDto.toString());
			conn = DBManager.getConnection();
			String sql = "INSERT INTO bestreview "
					   + "VALUES(?,?,?,?,?,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS')) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bstDto.getMovieCd());
			pstmt.setString(2, bstDto.getRcode());
			pstmt.setInt(3, Integer.parseInt(bstDto.getScore()));
			pstmt.setString(4, bstDto.getContent());
			pstmt.setString(5, bstDto.getWriter());
			pstmt.setInt(6, Integer.parseInt( bstDto.getGoodcnt()));
			pstmt.setString(7, bstDto.getRegdate());
			
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
}

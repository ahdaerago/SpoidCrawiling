package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Common.DBManager;
import dto.BoxOfficeDTO;

 
public class BoxOfficeDBDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public void insertTrend(BoxOfficeDTO mDto) {
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO  dailybox"
					   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mDto.getShowRange());
			pstmt.setString(2, mDto.getTargetDt());
			pstmt.setString(3, mDto.getMovieNm());
			pstmt.setString(4, mDto.getRank());
			pstmt.setString(5, mDto.getRankInten());
			pstmt.setString(6, mDto.getRankOldAndNew());
			pstmt.setString(7, mDto.getMovieCd());
			pstmt.setString(8, mDto.getOpenDt());
			pstmt.setString(9, mDto.getSalesAmt());
			pstmt.setString(10, mDto.getSalesShare());
			pstmt.setString(11, mDto.getSalesInten());
			pstmt.setString(12, mDto.getSalesChange());
			pstmt.setString(13, mDto.getSalesAcc());
			pstmt.setString(14, mDto.getAudiCnt());
			pstmt.setString(15, mDto.getAudiChange());
			pstmt.setString(16, mDto.getAudiAcc());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
			System.out.println("닫음!!!");
		}
	}
	

	public void insertWeekly(BoxOfficeDTO mDto) {
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO  weeklybox"
					   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mDto.gettargetDt());
			pstmt.setString(2, mDto.getYearWeekTime());
			pstmt.setString(3, mDto.getMovieNm());
			pstmt.setString(4, mDto.getRank());
			pstmt.setString(5, mDto.getRankInten());
			pstmt.setString(6, mDto.getRankOldAndNew());
			pstmt.setString(7, mDto.getMovieCd());
			pstmt.setString(8, mDto.getOpenDt());
			pstmt.setString(9, mDto.getSalesAmt());
			pstmt.setString(10, mDto.getSalesShare());
			pstmt.setString(11, mDto.getSalesInten());
			pstmt.setString(12, mDto.getSalesChange());
			pstmt.setString(13, mDto.getSalesAcc());
			pstmt.setString(14, mDto.getAudiCnt());
			pstmt.setString(15, mDto.getAudiChange());
			pstmt.setString(16, mDto.getAudiAcc());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
			System.out.println("닫음!!!");
		}
	}
}

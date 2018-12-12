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
	
	
	public boolean insertTrend(BoxOfficeDTO bDto) {
		boolean isExist = false;
		try {
			System.out.println(bDto.toString());
			conn = DBManager.getConnection();
			String sql = "INSERT INTO dailybox "
					   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bDto.getShowRange());
			pstmt.setString(2, bDto.getTargetDt());
			pstmt.setString(3, bDto.getMovieNm());
			pstmt.setString(4, bDto.getRank());
			pstmt.setString(5, bDto.getRankInten());
			pstmt.setString(6, bDto.getRankOldAndNew());
			pstmt.setString(7, bDto.getMovieCd());
			pstmt.setString(8, bDto.getOpenDt());
			pstmt.setString(9, bDto.getSalesAmt());
			pstmt.setString(10, bDto.getSalesShare());
			pstmt.setString(11, bDto.getSalesInten());
			pstmt.setString(12, bDto.getSalesChange());
			pstmt.setString(13, bDto.getSalesAcc());
			pstmt.setString(14, bDto.getAudiCnt());
			pstmt.setString(15, bDto.getAudiChange());
			pstmt.setString(16, bDto.getAudiAcc());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("===> 다음부터는 수집되어있음 일일 박스오피스 수집 종료!");
			isExist = true;
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
			System.out.println("닫음!!!");
		}
		return isExist;
	}
	

	public boolean insertWeekly(BoxOfficeDTO bDto) {
		boolean isExist = false;
		try {
			
			conn = DBManager.getConnection();
			String sql = "INSERT INTO weeklybox "
					   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bDto.gettargetDt());
			pstmt.setString(2, bDto.getYearWeekTime());
			pstmt.setString(3, bDto.getMovieNm());
			pstmt.setString(4, bDto.getRank());
			pstmt.setString(5, bDto.getRankInten());
			pstmt.setString(6, bDto.getRankOldAndNew());
			pstmt.setString(7, bDto.getMovieCd());
			pstmt.setString(8, bDto.getOpenDt());
			pstmt.setString(9, bDto.getSalesAmt());
			pstmt.setString(10, bDto.getSalesShare());
			pstmt.setString(11, bDto.getSalesInten());
			pstmt.setString(12, bDto.getSalesChange());
			pstmt.setString(13, bDto.getSalesAcc());
			pstmt.setString(14, bDto.getAudiCnt());
			pstmt.setString(15, bDto.getAudiChange());
			pstmt.setString(16, bDto.getAudiAcc());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("===> 다음부터는 수집되어있음 주말 박스오피스 수집 종료!");
			isExist = true;
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
			System.out.println("닫음!!!");
		}
		return isExist;
	}
}

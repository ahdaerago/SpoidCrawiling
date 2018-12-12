package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Common.DBManager;
import dto.ReviewDTO;

public class DaumDBDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void insertReview(ReviewDTO rDto) {
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO daumreview "
					   + "VALUES(?,?,?,?,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS')) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rDto.getMovieCd());
			pstmt.setString(2, rDto.getRcode());
			pstmt.setInt(3, Integer.parseInt(rDto.getScore()));
			pstmt.setString(4, rDto.getContent());
			pstmt.setString(5, rDto.getWriter());
			pstmt.setString(6, rDto.getRegdate());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
			System.out.println("닫음!!!");
		}
	}
}


package Common;
// 공통으로 사용하는 common으로 패키지 선언

import java.sql.*;

//싱글톤 패턴을 활용한 공유 방법
// 싱글콘패턴도 디자인패턴 중 하나로 static과 생성자를 이용
public class DBManager {
	// 생성자, static
	/*
	 * static 객체생성을 단 한 번만 실행하고 다른 클래스에서 사용할 때는 한 번 생성된 객체를 빌려다가 공유의 목적으로 사용하게 만드는
	 * 방법
	 */
	
	// 인스턴스의 주소를 저장하기 위한 참조변수
	private static Connection conn;
	
	//Connection 관련 정보 상수
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final static String USER = "java";
	private final static String PASSWORD = "1234";

	// 생성자를 private으로 만들어 은닉화 시킨다.
	// =>외부에서 인스턴스를 생성하지 못하도록 선언
	// =>이유 : 공유해서 사용하려고 하는데 다른 개발자가 객체 생성해서 사용하면 공유의 의미가 없음
	// (사용자의 실수를 막는 용도)
	// 다른 클래스에서 이 생성자를 쓰게 하는 걸 막는다.
	private DBManager() {
		// TODO Auto-generated constructor stub
	}

	// 인스턴스를 생성 또는 기존 인스턴스를 반환하는 메서드
	public static Connection getConnection() {
		conn = null;

		if (conn == null) {
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	// SELECT 수행 후 자원 해제를 위한 메서드(오버로딩 : 메소드 이름은 동일 but 매개변수는 다르다)
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// update, delete, insert 수행 후 자원 해제를 위한 메서드
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

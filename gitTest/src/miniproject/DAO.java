package miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {

	// 필드
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// conn
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String user = "campus_22IS_BIG2_mini_4";// db계정 유저 이름
			String password = "smhrd4";
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			conn = DriverManager.getConnection(url, user, password);

			if (conn != null) {
				System.out.println("연결 성공");
			} else {
				System.out.println("연결 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 회원가입
	public void insertUser() {
		Scanner scan = new Scanner(System.in);
		getConn();
		String sql = "insert into member values (?,?)";

		try {
			psmt = conn.prepareStatement(sql);

			System.out.println("아이디를 입력하세요.");
			String MEMBER_ID = scan.next();
			System.out.println("패스워드를 입력하세요.");
			String MEMBER_PW = scan.next();

			psmt.setString(1, MEMBER_ID);
			psmt.setString(2, MEMBER_PW);

			int row = psmt.executeUpdate();

			if (row > 0) {
				System.out.println("insert 완료");
			} else {
				System.out.println("insert 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 

		// 6번 연결 끊기
		// commit이 됨!!
	}

	// ranking 넣기
	public void insertRanking(String NAME, String MAX_FISH_NAME, double MAX_FISH_SIZE) {
		getConn();
		String sql = "insert into RANKING values (?,?,?)";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, NAME);
			psmt.setString(2, MAX_FISH_NAME);
			psmt.setDouble(3, MAX_FISH_SIZE);

			int row = psmt.executeUpdate();

			if (row > 0) {
				System.out.println("insert 완료");
			} else {
				System.out.println("insert 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	// user별 랭킹 불러오기
	public void rank() {

		getConn();
		try {

			String sql = "select * from RANKING";
			psmt = conn.prepareStatement(sql);
			// select 할 때만 달라지는 부분!!
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				String NAME = rs.getString(1);
				String MAX_FISH_NAME = rs.getString(2);
				String MAX_FISH_SIZE = rs.getString(3);

				System.out.println(NAME);
				System.out.println(MAX_FISH_NAME);
				System.out.println(MAX_FISH_SIZE);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 물고기 전체 목록 불러오기
	public ArrayList<FishDTO> fishAll() {
		ArrayList<FishDTO> array = new ArrayList<>();
		getConn();
		try {

			String sql = "select * from STAGE";
			psmt = conn.prepareStatement(sql);
			// select 할 때만 달라지는 부분!!
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				String STAGE_NAME = rs.getString(1);
				double STAGE_FISH_SIZE = rs.getDouble(2);
				int STAGE_FISH_LEVEL = rs.getInt(3);
				int STAGE_FISH_INDEX = rs.getInt(4);
				array.add(new FishDTO(STAGE_NAME, STAGE_FISH_SIZE, STAGE_FISH_LEVEL, STAGE_FISH_INDEX));
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return array;
	}

}

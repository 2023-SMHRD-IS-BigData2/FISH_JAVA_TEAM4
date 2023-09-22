package miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

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
		String sql = "insert into MEMBER values(?,?)";

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
				e.printStackTrace();
			}

		} 

	}

	// 로그인
	public String userLogin() {
		getConn();
		// sql문 통로
		String sql = "select * from MEMBER";
		PreparedStatement psmt;
		String inputId = null;

		try {
			psmt = conn.prepareStatement(sql);
			// sql 실행
			ResultSet rs = psmt.executeQuery();
			boolean run = true;
			while (run) {
				Scanner scan = new Scanner(System.in);
				System.out.println("아이디를 입력하세요 >>");
				inputId = scan.next();
				System.out.println("비밀번호를 입력하세요 >>");
				String inputPw = scan.next();
				boolean isLogin = true;
				
				while (rs.next()) {
					if (inputId.equals(rs.getString(1)) && inputPw.equals(rs.getString(2))) {
						System.out.println("로그인이 성공했습니다.");
						isLogin = false;
						run =false;
					}
				}
				if (isLogin) {
					System.out.println("로그인이 실패했습니다.");
					isLogin=true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inputId;
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

			String sql = "select * from RANKING ORDER BY RANKING_MAX_SIZE DESC";
			psmt = conn.prepareStatement(sql);
			// select 할 때만 달라지는 부분!!
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				String NAME = rs.getString(1);
				String MAX_FISH_NAME = rs.getString(2);
				String MAX_FISH_SIZE = rs.getString(3);

				System.out.print(NAME+" ");
				System.out.print(MAX_FISH_NAME+" ");
				System.out.print(MAX_FISH_SIZE);
				System.out.println();

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

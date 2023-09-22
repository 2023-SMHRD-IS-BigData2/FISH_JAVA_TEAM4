package miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {

	// 필드
	private Connection Conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// conn
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:project-db-campus.smhrd.com:1524:xe";
			String user = "campus_22IS_BIG2_mini_4";
			String password = "smhrd4";

			try {
				Conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (Conn != null) {
				System.out.println("connect success");
			} else {
				System.out.println("connect fail");
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

	// insert 회원가입
	public int makeUser
	{
		getConn();

		// sql통로
		String sql = "insert into member values(?,?)";
		int row = 0;
		try {
			psmt = Conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());

			// sql실행
			row = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return row;
	}

	// insert 랭킹 넣는거
	public int getjoin{

	getConn();

	// sql통로
	String sql = "insert into member values(?,?)";
	int row = 0;try
	{
		psmt = Conn.prepareStatement(sql);
		psmt.setString(1, dto.getId());
		psmt.setString(2, dto.getPw());

		// sql실행
		row = psmt.executeUpdate();

	}catch(SQLException e)
	{		e.printStackTrace();
	}finally{
		getClose();
	}
	return row;

	// select user별로 랭킹 불러오기
	public selUserRank() {
				getConn();
				ArrayList<MemberDTO> list_dto = new ArrayList<>();

				MemberDTO mdto = null;
				String sql = "select * from member";
				try {
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();

					while (rs.next()) {
						String id = rs.getString(1);
						String pw = rs.getString(2);
						String name = rs.getString(3);
						int age = rs.getInt(4);

						mdto = new MemberDTO(id, pw, name, age);
						list_dto.add(mdto);

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					getClose();
				}
				return list_dto;
			}

	// select 물고기 전체 목록 불러오기
	public selfishAll() {
				getConn();

	ArrayList<MemberDTO> list_dto = new ArrayList<>();

	MemberDTO mdto = null;
	String sql = "select * from miniproject where STAGE = ST1_FISH_NAME ";try
	{
		psmt = Conn.prepareStatement(sql);
		rs = psmt.executeQuery();

		while (rs.next()) {
			String id = rs.getString(1);
			String pw = rs.getString(2);
			String name = rs.getString(3);
			int age = rs.getInt(4);

			mdto = new MemberDTO(id, pw, name, age);
			list_dto.add(mdto);

		}
	}catch(
	SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		getClose();
	}return list_dto;

	}

	// close
	private void getClose() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (Conn != null)
				Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

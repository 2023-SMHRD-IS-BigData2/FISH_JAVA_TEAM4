package miniproject;

import java.util.Scanner;

public class Main_View {

	public static void main(String[] args) {
		DAO mdao = new DAO();
		Scanner sc = new Scanner(System.in);
		Controller fs = new Controller();
		while (true) {
			System.out.println("1. 회원가입 2. 로그인&게임시작 3. 랭킹확인 4. 게임종료");
			String select = sc.next();
			// 1. 회원가입
			if (select.equals("1")) {
				mdao.insertUser();
			} else if (select.equals("2")) {
				// 2. 로그인&게임시작
				String user = mdao.userLogin();
				int stage = fs.getStage(user);
				fs.fishing(stage);
				fs.finalResult();
			} else if (select.equals("3")) {
				// 3. 랭킹확인
				fs.printrank();

			} else if (select.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}

	}

}

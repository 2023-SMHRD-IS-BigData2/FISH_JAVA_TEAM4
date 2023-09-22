package miniproject;
public class Main_View {

	public static void main(String[] args) {

		
		DAO mdao = new DAO();
		//1. 회원가입
		mdao.insertUser();
		//2. 로그인
		String user = mdao.userLogin();
		Controller fs = new Controller();
		int stage = fs.getStage();
		fs.fishing(stage);
		fs.finalResult(user);
		//3. 랭킹확인
		fs.printrank();
		 
		  

	}

}


package miniproject;
public class Main_View {

	public static void main(String[] args) {

		
		DAO mdao = new DAO();
		mdao.insertUser();
		String user = mdao.userLogin();
		Controller fs = new Controller();
		int stage = fs.getStage();
		fs.fishing(stage);
		fs.finalResult(user);
		fs.printrank();
		 
		  

	}

}


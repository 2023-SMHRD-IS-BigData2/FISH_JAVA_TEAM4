package miniproject;

public class Main {

	public static void main(String[] args) {

		DAO mdao = new DAO();
		mdao.insertUser();
		mdao.userLogin();
		Controller fs = new Controller();
		int stage = fs.getStage();
		fs.fishing(stage);
		fs.finalResult();
		fs.printrank();

	}

}

package miniproject;
public class Main {

	public static void main(String[] args) {
		Controller fs = new Controller();
		int stage = fs.getStage();
		fs.fishing(stage);
		fs.finalResult();
		fs.printrank();
		
		  
	}

}

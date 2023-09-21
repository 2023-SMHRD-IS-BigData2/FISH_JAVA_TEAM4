package miniproject;
import java.util.Random;
import java.util.Scanner;

public class Fishing {
	private Scanner sc = new Scanner(System.in);
	private Random rd = new Random();
	private double max; 
	
	public int getBait(){
		System.out.println("미끼를 선택하세요");
		int bait = sc.nextInt();
		if(bait == 1) {
			System.out.println("1번 미끼입니다. 작습니다");
		}else if(bait == 2) {
			System.out.println("2번 미끼입니다. 중간크기입니다");
		}else if(bait == 3) {
			System.out.println("3번 미끼입니다. 큽니다");
		}
		return bait;
		
		
	}
	 

	public void fishing() {
		int baittype = getBait();
		int baitcnt = 50;
		while (true) {
			System.out.println("남은 미끼 수 : "+baitcnt);
			int timer = rd.nextInt(5)+5;
			long now = System.currentTimeMillis();	
			long currentTime = 0;
			System.out.println("타이머 : " +timer);

			// 밀리세컨드라 000빼고 계산 : 30초
			while (currentTime - now < timer*1000) {
				currentTime = System.currentTimeMillis();
			}
			int fishSize = rd.nextInt(3)+1;
			if(fishSize == 1) {
				System.out.println("!");
			}else if(fishSize==2) {
				System.out.println("!!");
			}else if(fishSize == 3) {
				System.out.println("!!!");
			}
			now = System.currentTimeMillis();
			int cnt = 0;
			while(currentTime - now < 10000) {
				currentTime = System.currentTimeMillis();
				String getFish = sc.next();
				if(getFish != null) {
					cnt++;
				}
				for(int i = 0; i<cnt; i++) {
					System.out.print("■");
				}
				if(cnt == 10) {
					break;
				}
			}
			long getTime = System.currentTimeMillis();
			if(cnt >= 10  && getTime - now <3000) {
				System.out.println("낚싯대를 잡았습니다!");
				baitcnt--;
				fightFish();
			}else {
				System.out.println("놓쳤습니다...");
				baitcnt--;
			}
		}
	}
	
	public void fightFish() {
		resultFish();
	}
	
	public void resultFish(){
		String getFishname = "";
		double getFishsize = 0;
		double resultSize = getFishsize + rd.nextDouble()*10;
		String result = String.format("%.3f", resultSize);
		System.out.println("잡은 물고기는 "+getFishname+"이었습니다");
		System.out.println("잡은 물고기 크기는"+result+"cm입니다");
		if (Double.valueOf(result)>max) {
			max = Double.valueOf(result);
		}
		System.out.println("현재 최대 물고기 크기는" +max+"cm입니다.");
	}
		
}

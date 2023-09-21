package miniproject;
import java.util.Random;
import java.util.Scanner;

public class Fishing {
	private Random rd = new Random();
	private double max; 
	private Scanner sc = new Scanner(System.in);
	
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
			System.out.println("키를 연타해서 물고기를 잡으세요!");
			now = System.currentTimeMillis();
			int cnt = 0;
			while(currentTime - now < 5000) {
				currentTime = System.currentTimeMillis();
				
				String getFish = sc.next();
				if(getFish != null) {
					cnt++;
				}
				for(int i = 0; i<cnt; i++) {
					System.out.print("■");
				}
				if(cnt >7) {
					System.out.println("거의 다 잡았어요!");
				}
				if(cnt == 10) {
					break;
				}
			}
			System.out.println();
			if(cnt >= 10) {
				System.out.println("낚싯대를 잡아 올렸습니다!");
				System.out.println("낚싯대를 잡아 올렸습니다!");
				System.out.println("낚싯대를 잡아 올렸습니다!");
				System.out.println("낚싯대를 잡아 올렸습니다!");
				System.out.println("낚싯대를 잡아 올렸습니다!");
				baitcnt--;
				fightFish();
			}else {
				System.out.println("놓쳤습니다...");
				baitcnt--;
				now = System.currentTimeMillis();	
				currentTime = 0;
				while (currentTime - now < 5000) {
					currentTime = System.currentTimeMillis();
				}
			}
			cnt=0;
		
		}
	}
	
	public void fightFish() {
		long now = System.currentTimeMillis();	
		long currentTime = 0;
		while (currentTime - now < 3000) {
			currentTime = System.currentTimeMillis();
		}
		int fishsize = 1;
		System.out.println("물고기를 낚아챌 방법을 고르세요.");
		System.out.println("1. 섬세한 낚아채기 2. 강력한 낚아채기");
		String catching = sc.next();
		if((catching.equals("1") && fishsize==1)||(catching.equals("2") && (fishsize==2||fishsize==3)))	{
			System.out.println("물고기를 낚았습니다!");
			resultFish();	
		}else{
			System.out.println("놓쳤습니다....");
			
		}
		
		
		
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
		System.out.println("로딩중...");
		long now = System.currentTimeMillis();	
		long currentTime = 0;
		while (currentTime - now < 5000) {
			currentTime = System.currentTimeMillis();
		}
	}
		
}

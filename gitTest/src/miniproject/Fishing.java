package miniproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Fishing {
	private Random rd = new Random();
	private double max_size;
	private String max_name;
	private Scanner sc = new Scanner(System.in);
	private ArrayList<FishDTO> todayGet = new ArrayList<>();
	private DAO dao = new DAO();

//	 스테이지 선택 (변경 가능성 높음)
	public int getStage() {
		System.out.println("스테이지를 선택하세요");
		int stage = sc.nextInt();
		if (stage == 1) {
			System.out.println("스테이지 1 입니다");
		} else if (stage == 2) {
			System.out.println("스테이지 2 입니다");
		} else if (stage == 3) {
			System.out.println("스테이지 3 입니다");
		}

		return stage;

	}

	// 낚시 시작후 낚싯대 잡아채는 과정
	public void fishing(int stage) {
		int baitcnt = stage * 1;// 남은 미끼 수
		while (true) {
			if (baitcnt == 0) {
				break;
			}
			System.out.println("남은 미끼 수 : " + baitcnt);

			// 타이머
			// timer 값에 따라 정해진 시간만큼 지연된 후에 다음 코드가 출력됨
			int timer = rd.nextInt(1) + 5;
			long now = System.currentTimeMillis();
			long currentTime = 0;
			System.out.println("타이머 : " + timer);

			// 밀리세컨드라 000빼고 계산 : 30초
			while (currentTime - now < timer * 1000) {
				currentTime = System.currentTimeMillis();
			}

			// 타이머 끝

			// 물고기 크기에 따라 느낌표 수가 다르게 표시했음
			// 물고기 낚이는 크기는 db와 연동할 예정
			int rdfish = 0;
			if (stage == 1) {
				rdfish = rd.nextInt(9);
			} else if (stage == 2) {
				rdfish = rd.nextInt(9) + 10;
			} else {
				rdfish = rd.nextInt(10) + 19;
			}
			FishDTO newfish = dao.fishAll().get(rdfish);
			if (newfish.getLevel() == 1) {
				System.out.println("!");
			} else if (newfish.getLevel() == 2) {
				System.out.println("!!");
			} else if (newfish.getLevel() == 3) {
				System.out.println("!!!");
			}

			// 물고기 잡아채는 과정
			System.out.println("스페이스 키를 연타해서 물고기를 잡으세요!");
			now = System.currentTimeMillis();
			int cnt = 0;

			// while문 안의 조건(5초가 넘어가면 반복문이 끝남)
			while (currentTime - now < (5 - stage) * 1000) {
				currentTime = System.currentTimeMillis();

				// 입력값이 스페이스바나 null이 아니면 cnt 값이 올라감

				// 아스키아트 삽입 지점(찌 왔다갔다)
				// cnt 값에 따라서 홀짝 마다 다른 그림 뜨게
				String getFish = sc.nextLine();
				if (getFish != null) {
					cnt++;
				}
				for (int i = 0; i < cnt; i++) {
					System.out.print("■");
				}
				// cnt값이 올라서 최대치까지 올라가기 전에 메세지 출력
				if (cnt > 7) {
					System.out.println("거의 다 잡았어요!");
				}
				// 10이면 물고기 낚시 성공
				if (cnt == 10) {
					break;
				}
			}
			System.out.println();
			if (cnt >= 10) {
				// 낚시 성공시 출력될 문장
				System.out.println("낚싯대를 잡아챘습니다!");
				if(newfish.getLevel()==1) {
					System.out.println("입질이 작습니다!");
				}else if(newfish.getLevel()==2) {
					System.out.println("적당한 입질입니다!!");
				}else {
					System.out.println("큰 입질입니다!!!");
				}
				baitcnt--;
				now = System.currentTimeMillis();
				currentTime = 0;
				while (currentTime - now < 500) {
					currentTime = System.currentTimeMillis();
				}
				fightFish(newfish);
			} else {
				// 낚시 실패시 출력될 문장
				System.out.println("놓쳤습니다...");
				baitcnt--;
				// 다음 낚시까지 잠시 지연됨
				now = System.currentTimeMillis();
				currentTime = 0;
				while (currentTime - now < 5000) {
					currentTime = System.currentTimeMillis();
				}
			}
			cnt = 0;

		}

	}

	// 낚싯대를 잡아채는 방법과 낚인 물고기 레벨에 따라 낚시 성공 실패를 가를 예정
	public void fightFish(FishDTO newfish) {
		long now = System.currentTimeMillis();
		long currentTime = 0;
		while (currentTime - now < 1000) {
			currentTime = System.currentTimeMillis();
		}
		// 입질때 느낌표 개수에 따라서 fishsize가 변경됨
		// 작은 물고기는 섬세한 낚아채기, 큰 물고기는 강력한 낚아채기가 필요
		while (true) {
			System.out.println("물고기를 낚아챌 방법을 고르세요.");
			System.out.println("1. 섬세한 낚아채기 2. 강력한 낚아채기");
			String catching = sc.next();
			if ((catching.equals("1") && newfish.getLevel() == 1)
					|| (catching.equals("2") && (newfish.getLevel() == 2 || newfish.getLevel() == 3))) {
				System.out.println("물고기를 낚았습니다!");
				resultFish(newfish);
				break;
			} else if (!catching.equals("1") && !catching.equals("2")) {
				System.out.println("잘못 입력하셨습니다");

			} else {
				System.out.println("물고기가 도망갔습니다...");
				break;
			}
		}

	}

	// 낚시 결과물
	public void resultFish(FishDTO newfish) {

		String getFishname = newfish.getName();
		double getFishsize = newfish.getSize();
		double resultSize = getFishsize + rd.nextDouble() * 10;
		String result = String.format("%.2f", resultSize);
		System.out.println("잡은 물고기는 " + getFishname + "이었습니다");
		System.out.println("잡은 물고기 크기는" + result + "cm입니다");
		FishDTO fs = new FishDTO(getFishname, getFishsize);
		todayGet.add(fs);
		if (Double.valueOf(result) > max_size) {
			max_size = Double.valueOf(result);
			max_name = getFishname;
		}
		System.out.println("로딩중...");
		long now = System.currentTimeMillis();
		long currentTime = 0;
		while (currentTime - now < 2000) {
			currentTime = System.currentTimeMillis();
		}
		while (true) {
			System.out.println("1. 물고기 더 낚기 2. 지금까지 낚은 물고기 확인하기");
			int select = sc.nextInt();
			if (select == 2) {
				icebox();
				break;
			} else if (select == 1) {
				break;
			} else {
				System.out.println("잘못입력하셨습니다");
			}
		}

	}

	public void icebox() {
		System.out.println("지금까지 잡은 물고기 목록입니다.");
		for (int i = 0; i < todayGet.size(); i++) {
			System.out.println(todayGet.get(i).getName());
		}

	}

	public void finalResult() {
		System.out.println("오늘 잡은 물고기 목록입니다.");
		for (int i = 0; i < todayGet.size(); i++) {
			System.out.println(todayGet.get(i).getName());
		}
		String testuser = "홍길동";
		System.out.println();
		System.out.println("오늘 잡은 최대 물고기는 " + max_name + "입니다");
		System.out.println("오늘 잡은 최대 물고기 크기는 " + max_size + "cm입니다.");
		dao.insertRanking(testuser, max_name, max_size);
	}

	public void printrank() {
		dao.rank();
	}

}

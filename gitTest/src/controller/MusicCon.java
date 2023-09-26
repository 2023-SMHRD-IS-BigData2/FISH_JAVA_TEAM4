package controller;

import javazoom.jl.player.MP3Player;
import view.MusicVO;

public class MusicCon {

	private MP3Player player;
	private MusicVO[] musics;

	public MusicCon() {
		player = new MP3Player();
		musics = new MusicVO[9];
		 
 
		musics[0] = new MusicVO("타이틀", "시스템", "./노라조_고등어.mp3", 195);
		musics[1] = new MusicVO("낚음", "시스템", "./낚음.mp3", 4);
		musics[2] = new MusicVO("미끼_물었을때", "노라조", "./미끼물었을때.mp3", 1);
		musics[3] = new MusicVO("놓쳤을때", "시스템", "./놓쳤을때.mp3", 3);
		musics[4] = new MusicVO("스테이지1", "시스템", "./스테이지1.mp3", 8);
		musics[5] = new MusicVO("스테이지2", "시스템", "./스테이지2.mp3", 8);
		musics[6] = new MusicVO("스테이지3", "시스템", "./스테이지3.mp3", 9);
		musics[7] = new MusicVO("낚시도중", "시스템", "./낚시도중.mp3", 148);
		musics[8] = new MusicVO("입질왔음", "시스템", "./입질왔음.mp3", 336);
		
		 
	}

	public void play(int number) {

		if(player.isPlaying()) { //현재 노래 재생중이면
			player.stop();// 중지
		}
		player.play(musics[number].getRoute());
	}// play 메소드 끝
	
	public void stop() {
		player.stop();
	}

}


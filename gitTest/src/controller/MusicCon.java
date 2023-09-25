package controller;

import javazoom.jl.player.MP3Player;
import view.MusicVO;

public class MusicCon {

	private MP3Player player;
	private MusicVO[] musics;

	public MusicCon() {
		player = new MP3Player();
		musics = new MusicVO[4];
		
 
		musics[0] = new MusicVO("타이틀", "시스템", "./타이틀.mp3", 8);
		musics[1] = new MusicVO("낚음", "시스템", "./낚음.mp3", 4);
		musics[2] = new MusicVO("미끼_물었을때", "노라조", "./미끼물었을때.mp3", 1);
		musics[3] = new MusicVO("놓쳤을때", "시스템", "./놓쳤을때.mp3", 3);
	}

	public void play(int number) {

		if(player.isPlaying()) { //현재 노래 재생중이면
			player.stop();// 중지
		}
		player.play(musics[number].getRoute());
	}// play 메소드 끝
	

}


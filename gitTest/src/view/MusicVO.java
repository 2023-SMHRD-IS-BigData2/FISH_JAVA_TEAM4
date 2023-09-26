package view;

public class MusicVO {
	private String title;
	private String singer;
	private String route;
	private int musicLength;

	public String getTitle() {
		return title;
	}

	public String getSinger() {
		return singer; 
	}

	public String getRoute() {
		return route;
	}

	public int getMusicLength() {
		return musicLength;
	}

	public MusicVO(String title, String singer, String route, int musicLength) {
		super();
		this.title = title;
		this.singer = singer;
		this.route = route;
		this.musicLength = musicLength;
	}

	public String toString() {
		return "제목 : " + title + ", 가수 : " + singer + ", 경로 : " + route + ", 재생시간 : " + musicLength + "]";
	}
}

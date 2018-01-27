

import java.util.Date;

/**
 * 歌故�?实体信息
 * @author Administrator
 *
 */
public class MusicStoryEntity {
	
	private long id;
	private String irc;
	private String motion;
	private Date createdAt;
	private String place;
	private int background;
	private String music;
	private String singer;
	
	private UserEntity user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIrc() {
		return irc;
	}
	public void setIrc(String irc) {
		this.irc = irc;
	}
	public String getMotion() {
		return motion;
	}
	public void setMotion(String motion) {
		this.motion = motion;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getBackground() {
		return background;
	}
	public void setBackground(int background) {
		this.background = background;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	
}

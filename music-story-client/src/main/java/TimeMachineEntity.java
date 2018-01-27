

import java.util.Date;

/**
 * 时光机信�?
 * @author Administrator
 *
 */
public class TimeMachineEntity {
	
	private long id;
	private Date createdAt;
	private MusicStoryEntity story;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public MusicStoryEntity getStory() {
		return story;
	}
	public void setStory(MusicStoryEntity story) {
		this.story = story;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}

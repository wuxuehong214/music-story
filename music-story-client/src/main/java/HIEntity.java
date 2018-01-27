

import java.util.List;

public class HIEntity {

	private int id;
	private int useridTo;
	private int useridFrom;
	private List<Integer> msIds;

	private List<MusicStoryEntity> stories;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUseridTo() {
		return useridTo;
	}

	public void setUseridTo(int useridTo) {
		this.useridTo = useridTo;
	}

	public int getUseridFrom() {
		return useridFrom;
	}

	public void setUseridFrom(int useridFrom) {
		this.useridFrom = useridFrom;
	}

	public List<Integer> getMsIds() {
		return msIds;
	}

	public void setMsIds(List<Integer> msIds) {
		this.msIds = msIds;
	}

	public List<MusicStoryEntity> getStories() {
		return stories;
	}

	public void setStories(List<MusicStoryEntity> stories) {
		this.stories = stories;
	}

}

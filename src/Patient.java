import java.time.LocalDate;

public class Patient {
	private String name;
	private String id;

	private LocalDate birth;
	private Boolean isFemale;

	public LocalDate getBirth() {
		return birth;
	}
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	public Boolean getIsFemale() {
		return isFemale;
	}
	public void setIsFemale(Boolean isFemale) {
		this.isFemale = isFemale;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

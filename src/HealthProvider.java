
public interface HealthProvider {
	String getSocialMediaURL();
	String getName();
	String getSpecialization();
	CoordinateDS getLocal();
	String getId();

	void setLocal(CoordinateDS local);
	void setSocialMediaURL(String url);
	void setName(String name);
	void setSpecialization(String specialization);
	void setId(String id);

	HealthProvider clone();
}

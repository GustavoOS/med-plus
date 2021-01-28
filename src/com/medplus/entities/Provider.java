package com.medplus.entities;

public class Provider implements HealthProvider {
	private String socialMediaUrl, name, specialization, id;
	private CoordinateDS local;

	public Provider()
	{

	}
	
	public Provider(String name, String url, String specialization, CoordinateDS local)
	{
		this.name = name;
		this.socialMediaUrl = url;
		this.specialization = specialization;
		this.local = local;
	}

	/*getters and setters */
	@Override
	public String getSocialMediaURL() {
		return this.socialMediaUrl;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getSpecialization() {
		return this.specialization;
	}

	@Override
	public CoordinateDS getLocal() {
		return this.local;
	}

	@Override
	public void setLocal(CoordinateDS local) {
		this.local = local;
	}

	@Override
	public void setSocialMediaURL(String url) {
		this.socialMediaUrl = url;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@Override
	public HealthProvider clone()
	{
		HealthProvider p = new Provider();
		p.setLocal(local);
		p.setName(name);
		p.setSocialMediaURL(socialMediaUrl);
		p.setSpecialization(specialization);
		p.setId(id);
		return p;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

}

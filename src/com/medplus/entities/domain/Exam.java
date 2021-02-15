package com.medplus.entities.domain;

import java.time.LocalDateTime;

public interface Exam {
	public String getId();
	public Exam withId(String id);
	public String getTitle();
	public Exam withTitle(String title);
	public LocalDateTime getInsertionDateTime();
	public Exam withInsertionDateTime(LocalDateTime insertionDateTime);
}

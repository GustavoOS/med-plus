package com.medplus.entities;

import java.time.LocalDateTime;

public class ExamImpl implements Exam {
	private String id, title;
	private LocalDateTime insertionDateTime;

	// Getters
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public LocalDateTime getInsertionDateTime() {
		return insertionDateTime;
	}

	// Withs
	public Exam withId(String id) {
		this.id = id;
		return this;
	}
	public Exam withTitle(String title) {
		this.title = title;
		return this;
	}
	public Exam withInsertionDateTime(LocalDateTime insertionDateTime) {
		this.insertionDateTime = insertionDateTime;
		return this;
	}
}

package com.medplus.useCases;

import java.time.LocalDateTime;

public interface Cancelable {
	void cancel(String id, LocalDateTime dateTime);
}

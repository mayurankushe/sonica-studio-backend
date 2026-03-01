package com.project.sonica.pagedResponse;

import java.time.LocalDateTime;
import java.util.List;

public class PagedResponse<T> {
	private int status;
	private String message;
	private List<T> data;
	private int page;
	private int size;
	private long totalElements;
	private int totalPages;
	private boolean last;
	private LocalDateTime timestamp;

	public PagedResponse(int status, String message, List<T> data, int page, int size, long totalElements,
			int totalPages, boolean last) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.page = page;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
		this.timestamp = LocalDateTime.now();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}

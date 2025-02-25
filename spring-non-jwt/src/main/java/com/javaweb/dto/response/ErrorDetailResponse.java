package com.javaweb.dto.response;

import java.util.List;

public class ErrorDetailResponse {
	private String error;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<String> getDetail() {
		return detail;
	}
	public void setDetail(List<String> detail) {
		this.detail = detail;
	}
	private List<String> detail;
}

package com.project.web.jdbc;

public class To_do_list {
	
	private int id;
	private String to_do;
	private String status;
	
	
	
	public To_do_list(String to_do, String status) {
		super();
		this.to_do = to_do;
		this.status = status;
	}
	
	

	public To_do_list(int id, String to_do, String status) {
		super();
		this.id = id;
		this.to_do = to_do;
		this.status = status;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}




	public String getTo_do() {
		return to_do;
	}



	public void setTo_do(String to_do) {
		this.to_do = to_do;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "List [id=" + id + ", to_do=" + to_do + ", status=" + status + "]";
	}
	
	
	

}

package com.mycompany.newgyms.event.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("EventVO")
public class EventVO {
	private int event_no;
	private int product_id;
	private String member_id;
	private String center_name;
	private String product_name;
	private String event_title;
	private String event_content;
	private String event_image;
	private Date event_write_date;
	private Date event_start_date;
	private Date event_end_date;
	private String event_ing;
	
	public EventVO() {
		
	}

	public int getEvent_no() {
		return event_no;
	}

	public void setEvent_no(int event_no) {
		this.event_no = event_no;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getCenter_name() {
		return center_name;
	}

	public void setCenter_name(String center_name) {
		this.center_name = center_name;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getEvent_title() {
		return event_title;
	}

	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}

	public String getEvent_content() {
		return event_content;
	}

	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}

	public String getEvent_image() {
		return event_image;
	}

	public void setEvent_image(String event_image) {
		this.event_image = event_image;
	}

	public Date getEvent_write_date() {
		return event_write_date;
	}

	public void setEvent_write_date(Date event_write_date) {
		this.event_write_date = event_write_date;
	}

	public Date getEvent_start_date() {
		return event_start_date;
	}

	public void setEvent_start_date(Date event_start_date) {
		this.event_start_date = event_start_date;
	}

	public Date getEvent_end_date() {
		return event_end_date;
	}

	public void setEvent_end_date(Date event_end_date) {
		this.event_end_date = event_end_date;
	}

	public String getEvent_ing() {
		return event_ing;
	}

	public void setEvent_ing(String event_ing) {
		this.event_ing = event_ing;
	}
		
}
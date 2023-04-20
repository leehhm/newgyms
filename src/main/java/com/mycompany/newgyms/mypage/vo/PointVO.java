package com.mycompany.newgyms.mypage.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("pointVO")
public class PointVO {
	private int point_id;
	private String point_state;
	private String point_name;
	private int point_price;
	private Date point_date;
	private String member_id;
	private int product_id;

	public PointVO() {

	}

	public PointVO(int point_id, String point_state, String point_name, int point_price, Date point_date,
			String member_id, int product_id) {
		this.point_id = point_id;
		this.point_state = point_state;
		this.point_name = point_name;
		this.point_price = point_price;
		this.point_date = point_date;
		this.member_id = member_id;
		this.product_id = product_id;
	}

	public int getPoint_id() {
		return point_id;
	}

	public String getPoint_state() {
		return point_state;
	}

	public String getPoint_name() {
		return point_name;
	}

	public int getPoint_price() {
		return point_price;
	}

	public Date getPoint_date() {
		return point_date;
	}

	public String getMember_id() {
		return member_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setPoint_id(int point_id) {
		this.point_id = point_id;
	}

	public void setPoint_state(String point_state) {
		this.point_state = point_state;
	}

	public void setPoint_name(String point_name) {
		this.point_name = point_name;
	}

	public void setPoint_price(int point_price) {
		this.point_price = point_price;
	}

	public void setPoint_date(Date point_date) {
		this.point_date = point_date;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

}

package com.mycompany.newgyms.wish.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;
@Component("wishVO")
public class WishVO {
	private int wish_id;
	private String member_id;
	private int product_id;
	private Date wish__entered_date;

	
	public int getWish_id() {
		return wish_id;
	}
	public void setWish_id(int wish_id) {
		this.wish_id = wish_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public Date getWish__entered_date() {
		return wish__entered_date;
	}
	public void setWish__entered_date(Date wish__entered_date) {
		this.wish__entered_date = wish__entered_date;
	}

	
}

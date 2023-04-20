package com.mycompany.newgyms.cart.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("cartVO")
public class CartVO {
	private int cart_id;
	private int product_id;
	private String member_id;
	private int option_id;
	private String center_name;
	private String product_option_name;
	private int product_option_price;
	private Date cart_entered_date;
	
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
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
	
	public int getOption_id() {
		return option_id;
	}
	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}
	
	public String getCenter_name() {
		return center_name;
	}
	public void setCenter_name(String center_name) {
		this.center_name = center_name;
	}
	public String getProduct_option_name() {
		return product_option_name;
	}
	public void setProduct_option_name(String product_option_name) {
		this.product_option_name = product_option_name;
	}
	public int getProduct_option_price() {
		return product_option_price;
	}
	public void setProduct_option_price(int product_option_price) {
		this.product_option_price = product_option_price;
	}
	public Date getCart_entered_date() {
		return cart_entered_date;
	}
	public void setCart_entered_date(Date product_entered_date) {
		this.cart_entered_date = product_entered_date;
	}
	
}

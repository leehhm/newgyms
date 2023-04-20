package com.mycompany.newgyms.review.vo;

import java.sql.Date;

public class ReviewVO {
	private int review_no;
	private int order_id;
	private int order_seq_num;
	private String member_id; //작성자
	private String center_name;
	private int product_id;
	private String owner_id; //사업자
	private String product_name;
	private String product_main_image;
	private String product_option_name;
	private String product_option_price;
	
	private String review_main_image;
	private String review_title;
	private String review_contents;
	private String review_image;
	private int review_score;
	private Date review_write_date;
	
	
	public int getOrder_seq_num() {
		return order_seq_num;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOrder_seq_num(int order_seq_num) {
		this.order_seq_num = order_seq_num;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_main_image() {
		return product_main_image;
	}
	public void setProduct_main_image(String product_main_image) {
		this.product_main_image = product_main_image;
	}
	public String getProduct_option_name() {
		return product_option_name;
	}
	public void setProduct_option_name(String product_option_name) {
		this.product_option_name = product_option_name;
	}
	public String getProduct_option_price() {
		return product_option_price;
	}
	public void setProduct_option_price(String product_option_price) {
		this.product_option_price = product_option_price;
	}
	public String getReview_main_image() {
		return review_main_image;
	}
	public void setReview_main_image(String review_main_image) {
		this.review_main_image = review_main_image;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_contents() {
		return review_contents;
	}
	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}
	public String getReview_image() {
		return review_image;
	}
	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}
	
	public int getReview_score() {
		return review_score;
	}
	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}
	public Date getReview_write_date() {
		return review_write_date;
	}
	public void setReview_write_date(Date review_write_date) {
		this.review_write_date = review_write_date;
	}
	
	
	
}

package com.mycompany.newgyms.qna.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("qnaVO")
public class QnaVO {
	private int qna_no;
	private int qna_parent_no;
	private int product_id;
	private String member_id;
	private String qna_answer_state;
	private String qna_secret;
	private String qna_title;
	private String qna_contents;
	private Date qna_write_date;
	
	//마이페이지에서 Qna 상품 정보
	private String product_main_image;
	private String product_name;
	private String center_name;
	
	
	public int getQna_no() {
		return qna_no;
	}
	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}
	public int getQna_parent_no() {
		return qna_parent_no;
	}
	public void setQna_parent_no(int qna_parent_no) {
		this.qna_parent_no = qna_parent_no;
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
	public String getQna_answer_state() {
		return qna_answer_state;
	}
	public void setQna_answer_state(String qna_answer_state) {
		this.qna_answer_state = qna_answer_state;
	}
	public String getQna_secret() {
		return qna_secret;
	}
	public void setQna_secret(String qna_secret) {
		this.qna_secret = qna_secret;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_contents() {
		return qna_contents;
	}
	public void setQna_contents(String qna_contents) {
		this.qna_contents = qna_contents;
	}

	public Date getQna_write_date() {
		return qna_write_date;
	}
	public void setQna_write_date(Date qna_write_date) {
		this.qna_write_date = qna_write_date;
	}
	public String getProduct_main_image() {
		return product_main_image;
	}
	public void setProduct_main_image(String product_main_image) {
		this.product_main_image = product_main_image;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getCenter_name() {
		return center_name;
	}
	public void setCenter_name(String center_name) {
		this.center_name = center_name;
	}
	
}

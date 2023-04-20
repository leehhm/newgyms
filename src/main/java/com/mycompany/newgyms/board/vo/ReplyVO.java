package com.mycompany.newgyms.board.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("ReplyVO")
public class ReplyVO {
	private int article_no;
	private int reply_no;
	private String reply_content;
	private String member_id;
	private Date reply_write_date;
	private String join_type;
	
	public ReplyVO() {
		
	}


	public int getArticle_no() {
		return article_no;
	}


	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}


	public int getReply_no() {
		return reply_no;
	}


	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}


	public String getReply_content() {
		return reply_content;
	}


	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public Date getReply_write_date() {
		return reply_write_date;
	}


	public void setReply_write_date(Date reply_write_date) {
		this.reply_write_date = reply_write_date;
	}


	public String getJoin_type() {
		return join_type;
	}


	public void setJoin_type(String join_type) {
		this.join_type = join_type;
	}
	
	
}

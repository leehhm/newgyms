package com.mycompany.newgyms.notice.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("NoticeVO")
public class NoticeVO {
	private int notice_no;
	private String notice_title;
	private String notice_content;
	private String notice_image;
	private Date notice_write_date;
	private String member_id;
	
	public NoticeVO() {
		
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public String getNotice_image() {
		try {
			if (notice_image != null && notice_image.length() != 0) {
				notice_image = URLDecoder.decode(notice_image, "UTF-8");	} 
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		}
		return notice_image;
	}

	public void setNotice_image(String notice_image) {
		try {
			this.notice_image = URLEncoder.encode(notice_image, "UTF-8");
			// 파일 이름에 특수문자가 있을 경우 인코딩
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		}
		this.notice_image = notice_image;
	}

	public Date getNotice_write_date() {
		return notice_write_date;
	}

	public void setNotice_write_date(Date notice_write_date) {
		this.notice_write_date = notice_write_date;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
}

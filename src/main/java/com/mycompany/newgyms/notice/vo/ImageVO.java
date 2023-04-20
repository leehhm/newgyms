package com.mycompany.newgyms.notice.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

public class ImageVO {
	private int imageFileNO;
	private String imageFileName;
	private Date regDate;
	private int notice_no;
	
	public int getImageFileNO() {
		return imageFileNO;
	}
	
	public void setImageFileNO(int imageFileNO) {
		this.imageFileNO = imageFileNO;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	
	public void setImageFileName(String imageFileName) {
		try {
			this.imageFileName = URLEncoder.encode(imageFileName, "UTF-8");
			// 파일 이름에 특수문자가 있을 경우 인코딩
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		}
	}
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	
}
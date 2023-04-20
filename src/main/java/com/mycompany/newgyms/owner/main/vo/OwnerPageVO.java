package com.mycompany.newgyms.owner.main.vo;

public class OwnerPageVO {
	private String member_id;
	private String intro_introduce;
	private String intro_notice;
	private String intro_time;
	private String intro_program;
	private String intro_service;
	
	public OwnerPageVO() {
		
	}
	
	public OwnerPageVO(String member_id, String intro_introduce, String intro_notice, String intro_time,
			String intro_program, String intro_service) {
		this.member_id = member_id;
		this.intro_introduce = intro_introduce;
		this.intro_notice = intro_notice;
		this.intro_time = intro_time;
		this.intro_program = intro_program;
		this.intro_service = intro_program;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getIntro_introduce() {
		return intro_introduce;
	}

	public void setIntro_introduce(String intro_introduce) {
		this.intro_introduce = intro_introduce;
	}

	public String getIntro_notice() {
		return intro_notice;
	}

	public void setIntro_notice(String intro_notice) {
		this.intro_notice = intro_notice;
	}

	public String getIntro_time() {
		return intro_time;
	}

	public void setIntro_time(String intro_time) {
		this.intro_time = intro_time;
	}

	public String getIntro_program() {
		return intro_program;
	}

	public void setIntro_program(String intro_program) {
		this.intro_program = intro_program;
	}

	public String getIntro_service() {
		return intro_service;
	}

	public void setIntro_service(String intro_service) {
		this.intro_service = intro_service;
	}
	
	
}

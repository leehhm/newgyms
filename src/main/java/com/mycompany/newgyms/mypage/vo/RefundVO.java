package com.mycompany.newgyms.mypage.vo;

import org.springframework.stereotype.Component;

@Component("refundVO")
public class RefundVO {
	private int refund_id;
	private int order_seq_num;
	private int refund_price;
	private String order_state;
	private String account_holder;
	private String account_bank;
	private String account_number;
	private String cancel_reason;
	
	public RefundVO() {
		
	}

	public int getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(int refund_id) {
		this.refund_id = refund_id;
	}

	public int getOrder_seq_num() {
		return order_seq_num;
	}

	public void setOrder_seq_num(int order_seq_num) {
		this.order_seq_num = order_seq_num;
	}

	public int getRefund_price() {
		return refund_price;
	}

	public void setRefund_price(int refund_price) {
		this.refund_price = refund_price;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}

	public String getAccount_holder() {
		return account_holder;
	}

	public void setAccount_holder(String account_holder) {
		this.account_holder = account_holder;
	}

	public String getAccount_bank() {
		return account_bank;
	}

	public void setAccount_bank(String account_bank) {
		this.account_bank = account_bank;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getCancel_reason() {
		return cancel_reason;
	}

	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}
}

package com.mycompany.newgyms.order.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("orderVO")
public class OrderVO {
	private int order_seq_num;
	private int order_id;
	private String member_id;
	private int product_id;
	private int option_id;
	
	private String orderer_name;
	private String orderer_hp1;
	private String orderer_hp2;
	private String orderer_hp3;
	private String nonmember_pw;
	private int total_price;
	private int new_point;
	private int point_price;
	

	private String center_name;
	private String product_name;
	private String product_option_name;
	private String product_option_price;
	private String product_main_image;
	private int product_price;
	private int product_sales_price;
	private int product_point;

	private String receiver_name;
	private String receiver_hp1;
	private String receiver_hp2;
	private String receiver_hp3;
	
	private String pay_method;
	private String card_com_name;
	private String card_pay_month;
	private String order_state;
	private Date pay_order_time;
	private int refund_price;


	public int getOrder_seq_num() {
		return order_seq_num;
	}

	public void setOrder_seq_num(int order_seq_num) {
		this.order_seq_num = order_seq_num;
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

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getOption_id() {
		return option_id;
	}

	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}

	public String getOrderer_name() {
		return orderer_name;
	}

	public void setOrderer_name(String orderer_name) {
		this.orderer_name = orderer_name;
	}

	public String getOrderer_hp1() {
		return orderer_hp1;
	}

	public void setOrderer_hp1(String orderer_hp1) {
		this.orderer_hp1 = orderer_hp1;
	}

	public String getOrderer_hp2() {
		return orderer_hp2;
	}

	public void setOrderer_hp2(String orderer_hp2) {
		this.orderer_hp2 = orderer_hp2;
	}

	public String getOrderer_hp3() {
		return orderer_hp3;
	}

	public void setOrderer_hp3(String orderer_hp3) {
		this.orderer_hp3 = orderer_hp3;
	}

	public String getNonmember_pw() {
		return nonmember_pw;
	}

	public void setNonmember_pw(String nonmember_pw) {
		this.nonmember_pw = nonmember_pw;
	}
	
	public int getNew_point() {
		return new_point;
	}

	public void setNew_point(int new_point) {
		this.new_point = new_point;
	}

	public int getPoint_price() {
		return point_price;
	}

	public void setPoint_price(int point_price) {
		this.point_price = point_price;
	}

	public String getCenter_name() {
		return center_name;
	}

	public void setCenter_name(String center_name) {
		this.center_name = center_name;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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

	public String getProduct_main_image() {
		return product_main_image;
	}

	public void setProduct_main_image(String product_main_image) {
		this.product_main_image = product_main_image;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getProduct_sales_price() {
		return product_sales_price;
	}

	public void setProduct_sales_price(int product_sales_price) {
		this.product_sales_price = product_sales_price;
	}

	public int getProduct_point() {
		return product_point;
	}

	public void setProduct_point(int product_point) {
		this.product_point = product_point;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getReceiver_hp1() {
		return receiver_hp1;
	}

	public void setReceiver_hp1(String receiver_hp1) {
		this.receiver_hp1 = receiver_hp1;
	}

	public String getReceiver_hp2() {
		return receiver_hp2;
	}

	public void setReceiver_hp2(String receiver_hp2) {
		this.receiver_hp2 = receiver_hp2;
	}

	public String getReceiver_hp3() {
		return receiver_hp3;
	}

	public void setReceiver_hp3(String receiver_hp3) {
		this.receiver_hp3 = receiver_hp3;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getCard_com_name() {
		return card_com_name;
	}

	public void setCard_com_name(String card_com_name) {
		this.card_com_name = card_com_name;
	}

	public String getCard_pay_month() {
		return card_pay_month;
	}

	public void setCard_pay_month(String card_pay_month) {
		this.card_pay_month = card_pay_month;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}

	public Date getPay_order_time() {
		return pay_order_time;
	}

	public void setPay_order_time(Date pay_order_time) {
		this.pay_order_time = pay_order_time;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public int getRefund_price() {
		return refund_price;
	}

	public void setRefund_price(int refund_price) {
		this.refund_price = refund_price;
	}

	
}
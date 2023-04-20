package com.mycompany.newgyms.product.vo;

import java.util.List;

public class ProductOptVO {
	private int option_id;
	private int product_id;
	private String product_option_name;
	private int product_option_price;
	
	private List<ProductOptVO> optionList;
	
	public int getOption_id() {
		return option_id;
	}
	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
	public List<ProductOptVO> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<ProductOptVO> optionList) {
		this.optionList = optionList;
	}
	
}

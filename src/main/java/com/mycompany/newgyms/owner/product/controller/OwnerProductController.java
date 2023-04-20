package com.mycompany.newgyms.owner.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.product.vo.ProductOptVO;

public interface OwnerProductController {
	public ModelAndView ownerProductList(@RequestParam("member_id") String member_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addProductForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addNewProduct(@ModelAttribute(value="ProductOptVO") ProductOptVO optionList, MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception ;
	
    public ModelAndView ProductModifyForm(@RequestParam("product_id") int product_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ResponseEntity modifyProduct(ProductOptVO productOptVO, MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;

    public void removeProductImage(int product_id, String[] delImageIdList, String[] delFileNameList)  throws Exception;
	public @ResponseBody String removeProduct(@RequestParam("product_id") int product_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
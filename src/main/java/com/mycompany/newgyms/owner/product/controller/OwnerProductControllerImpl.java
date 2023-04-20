package com.mycompany.newgyms.owner.product.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.owner.product.service.OwnerProductService;
import com.mycompany.newgyms.product.service.ProductService;
import com.mycompany.newgyms.product.vo.ProductImageVO;
import com.mycompany.newgyms.product.vo.ProductOptVO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Controller("ownerProductController")
@RequestMapping(value = "/owner/product")
public class OwnerProductControllerImpl implements OwnerProductController {
	private static final String CURR_IMAGE_REPO_PATH = "C:\\newgyms\\product";
	
	@Autowired
	private OwnerProductService ownerProductService;

	@Autowired
	private ProductService productService;
	

	// ����� ��ǰ ���
	@RequestMapping(value = "/ownerProductList.do", method = RequestMethod.GET)
	public ModelAndView ownerProductList(@RequestParam("member_id") String member_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		String chapter = request.getParameter("chapter");
		
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("member_id", member_id);
		condMap.put("chapter", chapter);
		String maxnum = ownerProductService.maxNumSelect(condMap);
		condMap.put("maxnum", maxnum);

		List<ProductVO> ownerProductList = ownerProductService.ownerProductList(condMap);
		
		mav.addObject("chapter", chapter);
		mav.addObject("maxnum", maxnum);
		mav.addObject("ownerProductList", ownerProductList);
		return mav;
	}

	//��ǰ��� ������ �̵�
	@Override
	@RequestMapping(value= { "/addProductForm.do"}, method = RequestMethod.GET)
	public ModelAndView addProductForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName"); 
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	//��ǰ ���
	@Override
	@RequestMapping(value="/addNewProduct.do" ,method={RequestMethod.POST})
	public ResponseEntity addNewProduct(ProductOptVO productOptVO, MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Map newProductMap = new HashMap();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			newProductMap.put(name,value);
		}
		System.out.println(newProductMap);
		
		//�Ǹ��� member_id ���ǿ��� �������� 
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		newProductMap.put("member_id",member_id);
		
		//��ǰ ��� �� product_state ���δ��� ����
		String product_state = "���δ��";
		newProductMap.put("product_state",product_state);
		
		/* �ɼ� */
		List<ProductOptVO> optionList = productOptVO.getOptionList();
		newProductMap.put("optionList",optionList);
		
		/* �̹��� */
		List<ProductImageVO> imageList =upload(multipartRequest);
		
		if(imageList!= null && imageList.size()!=0) {
			newProductMap.put("imageList", imageList);
		}
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int product_id = ownerProductService.addNewProduct(newProductMap);
			
	    	  //�����̹���
	    	  MultipartFile mainImage = multipartRequest.getFile("product_main_image");
	    	  String product_main_image = (String)newProductMap.get("product_main_image");
						
		    	  File m_srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+product_main_image);
		    	  File m_destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+product_id);
		    	  FileUtils.moveFileToDirectory(m_srcFile, m_destDir,true);

			
			//������ �̹���
			if(imageList!=null && imageList.size()!=0) {
				for(ProductImageVO  productImageVO:imageList) {
					String fileName = productImageVO.getFileName();
					System.out.println(fileName);
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+fileName);
					File destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+product_id);
					FileUtils.moveFileToDirectory(srcFile, destDir,true);
				}
			}
			message= "<script>";
			message += " alert('����ǰ�� �߰��߽��ϴ�.');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/owner/product/ownerProductList.do?member_id="+member_id+"&chapter=1';";
			message +=("</script>");
		}catch(Exception e) {
			if(imageList!=null && imageList.size()!=0) {
				for(ProductImageVO  imageFileVO:imageList) {
					String fileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+fileName);
					srcFile.delete();
				}
			}
			
			message= "<script>";
			message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/owner/product/ownerProductList.do?member_id="+member_id+"&chapter=1';";
			message +=("</script>");
			e.printStackTrace();
		}
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	

	
	//��ǰ �����ϱ� �� �̵�
	@Override
    @RequestMapping(value="/ProductModifyForm.do", method=RequestMethod.GET)
    public ModelAndView ProductModifyForm(@RequestParam("product_id") int product_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		/* �ɼ� */
		List<ProductOptVO> productOptList = productService.selectProductOptionList(product_id);
		mav.addObject("productOptList", productOptList);

		/* ��ǰ ������, ���α׷� �ȳ� */
		ProductVO productVO = productService.productDetail(product_id);
		mav.addObject("productVO", productVO);
		
		/* ���α׷� �ȳ� �̹��� */
		Map imageMap = productService.productImage(product_id);
		mav.addObject("imageMap", imageMap);
		
		mav.setViewName("/owner/product/modProductForm");

		return mav;
	}
	
    // ��ǰ �����ϱ�
    @Override
    @RequestMapping(value="/modifyProduct.do", method=RequestMethod.POST)
    public ResponseEntity modifyProduct(ProductOptVO productOptVO, MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
       multipartRequest.setCharacterEncoding("utf-8");
       
		Map productMap = new HashMap();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			productMap.put(name,value);
		}
		
		//�Ǹ��� member_id ���ǿ��� �������� 
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		productMap.put("member_id",member_id);
		
		/* �߰��� �ɼ� */
		List<ProductOptVO> optionList = productOptVO.getOptionList();
		
		if(optionList!= null && optionList.size()!=0) {
			productMap.put("optionList",optionList);
		}
		
		/* �߰��� �̹��� */
		List<ProductImageVO> imageList = upload(multipartRequest);
			productMap.put("imageList", imageList);
       
		
		/* ������ �̹��� */
			
		int product_id = Integer.parseInt(multipartRequest.getParameter("product_id"));
		String[] delImageIdList = multipartRequest.getParameterValues("del_image_id");
		String[] delFileNameList = multipartRequest.getParameterValues("del_fileName");

		if(delImageIdList!= null && delFileNameList!= null) {
			removeProductImage(product_id,delImageIdList,delFileNameList);
		}
       
       //��ǰ���̹���, ���� �����ϱ�
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
	      try {
	    	  
	    	  //�����̹���
	    	  MultipartFile mainImage = multipartRequest.getFile("product_main_image");
	    	  String originalFileName = (String)productMap.get("originalFileName");
	    	  String product_main_image = (String)productMap.get("product_main_image");
	    	  
				if(! originalFileName.equals(product_main_image)) {
						
		    	  File m_srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+product_main_image);
		    	  File m_destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+product_id);
		    	  FileUtils.moveFileToDirectory(m_srcFile, m_destDir,true);
				}
	    	  
	    	  
			//��ǰ �������� �̹���
			if(imageList!=null && imageList.size()!=0) {
				System.out.println("������ ���԰�"+imageList.size());
				for(ProductImageVO  productImageVO:imageList) {
					String fileName = productImageVO.getFileName();
					System.out.println("detail_image~~~"+fileName);
					
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+fileName);
					File destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+product_id);
					System.out.println("~~~product_id ������ �̵�");
					FileUtils.moveFileToDirectory(srcFile, destDir,true);
					System.out.println("product_id ������ �̵� �Ϸ�");
				}
				ownerProductService.modifyProduct(productMap);
			}
			message= "<script>";
			message += " alert('��ǰ�� �����߽��ϴ�.');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/owner/product/ownerProductList.do?member_id="+member_id+"&chapter=1';";
			message +=("</script>");
			
			resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
	      }catch(Exception e) {
			
			if(imageList!=null && imageList.size()!=0) {
				for(ProductImageVO  imageFileVO:imageList) {
					String fileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+fileName);
					srcFile.delete();
				}
			}
			
			message= "<script>";
			message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/owner/product/ownerProductList.do?member_id="+member_id+"&chapter=1';";
			message +=("</script>");
			e.printStackTrace();
			resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		
		return resEntity;
	}
	
	//��ǰ �̹��� ����
	@Override
	@RequestMapping(value="/removeProductImage.do" ,method={RequestMethod.POST})
	public void removeProductImage(int product_id, String[] delImageIdList, String[] delFileNameList)  throws Exception {
		
		try{
			ownerProductService.removeProductImage(delImageIdList);
			
			for(int i=0;i<delFileNameList.length;i++) {
				System.out.println(delFileNameList[i]);        
				
				File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+product_id+"\\"+delFileNameList[i]);
				srcFile.delete();
				
			    
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// ��ǰ ����
	@RequestMapping(value = "/removeProduct.do", method = RequestMethod.POST)
	public @ResponseBody String removeProduct(@RequestParam("product_id") int product_id, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		System.out.println(product_id);
			String result = ownerProductService.removeProduct(product_id);
			File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + product_id);
			FileUtils.deleteDirectory(destDir);
			
			if(result=="true" || result.equals("true")){
				return "success";
			}else{
				return "false";
			}
	}
	
	//�̹��� ���ε� �ϱ�
	private List<ProductImageVO> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		String fileName = null;
		
		//DB�� ������ imageList
		List<ProductImageVO> imageList= new ArrayList<ProductImageVO>();
		
		//���� �̹���
		MultipartFile mainImage = multipartRequest.getFile("product_main_image");
		ProductImageVO mainImageVO =new ProductImageVO();
		fileName = mainImage.getOriginalFilename();
			
			File file = new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+ fileName);
			if(mainImage.getSize()!=0){ //File Null Check
				if(! file.exists()){ //��λ� ������ �������� ���� ���
					if(file.getParentFile().mkdirs()){ //��ο� �ش��ϴ� ���丮���� ����
						file.createNewFile(); //���� ���� ����
					}
				}
				mainImage.transferTo(new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+fileName)); //�ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
			    	   
		}
		//detail �̹���
		List<MultipartFile> detailImageList = multipartRequest.getFiles("detail_image");
		
		for (MultipartFile image : detailImageList) {
			ProductImageVO productImageVO =new ProductImageVO();
			
			fileName = image.getOriginalFilename(); // ���� ���� ��
			
			productImageVO.setFileType("detail_image");
			productImageVO.setFileName(fileName);
			
			imageList.add(productImageVO);
			
			File file1 = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + fileName);
			if(image.getSize() != 0) {
				if(!file1.exists()){
					file1.getParentFile().mkdirs();
					image.transferTo( new File (CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + fileName ));
				}
			}
		}
		
		List<MultipartFile> priceImageList = multipartRequest.getFiles("price_image");
		
		for (MultipartFile image : priceImageList) {
			ProductImageVO productImageVO =new ProductImageVO();
			
			fileName = image.getOriginalFilename(); // ���� ���� ��
			
			productImageVO.setFileType("price_image");
			productImageVO.setFileName(fileName);
			
			imageList.add(productImageVO);
			
			File file2 = new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+ fileName);
			if(image.getSize()!=0){ //File Null Check
				if(! file2.exists()){ //��λ� ������ �������� ���� ���
					if(file2.getParentFile().mkdirs()){ //��ο� �ش��ϴ� ���丮���� ����
							file2.createNewFile(); //���� ���� ����
					}
				}
				image.transferTo(new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+fileName)); //�ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
			}
		}
		List<MultipartFile> facilityImageList = multipartRequest.getFiles("facility_image");
		
		for (MultipartFile image : facilityImageList) {
			ProductImageVO productImageVO =new ProductImageVO();
			
			fileName = image.getOriginalFilename(); // ���� ���� ��
			
			productImageVO.setFileType("facility_image");
			productImageVO.setFileName(fileName);
			
			imageList.add(productImageVO);
			System.out.println("facility_image: " + fileName);
			
			File file3 = new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+ fileName);
			if(image.getSize()!=0){ //File Null Check
				if(! file3.exists()){ //��λ� ������ �������� ���� ���
					if(file3.getParentFile().mkdirs()){ //��ο� �ش��ϴ� ���丮���� ����
							file3.createNewFile(); //���� ���� ����
					}
				}
				image.transferTo(new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+fileName)); //�ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
			}
		}
		
		return imageList;
	}


}
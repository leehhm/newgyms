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
	

	// 사업자 상품 목록
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

	//상품등록 페이지 이동
	@Override
	@RequestMapping(value= { "/addProductForm.do"}, method = RequestMethod.GET)
	public ModelAndView addProductForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName"); 
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	//상품 등록
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
		
		//판매자 member_id 세션에서 가져오기 
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		newProductMap.put("member_id",member_id);
		
		//상품 등록 시 product_state 승인대기로 설정
		String product_state = "승인대기";
		newProductMap.put("product_state",product_state);
		
		/* 옵션 */
		List<ProductOptVO> optionList = productOptVO.getOptionList();
		newProductMap.put("optionList",optionList);
		
		/* 이미지 */
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
			
	    	  //메인이미지
	    	  MultipartFile mainImage = multipartRequest.getFile("product_main_image");
	    	  String product_main_image = (String)newProductMap.get("product_main_image");
						
		    	  File m_srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+product_main_image);
		    	  File m_destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+product_id);
		    	  FileUtils.moveFileToDirectory(m_srcFile, m_destDir,true);

			
			//상세정보 이미지
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
			message += " alert('새상품을 추가했습니다.');";
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
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/owner/product/ownerProductList.do?member_id="+member_id+"&chapter=1';";
			message +=("</script>");
			e.printStackTrace();
		}
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	

	
	//상품 수정하기 폼 이동
	@Override
    @RequestMapping(value="/ProductModifyForm.do", method=RequestMethod.GET)
    public ModelAndView ProductModifyForm(@RequestParam("product_id") int product_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		/* 옵션 */
		List<ProductOptVO> productOptList = productService.selectProductOptionList(product_id);
		mav.addObject("productOptList", productOptList);

		/* 상품 상세정보, 프로그램 안내 */
		ProductVO productVO = productService.productDetail(product_id);
		mav.addObject("productVO", productVO);
		
		/* 프로그램 안내 이미지 */
		Map imageMap = productService.productImage(product_id);
		mav.addObject("imageMap", imageMap);
		
		mav.setViewName("/owner/product/modProductForm");

		return mav;
	}
	
    // 상품 수정하기
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
		
		//판매자 member_id 세션에서 가져오기 
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		productMap.put("member_id",member_id);
		
		/* 추가된 옵션 */
		List<ProductOptVO> optionList = productOptVO.getOptionList();
		
		if(optionList!= null && optionList.size()!=0) {
			productMap.put("optionList",optionList);
		}
		
		/* 추가된 이미지 */
		List<ProductImageVO> imageList = upload(multipartRequest);
			productMap.put("imageList", imageList);
       
		
		/* 삭제된 이미지 */
			
		int product_id = Integer.parseInt(multipartRequest.getParameter("product_id"));
		String[] delImageIdList = multipartRequest.getParameterValues("del_image_id");
		String[] delFileNameList = multipartRequest.getParameterValues("del_fileName");

		if(delImageIdList!= null && delFileNameList!= null) {
			removeProductImage(product_id,delImageIdList,delFileNameList);
		}
       
       //상품상세이미지, 내용 수정하기
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
	      try {
	    	  
	    	  //메인이미지
	    	  MultipartFile mainImage = multipartRequest.getFile("product_main_image");
	    	  String originalFileName = (String)productMap.get("originalFileName");
	    	  String product_main_image = (String)productMap.get("product_main_image");
	    	  
				if(! originalFileName.equals(product_main_image)) {
						
		    	  File m_srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+product_main_image);
		    	  File m_destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+product_id);
		    	  FileUtils.moveFileToDirectory(m_srcFile, m_destDir,true);
				}
	    	  
	    	  
			//상품 상세페이지 이미지
			if(imageList!=null && imageList.size()!=0) {
				System.out.println("사진이 맻게고"+imageList.size());
				for(ProductImageVO  productImageVO:imageList) {
					String fileName = productImageVO.getFileName();
					System.out.println("detail_image~~~"+fileName);
					
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+fileName);
					File destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+product_id);
					System.out.println("~~~product_id 폴더로 이동");
					FileUtils.moveFileToDirectory(srcFile, destDir,true);
					System.out.println("product_id 폴더로 이동 완료");
				}
				ownerProductService.modifyProduct(productMap);
			}
			message= "<script>";
			message += " alert('상품을 수정했습니다.');";
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
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/owner/product/ownerProductList.do?member_id="+member_id+"&chapter=1';";
			message +=("</script>");
			e.printStackTrace();
			resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		
		return resEntity;
	}
	
	//상품 이미지 삭제
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
	
	// 상품 삭제
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
	
	//이미지 업로드 하기
	private List<ProductImageVO> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		String fileName = null;
		
		//DB에 저장할 imageList
		List<ProductImageVO> imageList= new ArrayList<ProductImageVO>();
		
		//메인 이미지
		MultipartFile mainImage = multipartRequest.getFile("product_main_image");
		ProductImageVO mainImageVO =new ProductImageVO();
		fileName = mainImage.getOriginalFilename();
			
			File file = new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+ fileName);
			if(mainImage.getSize()!=0){ //File Null Check
				if(! file.exists()){ //경로상에 파일이 존재하지 않을 경우
					if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
						file.createNewFile(); //이후 파일 생성
					}
				}
				mainImage.transferTo(new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+fileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
			    	   
		}
		//detail 이미지
		List<MultipartFile> detailImageList = multipartRequest.getFiles("detail_image");
		
		for (MultipartFile image : detailImageList) {
			ProductImageVO productImageVO =new ProductImageVO();
			
			fileName = image.getOriginalFilename(); // 원본 파일 명
			
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
			
			fileName = image.getOriginalFilename(); // 원본 파일 명
			
			productImageVO.setFileType("price_image");
			productImageVO.setFileName(fileName);
			
			imageList.add(productImageVO);
			
			File file2 = new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+ fileName);
			if(image.getSize()!=0){ //File Null Check
				if(! file2.exists()){ //경로상에 파일이 존재하지 않을 경우
					if(file2.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
							file2.createNewFile(); //이후 파일 생성
					}
				}
				image.transferTo(new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+fileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
			}
		}
		List<MultipartFile> facilityImageList = multipartRequest.getFiles("facility_image");
		
		for (MultipartFile image : facilityImageList) {
			ProductImageVO productImageVO =new ProductImageVO();
			
			fileName = image.getOriginalFilename(); // 원본 파일 명
			
			productImageVO.setFileType("facility_image");
			productImageVO.setFileName(fileName);
			
			imageList.add(productImageVO);
			System.out.println("facility_image: " + fileName);
			
			File file3 = new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+ fileName);
			if(image.getSize()!=0){ //File Null Check
				if(! file3.exists()){ //경로상에 파일이 존재하지 않을 경우
					if(file3.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
							file3.createNewFile(); //이후 파일 생성
					}
				}
				image.transferTo(new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+"\\"+fileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
			}
		}
		
		return imageList;
	}


}
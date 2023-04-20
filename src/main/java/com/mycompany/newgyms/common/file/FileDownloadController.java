package com.mycompany.newgyms.common.file;


import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;


@Controller
public class FileDownloadController {
	private static String CURR_IMAGE_REPO_PATH = "C:\\newgyms";
	
	@RequestMapping("/download")
	protected void download(@RequestParam("fileName") String fileName, @RequestParam("product_id") String product_id, HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath=CURR_IMAGE_REPO_PATH+"\\"+ "product"+"\\"+product_id+"\\"+fileName;
		File image=new File(filePath);

		response.setHeader("Cache-Control","no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		FileInputStream in=new FileInputStream(image); 
		byte[] buffer=new byte[1024*8];
		while(true){
			int count=in.read(buffer); //���ۿ� �о���� ���ڰ���
			if(count==-1)  //������ �������� �����ߴ��� üũ
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
	}
	
	//���� �̹���
	@RequestMapping("/reviewImage")
	protected void reviewImage(@RequestParam("fileName") String fileName, @RequestParam("review_no") String review_no, HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath=CURR_IMAGE_REPO_PATH+"\\"+"review"+"\\"+review_no+"\\"+fileName;
		File image=new File(filePath);

		response.setHeader("Cache-Control","no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		FileInputStream in=new FileInputStream(image); 
		byte[] buffer=new byte[1024*8];
		while(true){
			int count=in.read(buffer); //���ۿ� �о���� ���ڰ���
			if(count==-1)  //������ �������� �����ߴ��� üũ
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
	}
		

	
	//�����Խ��� �̹���
	@RequestMapping("/boardImage")
	protected void reviewImage(@RequestParam("board_image") String board_image,
		                 	@RequestParam("article_no") int article_no,
			                 HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath=CURR_IMAGE_REPO_PATH+"\\"+"board"+"\\"+"article_image"+"\\"+article_no+"\\"+board_image;
		File image=new File(filePath);

		response.setHeader("Cache-Control","no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+board_image);
		FileInputStream in=new FileInputStream(image); 
		byte[] buffer=new byte[1024*8];
		while(true){
			int count=in.read(buffer); //���ۿ� �о���� ���ڰ���
			if(count==-1)  //������ �������� �����ߴ��� üũ
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
	}
	
	//�������� �̹���
	@RequestMapping("/noticeImage")
	protected void noticeImage(@RequestParam("notice_image") String notice_image,
		                 	@RequestParam("notice_no") int notice_no,
			                 HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath=CURR_IMAGE_REPO_PATH+"\\"+"notice"+"\\"+"notice_image"+"\\"+notice_no+"\\"+notice_image;
		File image=new File(filePath);
		
			response.setHeader("Cache-Control","no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+notice_image);
		FileInputStream in=new FileInputStream(image); 
		byte[] buffer=new byte[1024*8];
		while(true){
			int count=in.read(buffer); //���ۿ� �о���� ���ڰ���
			if(count==-1)  //������ �������� �����ߴ��� üũ
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
	}
		
	//�������� �̹���
	@RequestMapping("/eventImage")
	protected void eventImage(@RequestParam("event_image") String event_image,
		                 	@RequestParam("event_no") int event_no,
			                 HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath=CURR_IMAGE_REPO_PATH+"\\"+"event"+"\\"+"event_image"+"\\"+event_no+"\\"+event_image;
		File image=new File(filePath);
		
		response.setHeader("Cache-Control","no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+event_image);
		FileInputStream in=new FileInputStream(image); 
		byte[] buffer=new byte[1024*8];
		while(true){
			int count=in.read(buffer); //���ۿ� �о���� ���ڰ���
			if(count==-1)  //������ �������� �����ߴ��� üũ
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
	}	
	
	@RequestMapping("/thumbnails.do")
	protected void thumbnails(@RequestParam("fileName") String fileName,
                            	@RequestParam("product_id") String product_id,
			                 HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath=CURR_IMAGE_REPO_PATH+"\\"+"product"+"\\"+product_id+"\\"+fileName;
		File image=new File(filePath);
		
		if (image.exists()) { 
			Thumbnails.of(image).size(121,154).outputFormat("png").toOutputStream(out);
		}
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
	}
}

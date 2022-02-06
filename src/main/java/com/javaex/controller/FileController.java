package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
@RequestMapping("/fileupload")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	
	@RequestMapping("/form")
	public String form() {
		
		System.out.println("FileController.form");
		
		return "fileupload/form";
		
	}
	
	
	@RequestMapping("/upload")
	public String result(@RequestParam("file") MultipartFile file, Model model) { // 파일 전송할때는 MultipartFile
		
		System.out.println("FileController.upload");
		//System.out.println(file);//첨부 파일이 안 와도 콘솔에 찍힘
		//System.out.println(file.getOriginalFilename()); //이렇게 써줘야 제대로 확인가능
		//System.out.println(file.getSize());
		String savaName = fileService.restore(file);
		
		model.addAttribute("savaName",savaName);
		 
		return "fileupload/result";
		
	}
}

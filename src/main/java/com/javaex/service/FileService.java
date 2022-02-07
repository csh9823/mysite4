package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String restore(MultipartFile file) {

		System.out.println("FileService.restroe()");
		String saveDir = "E:\\javaStudy\\upload"; //저장위치
		// System.out.println(file.getOriginalFilename()); 파일 오나 찍어보기

		// 파일을 하드디스크에 저장(운영내용)

		// 원본파일이름
		String orgName = file.getOriginalFilename();

		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		// 저장파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName; // 업로드된 시간+이름+확장자
		System.out.println(saveName);

		// 파일 패스생성
		String filePath = saveDir + "\\" + saveName;

		// 파일 사이즈
		long fileSize = file.getSize();

		// 파일 저장(사용자 입장 업로드)

		try {
			byte[] fileData = file.getBytes(); // 배열로 저장
			OutputStream out = new FileOutputStream(filePath); // 메인스트림 파일을 가져와서 저장할 경로를 알려주고 저장해준다
			BufferedOutputStream bout = new BufferedOutputStream(out); // 보조 스트림 빠르게 저장하게 만드는  보조역할

			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// DB에 저장(과제)
		
		
		return saveName;
	}

}

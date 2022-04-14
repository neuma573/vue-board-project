package com.lguplus.medialog.project.board;

import java.security.SecureRandom;

import lombok.Data;

@Data
public class FileVO {
	
	private String fileNo;
	private Integer fileBrdNo;
	private String fileName;
	private String fileRealName;
	private long fileSize;
	private String fileRegDt;
	private String fileRandomNo;

	
	public String getRandomNo() {
		SecureRandom sr = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<=6; i++) {
			int temp = sr.nextInt(73)+49;
			sb.append(temp);
		}
		
		return sb.toString();
	}
}

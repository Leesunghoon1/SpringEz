package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//create table file(
//uuid varchar(226) not null,
//save_dir varchar(226) not null,
//file_name varchar(226) not null,
//file_type tinyint(1) default 0,
//bno bigint,
//file_size int,
//reg_at datetime default now(),
//primary key(uuid));

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileVO {
	
	private String uuid;
	private String saveDir;
	private String fileName;
	private int fileType;
	private long bno;
	private long fileSize;
	private String regAt;
}

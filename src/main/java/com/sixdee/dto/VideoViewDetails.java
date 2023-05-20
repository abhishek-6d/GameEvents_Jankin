package com.sixdee.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@Entity
@Table(name = "VIDEO_VIEW_DETAILS")
public class VideoViewDetails {

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "VIDEO_ID")
	private String videoId;
	
	@Column(name = "VIDEO_TITLE")
	private String videoTitle;
}

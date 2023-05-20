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
@Table(name = "REWARD_DETAILS")
public class RewardDetailsDTO {

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "REWARD_ID")
	private String rewardId;
	
	@Column(name = "REWARD_TYPE")
	private String rewardType;
	
	@Column(name = "REWARD_POINT")
	private String rewardPoint;
	
	@Column(name = "REWARD_TITLE")
	private String rewardtitle;
}

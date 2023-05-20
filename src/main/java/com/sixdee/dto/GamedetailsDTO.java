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
@Table(name = "GAME_DETAILS")
public class GamedetailsDTO {
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "GMAE_ID")
	private String gameId;
	
	@Column(name = "GAME_TYPE")
	private String gametype;
	
	@Column(name = "GAME_TITLE")
	private String gametitle;
	
}

package com.kemalakcicek.e_learning.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoBase {

	// private Long id;

	private Date createDate;

	private Date updateDate;

}

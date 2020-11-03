package com.sinosoft.visualization.business.api.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "platform_dic")
public class PlatformDic  implements Serializable {

	@Column(name = "platform_id")
	private Long platformId;

	@Column(name = "platform_name")
	private String platformName;

	@Column(name = "platform_code")
	private String platformCode;

	@Column(name = "is_active")
	private Long isActive;

	private String creator;

	private String modifier;

	@Column(name = "create_time")
	private java.sql.Date createTime;

	@Column(name = "modify_time")
	private java.sql.Date modifyTime;

}

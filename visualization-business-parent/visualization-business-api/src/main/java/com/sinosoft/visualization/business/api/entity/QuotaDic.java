package com.sinosoft.visualization.business.api.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "quota_dic")
public class QuotaDic  implements Serializable {

	@Column(name = "quota_id")
	private Long quotaId;

	@Column(name = "quota_name")
	private String quotaName;

	@Column(name = "quota_code")
	private String quotaCode;

	@Column(name = "is_active")
	private Long isActive;

	private String creator;

	private String modifier;

	@Column(name = "create_time")
	private java.sql.Date createTime;

	@Column(name = "modify_time")
	private java.sql.Date modifyTime;

}

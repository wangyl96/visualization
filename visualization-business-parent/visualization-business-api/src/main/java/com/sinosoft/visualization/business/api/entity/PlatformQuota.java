package com.sinosoft.visualization.business.api.entity;

import com.sinosoft.visualization.common.basic.entity.BasicEntity;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "platform_quota")
public class PlatformQuota extends BasicEntity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "platform_quota_id")
	private Long platformQuotaId;

	@Column(name = "platform_code")
	private String platformCode;

	@Column(name = "quota_code")
	private String quotaCode;

}

package com.sinosoft.visualization.business.api.entity;

import com.sinosoft.visualization.common.basic.entity.BasicEntity;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "quota_dic")
public class QuotaDic extends BasicEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quota_id")
	private Long quotaId;

	@Column(name = "quota_name")
	private String quotaName;

	@Column(name = "quota_code")
	private String quotaCode;

}

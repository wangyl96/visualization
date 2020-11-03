package com.sinosoft.visualization.business.api.entity;

import com.sinosoft.visualization.common.basic.entity.BasicEntity;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "platform_dic")
public class PlatformDic extends BasicEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "platform_id")
	private Long platformId;

	@Column(name = "platform_name")
	private String platformName;

	@Column(name = "platform_code")
	private String platformCode;

}

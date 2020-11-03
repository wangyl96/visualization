package com.sinosoft.visualization.business.api.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "app_vis_data")
public class AppVisData  implements Serializable {

	@Column(name = "app_vis_id")
	private Long appVisId;

	private Long visits;

	@Column(name = "app_installation")
	private Long appInstallation;

	private Long registrations;

	private Double product;

	private Double life;

	private Double health;

	private Double wealth;

	private Double gold;

	@Column(name = "vis_date")
	private java.sql.Date visDate;

	@Column(name = "is_active")
	private Long isActive;

	private String creator;

	private String modifier;

	@Column(name = "create_time")
	private java.sql.Date createTime;

	@Column(name = "modify_time")
	private java.sql.Date modifyTime;

}

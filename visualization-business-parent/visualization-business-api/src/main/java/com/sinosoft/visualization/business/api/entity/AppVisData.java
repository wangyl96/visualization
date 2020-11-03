package com.sinosoft.visualization.business.api.entity;

import com.sinosoft.visualization.common.basic.entity.BasicEntity;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "app_vis_data")
public class AppVisData extends BasicEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "app_vis_id")
	private Long appVisId;

	private Long visits;

	@Column(name = "app_installation")
	private Long appInstallation;

	private Long registrations;

	@Column(name = "life_day")
	private Long lifeDay;

	private Double product;

	private Double life;

	private Double health;

	private Double wealth;

	private Double gold;

	@Column(name = "vis_date")
	private java.sql.Date visDate;

}

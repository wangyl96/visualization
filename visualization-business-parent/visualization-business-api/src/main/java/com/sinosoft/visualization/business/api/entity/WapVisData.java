package com.sinosoft.visualization.business.api.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "wap_vis_data")
public class WapVisData implements Serializable {

	@Column(name = "wap_vis_id")
	private Long wapVisId;

	@Column(name = "visits_new")
	private Long visitsNew;

	@Column(name = "visits_old")
	private Long visitsOld;

	@Column(name = "visitors_new")
	private Long visitorsNew;

	@Column(name = "visitors_old")
	private Long visitorsOld;

	private Long registrations;

	private Double product;

	private Double life;

	private Double health;

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

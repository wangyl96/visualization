package com.sinosoft.visualization.business.api.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "app_com_vis_data")
public class AppComVisData  implements Serializable {

	@Column(name = "app_vis_data_id")
	private Long appVisDataId;

	private String company;

	private Long visits;

	@Column(name = "appInstallation")
	private Long appinstallation;

	private Long registrations;

	@Column(name = "vis_date")
	private java.sql.Date visDate;

	@Column(name = "is_active")
	private Long isActive;

	private String creator;

	private String modifier;

	@Column(name = "create_date")
	private java.sql.Date createDate;

	@Column(name = "modify_date")
	private java.sql.Date modifyDate;

}

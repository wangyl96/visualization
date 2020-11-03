package com.sinosoft.visualization.business.api.entity;

import com.sinosoft.visualization.common.basic.entity.BasicEntity;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "app_com_vis_data")
public class AppComVisData extends BasicEntity implements Serializable {

	@Id
	@Column(name = "app_vis_data_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appVisDataId;

	private String company;

	private Long visits;

	@Column(name = "appInstallation")
	private Long appinstallation;

	private Long registrations;

	@Column(name = "vis_date")
	private java.sql.Date visDate;


}

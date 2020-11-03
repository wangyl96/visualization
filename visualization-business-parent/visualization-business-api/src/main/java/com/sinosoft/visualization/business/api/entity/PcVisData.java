package com.sinosoft.visualization.business.api.entity;

import com.sinosoft.visualization.common.basic.entity.BasicEntity;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "pc_vis_data")
public class PcVisData extends BasicEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pc_vis_id")
	private Long pcVisId;

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

	private Double wealth;

	@Column(name = "vis_date")
	private java.sql.Date visDate;

}

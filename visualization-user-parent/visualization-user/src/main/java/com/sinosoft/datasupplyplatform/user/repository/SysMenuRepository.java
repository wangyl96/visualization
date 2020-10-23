package com.sinosoft.datasupplyplatform.user.repository;

import com.sinosoft.datasupplyplatform.user.api.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SysMenuRepository
 *
 * @author wangyl
 * Description:
 * Created in: 2019-04-28 14:04
 * Modified by:
 */
public interface SysMenuRepository extends JpaRepository<SysMenu, String> {
	

}

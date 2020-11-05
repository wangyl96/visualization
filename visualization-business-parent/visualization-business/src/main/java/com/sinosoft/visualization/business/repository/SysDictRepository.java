package com.sinosoft.visualization.business.repository;

import com.sinosoft.visualization.business.api.entity.SysDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wangb
 * @date 2020/11/3 13:43
 */
public interface SysDictRepository extends JpaRepository<SysDict,Integer> {

    @Query(value = "SELECT platform FROM sys_dict WHERE is_active = 1 GROUP BY platform",nativeQuery = true)
    List<String> getPlatFrom();

}

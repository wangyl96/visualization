package com.sinosoft.visualization.common.util;

import com.sinosoft.visualization.common.basic.entity.TreeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyl
 * @date 2020-8-25 10:59:52
 */
public class TreeUtil {

    /**
     * 两层循环实现建树
     *
     * @param treeEntities 传入的树实体列表
     * @return List
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> List<T> buildTree(List<? extends TreeEntity<T>> treeEntities, Object root) {
        List<TreeEntity<T>> treeEntityArrayList = new ArrayList<>();
        treeEntities.forEach(treeEntity -> {
            if (treeEntity.getParentId().equals(root)) {
                treeEntityArrayList.add(treeEntity);
            }
            treeEntities.forEach(childTreeEntity -> {
                if (childTreeEntity.getParentId().equals(treeEntity.getId())) {
                    if (treeEntity.getChildren() == null) {
                        treeEntity.setChildren(new ArrayList<>());
                    }
                    treeEntity.add(childTreeEntity);
                }
            });
        });
        return (List<T>) treeEntityArrayList;
    }
}

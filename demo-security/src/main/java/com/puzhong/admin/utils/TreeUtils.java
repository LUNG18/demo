package com.puzhong.admin.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 树形结构工具类，如：菜单、部门等
 */
public class TreeUtils {

    /**
     * 根据pid，构建树节点
     */
    public static <T extends TreeNode<T>> List<T> build(List<T> treeNodes, Long pid) {
        List<T> treeList = new ArrayList<>();
        // pid不能为空
        if (null != pid) {
            for (T treeNode : treeNodes) {
                if (pid.equals(treeNode.getParentId())) {
                    treeList.add(findChildren(treeNodes, treeNode));
                }
            }
        }
        return treeList;
    }

    /**
     * 查找子节点
     */
    private static <T extends TreeNode<T>> T findChildren(List<T> treeNodes, T rootNode) {
        for (T treeNode : treeNodes) {
            if (rootNode.getId().equals(treeNode.getParentId())) {
                rootNode.getChildren().add(findChildren(treeNodes, treeNode));
            }
        }
        return rootNode;
    }

    /**
     * 构建树节点
     */
    public static <T extends TreeNode<T>> List<T> build(List<T> treeNodes) {
        List<T> result = new ArrayList<>();

        Map<Object, T> nodeMap = new LinkedHashMap<>(treeNodes.size());
        for (T treeNode : treeNodes) {
            nodeMap.put(treeNode.getId(), treeNode);
        }

        for (T node : nodeMap.values()) {
            T parent = nodeMap.get(node.getParentId());
            if (parent != null && !(node.getId().equals(parent.getId()))) {
                parent.getChildren().add(node);
                continue;
            }

            result.add(node);
        }

        return result;
    }


    /**
     * 树节点，所有需要实现树节点的，都需要继承该类
     * 继承类中 可以通过重写 getId 和 getParentId 方法 使用别的属性
     */
    @Data
    public static abstract class TreeNode<T> implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 子节点列表
         */
        private List<T> children = new ArrayList<>();
        private Object id;
        private Object parentId;

    }
}

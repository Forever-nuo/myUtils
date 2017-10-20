package com.forever.util.treeUtil;


import java.util.ArrayList;
import java.util.List;


/**
 * @param <T> 元素的类型不明确 使用泛型
 */

public class TreeService<T extends BaseTreeObj<T, ID>, ID> {


    /**
     * 根据所有的元素 和 父节点id 获取其所有的子元素
     *
     * @param allList 所有的元素
     * @param pid     元素的id
     * @return
     */
    public List<T> getChildList(List<T> allList, ID pid) {
        List<T> childList = new ArrayList<T>();
        //遍历所有的元素 元素的pid和当前id相等 就是子元素
        for (T t : allList)
            if (t.getPId().equals(pid))
                childList.add(t);
        return childList;
    }

    /**
     * 根据所有的元素 和 父元素 获取其所有的子元素
     *
     * @param allList 所有的元素
     * @param pT      元素的
     * @return
     */
    public List<T> getChildList(List<T> allList, T pT) {
        return getChildList(allList, pT.getId());
    }

    /**
     * 获取父元素
     *
     * @param allList 所有元素
     * @param ct      子元素
     * @return
     */
    public T getParent(List<T> allList, T ct) {
        for (T t : allList)
            //如果遍历元素的id和 当前的pid相等则  遍历的元素是父元素
            if (t.getId().equals(ct.getPId()))
                return t;
        //全部遍历完没有则为null
        return null;
    }

    /**
     * 获取父元素
     *
     * @param allList 所有元素
     * @param pid     子元素的父id
     * @return
     */
    public T getParent(List<T> allList, ID pid) {
        for (T t : allList)
            //如果遍历元素的id和 当前的pid相等则  遍历的元素是父元素
            if (t.getId().equals(pid))
                return t;
        //全部遍历完没有则为null
        return null;
    }


    /**
     * 获取后代树形
     *
     * @param allList
     * @param pid
     * @return
     */
    public List<T> getChildTreeList(List<T> allList, ID pid) {
        //存放后代元素的List
        List<T> childTreeList = new ArrayList<T>();
        for (T t : allList)
            if (t.getPId().equals(pid)) {
                //递归子元素 设置后代元素
                recursionTree(allList, t);
                childTreeList.add(t);
            }
        return childTreeList;
    }

    /**
     * 获取后代树形
     *
     * @param allList
     * @param pt      父元素
     * @return
     */
    public List<T> getChildTreeList(List<T> allList, T pt) {
        return getChildTreeList(allList, pt.getId());
    }


    /**
     * 递归树 并且设置子元素
     *
     * @param allList
     * @param pt
     */
    public void recursionTree(List<T> allList, T pt) {
        List<T> childList = getChildList(allList, pt);
        pt.setChildList(childList);
        pt.setParent(getParent(allList, pt));
        for (T nextChild : childList)
            recursionTree(allList, nextChild);
    }

    /**
     * @param allList:
     * @param pt:
     * @Description: 获得后代元素id 集合
     * @Author: Forever丶诺
     * @Date: 1:41 2017-10-21
     */
    public List<ID> getChildIdTreeList(List<T> allList, T pt) {
        List<ID> idList = new ArrayList<ID>();
        ID pid = pt.getId();
        recursionIdTree(allList, pid, idList);
        return idList;
    }


    /**
     * @param allList:
     * @param pt:
     * @Description: 获得当前元素的id 及其后代id list
     * @Author: Forever丶诺
     * @Date: 1:44 2017-10-21
     */
    public List<ID> getIdTreeList(List<T> allList, T pt) {
        List<ID> treeChildIdList = getChildIdTreeList(allList, pt);
        treeChildIdList.add(pt.getId());
        return treeChildIdList;
    }


    /**
     * 获取父元素的List
     *
     * @param allList
     * @param ct
     * @return
     */
    public List<T> getParentList(List<T> allList, T ct) {
        List<T> parentList = new ArrayList<T>();
        //先获取当前元素的pid
        ID pId = ct.getPId();
        recursionParentTree(allList, pId, parentList);
        return parentList;
    }


    /**
     * 获取父树节点
     *
     * @param allList
     * @param ct
     * @return
     */
    public List<T> getParentTreeList(List<T> allList, T ct) {
        List<T> parentTreeList = getParentList(allList, ct);
        parentTreeList.add(ct);
        return parentTreeList;
    }


    /**
     * 递归父元素
     *
     * @param allList
     * @param pid
     * @param parentList
     */
    public void recursionParentTree(List<T> allList, ID pid, List<T> parentList) {
        T parent = getParent(allList, pid);
        if (parent != null) {
            recursionParentTree(allList, parent.getPId(), parentList);
            parentList.add(parent);
        }
    }


    public void recursionIdTree(List<T> allList, ID pid, List<ID> idList) {
        for (T nextObj : allList) {
            ID nextPId = nextObj.getPId();
            if (nextPId.equals(pid)) {
                recursionIdTree(allList, nextPId, idList);
                idList.add(nextPId);
            }
        }

    }


    /**
     * 判断是否有下一个子节点
     *
     * @param list
     * @param t
     * @return
     */
    public boolean hasChild(List<T> list, T t) {
        return getChildList(list, t).size() > 0;
    }


}

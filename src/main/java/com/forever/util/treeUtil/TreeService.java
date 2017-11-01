package com.forever.util.treeUtil;

import java.util.List;

/**
 * Created by Forever丶诺 on 2017-10-25.
 */
public interface TreeService<T extends BaseTreeObj<T, ID>, ID> {


    List<T> getChildList(List<T> allList, ID pid);

    List<T> getChildList(List<T> allList, T pT);

    T getParent(List<T> allList, T ct);

    List<T> getParentList(List<T> allList, T ct);

    T getParent(List<T> allList, ID pid);

    List<T> getChildObjList(List<T> allList, ID id);

    List<T> getChildObjList(List<T> allList, T pt);

    List<T> getChildTreeList(List<T> allList, ID pid);

    List<T> getChildTreeList(List<T> allList, T pt);

    void recursionTree(List<T> allList, T pt);

    List<ID> getChildIdTreeList(List<T> allList, T pt);

    List<ID> getChildIdTreeList(List<T> allList, ID pid);

    List<ID> getIdTreeList(List<T> allList, T pt);

    List<T> getParentTreeList(List<T> allList, T ct);

    List<T> getParentTreeList(List<T> allList, ID id);

    List<T> getParentTreeList(List<T> allList, T ct, ID rootId);

    List<T> recursionParentTree(List<T> allList, ID pid, List<T> parentList);

    void recursionParentTree(List<T> allList, T t, List<T> parentList);

    void recursionIdTree(List<T> allList, ID pid, List<ID> idList);

    boolean hasChild(List<T> list, T t);
}

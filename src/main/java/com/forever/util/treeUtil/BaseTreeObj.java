package com.forever.util.treeUtil;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseTreeObj<T,ID> implements Serializable {
    ID id;  //当前id
    ID pId;  //父id
    List<T> childList; //子元素
    T parent; //父元素

}

package com.binzikeji.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Chundekepa
 * @create: 2019-03-12 22:28
 **/
@Data
public abstract class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable {

    private T parent;
    private Boolean isParent;
}

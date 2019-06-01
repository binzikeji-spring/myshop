package com.binzikeji.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Chundekepa
 * @create: 2019-03-08 23:42
 **/
@Data
public abstract class BaseEntity implements Serializable {

    private Long id;
    private Date created;
    private Date updated;
}

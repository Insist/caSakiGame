package com.insist.saki.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Administrator on 2015/11/6.
 */
public abstract class BaseEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

}

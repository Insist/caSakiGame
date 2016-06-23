package com.insist.saki.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/11/6.
 */
@Entity
@Table(name = "test")
public class TestEntity implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6980093847795726310L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

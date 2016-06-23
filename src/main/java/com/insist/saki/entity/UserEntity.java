package com.insist.saki.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/3.
 */
@Entity
@Table(name = "cm_user")
@Data
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String token;
}

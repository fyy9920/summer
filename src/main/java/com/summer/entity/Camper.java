package com.summer.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_camper
 */
@TableName(value ="t_camper")
@Data
public class Camper implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 营员姓名
     */
    private String name;

    /**
     * 营员身份证号
     */
    private String idCard;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;


    /**
     * 0未删除 1删除
     */
    @TableLogic
    private Integer deleteMark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
package com.summer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author tubo
 * @TableName t_activity_user
 */
@TableName(value ="t_activity_user")
@Data
public class ActivityUser implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 选择的营期时间
     */
    private String entryTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
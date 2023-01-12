package com.summer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.summer.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.dto.ActivityDto;
import com.summer.entity.request.ActivityJoinReq;
import com.summer.entity.request.ActivityPageReq;
import com.summer.entity.request.ActivityReq;

/**
 *
 */
public interface IActivityService extends IService<Activity> {

    /**
     * 分页查询活动
     * @param activityReq
     * @return
     */
    IPage<ActivityDto> activityPageList(ActivityPageReq activityReq) throws IllegalAccessException;

    /**
     * 参加活动
     * @param activityJoinReq
     * @return
     */
    boolean joinActivity(ActivityJoinReq activityJoinReq);

    /**
     * 通过id查询详情
     * @param id
     * @return
     */
    ActivityDto getActivityById(Integer id);

    /**
     *
     * @param activityReq
     * @return
     */
    boolean saveActivity(ActivityReq activityReq);
}

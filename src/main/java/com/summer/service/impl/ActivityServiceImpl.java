package com.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.entity.Activity;
import com.summer.entity.ActivityUser;
import com.summer.entity.Order;
import com.summer.entity.WechatUser;
import com.summer.entity.dto.ActivityDto;
import com.summer.entity.request.ActivityJoinReq;
import com.summer.entity.request.ActivityPageReq;
import com.summer.entity.request.ActivityReq;
import com.summer.exception.ServiceException;
import com.summer.mapper.ActivityMapper;
import com.summer.service.IActivityService;
import com.summer.service.IActivityUserService;
import com.summer.service.IWechatUserService;
import com.summer.service.OrderService;
import com.summer.utils.QueryUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tubo
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Resource
    private IActivityUserService activityUserService;

    @Resource
    private IWechatUserService wechatUserService;

    @Resource
    private OrderService orderService;

    @Override
    public IPage<ActivityDto> activityPageList(ActivityPageReq activityReq) throws IllegalAccessException {
        QueryWrapper<Activity> queryWrapper = QueryUtils.getQueryWrapper(activityReq);
        Page<Activity> page = new Page<>(activityReq.getPageNum(), activityReq.getPageSize());
        return this.page(page, queryWrapper).convert(activity -> {
            ActivityDto activityDto = new ActivityDto();
            BeanUtils.copyProperties(activity, activityDto);
            return getActivityDto(activity, activityDto);
        });
    }

    private ActivityDto getActivityDto(Activity activity, ActivityDto activityDto) {
        if (StringUtils.isNotBlank(activity.getEntryTime())) {
            activityDto.setEntryTime(Arrays.asList(activity.getEntryTime().split(StringPool.COMMA)));
        }
        if (StringUtils.isNotBlank(activity.getSpecies())) {
            activityDto.setSpecies(Arrays.asList(activity.getSpecies().split(StringPool.COMMA)));
        }

        return activityDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean joinActivity(ActivityJoinReq activityJoinReq) {
        //todo 默认支付成功，等后面接入支付后，再根据支付的状态码进行支付状态的赋值
        Order order = new Order();
        BeanUtils.copyProperties(activityJoinReq,order);
        if(activityJoinReq.getType() == 1 ) {
            Optional.ofNullable(activityJoinReq.getCamperId()).orElseThrow(() -> new ServiceException("营员id不能为空"));
            Optional.ofNullable(activityJoinReq.getEntryTime()).orElseThrow(() -> new ServiceException("营期时间不能为空"));
            Optional.ofNullable(activityJoinReq.getSpecies()).orElseThrow(() -> new ServiceException("种类不能为空"));
            String camperStr = activityJoinReq.getCamperId().stream().collect(Collectors.joining(StringPool.COMMA));
            order.setCamperId(camperStr);
            //新增围观人数关联表,只有type=1的时候才有围观，也就是指活动才有围观人数这些
            ActivityUser activityUser = new ActivityUser();
            activityUser.setActivityId(activityJoinReq.getActivityId());
            activityUser.setUserId(1);
            activityUser.setEntryTime(activityJoinReq.getEntryTime());
            activityUserService.save(activityUser);
        }
        order.setUserId(1);
        order.setCreateBy(1);
        //先默认赋值为1，后面根据状态来判断，默认已支付
        order.setStatus(1);
        order.setOrderNum(UUID.randomUUID().toString().replace("-",""));
        return orderService.save(order);
    }


    @Override
    public ActivityDto getActivityById(Integer id) {
        Activity activity = getById(id);
        Optional.ofNullable(activity).orElseThrow(() -> new ServiceException("活动不存在"));
        ActivityDto activityDto = new ActivityDto();
        BeanUtils.copyProperties(activity, activityDto);
        List<ActivityUser> activityUsers = activityUserService.getBaseMapper().selectList(new LambdaQueryWrapper<ActivityUser>().eq(ActivityUser::getActivityId, activity.getId()).orderByDesc(ActivityUser::getEntryTime));
        if (CollectionUtils.isNotEmpty(activityUsers)) {
            List<WechatUser> userList = new ArrayList<>();
            activityUsers.forEach(activityUser -> {
                WechatUser wechatUser = wechatUserService.getById(activityUser.getUserId());
                userList.add(wechatUser);
            });
            activityDto.setWechatUserList(userList);
            activityDto.setOnlookers(activityUsers.size());
            activityDto.setLastDate(activityUsers.get(0).getEntryTime());

        }
        return getActivityDto(activity, activityDto);
    }

    @Override
    public boolean saveActivity(ActivityReq activityReq) {
        return false;
    }
}





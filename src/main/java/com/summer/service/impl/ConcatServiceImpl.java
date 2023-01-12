package com.summer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.entity.Concat;
import com.summer.entity.Opinion;
import com.summer.entity.request.ConcatReq;
import com.summer.exception.ServiceException;
import com.summer.service.ConcatService;
import com.summer.mapper.ConcatMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tubo
 * @description 针对表【t_concat(合作联系)】的数据库操作Service实现
 * @createDate 2022-12-29 10:24:39
 */
@Service
public class ConcatServiceImpl extends ServiceImpl<ConcatMapper, Concat>  implements ConcatService {

    @Override
    public boolean updateContact(ConcatReq concatReq) {
        Integer updateBy = 1;
        Concat concat = getById(concatReq.getId());
        Optional.ofNullable(concat).orElseThrow(() -> new ServiceException("联系方式不存在!"));
        concat.setUpdateBy(updateBy);
        concat.setPhone(concatReq.getPhone());
        return updateById(concat);
    }
}





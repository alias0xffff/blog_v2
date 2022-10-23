package com.minzheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minzheng.blog.entity.DeviceInfo;
import com.minzheng.blog.vo.ConditionVO;
import com.minzheng.blog.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeviceInfoService extends IService<DeviceInfo> {
    void add(DeviceInfo deviceInfo);

    void update(DeviceInfo deviceInfo);

    void deleteById(Integer integer);

    List<DeviceInfo> list(ConditionVO conditionVO);

}

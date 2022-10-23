package com.minzheng.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minzheng.blog.dao.DeviceInfoDao;
import com.minzheng.blog.entity.DeviceInfo;
import com.minzheng.blog.service.DeviceInfoService;
import com.minzheng.blog.vo.ConditionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeviceInfoServiceImpl  extends ServiceImpl<DeviceInfoDao, DeviceInfo> implements DeviceInfoService {
    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Override
    public void add(DeviceInfo deviceInfo) {
        deviceInfoDao.insert(deviceInfo);
        return;
    }

    @Override
    public void update(DeviceInfo deviceInfo) {
        deviceInfoDao.updateById(deviceInfo);
        return;
    }

    @Override
    public void deleteById(Integer id) {
        deviceInfoDao.deleteById(id);
        return;
    }

    @Override
    public List<DeviceInfo> list(ConditionVO conditionVO) {
        List<DeviceInfo> deviceInfos = deviceInfoDao.selectList(null);
        return deviceInfos;

    }
}

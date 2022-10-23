package com.minzheng.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minzheng.blog.dto.DeviceBackDTO;
import com.minzheng.blog.dto.UserBackDTO;
import com.minzheng.blog.entity.DeviceInfo;
import com.minzheng.blog.entity.UserAuth;
import com.minzheng.blog.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


    @Repository
    public interface DeviceInfoDao extends BaseMapper<DeviceInfo> {


    }


package com.minzheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minzheng.blog.entity.ApplyList;
import com.minzheng.blog.entity.ChatRecord;
import com.minzheng.blog.entity.DeviceInfo;
import com.minzheng.blog.vo.ConditionVO;

import java.util.List;

/**
 * @author chenjiashuai
 * @since 2022/9/29
 */
public interface ApplyListService extends IService<ApplyList> {
    void add(ApplyList applyList);

    void update(ApplyList applyList);

    void deleteById(Integer integer);

    List<ApplyList> list(ConditionVO conditionVO);
}

package com.minzheng.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minzheng.blog.dao.ApplyListDao;
import com.minzheng.blog.entity.ApplyList;
import com.minzheng.blog.entity.DeviceInfo;
import com.minzheng.blog.service.ApplyListService;
import com.minzheng.blog.vo.ConditionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjiashuai
 * @since 2022/9/29
 */
@Service
@Slf4j
public class ApplyListServiceImpl extends ServiceImpl<ApplyListDao, ApplyList> implements ApplyListService {
    @Autowired
    private ApplyListDao applyListDao;
    @Override
    public void add(ApplyList applyList) {
        applyListDao.insert(applyList);
    }

    @Override
    public void update(ApplyList applyList) {
        applyListDao.insert(applyList);
    }

    @Override
    public void deleteById(Integer id) {
        applyListDao.deleteById(id);
    }

    @Override
    public List<ApplyList> list(ConditionVO conditionVO) {
        List<ApplyList> applyLists = applyListDao.selectList(null);
        return applyLists;
    }
}

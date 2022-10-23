package com.minzheng.blog.controller;

import com.minzheng.blog.entity.ApplyList;
import com.minzheng.blog.entity.DeviceInfo;
import com.minzheng.blog.service.ApplyListService;
import com.minzheng.blog.service.DeviceInfoService;
import com.minzheng.blog.vo.ConditionVO;
import com.minzheng.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenjiashuai
 * @since 2022/9/29
 */
@RestController
@RequestMapping("/applylist")
public class ApplyListController {
    @Autowired
    private ApplyListService applyListService;

    @PostMapping("/add")
    public Result<ApplyList> addDevice(@RequestBody ApplyList applyList) {
        applyListService.add(applyList);
        return Result.ok();
    }

    @GetMapping("/list")
    public Result<List<ApplyList>> pageList(ConditionVO conditionVO) {
        List<ApplyList> applyLists = applyListService.list(conditionVO);
        return Result.ok(applyLists);
    }

    @DeleteMapping("/delete")
    public Result<?> deleteDevice(@RequestParam Integer id){
        applyListService.deleteById(id);
        return Result.ok();
    }
}

package com.minzheng.blog.controller;

import com.minzheng.blog.entity.DeviceInfo;
import com.minzheng.blog.service.DeviceInfoService;
import com.minzheng.blog.vo.ConditionVO;
import com.minzheng.blog.vo.PageResult;
import com.minzheng.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deviceinfo")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;

    @PostMapping("/add")
    public Result<DeviceInfo> addDevice(@RequestBody DeviceInfo deviceInfo) {
        deviceInfoService.add(deviceInfo);
        return Result.ok();
    }

    @GetMapping("/list")
    public Result<List<DeviceInfo>> pageList( ConditionVO conditionVO) {
        List<DeviceInfo> deviceInfoPageResult = deviceInfoService.list(conditionVO);
        return Result.ok(deviceInfoPageResult);
    }

    @DeleteMapping("/delete")
    public Result<?> deleteDevice(@RequestParam Integer id){
        deviceInfoService.deleteById(id);
        return Result.ok();
    }
}

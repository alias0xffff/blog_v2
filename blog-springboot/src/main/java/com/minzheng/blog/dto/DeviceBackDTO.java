package com.minzheng.blog.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceBackDTO {

    /**
     * 设备ID
     */
    private Integer id;


    /**
     * 设备名
     */
    private String deviceName;

    /**
     * 设备图像
     */
    private String avatar;

    /**
     * 设备功能
     */
    private String func;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

}

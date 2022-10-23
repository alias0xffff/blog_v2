package com.minzheng.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_apply_list")
public class ApplyList {
    /**
     * 设备ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField("functions")
    private String func;

    /**
     * 申请人姓名
     */
    private String applyName;

    /**
     * 申请理由
     */
    private String reason;

    /**
     * 申请要执行的操作
     */
    private String oper;

    /**
     * 是否禁用
     */
    private int isDisable;


    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}

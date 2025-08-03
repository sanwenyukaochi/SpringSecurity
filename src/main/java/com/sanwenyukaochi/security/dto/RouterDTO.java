package com.sanwenyukaochi.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouterDTO {
    
    private Long id;
    
    /**
     * 权限名称
     */
    private String name;
    
    /**
     * 权限标识码
     */
    private String code;
    
    /**
     * 路径/按钮绑定路径
     */
    private String path;
    
    /**
     * 类型：1-菜单，2-按钮
     */
    private String type;
    
    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 子权限列表
     */
    private List<RouterDTO> children;
}
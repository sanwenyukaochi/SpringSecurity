package com.springframework.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "t_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Comment("主键，自动增长，权限ID")
    private Long id;

    @Column(name = "name", length = 30)
    @Comment("权限名称")
    private String name;

    @Column(name = "code", length = 30)
    @Comment("权限编码（唯一标识）")
    private String code;

    @Column(name = "url", length = 30)
    @Comment("接口或路由路径")
    private String url;

    @Column(name = "type", length = 30)
    @Comment("权限类型（菜单/按钮/接口等）")
    private String type;

    @Column(name = "parent_id")
    @Comment("父级权限ID")
    private Long parentId;

    @Column(name = "order_no")
    @Comment("排序号")
    private Long orderNo;

    @Column(name = "icon", length = 100)
    @Comment("菜单图标")
    private String icon;

    @Column(name = "component", length = 50)
    @Comment("菜单对应要渲染的Vue组件名称")
    private String component;
}

package com.springframework.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "t_role_permission",
        indexes = {
                @Index(name = "idx_28593_t_role_permission_ibfk_1", columnList = "role_id"),
                @Index(name = "idx_28593_t_role_permission_ibfk_2", columnList = "permission_id")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Comment("主键，自动增长，关系ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",
            foreignKey = @ForeignKey(name = "t_role_permission_ibfk_1"))
    @Comment("关联的角色")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id",
            foreignKey = @ForeignKey(name = "t_role_permission_ibfk_2"))
    @Comment("关联的权限")
    private Permission permission;
}

package com.springframework.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "t_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Comment("主键，自动增长，角色ID")
    private Long id;

    @Column(name = "role", length = 30)
    @Comment("角色标识，例如：admin")
    private String role;

    @Column(name = "role_name", length = 30)
    @Comment("角色名称，例如：管理员")
    private String roleName;
}

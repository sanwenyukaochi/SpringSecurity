package com.springframework.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "t_user_role",
        indexes = {
                @Index(name = "idx_28628_t_user_role_ibfk_1", columnList = "user_id"),
                @Index(name = "idx_28628_t_user_role_ibfk_2", columnList = "role_id")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Comment("主键，自动增长，关系ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "t_user_role_ibfk_1"))
    @Comment("关联的用户")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",
            foreignKey = @ForeignKey(name = "t_user_role_ibfk_2"))
    @Comment("关联的角色")
    private Role role;
}

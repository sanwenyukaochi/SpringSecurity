package com.springframework.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "t_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "idx_28623_email", columnNames = "email"),
                @UniqueConstraint(name = "idx_28623_login_act", columnNames = "login_act"),
                @UniqueConstraint(name = "idx_28623_phone", columnNames = "phone")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Comment("主键，自动增长，用户ID")
    private Long id;

    @Column(name = "login_act", length = 32, nullable = false)
    @Comment("登录账号")
    private String loginAct;

    @Column(name = "login_pwd", length = 64, nullable = false)
    @Comment("登录密码")
    private String loginPwd;

    @Column(name = "name", length = 32)
    @Comment("用户姓名")
    private String name;

    @Column(name = "phone", length = 18)
    @Comment("用户手机")
    private String phone;

    @Column(name = "email", length = 64)
    @Comment("用户邮箱")
    private String email;

    @Column(name = "account_no_expired")
    @Comment("账户是否没有过期，0已过期 1正常")
    private Long accountNoExpired;

    @Column(name = "credentials_no_expired")
    @Comment("密码是否没有过期，0已过期 1正常")
    private Long credentialsNoExpired;

    @Column(name = "account_no_locked")
    @Comment("账号是否没有锁定，0已锁定 1正常")
    private Long accountNoLocked;

    @Column(name = "account_enabled")
    @Comment("账号是否启用，0禁用 1启用")
    private Long accountEnabled;

    @Column(name = "create_time")
    @Comment("创建时间")
    private Date createTime;

    @Column(name = "create_by")
    @Comment("创建人")
    private Long createBy;

    @Column(name = "edit_time")
    @Comment("编辑时间")
    private Date editTime;

    @Column(name = "edit_by")
    @Comment("编辑人")
    private Long editBy;

    @Column(name = "last_login_time")
    @Comment("最近登录时间")
    private Date lastLoginTime;
}

package com.secure.security.domain.model.entity.relation;

import com.secure.security.domain.model.entity.User;
import com.secure.security.domain.model.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;
import com.secure.security.domain.model.entity.base.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_user_role_rel",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_role", columnNames = {"user_id", "role_id"})
        })
@Comment("用户角色关联表")
public class UserRole extends BaseEntity {

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, 
            foreignKey = @ForeignKey(name = "fk_user_role_user_id"))
    @Comment("用户Id")
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_role_role_id"))
    @Comment("角色Id")
    private Role role;
}

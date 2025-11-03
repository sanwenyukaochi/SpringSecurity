package com.secure.security.domain.model.entity.relation;

import com.secure.security.domain.model.entity.Role;
import com.secure.security.domain.model.entity.Permission;
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
@Table(name = "sys_role_permission_rel",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_role_permission", columnNames = {"role_id", "permission_id"})
        })
@Comment("角色权限关联表")
public class RolePermission extends BaseEntity {

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_role_permission_role_id"))
    @Comment("角色Id")
    private Role role;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_role_permission_permission_id"))
    @Comment("权限Id")
    private Permission permission;
}

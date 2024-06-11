package com.company.jmixpm.security;

import com.company.jmixpm.entity.PersonalData;
import com.company.jmixpm.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "HRSpecialistRole", code = HRSpecialistRole.CODE, scope = "UI")
public interface HRSpecialistRole {
    String CODE = "h-r-specialist-role";

    @MenuPolicy(menuIds = {"User.list", "PersonalData.list"})
    @ViewPolicy(viewIds = {"User.list", "User.detail", "PersonalData.detail", "PersonalData.list"})
    void screens();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();

    @EntityAttributePolicy(entityClass = PersonalData.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = PersonalData.class, actions = EntityPolicyAction.ALL)
    void personalData();
}
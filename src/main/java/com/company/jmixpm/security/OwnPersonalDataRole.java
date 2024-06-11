package com.company.jmixpm.security;

import com.company.jmixpm.entity.PersonalData;
import com.company.jmixpm.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

@RowLevelRole(name = "OwnPersonalData", code = OwnPersonalDataRole.CODE)
public interface OwnPersonalDataRole {
    String CODE = "own-personal-data";

    @PredicateRowLevelPolicy(entityClass = PersonalData.class, actions = RowLevelPolicyAction.UPDATE)
    default RowLevelBiPredicate<PersonalData, ApplicationContext> personalDataPredicate() {
        return (personalData, applicationContext) -> {
            User currentUser = ((User) applicationContext.getBean(CurrentAuthentication.class).getUser());
            return currentUser.equals(personalData.getUser());
        };
    }
}
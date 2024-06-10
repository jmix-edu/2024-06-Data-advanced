package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import io.jmix.core.DataManager;
import io.jmix.core.validation.group.UiComponentChecks;
import io.jmix.core.validation.group.UiCrossFieldChecks;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated(value = {Default.class, UiComponentChecks.class, UiCrossFieldChecks.class})
@Component
public class ProjectsService {
    private final DataManager dataManager;

    public ProjectsService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void save(@NotNull @Valid Project project) {
        dataManager.save(project);
    }
}
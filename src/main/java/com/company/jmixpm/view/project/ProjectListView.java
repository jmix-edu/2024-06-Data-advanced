package com.company.jmixpm.view.project;

import com.company.jmixpm.datatype.ProjectLabels;
import com.company.jmixpm.entity.Project;

import com.company.jmixpm.entity.Roadmap;
import com.company.jmixpm.entity.User;
import com.company.jmixpm.view.main.MainView;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.UiComponentsGenerator;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.grid.editor.DataGridEditor;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Route(value = "projects", layout = MainView.class)
@ViewController("Project.list")
@ViewDescriptor("project-list-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "64em")
public class ProjectListView extends StandardListView<Project> {

    @Autowired
    private UiComponentsGenerator uiComponentsGenerator;
    @ViewComponent
    private DataGrid<Project> projectsDataGrid;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;
    @ViewComponent
    private CollectionContainer<Project> projectsDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInit(final InitEvent event) {
        DataGridEditor<Project> editor = projectsDataGrid.getEditor();
    }

    @Subscribe(id = "createWithDataManagerBtn", subject = "clickListener")
    public void onCreateWithDataManagerBtnClick(final ClickEvent<JmixButton> event) {

        Project project = unconstrainedDataManager.create(Project.class);
        project.setName("Project " + RandomStringUtils.randomAlphabetic(5));

        User user = (User) currentAuthentication.getUser();
        project.setManager(user);
        Roadmap roadmap = unconstrainedDataManager.create(Roadmap.class);
        roadmap.setName("Roadmap: " + project.getName());
        roadmap.setStartDate(LocalDate.now());
        project.setRoadmap(roadmap);
        // Bean validation is invoked while saving entity instance
        project.setProjectLabels(new ProjectLabels(List.of("task", "enhancement", "bug")));

        Project saved = unconstrainedDataManager.save(project, roadmap).get(project);
        projectsDc.getMutableItems().add(saved);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        int newProjectsCount = dataManager.loadValue("select count(e) from Project e" +
                        " where :session_isManager = TRUE and e.status = @enum(com.company.jmixpm.entity.ProjectStatus.NEW)", Integer.class)
                .one();
        if (newProjectsCount != 0) {
            notifications.create("New projects", "Projects with NEW status: " + newProjectsCount)
                    .withPosition(Notification.Position.TOP_END)
                    .show();
        }
    }
    
    
}
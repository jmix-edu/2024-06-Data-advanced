package com.company.jmixpm.view.project;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.*;
import io.jmix.flowui.view.*;

@Route(value = "projects-master-detail", layout = MainView.class)
@ViewController("ProjectMasterDetail.list")
@ViewDescriptor("project-master-detail-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "64em")
public class ProjectMasterDetailView extends StandardListView<Project> {

    @ViewComponent
    private DataContext dataContext;

    @ViewComponent
    private CollectionContainer<Project> projectsDc;

    @ViewComponent
    private InstanceContainer<Project> projectDc;

    @ViewComponent
    private InstanceLoader<Project> projectDl;

    @ViewComponent
    private VerticalLayout listLayout;

    @ViewComponent
    private FormLayout form;

    @ViewComponent
    private HorizontalLayout detailActions;

    @Subscribe
    public void onInit(final InitEvent event) {
        updateControls(false);
    }

    @Subscribe("projectsDataGrid.create")
    public void onProjectsDataGridCreate(final ActionPerformedEvent event) {
        dataContext.clear();
        Project entity = dataContext.create(Project.class);
        projectDc.setItem(entity);
        updateControls(true);
    }

    @Subscribe("projectsDataGrid.edit")
    public void onProjectsDataGridEdit(final ActionPerformedEvent event) {
        updateControls(true);
    }

    @Subscribe("saveBtn")
    public void onSaveButtonClick(final ClickEvent<JmixButton> event) {
        dataContext.save();
        projectsDc.replaceItem(projectDc.getItem());
        updateControls(false);
    }

    @Subscribe("cancelBtn")
    public void onCancelButtonClick(final ClickEvent<JmixButton> event) {
        dataContext.clear();
        projectDl.load();
        updateControls(false);
    }

    @Subscribe(id = "projectsDc", target = Target.DATA_CONTAINER)
    public void onProjectsDcItemChange(final InstanceContainer.ItemChangeEvent<Project> event) {
        Project entity = event.getItem();
        dataContext.clear();
        if (entity != null) {
            projectDl.setEntityId(entity.getId());
            projectDl.load();
        } else {
            projectDl.setEntityId(null);
            projectDc.setItem(null);
        }
    }

    private void updateControls(boolean editing) {
        form.getChildren().forEach(component -> {
            if (component instanceof HasValueAndElement<?, ?> field) {
                field.setReadOnly(!editing);
            }
        });

        detailActions.setVisible(editing);
        listLayout.setEnabled(!editing);
    }
}
package com.company.jmixpm.view.project;

import com.company.jmixpm.entity.Project;

import com.company.jmixpm.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.UiComponentsGenerator;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.grid.editor.DataGridEditor;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Subscribe
    public void onInit(final InitEvent event) {
        DataGridEditor<Project> editor = projectsDataGrid.getEditor();
    }
    
    
}
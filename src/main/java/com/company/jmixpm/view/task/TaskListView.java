package com.company.jmixpm.view.task;

import com.company.jmixpm.entity.Task;

import com.company.jmixpm.view.main.MainView;

import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "tasks", layout = MainView.class)
@ViewController("Task_.list")
@ViewDescriptor("task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListView extends StandardListView<Task> {

    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private DialogWindows dialogWindows;

//    @Supply(to = "tasksDataGrid.label", subject = "renderer")
//    private Renderer<Task> tasksDataGridLabelRenderer() {
//
//        return new ComponentRenderer<JmixButton, Task>(task -> {
//            JmixButton btn = uiComponents.create(JmixButton.class);
//            btn.addClickListener(click -> {
//                dialogWindows.detail(this, Task.class)
//                        .open();
//            });
//            return btn;
//        });
//
//    }
}
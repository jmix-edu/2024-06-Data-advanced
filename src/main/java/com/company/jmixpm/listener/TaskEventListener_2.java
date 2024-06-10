package com.company.jmixpm.listener;

import com.company.jmixpm.entity.Task;
import io.jmix.core.event.EntityLoadingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TaskEventListener_2 {

    @EventListener
    public void onTaskLoading(final EntityLoadingEvent<Task> event) {

    }
}
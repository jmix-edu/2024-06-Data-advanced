package com.company.jmixpm.listener;

import com.company.jmixpm.entity.Task;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TaskEventListener_1 {

    @TransactionalEventListener
    public void onTaskChangedAfterCommit(final EntityChangedEvent<Task> event) {

    }
}
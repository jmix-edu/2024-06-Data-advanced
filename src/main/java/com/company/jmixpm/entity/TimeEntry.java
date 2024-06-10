package com.company.jmixpm.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import io.jmix.core.metamodel.datatype.DatatypeFormatter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity(annotatedPropertiesOnly = false)
@Table(name = "TIME_ENTRY", indexes = {
        @Index(name = "IDX_TIME_ENTRY_TASK", columnList = "TASK_ID"),
        @Index(name = "IDX_TIME_ENTRY_USER", columnList = "USER_ID")
})
@Entity
public class TimeEntry {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "TASK_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Task task;

    @Column(name = "TIME_SPENT", nullable = false)
    @Positive(message = "Spent time can not be less or equal zero")
    @NotNull
    private Integer timeSpent;

    @PastOrPresent(message = "{msg://com.company.jmixpm.entity/TimeEntry.entryDate.validation.PastOrPresent}")
    @Column(name = "ENTRY_DATE", nullable = false)
    @NotNull
    private LocalDateTime entryDate;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Task user;

    @Size(message = "{msg://com.company.jmixpm.entity/TimeEntry.description.validation.Size}", min = 100, max = 500)
    @Column(name = "DESCRIPTION")
    @Lob
    @Length(min = 100, message = "{msg://com.company.jmixpm.entity/timeEntry.description.error}")
    @JmixProperty
    private String description;

    @JmixProperty
    @Transient
    private String transientString;

//    @Enumerated(EnumType.ORDINAL)
//    @Enumerated(EnumType.STRING)

    public String getTransientString() {
        return transientString;
    }

    public void setTransientString(String transientString) {
        this.transientString = transientString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getUser() {
        return user;
    }

    public void setUser(Task user) {
        this.user = user;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"entryDate", "task"})
    public String getInstanceName(MetadataTools metadataTools, DatatypeFormatter datatypeFormatter) {
        return String.format("%s %s",
                datatypeFormatter.formatLocalDateTime(entryDate),
                metadataTools.format(task));
    }
}
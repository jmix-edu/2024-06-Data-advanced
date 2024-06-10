package com.company.jmixpm.app;

import com.company.jmixpm.datatype.ProjectLabels;
import com.company.jmixpm.datatype.ProjectLabelsDatatype;
import com.company.jmixpm.entity.Project;
import com.google.common.base.Strings;
import com.vaadin.flow.component.Component;
import io.jmix.core.JmixOrder;
import io.jmix.core.metamodel.datatype.Datatype;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.core.metamodel.model.MetaProperty;
import io.jmix.core.metamodel.model.MetaPropertyPath;
import io.jmix.core.metamodel.model.Range;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.ComponentGenerationContext;
import io.jmix.flowui.component.ComponentGenerationStrategy;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.sys.ValuePathHelper;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;

@org.springframework.stereotype.Component("ProjectFieldsComponentGenerationStrategy")
public class ProjectFieldsComponentGenerationStrategy implements ComponentGenerationStrategy, Ordered {

    private final UiComponents uiComponents;

    public ProjectFieldsComponentGenerationStrategy(UiComponents uiComponents) {
        this.uiComponents = uiComponents;
    }

    @Nullable
    @Override
    public Component createComponent(ComponentGenerationContext context) {
        String checkProperty = context.getProperty();

        if (Strings.isNullOrEmpty(checkProperty)) {
            return null;
        }

        String[] properties = ValuePathHelper.parse(context.getProperty());

        if (properties.length > 1) {
            checkProperty = properties[properties.length -1];
        }

        if (!"projectLabels".equals(checkProperty)) {
            return null;
        }

        MetaClass metaClass = context.getMetaClass();

        MetaPropertyPath propertyPath = metaClass.getPropertyPath(context.getProperty());

        if (propertyPath != null) {
            metaClass = propertyPath.getMetaProperty().getDomain();
        }
        if (!metaClass.getJavaClass().equals(Project.class)) {
            return null;
        }

        MetaProperty property = metaClass.getProperty(checkProperty);

        Range range = property.getRange();

        if (range.isDatatype()) {
            Datatype projectLabelsDatatype = range.asDatatype();
            if (projectLabelsDatatype instanceof ProjectLabelsDatatype) {
                return create(context);
            }
        }

        return null;
    }

    private Component create(ComponentGenerationContext context) {
        TypedTextField<ProjectLabels> textField = uiComponents.create(TypedTextField.class);
        textField.setValueSource(context.getValueSource());
        textField.setReadOnly(false);
        return textField;
    }

    @Override
    public int getOrder() {
        return JmixOrder.HIGHEST_PRECEDENCE + 10;
    }
}

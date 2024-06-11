package com.company.jmixpm.view.personaldata;

import com.company.jmixpm.entity.PersonalData;
import com.company.jmixpm.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.AccessManager;
import io.jmix.core.accesscontext.InMemoryCrudEntityContext;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "personalDatas/:id", layout = MainView.class)
@ViewController("PersonalData.detail")
@ViewDescriptor("personal-data-detail-view.xml")
@EditedEntityContainer("personalDataDc")
public class PersonalDataDetailView extends StandardDetailView<PersonalData> {
    @ViewComponent
    private InstanceContainer<PersonalData> personalDataDc;
    @Autowired
    private AccessManager accessManager;
    @ViewComponent
    private TypedTextField<String> pasportNumberField;
    @ViewComponent
    private TypedTextField<String> birthDateField;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        InMemoryCrudEntityContext inMemoryCrudEntityContext =
                new InMemoryCrudEntityContext(personalDataDc.getEntityMetaClass(), getApplicationContext());
        accessManager.applyRegisteredConstraints(inMemoryCrudEntityContext);

        boolean updateForbidden = inMemoryCrudEntityContext.isUpdatePermitted(getEditedEntity());
        pasportNumberField.setReadOnly(!updateForbidden);
        birthDateField.setReadOnly(!updateForbidden);
    }
    
    
}
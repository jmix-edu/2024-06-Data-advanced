package com.company.jmixpm.view.personaldata;

import com.company.jmixpm.entity.PersonalData;
import com.company.jmixpm.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "personalDatas", layout = MainView.class)
@ViewController("PersonalData.list")
@ViewDescriptor("personal-data-list-view.xml")
@LookupComponent("personalDatasDataGrid")
@DialogMode(width = "64em")
public class PersonalDataListView extends StandardListView<PersonalData> {
}
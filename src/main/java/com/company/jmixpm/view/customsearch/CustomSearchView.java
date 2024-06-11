package com.company.jmixpm.view.customsearch;


import com.company.jmixpm.entity.Customer;
import com.company.jmixpm.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = "custom-search-view", layout = MainView.class)
@ViewController("CustomSearchView")
@ViewDescriptor("custom-search-view.xml")
public class CustomSearchView extends StandardView {

    private static final Logger log = LoggerFactory.getLogger(CustomSearchView.class);

    @ViewComponent
    private CollectionContainer<Customer> customersDc;

    @Subscribe
    public void onInit(InitEvent event) {
        log.info("InitEvent - customers size: " + customersDc.getItems().size());
    }
}
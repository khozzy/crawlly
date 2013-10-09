package com.indeed.presentation.other;


import org.primefaces.component.datalist.DataList;
import org.primefaces.component.export.ExcelExporter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class CustomXLSExporter extends ExcelExporter {

    @Override
    protected String exportValue(FacesContext context, UIComponent component) {
        if (component instanceof DataList) {
            DataList dataList = (DataList) component;
            return "custom";
        } else {
            return super.exportValue(context, component);
        }
    }
}

package com.clouway.gwtphonegap.client.dnd;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class EmployeeBox extends Composite implements DndController.Draggable {
  interface EmployeeBoxUiBinder extends UiBinder<Widget, EmployeeBox> {
  }

  private static EmployeeBoxUiBinder binder = GWT.create(EmployeeBoxUiBinder.class);

  public EmployeeBox() {

    initWidget(binder.createAndBindUi(this));

  }
}
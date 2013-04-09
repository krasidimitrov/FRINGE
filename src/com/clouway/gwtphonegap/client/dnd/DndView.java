package com.clouway.gwtphonegap.client.dnd;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class DndView extends Composite {
  interface DndViewUiBinder extends UiBinder<Widget, DndView> {
  }

  private static DndViewUiBinder binder = GWT.create(DndViewUiBinder.class);


  @UiField
  HTMLPanel mainPanel;
  private DndController<EmployeeBox, DndController.DropZone<EmployeeBox, DndController.DraggableSource<EmployeeBox>>> dndController = new DndController<EmployeeBox, DndController.DropZone<EmployeeBox, DndController.DraggableSource<EmployeeBox>>>();

  public DndView() {

    initWidget(binder.createAndBindUi(this));
    TeamBox teamBox = new TeamBox(dndController);
    TeamBox teamBox2 = new TeamBox(dndController);
    teamBox.addEmployee(new EmployeeBox());
    mainPanel.add(teamBox);
    mainPanel.add(teamBox2);
  }

}
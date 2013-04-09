package com.clouway.gwtphonegap.client.dnd;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class TeamBox extends Composite implements DndController.DraggableSource<EmployeeBox>, DndController.DropZone<EmployeeBox, DndController.DraggableSource<EmployeeBox>> {
  @Override
  public void onDraggableDrop(EmployeeBox draggable) {
  }

  @Override
  public void onDragStart(EmployeeBox draggable) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void onDrop(EmployeeBox draggable, DndController.DraggableSource<EmployeeBox> source) {
    mainPanel.add(draggable);
  }

  @Override
  public void onDragOver(EmployeeBox draggable, DndController.DraggableSource<EmployeeBox> source) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void onDragLeave(EmployeeBox draggable) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  interface TeamBoxUiBinder extends UiBinder<Widget, TeamBox> {
  }

  private static TeamBoxUiBinder binder = GWT.create(TeamBoxUiBinder.class);

  @UiField
  HTMLPanel mainPanel;
  private DndController<EmployeeBox, DndController.DropZone<EmployeeBox, DndController.DraggableSource<EmployeeBox>>> dndController;

  public TeamBox(DndController<EmployeeBox, DndController.DropZone<EmployeeBox, DndController.DraggableSource<EmployeeBox>>> dndController) {

    initWidget(binder.createAndBindUi(this));
    this.dndController = dndController;
    dndController.registerDropZone(this);
    dndController.registerDraggableSource(this);

  }

  public void addEmployee(EmployeeBox employeeBox){
    dndController.registerDraggable(employeeBox);
    mainPanel.add(employeeBox);
  }
}
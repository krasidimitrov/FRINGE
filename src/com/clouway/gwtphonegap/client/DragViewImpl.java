package com.clouway.gwtphonegap.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwtquery.plugins.draggable.client.DraggableOptions;
import gwtquery.plugins.draggable.client.gwt.DraggableWidget;
import gwtquery.plugins.droppable.client.events.DropEvent;
import gwtquery.plugins.droppable.client.gwt.DroppableWidget;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class DragViewImpl extends Composite {
  interface DragViewImplUiBinder extends UiBinder<HTMLPanel, DragViewImpl> {
  }

  private static DragViewImplUiBinder ourUiBinder = GWT.create(DragViewImplUiBinder.class);

  @UiField
  Label aDrag;
  @UiField
  Label notADrag;
  @UiField
  HTMLPanel dragStartPanel;
  @UiField
  HTMLPanel dropPanel;

  public DragViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
    dragStartPanel.add(createDraggable(aDrag));
    setDropArea(dropPanel);
  }

  private Widget createDraggable(Label label){
    DraggableWidget<Label> draggableWidget = new DraggableWidget<Label>(label);
    draggableWidget.setRevert(DraggableOptions.RevertOption.ON_INVALID_DROP);
    draggableWidget.setRevert(DraggableOptions.RevertOption.ON_INVALID_DROP);
    draggableWidget.setDraggingZIndex(100);
    draggableWidget.setDraggingCursor(Style.Cursor.POINTER);
    return draggableWidget;
  }

  private void setDropArea(HTMLPanel htmlPanel){
    DroppableWidget<HTMLPanel> droppableWidget = new DroppableWidget<HTMLPanel>(htmlPanel);
    droppableWidget.addDropHandler(new DropEvent.DropEventHandler() {
      @Override
      public void onDrop(DropEvent event) {
        Widget droppedLabel = event.getDraggableWidget();
        droppedLabel.removeFromParent();
        dropPanel.add(droppedLabel);
      }
    });
    RootPanel.get().add(droppableWidget);
  }
}
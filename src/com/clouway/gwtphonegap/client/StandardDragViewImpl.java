package com.clouway.gwtphonegap.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class StandardDragViewImpl extends Composite {
  interface StandardDragViewImplUiBinder extends UiBinder<HTMLPanel, StandardDragViewImpl> {
  }

  private static StandardDragViewImplUiBinder ourUiBinder = GWT.create(StandardDragViewImplUiBinder.class);

  @UiField
  Label draggableLabel;
  @UiField
  HTMLPanel firstPanel;
  @UiField
  HTMLPanel secondPanel;
  Widget draggedWidget;


  public StandardDragViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
    UserViewImpl userView = new UserViewImpl("Ivan Ivanov","Veliko Tarnovo");
    UserViewImpl userView2 = new UserViewImpl("Zvezdio Zvezdev","Veliko Tarnovo");
    firstPanel.add(userView);
    firstPanel.add(userView2);
    makeDraggable(userView);
    makeDraggable(userView2);
    setUpDropArea(firstPanel);
    setUpDropArea(secondPanel);

    RootPanel.get().addDomHandler(new ContextMenuHandler() {
      @Override
      public void onContextMenu(ContextMenuEvent event) {
        event.preventDefault();
        event.stopPropagation();
      }
    }, ContextMenuEvent.getType());
  }


  public void makeDraggable(final Widget widget) {
    widget.getElement().setDraggable(Element.DRAGGABLE_TRUE);
    widget.addDomHandler(new DragStartHandler() {
      @Override
      public void onDragStart(DragStartEvent event) {
        event.setData("text", "uniqueId");
        draggedWidget = widget;
        event.getDataTransfer().setDragImage(widget.getElement(), 10, 10);
      }
    }, DragStartEvent.getType());
  }

  public void setUpDropArea(final HTMLPanel htmlPanel) {
    htmlPanel.addDomHandler(new DragOverHandler() {
      @Override
      public void onDragOver(DragOverEvent event) {

      }
    }, DragOverEvent.getType());

    htmlPanel.addDomHandler(new DropHandler() {
      @Override
      public void onDrop(DropEvent event) {
        htmlPanel.add(draggedWidget);
      }
    }, DropEvent.getType());
  }


}
package com.clouway.gwtphonegap.client.dndsimple;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class SimpleDndView extends Composite {
  interface SimpleDndViewUiBinder extends UiBinder<Widget, SimpleDndView> {
  }

  private static SimpleDndViewUiBinder binder = GWT.create(SimpleDndViewUiBinder.class);

  @UiField
  HTMLPanel panelOne;
  @UiField
  HTMLPanel panelTwo;
  @UiField
  Label employee;

  public SimpleDndView() {

    initWidget(binder.createAndBindUi(this));
    setDropZone(panelOne);
    setDropZone(panelTwo);
    makeDraggable(employee);
  }

  public void setDropZone(final HTMLPanel htmlPanel){
    htmlPanel.addDomHandler(new DragOverHandler() {
      @Override
      public void onDragOver(DragOverEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
      }
    }, DragOverEvent.getType());

    htmlPanel.addDomHandler(new DropHandler() {
      @Override
      public void onDrop(DropEvent event) {
        htmlPanel.add(employee);
      }
    }, DropEvent.getType());
  }

  public void makeDraggable(final Label label){
    label.getElement().setDraggable(Element.DRAGGABLE_TRUE);
    label.addDomHandler(new DragStartHandler() {
      public void onDragStart(DragStartEvent event) {
        event.setData("text", "firefox");
        event.getDataTransfer().setDragImage(label.getElement(), 0, 0);

      }
    }, DragStartEvent.getType());
  }
}
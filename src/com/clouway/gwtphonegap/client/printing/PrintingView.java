package com.clouway.gwtphonegap.client.printing;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class PrintingView extends Composite {
  interface PrintingViewUiBinder extends UiBinder<Widget, PrintingView> {
  }

  private static PrintingViewUiBinder binder = GWT.create(PrintingViewUiBinder.class);

  interface Style extends CssResource{

    String panelOneStyle();

    String panelTwoStyle();
  }

  @UiField
  DivElement panelOne;
  @UiField
  HTMLPanel panelTwo;
  @UiField
  Button printButton;
  @UiField
  Style style;
  @UiField
  HTMLPanel panelHolder;
  PanelView panelView = new PanelView();

  public PrintingView() {

    initWidget(binder.createAndBindUi(this));
    panelHolder.add(panelView);
  }

  @UiHandler("printButton")
  public void onPrintButtonClicked(ClickEvent event)   {
    Print.it(panelOne.getInnerHTML());
  }
}
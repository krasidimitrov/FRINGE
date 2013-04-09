package com.clouway.gwtphonegap.client.flipcss;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class FlipView extends Composite {
  interface FlipViewUiBinder extends UiBinder<Widget, FlipView> {
  }

  private static FlipViewUiBinder binder = GWT.create(FlipViewUiBinder.class);

  interface Style extends CssResource{

    String flipStyle2();

  }

  @UiField
  HTMLPanel flipPanel;
  @UiField
  Style style;
  private int i=0;

  public FlipView() {

    initWidget(binder.createAndBindUi(this));

    flipPanel.addDomHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        if(i == 0){
        flipPanel.addStyleName(style.flipStyle2());
        i++;
        } else {
          i=0;
          flipPanel.removeStyleName(style.flipStyle2());
        }
      }
    },ClickEvent.getType());
  }
}
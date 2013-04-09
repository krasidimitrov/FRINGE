package com.clouway.gwtphonegap.client.printing;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class PanelView extends Composite {
  public void print() {
    Print.it();
  }

  interface PanelViewUiBinder extends UiBinder<Widget, PanelView> {
  }

  private static PanelViewUiBinder binder = GWT.create(PanelViewUiBinder.class);

  public PanelView() {

    initWidget(binder.createAndBindUi(this));

  }
}
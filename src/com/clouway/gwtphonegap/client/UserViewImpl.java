package com.clouway.gwtphonegap.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Created with IntelliJ IDEA.
 * User: kdimitrov
 * Date: 1/8/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserViewImpl extends Composite{
  interface UserViewImplUiBinder extends UiBinder<HTMLPanel, UserViewImpl> {
  }

  private static UserViewImplUiBinder ourUiBinder = GWT.create(UserViewImplUiBinder.class);

  @UiField
  Label name;
  @UiField
  Label address;

  public UserViewImpl(String name, String address) {
    initWidget(ourUiBinder.createAndBindUi(this));
    this.name.setText(name);
    this.address.setText(address);
  }
}
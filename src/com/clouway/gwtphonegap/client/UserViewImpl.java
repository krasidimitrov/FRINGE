package com.clouway.gwtphonegap.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
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

  interface Style extends CssResource {

    String flipStyle2();

    String flipStyle();

  }

  @UiField
  HTMLPanel flipPanel;
  @UiField
  Style style;
  @UiField
  Label name;
  @UiField
  Label address;
  @UiField
  Button editButton;

  public UserViewImpl(String name, String address) {
    initWidget(ourUiBinder.createAndBindUi(this));
    this.name.setText(name);
    this.address.setText(address);

    flipPanel.addDomHandler(new MouseUpHandler() {
      @Override
      public void onMouseUp(MouseUpEvent event) {
        if (event.getNativeButton() == NativeEvent.BUTTON_RIGHT) {
          if(flipPanel.getStyleName().equals(style.flipStyle())){
            flipPanel.addStyleName(style.flipStyle2());
          } else {
            flipPanel.removeStyleName(style.flipStyle2());
          }
        }
      }
    }, MouseUpEvent.getType());
  }

  @UiHandler("editButton")
  public void onEditButtonClicked(ClickEvent event){
    Window.alert("You are editing");
  }
}
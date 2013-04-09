package com.clouway.gwtphonegap.client;


import com.clouway.gwtphonegap.client.channel.ChannelFactory;
import com.google.gwt.appengine.channel.client.Channel;
import com.google.gwt.appengine.channel.client.SocketError;
import com.google.gwt.appengine.channel.client.SocketListener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.web.bindery.event.shared.EventBus;


/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SampleViewImpl extends Composite{
  private MessageServiceAsync rpcService;
  private EventBus eventBus;
  private String token;

  interface SampleViewImplUiBinder extends UiBinder<HTMLPanel, SampleViewImpl> {
  }

  private static SampleViewImplUiBinder ourUiBinder = GWT.create(SampleViewImplUiBinder.class);


  @UiField
  Button sendButton;
  @UiField
  Button tokenButton;
  @UiField
  Label messageLabel;
  @UiField
  TextBox writeBox;
  @UiField
  Label tokenLabel;


  public SampleViewImpl(MessageServiceAsync rpcService, EventBus eventBus) {

    initWidget(ourUiBinder.createAndBindUi(this));
    this.rpcService = rpcService;
    this.eventBus = eventBus;
  }

  @UiHandler("sendButton")
  public void onSendButtonClicked(ClickEvent event){
    rpcService.sendMessage(writeBox.getText(),new AsyncCallback<Void>() {
      @Override
      public void onFailure(Throwable caught) {
        Window.alert("MessageFailed");
      }

      @Override
      public void onSuccess(Void result) {
      }
    });
  }

  @UiHandler("tokenButton")
  public void onTokenButtonClicked(ClickEvent event){

    rpcService.getToken(new AsyncCallback<String>() {
      @Override
      public void onFailure(Throwable caught) {
        messageLabel.setText("connection failed");
      }

      @Override
      public void onSuccess(String result) {
        createChannel(result);

      }
    });
  }

  public void createChannel(String token){
    tokenLabel.setText("Your id is: "+ token);

    ChannelFactory.createChannel(token, new ChannelFactory.ChannelCreatedCallback() {
      @Override
      public void onChannelCreated(Channel channel) {
        channel.open(new SocketListener() {
          @Override
          public void onOpen() {
            messageLabel.setText("OPENED");
//            Timer timer = new Timer() {
//              @Override
//              public void run() {
//                rpcService.getToken(new AsyncCallback<String>() {
//                  @Override
//                  public void onFailure(Throwable caught) {
//                    //To change body of implemented methods use File | Settings | File Templates.
//                  }
//
//                  @Override
//                  public void onSuccess(String result) {
//                    createChannel(result);
//                  }
//                });
//              }
//            };
//            timer.schedule(50000);
          }


          @Override
          public void onMessage(String message) {
            messageLabel.setText(message + "555");
          }

          @Override
          public void onError(SocketError error) {
            Window.alert(error.getCode()+" --- " + error.getDescription());
            rpcService.getToken(new AsyncCallback<String>() {
              @Override
              public void onFailure(Throwable caught) {

              }

              @Override
              public void onSuccess(String result) {
                createChannel(result);
              }
            });

          }

          @Override
          public void onClose() {
            Window.alert("Channel closed");
          }
        });

      }
    });
  }
}
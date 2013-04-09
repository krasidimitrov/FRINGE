package com.clouway.gwtphonegap.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MessageServiceAsync {
  void sendMessage(String message, AsyncCallback<Void> async);

  void getToken(AsyncCallback<String> async);
}

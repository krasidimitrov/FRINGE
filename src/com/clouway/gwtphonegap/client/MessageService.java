package com.clouway.gwtphonegap.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@RemoteServiceRelativePath("MessageService")
public interface MessageService extends RemoteService{

  public void sendMessage(String message);


  public String getToken();
}

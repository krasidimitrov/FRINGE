package com.clouway.gwtphonegap.server;

import com.clouway.gwtphonegap.client.MessageService;
import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//import sun.misc.BASE64Decoder;


/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {
  private List<String> tokens = new ArrayList<String>();
  private ChannelService channelService;

  public MessageServiceImpl(){
    channelService = ChannelServiceFactory.getChannelService();
  }


  /**
   * Send messages to all clients
   * @param message the message that is to be send to all clients
   */
  public void sendMessage(String message) {
    for (String token : tokens) {
      channelService.sendMessage(new ChannelMessage(token, message));
    }
  }

  /**
   * Create a unique clientId from which it generate
   * @return
   */
  public String getToken() {
    String clientId = UUID.randomUUID().toString();
    //max time is 24*60 minutes
    String token = channelService.createChannel(clientId,1);
    tokens.add(clientId);
    return token;
  }

}

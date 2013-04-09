package com.clouway.gwtphonegap.client.dnd;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class UnknownDraggableSource extends RuntimeException {

  public UnknownDraggableSource(String s) {
    super(s);
  }

  public UnknownDraggableSource() {
  }
}

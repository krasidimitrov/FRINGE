package com.clouway.gwtphonegap.client.comments;

import java.util.List;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public interface CommentBoardView {

  interface Presenter{
  }

  void renderComments(List<Comment> comments);

}

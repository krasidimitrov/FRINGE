package com.clouway.gwtphonegap.client.comments;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class CommentBoxViewImpl extends Composite {
  interface CommentBoxViewImplUiBinder extends UiBinder<Widget, CommentBoxViewImpl> {
  }

  private static CommentBoxViewImplUiBinder binder = GWT.create(CommentBoxViewImplUiBinder.class);

  @UiField
  Label ownerName;
  @UiField
  Label dateStamp;
  @UiField
  Label content;

  public CommentBoxViewImpl() {
    initWidget(binder.createAndBindUi(this));
  }

  public void setCommentData(Comment comment){
    ownerName.setText(comment.getOwner());
    dateStamp.setText(comment.getDatestamp());
    content.setText(comment.getContent());
  }
}
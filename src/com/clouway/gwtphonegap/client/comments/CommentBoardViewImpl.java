package com.clouway.gwtphonegap.client.comments;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class CommentBoardViewImpl extends Composite {
  interface CommentBoardViewImplUiBinder extends UiBinder<Widget, CommentBoardViewImpl> {
  }

  private static CommentBoardViewImplUiBinder binder = GWT.create(CommentBoardViewImplUiBinder.class);

  interface Style extends CssResource{

    String visibleWritePanel();

    String hiddenWritePanel();

    String visibeBeforeWriteBoxStyle();

    String hiddenBeforeWriteBoxStyle();
  }


  @UiField
  Button postButton;
  @UiField
  Button cancelButton;
  @UiField
  TextBox beforeWriteTextBox;
  @UiField
  HTMLPanel writeCommentPanel;
  @UiField
  TextArea writeCommentBox;
  @UiField
  Style style;
  @UiField
  HTMLPanel commentList;

  public CommentBoardViewImpl() {

    initWidget(binder.createAndBindUi(this));

  }

  @UiHandler("beforeWriteTextBox")
  public void onBeforeWriteTextBoxClicked(ClickEvent event){
    beforeWriteTextBox.setStyleName(style.hiddenBeforeWriteBoxStyle());
    writeCommentPanel.setStyleName(style.visibleWritePanel());
    writeCommentBox.setFocus(true);
    writeCommentBox.setVisibleLines(3);

    writeCommentBox.addKeyDownHandler(new KeyDownHandler() {
      @Override
      public void onKeyDown(KeyDownEvent event) {
        int lines=0;
        final String content = writeCommentBox.getText();

        for(int i = 0; i != -1; i = content.indexOf("\n", i + 1)) {
          lines++;
        }
        if(writeCommentBox.getVisibleLines() < lines){
          writeCommentBox.setVisibleLines(lines);
        }
      }
    });
  }

  @UiHandler("cancelButton")
  public void onCancelButtonClicked(ClickEvent event){
    resetWriteBox();
  }

  @UiHandler("postButton")
  public void onPostButtonClicked(ClickEvent event){
    if(!writeCommentBox.getText().equals("") && writeCommentBox.getText().trim().length() != 0){
      CommentBoxViewImpl commentBox = new CommentBoxViewImpl();
      commentBox.setCommentData(new Comment(writeCommentBox.getText(), "Krasi", "23/12/12 12:25"));
      commentList.add(commentBox);
      resetWriteBox();
    }
  }

  private void resetWriteBox(){
    beforeWriteTextBox.setStyleName(style.visibeBeforeWriteBoxStyle());
    writeCommentPanel.setStyleName(style.hiddenBeforeWriteBoxStyle());
    writeCommentBox.setText("");
  }
}
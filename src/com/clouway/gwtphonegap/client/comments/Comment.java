package com.clouway.gwtphonegap.client.comments;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class Comment {

  private String content;
  private String owner;
  private String datestamp;

  public Comment(String content, String owner, String datestamp){
    this.content = content;
    this.owner = owner;
    this.datestamp = datestamp;
  }

  public String getContent() {
    return content;
  }

  public String getOwner() {
    return owner;
  }

  public String getDatestamp() {
    return datestamp;
  }
}

package com.clouway.gwtphonegap.client;

import flexjson.JSONSerializer;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class TestObj {
  private String name;
  private String message;
  private int date;

  public TestObj(String name, String message, int date){
    this.name = name;
    this.message = message;
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public String getMessage() {
    return message;
  }

  public int getDate() {
    return date;
  }

  public void test(){
    TestObj testObj = new TestObj("haha","haha",12);
    JSONSerializer jsonSerializer = new JSONSerializer();
    String text = jsonSerializer.serialize(jsonSerializer);

  }
}

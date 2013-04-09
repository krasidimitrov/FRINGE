package com.clouway.gwtphonegap.client.printing;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

/*/
import com.google.gwt.user.client.DeferredCommand;
/*/
//*/

public class Print {

  /**
   * If true, use a Timer instead of DeferredCommand to print the internal fram
   */
  public static boolean USE_TIMER     = false;

  /**
   * Time in seconds to wait before printing the internal frame when using Timer
   */
  public static int TIMER_DELAY       = 2;


  public static native void it() /*-{
      $wnd.print();
  }-*/;

  public static void it(UIObject obj) {
    it("", obj);
  }

  public static void it(Element element) {
    it("", element);
  }

  public static void it(String style, UIObject obj) {
    it(style, obj.getElement());
  }

  public static void it(String docType, String style, UIObject obj) {
    it(docType, style, obj.getElement());
  }

  public static void it(String style, Element element) {
    it("", style, element);
  }

  public static void it(String docType, String style, Element element) {
    saveOrigIFrames(element);
    updateFieldsDOM(element);
    it(docType, style, DOM.toString(element));
  }

  public static void it(String docType, String style, String it) {
    it(docType
            +"<html>"
            +"<head>"
            +"<meta http-equiv=\"Content-Type\"          content=\"text/html; charset=utf-8\">"
            +"<meta http-equiv=\"Content-Style-Type\"    content=\"text/css\">"
            +    style
            +"</head>"+"<body>"
            +    it
            +"</body>"+
            "</html>");
  }

  public static void it(String html) {
    try {
      buildFrame(html);
      setPFIFrames();

      if (USE_TIMER) {
        Timer timer     = new Timer() {
          public void run() {
            printFrame();
          }
        };
        timer.schedule(TIMER_DELAY * 1000);
      } else {
        // As per Duvo.Sumy sugestion, using switching from DeferredCommand to Scheduler
        // If you use an older GWT distribuition, just add a / to the line below
                /*/
                DeferredCommand.addCommand(new Command() {
                        public void execute() {
                            printFrame();
                        }
                    });
                /*/
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override public void execute() {
            printFrame();
          }
        });
        //*/
      }

    } catch (Throwable exc) {
      Window.alert(exc.getMessage());
    }
  }

  public static native void buildFrame(String html) /*-{
      var frame = $doc.getElementById('__printingFrame');
      if (!frame) {
          $wnd.alert("Error: Can't find printing frame.");
          return;
      }
      var doc = frame.contentWindow.document;
      doc.open();
      doc.write(html);
      doc.close();

  }-*/;

  public static native void printFrame() /*-{
      var frame = $doc.getElementById('__printingFrame');
      frame = frame.contentWindow;
      frame.focus();
      frame.print();
  }-*/;

  // Great contribution from mgrushinskiy to print form element
  public static void updateFieldsDOM(Element dom) {
    NodeList<com.google.gwt.dom.client.Element> textareas   = dom.getElementsByTagName("textarea");
    NodeList<com.google.gwt.dom.client.Element> inputs      = dom.getElementsByTagName("input");
    NodeList<com.google.gwt.dom.client.Element> options     = dom.getElementsByTagName("option");

    if (textareas != null) {
      for (int cii = 0;  cii < textareas.getLength();  cii++) {
        updateDOM(TextAreaElement.as(textareas.getItem(cii)));
      }
    }
    if (inputs != null) {
      for (int cii = 0;  cii < inputs.getLength();  cii++) {
        updateDOM(InputElement.as(inputs.getItem(cii)));
      }
    }
    if (options != null) {
      for (int cii = 0;  cii < options.getLength();  cii++) {
        updateDOM(OptionElement.as(options.getItem(cii)));
      }
    }
  }

  public static void updateDOM(InputElement item) {
    try {
      item.setDefaultValue(               item.getValue());
    } finally {}
    try {
      item.setDefaultChecked(             item.isChecked());
    } finally {}
  }

  public static void updateDOM(TextAreaElement item) {
    item.setDefaultValue(                   item.getValue());
    item.setInnerText(item.getValue());
  }

  public static void updateDOM(OptionElement item) {
    item.setDefaultSelected(                item.isSelected());
  }


  // Another great contribution, this time from italobb, to print IFRAME content
  public static NodeList<com.google.gwt.dom.client.Element>
          _origIFrames            = null;
  public static void saveOrigIFrames(Element element) {
    _origIFrames            = element.getElementsByTagName("iframe");
  }

  public static void setPFIFrames() {
    if (_origIFrames == null  ||  _origIFrames.getLength() <= 0)
      return;

    NodeList<com.google.gwt.dom.client.Element>
            pfIFrames           = getIFrameEl(DOM.getElementById("__printingFrame")).getElementsByTagName("iframe");
    for (int cii = 0;  cii < pfIFrames.getLength();  cii++) {
      com.google.gwt.dom.client.Element
              pfEl            = getIFrameEl(pfIFrames.getItem(cii));
      com.google.gwt.dom.client.Element
              origEl          = getIFrameEl(_origIFrames.getItem(cii));
      pfEl.setInnerHTML(  origEl.getInnerHTML());
    }
    _origIFrames                            = null;
  }

  public static com.google.gwt.dom.client.Element getIFrameEl(com.google.gwt.dom.client.Element victim) {
    return IFrameElement.as(victim).getContentDocument().getDocumentElement();
  }



  // Another contribuition from italobb. It copies the header of the original document
  public static void itAll(UIObject obj) {
    itAll(obj.getElement());
  }

  public static void itAll(Element element) {
    saveOrigIFrames(element);
    updateFieldsDOM(element);
    itAll(DOM.toString(element));
  }

  public static void itAll(String it) {
    Document node           = (Document) Document.get().cloneNode(true);
    BodyElement body        = node.getBody();
    com.google.gwt.dom.client.Element
            header              = node.getElementById("header");
    if (header != null) {
      String newHeader    = header.getInnerHTML();
      header.setInnerHTML(newHeader);
    }
    body.setInnerHTML(it);

    it(node.getDocumentElement().getString());
  }


} // end of class Print
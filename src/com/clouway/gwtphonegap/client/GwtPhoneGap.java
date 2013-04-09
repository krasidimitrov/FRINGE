package com.clouway.gwtphonegap.client;

import com.clouway.gwtphonegap.client.comments.CommentBoardView;
import com.clouway.gwtphonegap.client.comments.CommentBoardViewImpl;
import com.clouway.gwtphonegap.client.dnd.DndView;
import com.clouway.gwtphonegap.client.dndsimple.SimpleDndView;
import com.clouway.gwtphonegap.client.flipcss.FlipView;
import com.clouway.gwtphonegap.client.printing.PrintingView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class GwtPhoneGap implements EntryPoint {

  public void onModuleLoad() {


//    final PhoneGap phoneGap = GWT.create(PhoneGap.class);
//
//    phoneGap.addHandler(new PhoneGapAvailableHandler() {
//
//      @Override
//      public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
//
//        SampleViewImpl view = new SampleViewImpl((MessageServiceAsync)GWT.create(MessageService.class),new SimpleEventBus());
//        RootPanel.get().add(view);
//
//      }
//    });
//
//    phoneGap.addHandler(new PhoneGapTimeoutHandler() {
//
//      @Override
//      public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
//        //can not start phonegap - something is for with your setup
//      }
//    });

//    phoneGap.initializePhoneGap();



//    SampleViewImpl view = new SampleViewImpl((MessageServiceAsync)GWT.create(MessageService.class),new SimpleEventBus());
//    RootPanel.get().add(view);

//    DragViewImpl view = new DragViewImpl();
//    RootPanel.get().add(view);

//    StandardDragViewImpl view = new StandardDragViewImpl();
//    FlipView view = new FlipView();
//    DndView view = new DndView();
//    SimpleDndView view = new SimpleDndView();
//    CommentBoardViewImpl view = new CommentBoardViewImpl();
    PrintingView view = new PrintingView();
    RootPanel.get().add(view);
  }
}

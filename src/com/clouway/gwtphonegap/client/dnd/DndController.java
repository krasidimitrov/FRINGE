package com.clouway.gwtphonegap.client.dnd;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class DndController<D extends DndController.Draggable, Z extends DndController.DropZone> {

  public interface DropZone<T extends Draggable, V extends DraggableSource<T>> extends IsWidget {
    void onDrop(T draggable, V source);

    void onDragOver(T draggable, V source);

    void onDragLeave(T draggable);
  }

  public interface Draggable extends IsWidget {
  }

  public interface DraggableSource<T extends Draggable> extends IsWidget {
    void onDraggableDrop(T draggable);

    void onDragStart(T draggable);

  }

  private Draggable draggable;
  private DraggableSource<D> source ;


  public DndController(){
  }

  /**
   * Register a zone in which draggable elements are going to be dropped.
   */
  public void registerDropZone(final Z dropZone){

    dropZone.asWidget().addDomHandler(new DragOverHandler() {
      @Override
      public void onDragOver(DragOverEvent event) {
//        dropZone.onDragOver(draggable,source);
      }
    }, DragOverEvent.getType());

//    dropZone.asWidget().addDomHandler(new DragLeaveHandler() {
//      @Override
//      public void onDragLeave(DragLeaveEvent event) {
//        dropZone.onDragLeave(draggable);
//      }
//    }, DragLeaveEvent.getType());

    dropZone.asWidget().addDomHandler(new DropHandler() {
      @Override
      public void onDrop(DropEvent event) {
          dropZone.onDrop(draggable, source);
      }
    }, DropEvent.getType());
  }

  /**
   * Register an object as draggable so it can be dragged from drag sourced and dropped in drop zones.
   */
  public void registerDraggable(final D draggable){

    draggable.asWidget().getElement().setDraggable(Element.DRAGGABLE_TRUE);
    draggable.asWidget().addDomHandler(new DragStartHandler() {
      public void onDragStart(DragStartEvent event) {
        DndController.this.draggable = draggable;
        event.setData("text", "firefox");
        event.getDataTransfer().setDragImage(draggable.asWidget().getElement(), 0, 0);
        if(source == null){
          throw new UnknownDraggableSource("Draggable source is not registered for the dragged widget");
        }
        source.onDragStart(draggable);

      }
    }, DragStartEvent.getType());
  }

  /**
   * Register a zone from which a draggable objects can be dragged.
   */
  public void registerDraggableSource(final DraggableSource<D> draggableSource){
    draggableSource.asWidget().addDomHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        source = draggableSource;
      }
    }, MouseDownEvent.getType());
  }
}

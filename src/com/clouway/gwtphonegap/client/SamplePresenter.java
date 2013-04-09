package com.clouway.gwtphonegap.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.gwtphonegap.client.camera.CameraMobileImpl;
import com.googlecode.gwtphonegap.client.camera.PictureCallback;
import com.googlecode.gwtphonegap.client.camera.PictureOptions;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SamplePresenter {



  public void capturePicture(final Image image){
    PictureOptions options = new PictureOptions(100);
    options.setDestinationType(PictureOptions.DESTINATION_TYPE_DATA_URL);
    options.setSourceType(PictureOptions.PICTURE_SOURCE_TYPE_CAMERA);



      CameraMobileImpl cameraMobile = new CameraMobileImpl();
      cameraMobile.getPicture(options, new PictureCallback() {
        @Override
        public void onSuccess(String data) {
          image.setUrl("data:image/jpg;base64,"+data);
        }

        @Override
        public void onFailure(String message) {
          Window.alert("Photo not successful");
        }
      });
  }

}

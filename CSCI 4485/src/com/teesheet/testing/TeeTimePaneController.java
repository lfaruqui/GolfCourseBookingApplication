package com.teesheet.testing;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.teesheet.application.utility.JSON.JSONObject;
import com.teesheet.testing.fxmlTests.FxmlLoaderTest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.skin.TitledPaneSkin;
import javafx.scene.layout.HBox;

/**
 * @author Jayden Craft Apr 5, 2022
 */
public class TeeTimePaneController {

	@FXML
	TitledPane pane;

	ExpandedReservationControllerTest rc;

	public void loadTeeTime(JSONObject singleTeeTime) {

		JSONObject info = singleTeeTime.optJSONObject("info");

		LocalTime time = LocalTime.parse(singleTeeTime.optString("time"), DateTimeFormatter.ofPattern("HH:mm:ss"));

		Label label = new Label();

		if (!info.optString("member_name").equals("")) {

			pane.setDisable(false);
			pane.getChildrenUnmodifiable().get(1).setStyle("");

			String title = time.format(DateTimeFormatter.ofPattern("h:mm a")) + "   ";

			if (!info.optString("member_name").equals("")) {
				title += "Member: " + info.optString("member_name") + "   ";

				if (!info.optString("player1_name").equals("")) {
					title += "Player 1: " + info.optString("player1_name") + "   ";

					if (!info.optString("player2_name").equals("")) {
						title += "Player 2: " + info.optString("player2_name") + "   ";

						if (!info.optString("player3_name").equals("")) {
							title += "Player 3: " + info.optString("player3_name") + "   ";

							if (!info.optString("player4_name").equals("")) {
								title += "Player 4: " + info.optString("player4_name") + "   ";
							}

						}

					}

				}

			}

			label.setText(title);

			pane.setGraphic(label);

			pane.getGraphic()
					.setStyle("{\r\n"
							+ "	-fx-font-family: 'Lucida Grande',Verdana,Geneva,Lucida,Arial,Helvetica,sans-serif;\r\n"
							+ "    -fx-font-size: 18px;\r\n" + "    -fx-font-weight: bold;\r\n"
							+ "    -fx-text-fill: #235675;\r\n" + "}");

		} else {

			HBox graphic = getNewGraphic();
//			System.out.println(graphic);
			Label l = (Label) graphic.getChildren().get(0);

			l.setText(time.format(DateTimeFormatter.ofPattern("h:mm a")));
			
			pane.setGraphic(graphic);

		}

		if (info.optBoolean("blocked")) {

//			pane.setExpanded(false);
			pane.getChildrenUnmodifiable().get(1).setStyle(
					"-fx-background-color: linear-gradient(from 13px 38px to 52px 52px, reflect,  #ff5c6c, #ff0019 100%);");
			pane.setDisable(true);

			label.setText(singleTeeTime.optString("time"));
		}else {
			pane.getChildrenUnmodifiable().get(1).setStyle("");
			pane.setDisable(false);
		}
		
		rc.setTeeTime(singleTeeTime);

	}
	
	public void initialize() {
		FxmlLoaderTest loader = new FxmlLoaderTest("ExpandedReservationPaneTest.fxml");
		pane.setContent(loader.loadPage());

		/*
		 * The skin is not set upon load by default, so we must do it before the page is
		 * finished loading so we can style the titleRegion attribute for blocked dates.
		 * This is non existent if we do not do it like this.
		 */
		TitledPaneSkin ts = new TitledPaneSkin(pane);
		pane.setSkin(ts);
		
		rc = loader.getLoader().getController();
	}
	
	private HBox getNewGraphic() {
		FxmlLoaderTest graphicLoader = new FxmlLoaderTest("EmptyTeeTimeTitle.fxml");
		return (HBox) graphicLoader.loadPage();
	}

}

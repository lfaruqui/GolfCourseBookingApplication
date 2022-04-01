package com.teesheet.testing.fxmlTests;

import java.io.IOException;

import com.teesheet.testing.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * @author Jayden Craft
 * Mar 31, 2022
 */
public class FxmlLoader {
	
	private Node node;
	
	public Node loadPage(String fileName) {
		
		try {
			
			node = FXMLLoader.load(FxmlLoader.class.getResource(fileName));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return node;
	}

}

package com.teesheet.application.gui.fxml;

import java.io.IOException;

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
			System.out.println("LOADED");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return node;
	}

}

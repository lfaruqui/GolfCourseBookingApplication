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
	
	private FXMLLoader loader;
	
	public FxmlLoader(String fileName) {
		loader = new FXMLLoader(FxmlLoader.class.getResource(fileName));
	}
	
	public FXMLLoader getLoader() {
		return loader;
	}
	
	public Node loadPage() {
		
		try {
			
			node = loader.load();
//			System.out.println("LOADED");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return node;
	}

}

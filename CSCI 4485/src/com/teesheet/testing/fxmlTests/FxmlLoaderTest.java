package com.teesheet.testing.fxmlTests;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * @author Jayden Craft
 * Mar 31, 2022
 */
public class FxmlLoaderTest {
	
private Node node;
	
	private FXMLLoader loader;
	
	public FxmlLoaderTest(String fileName) {
		loader = new FXMLLoader(FxmlLoaderTest.class.getResource(fileName));
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

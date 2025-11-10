package app;

import controller.*;
import view.*;
public class main {
	public static void main(String[] args) {
		GUI view = new GUI();
		view.setVisible(true);
		sosGameController sosController= new sosGameController(view);
	}
}
package sprint2;




public class main {
	public static void main(String[] args) {
		GUI view = new GUI();
		view.setVisible(true);
		Controller sosController= new Controller(view);
	}
}
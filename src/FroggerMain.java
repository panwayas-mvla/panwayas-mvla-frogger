import javax.swing.*;

public class FroggerMain {
	public static void main(String[] args) {

		//view being created first causes a null pointer when model creates the lanes that are called in view	

		FroggerView view = new FroggerView();
		FroggerModel model = new FroggerModel(view);
		//view.initializeAnimationPanel();

		view.setModel(model);
		new FroggerController(model, view);
		model.setupNewGame(12);
		model.startGame();
		view.setSize(FroggerView.panelWidth, FroggerView.panelHeight + 100);
		view.setLocation(300, 300);
		view.setVisible(true);
		view.setResizable(false);
		view.getGamePanel().requestFocusInWindow();
	}
}


import java.awt.event.*;
import java.awt.event.KeyListener;

public class FroggerController implements KeyListener, ActionListener{
	private FroggerModel model;
	private FroggerView view;

	public FroggerController(FroggerModel model, FroggerView view) {
		this.model = model;
		this.view = view;
		this.view.addActionListener(this);
		this.view.getGamePanel().addKeyListener(this);
		view.getGamePanel().requestFocusInWindow();
	}

	public void actionPerformed(ActionEvent ev) {
		String command = ev.getActionCommand();
		if(command.equals("Start")) {
			view.showPanel("GamePanel");
			model.setupNewGame(12);
			model.startGame();
			view.getGamePanel().requestFocusInWindow();

		}
		if(command.equals("Restart")) {
		//	System.out.println("restart button hit");
			model.resetNewGame();
			view.showPanel("GamePanel");
			model.setupNewGame(12);
			model.startGame();
			view.getGamePanel().requestFocusInWindow();
		}
		if(command.equals("Restart ")) {
		//	System.out.println("restart button hit");
			model.resetNewGame();
			view.showPanel("GamePanel");
			model.setupNewGame(12);
			model.startGame();
			view.getGamePanel().requestFocusInWindow();
		}
		view.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("Key typed " + e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			model.getFrog().moveLeft();
		}
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			model.getFrog().moveRight();
		}
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			model.getFrog().moveUp();

		}
		if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			model.getFrog().moveDown();

		}
		view.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}



}

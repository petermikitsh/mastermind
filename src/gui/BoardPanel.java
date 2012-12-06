/**
 * BoardPanel.java
 * 
 * BoardPanel draws a visual representation of the board.
 * 
 * @author Evan
 */

package gui;

import java.awt.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BoardPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final String NONE = "NONE.png";
	private JPanel _guessPanel;
	private int _gameRows;
	private int _gamePegSize;
	
	/**
	 * Sets up the main board.
	 * @return the panel that contains everything.
	 */
	public BoardPanel(Integer gameRows, Integer gamePegSize) {

		this._gameRows = gameRows;
		this._gamePegSize = gamePegSize;
		
		//Set the drawing properties for the super panel.
		this.setLayout(new GridLayout(1, 1));
		this.setBorder(new EmptyBorder(10, 25, 50, 9));
		
		// Make a sub-panel to display guesses.
		drawInitialGuesses();
		
		this.add(_guessPanel);
		
	}
	
	/**
	 * Sets up the default guess buttons.
	 */
	public void drawInitialGuesses() {
		
		// reset the guess panel and guesses.
		_guessPanel = new JPanel();
		
		ImageIcon blank = new ImageIcon(getClass().getResource(NONE));
		
		// make all "default" buttons.
		for (int i = 0; i < this._gameRows; i++) {
			for (int j = 0; j < this._gamePegSize; j++) {
				JButton button = new JButton();
				button.setBackground(Color.white);
				button.setIcon(blank);
				_guessPanel.add(button);
			}
		}
		
		// set the guess panel's drawing properties.
		_guessPanel.setBorder(new EmptyBorder(10, 25, 25, 25));
		_guessPanel.setLayout(new GridLayout(this._gameRows, this._gamePegSize, 25, 5));
	}
	
	public void makeNewBoard(){
		
		//for( int j = 0; j <_guessPanel.getComponentCount(); j++){
		//	System.out.println(_guessPanel.getComponentCount());
		//	_guessPanel.remove(0);
		//}
		
		_guessPanel.removeAll();
		
		ImageIcon blank = new ImageIcon(getClass().getResource(NONE));
		
		for (int i = 0; i < this._gameRows; i++) {
			for (int j = 0; j < this._gamePegSize; j++) {
				JButton button = new JButton();
				button.setBackground(Color.white);
				button.setIcon(blank);
				_guessPanel.add(button);
			}
		}
		_guessPanel.setLayout(new GridLayout(this._gameRows, this._gamePegSize, 25, 5));
		_guessPanel.repaint();
		_guessPanel.setVisible(false);
		_guessPanel.setVisible(true);
		_guessPanel.validate();
	}
	
	/**
	 * Redraws the guess buttons from a list of image file names.
	 * @param imageFiles A list of image file names.
	 */
	public void redrawGuesses(ArrayList<String> imageFiles) {
		for (int i = 0; i < imageFiles.size(); i++) {
			JButton button = (JButton) _guessPanel.getComponent(i);
			try {
				Image img = ImageIO.read(getClass().getResource(imageFiles.get(i)));
				button.setIcon(new ImageIcon(img));
				button.setEnabled(true);
			} catch (Exception e) {}
		}
		
		ImageIcon blank = new ImageIcon(getClass().getResource(NONE));
		
		for (int j = imageFiles.size(); j < this._gameRows * this._gamePegSize; j++) {
			JButton button = (JButton) _guessPanel.getComponent(j);
			try {
				button.setIcon(blank);
				button.setEnabled(true);
			} catch (Exception e) {}
		}
		validate();
	}
	
	public void updateGameRows(int gameRows) {
		this._gameRows = gameRows;
		makeNewBoard();
	}
	
	public void updatePegSize(int gamePegSize) {
		this._gamePegSize = gamePegSize;
		makeNewBoard();
	}
	
}

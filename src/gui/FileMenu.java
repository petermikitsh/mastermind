/**
 * FileMenu.java
 * 
 * The menu bar at the the top of the main GUI window.
 * 
 * @author Evan
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class FileMenu {
	
	private MainGUIPanel mainPanel;
	Options optionPopup;
	
	/**
	 * The file menu bar is created here
	 * @return
	 */

	public JMenuBar makeFileMenu(MainGUIPanel gui){
		
		this.mainPanel = gui;
		optionPopup = new Options(mainPanel);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.newGame();
				optionPopup.newGame();
				//mainPanel.getSolution();
				//mainPanel.newGame();
			}
		});
		
		JMenuItem options = new JMenuItem("Options");
		options.addActionListener(new optionsListen());
		
		JMenuItem viewSolution = new JMenuItem("View Solution");
		viewSolution.addActionListener(new solutionListener());
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new quitListener());
		
		file.add(newGame);
		file.add(options);
		file.add(viewSolution);
		file.add(quit);
		
		menuBar.add(file);
		
		return menuBar;
	}
	
	class optionsListen implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			optionPopup.setVisible(true);

		}

	}
	
	class solutionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			mainPanel.getSolutionPanel().setVisible(true);
			
		}
		
	}
	
	class quitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}

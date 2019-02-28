import java.awt.EventQueue;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.io.IOException;
import java.io.PrintStream;


public class Interface extends JFrame {

	
	private JTextField txtHillClimbing;
	private JTextField txtFirstchoiceHillclimbing;
	private JTextField txtParallelHillclimbing;
	private JPanel contentPane;
	
	private JTextArea textArea_Output;
	private JTextField txtSimulatedAnnealing;
	private JTextField txtLocalBeamSearch;

	/**
	 * main to launch the application (Java window)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Most of the code is automatic generated from JFrame itself (will comment everything)
	 * Create the JFrame 
	 */
	public Interface() {
		setBackground(Color.DARK_GRAY);				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1320, 789);									// size of window when pop up
		contentPane = new JPanel();					
		contentPane.setBackground(Color.DARK_GRAY);						// overall background color of window
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();									// create panel (on which are the textAreas and buttons)
		panel.setLayout(null);
		panel.setForeground(Color.DARK_GRAY);							// color of panel
		panel.setBackground(Color.DARK_GRAY);							// color of panel	
		panel.setBounds(0, 0, 1304, 750);								// size of panel
		contentPane.add(panel);											// adding panel to window
	
		/**
		 * textAreas for "welcome" and "guide through"
		 */
		JTextArea textArea_Welcome = new JTextArea();					// create textArea "welcome"
		textArea_Welcome.setText("Welcome");							// insert text
		textArea_Welcome.setForeground(Color.ORANGE);					// color text
		textArea_Welcome.setFont(new Font("Arial", Font.BOLD, 80));		// text properties
		textArea_Welcome.setBackground(Color.DARK_GRAY);				// background color of textArea
		textArea_Welcome.setBounds(39, 19, 442, 87);					// size of textArea
		panel.add(textArea_Welcome);									// adding to the panel
		
		JTextArea txtr_GuideThrough = new JTextArea();					// create textArea "guide through"
		txtr_GuideThrough.setText("\r\nYou have to:\r\n\r\n1. Import a warehouse configuration\r\n2. Import an order \r\n\r\nThan you can run each algorithm!\r\n\r\nYou can:\r\n- change your warehouse configuration \r\n- change your order\r\n- change the algorithm\r\n- or rerun the same algorithm"); // insert text
		txtr_GuideThrough.setForeground(Color.WHITE);					// text color
		txtr_GuideThrough.setFont(new Font("Arial", Font.BOLD, 16)); 	// text  properties
		txtr_GuideThrough.setBackground(Color.DARK_GRAY);				// background color of textArea
		txtr_GuideThrough.setBounds(43, 117, 343, 270);					// size textArea
		panel.add(txtr_GuideThrough);									// adding to the panel
		
		/**
		 * textArea for "warehouse configuration"
		 * Button "import" warehouse configuration
		 * Open Dialog window to chose a file
		 * Using method readProblem
		 * Print out path to file
		 */
		JTextArea txtr_PleaseImport = new JTextArea();								// create textArea "please import"
		txtr_PleaseImport.setText("1. Please import your warehouse configutaiton"); // insert text
		txtr_PleaseImport.setForeground(Color.ORANGE);								// color text
		txtr_PleaseImport.setFont(new Font("Arial", Font.BOLD, 16)); 				// text properties
		txtr_PleaseImport.setBackground(Color.DARK_GRAY);							// background color of textArea
		txtr_PleaseImport.setBounds(634, 53, 404, 33);								// size textArea
		panel.add(txtr_PleaseImport);												// adding to the panel
			
		JButton button_import = new JButton("Import");					// create button "Import"
		button_import.addActionListener(new ActionListener() {			// adding action to the button
			public void actionPerformed(ActionEvent e) {				// button action
				String filename = "";								
				String dir = "";
				
				JFileChooser c = new JFileChooser();				
				int rVal = c.showOpenDialog(Interface.this);			// open dialog window to choose file
				if (rVal == JFileChooser.APPROVE_OPTION) {			
					filename = c.getSelectedFile().getName();			// filename
				    dir = c.getCurrentDirectory().toString();			// directory of file
				}
				
				Path p = Paths.get(dir, filename);						// path to file including its directory and name
				String path = p.toString();
				textArea_Output.setText(null);							// clear textArea_Output
				System.out.println("You imported the file: " + path);	// print out path to file
				readProblem(p);											// see method readProblem
			}
		});
		button_import.setFont(new Font("Arial", Font.PLAIN, 11));		// button text properties
		button_import.setBounds(1048, 55, 89, 23);						// button size
		panel.add(button_import);										// adding button to panel
		
		
		/**
		 * textArea for "insert order"
		 * Button "insert" order
		 * Open Dialog window to chose a file
		 * Using method readOrder
		 * Print out path to file
		 */
		JTextArea txtr_PleaseInsert = new JTextArea();					// create textArea "please insert"
		txtr_PleaseInsert.setText("2. Please import your order");		// insert text
		txtr_PleaseInsert.setForeground(Color.ORANGE);					// color text
		txtr_PleaseInsert.setFont(new Font("Arial", Font.BOLD, 16));	// properties text
		txtr_PleaseInsert.setBackground(Color.DARK_GRAY);				// background color of textArea
		txtr_PleaseInsert.setBounds(634, 124, 348, 33);					// size textArea
		panel.add(txtr_PleaseInsert);									// adding to the panel
		
		JButton btnImport = new JButton("Import");					// create button "insert"
		btnImport.addActionListener(new ActionListener() {			// adding action to the button
			public void actionPerformed(ActionEvent e) {				// button action
				String filename = "";
				String dir = "";
				
				JFileChooser c = new JFileChooser();				
				int rVal = c.showOpenDialog(Interface.this);			// open dialog window to choose file
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename = c.getSelectedFile().getName();			// filename
				    dir = c.getCurrentDirectory().toString();			// directory to file
				}
				
				Path p = Paths.get(dir, filename);						// path to file including its directory and name
				String path = p.toString();							
				textArea_Output.setText(null);							// clear textArea_Output
				System.out.println("You imported the file: " + path);	// print out oath to file
				readOrder(p);											// see method readOrder
			}
		});
		btnImport.setFont(new Font("Arial", Font.PLAIN, 11));		// button text properties
		btnImport.setBounds(1048, 126, 89, 23);						// button size
		panel.add(btnImport);										// adding button to panel
		
		/**
		 * textArea "please choose"
		 */
		JTextArea txtr_PleaseChooseBetween = new JTextArea();				// create textArea "please choose"
		txtr_PleaseChooseBetween.setText("Please choose between these algorithms \r\nto run the programm:"); // insert text
		txtr_PleaseChooseBetween.setForeground(Color.ORANGE);				// text color
		txtr_PleaseChooseBetween.setFont(new Font("Arial", Font.BOLD, 16));	// text properties
		txtr_PleaseChooseBetween.setBackground(Color.DARK_GRAY);			// background color of textArea
		txtr_PleaseChooseBetween.setBounds(634, 185, 449, 42);				// size textArea
		panel.add(txtr_PleaseChooseBetween);								// adding textArea to panel
		
		/**
		 * textArea "Hill Climbing"
		 * Button "run" for Hill Climbing
		 * Method Hill Climbing
		 */
		txtHillClimbing = new JTextField();								// create textArea
		txtHillClimbing.setBackground(Color.LIGHT_GRAY);				// background color of textArea 
		txtHillClimbing.setFont(new Font("Arial", Font.PLAIN, 14));		// text properties
		txtHillClimbing.setText("Hill- Climbing");						// insert text
		txtHillClimbing.setBounds(634, 238, 289, 23);					// size textArea
		panel.add(txtHillClimbing);										// adding textArea to panel
		
		JButton button_HC = new JButton("Run");							// create button "run" for Hill Climbing
		button_HC.addActionListener(new ActionListener() {				// adding action to the button
			public void actionPerformed(ActionEvent e) {				// button action
				textArea_Output.setText(null);							// clear textArea_Output
				Algorithms1.Hill_Climbing();							// see method Hill_Climbing
			}
		});
		button_HC.setForeground(Color.BLACK);							// text color
		button_HC.setFont(new Font("Arial", Font.BOLD, 11));			// text properties
		button_HC.setBounds(959, 238, 89, 23);							// button size
		panel.add(button_HC);											// adding button to panel
		
		/**
		 * textArea "First Choice Hill Climbing"
		 * Button "run" for First Choice Hill Climbing
		 * Method First Choice Hill Climbing
		 */
		txtFirstchoiceHillclimbing = new JTextField();							// create textArea			
		txtFirstchoiceHillclimbing.setBackground(Color.LIGHT_GRAY);				// background color of textArea
		txtFirstchoiceHillclimbing.setText("First-Choice Hill-Climbing");		// insert text
		txtFirstchoiceHillclimbing.setFont(new Font("Arial", Font.PLAIN, 14));	// text properties
		txtFirstchoiceHillclimbing.setBounds(634, 272, 289, 23);				// size textArea
		panel.add(txtFirstchoiceHillclimbing);									// adding textArea to panel
		
		JButton button_FCHC = new JButton("Run");						// create button "run" for First Choice Hill Climbing
		button_FCHC.addActionListener(new ActionListener() {			// adding action to the button
			public void actionPerformed(ActionEvent e) {				// button action
				textArea_Output.setText(null);							// clear textArea_Output
				Algorithms1.First_Choice_Hill_Climbing();				// see method First_Choice_Hill_Climbing
			}
		});
		button_FCHC.setForeground(Color.BLACK);							// text color
		button_FCHC.setFont(new Font("Arial", Font.BOLD, 11));			// text properties
		button_FCHC.setBounds(959, 272, 89, 23);						// button size
		panel.add(button_FCHC);											// adding button to panel
			
		/**
		 * textArea "Parallel Hill Climbing"
		 * pop up window to insert how much Hill Climbing should run parallel 
		 * Button "run" for Parallel Choice Hill Climbing
		 * Method First Choice Hill Climbing
		 */
		txtParallelHillclimbing = new JTextField();							// create textArea
		txtParallelHillclimbing.setBackground(Color.LIGHT_GRAY);			// background color of textArea
		txtParallelHillclimbing.setText("Parallel Hill-Climbing");			// insert text
		txtParallelHillclimbing.setFont(new Font("Arial", Font.PLAIN, 14));	// text properties
		txtParallelHillclimbing.setBounds(634, 305, 289, 23);				// size textArea
		panel.add(txtParallelHillclimbing);									// adding textArea to panel
		
		JButton button_PHC = new JButton("Run");							// create button "run" for Parallel Hill Climbing
		button_PHC.addActionListener(new ActionListener() {					// adding action to the button
			public void actionPerformed(ActionEvent e) {					// button action
				
				
				int ans = Integer.parseInt(JOptionPane.showInputDialog(Interface.this, "Insert number of states", "Title", JOptionPane.INFORMATION_MESSAGE)); 	// pop up window to insert how often HC should run parallel
				// System.out.println(ans);
				textArea_Output.setText(null);								// clear textArea_Output
				Algorithms1.Parallel_Hill_Climbing(ans);					// see method Parallel_Hill_Climbing
			}
		});
		button_PHC.setForeground(Color.BLACK);							// color text
		button_PHC.setFont(new Font("Arial", Font.BOLD, 11));			// text properties
		button_PHC.setBounds(959, 306, 89, 23);							// button size
		panel.add(button_PHC);											// adding button to panel
		
		/**
		 * textArea "Simulated Annealing"
		 * Button "run" for Simulated Annealing
		 * No call for method Simulated Annealing! instead print out.
		 */
		
		txtSimulatedAnnealing = new JTextField();							// create new textArea
		txtSimulatedAnnealing.setText("Simulated Annealing");				// insert text
		txtSimulatedAnnealing.setFont(new Font("Arial", Font.PLAIN, 14));	// text properties
		txtSimulatedAnnealing.setBackground(Color.LIGHT_GRAY);				// background color text area
		txtSimulatedAnnealing.setBounds(634, 339, 289, 23);					// size textArea
		panel.add(txtSimulatedAnnealing);									// adding textArea to panel
			
		JButton button_SA = new JButton("Run");												// create button "run" for Simulated Annealing
		button_SA.addActionListener(new ActionListener() {										// adding action to the button
			public void actionPerformed(ActionEvent arg0) {									// button action
				textArea_Output.setText(null);												// clear textArea_Output
				// Algorithms3.Simulated_Annealing();										// see method Simulated_Annealing
				System.out.println("The algorithm Simulated Annealing can not be run.");	// since the method Simulated Annealing is not running, print out this
				
			}
		});
		button_SA.setForeground(Color.BLACK);								// color text
		button_SA.setFont(new Font("Arial", Font.BOLD, 11));				// text properties
		button_SA.setBounds(959, 340, 89, 23);								// size button
		panel.add(button_SA);												// adding button to panel
		
		
		/**
		 * textArea "Local Beam Search"
		 * Button "run" for Local Beam Search
		 * No call for method Local Beam Search! instead print out.
		 */
		
		txtLocalBeamSearch = new JTextField();							// create new textArea
		txtLocalBeamSearch.setText("Local Beam Search");				// insert text
		txtLocalBeamSearch.setFont(new Font("Arial", Font.PLAIN, 14));	// text properties
		txtLocalBeamSearch.setBackground(Color.LIGHT_GRAY);				// background color
		txtLocalBeamSearch.setBounds(634, 373, 289, 23);				// size textArea
		panel.add(txtLocalBeamSearch);									// adding textArea to panel
		

		JButton button_LBS = new JButton("Run");											// create button "run" for Local Beam Search
		button_LBS.addActionListener(new ActionListener() {									// adding action to the button
			public void actionPerformed(ActionEvent e) {									// button action
				textArea_Output.setText(null);												// clear textArea_Output
				
				int ans = Integer.parseInt(JOptionPane.showInputDialog(Interface.this, "Insert number of states", "Title", JOptionPane.INFORMATION_MESSAGE)); 	// pop up window to insert how often HC should run parallel
				// System.out.println(ans);
				textArea_Output.setText(null);							// clear textArea_Output
				// Algorithms2.local_beam_search(ans);					// see method local beam search		
				System.out.println("The algorithm Local Beam Search can not be run."); 	// since the method Local Beam Search is not running, print out this
			}
		});
		button_LBS.setForeground(Color.BLACK);							// color text
		button_LBS.setFont(new Font("Arial", Font.BOLD, 11));				// text properties
		button_LBS.setBounds(959, 374, 89, 23);							// size button
		panel.add(button_LBS);											// adding button to panel
		
	
		/**
		 * textAreas "result" and "output"
		 */
		JTextArea txtrResult = new JTextArea();							// create textArea "Result"
		txtrResult.setText("Result:");									// insert text
		txtrResult.setFont(new Font("Arial", Font.BOLD, 20));			// text properties
		txtrResult.setForeground(Color.ORANGE);							// text  color
		txtrResult.setBackground(Color.DARK_GRAY);						// background color textArea
		txtrResult.setBounds(33, 398, 89, 23);							// size textArea
		panel.add(txtrResult);											// adding textArea to panel
		
		textArea_Output = new JTextArea(50,10);													// create textArea "Output"
		textArea_Output.setFont(new Font("Arial", Font.PLAIN, 14));								// text properties
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea_Output)); 	// print out everything on this textArea instead of Console!!!
		System.setOut(printStream);
		System.setErr(printStream);
		textArea_Output.setBounds(33, 432, 1240, 307);											// size textArea
		panel.add(textArea_Output);																// adding textArea to panel
	
	}

			
	/**
	 * File reader for the warehouse configuration
	 * @param filepath to imported warehouse configuration
	 */
	public static void readProblem(Path filepath) {
		try {
			List<String> lines = Files.readAllLines(filepath);				// read all lines of the file
			String warehouse = lines.get(0);								// line 1 are all items of the warehouse
			String[] items = warehouse.trim().split(" ");					// items are separated through space
			
			int nr_psus = lines.size() - 2;								// PSUs starts with line 3
			int[][] psus = new int[nr_psus][0];							// each line is a new PSU
			for (int i = 2; i < lines.size(); i++) {						// for all PSUs:
				String[] psu_items = lines.get(i).trim().split(" ");		// in each line items stored in PSU are separated through space
				psus[i - 2] = new int[psu_items.length];					// create new arrays with length of each individual PSU 
				for (int j = 0; j < psu_items.length; j++) {				// for each PSU:
					int index = Arrays.asList(items).indexOf(psu_items[j]);	// replace real name of item with numbers as place holders according to the position in the warehouse
					psus[i - 2][j] = index;
				}
			}
			
			Algorithms1.PSU_List = psus;									// use this for the algorithms
			Algorithms1.Items = items;										// use this for the algorithms
			
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * File reader for the order 
	 * @param filepath to the inserted order
	 */
	public static void readOrder(Path filepath) {
		try {
			List<String> lines = Files.readAllLines(filepath);						// read all lines of the file					
			String[] items = lines.get(0).trim().split(" ");						// items are separated through space
			
			int[] order_list = new int[items.length];								// create new array with length of the items
			for (int i = 0; i < items.length; i++) {								// for each item:
				int index = Arrays.asList(Algorithms1.Items).indexOf(items[i]);		// replace the real name with numbers as place holders according to the warehouse according to the position in the warehouse
				order_list[i] = index;
			}
					
			Algorithms1.Orig_Order_List = order_list;								// use this for the algorithms
			
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
}


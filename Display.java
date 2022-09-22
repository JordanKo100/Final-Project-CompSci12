import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

 /*
  * Display class is a game program that makes the user find islands.
  * If the user loses all their lives by clicking on an ocean square then they've lose the game. 
  * If the user gains 5 points by finding all parts of an island or individual islands then they've won the game.
  */
public class Display implements ActionListener {

    int lives = 15; // Index for when the user loses a life
    int island_counter = 0; //Index for how many islands the user finds

    String game_description = "HOW TO PLAY: Click Start to begin game and then Guess and Click to try and find the islands!! If you can score 5 points without loosing all your lives. YOU WIN!";

    ArrayList<Integer> island_squares = new ArrayList<Integer>(); // Array List is used to have a flexible array that can call a shuffle method from the imported Collections class
    int num_CorrectIslands = 5; // The number of island parts the user needs to find

    JFrame frame = new JFrame();

    JPanel tile_panel = new JPanel();

    JButton[] button = new JButton[25];
    JButton reset_button = new JButton();
    JButton start_button = new JButton();

    JTextArea description = new JTextArea();

    JTextField title = new JTextField();
    JTextField motto = new JTextField();
    JTextField failure = new JTextField();
    JTextField success = new JTextField();
    JTextField life_label = new JTextField();
    JTextField score_label = new JTextField();

    JLabel score_counter = new JLabel();
    JLabel life_counter = new JLabel();
   

    /*
     * Constructor method to display the whole frame with all contents inside.
     */
    public Display(){

      // Initizializing values from 0 - 24 to the array list and for used later
      for (int i = 0; i < button.length; i++){
        island_squares.add(i);
      }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,700);
        frame.getContentPane().setBackground(new Color(219, 188, 35));
        frame.setLayout(null);

        title.setBounds(0,0,600,55);
        title.setBackground(new Color(219, 188, 35));
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Ink Free", Font.BOLD, 50));
        title.setEditable(false);
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setBorder(null);
        title.setText("Island Findings");

        motto.setBounds(0,60,600,25);
        motto.setBackground(new Color(219, 188, 35));
        motto.setForeground(Color.BLACK);
        motto.setFont(new Font("Ink Free", Font.BOLD, 25));
        motto.setEditable(false);
        motto.setHorizontalAlignment(JTextField.CENTER);
        motto.setBorder(null);
        motto.setText("Find the Islands!");

        // Create a text area to displays instructions
        description.setBounds(440, 100, 130,260);
        description.setBackground(new Color(219, 188, 35));
        description.setForeground(Color.BLACK);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setFont(new Font("Ink Free", Font.PLAIN, 18));
        description.setBorder(null);
        description.setEditable(false);
        description.setText(game_description);

        life_label.setBounds(480,360,100,25);
        life_label.setBackground(new Color(219, 188, 35));
        life_label.setForeground(Color.BLACK);
        life_label.setFont(new Font("Ink Free",Font.PLAIN,25));
        life_label.setText("Lives: ");
        life_label.setBorder(null);
        life_label.setEditable(false);

        // Create a textfield to display life counter
        life_counter.setBounds(500,400,100,25);
        life_counter.setForeground(Color.BLACK);
        life_counter.setFont(new Font("Ink Free",Font.PLAIN,25));
        life_counter.setText(String.valueOf(lives));
        
        score_label.setBounds(480,470,100,25);
        score_label.setBackground(new Color(219, 188, 35));
        score_label.setForeground(Color.BLACK);
        score_label.setFont(new Font("Ink Free",Font.PLAIN,25));
        score_label.setText("Score: ");
        score_label.setBorder(null);
        score_label.setEditable(false);

        // Create a textfield to display score counter
        score_counter.setBounds(500,510,100,25);
        score_counter.setForeground(Color.BLACK);
        score_counter.setFont(new Font("Ink Free",Font.PLAIN,25));
        score_counter.setText(String.valueOf(island_counter));

        // Create a panel to store all the squares
        tile_panel.setBounds(25,150,400,400);
        tile_panel.setBackground(Color.CYAN);
        tile_panel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
        tile_panel.setLayout(new GridLayout(5,5));

        // Creating 25 panels to be added into the panel
        for (int i = 0; i < button.length; i++){
            button[i] = new JButton();
            button[i].setBackground(Color.WHITE);
            button[i].setSize(80,80);
            button[i].setEnabled(false);
            button[i].addActionListener(this);

            tile_panel.add(button[i]);
        }

        // Create Start Button
        start_button.setBounds(260,100,100,25);
        start_button.addActionListener(this);
        start_button.setBackground(Color.WHITE);
        start_button.setForeground(Color.BLACK);
        start_button.setFont(new Font("Times New Roman", Font.PLAIN,25));
        start_button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        start_button.setText("Start");
     

        // Create Reset Button
        reset_button.setBounds(470,600,75,25);
        reset_button.addActionListener(this);
        reset_button.setBackground(Color.WHITE);
        reset_button.setForeground(Color.BLACK);
        reset_button.setFont(new Font("Times New Roman", Font.PLAIN,25));
        reset_button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        reset_button.setEnabled(false);
        reset_button.setText("Reset");

        frame.add(description);
        frame.add(motto);
        frame.add(start_button);
        frame.add(reset_button);
        frame.add(tile_panel);
        frame.add(score_label);
        frame.add(score_counter);
        frame.add(life_label);
        frame.add(life_counter);
        frame.add(title);
        frame.setVisible(true);
    }

    /*
     * Action performed method is called whenever a button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        boolean island = false;

        // Game starts when start button is clicked
        if(e.getSource() == start_button){

          for(int i = 0; i < button.length; i++){
            button[i].setEnabled(true);
          }

          reset_button.setEnabled(true);

          start_button.setEnabled(false);

          // (NOT NECESSARY FOR CODE FUNCTION) Helped to know the values in array list before being shuffled
          System.out.println("BEFORE SHUFFLE: ");
          for (int i = 0; i < 25; i++){
            System.out.print(island_squares.get(i) + "," + " ");
          }
          System.out.println("");

          Collections.shuffle(island_squares); // (IMPORTANT) Shuffles the array list to create more randomness
    
          // (NOT NECESSARY FOR CODE FUNCTION) Helped to know the values in array list after being shuffled
          System.out.println("Island Locations: ");
          for (int i = 0; i < num_CorrectIslands; i++){
            System.out.print(island_squares.get(i) + "," + " ");
          }
          System.out.println("");
        }

        // Reset button to restart game
        if (e.getSource() == reset_button){

          // (NOT NECESSARY FOR CODE FUNCTION) Helped to know the values in array list before being shuffled AGAIN
          System.out.println("BEFORE SHUFFLED AGAIN: ");
          for (int i = 0; i < button.length; i++){
            System.out.print(island_squares.get(i) + "," + " ");
          }
          System.out.println("");

          Collections.shuffle(island_squares); // (IMPORTANT) Shuffles the array list again to create greater more randomness
          
          // (NOT NECESSARY FOR CODE FUNCTION) Helped to know the values in array list after being shuffled
          System.out.println("Island Locations: ");
          for (int i = 0; i < num_CorrectIslands; i++){
            System.out.print(island_squares.get(i) + "," + " ");
          }
          System.out.println("");

          if(lives == 0){
            failure.setVisible(false);
            frame.remove(failure); // Will remove "game over" text field 
          }
          else if(island_counter == num_CorrectIslands){
            success.setVisible(false);
            frame.remove(success); // Will remove "you won" text field
          }

          for(int i = 0; i < button.length; i++){
            button[i].setBackground(Color.WHITE);
            button[i].setEnabled(true);
          }          

          // Resets counters
          island_counter = 0; 
          score_counter.setText(String.valueOf(island_counter));
          lives = 15;
          life_counter.setText(String.valueOf(lives));
        }

        // Chunk of code to determine what actions to take when the buttons in the 5x5 grid is clicked on 
        for(int i = 0; i < button.length; i++){
            if (e.getSource() == button[i]){
                for (int k = 0; k < num_CorrectIslands; k++){
                    if (island_squares.get(k) == i){
                      button[i].setBackground(new Color(219, 188, 35));
                      button[i].setEnabled(false);
                      island = true;
                      island_counter++;
                      score_counter.setText(String.valueOf(island_counter));
                      k = num_CorrectIslands;
                    } 
                }
                if (island == false){
                    button[i].setBackground(Color.CYAN);
                    button[i].setEnabled(false);
                    lives--;
                    life_counter.setText(String.valueOf(lives));
                    gameOver();
                }
                else if (island_counter == num_CorrectIslands){
                  gameOver();
                }
            }
        }
    }

    /*
     * Game over method is called when the game is finished.
     * Either it will display "YOU DIED" or "YOU WON" if one of the IF statements have been satisfied.
     */
    public void gameOver(){

      if(lives == 0){

        for(int i = 0; i < button.length; i++){
          button[i].setEnabled(false);
          button[i].setBackground(Color.RED);
        }

        // Create "Game Over" text field
        failure.setBounds(100,580,250,50);
        failure.setBackground(Color.BLACK);
        failure.setForeground(Color.RED);
        failure.setFont(new Font("Times New Roman",Font.PLAIN,50));
        failure.setBorder(null);
        failure.setEditable(false);
        failure.setText("YOU DIED");
        failure.setVisible(true);

        frame.add(failure);
      }
      else if (island_counter == num_CorrectIslands){

        for(int i = 0; i < button.length; i++){
          button[i].setEnabled(false);
          button[i].setBackground(Color.GREEN);
        }

        // Create "You Won" text field
        success.setBounds(100,580,250,50);
        success.setBackground(Color.WHITE);
        success.setForeground(Color.GREEN);
        success.setFont(new Font("Free Ink",Font.PLAIN,50));
        success.setBorder(null);
        success.setEditable(false);
        success.setText("YOU WON");
        success.setVisible(true);

        frame.add(success);
      }
    }   
}
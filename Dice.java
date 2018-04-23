
/*
 * Nick Shannon, Luke Johnson, Jared Crouse
 * CSC 161 Final project
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Dice extends Application
{
	public static java.io.File inFile;

	public static void main( String[ ] args ) throws IOException
	{
		
		highScores(1);
		System.out.println ( highScores(8) );
		playerName("Matt Smitg", 40);
		launch ( args );

	}

	

	
	
	
	//work in progress for the highscores output
	
	public static String highScores(int loc) throws FileNotFoundException
	{
		java.io.File inFile;
		inFile = new java.io.File ( "scores.txt" );
		Scanner input = new Scanner(inFile);
		ArrayList<HighScores> leaderBoard = new ArrayList<HighScores>();
		
		while(input.hasNext ( ))
		{
			String name = input.nextLine ( );
			int score = input.nextInt ( );
			String junk = input.nextLine ( );
			leaderBoard.add ( new HighScores (name, score));
		}
		Collections.sort(leaderBoard, new SortByScore());
		String names = leaderBoard.get ( loc ).getPlayerName();
		String score = Integer.toString ( leaderBoard.get ( loc ).getScore ( ) );
		String topScore = ( names + "\t\t" + score);
		return topScore;	
	}
	
	
	
	
	
	
	public static void playerName( String name, int score) throws IOException
	{

		PrintWriter writer = new PrintWriter ( new FileWriter( "scores.txt", true) );

		writer.println ( name );
		writer.println ( score );
		writer.close();

	}

	/****************
	 * @author Nicks
	 * @return the randomized resource name
	 */
	public static String getDieRolls( )
	{
		int die1 = 0;
		String dieString = "No Dice";
		for ( int i = 0; i < 1; i++ )
		{
			die1 = (int) ( Math.random ( ) * 5 + 1 );
			switch ( die1 )
			{
				case 1:
					dieString = "Dihydrogen Monoxide";
					break;
				case 2:
					dieString = "Silicon Dioxide";
					break;
				case 3:
					dieString = "Iron Ore";
					break;
				case 4:
					dieString = "Oxygen";
					break;
				case 5:
					dieString = "Solar Batteries";
					break;
				case 0:
					dieString = "Yarr thar be a grave ERROR";
					break;
			}

			// System.out.println ( dieString );
		}
		return dieString;
	}

	/**********************
	 * @author Nicks
	 * @param button
	 * @return the image related to the name for each button
	 */
	public static Image getImage( Button button )
	{
		Image water = new Image ( "water.png" );
		Image ore = new Image ( "ore_icon.png" );
		Image oxygen = new Image ( "oxygen_icon.png" );
		Image silicon = new Image ( "silicon_icon.png" );
		Image solar = new Image ( "solar_icon.png" );

		Image imageName = water;
		switch ( button.getText ( ) )
		{
			case "Dihydrogen Monoxide":
				button.setGraphic ( new ImageView ( water ) );
				break;
			case "Silicon Dioxide":
				button.setGraphic ( new ImageView ( silicon ) );
				break;
			case "Iron Ore":
				button.setGraphic ( new ImageView ( ore ) );
				break;
			case "Oxygen":
				button.setGraphic ( new ImageView ( oxygen ) );
				break;
			case "Solar Batteries":
				button.setGraphic ( new ImageView ( solar ) );
				break;

		}
		return imageName;

	}

	/*********************
	 * @author Nicks
	 * @param check
	 *           the check box
	 * @param button
	 *           the corrisponding button
	 */
	public static void checkClicked( CheckBox check, Button button )
	{
		check.selectedProperty ( ).addListener ( new ChangeListener<Boolean> ( )
		{
			public void changed( ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val )
			{
				System.out.println ( check.isSelected ( ) );
				if ( check.isSelected ( ) )
				{
					button.setDisable ( true );
				}
				else
				{
					button.setDisable ( false );
				}
			}
		} );

	}
	int  rollCount = 3;
	/*****************
	 * sets the stage
	 * 
	 * @throws IOException
	 */
	@Override
	public void start( Stage primaryStage ) throws IOException
	{
		
		inFile = new java.io.File ( "scores.txt" );
		Scanner input = new Scanner(inFile);
		
			
		
		

		Die die1 = new Die ( );
		Die die2 = new Die ( );
		Die die3 = new Die ( );
		Die die4 = new Die ( );
		Die die5 = new Die ( );
		Die die6 = new Die ( );
		primaryStage.setTitle ( "Roll Dice!" );
		
		
		
		// initialize the buttons
		Button rollButton = new Button ( );
		Button die1Button = new Button ( );
		Button die2Button = new Button ( );
		Button die3Button = new Button ( );
		Button die4Button = new Button ( );
		Button die5Button = new Button ( );
		Button die6Button = new Button ( );
		CheckBox d1Check = new CheckBox ( "Hold" );
		CheckBox d2Check = new CheckBox ( "Hold" );
		CheckBox d3Check = new CheckBox ( "Hold" );
		CheckBox d4Check = new CheckBox ( "Hold" );
		CheckBox d5Check = new CheckBox ( "Hold" );
		CheckBox d6Check = new CheckBox ( "Hold" );
		Button commit = new Button ( );
		Text count = new Text ( "Rolls left " + rollCount );

		// d1Check.setText ( "Hold" );
		rollButton.setText ( "'Roll Dice'" );
		commit.setText ( "Keep Dice" );
		
		String[ ] finalDice = new String[6];

		GridPane grid = new GridPane ( );
		grid.setAlignment ( Pos.CENTER );
		grid.setHgap ( 10 );
		grid.setVgap ( 10 );
		grid.add ( rollButton, 3, 4 );
		grid.add ( die1Button, 1, 0 );
		grid.add ( die2Button, 2, 0 );
		grid.add ( die3Button, 3, 0 );
		grid.add ( die4Button, 4, 0 );
		grid.add ( die5Button, 5, 0 );
		grid.add ( die6Button, 6, 0 );
		grid.add ( d1Check, 1, 2 );
		grid.add ( d2Check, 2, 2 );
		grid.add ( d3Check, 3, 2 );
		grid.add ( d4Check, 4, 2 );
		grid.add ( d5Check, 5, 2 );
		grid.add ( d6Check, 6, 2 );
		grid.add ( commit, 3, 5 );
		grid.add ( count, 0, 1 );
		

		// runs through to check for saved dice
		checkClicked ( d1Check, die1Button );
		checkClicked ( d2Check, die2Button );
		checkClicked ( d3Check, die3Button );
		checkClicked ( d4Check, die4Button );
		checkClicked ( d5Check, die5Button );
		checkClicked ( d6Check, die6Button );

		// gets the button rolls sets icons and names
		

		
		Text highScores = new Text ( );
		grid.add ( highScores, 6, 6 );
		
		
		
		

		
		// commit.setDisable ( true );

		rollButton.setOnAction ( new EventHandler<ActionEvent> ( )
		{
			
			@Override
			public void handle( ActionEvent event )
			{
				count.setText (  "Rolls left " + ( --rollCount) );	
				if(rollCount == 0)
				{
					rollButton.setDisable ( true );
					Turn turn = new Turn ( );
					finalDice[0] = die1.getDieString ( );
					finalDice[1] = die2.getDieString ( );
					finalDice[2] = die3.getDieString ( );
					finalDice[3] = die4.getDieString ( );
					finalDice[4] = die5.getDieString ( );
					finalDice[5] = die6.getDieString ( );
					for ( int i = 0; i < finalDice.length; i++ )
					{
						System.out.println ( finalDice[i] );
					}
					for ( int i = 0; i < finalDice.length; i++ )
					{
						switch ( finalDice[i] )
						{
							case "Dihydrogen Monoxide":
								turn.setWat ( turn.getWat ( ) + 1 );
								break;
							case "Silicon Dioxide":
								turn.setSil ( turn.getSil ( ) + 1 );
								break;
							case "Iron Ore":
								turn.setOre ( turn.getOre ( ) + 1 );
								break;
							case "Oxygen":
								turn.setOx ( turn.getOx ( ) + 1 );
								break;
							case "Solar Batteries":
								turn.setSol ( turn.getSol ( ) + 1 );
								break;

						}
					}
					turn.toString ( );	
					d1Check.setSelected ( true );
					d2Check.setSelected ( true );
					d3Check.setSelected ( true );
					d4Check.setSelected ( true );
					d5Check.setSelected ( true );
					d6Check.setSelected ( true );
					commit.setDisable ( true );
				}
				if ( die1Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{
					die1.setDieString ( getDieRolls ( ) );
					die1Button.setContentDisplay ( ContentDisplay.TOP );
					die1Button.setText ( die1.getDieString ( ) );
					
					getImage ( die1Button );

					// die1Button.setGraphic ( getImage(die1, die1Button));
				}
				if ( die2Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{

					die2.setDieString ( getDieRolls ( ) );
					die2Button.setContentDisplay ( ContentDisplay.TOP );
					die2Button.setText ( die2.getDieString ( ) );
					getImage ( die2Button );
				}
				if ( die3Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{
					die3.setDieString ( getDieRolls ( ) );
					die3Button.setContentDisplay ( ContentDisplay.TOP );
					die3Button.setText ( die3.getDieString ( ) );
					getImage ( die3Button );
				}
				if ( die4Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{
					die4.setDieString ( getDieRolls ( ) );
					die4Button.setContentDisplay ( ContentDisplay.TOP );
					die4Button.setText ( die4.getDieString ( ) );
					getImage ( die4Button );
				}
				if ( die5Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{
					die5.setDieString ( getDieRolls ( ) );
					die5Button.setContentDisplay ( ContentDisplay.TOP );
					die5Button.setText ( die5.getDieString ( ) );
					getImage ( die5Button );
				}
				if ( die6Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{
					die6.setDieString ( getDieRolls ( ) );
					die6Button.setContentDisplay ( ContentDisplay.TOP );
					die6Button.setText ( die6.getDieString ( ) );
					getImage ( die6Button );
				}
			}

		} );

		Turn turn = new Turn ( );
		commit.setOnAction ( new EventHandler<ActionEvent> ( )
		{

			@Override
			public void handle( ActionEvent event )
			{
				finalDice[0] = die1.getDieString ( );
				finalDice[1] = die2.getDieString ( );
				finalDice[2] = die3.getDieString ( );
				finalDice[3] = die4.getDieString ( );
				finalDice[4] = die5.getDieString ( );
				finalDice[5] = die6.getDieString ( );
				for ( int i = 0; i < finalDice.length; i++ )
				{
					System.out.println ( finalDice[i] );
				}
				for ( int i = 0; i < finalDice.length; i++ )
				{
					switch ( finalDice[i] )
					{
						case "Dihydrogen Monoxide":
							turn.setWat ( turn.getWat ( ) + 1 );
							break;
						case "Silicon Dioxide":
							turn.setSil ( turn.getSil ( ) + 1 );
							break;
						case "Iron Ore":
							turn.setOre ( turn.getOre ( ) + 1 );
							break;
						case "Oxygen":
							turn.setOx ( turn.getOx ( ) + 1 );
							break;
						case "Solar Batteries":
							turn.setSol ( turn.getSol ( ) + 1 );
							break;

					}
				}
				turn.toString ( );
			}

		} );


		primaryStage.setMaximized ( true );
		primaryStage.setScene ( new Scene ( grid, 900, 900 ) );
		primaryStage.show ( );
	}
}

class SortByScore implements Comparator<HighScores>
{
    // Used for sorting in ascending order of score number
     
    public int compare(HighScores a, HighScores b)
    {
        return b.getScore ( ) - a.getScore();
    }
}
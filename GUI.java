import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GUI extends Application
{
	public static void main( String[ ] args ) throws FileNotFoundException
	{
		Application.launch ( args );

	}

	@Override // Override the start method in the Application class
	public void start( Stage primaryStage ) throws FileNotFoundException
	{
		StackPane root = new StackPane ( );
		Scene scene = new Scene ( root, 1920, 1080 );
		Image splash = new Image ( "Background.jpeg" );

		// Colonize Mars title with dropshadow
		DropShadow shadow = new DropShadow ( );
		shadow.setRadius ( 10.0 );
		shadow.setOffsetX ( 5.0 );
		shadow.setOffsetY ( 5.0 );
		Text title = new Text ( );
		title.setEffect ( shadow );
		title.setCache ( true );
		title.setTranslateY ( -250 );
		title.setFill ( Color.GREEN );
		title.setText ( "Colonize Mars" );
		title.setFont ( Font.font ( null, 200 ) );

		// Player name text
		Text playerName = new Text ( );
		playerName.setCache ( true );
		playerName.setTranslateX ( -200 );
		playerName.setTranslateY ( -150 );
		playerName.setText ( "Enter your name:" );
		playerName.setFont ( Font.font ( null, FontWeight.BOLD, 35 ) );

		// The name text field
		TextField name = new TextField ( );
		name.setMaxWidth ( 150 );
		name.setTranslateX ( 55 );
		name.setTranslateY ( -150 );

		// Error label
		Label errorLabel = new Label ( );
		errorLabel.setTextFill ( Color.RED );
		errorLabel.setFont ( Font.font ( null, 15 ) );
		errorLabel.setTranslateX ( 115 );
		errorLabel.setTranslateY ( -126 );

		// Start button
		Button start = new Button ( "Launch!" );
		start.setOnAction ( new EventHandler<ActionEvent> ( )
		{
			@Override
			public void handle( ActionEvent event )
			{
				if ( name.getText ( ).isEmpty ( ) )
				{
					errorLabel.setText ( "You must enter a name to continue!" );
				}
				else
				{
					String playerName = name.getText ( );
					SecondStage playerWindow = new SecondStage ( playerName );
					playerWindow.start ( playerWindow );
					primaryStage.close ( );
				}
			}// end action
		} );
		start.setTranslateX ( 180 );
		start.setTranslateY ( -150 );

		Text highScores = new Text ( );
		highScores.setText ( "High Scores" );
		highScores.setFill ( Color.YELLOW );
		highScores.setTranslateY ( -50 );
		highScores.setFont ( Font.font ( null, FontWeight.BOLD, 40 ) );

		Text score1 = new Text ( );
		score1.setFill ( Color.YELLOW );
		score1.setText ( highScores ( 0 ) );
		score1.setFont ( Font.font ( null, 35 ) );
		Text score2 = new Text ( );
		score2.setFill ( Color.YELLOW );
		score1.setTranslateY ( 50 );
		score2.setText ( highScores ( 1 ) );
		score2.setFont ( Font.font ( null, 35 ) );
		Text score3 = new Text ( );
		score3.setFill ( Color.YELLOW );
		score3.setTranslateY ( 100 );
		score3.setText ( highScores ( 2 ) );
		score3.setFont ( Font.font ( null, 35 ) );
		Text score4 = new Text ( );
		score4.setFill ( Color.YELLOW );
		score4.setTranslateY ( 150 );
		score4.setText ( highScores ( 3 ) );
		score4.setFont ( Font.font ( null, 35 ) );
		Text score5 = new Text ( );
		score5.setFill ( Color.YELLOW );
		score5.setTranslateY ( 200 );
		score5.setText ( highScores ( 4 ) );
		score5.setFont ( Font.font ( null, 35 ) );
		Text score6 = new Text ( );
		score6.setFill ( Color.YELLOW );
		score6.setTranslateY ( 250 );
		score6.setText ( highScores ( 5 ) );
		score6.setFont ( Font.font ( null, 35 ) );
		Text score7 = new Text ( );
		score7.setFill ( Color.YELLOW );
		score7.setTranslateY ( 300 );
		score7.setText ( highScores ( 6 ) );
		score7.setFont ( Font.font ( null, 35 ) );
		Text score8 = new Text ( );
		score8.setFill ( Color.YELLOW );
		score8.setTranslateY ( 350 );
		score8.setText ( highScores ( 7 ) );
		score8.setFont ( Font.font ( null, 35 ) );
		Text score9 = new Text ( );
		score9.setFill ( Color.YELLOW );
		score9.setTranslateY ( 400 );
		score9.setText ( highScores ( 8 ) );
		score9.setFont ( Font.font ( null, 35 ) );
		Text score10 = new Text ( );
		score10.setFill ( Color.YELLOW );
		score10.setTranslateY ( 450 );
		score10.setText ( highScores ( 9 ) );
		score10.setFont ( Font.font ( null, 35 ) );

		// Add background & buttons here-----------------------------------------------------------
		root.getChildren ( ).add ( new ImageView ( splash ) );
		root.getChildren ( ).add ( title );
		root.getChildren ( ).add ( playerName );
		root.getChildren ( ).add ( name );
		root.getChildren ( ).add ( start );
		root.getChildren ( ).add ( errorLabel );
		root.getChildren ( ).add ( highScores );
		root.getChildren ( ).add ( score1 );
		root.getChildren ( ).add ( score2 );
		root.getChildren ( ).add ( score3 );
		root.getChildren ( ).add ( score4 );
		root.getChildren ( ).add ( score5 );
		root.getChildren ( ).add ( score6 );
		root.getChildren ( ).add ( score7 );
		root.getChildren ( ).add ( score8 );
		root.getChildren ( ).add ( score9 );
		root.getChildren ( ).add ( score10 );

		primaryStage.setTitle ( "Colonize Mars" ); // Set the stage title
		primaryStage.setScene ( scene ); // Place the scene in the stage
		primaryStage.show ( ); // Display the stage

	}

	// work in progress for the highscores output

	public static String highScores( int loc ) throws FileNotFoundException
	{
		java.io.File inFile;
		inFile = new java.io.File ( "scores.txt" );
		Scanner input = new Scanner ( inFile );
		ArrayList<HighScores> leaderBoard = new ArrayList<HighScores> ( );

		while ( input.hasNext ( ) )
		{
			String name = input.nextLine ( );
			int score = input.nextInt ( );
			String junk = input.nextLine ( );
			leaderBoard.add ( new HighScores ( name, score ) );
		}
		Collections.sort ( leaderBoard, new SortByScore ( ) );
		String names = leaderBoard.get ( loc ).getPlayerName ( );
		String score = Integer.toString ( leaderBoard.get ( loc ).getScore ( ) );
		String topScore = ( names + "\t" + score );
		return topScore;
	}
}

class SortByScore implements Comparator<HighScores>
{
	// Used for sorting in ascending order of
	// roll number
	public int compare( HighScores a, HighScores b )
	{
		return b.getScore ( ) - a.getScore ( );
	}
}







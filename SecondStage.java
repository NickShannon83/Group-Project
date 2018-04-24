import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class SecondStage extends Stage
{
	String playerName;
	int turnNum = 1; // Turn number
	int score = 0; // The players score
	int rollCount = 3; // The number of die rolls per turn
	Player secondStagePlayer;
	//Node current = secondStagePlayer.getGameBoard ( ).goToTile ( 1 );

	// The SecondStage constructor
	public SecondStage(Player player)
	{
		super ( );
		this.playerName = player.getName ( );
		secondStagePlayer = player;
	}

	public void start( Stage secondaryStage )
	{
		StackPane root = new StackPane ( );
		Scene scene = new Scene ( root, 1920, 1080 );
		Image image = new Image ( "Player Screen White.jpg" );

		// Quit button
		Button quit = new Button ( "Evacuate Colony" );
		quit.setTranslateX ( -800 );
		quit.setTranslateY ( -450 );
		quit.setOnAction ( new EventHandler<ActionEvent> ( )
		{
			@Override
			public void handle( ActionEvent event )
			{
				QuitStage quitWindow = new QuitStage ( );
				quitWindow.start ( quitWindow );
			}// end action
		} );

		// Guide button
		Button guide = new Button ( "Martian Colony \nBuilders Guide" );
		guide.setTranslateX ( -800 );
		guide.setTranslateY ( -400 );
		guide.setOnAction ( new EventHandler<ActionEvent> ( )
		{
			@Override
			public void handle( ActionEvent event )
			{
				GuideStage guideWindow = new GuideStage ( );
				guideWindow.start ( guideWindow );
			}// end action
		} );

		// Player name with dropshadow
		DropShadow shadow = new DropShadow ( );
		shadow.setRadius ( 10.0 );
		shadow.setOffsetX ( 5.0 );
		shadow.setOffsetY ( 5.0 );
		Text player = new Text ( );
		player.setEffect ( shadow );
		player.setCache ( true );
		player.setTranslateY ( -425 );
		player.setFill ( Color.GREEN );
		player.setText ( playerName );
		player.setFont ( Font.font ( null, 100 ) );

		// Number of turns & score text
		Text turnScore = new Text ( );
		turnScore.setCache ( true );
		turnScore.setTranslateY ( -325 );
		turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
		turnScore.setFont ( Font.font ( null, 35 ) );

		// Text area for in game console-type output
		TextArea inGameConsole = new TextArea ( );
		inGameConsole.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		inGameConsole.setFont ( Font.font ( null, 20 ) );
		inGameConsole.setText ( "\t\t" + playerName + "'s Game History\n" );
		inGameConsole.setMaxWidth ( 425 );
		inGameConsole.setMaxHeight ( 1000 );
		inGameConsole.setTranslateX ( 750 );

		// Button Nodes
		Button roadNode1Btn = new Button ( );
		Button roadNode2Btn = new Button ( );
		Button roadNode3Btn = new Button ( );
		Button roadNode4Btn = new Button ( );
		Button roadNode5Btn = new Button ( );
		Button roadNode6Btn = new Button ( );
		Button sideRoadNode1Btn = new Button ( );
		Button sideRoadNode2Btn = new Button ( );
		Button sideRoadNode3Btn = new Button ( );
		Button sideRoadNode4Btn = new Button ( );
		Button sideRoadNode5Btn = new Button ( );
		Button sideRoadNode6Btn = new Button ( );

		// MAIN ROADS
		// -----------------------------------------------------
		// Road node 1
		Image road = new Image ( getClass ( ).getResourceAsStream ( "road.png" ) );
		Image roadC = new Image ( getClass ( ).getResourceAsStream ( "roadC.png" ) );
		roadNode1Btn.setGraphic ( new ImageView ( road ) );
		roadNode1Btn.setPadding ( Insets.EMPTY );
		roadNode1Btn.getTransforms ( ).add ( new Rotate ( 135, 0, 0 ) );
		roadNode1Btn.setTranslateX ( -31 );
		roadNode1Btn.setTranslateY ( -55 );
		roadNode1Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{

				roadNode1Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased main road 1\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );

				Node current = secondStagePlayer.getGameBoard().goToTile ( 1 );
				current.getMainRoad ( ).setUnlocked ( true );
				current.getMainRoad ( ).setOwned ( true );
				current.getSideRoad ( ).setUnlocked ( true );
				current.getBioDome ( ).setUnlocked ( true );
				current = current.getLink ( );  // THIS IS FUCKED, LINKED LIST DUN BROKED SUCKA, you talk like a fag!
				current.getMainRoad ( ).setUnlocked ( false );
				System.out.println ( current.getMainRoad ( ).getUnlocked ( ) );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Road node 2
		roadNode2Btn.setGraphic ( new ImageView ( road ) );
		roadNode2Btn.getTransforms ( ).add ( new Rotate ( 45, 0, 0 ) );
		roadNode2Btn.setPadding ( Insets.EMPTY );
		roadNode2Btn.setTranslateX ( -92 );
		roadNode2Btn.setTranslateY ( 14 );
		roadNode2Btn.setDisable ( true );
		//Node current = secondStagePlayer.getGameBoard().goToTile ( 2 );
		/*if ( current.getMainRoad ( ).getUnlocked ( ) == true )
		{*/
			//roadNode2Btn.setDisable ( false );
			roadNode2Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
			{
				
				@Override
				public void handle( MouseEvent e )
				{
					roadNode2Btn.setGraphic ( new ImageView ( roadC ) );
					inGameConsole.appendText ( "You purchased main road 2\n" );
					score++;
					turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
				}
			} );
	/*	}
		else
		{
			System.out.println ( "Road node 2 not unlocked" );
		}*/
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Road node 3
		roadNode3Btn.setGraphic ( new ImageView ( road ) );
		roadNode3Btn.setPadding ( Insets.EMPTY );
		roadNode3Btn.setTranslateY ( 85 );
		roadNode3Btn.setDisable ( true );
		roadNode3Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				roadNode3Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased main road 3\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Road node 4
		roadNode4Btn.setGraphic ( new ImageView ( road ) );
		roadNode4Btn.setPadding ( Insets.EMPTY );
		roadNode4Btn.getTransforms ( ).add ( new Rotate ( 135, 0, 0 ) );
		roadNode4Btn.setTranslateX ( 192 );
		roadNode4Btn.setTranslateY ( 32 );
		roadNode4Btn.setDisable ( true );
		roadNode4Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				roadNode4Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased main road 4\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Road node 5
		roadNode5Btn.setGraphic ( new ImageView ( road ) );
		roadNode5Btn.setPadding ( Insets.EMPTY );
		roadNode5Btn.getTransforms ( ).add ( new Rotate ( 45, 0, 0 ) );
		roadNode5Btn.setTranslateX ( 134 );
		roadNode5Btn.setTranslateY ( -67 );
		roadNode5Btn.setDisable ( true );
		roadNode5Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				roadNode5Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased main road 5\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Road node 6
		roadNode6Btn.setGraphic ( new ImageView ( road ) );
		roadNode6Btn.setPadding ( Insets.EMPTY );
		roadNode6Btn.setTranslateY ( -85 );
		roadNode6Btn.setDisable ( true );
		roadNode6Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				roadNode6Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased main road 6\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// SIDE ROADS
		// -----------------------------------------------------
		// Side Road node 1
		sideRoadNode1Btn.setGraphic ( new ImageView ( road ) );
		sideRoadNode1Btn.setPadding ( Insets.EMPTY );
		sideRoadNode1Btn.setTranslateX ( -220 );
		sideRoadNode1Btn.setDisable ( true );
		sideRoadNode1Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				sideRoadNode1Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased side road 1\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// Side Road node 2
		sideRoadNode2Btn.setGraphic ( new ImageView ( road ) );
		sideRoadNode2Btn.setPadding ( Insets.EMPTY );
		sideRoadNode2Btn.getTransforms ( ).add ( new Rotate ( 135, 0, 0 ) );
		sideRoadNode2Btn.setTranslateX ( -32 );
		sideRoadNode2Btn.setTranslateY ( 115 );
		sideRoadNode2Btn.setDisable ( true );
		sideRoadNode2Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				sideRoadNode2Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased side road 2\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Side Road node 3
		sideRoadNode3Btn.setGraphic ( new ImageView ( road ) );
		sideRoadNode3Btn.setPadding ( Insets.EMPTY );
		sideRoadNode3Btn.getTransforms ( ).add ( new Rotate ( 45, 0, 0 ) );
		sideRoadNode3Btn.setTranslateX ( 132 );
		sideRoadNode3Btn.setTranslateY ( 100 );
		sideRoadNode3Btn.setDisable ( true );
		sideRoadNode3Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				sideRoadNode3Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased side road 3\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Side Road node 4
		sideRoadNode4Btn.setGraphic ( new ImageView ( road ) );
		sideRoadNode4Btn.setPadding ( Insets.EMPTY );
		sideRoadNode4Btn.setTranslateX ( 216 );
		sideRoadNode4Btn.setDisable ( true );
		sideRoadNode4Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				sideRoadNode4Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased side road 4\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Side Road node 5
		sideRoadNode5Btn.setGraphic ( new ImageView ( road ) );
		sideRoadNode5Btn.setPadding ( Insets.EMPTY );
		sideRoadNode5Btn.getTransforms ( ).add ( new Rotate ( 135, 0, 0 ) );
		sideRoadNode5Btn.setTranslateX ( 192 );
		sideRoadNode5Btn.setTranslateY ( -140 );
		sideRoadNode5Btn.setDisable ( true );
		sideRoadNode5Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				sideRoadNode5Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased side road 5\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Side Road node 6
		sideRoadNode6Btn.setGraphic ( new ImageView ( road ) );
		sideRoadNode6Btn.setPadding ( Insets.EMPTY );
		sideRoadNode6Btn.getTransforms ( ).add ( new Rotate ( 45, 0, 0 ) );
		sideRoadNode6Btn.setTranslateX ( -90 );
		sideRoadNode6Btn.setTranslateY ( -150 );
		sideRoadNode6Btn.setDisable ( true );
		sideRoadNode6Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				sideRoadNode6Btn.setGraphic ( new ImageView ( roadC ) );
				inGameConsole.appendText ( "You purchased side road 6\n" );
				score++;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// ASTRONAUTS
		// -----------------------------------------------------
		// Astronaut node 1
		Image astro1 = new Image ( getClass ( ).getResourceAsStream ( "astro1.png" ) );
		Image astro1C = new Image ( getClass ( ).getResourceAsStream ( "astro1C.png" ) );
		Button astroNode1Btn = new Button ( );
		astroNode1Btn.setGraphic ( new ImageView ( astro1 ) );
		astroNode1Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		astroNode1Btn.setTranslateX ( -218 );
		astroNode1Btn.setTranslateY ( -130 );
		astroNode1Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				astroNode1Btn.setGraphic ( new ImageView ( astro1C ) );
				inGameConsole.appendText ( "You purchased astronaut 1\n" );
				score += 1;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Astronaut node 2
		Image astro2 = new Image ( getClass ( ).getResourceAsStream ( "astro2.png" ) );
		Image astro2C = new Image ( getClass ( ).getResourceAsStream ( "astro2C.png" ) );
		Button astroNode2Btn = new Button ( );
		astroNode2Btn.setGraphic ( new ImageView ( astro2 ) );
		astroNode2Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		astroNode2Btn.setTranslateX ( -218 );
		astroNode2Btn.setTranslateY ( 35 );
		astroNode2Btn.setDisable ( true );
		astroNode2Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				astroNode2Btn.setGraphic ( new ImageView ( astro2C ) );
				inGameConsole.appendText ( "You purchased astronaut 2\n" );
				score += 2;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Astronaut node 3
		Image astro3 = new Image ( getClass ( ).getResourceAsStream ( "astro3.png" ) );
		Image astro3C = new Image ( getClass ( ).getResourceAsStream ( "astro3C.png" ) );
		Button astroNode3Btn = new Button ( );
		astroNode3Btn.setGraphic ( new ImageView ( astro3 ) );
		astroNode3Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		astroNode3Btn.setTranslateY ( 118 );
		astroNode3Btn.setDisable ( true );
		astroNode3Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				astroNode3Btn.setGraphic ( new ImageView ( astro3C ) );
				inGameConsole.appendText ( "You purchased astronaut 3\n" );
				score += 3;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Astronaut node 4
		Image astro4 = new Image ( getClass ( ).getResourceAsStream ( "astro4.png" ) );
		Image astro4C = new Image ( getClass ( ).getResourceAsStream ( "astro4C.png" ) );
		Button astroNode4Btn = new Button ( );
		astroNode4Btn.setGraphic ( new ImageView ( astro4 ) );
		astroNode4Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		astroNode4Btn.setTranslateX ( 222 );
		astroNode4Btn.setTranslateY ( 37 );
		astroNode4Btn.setDisable ( true );
		astroNode4Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				astroNode4Btn.setGraphic ( new ImageView ( astro4C ) );
				inGameConsole.appendText ( "You purchased astronaut 4\n" );
				score += 4;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Astronaut node 5
		Image astro5 = new Image ( getClass ( ).getResourceAsStream ( "astro5.png" ) );
		Image astro5C = new Image ( getClass ( ).getResourceAsStream ( "astro5C.png" ) );
		Button astroNode5Btn = new Button ( );
		astroNode5Btn.setGraphic ( new ImageView ( astro5 ) );
		astroNode5Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		astroNode5Btn.setTranslateX ( 222 );
		astroNode5Btn.setTranslateY ( -130 );
		astroNode5Btn.setDisable ( true );
		astroNode5Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				astroNode5Btn.setGraphic ( new ImageView ( astro5C ) );
				inGameConsole.appendText ( "You purchased astronaut 5\n" );
				score += 5;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Astronaut node 6
		Image astro6 = new Image ( getClass ( ).getResourceAsStream ( "astro6.png" ) );
		Image astro6C = new Image ( getClass ( ).getResourceAsStream ( "astro6C.png" ) );
		Button astroNode6Btn = new Button ( );
		astroNode6Btn.setGraphic ( new ImageView ( astro6 ) );
		astroNode6Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		astroNode6Btn.setTranslateY ( -213 );
		astroNode6Btn.setDisable ( true );
		astroNode6Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				astroNode6Btn.setGraphic ( new ImageView ( astro6C ) );
				inGameConsole.appendText ( "You purchased Mark Watney!\n" );
				score += 9;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// DOMES
		// -----------------------------------------------------
		// Dome node 1
		Image dome1 = new Image ( getClass ( ).getResourceAsStream ( "dome1.png" ) );
		Image dome1C = new Image ( getClass ( ).getResourceAsStream ( "dome1C.png" ) );
		Button domeNode1Btn = new Button ( );
		domeNode1Btn.setGraphic ( new ImageView ( dome1 ) );
		domeNode1Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		domeNode1Btn.setTranslateX ( -150 );
		domeNode1Btn.setTranslateY ( -5 );
		domeNode1Btn.setDisable ( true );
		domeNode1Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				domeNode1Btn.setGraphic ( new ImageView ( dome1C ) );
				inGameConsole.appendText ( "You purchased dome 1\n" );
				score += 2;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Dome node 2
		Image dome2 = new Image ( getClass ( ).getResourceAsStream ( "dome2.png" ) );
		Image dome2C = new Image ( getClass ( ).getResourceAsStream ( "dome2C.png" ) );
		Button domeNode2Btn = new Button ( );
		domeNode2Btn.setGraphic ( new ImageView ( dome2 ) );
		domeNode2Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		domeNode2Btn.setTranslateX ( -65 );
		domeNode2Btn.setTranslateY ( 80 );
		domeNode2Btn.setDisable ( true );
		domeNode2Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				domeNode2Btn.setGraphic ( new ImageView ( dome2C ) );
				inGameConsole.appendText ( "You purchased dome 2\n" );
				score += 4;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Dome node 3
		Image dome3 = new Image ( getClass ( ).getResourceAsStream ( "dome3.png" ) );
		Image dome3C = new Image ( getClass ( ).getResourceAsStream ( "dome3C.png" ) );
		Button domeNode3Btn = new Button ( );
		domeNode3Btn.setGraphic ( new ImageView ( dome3 ) );
		domeNode3Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		domeNode3Btn.setTranslateX ( 66 );
		domeNode3Btn.setTranslateY ( 80 );
		domeNode3Btn.setDisable ( true );
		domeNode3Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				domeNode3Btn.setGraphic ( new ImageView ( dome3C ) );
				inGameConsole.appendText ( "You purchased dome 3\n" );
				score += 6;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Dome node 4
		Image dome4 = new Image ( getClass ( ).getResourceAsStream ( "dome4.png" ) );
		Image dome4C = new Image ( getClass ( ).getResourceAsStream ( "dome4C.png" ) );
		Button domeNode4Btn = new Button ( );
		domeNode4Btn.setGraphic ( new ImageView ( dome4 ) );
		domeNode4Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		domeNode4Btn.setTranslateX ( 148 );
		domeNode4Btn.setTranslateY ( -3 );
		domeNode4Btn.setDisable ( true );
		domeNode4Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				domeNode4Btn.setGraphic ( new ImageView ( dome4C ) );
				inGameConsole.appendText ( "You purchased dome 4\n" );
				score += 8;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Dome node 5
		Image dome5 = new Image ( getClass ( ).getResourceAsStream ( "dome5.png" ) );
		Image dome5C = new Image ( getClass ( ).getResourceAsStream ( "dome5C.png" ) );
		Button domeNode5Btn = new Button ( );
		domeNode5Btn.setGraphic ( new ImageView ( dome5 ) );
		domeNode5Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		domeNode5Btn.setTranslateX ( 68 );
		domeNode5Btn.setTranslateY ( -88 );
		domeNode5Btn.setDisable ( true );
		domeNode5Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				domeNode5Btn.setGraphic ( new ImageView ( dome5C ) );
				inGameConsole.appendText ( "You purchased dome 5\n" );
				score += 10;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Dome node 6
		Image dome6 = new Image ( getClass ( ).getResourceAsStream ( "dome6.png" ) );
		Image dome6C = new Image ( getClass ( ).getResourceAsStream ( "dome6C.png" ) );
		Button domeNode6Btn = new Button ( );
		domeNode6Btn.setGraphic ( new ImageView ( dome6 ) );
		domeNode6Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		domeNode6Btn.setTranslateX ( -65 );
		domeNode6Btn.setTranslateY ( -88 );
		domeNode6Btn.setDisable ( true );
		domeNode6Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				domeNode6Btn.setGraphic ( new ImageView ( dome6C ) );
				inGameConsole.appendText ( "You purchased dome 6\n" );
				score += 12;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// RESEARCH STATIONS
		// -----------------------------------------------------
		// Research node 1
		Image research1 = new Image ( getClass ( ).getResourceAsStream ( "research1.png" ) );
		Image research1C = new Image ( getClass ( ).getResourceAsStream ( "research1C.png" ) );
		Button researchNode1Btn = new Button ( );
		researchNode1Btn.setGraphic ( new ImageView ( research1 ) );
		researchNode1Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		researchNode1Btn.setTranslateX ( -285 );
		researchNode1Btn.setTranslateY ( -10 );
		researchNode1Btn.setDisable ( true );
		researchNode1Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				researchNode1Btn.setGraphic ( new ImageView ( research1C ) );
				inGameConsole.appendText ( "You purchased research station 1\n" );
				score += 4;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Research node 2
		Image research2 = new Image ( getClass ( ).getResourceAsStream ( "research2.png" ) );
		Image research2C = new Image ( getClass ( ).getResourceAsStream ( "research2C.png" ) );
		Button researchNode2Btn = new Button ( );
		researchNode2Btn.setGraphic ( new ImageView ( research2 ) );
		researchNode2Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		researchNode2Btn.setTranslateX ( -155 );
		researchNode2Btn.setTranslateY ( 160 );
		researchNode2Btn.setDisable ( true );
		researchNode2Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				researchNode2Btn.setGraphic ( new ImageView ( research2C ) );
				inGameConsole.appendText ( "You purchased research station 2\n" );
				score += 8;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Research node 3
		Image research3 = new Image ( getClass ( ).getResourceAsStream ( "research3.png" ) );
		Image research3C = new Image ( getClass ( ).getResourceAsStream ( "research3C.png" ) );
		Button researchNode3Btn = new Button ( );
		researchNode3Btn.setGraphic ( new ImageView ( research3 ) );
		researchNode3Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		researchNode3Btn.setTranslateX ( 155 );
		researchNode3Btn.setTranslateY ( 160 );
		researchNode3Btn.setDisable ( true );
		researchNode3Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				researchNode3Btn.setGraphic ( new ImageView ( research3C ) );
				inGameConsole.appendText ( "You purchased research station 3\n" );
				score += 12;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Research node 4
		Image research4 = new Image ( getClass ( ).getResourceAsStream ( "research4.png" ) );
		Image research4C = new Image ( getClass ( ).getResourceAsStream ( "research4C.png" ) );
		Button researchNode4Btn = new Button ( );
		researchNode4Btn.setGraphic ( new ImageView ( research4 ) );
		researchNode4Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		researchNode4Btn.setTranslateX ( 283 );
		researchNode4Btn.setTranslateY ( -10 );
		researchNode4Btn.setDisable ( true );
		researchNode4Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				researchNode4Btn.setGraphic ( new ImageView ( research4C ) );
				inGameConsole.appendText ( "You purchased research station 4\n" );
				score += 16;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Research node 5
		Image research5 = new Image ( getClass ( ).getResourceAsStream ( "research5.png" ) );
		Image research5C = new Image ( getClass ( ).getResourceAsStream ( "research5C.png" ) );
		Button researchNode5Btn = new Button ( );
		researchNode5Btn.setGraphic ( new ImageView ( research5 ) );
		researchNode5Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		researchNode5Btn.setTranslateX ( 155 );
		researchNode5Btn.setTranslateY ( -178 );
		researchNode5Btn.setDisable ( true );
		researchNode5Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				researchNode5Btn.setGraphic ( new ImageView ( research5C ) );
				inGameConsole.appendText ( "You purchased research station 5\n" );
				score += 20;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Research node 6
		Image research6 = new Image ( getClass ( ).getResourceAsStream ( "research6.png" ) );
		Image research6C = new Image ( getClass ( ).getResourceAsStream ( "research6C.png" ) );
		Button researchNode6Btn = new Button ( );
		researchNode6Btn.setGraphic ( new ImageView ( research6 ) );
		researchNode6Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		researchNode6Btn.setTranslateX ( -155 );
		researchNode6Btn.setTranslateY ( -178 );
		researchNode6Btn.setDisable ( true );
		researchNode6Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				researchNode6Btn.setGraphic ( new ImageView ( research6C ) );
				inGameConsole.appendText ( "You purchased research station 6\n" );
				score += 24;
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + score );
			}
		} );
		// -----------------------------------------------------

		// *************************************************************
		// DICE
		Die die1 = new Die ( );
		Die die2 = new Die ( );
		Die die3 = new Die ( );
		Die die4 = new Die ( );
		Die die5 = new Die ( );
		Die die6 = new Die ( );

		Button die1Button = new Button ( );
		die1Button.setTranslateX ( -850 );
		die1Button.setTranslateY ( 360 );
		die1Button.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		Button die2Button = new Button ( );
		die2Button.setTranslateX ( -700 );
		die2Button.setTranslateY ( 360 );
		die2Button.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		Button die3Button = new Button ( );
		die3Button.setTranslateX ( -550 );
		die3Button.setTranslateY ( 360 );
		die3Button.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		Button die4Button = new Button ( );
		die4Button.setTranslateX ( -400 );
		die4Button.setTranslateY ( 360 );
		die4Button.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		Button die5Button = new Button ( );
		die5Button.setTranslateX ( -250 );
		die5Button.setTranslateY ( 360 );
		die5Button.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		Button die6Button = new Button ( );
		die6Button.setTranslateX ( -100 );
		die6Button.setTranslateY ( 360 );
		die6Button.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );

		CheckBox d1Check = new CheckBox ( "Hold" );
		d1Check.setTranslateX ( -850 );
		d1Check.setTranslateY ( 440 );
		d1Check.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		d1Check.setFont ( Font.font ( null, FontWeight.BOLD, 20 ) );
		CheckBox d2Check = new CheckBox ( "Hold" );
		d2Check.setTranslateX ( -700 );
		d2Check.setTranslateY ( 440 );
		d2Check.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		d2Check.setFont ( Font.font ( null, FontWeight.BOLD, 20 ) );
		CheckBox d3Check = new CheckBox ( "Hold" );
		d3Check.setTranslateX ( -550 );
		d3Check.setTranslateY ( 440 );
		d3Check.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		d3Check.setFont ( Font.font ( null, FontWeight.BOLD, 20 ) );
		CheckBox d4Check = new CheckBox ( "Hold" );
		d4Check.setTranslateX ( -400 );
		d4Check.setTranslateY ( 440 );
		d4Check.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		d4Check.setFont ( Font.font ( null, FontWeight.BOLD, 20 ) );
		CheckBox d5Check = new CheckBox ( "Hold" );
		d5Check.setTranslateX ( -250 );
		d5Check.setTranslateY ( 440 );
		d5Check.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		d5Check.setFont ( Font.font ( null, FontWeight.BOLD, 20 ) );
		CheckBox d6Check = new CheckBox ( "Hold" );
		d6Check.setTranslateX ( -100 );
		d6Check.setTranslateY ( 440 );
		d6Check.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		d6Check.setFont ( Font.font ( null, FontWeight.BOLD, 20 ) );

		Text count = new Text ( "Rolls left " + rollCount );
		count.setFont ( Font.font ( null, FontWeight.BOLD, 50 ) );
		count.setTranslateX ( 225 );
		count.setTranslateY ( 350 );

		Button commit = new Button ( );
		commit.setText ( " Spend " );
		commit.setTranslateX ( 280 );
		commit.setTranslateY ( 420 );

		String[ ] finalDice = new String[6];

		// runs through to check for saved dice
		checkClicked ( d1Check, die1Button );
		checkClicked ( d2Check, die2Button );
		checkClicked ( d3Check, die3Button );
		checkClicked ( d4Check, die4Button );
		checkClicked ( d5Check, die5Button );
		checkClicked ( d6Check, die6Button );

		Button rollButton = new Button ( );
		rollButton.setText ( " Roll Dice " );
		rollButton.setTranslateX ( 160 );
		rollButton.setTranslateY ( 420 );
		commit.setDisable ( true );
		rollButton.setOnAction ( new EventHandler<ActionEvent> ( )
		{

			@Override
			public void handle( ActionEvent event )
			{
				commit.setDisable ( false );
				// count.setText ( "Rolls left " + ( --rollCount ) );
				if ( rollCount == 0 )
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
					getImage ( die1, die1Button );
					die1Button.setText ( die1.getDieString ( ) );
				}
				if ( die2Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{

					die2.setDieString ( getDieRolls ( ) );
					die2Button.setContentDisplay ( ContentDisplay.TOP );
					getImage ( die2, die2Button );
					die2Button.setText ( die2.getDieString ( ) );
				}
				if ( die3Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{
					die3.setDieString ( getDieRolls ( ) );
					die3Button.setContentDisplay ( ContentDisplay.TOP );
					getImage ( die3, die3Button );
					die3Button.setText ( die3.getDieString ( ) );
				}
				if ( die4Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{
					die4.setDieString ( getDieRolls ( ) );
					die4Button.setContentDisplay ( ContentDisplay.TOP );
					getImage ( die4, die4Button );
					die4Button.setText ( die4.getDieString ( ) );
				}
				if ( die5Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{
					die5.setDieString ( getDieRolls ( ) );
					die5Button.setContentDisplay ( ContentDisplay.TOP );
					getImage ( die5, die5Button );
					die5Button.setText ( die5.getDieString ( ) );
				}
				if ( die6Button.isDisable ( ) )
				{

					// do nothing
				}
				else
				{
					die6.setDieString ( getDieRolls ( ) );
					die6Button.setContentDisplay ( ContentDisplay.TOP );
					getImage ( die6, die6Button );
					die6Button.setText ( die6.getDieString ( ) );
				}
			}

		} );

		Turn turn = new Turn ( );
		commit.setOnAction ( new EventHandler<ActionEvent> ( )
		{

			@Override
			public void handle( ActionEvent event )
			{
				commit.setDisable ( true );
				rollButton.setDisable ( true );
				d1Check.setSelected ( true );
				d2Check.setSelected ( true );
				d3Check.setSelected ( true );
				d4Check.setSelected ( true );
				d5Check.setSelected ( true );
				d6Check.setSelected ( true );
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

		// purchase (player1);

		// *************************************************************

		// Add background & buttons here-----------------------------------------------------------
		root.getChildren ( ).add ( new ImageView ( image ) );
		root.getChildren ( ).add ( quit );
		root.getChildren ( ).add ( guide );
		root.getChildren ( ).add ( player );
		root.getChildren ( ).add ( turnScore );
		root.getChildren ( ).add ( inGameConsole );
		root.getChildren ( ).add ( roadNode1Btn );
		root.getChildren ( ).add ( roadNode2Btn );
		root.getChildren ( ).add ( roadNode3Btn );
		root.getChildren ( ).add ( roadNode4Btn );
		root.getChildren ( ).add ( roadNode5Btn );
		root.getChildren ( ).add ( roadNode6Btn );
		root.getChildren ( ).add ( sideRoadNode1Btn );
		root.getChildren ( ).add ( sideRoadNode2Btn );
		root.getChildren ( ).add ( sideRoadNode3Btn );
		root.getChildren ( ).add ( sideRoadNode4Btn );
		root.getChildren ( ).add ( sideRoadNode5Btn );
		root.getChildren ( ).add ( sideRoadNode6Btn );
		root.getChildren ( ).add ( astroNode1Btn );
		root.getChildren ( ).add ( astroNode2Btn );
		root.getChildren ( ).add ( astroNode3Btn );
		root.getChildren ( ).add ( astroNode4Btn );
		root.getChildren ( ).add ( astroNode5Btn );
		root.getChildren ( ).add ( astroNode6Btn );
		root.getChildren ( ).add ( domeNode1Btn );
		root.getChildren ( ).add ( domeNode2Btn );
		root.getChildren ( ).add ( domeNode3Btn );
		root.getChildren ( ).add ( domeNode4Btn );
		root.getChildren ( ).add ( domeNode5Btn );
		root.getChildren ( ).add ( domeNode6Btn );
		root.getChildren ( ).add ( researchNode1Btn );
		root.getChildren ( ).add ( researchNode2Btn );
		root.getChildren ( ).add ( researchNode3Btn );
		root.getChildren ( ).add ( researchNode4Btn );
		root.getChildren ( ).add ( researchNode5Btn );
		root.getChildren ( ).add ( researchNode6Btn );
		root.getChildren ( ).add ( rollButton );
		root.getChildren ( ).add ( die1Button );
		root.getChildren ( ).add ( die2Button );
		root.getChildren ( ).add ( die3Button );
		root.getChildren ( ).add ( die4Button );
		root.getChildren ( ).add ( die5Button );
		root.getChildren ( ).add ( die6Button );
		root.getChildren ( ).add ( d1Check );
		root.getChildren ( ).add ( d2Check );
		root.getChildren ( ).add ( d3Check );
		root.getChildren ( ).add ( d4Check );
		root.getChildren ( ).add ( d5Check );
		root.getChildren ( ).add ( d6Check );
		root.getChildren ( ).add ( commit );
		root.getChildren ( ).add ( count );
		root.setVisible ( true );

		secondaryStage.setTitle ( playerName + "'s Martian Colony" ); // Set the stage title
		secondaryStage.setScene ( scene ); // Place the scene in the stage
		secondaryStage.show ( ); // Display the stage

		// Change color of the inGameConsole textarea after stage is shown
		// Region region = ( Region ) inGameConsole.lookup( ".content" );
		// region.setStyle( "-fx-background-color: black; -fx-text-fill: #00ff00;" ); //#00ff00

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
	public static Image getImage( Die die, Button button )
	{
		Image water = new Image ( "water die.png" );
		Image ore = new Image ( "ore die.png" );
		Image oxygen = new Image ( "oxygen die.png" );
		Image silicon = new Image ( "silicon die.png" );
		Image solar = new Image ( "solar die.png" );

		Image imageName = water;
		switch ( die.getDieString ( ) )
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
	 *           the corresponding button
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

	static Scanner input = new Scanner ( System.in );
	/*********************************************************************
	 * Method to purchase a resource
	 * 
	 * @author Luke Johnson
	 * 
	 * @param player:
	 *           player who is purchasing a resource
	 * @param turn:
	 *           player's assets in a turn
	 */
	/*
	 * public static void purchase(Player player) { int tileChoice, resourceChoice; Node head =
	 * player.getGameBoard().getHead(); Node current = head;
	 * 
	 * // Execute if player can purchase at least one resource if (player.canPurchase()) { // execute while player can
	 * purchase while (player.canPurchase()) { do {
	 * System.out.println("What tile would you like to purchase a resource from?");
	 * System.out.println("Enter a number 1 - 6."); tileChoice = input.nextInt();
	 * System.out.println("Which resource would you like to from the tile?");
	 * System.out.println("1) Main Road\n2) Side Road\n3) Astronaut\n4) Bio-dome\n" + "5) Research Facility");
	 * resourceChoice = input.nextInt(); if ((tileChoice < 1 || tileChoice > 6) || (resourceChoice < 1 || resourceChoice
	 * > 5)) { System.out.println("Invalid selection."); } } while (((tileChoice < 1) || (tileChoice > 6)) ||
	 * (resourceChoice < 1 || resourceChoice > 5));
	 * 
	 * for (int i = 1; i < tileChoice; i++) { current = current.getLink(); } switch (resourceChoice) { // purchase a main
	 * road // unlock side road, next main road and bio-dome case 1: if (current.getMainRoad().isUnlocked() &&
	 * !current.getMainRoad().isOwned() && player.getTurn().getSil() > 0 && player.getTurn().getOx() > 0) {
	 * current.getMainRoad().setOwned(true); player.setScore(player.getScore() + current.getMainRoad().getValue());
	 * player.getTurn().setSil(player.getTurn().getSil() - 1); player.getTurn().setOx(player.getTurn().getOx() - 1);
	 * current.getSideRoad().setUnlocked(true); current.getBioDome().setUnlocked(true);
	 * 
	 * if (current.getLink() != null) { current.getLink().getMainRoad().setUnlocked(true); } } // output error statements
	 * else if (!current.getMainRoad().isUnlocked()) { System.out.println("That is not unlocked."); } else if
	 * (current.getMainRoad().isOwned()) { System.out.println("You already own that."); } else {
	 * System.out.println("You can't afford that."); } break;
	 * 
	 * // purchase a side road // unlock the research facility case 2: if (current.getSideRoad().isUnlocked() &&
	 * !current.getSideRoad().isOwned() && player.getTurn().getSil() > 0 && player.getTurn().getOx() > 0) {
	 * current.getSideRoad().setOwned(true); player.setScore(player.getScore() + current.getSideRoad().getValue());
	 * player.getTurn().setSil(player.getTurn().getSil() - 1); player.getTurn().setOx(player.getTurn().getOx() - 1);
	 * current.getResearch().setUnlocked(true); } // output error statements else if
	 * (!current.getSideRoad().isUnlocked()) { System.out.println("That is not unlocked."); } else if
	 * (current.getSideRoad().isOwned()) { System.out.println("You already own that."); } else {
	 * System.out.println("You can't afford that."); } break;
	 * 
	 * // purchase an astronaut // unlock the next astronaut case 3: if (current.getAstronaut().isUnlocked() &&
	 * !current.getAstronaut().isOwned() && player.getTurn().getWat() > 0 && player.getTurn().getSol() > 0 &&
	 * player.getTurn().getOre() > 0) { current.getAstronaut().setOwned(true); player.setScore(player.getScore() +
	 * current.getAstronaut().getValue()); player.getTurn().setWat(player.getTurn().getWat() - 1);
	 * player.getTurn().setOre(player.getTurn().getOre() - 1); player.getTurn().setSol(player.getTurn().getSol() - 1); if
	 * (current.getLink() != null) { current.getLink().getAstronaut().setUnlocked(true); } } // output error statements
	 * else if (!current.getAstronaut().isUnlocked()) { System.out.println("That is not unlocked."); } else if
	 * (current.getAstronaut().isOwned()) { System.out.println("You already own that."); } else {
	 * System.out.println("You can't afford that."); } break;
	 * 
	 * // purchase a bio-dome case 4: if (current.getBioDome().isUnlocked() && !current.getBioDome().isOwned() &&
	 * player.getTurn().getSil() > 0 && player.getTurn().getOx() > 0 && player.getTurn().getWat() > 0 &&
	 * player.getTurn().getSol() > 0) { current.getBioDome().setOwned(true); player.setScore(player.getScore() +
	 * current.getBioDome().getValue()); player.getTurn().setWat(player.getTurn().getWat() - 1);
	 * player.getTurn().setOx(player.getTurn().getOx() - 1); player.getTurn().setSol(player.getTurn().getSol() - 1);
	 * player.getTurn().setSil(player.getTurn().getSil() - 1); } // output error statements else if
	 * (!current.getBioDome().isUnlocked()) { System.out.println("That is not unlocked."); } else if
	 * (current.getBioDome().isOwned()) { System.out.println("You already own that."); } else {
	 * System.out.println("You can't afford that."); } break;
	 * 
	 * // purchase a research facility case 5: if (current.getResearch().isUnlocked() && !current.getResearch().isOwned()
	 * && player.getTurn().getOre() == 3 && player.getTurn().getSol() == 2) { current.getResearch().setOwned(true);
	 * player.setScore(player.getScore() + current.getResearch().getValue());
	 * player.getTurn().setWat(player.getTurn().getWat() - 1); player.getTurn().setOx(player.getTurn().getOx() - 1);
	 * player.getTurn().setSol(player.getTurn().getSol() - 1); player.getTurn().setSil(player.getTurn().getSil() - 1); }
	 * 
	 * // output error statements else if (!current.getResearch().isUnlocked()) {
	 * System.out.println("That is not unlocked."); } else if (current.getResearch().isOwned()) {
	 * System.out.println("You already own that."); } else { System.out.println("You can't afford that."); } break; } //
	 * set current back to the head of the list current = head; } System.out.println("No further purchases available.");
	 * } else { System.out.println("No purchases available.\nBetter luck next turn."); } //reset all of player's dice to
	 * 0 player.getTurn().setOre(0); player.getTurn().setOx(0); player.getTurn().setSil(0); player.getTurn().setSol(0);
	 * player.getTurn().setWat(0); }
	 */
}

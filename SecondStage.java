/*****************************************************
 * This class is the biggest and basically runs the whole game.
 * @author Jared Crouse
 * 
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
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
	String playerName; // Players name
	int turnNum = 1; // Turn number
	int score = 0; // The players score
	int rollCount = 3; // The number of die rolls per turn
	Player secondStagePlayer; // The second stage player
	boolean gameOver = false; // gameOver boolean

	// The SecondStage constructor
	public SecondStage(String name)
	{
		super ( );
		this.playerName = name;
		secondStagePlayer = new Player ( name );
	}

	// Start the secondaryStage
	public void start( Stage secondaryStage )
	{
		initializePlayer ( secondStagePlayer );
		StackPane root = new StackPane ( );
		Scene scene = new Scene ( root, 1920, 1080 ); // Window size
		Image image = new Image ( "Player Screen White.jpg" ); // Background image

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
		turnScore.setText ( "Turn: " + secondStagePlayer.getTurnNum ( ) + "\tScore: " + secondStagePlayer.getScore ( ) );
		turnScore.setFont ( Font.font ( null, 35 ) );

		// Text area for in game console-type output
		TextArea inGameConsole = new TextArea ( );
		inGameConsole.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		inGameConsole.setFont ( Font.font ( null, 20 ) );
		inGameConsole.setText ( "\t\t" + playerName + "'s Game History\n" );
		inGameConsole.setMaxWidth ( 425 );
		inGameConsole.setMaxHeight ( 1000 );
		inGameConsole.setTranslateX ( 750 );
		inGameConsole.appendText ( "Starting turn number 1\n" );

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
		Button domeNode1Btn = new Button ( );
		Button domeNode2Btn = new Button ( );
		Button domeNode3Btn = new Button ( );
		Button domeNode4Btn = new Button ( );
		Button domeNode5Btn = new Button ( );
		Button domeNode6Btn = new Button ( );
		Button researchNode1Btn = new Button ( );
		Button researchNode2Btn = new Button ( );
		Button researchNode3Btn = new Button ( );
		Button researchNode4Btn = new Button ( );
		Button researchNode5Btn = new Button ( );
		Button researchNode6Btn = new Button ( );
		Button astroNode1Btn = new Button ( );
		Button astroNode2Btn = new Button ( );
		Button astroNode3Btn = new Button ( );
		Button astroNode4Btn = new Button ( );
		Button astroNode5Btn = new Button ( );
		Button astroNode6Btn = new Button ( );
		Button endTurn = new Button ( );
		endTurn.setText ( " End Turn " );
		endTurn.setTranslateX ( 320 );
		endTurn.setTranslateY ( 420 );

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

				boolean purchased = purchaseMainRoad ( 1, roadNode1Btn, inGameConsole, roadC, secondStagePlayer );
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + secondStagePlayer.getScore ( ) );

				if ( purchased )
				{
					roadNode2Btn.setDisable ( false );
					sideRoadNode1Btn.setDisable ( false );
					domeNode1Btn.setDisable ( false );
				}
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
		roadNode2Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{

			@Override
			public void handle( MouseEvent e )
			{

				boolean purchased = purchaseMainRoad ( 2, roadNode2Btn, inGameConsole, roadC, secondStagePlayer );

				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + secondStagePlayer.getScore ( ) );

				if ( purchased )
				{
					domeNode2Btn.setDisable (
							secondStagePlayer.getGameBoard ( ).goToTile ( 1 ).getBioDome ( ).isOwned ( ) ? false : true );
					roadNode3Btn.setDisable ( false );
					sideRoadNode2Btn.setDisable ( false );

				}
			}
		} );

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
				boolean purchased = purchaseMainRoad ( 3, roadNode3Btn, inGameConsole, roadC, secondStagePlayer );
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + secondStagePlayer.getScore ( ) );

				if ( purchased )
				{
					roadNode4Btn.setDisable ( false );
					sideRoadNode3Btn.setDisable ( false );
					domeNode3Btn.setDisable (
							secondStagePlayer.getGameBoard ( ).goToTile ( 2 ).getBioDome ( ).isOwned ( ) ? false : true );
				}
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
				boolean purchased = purchaseMainRoad ( 4, roadNode4Btn, inGameConsole, roadC, secondStagePlayer );
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + secondStagePlayer.getScore ( ) );

				if ( purchased )
				{
					roadNode5Btn.setDisable ( false );
					sideRoadNode4Btn.setDisable ( false );
					domeNode4Btn.setDisable (
							secondStagePlayer.getGameBoard ( ).goToTile ( 3 ).getBioDome ( ).isOwned ( ) ? false : true );
				}
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
				boolean purchased = purchaseMainRoad ( 5, roadNode5Btn, inGameConsole, roadC, secondStagePlayer );
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + secondStagePlayer.getScore ( ) );

				if ( purchased )
				{
					roadNode6Btn.setDisable ( false );
					sideRoadNode5Btn.setDisable ( false );
					domeNode5Btn.setDisable (
							secondStagePlayer.getGameBoard ( ).goToTile ( 4 ).getBioDome ( ).isOwned ( ) ? false : true );
				}
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
				boolean purchased = purchaseMainRoad ( 6, roadNode6Btn, inGameConsole, roadC, secondStagePlayer );
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + secondStagePlayer.getScore ( ) );

				if ( purchased )
				{
					sideRoadNode6Btn.setDisable ( false );
					domeNode6Btn.setDisable (
							secondStagePlayer.getGameBoard ( ).goToTile ( 5 ).getBioDome ( ).isOwned ( ) ? false : true );
				}
			}
		} );
		// -------------------------------------------------------------------------------------------------------------------------

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
				boolean purchased = purchaseSideRoad ( 1, sideRoadNode1Btn, inGameConsole, roadC, secondStagePlayer );

				if ( purchased )
				{
					researchNode1Btn.setDisable ( false );
				}
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
				boolean purchased = purchaseSideRoad ( 2, sideRoadNode2Btn, inGameConsole, roadC, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 1 ).getResearch ( ).isOwned ( ) )
				{
					researchNode2Btn.setDisable ( false );
				}
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
				boolean purchased = purchaseSideRoad ( 3, sideRoadNode3Btn, inGameConsole, roadC, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 2 ).getResearch ( ).isOwned ( ) )
				{
					researchNode3Btn.setDisable ( false );
				}
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
				boolean purchased = purchaseSideRoad ( 4, sideRoadNode4Btn, inGameConsole, roadC, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 3 ).getResearch ( ).isOwned ( ) )
				{
					researchNode4Btn.setDisable ( false );
				}
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
				boolean purchased = purchaseSideRoad ( 5, sideRoadNode5Btn, inGameConsole, roadC, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 4 ).getResearch ( ).isOwned ( ) )
				{
					researchNode5Btn.setDisable ( false );
				}
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
				boolean purchased = purchaseSideRoad ( 6, sideRoadNode6Btn, inGameConsole, roadC, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 5 ).getResearch ( ).isOwned ( ) )
				{
					researchNode6Btn.setDisable ( false );
				}
			}
		} );
		// -------------------------------------------------------------------------------------------------------------------------

		// ASTRONAUTS
		// -----------------------------------------------------
		// Astronaut node 1
		Image astro1 = new Image ( getClass ( ).getResourceAsStream ( "astro1.png" ) );
		Image astro1C = new Image ( getClass ( ).getResourceAsStream ( "astro1C.png" ) );
		astroNode1Btn.setGraphic ( new ImageView ( astro1 ) );
		astroNode1Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		astroNode1Btn.setTranslateX ( -218 );
		astroNode1Btn.setTranslateY ( -130 );
		astroNode1Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				boolean purchased = purchaseAstronaut ( 1, astroNode1Btn, inGameConsole, astro1C, secondStagePlayer );
				turnScore.setText ( "Turn: " + turnNum + "\tScore: " + secondStagePlayer.getScore ( ) );

				if ( purchased )
				{
					astroNode2Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Astronaut node 2
		Image astro2 = new Image ( getClass ( ).getResourceAsStream ( "astro2.png" ) );
		Image astro2C = new Image ( getClass ( ).getResourceAsStream ( "astro2C.png" ) );
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
				boolean purchased = purchaseAstronaut ( 2, astroNode2Btn, inGameConsole, astro2C, secondStagePlayer );

				if ( purchased )
				{
					astroNode3Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Astronaut node 3
		Image astro3 = new Image ( getClass ( ).getResourceAsStream ( "astro3.png" ) );
		Image astro3C = new Image ( getClass ( ).getResourceAsStream ( "astro3C.png" ) );
		astroNode3Btn.setGraphic ( new ImageView ( astro3 ) );
		astroNode3Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		astroNode3Btn.setTranslateY ( 118 );
		astroNode3Btn.setDisable ( true );
		astroNode3Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				boolean purchased = purchaseAstronaut ( 3, astroNode3Btn, inGameConsole, astro3C, secondStagePlayer );

				if ( purchased )
				{
					astroNode4Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Astronaut node 4
		Image astro4 = new Image ( getClass ( ).getResourceAsStream ( "astro4.png" ) );
		Image astro4C = new Image ( getClass ( ).getResourceAsStream ( "astro4C.png" ) );
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
				boolean purchased = purchaseAstronaut ( 4, astroNode4Btn, inGameConsole, astro4C, secondStagePlayer );

				if ( purchased )
				{
					astroNode5Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Astronaut node 5
		Image astro5 = new Image ( getClass ( ).getResourceAsStream ( "astro5.png" ) );
		Image astro5C = new Image ( getClass ( ).getResourceAsStream ( "astro5C.png" ) );
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
				boolean purchased = purchaseAstronaut ( 5, astroNode5Btn, inGameConsole, astro5C, secondStagePlayer );

				if ( purchased )
				{
					astroNode6Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Astronaut node 6
		Image astro6 = new Image ( getClass ( ).getResourceAsStream ( "astro6.png" ) );
		Image astro6C = new Image ( getClass ( ).getResourceAsStream ( "astro6C.png" ) );
		astroNode6Btn.setGraphic ( new ImageView ( astro6 ) );
		astroNode6Btn.setStyle ( "-fx-background-color: rgba(0, 0, 0, 0);" );
		astroNode6Btn.setTranslateY ( -213 );
		astroNode6Btn.setDisable ( true );
		astroNode6Btn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				purchaseAstronaut ( 6, astroNode6Btn, inGameConsole, astro6C, secondStagePlayer );
			}
		} );
		// --------------------------------------------------------------------------------------------------------------------------

		// DOMES
		// -----------------------------------------------------
		// Dome node 1
		Image dome1 = new Image ( getClass ( ).getResourceAsStream ( "dome1.png" ) );
		Image dome1C = new Image ( getClass ( ).getResourceAsStream ( "dome1C.png" ) );
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
				boolean purchased = purchaseDome ( 1, domeNode1Btn, inGameConsole, dome1C, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 2 ).getMainRoad ( ).isOwned ( ) )
				{
					domeNode2Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Dome node 2
		Image dome2 = new Image ( getClass ( ).getResourceAsStream ( "dome2.png" ) );
		Image dome2C = new Image ( getClass ( ).getResourceAsStream ( "dome2C.png" ) );
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
				boolean purchased = purchaseDome ( 2, domeNode2Btn, inGameConsole, dome2C, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 3 ).getMainRoad ( ).isOwned ( ) )
				{
					domeNode3Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Dome node 3
		Image dome3 = new Image ( getClass ( ).getResourceAsStream ( "dome3.png" ) );
		Image dome3C = new Image ( getClass ( ).getResourceAsStream ( "dome3C.png" ) );
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
				boolean purchased = purchaseDome ( 3, domeNode3Btn, inGameConsole, dome3C, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 4 ).getMainRoad ( ).isOwned ( ) )
				{
					domeNode4Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Dome node 4
		Image dome4 = new Image ( getClass ( ).getResourceAsStream ( "dome4.png" ) );
		Image dome4C = new Image ( getClass ( ).getResourceAsStream ( "dome4C.png" ) );
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
				boolean purchased = purchaseDome ( 4, domeNode4Btn, inGameConsole, dome4C, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 5 ).getMainRoad ( ).isOwned ( ) )
				{
					domeNode5Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Dome node 5
		Image dome5 = new Image ( getClass ( ).getResourceAsStream ( "dome5.png" ) );
		Image dome5C = new Image ( getClass ( ).getResourceAsStream ( "dome5C.png" ) );
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
				boolean purchased = purchaseDome ( 5, domeNode5Btn, inGameConsole, dome5C, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 6 ).getMainRoad ( ).isOwned ( ) )
				{
					domeNode6Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Dome node 6
		Image dome6 = new Image ( getClass ( ).getResourceAsStream ( "dome6.png" ) );
		Image dome6C = new Image ( getClass ( ).getResourceAsStream ( "dome6C.png" ) );
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
				purchaseDome ( 6, domeNode6Btn, inGameConsole, dome6C, secondStagePlayer );
			}
		} );
		// ------------------------------------------------------------------------------------------------------------------------

		// RESEARCH STATIONS
		// -----------------------------------------------------
		// Research node 1
		Image research1 = new Image ( getClass ( ).getResourceAsStream ( "research1.png" ) );
		Image research1C = new Image ( getClass ( ).getResourceAsStream ( "research1C.png" ) );
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
				boolean purchased = purchaseResearch ( 1, researchNode1Btn, inGameConsole, research1C, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 2 ).getSideRoad ( ).isOwned ( ) )
				{
					researchNode2Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Research node 2
		Image research2 = new Image ( getClass ( ).getResourceAsStream ( "research2.png" ) );
		Image research2C = new Image ( getClass ( ).getResourceAsStream ( "research2C.png" ) );
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
				boolean purchased = purchaseResearch ( 2, researchNode2Btn, inGameConsole, research2C, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 3 ).getSideRoad ( ).isOwned ( ) )
				{
					researchNode3Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Research node 3
		Image research3 = new Image ( getClass ( ).getResourceAsStream ( "research3.png" ) );
		Image research3C = new Image ( getClass ( ).getResourceAsStream ( "research3C.png" ) );
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
				boolean purchased = purchaseResearch ( 3, researchNode3Btn, inGameConsole, research3C, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 4 ).getSideRoad ( ).isOwned ( ) )
				{
					researchNode4Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Research node 4
		Image research4 = new Image ( getClass ( ).getResourceAsStream ( "research4.png" ) );
		Image research4C = new Image ( getClass ( ).getResourceAsStream ( "research4C.png" ) );
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
				boolean purchased = purchaseResearch ( 4, researchNode4Btn, inGameConsole, research4C, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 5 ).getSideRoad ( ).isOwned ( ) )
				{
					researchNode5Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Research node 5
		Image research5 = new Image ( getClass ( ).getResourceAsStream ( "research5.png" ) );
		Image research5C = new Image ( getClass ( ).getResourceAsStream ( "research5C.png" ) );
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
				boolean purchased = purchaseResearch ( 5, researchNode5Btn, inGameConsole, research5C, secondStagePlayer );

				if ( purchased && secondStagePlayer.getGameBoard ( ).goToTile ( 6 ).getSideRoad ( ).isOwned ( ) )
				{
					researchNode6Btn.setDisable ( false );
				}
			}
		} );
		// -----------------------------------------------------

		// -----------------------------------------------------
		// Research node 6
		Image research6 = new Image ( getClass ( ).getResourceAsStream ( "research6.png" ) );
		Image research6C = new Image ( getClass ( ).getResourceAsStream ( "research6C.png" ) );
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
				purchaseResearch ( 6, researchNode6Btn, inGameConsole, research6C, secondStagePlayer );

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

		// Rolls left text
		Text count = new Text ( "Rolls left " + rollCount );
		count.setFont ( Font.font ( null, FontWeight.BOLD, 50 ) );
		count.setTranslateX ( 225 );
		count.setTranslateY ( 350 );

		//Commit button to "spend" dice
		Button commit = new Button ( );
		commit.setText ( " Spend " );
		commit.setTranslateX ( 225 );
		commit.setTranslateY ( 420 );

		String[ ] finalDice = new String[6];

		// runs through to check for saved dice
		checkClicked ( d1Check, die1Button );
		checkClicked ( d2Check, die2Button );
		checkClicked ( d3Check, die3Button );
		checkClicked ( d4Check, die4Button );
		checkClicked ( d5Check, die5Button );
		checkClicked ( d6Check, die6Button );

		//Roll dice button
		Button rollButton = new Button ( );
		rollButton.setText ( " Roll Dice " );
		rollButton.setTranslateX ( 130 );
		rollButton.setTranslateY ( 420 );
		commit.setDisable ( true );
		
		//Roll dice button event handler
		rollButton.setOnAction ( new EventHandler<ActionEvent> ( )
		{

			@Override
			public void handle( ActionEvent event )
			{
				commit.setDisable ( false );
				count.setText ( "Rolls left " + ( --rollCount ) );

				if ( die1Button.isDisable ( ) )
				{
					// do nothing
				}
				else
				{
					die1.setDieString ( getDieRolls ( ) );
					die1Button.setContentDisplay ( ContentDisplay.TOP );
					getImage ( die1, die1Button );
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
				}
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
						System.out.println ( finalDice[i] );
						switch ( finalDice[i] )
						{
							case "Dihydrogen Monoxide":
								secondStagePlayer.getTurn ( ).setWat ( secondStagePlayer.getTurn ( ).getWat ( ) + 1 );
								break;
							case "Silicon Dioxide":
								secondStagePlayer.getTurn ( ).setSil ( secondStagePlayer.getTurn ( ).getSil ( ) + 1 );
								break;
							case "Iron Ore":
								secondStagePlayer.getTurn ( ).setOre ( secondStagePlayer.getTurn ( ).getOre ( ) + 1 );
								break;
							case "Oxygen":
								secondStagePlayer.getTurn ( ).setOx ( secondStagePlayer.getTurn ( ).getOx ( ) + 1 );
								break;
							case "Solar Batteries":
								secondStagePlayer.getTurn ( ).setSol ( secondStagePlayer.getTurn ( ).getSol ( ) + 1 );
								break;

						}
					}
					d1Check.setSelected ( true );
					d2Check.setSelected ( true );
					d3Check.setSelected ( true );
					d4Check.setSelected ( true );
					d5Check.setSelected ( true );
					d6Check.setSelected ( true );
					commit.setDisable ( true );
				}
			}

		} );

		//Commit button event handler
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
							secondStagePlayer.getTurn ( ).setWat ( secondStagePlayer.getTurn ( ).getWat ( ) + 1 );
							break;
						case "Silicon Dioxide":
							secondStagePlayer.getTurn ( ).setSil ( secondStagePlayer.getTurn ( ).getSil ( ) + 1 );
							break;
						case "Iron Ore":
							secondStagePlayer.getTurn ( ).setOre ( secondStagePlayer.getTurn ( ).getOre ( ) + 1 );
							break;
						case "Oxygen":
							secondStagePlayer.getTurn ( ).setOx ( secondStagePlayer.getTurn ( ).getOx ( ) + 1 );
							break;
						case "Solar Batteries":
							secondStagePlayer.getTurn ( ).setSol ( secondStagePlayer.getTurn ( ).getSol ( ) + 1 );
							break;
					}
				}
				secondStagePlayer.getTurn ( ).toString ( );
			}
		} );

		//End turn button event handler
		endTurn.setOnMouseClicked ( new EventHandler<MouseEvent> ( )
		{
			@Override
			public void handle( MouseEvent e )
			{
				if ( secondStagePlayer.getTurnNum ( ) >= 15 )
				{
					quit.setDisable ( true );
					guide.setDisable ( true );
					die1Button.setDisable ( true );
					die2Button.setDisable ( true );
					die3Button.setDisable ( true );
					die4Button.setDisable ( true );
					die5Button.setDisable ( true );
					die6Button.setDisable ( true );
					d1Check.setDisable ( true );
					d2Check.setDisable ( true );
					d3Check.setDisable ( true );
					d4Check.setDisable ( true );
					d5Check.setDisable ( true );
					d6Check.setDisable ( true );
					rollButton.setDisable ( true );
					commit.setDisable ( true );
					endTurn.setDisable ( true );
					roadNode1Btn.setDisable ( true );
					roadNode2Btn.setDisable ( true );
					roadNode3Btn.setDisable ( true );
					roadNode4Btn.setDisable ( true );
					roadNode5Btn.setDisable ( true );
					roadNode6Btn.setDisable ( true );
					sideRoadNode1Btn.setDisable ( true );
					sideRoadNode2Btn.setDisable ( true );
					sideRoadNode3Btn.setDisable ( true );
					sideRoadNode4Btn.setDisable ( true );
					sideRoadNode5Btn.setDisable ( true );
					sideRoadNode6Btn.setDisable ( true );
					domeNode1Btn.setDisable ( true );
					domeNode2Btn.setDisable ( true );
					domeNode3Btn.setDisable ( true );
					domeNode4Btn.setDisable ( true );
					domeNode5Btn.setDisable ( true );
					domeNode6Btn.setDisable ( true );
					researchNode1Btn.setDisable ( true );
					researchNode2Btn.setDisable ( true );
					researchNode3Btn.setDisable ( true );
					researchNode4Btn.setDisable ( true );
					researchNode5Btn.setDisable ( true );
					researchNode6Btn.setDisable ( true );
					astroNode1Btn.setDisable ( true );
					astroNode2Btn.setDisable ( true );
					astroNode3Btn.setDisable ( true );
					astroNode4Btn.setDisable ( true );
					astroNode5Btn.setDisable ( true );
					astroNode6Btn.setDisable ( true );
					
					try
					{
						EndGameScore( playerName, secondStagePlayer.getScore ( ));
					} catch ( IOException e1 )
					{
						System.out.println ( "The EndGameScore function call in SecondStage.java line 1212 is broken!" );
						e1.printStackTrace();
					}
					
					EndStage endWindow = new EndStage ( playerName, secondStagePlayer.getScore ( ) );
					endWindow.start ( endWindow );					
				}
				else
				{
					secondStagePlayer.getTurn ( ).setOre ( 0 );
					secondStagePlayer.getTurn ( ).setOx ( 0 );
					secondStagePlayer.getTurn ( ).setSil ( 0 );
					secondStagePlayer.getTurn ( ).setSol ( 0 );
					secondStagePlayer.getTurn ( ).setWat ( 0 );
					secondStagePlayer.setTurnNum ( secondStagePlayer.getTurnNum ( ) + 1 );
					inGameConsole.appendText ( "Starting turn number " + secondStagePlayer.getTurnNum ( ) + "\n" );
					die1Button.setGraphic ( null );
					die2Button.setGraphic ( null );
					die3Button.setGraphic ( null );
					die4Button.setGraphic ( null );
					die5Button.setGraphic ( null );
					die6Button.setGraphic ( null );
					d1Check.setSelected ( false );
					d2Check.setSelected ( false );
					d3Check.setSelected ( false );
					d4Check.setSelected ( false );
					d5Check.setSelected ( false );
					d6Check.setSelected ( false );
					commit.setDisable ( false );
					rollButton.setDisable ( false );
					rollCount = 3;
					count.setText ( "Rolls left " + rollCount );
					turnScore.setText (
							"Turn: " + secondStagePlayer.getTurnNum ( ) + "\tScore: " + secondStagePlayer.getScore ( ) );
				}
			}
		} );

		// Dice end here ***************************************************

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
		root.getChildren ( ).add ( endTurn );

		secondaryStage.setTitle ( playerName + "'s Martian Colony" ); // Set the stage title
		secondaryStage.setScene ( scene ); // Place the scene in the stage
		secondaryStage.show ( ); // Display the stage
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
	
	//--------------------------------------------
	// Method to write the players name andscore to a file before the game is closed
	// Parameters: String name and int score
	// return: none
	//--------------------------------------------
	public static void EndGameScore( String name, int score) throws IOException
	{

		PrintWriter writer = new PrintWriter ( new FileWriter( "scores.txt", true) );

		writer.println ( name );
		writer.println ( score );
		writer.close();

	}

	/******************************************************************************************************
	 * Method to purchase a research facility
	 * 
	 * @author Luke Johnson
	 * 
	 * @param tileNum:
	 *           corresponding tile on the game board
	 * @param researchNodeBtn:
	 *           corresponding research button
	 * @param inGameConsole:
	 *           in game output console
	 * @param researchC:
	 *           colored research facility image
	 * @param player:
	 *           player whose game board is being dealt with
	 * 
	 * @return: boolean value for whether the purchase was successful
	 */
	public static boolean purchaseResearch( int tileNum, Labeled researchNodeBtn, TextInputControl inGameConsole,
			Image researchC, Player player )
	{
		boolean purchased = false;
		Node current = player.getGameBoard ( ).goToTile ( tileNum );
		Node next = current.getLink ( );
		Resource research = current.getResearch ( );

		if ( research.isUnlocked ( ) && !research.isOwned ( ) && player.getTurn ( ).getOre ( ) >= 3
				&& player.getTurn ( ).getSol ( ) >= 2 )
		{
			researchNodeBtn.setGraphic ( new ImageView ( researchC ) );
			inGameConsole.appendText ( "You purchased Research Facility " + tileNum + "\n" );
			player.setScore ( player.getScore ( ) + research.getValue ( ) );
			player.getTurn ( ).setOre ( player.getTurn ( ).getOre() - 3 );
			player.getTurn ( ).setSol ( player.getTurn ( ).getSol() - 2 );
			research.setOwned ( true );
			purchased = true;

			if ( next != null && next.getMainRoad ( ).isOwned ( ) )
			{
				next.getResearch ( ).setUnlocked ( true );
			}
		}
		else if ( research.isOwned ( ) )
		{
			inGameConsole.appendText ( "You already own that.\n" );
		}
		else if ( !research.isUnlocked ( ) )
		{
			inGameConsole.appendText ( "That isn't unlocked.\n" );
		}

		else if ( player.getTurn ( ).getOre ( ) != 3 || player.getTurn ( ).getSol ( ) != 2 )
		{
			inGameConsole.appendText ( "You can't afford that.\n" );
		}

		return purchased;
	}

	/******************************************************************************************************
	 * Method to purchase a side road
	 * 
	 * @author Luke Johnson
	 * 
	 * @param tileNum:
	 *           corresponding tile on the game board
	 * @param roadNodeBtn:
	 *           corresponding road button
	 * @param inGameConsole:
	 *           in game output console
	 * @param roadC:
	 *           colored road image
	 * @param player:
	 *           player whose game board is being dealt with
	 * 
	 * @return: boolean value for whether the purchase was successful
	 */
	public static boolean purchaseSideRoad( int tileNum, Labeled roadNodeBtn, TextInputControl inGameConsole,
			Image roadC, Player player )
	{
		boolean purchased = false;
		Node current = player.getGameBoard ( ).goToTile ( tileNum );
		Node previous = tileNum > 1 ? player.getGameBoard ( ).goToTile ( tileNum - 1 ) : null;
		Resource sideRoad = current.getSideRoad ( );
		Resource mainRoad = current.getMainRoad ( );
		

		if ( mainRoad.isOwned ( ) && !sideRoad.isOwned ( ) && player.getTurn ( ).getSil ( ) > 0
				&& player.getTurn ( ).getOx ( ) > 0 )
		{
			roadNodeBtn.setGraphic ( new ImageView ( roadC ) );
			inGameConsole.appendText ( "You purchased side road " + tileNum + " \n" );
			player.setScore ( player.getScore ( ) + sideRoad.getValue ( ) );
			sideRoad.setOwned ( true );
			player.getTurn ( ).setSil ( player.getTurn ( ).getSil ( ) - 1 );
			player.getTurn ( ).setOx ( player.getTurn ( ).getOx ( ) - 1 );
			purchased = true;

			if ( previous != null && previous.getResearch ( ).isOwned ( ) )
			{
				current.getResearch ( ).setUnlocked ( true );
			}
			else if ( previous == null )
			{
				current.getResearch ( ).setUnlocked ( true );
			}
		}
		else if ( sideRoad.isOwned ( ) )
		{
			inGameConsole.appendText ( "You already own that.\n" );
		}
		else if ( !sideRoad.isUnlocked ( ) )
		{
			inGameConsole.appendText ( "That isn't unlocked.\n" );
		}

		else if ( player.getTurn ( ).getSil ( ) < 1 || player.getTurn ( ).getOx ( ) < 1 )
		{
			inGameConsole.appendText ( "You can't afford that.\n" );
		}

		return purchased;
	}

	/***************************************************************************************************
	 * Method to purchase a main road
	 * 
	 * @author Luke Johnosn
	 * @param tileNum:
	 *           tile road is on
	 * @param roadNodeBtn:
	 *           corresponding button
	 * @param inGameConsole:
	 *           the console for output
	 * @param roadC:
	 *           colored road image
	 * @param player:
	 *           player whosed board is being dealt with
	 * 
	 * @return: boolean value for whether the purchase is successful
	 */
	public static boolean purchaseMainRoad( int tileNum, Labeled roadNodeBtn, TextInputControl inGameConsole,
			Image roadC, Player player )
	{
		Node current = player.getGameBoard ( ).goToTile ( tileNum );
		Node previous = tileNum > 1 ? player.getGameBoard ( ).goToTile ( tileNum - 1 ) : null;
		Resource mainRoad = current.getMainRoad ( );
		if ( mainRoad.isUnlocked ( ) && !mainRoad.isOwned ( ) && player.getTurn ( ).getSil ( ) > 0
				&& player.getTurn ( ).getOx ( ) > 0 )
		{
			roadNodeBtn.setGraphic ( new ImageView ( roadC ) );
			inGameConsole.appendText ( "You purchased main road " + tileNum + " \n" );
			player.setScore ( player.getScore ( ) + 1 );
			player.getTurn ( ).setSil ( player.getTurn ( ).getSil ( ) - 1 );
			player.getTurn ( ).setOx ( player.getTurn ( ).getOx ( ) - 1 );
			mainRoad.setOwned ( true );
			current.getSideRoad ( ).setUnlocked ( true );
			if ( previous != null && previous.getBioDome ( ).isOwned ( ) )
			{
				current.getBioDome ( ).setUnlocked ( true );
			}
			else if ( previous == null )
			{
				current.getBioDome ( ).setUnlocked ( true );
			}
			if ( current.getLink ( ) != null )
			{
				current.getLink ( ).getMainRoad ( ).setUnlocked ( true );

			}
			return true;
		}
		else if ( current.getMainRoad ( ).isOwned ( ) )
		{
			inGameConsole.appendText ( "You already own that\n" );
		}
		else if ( !current.getMainRoad ( ).isUnlocked ( ) )
		{
			inGameConsole.appendText ( "That is not unlocked\n" );
		}

		else if ( player.getTurn ( ).getSil ( ) < 1 || player.getTurn ( ).getOx ( ) < 1 )
		{
			inGameConsole.appendText ( "You can't afford that\n" );
		}

		return false;
	}

	/**************************************************************************************
	 * Method to purchase a bio-dome
	 * 
	 * @author Luke Johnson
	 * 
	 * @param tileNum:
	 *           corresponding tile on the game board
	 * @param domeNodeBtn:
	 *           corresponding dome button
	 * @param inGameConsole:
	 *           in game console for output
	 * @param domeC:
	 *           corresponding colored dome button
	 * @param player:
	 *           player whose game board is being dealt with
	 * 
	 * @return: boolean value for whether the purchase was successful
	 */
	public boolean purchaseDome( int tileNum, Labeled domeNodeBtn, TextInputControl inGameConsole, Image domeC,
			Player player )
	{
		Node current = player.getGameBoard ( ).goToTile ( tileNum );
		Node next = current.getLink ( );
		Resource bioDome = current.getBioDome ( );
		boolean purchased = false;
		System.out.println ( player.getTurn ( ).toString ( ) );
		if ( bioDome.isUnlocked ( ) && !bioDome.isOwned ( )
				&& ( player.getTurn ( ).getSil ( ) > 0 && player.getTurn ( ).getOx ( ) > 0
						&& player.getTurn ( ).getWat ( ) > 0 && player.getTurn ( ).getSol ( ) > 0 ) )
		{
			domeNodeBtn.setGraphic ( new ImageView ( domeC ) );
			inGameConsole.appendText ( "You purchased Bio-dome " + tileNum + "\n" );
			player.setScore ( player.getScore ( ) + bioDome.getValue ( ) );
			player.getTurn ( ).setWat ( player.getTurn ( ).getWat ( ) - 1 );
			player.getTurn ( ).setOx ( player.getTurn ( ).getOx ( ) - 1 );
			player.getTurn ( ).setSol ( player.getTurn ( ).getSol ( ) - 1 );
			player.getTurn ( ).setSil ( player.getTurn ( ).getSil ( ) - 1 );
			bioDome.setOwned ( true );
			purchased = true;

			if ( next != null && next.getMainRoad ( ).isOwned ( ) )
			{
				next.getBioDome ( ).setUnlocked ( true );
			}
		}
		else if ( bioDome.isOwned ( ) )
		{
			inGameConsole.appendText ( "You already own that.\n" );
		}
		else if ( !bioDome.isUnlocked ( ) )
		{
			inGameConsole.appendText ( "That isn't unlocked.\n" );
		}

		else if ( player.getTurn ( ).getSil ( ) < 1 || player.getTurn ( ).getOx ( ) < 1
				|| player.getTurn ( ).getWat ( ) < 1 || player.getTurn ( ).getSol ( ) < 1 )
		{
			inGameConsole.appendText ( "You can't afford that.\n" );
		}

		return purchased;
	}

	/**************************************************************************************
	 * Method to purchase an astronaut
	 * 
	 * @author Luke Johnson
	 * 
	 * @param tileNum:
	 *           tile on the game board
	 * @param astroNodeBtn:
	 *           button for the corresponding astronaut
	 * @param inGameConsole:
	 *           in-game output console
	 * @param astroC:
	 *           the corresponding colored button
	 * @param player:
	 *           player whose board is being dealt with
	 * @return: boolean value for whether the purchase was successful
	 */
	public boolean purchaseAstronaut( int tileNum, Labeled astroNodeBtn, TextInputControl inGameConsole, Image astroC,
			Player player )
	{
		Node current = player.getGameBoard ( ).goToTile ( tileNum );
		Resource astronaut = current.getAstronaut ( );
		boolean purchased = false;

		if ( astronaut.isUnlocked ( ) && !astronaut.isOwned ( ) && player.getTurn ( ).getWat ( ) > 0
				&& player.getTurn ( ).getSol ( ) > 0 && player.getTurn ( ).getOre ( ) > 0 )
		{
			astroNodeBtn.setGraphic ( new ImageView ( astroC ) );
			inGameConsole.appendText ( tileNum == 6 ? ( "You've purchased Mark Watney,\nthe space pirate!\n" )
					: ( "You purchased" + " astronaut " + tileNum + " \n" ) );
			player.setScore ( player.getScore ( ) + current.getAstronaut ( ).getValue ( ) );
			astronaut.setOwned ( true );
			player.getTurn ( ).setWat ( player.getTurn ( ).getWat ( ) - 1 );
			player.getTurn ( ).setOre ( player.getTurn ( ).getOre ( ) - 1 );
			player.getTurn ( ).setSol ( player.getTurn ( ).getSol ( ) - 1 );
			purchased = true;
			if ( current.getLink ( ) != null )
			{
				current.getLink ( ).getAstronaut ( ).setUnlocked ( true );
			}
		}
		else if ( astronaut.isOwned ( ) )
		{
			inGameConsole.appendText ( "You already own that.\n" );
		}
		else if ( !astronaut.isUnlocked ( ) )
		{
			inGameConsole.appendText ( "That isn't unlocked.\n" );
		}

		else if ( player.getTurn ( ).getWat ( ) < 1 || player.getTurn ( ).getSol ( ) < 1
				|| player.getTurn ( ).getOre ( ) < 1 )
		{
			inGameConsole.appendText ( "You can't afford that\n" );
		}

		return purchased;
	}

	/**********************************************************************************
	 * Method to initialize a player
	 * 
	 * @author Luke Johnson
	 * @param player:
	 *           player whose board is being initialized
	 */
	public static Player initializePlayer( Player player )
	{
		Node head = player.getGameBoard ( ).getHead ( );
		Node current = head;

		// create all of the tiles on the board
		for ( int i = 0; i < 5; i++ )
		{
			Node newNode = new Node ( ( i + 2 ), current.getResearch ( ).getValue ( ),
					current.getAstronaut ( ).getValue ( ), current.getBioDome ( ).getValue ( ) );
			player.getGameBoard ( ).addToTail ( newNode );
			current.setLink ( newNode );
			current = newNode;
			player.getGameBoard ( ).setNumTiles ( player.getGameBoard ( ).getNumTiles ( ) + 1 );

		}
		// unlock main road and astronaut on first tile
		player.getGameBoard ( ).getHead ( ).getMainRoad ( ).setUnlocked ( true );
		player.getGameBoard ( ).getHead ( ).getAstronaut ( ).setUnlocked ( true );

		return player;
	}

	static Scanner input = new Scanner ( System.in );
}
/*****************************************************
 * This class creates a pop-up window giving the player all the basic rules for
 * the game. Once finished the window can be closed by the user to return to the game.
 * @author Jared Crouse
 * 
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GuideStage extends Stage
{
	public void start( Stage guideStage )
	{
		StackPane root = new StackPane ( );
		Scene scene = new Scene ( root, 1920, 1080 );
		Image image = new Image ( "guideScreen.jpg" );

		// Guide title with dropshadow
		DropShadow shadow = new DropShadow ( );
		shadow.setRadius ( 10.0 );
		shadow.setOffsetX ( 5.0 );
		shadow.setOffsetY ( 5.0 );
		Text title = new Text ( );
		title.setEffect ( shadow );
		title.setCache ( true );
		title.setTranslateY ( -425 );
		title.setFill ( Color.GREEN );
		title.setText ( "Martian Colony Builders Guide" );
		title.setFont ( Font.font ( null, 100 ) );

		// Defining the return button
		Button ret = new Button ( "Return to game" );
		ret.setTranslateX ( -850 );
		ret.setTranslateY ( -450 );
		ret.setOnAction ( new EventHandler<ActionEvent> ( )
		{
			@Override
			public void handle( ActionEvent event )
			{
				guideStage.close ( );
			}// end action
		} );
		
		// Guide content
		Text heading1 = new Text ( );
		heading1.setTranslateY ( -349 );
		heading1.setFill ( Color.YELLOW );
		heading1.setText ( "The Basics" );
		heading1.setFont ( Font.font ( null, FontWeight.BOLD, 25 ) );
		
		Text paragraph1 = new Text ( );
		paragraph1.setTranslateY ( -295 );
		paragraph1.setFill ( Color.YELLOW );
		paragraph1.setTextAlignment(TextAlignment.CENTER);
		paragraph1.setText ( "The game map depicts the planet of Mars, with special symbols for Research stations, Biodomes, Roads, and Astronauts. During the course\n "
				+ " of the game, you and your fellow players each try to build as many Roads, Research Stations, Biodomes, and Astronauts as possible\n" 
				+ "on the planet depicted on your game map." );
		paragraph1.setFont ( Font.font ( null, 25 ) );
		
		Text heading2 = new Text ( );
		heading2.setTranslateY ( -232 );
		heading2.setFill ( Color.YELLOW );
		heading2.setText ( "Building Costs & Resources" );
		heading2.setFont ( Font.font ( null, FontWeight.BOLD, 25 ) );
		
		Text paragraph2 = new Text ( );
		paragraph2.setTranslateY ( -195 );
		paragraph2.setFill ( Color.YELLOW );
		paragraph2.setTextAlignment(TextAlignment.CENTER);
		paragraph2.setText ( "6 dice are used to roll for resources. Each die has 5 sides, and each side depicts a different resource: Water, Silicon, Solar Batteries,\n" + 
				"Iron Ore & Oxygen. For example, building a road costs 1 Silicon and 1 Oxygen, so you can build a road only after having rolled these two resources." );
		paragraph2.setFont ( Font.font ( null, 25 ) );
		
		Text heading3 = new Text ( );
		heading3.setTranslateY ( -150 );
		heading3.setFill ( Color.YELLOW );
		heading3.setText ( "Rolling The Dice" );
		heading3.setFont ( Font.font ( null, FontWeight.BOLD, 25 ) );
		
		Text paragraph3 = new Text ( );
		paragraph3.setTranslateY ( -100 );
		paragraph3.setFill ( Color.YELLOW );
		paragraph3.setTextAlignment(TextAlignment.CENTER);
		paragraph3.setText ( "When it is your turn, you may roll the dice up to three times. After the first roll, you set any number of dice aside by pressing the hold button and\n" + 
				"roll the remaining dice once again. After the second roll, you may set aside more dice, but you may also pick up dice that were set aside\n" +
				"previously and use them for your last roll. You may also choose to stand pat” with the result obtained after the first or second dice roll." );
		paragraph3.setFont ( Font.font ( null, 25 ) );
		
		Text heading4 = new Text ( );
		heading4.setTranslateY ( -42 );
		heading4.setFill ( Color.YELLOW );
		heading4.setText ( "Building" );
		heading4.setFont ( Font.font ( null, FontWeight.BOLD, 25 ) );
		
		Text paragraph4 = new Text ( );
		paragraph4.setTranslateY ( 38 );
		paragraph4.setFill ( Color.YELLOW );
		paragraph4.setTextAlignment(TextAlignment.CENTER);
		paragraph4.setText ( "The Building Overview indicates which resources are required to build Research Stations,Biodomes, Roads, or Astronauts. For example, if you build an\n" + 
				"Astronaut, set aside one die with Iron Ore, one with Water, and one with Solar Batteries — then click an Astronaut symbol on your game map.\n" +
				"The procedure is the same as for building a Road, Research Station or Biodome (with different resources of course). You may build multiple times\n" +
				"during your turn, if you have rolled enough of the appropriate resources. After building, the points will automatically tally in the score dialogue\n" +
				"near the top of your screen." );
		paragraph4.setFont ( Font.font ( null, 25 ) );
		
		Text heading5 = new Text ( );
		heading5.setTranslateX ( -900 );
		heading5.setTranslateY ( 125 );
		heading5.setFill ( Color.BLUE );
		heading5.setText ( "Roads" );
		heading5.setFont ( Font.font ( null, FontWeight.BOLD, 25 ) );
		
		Text paragraph5 = new Text ( );
		paragraph5.setTranslateY ( 163 );
		paragraph5.setFill ( Color.BLUE );
		paragraph5.setTextAlignment(TextAlignment.CENTER);
		paragraph5.setText ( "Built in sequence, meaning that a new Road must be built next to a Road that has already been built. A Research Station or Biodome next to a Road\n" +
		"does not obstruct subsequent Road building—this also applies if the Biodome or Research Station hasn’t yet been built." );
		paragraph5.setFont ( Font.font ( null, 25 ) );
		
		Text heading6 = new Text ( );
		heading6.setTranslateX ( -878 );
		heading6.setTranslateY ( 207 );
		heading6.setFill ( Color.BLUE );
		heading6.setText ( "Biodomes" );
		heading6.setFont ( Font.font ( null, FontWeight.BOLD, 25 ) );
		
		Text paragraph6 = new Text ( );
		paragraph6.setTranslateY ( 250 );
		paragraph6.setFill ( Color.BLUE );
		paragraph6.setTextAlignment(TextAlignment.CENTER);
		paragraph6.setText ( "Can only be built if an already built Road is next to it. Furthermore, Biodomes must be built following the sequence of their points: first the Biodome\n" + 
				"with 2 points must be built, then the Biodome with 4 points, and so on." );
		paragraph6.setFont ( Font.font ( null, 25 ) );
		
		Text heading7 = new Text ( );
		heading7.setTranslateX ( -818 );
		heading7.setTranslateY ( 290 );
		heading7.setFill ( Color.BLUE );
		heading7.setText ( "Research Stations" );
		heading7.setFont ( Font.font ( null, FontWeight.BOLD, 25 ) );
		
		Text paragraph7 = new Text ( );
		paragraph7.setTranslateY ( 330 );
		paragraph7.setFill ( Color.BLUE );
		paragraph7.setTextAlignment(TextAlignment.CENTER);
		paragraph7.setText ( "The procedure is the same as for building a Biodome. A Research Station can only be built if it is next to an already built road. Research stations must\n" +
		"also be built following the sequence of their points." );
		paragraph7.setFont ( Font.font ( null, 25 ) );
		
		Text heading8 = new Text ( );
		heading8.setTranslateX ( -865 );
		heading8.setTranslateY ( 370 );
		heading8.setFill ( Color.BLUE );
		heading8.setText ( "Astronauts" );
		heading8.setFont ( Font.font ( null, FontWeight.BOLD, 25 ) );
		
		Text paragraph8 = new Text ( );
		paragraph8.setTranslateX ( -580 );
		paragraph8.setTranslateY ( 400 );
		paragraph8.setFill ( Color.BLUE );
		paragraph8.setTextAlignment(TextAlignment.LEFT);
		paragraph8.setText ( "Must also be built following the sequence of their points." );
		paragraph8.setFont ( Font.font ( null, 25 ) );
		
		Text heading9 = new Text ( );
		heading9.setTranslateY ( 420 );
		heading9.setFill ( Color.RED );
		heading9.setText ( "End Of The Game" );
		heading9.setFont ( Font.font ( null, FontWeight.BOLD, 25 ) );
		
		Text paragraph9 = new Text ( );
		paragraph9.setTranslateY ( 458 );
		paragraph9.setFill ( Color.RED );
		paragraph9.setTextAlignment(TextAlignment.CENTER);
		paragraph9.setText ( "The game ends after each player has finished 15 turns.  Your points are automatically added up and saved to the High Scores file, the player with\n" +
		"the highest score wins." );
		paragraph9.setFont ( Font.font ( null, 25 ) );

		root.getChildren ( ).add ( new ImageView ( image ) );
		root.getChildren ( ).add ( title );
		root.getChildren ( ).add ( ret );
		root.getChildren ( ).add ( heading1 );
		root.getChildren ( ).add ( heading2 );
		root.getChildren ( ).add ( heading3 );
		root.getChildren ( ).add ( heading4 );
		root.getChildren ( ).add ( heading5 );
		root.getChildren ( ).add ( heading6 );
		root.getChildren ( ).add ( heading7 );
		root.getChildren ( ).add ( heading8 );
		root.getChildren ( ).add ( heading9 );
		root.getChildren ( ).add ( paragraph1 );
		root.getChildren ( ).add ( paragraph2 );
		root.getChildren ( ).add ( paragraph3 );
		root.getChildren ( ).add ( paragraph4 );
		root.getChildren ( ).add ( paragraph5 );
		root.getChildren ( ).add ( paragraph6 );
		root.getChildren ( ).add ( paragraph7 );
		root.getChildren ( ).add ( paragraph8 );
		root.getChildren ( ).add ( paragraph9 );
		
		guideStage.setTitle ( "Martian Colony Builders Guide" ); // Set the stage title
		guideStage.setScene ( scene ); // Place the scene in the stage
		guideStage.show ( ); // Display the stage
	}
}
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuitStage extends Stage
{
	public void start( Stage quitStage )
	{
		StackPane root = new StackPane ( );
		Scene scene = new Scene ( root, 1920, 1080 );
		Image image = new Image ( "quitScreen.jpg" );

		// Confirmation text with dropshadow
		DropShadow shadow = new DropShadow ( );
		shadow.setRadius ( 10.0 );
		shadow.setOffsetX ( 5.0 );
		shadow.setOffsetY ( 5.0 );
		Text confirm = new Text ( );
		confirm.setEffect ( shadow );
		confirm.setCache ( true );
		confirm.setTranslateY ( -425 );
		confirm.setFill ( Color.GREEN );
		confirm.setText ( "Are you sure?" );
		confirm.setFont ( Font.font ( null, 100 ) );

		// Defining the NO button
		Button no = new Button ( "No, I can science this!" );
		no.setTranslateX ( -100 );
		no.setTranslateY ( -300 );
		no.setOnAction ( new EventHandler<ActionEvent> ( )
		{
			@Override
			public void handle( ActionEvent event )
			{
				quitStage.close ( );
			}// end action
		} );
		
		// Defining the YES button
		Button yes = new Button ( "Yes, evacuate in shame!" );
		yes.setTranslateX ( 100 );
		yes.setTranslateY ( -300 );
		yes.setOnAction ( new EventHandler<ActionEvent> ( )
		{
			@Override
			public void handle( ActionEvent event )
			{
				Platform.exit ( );
			}// end action
		} );

		root.getChildren ( ).add ( new ImageView ( image ) );
		root.getChildren ( ).add ( confirm );
		root.getChildren ( ).add ( no );
		root.getChildren ( ).add ( yes );
		
		quitStage.setTitle ( "EVACUATE" ); // Set the stage title
		quitStage.setScene ( scene ); // Place the scene in the stage
		quitStage.show ( ); // Display the stage
	}
}

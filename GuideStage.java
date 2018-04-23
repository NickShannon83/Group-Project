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
		Text content = new Text ( );
		content.setTranslateY ( -350 );
		content.setFill ( Color.YELLOW );
		content.setText ( "Guide contents here" );
		content.setFont ( Font.font ( null, 25 ) );

		root.getChildren ( ).add ( new ImageView ( image ) );
		root.getChildren ( ).add ( title );
		root.getChildren ( ).add ( ret );
		root.getChildren ( ).add ( content );
		
		
		guideStage.setTitle ( "Martian Colony Builders Guide" ); // Set the stage title
		guideStage.setScene ( scene ); // Place the scene in the stage
		guideStage.show ( ); // Display the stage
	}
}
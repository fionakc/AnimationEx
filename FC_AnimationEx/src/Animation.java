import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation extends Application {
	
	int width=400, height=300;
	float x=100, y=100, dx=-1.5f, dy=-1.5f;

	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) throws Exception {
		final Circle circle =new Circle(x, y, 30);
		Group root=new Group(circle);
		final Scene scene=new Scene(root,width,height);
		
		KeyFrame frame =new KeyFrame(Duration.millis(16),new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				if(circle.getCenterX()+circle.getTranslateX()<circle.getRadius() || 
						circle.getCenterX()+circle.getTranslateX()+circle.getRadius()> scene.getWidth()) { //hits left wall and right wall
					dx=-dx;
				}
				
				if(circle.getCenterY()+circle.getTranslateY()<circle.getRadius() || 
						circle.getCenterY()+circle.getTranslateY()+circle.getRadius()>scene.getHeight()) { //hits top or bottom
					dy=-dy;
				}
				
				circle.setTranslateX(circle.getTranslateX()+dx);
				circle.setTranslateY(circle.getTranslateY()+dy);
			}
			
		});
		
		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();
		
		
		primaryStage.setTitle("Hello Animation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();

	}

}

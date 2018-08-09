import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.lang.Math;

public class AnimateMultiBalls extends Application {

	//fields
	int width=400, height=300; //window width and height
	float x=100, y=100, dx=-1.5f, dy=-1.5f; //ball starting position and direction
	//Circle[] circles=new Circle[3]; //create array of circles
	Ball[] balls=new Ball[3];
	Group root=new Group();
	final Scene scene =new Scene(root,width,height);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//final Circle circle =new Circle(x, y, 30);
		initialiseCircles();
		
		//adding circles to the group individually
		for(int i=0;i<balls.length;i++) {
			root.getChildren().add(balls[i].getCircle());
		}
		
		
		KeyFrame frame =new KeyFrame(Duration.millis(16),new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				checkWallBounce();
				checkBallBounce();
			}
			
		});
		
		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();
		
		
		primaryStage.setTitle("Hello Animation");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public void initialiseCircles() {
		//final Circle circle =new Circle(x, y, 30);
		for(int i=0;i<balls.length;i++) {
//			float xPos=(float) (Math.random()*this.width);
//			float yPos=(float) (Math.random()*this.height);
//			final Circle circle=new Circle(xPos,yPos,30);
//			circles[i]=circle;
			balls[i]=new Ball(width, height);
		}
		
	}
	
	public void checkWallBounce() {
		for(int i=0;i<balls.length;i++) {
		
			if(balls[i].getCircle().getCenterX()+balls[i].getCircle().getTranslateX()<balls[i].getCircle().getRadius()) {
				balls[i].setdx(-1);
				//balls[i].setXPos(balls[i].getCircle().getRadius());
			}
			
			if(balls[i].getCircle().getCenterX()+balls[i].getCircle().getTranslateX()+balls[i].getCircle().getRadius()> scene.getWidth()) {
				balls[i].setdx(-1);
				//balls[i].setXPos(scene.getWidth()-balls[i].getCircle().getRadius());
			}
//			if(balls[i].getXPos()<balls[i].getRadius() || 
//					balls[i].getXPos()> scene.getWidth()-balls[i].getRadius()) { //hits left wall and right wall
//				dx=-dx;
//			}
			
			if(balls[i].getCircle().getCenterY()+balls[i].getCircle().getTranslateY()<balls[i].getCircle().getRadius()) {
				balls[i].setdy(-1);
				//balls[i].setYPos(balls[i].getCircle().getRadius());
			}
			
			if(balls[i].getCircle().getCenterY()+balls[i].getCircle().getTranslateY()+balls[i].getCircle().getRadius()>scene.getHeight()) {
				balls[i].setdy(-1);
				//balls[i].setYPos(scene.getWidth()-balls[i].getCircle().getRadius());
			}
			
//			if(balls[i].getYPos()<balls[i].getRadius() || 
//					balls[i].getYPos()>scene.getHeight()-balls[i].getRadius()) { //hits top or bottom
//				dy=-dy;
//			}
			
			balls[i].getCircle().setTranslateX(balls[i].getCircle().getTranslateX()+balls[i].getdx());
			balls[i].getCircle().setTranslateY(balls[i].getCircle().getTranslateY()+balls[i].getdy());
		}
		
		
	} //end checkWallBounce
	
	public void checkBallBounce() {
		double x1, y1, rad1, x2, y2, rad2;
		
		
		for(int i=0;i<balls.length;i++) {
			x1=balls[i].getCircle().getCenterX(); //can't just use getXPos, because need internal circle values, not outside values
			y1=balls[i].getCircle().getCenterY();
			rad1=balls[i].getCircle().getRadius();
			
			
			for(int j=0;j<balls.length;j++) {
				x2=balls[j].getCircle().getCenterX();
				y2=balls[j].getCircle().getCenterY();
				rad2=balls[j].getCircle().getRadius();
				if(i!=j) { //don't compare ball to itself
					
					
					double minDistanceSqd=Math.pow(rad1+rad2,2);
					double distanceSqd=Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2);

			          //quadratic formula time
			         // xI=((2*xJ)+sqrt(sq(2*xJ)-4*(sq(xJ)-minDistanceSqd))/(2));
			         // yI=((2*yJ)+sqrt(sq(2*yJ)-4*(sq(yJ)-minDistanceSqd))/(2));
					
					if(distanceSqd<minDistanceSqd) {
						balls[i].setdx(-1);
						balls[i].setdy(-1);
						
										
						
						balls[i].getCircle().setCenterX(((2*x2)+Math.sqrt(Math.pow(2*x2, 2)-4*(Math.pow(x2, 2)-minDistanceSqd)))/2);
						balls[i].getCircle().setCenterY(((2*y2)+Math.sqrt(Math.pow(2*y2, 2)-4*(Math.pow(y2, 2)-minDistanceSqd)))/2);
						
						balls[i].getCircle().setTranslateX(balls[i].getCircle().getTranslateX()+balls[i].getdx());
						balls[i].getCircle().setTranslateY(balls[i].getCircle().getTranslateY()+balls[i].getdy());
						
					}
					
				} //end if not same ball stmt
				
				
			} //end for j loop
		} //end for i loop
		
		
	} //end checkBallBounce

	public static void main(String[] args) {
		launch();

	}

}

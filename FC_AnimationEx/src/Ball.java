import javafx.scene.shape.Circle;

public class Ball {

	float xPos, yPos, dx, dy;
	final Circle circle;
	float radius=30;
	
	public Ball(int width, int height) {
		
		xPos=(float) (Math.random()*(width-2*this.radius)+this.radius);
		yPos=(float) (Math.random()*(height-2*this.radius)+this.radius);
		dx=-1.5f;
		dy=-1.5f;
		
		circle=new Circle(xPos,yPos,radius);
	}
	
	public float getXPos() {
		return this.xPos;
	}
	
	public void setXPos(double xVal) {
		this.xPos+=xVal;
	}
	
	public float getYPos() {
		return this.yPos;
	}
	
	public void setYPos(double yVal) {
		this.yPos+=yVal;
	}
	
	public float getRadius() {
		return this.radius;
	}
	
	public float getdx() {
		return this.dx;
	}
	
	public float getdy() {
		return this.dy;
	}
	
	public void setdx(int change) {
		this.dx=change*this.dx;
	}
	
	public void setdy(int change) {
		this.dy=change*this.dy;
	}
	
	public Circle getCircle() {
		return this.circle;
	}
}

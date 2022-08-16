/**
 * 
 */
package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Graph.Vertex;

/**
 * @author MC Mahlakwane, 217044163
 *	Used for drawing the Vertices
 *	Extends the Vertex<T> class
 * @param <T>
 */
public class childVertex<T extends Comparable<T>> extends Vertex<T> {

	Circle circle;
	double x;
	double y;
	
	/**
	 * @param value
	 */
	public childVertex(T value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param value
	 * @param weight
	 */
	public childVertex(T value, int weight) {
		super(value, weight);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param vertex
	 */
	public childVertex(Vertex<T> vertex) {
		super(vertex);
		// TODO Auto-generated constructor stub
	}
	
	public Circle drawVertex(Double x, Double y)
	{
		circle= new Circle(x, y, 30, Color.TRANSPARENT);
		circle.setStroke(Color.BLACK);
		return circle;
	}
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return circle
	 */
	public Circle getCircle()
	{
		return circle;
	}
}

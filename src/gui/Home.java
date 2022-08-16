/**
 * Packages and Imports
 */
package gui;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.Block;
import model.Graph;
import model.childVertex;
import model.Graph.Edge;
import model.Graph.Vertex;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Optional;

import gui.Arrow;
import gui.CommonStyle;
import gui.Toast;

/**
 * @author Mohau Mahlakwane, 217044163
 * Graphical User Interface Class
 */
public class Home extends BorderPane {

	 ArrayList<Block> blockchain = new ArrayList<Block>();
       int difficulty = 5;
	int count = 0;
	Block b = new Block();
	childVertex<String> vertex;
	childVertex<String> fromVertex;
	childVertex<String> toVertex;
	Edge<String> edge;
	
	Graph<String> graph;
	
	@FXML
	Arrow arrow;
	List<Arrow> arrArrow = new ArrayList<>();
	
	Pane pane;
	
	RadioButton addVertex;
	RadioButton addEdge;
	RadioButton removeVertex;
	RadioButton removeEdge;
	
	ToggleGroup toggleGroup;
	
	Button btnAddEdge;
	
	Label edgeweight;
	Label lbladdEdge;
	Label nodeCost;
	Label nodeName;
	List<Label> nodeLabels = new ArrayList<>();
	
	TextField fromVert;
	TextField toVert;
	TextField edgeWeight;;
	
	TextArea output;
	
	int numVertices = 0;
	int numEdges = 0;
	
	/**
	 * This constructor will Initialize everything on the GUI
	 */
	public Home()
	{
		
	    pane = new Pane();
	    pane.setStyle("-fx-background-color: white;");
	    this.setCenter(pane);
		
		graph = new Graph<>();
		VBox leftBox = new VBox();

		toggleGroup = new ToggleGroup();
		
		addVertex = new RadioButton("Add Vertex");
		addVertex.setToggleGroup(toggleGroup);
	    addVertex.setOnAction(e ->{
	    	addVertex();
	    });
	    leftBox.getChildren().add(addVertex);
	    
	    removeVertex = new RadioButton("Remove Vertex");
	    removeVertex.setToggleGroup(toggleGroup);
	    removeVertex.setDisable(true);
	    removeVertex.setOnAction(e ->{
	    	remVertex();
	    });
	    leftBox.getChildren().add(removeVertex);
	    
	    lbladdEdge = new Label("Add Edge");
	    lbladdEdge.setMaxWidth(Double.MAX_VALUE);
	    leftBox.getChildren().add(lbladdEdge);
	    
	    
	    addEdge = new RadioButton("Add Edge");
	    addEdge.setToggleGroup(toggleGroup);
	    addEdge.setDisable(true);
	    leftBox.getChildren().add(addEdge);
	    
	    fromVert = new TextField();
	    fromVert.setPromptText("From Vertex");
	    fromVert.setMaxWidth(Double.MAX_VALUE);
	    fromVert.setEditable(false);
	    leftBox.getChildren().add(fromVert);
	    
	    toVert = new TextField();
	    toVert.setPromptText("To Vertex");
	    toVert.setMaxWidth(Double.MAX_VALUE);
	    toVert.setEditable(false);
	    leftBox.getChildren().add(toVert);
	    
	    edgeWeight = new TextField();
	    edgeWeight.setPromptText("Edge Weight");
	    edgeWeight.setMaxWidth(Double.MAX_VALUE);
	    edgeWeight.setEditable(false);
	    leftBox.getChildren().add(edgeWeight);
	    
	    btnAddEdge = new Button("Add Edge");
	    btnAddEdge.setDisable(true);
	    btnAddEdge.setOnAction(e ->{
	    	addEdge();
	    });
	    leftBox.getChildren().add(btnAddEdge);
	    
	    output = new TextArea();
	    output.setPromptText("Your graph's additions and removals will be shown here");
	    output.resize(600.0, 800.0);
	    output.setEditable(false);
	    
	    this.setBottom(output);
	    
	    this.setLeft(leftBox);
	}
	
	/**
	 * This will add a Vertex on the GUI and graph
	 */
	private void addVertex()
	{
		if(this.addVertex.isSelected())
		{
			setNull();
			this.pane.setOnMouseClicked(e ->{
				    nodeName = new Label();
				    nodeName.setTextAlignment(TextAlignment.CENTER);
				    nodeName.setMouseTransparent(true);
				    
				    nodeCost = new Label();
				    nodeCost.setAlignment(Pos.CENTER);
				    nodeCost.setTextAlignment(TextAlignment.CENTER);
				    nodeCost.setMouseTransparent(true);
				    
					TextInputDialog dialog1 = new TextInputDialog("0");
                    dialog1.setTitle(null);
                    dialog1.setHeaderText("Enter The Client Name :");
                    dialog1.setContentText(null);
                    
                    Optional<String> result1 = dialog1.showAndWait();
                    if (result1.isPresent()) {
                    	nodeName.setText(result1.get());
                    	//create block 
                    	 b = new Block(count, b.currentHash, result1.get());
                    	b.mineBlock(difficulty);
                    	blockchain.add(b);
                    	System.out.println(b.toString());
                    	System.out.println("Current Chain Valid: " +Block.validateChain(blockchain));
                    	count++;
                    } else {
                    	nodeName.setText("");
                    }
                   
                    TextInputDialog dialog2 = new TextInputDialog("0");
                    dialog2.setTitle(null);
                    dialog2.setHeaderText("Enter The Cost Of The Resource :");
                    dialog2.setContentText(null);
                    
                    Optional<String> result2 = dialog2.showAndWait();
                    if (result2.isPresent()) {
                    	
                    	nodeCost.setText(result2.get());
                    } else {
                    	nodeCost.setText("");
                    }
                    try
                    {
                    	if(!nodeName.getText().contains("0") && !nodeCost.getText().equals("0"))
                    	{
	                    	vertex = new childVertex<>(nodeName.getText(),Integer.parseInt(nodeCost.getText()));
	                    	graph.getVertices().add(vertex);
							
							vertex.setX(e.getX());
							vertex.setY(e.getY());
							
							nodeName.setLayoutX(e.getX() - 10);
							nodeName.setLayoutY(e.getY());
							
							nodeCost.setLayoutX(e.getX() - 10);
							nodeCost.setLayoutY(e.getY() - 20);
							
							this.pane.getChildren().addAll(vertex.drawVertex(e.getX(), e.getY()), nodeName, nodeCost);
							nodeLabels.add(nodeName);
							nodeLabels.add(nodeCost);
							
							numVertices++;
							if(numVertices >= 2)
							{
								 addEdge.setDisable(false);
								 fromVert.setEditable(true);
								 toVert.setEditable(true);
								 edgeWeight.setEditable(true);
								 btnAddEdge.setDisable(false);
							}
							else
							{
								addEdge.setDisable(true);
								 fromVert.setEditable(false);
								 toVert.setEditable(false);
								 edgeWeight.setEditable(false);
								 btnAddEdge.setDisable(true);
							}
							if(numVertices >= 1)
							{
								removeVertex.setDisable(false);
							}
							else
							{
								removeVertex.setDisable(true);
							}
							output.appendText(b.toString() +"\n");
							output.appendText("Node Name: " + nodeName.getText() + "\t Node Cost: " + nodeCost.getText() + "\n");
							
                    	}
                    	else
                    	{
                    		String addedMsg = "Client Name or Cost doesn't Have A Value";
    						int time = 3500;
    						int fadeIn = 500;
    						int fadeOut = 500;
    						Toast.makeText(null, addedMsg, time, fadeIn, fadeOut);
                    	}
                    }
                    catch(NumberFormatException ex)
                    {
                    	String addedMsg = "Cost Must Be An Integer";
						int time = 3500;
						int fadeIn = 500;
						int fadeOut = 500;
						Toast.makeText(null, addedMsg, time, fadeIn, fadeOut);
						
                    }
			});
		}
	}
	
	/**
	 * This is for adding an Edge to the GUI and graph
	 */
	private void addEdge()
	{
		if(addEdge.isSelected())
		{
				arrow = new Arrow();
				int count = 0;
				if(!(fromVert.getText().isEmpty() || fromVert.getText().isEmpty()))
				{
					for(Vertex<String> v : graph.getVertices())
					{
						if(v != null)
						{
							childVertex<String> c1 = (childVertex<String>) v;
							
							if(fromVert.getText().equals(c1.getValue()))
							{
								fromVertex = c1;
								arrow.setStartX(c1.getCircle().getCenterX());
								arrow.setStartY(c1.getCircle().getCenterY());
							}
							
							if(toVert.getText().equals(c1.getValue()))
							{
								toVertex = c1;
								arrow.setEndX(c1.getCircle().getCenterX());
								arrow.setEndY(c1.getCircle().getCenterY());
							}
						}
						else
						{
							String addedMsg = "Vertex Was Not Found";
							int time = 3500;
							int fadeIn = 500;
							int fadeOut = 500;
							Toast.makeText(null, addedMsg, time, fadeIn, fadeOut);
						}
						count++;	
					}
				}
				else
				{
					String addedMsg = "Please Make Sure That The To and From TextBoxes are filled";
					int time = 3500;
					int fadeIn = 500;
					int fadeOut = 500;
					Toast.makeText(null, addedMsg, time, fadeIn, fadeOut);
					
				}
			    try
                {
				    if(count != 0 && count == graph.getVertices().size())
					{
				    	if(!edgeWeight.getText().isEmpty())
				    	{
				    		edgeweight = new Label();
				    		edgeweight.setLayoutX(((fromVertex.getX()) + (toVertex.getX()))/2);
							edgeweight.setLayoutY(((fromVertex.getY()) + (toVertex.getY()))/2);
							edge = new Edge<>(Integer.parseInt(edgeWeight.getText()), fromVertex, toVertex);
							for(Vertex<String> v : graph.getVertices())
							{
								if(fromVertex.equals(v))
								{
									v.addEdge(edge);
									arrArrow.add(arrow);
									output.appendText("Added\nEdge: " + v.getEdges().toString() + "\n");
									edgeweight.setText(edgeWeight.getText());
									pane.getChildren().add(arrow);
									pane.getChildren().add(edgeweight);
									nodeLabels.add(edgeweight);
									numEdges++;
								}
									
							}
							if(numEdges >= 1)
							{

							   removeEdge.setDisable(false);
							}
				    	}
					}
					else
					{
						String addedMsg = "Please Make Sure That The Weight TextBox is filled";
						int time = 3500;
						int fadeIn = 500;
						int fadeOut = 500;
						Toast.makeText(null, addedMsg, time, fadeIn, fadeOut);
						
					}	
					
                }
                catch(NumberFormatException ex)
                {
                	String addedMsg = "Weight Must Be An Integer";
					int time = 3500;
					int fadeIn = 500;
					int fadeOut = 500;
					Toast.makeText(null, addedMsg, time, fadeIn, fadeOut);
					
                }
			    catch(NullPointerException ex2)
			    {
			    
			    }
		}
				
	}

    
    /**
	 * This is for removing a vertex in the graph
	 */
	private void remVertex()
	{

		if(this.removeVertex.isSelected())
		{
			setNull();
			this.pane.setOnMouseClicked(e ->{
				try {
					for(Vertex<String> v : graph.getVertices())
					{
						childVertex<String> c1 = (childVertex<String>) v;
						Point2D p1 = new Point2D(e.getX(),e.getY());
						
						if(c1.getCircle().contains(p1))
						{
							Point2D arrPoint = new Point2D(c1.getX(),c1.getY());
							graph.getVertices().remove(c1);
							List<Edge<String>> edges = c1.getEdges();
							for(Edge<String> edge : edges)
							{
								graph.getEdges().remove(edge);
							}
							numVertices--;
							for(int i = 0; i < nodeLabels.size(); i++)
							{
								Label l = nodeLabels.get(i);
								if(c1.getCircle().contains(l.getLayoutX(), l.getLayoutY()))
								{
									this.pane.getChildren().remove(l);
								}
								
								
							}
							
							for(Arrow a : arrArrow)
							{
								if(a.contains(arrPoint))
								{
									this.pane.getChildren().remove(a);
									for(int i = 0; i < nodeLabels.size(); i++)
									{
										Label l = nodeLabels.get(i);
										if(a.contains(l.getLayoutX(), l.getLayoutY()))
										{
											this.pane.getChildren().remove(l);
										}
									}
								
								}
							}
							this.pane.getChildren().removeAll(c1.getCircle());

							output.appendText("Removed\nNode Name: " + c1.getValue() + "\tNode Cost: " + c1.getWeight() + " \tWith Edges: " + edges.toString() + "\n");
						}				
					}
				}
				catch(ConcurrentModificationException ex)
				{
					
				}
			});
		}
	}
	
	/**
	 * This is for setting the Mouse Events to null
	 */
	private void setNull()
    {
        this.pane.setOnMouseClicked(null);
        this.pane.setOnMousePressed(null);
        this.pane.setOnMouseDragged(null);
        this.pane.setOnMouseReleased(null);
    }
	
}

package test;
import classifier.*;
import junit.framework.TestCase;



public class TestClassifier extends TestCase {

	public TestClassifier(String arg0) {
		super(arg0);
	}

    private DecisionTree buildTree(){
		Node root = new Node("AC");
		
		Node n1=new Node("ABS");
		Node n2=new Node("ABS");
		root.addChild("yes",n1);
		root.addChild("no",n2);
		
		//expanded to 3 features
		Node f3=new Node("feature");
		Node f4=new Node("feature");
		Node f5=new Node("feature");
		Node f6=new Node("feature");
		n1.addChild("yes", f3);
		n1.addChild("no", f4);
		n2.addChild("yes", f5);
		n2.addChild("no", f6);
		
		// leaves
		Node l1 = new Node("high");
		Node l2 = new Node("medium");
		Node l3 = new Node("medium");
		Node l4 = new Node("low");
		
		// extra leaves for the expansion
		Node l5 = new Node("high");
		Node l6 = new Node("medium");
		Node l7 = new Node("medium");
		Node l8 = new Node("low");

		//child added to lowest level
		f3.addChild("yes",l1);
		f3.addChild("no",l2);

		f4.addChild("yes",l3);
		f4.addChild("no",l4);
		
		f5.addChild("yes",l5);
		f5.addChild("no",l6);

		f6.addChild("yes",l7);
		f6.addChild("no",l8);
		
		return new DecisionTree(root);
    }
	public void testCategory(){
		
		DecisionTree dt = buildTree();

		FeatureType yn = new FeatureType("YesNo"
						,new String[]{"yes","no"});

		Feature[] features = new Feature[]
		{ new Feature("AC","yes",yn),
		  new Feature("ABS","yes",yn),
		  new Feature("feature","yes",yn)
		};
		
		Item item = new Item("car",features);
		
		String category = dt.assignCategory(item);
		assertEquals("high",category);
		
		
		item.setFeatureValue("AC","no");
		category = dt.assignCategory(item);
	//	assertEquals("medium",category);
		
		item.setFeatureValue("feature", "no");
		category = dt.assignCategory(item);
	//	assertEquals("medium",category);

		item.setFeatureValue("ABS","no");
		category = dt.assignCategory(item);
		assertEquals("low",category);

	}
}

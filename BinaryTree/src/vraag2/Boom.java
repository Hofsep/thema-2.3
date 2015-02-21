package vraag2;

import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;

public class Boom{
	
	//All tree nodes
	static DefaultMutableTreeNode person = new DefaultMutableTreeNode("person");
	static DefaultMutableTreeNode employee = new DefaultMutableTreeNode("employee");
	static DefaultMutableTreeNode customer = new DefaultMutableTreeNode("customer");
	static DefaultMutableTreeNode sales_rep = new DefaultMutableTreeNode("sales_rep");
	static DefaultMutableTreeNode engineer = new DefaultMutableTreeNode("engineer");
	static DefaultMutableTreeNode us_customer = new DefaultMutableTreeNode("us_customer");
	static DefaultMutableTreeNode non_us_customer = new DefaultMutableTreeNode("non_us_customer");
	static DefaultMutableTreeNode local_customers = new DefaultMutableTreeNode("local_customers");
	static DefaultMutableTreeNode regional_customers = new DefaultMutableTreeNode("regional_customers");
	
	
	//Enumerations with warning supression
	@SuppressWarnings("rawtypes")
	static Enumeration breadth = person.breadthFirstEnumeration();
	@SuppressWarnings("rawtypes")
	static Enumeration preorder = person.preorderEnumeration();
	@SuppressWarnings("rawtypes")
	static Enumeration postorder = person.postorderEnumeration();
	
	public static void main(String[] args)
	{
		//Build up the tree
		person.add(employee);person.add(customer);
		employee.add(sales_rep);employee.add(engineer);
		customer.add(us_customer);customer.add(non_us_customer);
		us_customer.add(local_customers);us_customer.add(regional_customers);
		
		//Print out Breadth-First
		System.out.println("<<Breadth-First:>>");
		while(breadth.hasMoreElements())
		{
			System.out.println(breadth.nextElement());
		}
		System.out.println("");
		
		//Print out Pre-Order
		System.out.println("<<Pre-Order:>>");
		while(preorder.hasMoreElements())
		{
			System.out.println(preorder.nextElement());
		}
		System.out.println("");
		
		//Print out Post-Order
		System.out.println("<<Post-Order:>>");
		while(postorder.hasMoreElements())
		{
			System.out.println(postorder.nextElement());
		}
	}
}

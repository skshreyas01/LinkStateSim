

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Algorithm 
{
	public int[][] topology_matrix;
	private int[] shortest_distance;  
	private int[] node_prev; 
	private int end;
	private int start;
	final String newline = "\n";
	JTextArea area;  
	JFrame f;  

	public void shortest_path(int start_node, int end_node, int[][]toplogy_mat) 
	{
		topology_matrix = toplogy_mat;
		int len = topology_matrix.length;			
		shortest_distance=  new int[len];
		node_prev=  new int[len];
		start = start_node-1;
		end=end_node-1;

		
		Set<Integer> nodes_unvisited = new HashSet<>();

		 
		for(int i=0;i<len;i++)
		{
			nodes_unvisited.add(i);
			if(i != start)
				shortest_distance[i] = 999;
		}

		node_prev[start] = start;

		while(!nodes_unvisited.isEmpty())
		{
			int node_current = get_next_node(shortest_distance,nodes_unvisited); 
			if(node_current==-1)
				break;
			nodes_unvisited.remove(node_current);
			for(int i=0; i<len; i++)
			{
				if(nodes_unvisited.contains(i) && topology_matrix[node_current][i] > 0)
				{
					int new_dist = shortest_distance[node_current]+topology_matrix[node_current][i];

					if(new_dist < shortest_distance[i])
					{
						shortest_distance[i] = new_dist;
						node_prev[i] = node_current;
					}
				}
			}
		}
	}
	
	public void shortest_path_display() 
	{
		 f=new JFrame();  
		 f.setTitle("Shortest Path");
		// Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
		 area=new JTextArea(400,150);  
		 area.setFont(new Font("Monospaced", Font.BOLD, 20));
		// area.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
    	 area.setEditable(false);
		 area.setBounds(10,30,400,150);  
		
		int start_node = start+1, end_node=end+1;
		area.append("\n\tShortest distance from Router " + start_node +" to Router " +end_node);
		area.append(newline);
		area.append("\tCost: " + shortest_distance[end]);
		area.append(newline);
		area.append("\tPath: ");
		//System.out.println("\nShortest distance from Router " + start_node +" to Router " +end_node + " is : " + shortest_distance[end]);
		
		
		int x=end;
		String path=Integer.toString(end_node);
		while(true)
		{
			if(node_prev[x]==x)
				break;

			path +=" >- "+(node_prev[x]+1);
			x = node_prev[x];
		}


		for(int i=path.length()-1;i>=0;i--)
		{
			area.append(path.charAt(i)+"");
			//System.out.print(path.charAt(i));
		}
		
		f.add(area); 
	      
	    f.setSize(700,250);  
	    f.setLayout(new BorderLayout());
	    f.getContentPane().add(area);
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
		
		
	}

	private int get_next_node(int[] shortest_distance, Set<Integer> order_nodes) {

		int min = 999;
		int new_node = -1;
		for(int x: order_nodes)
		{
			if(shortest_distance[x] < min)
			{
				new_node = x;
				min = shortest_distance[x];
			}
		}
		return new_node;
	}

	public void build_conn_table(int start_node, int[][]toplogy_mat)
	{
		topology_matrix = toplogy_mat;
		
		    f=new JFrame();  
		    f.setTitle("Connection Table");
		   
		          
		    area=new JTextArea(300,500);  
		    area.setFont(new Font("Monospaced",Font.BOLD,20)); 
		    area.setEditable(false);
		    area.setBounds(10,30,460,500);  
		      
		    area.setText("\n\tConnection Table for Router # " + start_node + "\n\tDestination\tInterface\n");      
		    //System.out.println("\n \t\t\t  Connection Table for Router # " + start_node + " ");
		    area.append(newline);
		    
		
		int max = 999;
		if(topology_matrix.length != start_node)
			shortest_path(start_node, topology_matrix.length,toplogy_mat);
		else
			shortest_path(start_node, topology_matrix.length-1,toplogy_mat);
		
		for(int i=0;i<topology_matrix.length;i++)
		{
			if(i == start_node-1)
			{
				area.append("\t\t"+start_node + "\t -\n");
				area.append(newline);
				//System.out.println(start_node + "\t -");
			}
			else
			{
				int prev = i;
				while(true)
				{
					if(node_prev[prev] == prev || node_prev[prev] == start_node-1)
						break;
					else
						prev = node_prev[prev];
				}
				if(shortest_distance[i] != max)
				{
				//	area.append(newline);
					area.append("\t\t"+(i+1)+"\t "+(prev+1));
					area.append(newline);
					//System.out.println((i+1)+"\t "+(prev+1));
				}	
				else
					//area.append(newline);
					area.append("\t\t"+(i+1)+"\t --");
				    area.append(newline);
					//System.out.println((i+1)+"\t --");
			}
		}
		
		
		f.add(area); 
	      
	    f.setSize(700,500);  
	    f.setLayout(new BorderLayout());
	    f.getContentPane().add(area);
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
	}

	
	
	public void Print_Topology(int [][]topology){
		
		
		 f=new JFrame();  
		 f.setTitle("Topology");
         
		    area=new JTextArea(500,300);  
		    area.setFont(new Font("Monospaced",Font.BOLD,20));
		    area.setEditable(false);
		    area.setBounds(10,30,5000,400);  
		
		area.append("\t");
		for (int[] row : topology) {
            for (int col : row) {
            	area.append(col+"\t");
                //System.out.printf(col+"\t");
            }
            
            area.append(newline);
            area.append("\t");
            //System.out.println();
        }
		
		f.add(area); 
	      
	    f.setSize(550,400);  
	    f.setLayout(new BorderLayout());
	    f.getContentPane().add(area);
	    //f.pack();
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);

		
	    
	}
	
	
	public void Print_del(int [][]topology){
		
		
		 f=new JFrame();  
		 f.setTitle("Deletion");
        
		    area=new JTextArea(500,300);  
		    area.setFont(new Font("Monospaced",Font.BOLD,20));
		    area.setEditable(false);
		    area.setBounds(10,30,5000,400);  
		
		    area.append("\tTopology after Deletion:");
		    area.append(newline);
     		area.append("\t");
		for (int[] row : topology) {
           for (int col : row) {
           	area.append(col+"\t");
               //System.out.printf(col+"\t");
           }
           
           area.append(newline);
           area.append("\t");
           //System.out.println();
       }
		
		f.add(area); 
	      
	    f.setSize(550,400);  
	    f.setLayout(new BorderLayout());
	    f.getContentPane().add(area);
	    //f.pack();
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);

		
	}
	
	
	
	
	
	
	public void Print_add(int [][]topology){
		
		
		 f=new JFrame();  
		 f.setTitle("Addition");
       
		    area=new JTextArea(500,300);  
		    area.setFont(new Font("Monospaced",Font.BOLD,20));
		    area.setEditable(false);
		    area.setBounds(10,30,5000,400);  
		
		    area.append("\tTopology after Addition of router :"+(topology.length));
		    area.append(newline);
    		area.append("\t");
		for (int[] row : topology) {
          for (int col : row) {
          	area.append(col+"\t");
              //System.out.printf(col+"\t");
          }
          
          area.append(newline);
          area.append("\t");
          //System.out.println();
      }
		
		f.add(area); 
	      
	    f.setSize(600,400);  
	    f.setLayout(new BorderLayout());
	    f.getContentPane().add(area);
	    //f.pack();
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int[][] router_delete(int src_router,int[][] before_del) 
	{
		//int[][] after_del = new int[before_del.length][before_del.length];
		int value=src_router-1;
		
		for(int i=0;i<before_del.length;i++){
			before_del[value][i]=-1;
		}
		for(int j=0;j<before_del.length;j++){
			before_del[j][value] = -1;
		}
		
		
		
		/*int p=0;
		for(int i=0;i<before_del.length;i++)
		{
			if ( i == value) 
				continue;

			int q = 0;
			for( int j = 0; j <before_del.length; ++j) 
			{
				if ( j == value)
					continue;

				after_del[p][q] = before_del[i][j];
				q++;
			}

			p++;
		}*/
		return before_del;
	}
	
	
	public int [][] router_add(int [][]before_add){
		
		Scanner ip= new Scanner(System.in);
		int [][]after_add=new int[before_add.length+1][before_add.length+1];
		for(int i=0;i<before_add.length;i++)
		{
			for( int j = 0; j <before_add.length; ++j)
			{
				after_add[i][j]=before_add[i][j];
			}
		}

		System.out.print("\n\t\t\tEnter weight from other routers to new router");
		for( int i = 0; i <before_add.length+1; i++)
		{
			for(int j=before_add.length;j<before_add.length+1;j++) 
			{
				after_add[i][j]=after_add[j][i]=ip.nextInt();
			}
		}
		
		
		return after_add;
		
	}

	
	
	
}


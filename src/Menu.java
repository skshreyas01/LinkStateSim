

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Menu{

public static void main(String[] args) {
    
	Algorithm algorithm=new Algorithm();
	String fname = new String();
	int[][] toplogy_arr = null;
	
	
	//Reading entire file
	Scanner sc = new Scanner(System.in);
	
	try{
	fname =  JOptionPane.showInputDialog(null, "Enter the input file name: ", "Input\\test.txt");
    List<String> complete_file = Files.readAllLines(Paths.get(fname));
    
    for (int i = complete_file.size() - 1; i >= 0; i--) {
        if (complete_file.get(i).isEmpty()) {
            complete_file.remove(i);
        }
    }

   toplogy_arr = new int[complete_file.size()][];

    for (int i = 0; i < complete_file.size(); i++) {
        String[] split_arr = complete_file.get(i).split("\\s");

        toplogy_arr[i] = new int[split_arr.length]; 
        for (int j = 0; j < split_arr.length; j++) {
            toplogy_arr[i][j] = Integer.parseInt(split_arr[j]);
        }
    }

	}
	catch(IOException e){
			System.out.println("File Not found Error!");	
			System.out.println("Exiting");
			System.exit(0);
	}
	catch(NullPointerException e){
		System.out.println("Exiting");
		System.exit(0);
	}
	
    int choice;
    int src_router;
    int dest_router;
    
    
    
    try{
        while(true)
        {
        	
        	 	System.out.println("\t\t\tLink State Routing Menu");
        	    System.out.println("1.Display Current Topology\n2.Build Connection Table\n3.Determine Shortest Path\n4.Delete Router\n5.Add Router\n6.Exit ");
        	    System.out.println("Enter choice:");
        	    choice=sc.nextInt();
        	    
        	    switch (choice) {
        	    
        	    case 1:
        	    	algorithm.Print_Topology(toplogy_arr);
        	    	break;
        	    
        	    
    			case 2:
    		    	 System.out.println("Enter router number:");
    		    	 src_router=sc.nextInt();
    		    	 System.out.println("Router number to build connection table "+ src_router);
    		    	 algorithm.build_conn_table(src_router, toplogy_arr);
    				break;
    				
    			case 3:
    				System.out.println("Shortest Path");
    				System.out.println("Enter source router:");
    				src_router = sc.nextInt();
    				System.out.println("Enter destination router:");
    				dest_router = sc.nextInt();
    				algorithm.shortest_path(src_router, dest_router, toplogy_arr);
    				algorithm.shortest_path_display();
    				break;
    				
    			case 4:
    				//int after_del[][]=new int[toplogy_arr.length][];
    				System.out.println("Router Deletion");
    				System.out.println("Enter router to be deleted");
    				int del = sc.nextInt();
    				toplogy_arr = algorithm.router_delete(del, toplogy_arr);
    				System.out.println("Toplogy after Deletion");
    				
    				algorithm.Print_del(toplogy_arr);
    				
    				
    				break;
    	
    			case 5:
    				int after_add[][] = new int[toplogy_arr.length][];
    				System.out.println("Router Addition");
    				System.out.println("Router will be added at position: "+(toplogy_arr.length+1));
    				toplogy_arr = algorithm.router_add(toplogy_arr);
    				algorithm.Print_add(toplogy_arr);
    				break;
    				
    			case 6:
    				JOptionPane.showMessageDialog(null, "Exiting");
    				System.out.println("\t\t\tTerminated");
    				System.exit(0);
    			default:
    				break;
    			}
        	    
        	
        }
    	
    }
    catch(ArrayIndexOutOfBoundsException e){
    	System.out.println("No Such Element found");
    	System.out.println("Exiting");
    	System.exit(0);
    }

   
    
    
}

}
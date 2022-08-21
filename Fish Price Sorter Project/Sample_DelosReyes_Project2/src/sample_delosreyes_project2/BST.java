/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample_delosreyes_project2;

import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Bezalel
 */



public class BST {
    
    class Node{     //CLASS NODE
        String fishname, price, quantity, ownername, gender, pond, numoffarmers, sourcewater, msystem, csystem, fertilization;
        int height;
        Node left, right;
        
        public Node (String fishname,String price,String quantity,String ownername,String gender, 
                    String pond,String numoffarmers,String sourcewater, String msystem,String csystem,String fertilization){    //CONSTRUCTOR OF NODE CLASS
            this.fishname = fishname;
            this.price = price;
            this.quantity = quantity;
            this.ownername = ownername;
            this.gender = gender;
            this.pond = pond;
            this.numoffarmers = numoffarmers;
            this.sourcewater = sourcewater;
            this.msystem = msystem;
            this.csystem = csystem;
            this.fertilization = fertilization;

            left = right = null;
        }
    }
    
    // Root of BST
        Node root;

        // Constructor
        
        BST()
        {
        root = null;
        }
        
    // A utility function to get the height of the tree 
        
    int height(Node N) { 
        if (N == null) 
            return 0; 
  
        return N.height; 
    } 
  
    // A utility function to get maximum of two integers 
    
    int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 

    public String minValue(Node root)
    {
        String minvalue = root.price;
        while (root.left != null)
    {
        minvalue = root.left.price;
        root = root.left;
     }
     return minvalue;
    }
    
    //INSERT FUNCTION
    Node insert(Node root, String fishname,String price,String quantity,String ownername,String gender, 
                    String pond,String numoffarmers,String sourcewater, String msystem,String csystem,String fertilization){
        
        //INSERTS A VALUE IF THE ROOT IS NULL
        if( root == null){
            return new Node ( fishname, price, quantity, ownername, gender, 
                     pond, numoffarmers, sourcewater,  msystem, csystem, fertilization);
        }
        
        //TRAVERSE FROM LEFT IF THE VALUE IS LESSER THAN THE ROOT NODE
        if (price.compareTo(root.price)<0){
        root.left = insert(root.left,  fishname, price, quantity, ownername, gender, 
                     pond, numoffarmers, sourcewater,  msystem, csystem, fertilization);
        
        //TRAVERSE FROM RIGHT IF THE VALUE IS LESSER THAN THE ROOT NODE
        }else if (price.compareTo(root.price)>0){
        root.right = insert(root.right,  fishname, price, quantity, ownername, gender, 
                     pond, numoffarmers, sourcewater,  msystem, csystem, fertilization);
        
        }else{
        return root;
        }
        return root;
    }
    
    //SEARCH FUNCTION
    Node search(String price){                       
        
        Node current=root;
        
        
        //EXECUTE BELOW IF THE ROOT IS NOT NULL
        while(current!=null){
        //RETURN THE ROOT IF THE PRICE IS EQUALS TO THE CURRENT PRICE NODE
        if(current.price.equals(price)){
            return current;
        }
        //TRAVERSE TO RIGHT IF THE PRICE IS LESSER THAN THE CURRENT PRICE NODE
        if(price.compareTo(current.price)>0){
            current=current.right;  
            
         //TRAVERSE TO RIGHT IF THE PRICE IS LESSER THAN THE CURRENT PRICE NODE
        }else if(price.compareTo(current.price)<0){
            current=current.left;                              
        }
        }
        return null;                                            // return -1 if no element found
    }
    
    
    //DELETE FUNCTION
    Node delete(Node node, String key){
        // STEP 1: PERFORM STANDARD BST DELETE  
        if (node == null)  
            return node;  
  
        // If the key to be deleted is smaller than  
        // the root's key, then it lies in left subtree  
        if (key.compareTo(node.price)<0)  
            node.left = delete(node.left, key);  
  
        // If the key to be deleted is greater than the  
        // root's key, then it lies in right subtree  
        else if (key.compareTo(node.price)>0)  
            node.right = delete(node.right, key);  
  
        // if key is same as root's key, then this is the node  
        // to be deleted  
        else{
            // node with only one child or no child  
            if ((node.left == null) || (node.right == null))  
            {  
                Node temp = null;  
                if (temp == node.left)  
                    temp = node.right;  
                else
                    temp = node.left;  
  
                // No child case  
                if (temp == null)  
                {  
                    temp = node;  
                    node = null;  
                }  
                else // One child case  
                    node = temp; // Copy the contents of  
                                // the non-empty child  
            }  
            else
            {  
                // node with two children: Get the inorder  
                // successor (smallest in the right subtree)  
                String temp = minValue(node.right);  
  
                // Copy the inorder successor's data to this node  
                node.price = temp;  
  
                // Delete the inorder successor  
                node.right = delete(node.right, temp);  
            }}  
  
        // If the tree had only one node then return  
        if (node == null)  
            return node;  
        return node;    
    }
    
    
    //SAVE FUNCTION
    public void Save(){
        
        JFileChooser file = new JFileChooser();
        file.setDialogTitle("Specify a file to save");
        int userSelection = file.showSaveDialog(null);
        
        if(userSelection==JFileChooser.APPROVE_OPTION){
        File saveFile = file.getSelectedFile();
            //TRY CATCH FUNCTION
            try{
                
                //INITIALIZE A FILE WRITER
                FileWriter filewrite = new FileWriter(file.getSelectedFile());
                BufferedWriter bufferwrite = new BufferedWriter(filewrite);
                
                Node current, previous; 
                current = root; 
                
                
                //BELOW ARE THE CONDITIONS TO ADD ALL THE VALUES INSIDE A CERTAIN FILE PATH
                while (current != null) { 
                    if (current.left == null) {
                        bufferwrite.write(current.fishname+","+current.price+","+current.quantity+","+
                                current.ownername+","+current.gender+","+current.pond+","+current.numoffarmers+","+
                                current.sourcewater+","+current.msystem+","+current.csystem+","+
                                current.fertilization);
                        bufferwrite.newLine();
                        current = current.right; 
                    } 
                    else { 
                    // Find the inorder predecessor of current 
                        previous = current.left; 
                    while (previous.right != null && previous.right != current) 
                        previous = previous.right; 
  
                    // Make current as right child of its inorder predecessor 
                    if (previous.right == null) { 
                        previous.right = current; 
                        current = current.left; 
                    } 
  
                    // Revert the changes made in the 'if' part to restore the  
                    // original tree i.e., fix the right child of predecessor
                    else { 
                        previous.right = null; 
                        bufferwrite.write(current.fishname+","+current.price+","+current.quantity+","+
                                current.ownername+","+current.gender+","+current.pond+","+current.numoffarmers+","+
                                current.sourcewater+","+current.msystem+","+current.csystem+","+
                                current.fertilization);
                        bufferwrite.newLine();
                        current = current.right;  
                    }
                }
            }   
                bufferwrite.close();
                filewrite.close();
                JOptionPane.showMessageDialog(null,"Data has been saved","Saving", WIDTH);
                
            }catch(IOException e){
                System.out.println(e);
            }
            System.out.println("Save as file: "+ saveFile.getAbsolutePath());
        }
    }
    
    // LOAD FUNCTION
    
    public void Open(){
        JFileChooser file = new JFileChooser();
        file.setDialogTitle("Specify a file to open");
        int userSelection = file.showOpenDialog(null);
        
        if(userSelection==JFileChooser.APPROVE_OPTION){
            File openFile = file.getSelectedFile();
            
            //TRY CATCH FUNCTION
            try{
                
                 //INITIALIZE A FILE READER
                FileReader fileread;
                fileread = new FileReader(openFile);
                BufferedReader bufferread = new BufferedReader(fileread);
                
                String line = bufferread.readLine();
                
                //BELOW ARE THE CONDITIONS TO READ THE VALUES INSIDE THE FILE
                while(line != null){
                //LINE 284 MEANS TO SPLIT THE VALUES AND ITS INDICATOR IS ","
                String[] info = line.split(",");
                
                //INSERTS THE VALUES 
                root = insert(root, info[0], info[1], info[2], info[3], info[4],info[5],
                                 info[6], info[7], info[8], info[9], info[10]);
                line = bufferread.readLine();
                }
                
                JOptionPane.showMessageDialog(null,"File has been loaded","", WIDTH);
                fileread.close();
                bufferread.close();
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }
}

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Scans the csv file
 * 
 */
public class scanRecords
{
   ItemRecord[] recordArray; 
    
   public scanRecords(){
       
    Scanner scan = null;
    recordArray = new ItemRecord[10]; 
    BufferedReader br = null;
    String InputLine = "";
    String salesfileLoc;
    String splitBy = ",";
    int row = 0;
    int x = 0;
    int rowsInFile = 0;
    
    salesfileLoc = " file name goes here";
    
   try
   {
       //setup a scanner
       br = new BufferedReader(new FileReader(salesfileLoc));
       scan = new Scanner(br);
       
       //need some way to find out how big to make the recordArray.  When  I try the while loop 
       //that's commented out the object is not created later on.
       
       //while(br.readLine() != null){
         // rowsInFile++;
         //row++;
        //}
        
        
        
       //while the CSV file has lines,
       while (scan.hasNextLine())
       //for(int i = 0; i < rowsInFile; i++)
       {
           //Read line in from file
           InputLine = scan.nextLine();
          
           //split the Inputline into an array at the commas
           String[] InArray = InputLine.split(splitBy);
           
          //Create a new itemRecord    
           ItemRecord itemRecord = new ItemRecord();
           itemRecord.setSKU(InArray[0]); 
           itemRecord.setName(InArray[1]);
           itemRecord.setDescription(InArray[2]);
           //etc....copy the contents into each ItemRecord object.
          
          
           //Then add to Array
           recordArray[x] = itemRecord;
           
          x++;
        }
        
        for(int j = 0; j < recordArray.length; j++)
        {
        System.out.println(recordArray[j]);
       }
    }
    catch (Exception e)
    {
        System.out.println(e);
    }
  }
  
  public ItemRecord[] getRecords(){
     return recordArray; 
    }
  
 
  
}

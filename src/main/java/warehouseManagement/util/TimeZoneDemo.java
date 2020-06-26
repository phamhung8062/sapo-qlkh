package warehouseManagement.util;


import java.util.*;

public class TimeZoneDemo {
   public static void main( String args[] ) {       
//
//      // getting available Ids for offset
//      String[] availId = TimeZone.getAvailableIDs();
//
//      // checking available Ids for offset
//      System.out.println("Available Ids for offset are: ");
//
//      for (int i = 0; i<availId.length; i++) {
//         System.out.println(availId[i]);
//      }
//      Collection listOne = new ArrayList(Arrays.asList(1,2,3,4,5,6,7));
      Collection listOne = new ArrayList();
      listOne.add(1);
      listOne.add(2);
      Collection listTwo = new ArrayList(Arrays.asList(2,3));
      listOne.retainAll( listTwo );
      listTwo.containsAll( listOne );

      System.out.println( listOne );
      System.out.println( listTwo );
   }    
}
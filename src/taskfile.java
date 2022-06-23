import java.io.FileInputStream;
import java.util.*;

public class taskfile {

    public static void main(String[] args) {

        //InputStream for Files
        FileInputStream inputStream = null;
        Scanner my_scanner = null;
        //Map > Key: String, Value: List of Integers [Total_Runtime,Min,Max,SD]
        Map<String,List<Integer>> sysMap = new HashMap<>();

        try {
            inputStream = new FileInputStream("./files/CODETEST.CSV");
            my_scanner = new Scanner(inputStream, "UTF-8");
            while (my_scanner.hasNextLine()) {
                String line = my_scanner.nextLine();
                String[] lineparts = line.split(",");

                if (sysMap.containsKey(lineparts[1])){
                    List<Integer> list = new ArrayList<Integer>();
                    list = sysMap.get(lineparts[1]);

                    //Updating the total:
                    list.set(0, list.get(0) + Integer.valueOf(lineparts[2]));

                    //Updating the Minimum
                    if(Integer.valueOf(lineparts[2]) < list.get(1)){
                        list.set(1, Integer.valueOf(lineparts[2]));
                    }
                    //Updating the Maximum
                    if (Integer.valueOf(lineparts[2]) > list.get(2)) {
                        list.set(2, Integer.valueOf(lineparts[2]));
                    }
                    //Updating the item count:
                    int count = list.get(3) + 1;
                    list.set(3, count);

                }
                else{

                    //List content: 0:total 1:min 2:max 3:count 4:current mean 5:current variance
                    List<Integer> list = new ArrayList<Integer>();
                    //0: Adding the run time
                    list.add(Integer.valueOf(lineparts[2]));
                    //1: Adding the Min exec time
                    list.add(Integer.valueOf(lineparts[2]));
                    //2: dding the Max exec time
                    list.add(Integer.valueOf(lineparts[2]));
                    //3: Count of number of items:
                    list.add(1);
//                  //4: Adding the current mean
                    list.add(Integer.valueOf(lineparts[2]));
                    //5: Adding the current variance
                    list.add(0);
                    sysMap.put(lineparts[1],list);
                }
       }
            // Print: Value(1):Minimum Execution time | Value(2):Maximum Execution time | Value(0):/Value(3):Average Execution time | Standard Deviation
            sysMap.forEach((key,value) -> System.out.println("Task Name: " + key + "\nThe minimum execution time: " + value.get(1) + " millis \nThe maximum execution time: " +value.get(2) +" millis\nThe average execution time: "+ (Float.valueOf(value.get(0))/Float.valueOf(value.get(3))) + " millis \n"));



//            //Because scanners suppress exceptions it seems
//            if (my_scanner.ioException() != null) {
//                throw my_scanner.ioException();
//            }
        } catch (Exception e) {
            System.out.println("Exception caught " + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (my_scanner != null) {
                    my_scanner.close();
                }
            }
            catch(Exception e) {
                System.out.println("File close exception caught");

            }
        }

    }
}


//    long start = System.nanoTime();
//        long time = System.nanoTime() - start;
//        System.out.printf("Took %.3f seconds%n", time/1e9);

//Current path:
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
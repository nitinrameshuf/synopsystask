import java.io.FileInputStream;
import java.util.*;

public class taskfile_sd_twopass {


    public static void main(String[] args) {

        //InputStream for Files
        FileInputStream inputStream = null;
        Scanner my_scanner = null;
        //Map > Key: String, Value: List of Integers [Total_Runtime,Min,Max,SD]
        Map<String,List<Float>> sysMap = new HashMap<>();

        try {
            inputStream = new FileInputStream("./files/CODETEST.CSV");
            my_scanner = new Scanner(inputStream, "UTF-8");
            while (my_scanner.hasNextLine()) {
                String line = my_scanner.nextLine();
                String[] lineparts = line.split(",");

                if (sysMap.containsKey(lineparts[1])){
                    List<Float> list = new ArrayList<Float>();
                    list = sysMap.get(lineparts[1]);

                    //0: Updating the total:
                    list.set(0, list.get(0) + Float.valueOf(lineparts[2]));

                    //1: Updating the Minimum
                    if(Float.valueOf(lineparts[2]) < list.get(1)){
                        list.set(1, Float.valueOf(lineparts[2]));
                    }
                    //2: Updating the Maximum
                    if (Float.valueOf(lineparts[2]) > list.get(2)) {
                        list.set(2, Float.valueOf(lineparts[2]));
                    }
                    //3: Updating the item count:
                    float count = list.get(3) + 1;
                    list.set(3, count);

                }
                else{

                    //List content: 0:total 1:min 2:max 3:count 4:current mean 5:Sum of squares of differences
                    List<Float> list = new ArrayList<Float>();
                    //0: Adding the run time
                    list.add(Float.valueOf(lineparts[2]));
                    //1: Adding the Min exec time
                    list.add(Float.valueOf(lineparts[2]));
                    //2: dding the Max exec time
                    list.add(Float.valueOf(lineparts[2]));
                    //3: Count of number of items:
                    list.add(1F);
                    //4: Standard deviation
                    list.add(0F);
//
                    // Adding to the list
                    sysMap.put(lineparts[1],list);
                }
            }




            // Print: Value(1):Minimum Execution time | Value(2):Maximum Execution time | Value(0):/Value(3):Average Execution time | Standard Deviation
            sysMap.forEach((key,value) ->
                    System.out.println("Task Name: " + key + "\nThe minimum execution time: " + value.get(1) + " millis \nThe maximum execution time: " +value.get(2) +" millis\nThe average execution time: "+ value.get(0)/value.get(3) + " millis.\nThe standard deviation is: " + Math.sqrt((value.get(5)/(value.get(3)-1))) +"\n"));

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
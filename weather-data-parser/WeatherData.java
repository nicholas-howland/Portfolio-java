import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class weatherData{
    public static void main(String args[]) throws FileNotFoundException {
// print a nice opening message
        System.out.println("Printing weather data...");
// open the file
        File data = new File("./4343456.csv");
        Scanner fscaner = new Scanner(data);

// set up vars
        String line = fscaner.nextLine();
        String[] val = line.split(",");   
        String startDay="";
        String endDay="";

        int avgLow=0;
        int avgHigh=0;
        int dayHigh=0;
        int dayLow=0;
        int missingData=0;

// Column Title Discovery
        for( int i=0; i < 35; i++) {
            System.out.println("Index of "+i+" : "+val[i]);
        }
//*/
///*
        int lineNumber = 1;
        while(fscaner.hasNextLine()){
            line = fscaner.nextLine();
            val = line.split(",");
            String station = val[0];
            String lat = val[2];
            String lon = val[3];
            String elev= val[4];
            String precip= val[7];
            String tempLow = val[21].replaceAll("\"","");
            String tempHigh= val[17].replaceAll("\"","");
            if(tempHigh==""){
                dayHigh=avgHigh/(lineNumber%7);
                missingData=missingData+1;
            } else {
                dayHigh=Integer.valueOf(tempHigh);
            }
            if(tempLow==""){
                dayLow=avgLow/(lineNumber%7);            
                missingData=missingData+1;
            } else {
                dayLow=Integer.valueOf(tempLow);
            }

            avgLow=avgLow+dayLow;
            avgHigh=avgHigh+dayHigh;

            lineNumber++;
            if(lineNumber%7==0){
//                System.out.println("Line : "+lineNumber);
                System.out.println("7 day average for "+startDay+" to "+endDay+" the average highs and lows were "+avgHigh/7+" / "+avgLow/7);
                if(missingData>0){
                    System.out.println("Missing datapoints: "+missingData);
                }
                avgLow=0;
                avgHigh=0;

                missingData=0;

                endDay=startDay;
                startDay=val[6].replaceAll("\"","");
            }
        }   

    }
}

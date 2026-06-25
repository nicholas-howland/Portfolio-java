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
        int lineNumber = 1;

// date variubles
        String startDay="";
        String endDay="";

// CSV data variubles
        String station;
        String lat;
        String lon;
        String elev;
        String precip;
        String tempLow;
        String tempHigh;

// temperature variubles
        int avgLow=0;
        int avgHigh=0;
        int dayHigh=0;
        int dayLow=0;
        int missingData=0;

// Column Title Discovery
        for( int i=0; i < 35; i++) {
            System.out.println("Index of "+i+" : "+val[i]);
        }

// traverse line by line throuhg the file
        while(fscaner.hasNextLine()){

// set the line to be the text from the file
            line = fscaner.nextLine();
// split the line using commas, gotta love standards
            val = line.split(",");
            lineNumber++;
// get data from the line number
            station = val[0];
            lat = val[2];
            lon = val[3];
            elev= val[4];
            precip= val[7];
// remove the " so the temperature values can be transformed properly
            tempLow = val[21].replaceAll("\"","");
            tempHigh= val[17].replaceAll("\"","");

// handle and track missing data for highs and lows
            if(tempHigh==""){
            // use the averages from the days prior to that day to get an aproximaion
                dayHigh=avgHigh/(lineNumber%7);
                missingData=missingData+1;
            } else {
                dayHigh=Integer.valueOf(tempHigh);
            }
            if(tempLow==""){
            // use the averages from the days prior to that day to get an aproximaion
                dayLow=avgLow/(lineNumber%7);            
                missingData=missingData+1;
            } else {
                dayLow=Integer.valueOf(tempLow);
            }

            avgLow=avgLow+dayLow;
            avgHigh=avgHigh+dayHigh;


            if(lineNumber%7==0){
//                System.out.println("Line : "+lineNumber);
                // print out the seven day averages
                System.out.println("7 day average for "+startDay+" to "+endDay+" the average highs and lows were "+avgHigh/7+" / "+avgLow/7);
                // print out a warning about missing data.
                if(missingData>0){
                    System.out.println("# Averages may not be accurate, they were calculated based off of the previous days average");
                    System.out.println("# Number of missing datapoints: "+missingData);
                }
                // reset the value counters
                avgLow=0;
                avgHigh=0;
                missingData=0;
                // set the dates
                endDay=startDay;
                startDay=val[6].replaceAll("\"","");
            }
        }   

    }
}

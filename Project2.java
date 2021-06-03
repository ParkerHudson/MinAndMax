import java.io.*;


class Project2
{

    public static boolean isAValidDouble(String s){
        try{
            Double.parseDouble(s);
            return true;
        }
        catch(NumberFormatException nf){

            return false;

        }
    }


    
    public static void main(String args[]){

        if(args.length != 1){
            System.out.println("Missing <filename> argument");
            System.out.println("Usage: java Project2 <filename>");
            return;
        }


        try(    FileInputStream fstream = new FileInputStream(args[0]);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))
        
        ){
            
            String strLine;
            double total = 0.0;
            int totalLines = 0;
            int linesSkipped = 0;
            double min = 0.0;
            double max = 0.0;
            double temp = 0.0;
            int numOfDoubles = 0;

            //Read the file line by line
            while((strLine = br.readLine()) != null){
                totalLines++;

                if(isAValidDouble(strLine)){
                    numOfDoubles++;
                    temp = Double.parseDouble(strLine);
                    total += temp;
                    //if greater than max, set new max.
                    if(temp > max){
                        max = temp;
                    }
                    //If less than minimum, set new min.
                    if(temp < min || min == 0){
                        min = temp;
                    }
                }
                else{
                    linesSkipped++;
                }
            }
            
            System.out.println("Total # of lines: " + totalLines);
            System.out.println("# of lines skipped: " + linesSkipped);
            if(numOfDoubles == 0){
                System.out.println("Total: N/A");
                System.out.println("Minimum value read: N/A");
                System.out.println("Maximum value read: N/A");
            }
            System.out.println("Total: " + total);
            System.out.println("Minimum value read: " + min);
            System.out.println("Maximum value read: " + max);

        }

        catch(FileNotFoundException fnf){

            System.out.println("Could not find file " + args[0]);

        }

        catch(SecurityException se){
            System.out.println("The file " + args[0] + " could not be opened for reading. Permission denied.");
        }

        catch(Exception ex){
            System.out.println("Oopsie doopsie!: " + ex.toString());

        }
    }
}

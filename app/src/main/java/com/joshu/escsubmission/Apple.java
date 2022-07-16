package com.joshu.escsubmission;

import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class Apple {
    public static void main(String[] args) {
        String rawfile1=null;
        String rawfile2=null;

        // Uses system time to differentiate between new files if running on the same inputs
        String output = String.valueOf(System.currentTimeMillis());
        // Requires CLI arguments of the paths of 2 CSV files and the directory where you the exceptions csv can be recorded
        if (args.length == 3) {
            rawfile1 = args[0];
            rawfile2 = args[1];
            output = args[2] + "\\" + output + ".csv";
        }
        else{
            // Invalid arguments, print error message
            System.out.println("Invalid Arguments. Run the program as: ");
            System.out.println("java Apple.java <first.csv> <second.csv> <output directory>");
            System.out.println("Ensure the output directory exists before running this program.");
            exit(1);
        }

        try{
            File csvOutputFile = new File(output);
            if (!csvOutputFile.createNewFile()){
                //check if file created already, if not, creates it
                System.out.println("File already exists, please clean directory");
                exit(1);
            }
            ArrayList<String> arr1 = readCSV(rawfile1);
            ArrayList<String> arr2 = readCSV(rawfile2);
            assert arr1 != null;
            HashSet<String> f1 = new HashSet<>(arr1);
            HashSet<String> f2 = new HashSet<>(arr1);
            assert arr2 != null;
            HashSet<String> f3 = new HashSet<>(arr2);
            HashSet<String> f4 = new HashSet<>(arr2);
            f3.removeAll(f1); // f3 now contains only the lines which are not in f1
            f2.removeAll(f4); // f2 now contains only the lines which are not in f4

            writeCSV(csvOutputFile, f3, f2); // write to file
            // print success message
            System.out.println("\nSuccess!");
            System.out.println("File written at: " + csvOutputFile.getAbsolutePath());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Invalid file path");
        }
    }

    /**
     * Appends to a specified CSV file from a Hashset of Strings passed to it. Does not overwrite.
     * PrintWriter implements AutoCloseable so I don't have to close it.
     *
     * @param file Specified CSV file object
     * @param args Arbitrary number of Hashsets to write to file
     * @throws FileNotFoundException thrown if file not found or file permission is lacking
     */
    public static void writeCSV(File file, HashSet ... args) throws FileNotFoundException{

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))){
            for (HashSet f : args){
                for (Object o : f) {
                    String line = o + "\n";
                    pw.write(line);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            // since i already know why it could be thrown, i don't print the whole stack trace
        }
    }

    /**
     * Reads a CSV file and returns an ArrayList of Strings for each line.
     *
     * @param filename Specified CSV file object
     * @return ArrayList of strings where each element is a line from the csv file
     */
    public static ArrayList<String> readCSV(String filename) throws FileNotFoundException {
        File file= new File(filename);
        ArrayList<String> result = new ArrayList<>();
        // read the file
        try {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert fis != null;
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            // first line is the headers
            String line = br.readLine();
            while (line!= null) {
                result.add(line);
                line = br.readLine();
            }
            br.close();
            in.close();
            fis.close();
            return result;
        } catch (IOException e) {
            System.out.println("File not found.");
            e.printStackTrace();
            return null;
        }
    }
}

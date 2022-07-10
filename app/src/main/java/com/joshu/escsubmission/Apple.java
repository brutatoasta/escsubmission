package com.joshu.escsubmission;

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
import java.util.Iterator;

public class Apple {
    public static void main(String[] args) {
        String rawfile1 = "C:\\Users\\joshu\\Documents\\Github\\escsubmission\\sample_file_1.csv";
        String rawfile2 = "C:\\Users\\joshu\\Documents\\Github\\escsubmission\\sample_file_3.csv";
        String output = "C:\\Users\\joshu\\Documents\\Github\\escsubmission\\help.csv";
        // Uses the sample files and default output by default unless input and output files are specified in the arguments
        if (args.length > 0) rawfile1 = args[0];
        if (args.length > 1) rawfile2 = args[1];
        if (args.length > 2) output = args[2];
        try{
            HashSet<String> f1 = new HashSet<>(readCSV(rawfile1));
            HashSet<String> f2 = new HashSet<>(readCSV(rawfile1));
            HashSet<String> f3 = new HashSet<>(readCSV(rawfile2));
            HashSet<String> f4 = new HashSet<>(readCSV(rawfile2));
            f3.removeAll(f1); // f2 now contains only the lines which are not in f1
            f2.removeAll(f4);
            File csvOutputFile = new File(output);
            writeCSV(f3, csvOutputFile);
            writeCSV(f2, csvOutputFile);
        }
        finally{}

    }
    public static void writeCSV(HashSet f, File file){
        Iterator<String> it = f.iterator();
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))){
            while (it.hasNext()){
                String line = it.next() + "\n";
                pw.write(line);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
    public static ArrayList<String> readCSV(String filename) {
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

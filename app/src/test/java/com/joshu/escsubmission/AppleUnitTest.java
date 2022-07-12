package com.joshu.escsubmission;

import static com.joshu.escsubmission.Apple.readCSV;
import static com.joshu.escsubmission.Apple.writeCSV;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;


public class AppleUnitTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * @throws IOException
     */
    @Test
    public void readCSVTest() throws IOException {
        String filename = "C:\\Users\\joshu\\Documents\\Github\\escsubmission\\sample_file_output_comparing_1_and_3.csv";

        ArrayList<String> arr = readCSV(filename);
        //System.out.println(arr.get(0));
        //System.out.println("\"ID99\",\"BOS8059799\",\"SGD\",\"CURRENT\",\"208043\"");
        assert(arr.get(0).equals("\"ID99\",\"BOS8059799\",\"SGD\",\"CURRENT\",\"208043\"") );
    }

    /**
     * @throws IOException
     */
    @Test
    public void writeCSVTest() throws IOException{

        //use an existing file
        String filename = "C:\\Users\\joshu\\Documents\\Github\\escsubmission\\writetest0.csv";
        File file = new File(filename);
        HashSet<String> result = new HashSet<>();

        //intended answer
        HashSet<String> intended = new HashSet<>(){{
            add("jobu,tupaki");
            add("alpha,waymond");
            add("michelle,yeoh");
        }};

        // we'll try to add this to writetest0.csv
        HashSet<String> toAdd = new HashSet<>(){{
            add("alpha,waymond");
            add("michelle,yeoh");
        }};

        // run the function we want to test
        writeCSV(toAdd, file);

        // time to verify: read the file
        // TODO: very similar code to readCSV function I wrote, is this okay?
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

            String line = br.readLine();
            while (line !=null) {
                result.add(line);
                line = br.readLine();
            }
            assert(result.equals(intended));
            br.close();
            in.close();
            fis.close();

        } catch (IOException e) {
            System.out.println("File not found.");
            e.printStackTrace();

        }

    }
}
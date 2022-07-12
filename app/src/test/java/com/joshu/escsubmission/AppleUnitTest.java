package com.joshu.escsubmission;

import static com.joshu.escsubmission.Apple.readCSV;
import static com.joshu.escsubmission.Apple.writeCSV;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;


public class AppleUnitTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    /**
     * Read a static sample file, check the first row
     *
     * @throws IOException
     */
    @Test
    public void readCSVTest() throws IOException {
        String filename = "C:\\Users\\joshu\\Documents\\Github\\escsubmission\\sample_file_output_comparing_1_and_3.csv";
        ArrayList<String> arr = readCSV(filename);
        assert(arr.get(0).equals("\"ID99\",\"BOS8059799\",\"SGD\",\"CURRENT\",\"208043\"") );
    }

    /**
     * Creates a temporary copy of writetest0.csv, with a single entry.
     * Adds 2 lines of entries and check if the hashset that is read back is correct
     *
     * @throws IOException
     */
    @Test
    public void writeCSVTest() throws IOException{
        // copy file
        File copied =  folder.newFile("testTemp.csv");
        String original = "C:\\Users\\joshu\\Documents\\Github\\escsubmission\\writetest0.csv";
        String intendedfilename = "C:\\Users\\joshu\\Documents\\Github\\escsubmission\\writetest1.csv";
        try (
                InputStream in = new BufferedInputStream(
                        new FileInputStream(original));
                OutputStream out = new BufferedOutputStream(
                        new FileOutputStream(copied))) {

            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        }
        catch (IOException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        assert(copied.exists());
        assert(Files.readAllLines(Paths.get(original))
                .equals(Files.readAllLines(copied.toPath())));

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
        writeCSV(toAdd, copied);

        // time to verify: read the file
        // TODO: very similar code to readCSV function I wrote, is this okay?
        try {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(copied);
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
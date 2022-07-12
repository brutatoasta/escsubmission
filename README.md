# escsubmission

Written for Elements of Software Construction, Summer 2022 by Joshua Ng, 1005285.

[Apple.java](../main/app/src/main/java/com/joshu/escsubmission/Apple.java) takes 2 CSV files and makes 2 hashsets each, before removing all duplicate entries from each other, to each other. This leaves the unique entries of each CSV file, and we write that to a new CSV file.

## Use Case Diagram
<img src=https://github.com/brutatoasta/escsubmission/blob/main/wk8_use_case_diagram.jpg >

## Usage
Run the program as: 
java Apple.java <first.csv> <second.csv> <output directory>
Remember to ensure the output directory exists before running this program. If the input files and output directory are in the root directory, then you can use the relative path. Otherwise, it's better to use the absolute path.

For example, the file structure can look like this:
<img src=https://github.com/brutatoasta/escsubmission/blob/main/tree.png>

And the command can look like this: 
```
java Apple.java sample_file_1.csv sample_file_3.csv output
```
or
```
java Apple.java C:\\Users\\joshu\\Documents\\Github\\escsubmission\\sample_file_1.csv C:\\Users\\joshu\\Documents\\Github\\escsubmission\\sample_file_3.csv C:\\Users\\joshu\\Documents\\Github\\escsubmission\\output
```

## Caveats
Obviously, this is in no way optimised.

If you are running this in Android Studio, read on. 
I wrote it in Android Studio which has the annoying habit of caching old data, so it doesn't update the byte code to match the source code (see: https://stackoverflow.com/questions/39990752/source-code-does-not-match-the-bytecode-when-debugging-on-a-device)
and AS refuses to run java files independently (see: https://stackoverflow.com/questions/57734823/android-studio-refuses-to-run-main) so you will have to clean your gradle build and either run with coverage or edit the gradle.xml file in .idea directory to get this to work on your device.

## Acknowledgements
Referenced file reading from Term 4's SAT solver, and the idea to use hashset comes from here: https://stackoverflow.com/questions/10864654/comparing-two-csv-files-in-java

# ESC Software Testing Mini Campaign

Written for Elements of Software Construction, Summer 2022 by Joshua Ng, 1005285.

[Apple.java](../main/app/src/main/java/com/joshu/escsubmission/Apple.java) takes 2 CSV files and makes 2 hashsets each, before removing all duplicate entries from each other, to each other. This leaves the unique entries of each CSV file, and we write that to a new CSV file.

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
If the CSV files have the same data but some columns are swapped, Apple.java will treat all rows as different.
I have taken this policy because reordering via tokenisation requires the CSV files to be clean and well formatted beforehand, and there is no straightforward way to ensure that, or to handle the tokenisation of garbage data.

If you are running this in Android Studio, read on. 
I wrote it in Android Studio which has the annoying habit of caching old data, so it doesn't update the byte code to match the source code (see: https://stackoverflow.com/questions/39990752/source-code-does-not-match-the-bytecode-when-debugging-on-a-device)
and AS refuses to run java files independently (see: https://stackoverflow.com/questions/57734823/android-studio-refuses-to-run-main) so you will have to clean your gradle build and either run with coverage or edit the gradle.xml file in .idea directory to get this to work on your device.

## Use Case Diagram
<img src=https://github.com/brutatoasta/escsubmission/blob/main/wk8_use_case_diagram.jpg >

## Equivalence class partitioning and boundary value analysis

ECP is about sorting between valid and invalid inputs, and at which boundary value it becomes invalid(BVA).
We investigate 2 main use cases: reading files and writing files and partition their inputs into equivalence classes, then suggest appropriate boundary values for testing.

### Reading files
**Invalid files:**

Boundaries: 
- Path does not exist or no read permissions
- Not a comma separated value file, e.g. a password protected excel file
- Invalid text encoding e.g. not UTF-8 as Apple.java is not configured for other text encoding
- Empty CSV file
- Columns are swapped

**Valid files:**

Boundaries:
- No header row
- Identical columns

### Writing files
**Invalid output path**

Boundaries:
- Directory does not exist

**Valid output path**

Boundaries:
- File already exists (Since outputs are named based on system time, you could try to predict the time and have an existing file with the same name, but its highly unlikely. Maybe if the directory is filled with files named by system time over a the time range we are creating the files in.)

Middle: 

- File cannot be created e.g. abort write

## Acknowledgements
Referenced file reading from Term 4's SAT solver, and the idea to use hashset comes from here: https://stackoverflow.com/questions/10864654/comparing-two-csv-files-in-java

Referenced file copying from [Baeldung](https://www.baeldung.com/java-copy-file)

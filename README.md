# escsubmission

Written for Elements of Software Construction, Summer 2022 by Joshua Ng, 1005285.

## Caveats
Apple.java main function takes 2 CSV files and makes 2 hashsets each, before removing all duplicate entries from each other, to each other. This leaves the unique entries of each csv file, and we write that to a new csv file.
Obviously, this is in no way optimised. 

For now, it is hardcoded to the sample file 1 and 3 on my PC's absolute path, and also only outputs to a csv file named help.csv.

I wrote it in Android Studio which has the annoying habit of caching old data, so it doesnt update the byte code to match the source code (see: https://stackoverflow.com/questions/39990752/source-code-does-not-match-the-bytecode-when-debugging-on-a-device)
and AS refuese to run java files independently (see: https://stackoverflow.com/questions/57734823/android-studio-refuses-to-run-main) so you will have to clean your gradle build and either run with coverage or edite the gradle.xml file in .idea directory to get this to work on your device.

At least it seems to work.

## Acknowledgements
Referenced file reading from Term 4's SAT solver, and the idea to use hashset comes from here: https://stackoverflow.com/questions/10864654/comparing-two-csv-files-in-java

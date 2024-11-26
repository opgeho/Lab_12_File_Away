import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {
    private static ArrayList<String> CSVList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        String CSV = "";
        String fileName = "";
        int entry = 0;
        do{
            CSV = getDataEntry(in);
            CSVList.add(entry, CSV);
            entry++;
            done = SafeInput.getYNConfirm(in, "Are you done entering data");
        }while (!done);
        fileName = SafeInput.getNonZeroLenString(in, "Enter file name") + ".csv";
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName);

        try
        {

            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for(String rec : CSVList)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static String getDataEntry(Scanner in) {
        System.out.println("Enter CSV: ");
        String fName = SafeInput.getNonZeroLenString(in, "Enter first name");
        String lName = SafeInput.getNonZeroLenString(in, "Enter last name");
        String idNum = SafeInput.getRegExString(in, "Enter ID number", "^\\d{6}$");
        String email = SafeInput.getNonZeroLenString(in, "Enter email");
        int yob = SafeInput.getRangedInt(in, "Enter year of birth", 1900, 2024);
        String retVal = fName + " " +  lName + " " + idNum + " " + email + " " + yob;
        return retVal;
    }

}

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DirectoryList {

    public List<String> listFilesForFolder(String inputLocation) {
        File folder = new File(inputLocation);
        List<String> result = new ArrayList<>();

        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry.toString());
            } else {
                result.add(fileEntry.getName());
            }
        }
        return result;
    }

    public List<String> sortDirectoryFiles(List<String> result) {
        List<String> finalList = new ArrayList<>();
        for (String file : result) {
            String[] finalFile = file.split("s", 2);

            if (finalFile[0].equals("order")) {
                String[] finalFile2 = finalFile[1].split("\\.", 2);

                if (isInt(finalFile2[0]) && finalFile2[1].equals("xml")) {
                    finalList.add(file);
                } else {
                    System.out.println("file " + file + " has a bad name!");
                }
            }
        }
        return finalList;
    }

    public boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

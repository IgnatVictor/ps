import java.util.List;


public class ReadAndWrite {

    ReadXml readXml = new ReadXml();
    OutputXml outputXml = new OutputXml();
    DirectoryList directoryList = new DirectoryList();

    public void readAndWrite(String inputLocation, String outputLocation) {
        List<String> files = directoryList.sortDirectoryFiles(directoryList.listFilesForFolder(inputLocation));

        for (String file : files) {
            String[] filePart = file.split("s", 2);
            outputXml.writeToXml(readXml.read(inputLocation, file), filePart[1], outputLocation);
        }
    }
}

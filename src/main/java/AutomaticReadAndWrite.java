import java.util.List;


public class AutomaticReadAndWrite {

    ReadXml readXml = new ReadXml();
    WriteXml outputXml = new WriteXml();
    DirectoryList directoryList = new DirectoryList();

    public void readAndWrite(String inputLocation, String outputLocation) {
        List<String> files = directoryList.sortDirectoryFiles(directoryList.listFilesForFolder(inputLocation));

        for (String file : files) {
            String[] filePart = file.split("s", 2);
            String orderNumber = filePart[1];
            outputXml.writeToXml(readXml.read(inputLocation, file), orderNumber, outputLocation);
        }
    }
}

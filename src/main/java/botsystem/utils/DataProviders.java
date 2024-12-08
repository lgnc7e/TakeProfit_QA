package botsystem.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider(name = "registration1")
    public Iterator<Object[]> getDataFromCSV() throws IOException, CsvException {
        String filePath = "src/test/resources/registration_1.csv";

        CSVReader csvReader = new CSVReader(new FileReader(filePath));
        List<String[]> allData = csvReader.readAll();

        return allData.stream()
                .map(data -> (Object[]) data)
                .iterator();
    }

    @DataProvider(name = "registration2")
    public Iterator<Object[]> getDataFromCSV2() throws IOException, CsvException {
        String filePath = "src/test/resources/registration_2.csv";

        CSVReader csvReader = new CSVReader(new FileReader(filePath));
        List<String[]> allData = csvReader.readAll();

        return allData.stream()
                .map(data -> (Object[]) data)
                .iterator();
    }

    @DataProvider(name = "ValidShortBotTest")
    public Iterator<Object[]> getDataFromCSV3() throws IOException, CsvException {
        String filePath = "src/test/resources/validShortBotTest.csv";

        CSVReader csvReader = new CSVReader(new FileReader(filePath));
        List<String[]> allData = csvReader.readAll();

        return allData.stream()
                .map(data -> (Object[]) data)
                .iterator();
    }

    @DataProvider(name = "ValidLongBotTest")
    public Iterator<Object[]> getDataFromCSV4() throws IOException, CsvException {
        String filePath = "src/test/resources/validLongBotTest.csv";

        CSVReader csvReader = new CSVReader(new FileReader(filePath));
        List<String[]> allData = csvReader.readAll();

        return allData.stream()
                .map(data -> (Object[]) data)
                .iterator();
    }




}

package ro.fasttrackit.curs5homework.services;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs5homework.ConfigProperties;
import ro.fasttrackit.curs5homework.model.Country;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Component
public class CountryReader {
    private final String path;

    public CountryReader(ConfigProperties config) {
        this.path = config.getFilePath();
    }

    public List<Country> readFile() {
        try {
            List<String> lines = Files.lines(Path.of(path)).toList();
            return IntStream.range(1,lines.size()).mapToObj(index -> buildCountry(index,lines.get(index))).collect(toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Country buildCountry(int index,String s) {
        String[] countryData = s.split("\\|");
        List<String> neighboursData = Arrays.stream(Arrays.stream(countryData).toList().get(countryData.length -1).split("~")).toList();
        try {
            return new Country(index,
                    countryData[0],
                    countryData[1],
                    Integer.parseInt(countryData[2]),
                    Integer.parseInt(countryData[3]),
                    countryData[4],
                    neighboursData);
        }catch (NumberFormatException e){
            throw new RuntimeException("Wrong data format supplied for country builder");
        }
    }
}

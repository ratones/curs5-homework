package ro.fasttrackit.curs5homework;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConstructorBinding
@ConfigurationProperties(prefix="config.countries")
public class ConfigProperties {
    private final String filePath;

    public ConfigProperties(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

}

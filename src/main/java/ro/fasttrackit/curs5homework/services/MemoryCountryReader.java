package ro.fasttrackit.curs5homework.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs5homework.model.Country;

import java.util.ArrayList;
import java.util.List;

@Profile("memory")
@Service
public class MemoryCountryReader implements CountryReader{
    @Override
    public List<Country> getCountries() {
        return List.of(
                new Country(1,"Algeria","Algiers",40400000,2381741,"Africa",List.of("TUN","LBY","NER","ESH","MRT","MLI","MAR")),
                new Country(2,"American Samoa","Pago Pago",57100,199,"Oceania",new ArrayList<>()),
                new Country(3,"Andorra","Andorra la Vella",78014,468,"Europe",List.of("FRA","ESP")),
                new Country(4,"Angola","Luanda",25868000,1246700,"Africa",List.of("COG","COD","ZMB","NAM"))
        );
    }
}

package ro.fasttrackit.curs5homework.services;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs5homework.model.Country;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private final List<Country> countries;

    public CountryService(CountryReader reader) {
        this.countries = reader.getCountries();
    }

    public List<Country> getAll(){
        return new ArrayList<>(countries);
    }



    public List<String> getCountriesNames() {
        return countries.stream().map(Country::name).toList();
    }

    public String getCountryCapital(int id) {
        return countries.stream()
                .filter(country -> country.id() == id)
                .findFirst()
                .map(Country::capital)
                .orElse("Not Found");
    }

    public double getCountryPopulation(int id) {
        return countries.stream()
                .filter(country -> country.id() == id)
                .findFirst()
                .map(Country::population)
                .orElse(Long.MIN_VALUE);
    }

    public List<Country> getCountriesInContinent(String continent, int minPopulation) {
        return countries.stream().filter(country -> country.continent().equalsIgnoreCase(continent) && country.population() >= minPopulation).toList();
    }

    public List<String> getCountryNeighbours(int id) {
        return countries.stream()
                .filter(country -> country.id() == id)
                .findFirst()
                .map(Country::neighbours)
                .orElse(new ArrayList<>());
    }

    public List<Country> getCountriesByNeighbours(String includeNeighbour, String excludeNeighbour) {
        return countries.stream()
                .filter(country -> country.neighbours().contains(includeNeighbour) && !country.neighbours().contains(excludeNeighbour))
                .toList();
    }

    public Map<String, Long> getPopulationOfCountry() {
        return countries.stream()
                .collect(Collectors.toMap(Country::name, Country::population));
    }

    public Map<String, List<Country>> getCountriesByContinent() {
        return countries.stream().collect(Collectors.groupingBy(Country::continent));
    }

    public Country getCountryByName(String countryName) {
        return countries.stream().filter(country -> country.name().equalsIgnoreCase(countryName)).findFirst().orElse(null);
    }
}

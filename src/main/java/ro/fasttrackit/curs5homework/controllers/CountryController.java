package ro.fasttrackit.curs5homework.controllers;

import com.sun.net.httpserver.HttpContext;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.fasttrackit.curs5homework.model.Country;
import ro.fasttrackit.curs5homework.services.CountryHeaderProvider;
import ro.fasttrackit.curs5homework.services.CountryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService service;
    private final ApplicationContext context;

    CountryController(CountryService service, ApplicationContext context){
        System.out.println("Controller constructed");
        this.service = service;
        this.context = context;
    }

    @GetMapping()
    List<Country> getAll(@RequestParam(required = false, defaultValue = "") String includeNeighbour,
                         @RequestParam(required = false, defaultValue = "") String excludeNeighbour){
        if(!includeNeighbour.isBlank() && !excludeNeighbour.isBlank()){
            return service.getCountriesByNeighbours(includeNeighbour,excludeNeighbour);
        }
        return service.getAll();
    }

    @GetMapping("/names")
    List<String> getNames(){
        return service.getCountriesNames();
    }

    @GetMapping("{id}/capital")
    String getCountryCapital(@PathVariable() int id){
        return service.getCountryCapital(id);
    }

    @GetMapping("{id}/population")
    double getCountryPopulation(@PathVariable() int id){
        return service.getCountryPopulation(id);
    }

    @GetMapping("/continents/{continent}/countries")
    List<Country> getCountriesInContinent(@PathVariable() String continent, int minPopulation){
        return service.getCountriesInContinent(continent, minPopulation);
    }

    @GetMapping("{id}/neighbours")
    List<String> getCountryNeighbours(@PathVariable() int id){
        return service.getCountryNeighbours(id);
    }

    @GetMapping("/population")
    Map<String, Long> getPopulationOfCountry(){
       return service.getPopulationOfCountry();
    }

    @GetMapping("/continents/countries")
    Map<String, List<Country>> getCountriesByContinent(){
        return service.getCountriesByContinent();
    }

    @GetMapping("/mine")
    Country getMyCountry() throws ResponseStatusException {
        CountryHeaderProvider headerProvider = context.getBean(CountryHeaderProvider.class);
        String countryName = headerProvider
                .getRequestCountry()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Custom header X-Country must be provided for this method!"
                ));
        return service.getCountryByName(countryName);
    }


}
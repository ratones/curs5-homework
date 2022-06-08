package ro.fasttrackit.curs5homework.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@RequestScope
public class CountryHeaderProvider {
    private final HttpServletRequest request;

    CountryHeaderProvider(HttpServletRequest request){
        System.out.println("Header provider constructed");
        this.request = request;
    }

    public Optional<String> getRequestCountry(){
        String countryHeader = "X-Country";
        return Optional.ofNullable(request.getHeader(countryHeader));
    }
}

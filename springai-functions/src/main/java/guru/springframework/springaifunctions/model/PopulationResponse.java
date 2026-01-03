package guru.springframework.springaifunctions.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record PopulationResponse(
    @JsonPropertyDescription("The name of the country.") @JsonProperty("country_name") String countryName,
    @JsonPropertyDescription("Array of historical population data objects.") @JsonProperty("historical_population") List<HistoricalPopulation> historicalPopulation,
    @JsonPropertyDescription("Array of projected future population data objects.") @JsonProperty("population_forecast") List<HistoricalPopulation> populationForecast

) {
}

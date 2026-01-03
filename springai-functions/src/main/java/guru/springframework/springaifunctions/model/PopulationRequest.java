package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Population API request")
public record PopulationRequest(
    @JsonProperty(required = true, value = "country") String country,
    @JsonProperty(required = false, value = "min_population") Integer minPopulation,
    @JsonProperty(required = false, value = "max_population") Integer maxPopulation,
    @JsonProperty(required = false, value = "offset") Integer offset
) {

}

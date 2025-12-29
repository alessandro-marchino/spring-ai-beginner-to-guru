package guru.springframework.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalWithInfoResponse(
    @JsonPropertyDescription("The state or country requested") String stateOrCountry,
    @JsonPropertyDescription("The name of the capital city") String capital,
    @JsonPropertyDescription("The population of the capital city") Integer population,
    @JsonPropertyDescription("The region in which the capital city is located") String region,
    @JsonPropertyDescription("The primary language spoken in the capital city") String language,
    @JsonPropertyDescription("The currency used in the capital city") String currency
) {

}

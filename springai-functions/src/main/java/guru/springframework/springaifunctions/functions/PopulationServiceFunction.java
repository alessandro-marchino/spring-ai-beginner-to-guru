package guru.springframework.springaifunctions.functions;

import java.util.function.Function;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import guru.springframework.springaifunctions.model.PopulationRequest;
import guru.springframework.springaifunctions.model.PopulationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PopulationServiceFunction implements Function<PopulationRequest, PopulationResponse> {

    public static final String URL = "https://api.api-ninjas.com/v1/population";
    private final String apiNinjaKey;

    @Override
    public PopulationResponse apply(PopulationRequest populationRequest) {
        RestClient restClient = RestClient.builder()
            .baseUrl(URL)
            .defaultHeaders(httpHeaders -> {
                httpHeaders.set("X-Api-Key", apiNinjaKey);
                httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
                httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            })
            .build();
        PopulationResponse res = restClient.get()
            .uri(uriBuilder -> {
                log.info("Building URI for population request: {}", populationRequest);
                uriBuilder.queryParam("country", populationRequest.country());
                if(populationRequest.minPopulation() != null) {
                    uriBuilder.queryParam("min_population", populationRequest.minPopulation());
                }
                if(populationRequest.maxPopulation() != null) {
                    uriBuilder.queryParam("max_population", populationRequest.maxPopulation());
                }
                if(populationRequest.offset() != null) {
                    uriBuilder.queryParam("offset", populationRequest.offset());
                }

                return uriBuilder.build();
            })
            .retrieve()
            .body(PopulationResponse.class);
        log.trace("Response: {}", res);
        return res;
    }

}

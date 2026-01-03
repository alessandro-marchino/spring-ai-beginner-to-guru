package guru.springframework.springaifunctions.functions;

import java.util.function.Function;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import guru.springframework.springaifunctions.model.stock.StockPriceRequest;
import guru.springframework.springaifunctions.model.stock.StockPriceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class StockPriceServiceFunction implements Function<StockPriceRequest, StockPriceResponse> {

    public static final String URL = "https://api.api-ninjas.com/v1/stockprice";
    private final String apiNinjaKey;

    @Override
    public StockPriceResponse apply(StockPriceRequest request) {
        try {
            RestClient restClient = RestClient.builder()
                .baseUrl(URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjaKey);
                    httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
                })
                .build();
            StockPriceResponse res = restClient.get()
                .uri(uriBuilder -> {
                    log.info("Building URI for population request: {}", request);
                    uriBuilder.queryParam("ticker", request.ticker());
                    return uriBuilder.build();
                })
                .retrieve()
                .body(StockPriceResponse.class);
            log.info("Response: {}", res);
            return res;
        } catch(Exception e) {
            log.error("Exception", e);
            return null;
        }
    }
}

package guru.springframework.springaifunctions.model.stock;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record StockPriceRequest(
    @JsonPropertyDescription("Stock or index ticker symbol (e.g., AAPL or ^DJI).") String ticker
) {

}

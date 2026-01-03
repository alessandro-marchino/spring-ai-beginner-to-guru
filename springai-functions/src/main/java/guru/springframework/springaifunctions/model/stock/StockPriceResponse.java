package guru.springframework.springaifunctions.model.stock;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record StockPriceResponse(
    @JsonPropertyDescription("Stock or index ticker symbol (e.g., AAPL or ^DJI).") String ticker,
    @JsonPropertyDescription("The current stock price.") BigDecimal price,
    @JsonPropertyDescription("Unix timestamp indicating when the price was last updated.") Long updated,
    @JsonPropertyDescription("The trading volume (number of shares traded).") Long volume
) {

}

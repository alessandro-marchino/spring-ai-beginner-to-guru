package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record HistoricalPopulation(
    @JsonPropertyDescription("The year for which the population data is recorded.") Integer year,
    @JsonPropertyDescription("Total population count for the country in the given year.") Integer population,
    @JsonPropertyDescription("Percentage change in population from the previous year (can be positive or negative).") Integer yearlyChangePercentage,
    @JsonPropertyDescription("Absolute change in population from the previous year (can be positive or negative).") Integer yearlyChange,
    @JsonPropertyDescription("Net number of migrants (immigrants minus emigrants) for the year.") Integer migrants,
    @JsonPropertyDescription("Median age of the population in years.") Integer medianAge,
    @JsonPropertyDescription("Average number of children born per woman in the population.") Integer fertilityRate,
    @JsonPropertyDescription("Population density per square kilometer.") Integer density,
    @JsonPropertyDescription("Percentage of the total population living in urban areas.") Integer urbanPopulationPct,
    @JsonPropertyDescription("Total number of people living in urban areas.") Integer urbanPopulation,
    @JsonPropertyDescription("Percentage of the world's total population represented by this country.") Integer percentageOfWorldPopulation,
    @JsonPropertyDescription("World ranking by population size (1 being the most populous country).") Integer rank
) {

}

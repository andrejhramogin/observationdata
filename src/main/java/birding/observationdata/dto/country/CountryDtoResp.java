package birding.observationdata.dto.country;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class CountryDtoResp {
    @Schema(name = "id", description = "Country id", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    private UUID id;

    @Schema(name = "countryDescr", description = "Description of the country", example = "Belarus")
    private String countryDescr;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCountryDescr() {
        return countryDescr;
    }

    public void setCountryDescr(String countryDescr) {
        this.countryDescr = countryDescr;
    }
}

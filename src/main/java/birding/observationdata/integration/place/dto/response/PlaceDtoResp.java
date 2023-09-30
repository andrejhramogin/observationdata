package birding.observationdata.integration.place.dto.response;

import birding.observationdata.dto.country.CountryDtoResp;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

public class PlaceDtoResp {
    @Schema(name = "id", description = "Place id", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    private UUID Id;

    @Schema (name = "latitude", description = "Place latitude", example = "65.11589054716222")
    private BigDecimal latitude;

    @Schema (name = "longitude", description = "Place longitude", example = "-48.76363334487886")
    private BigDecimal longitude;

    @Schema (name = "address", description = "Address or plus-code",
            example = "Grodno, Gaya str, 31 or MRFV+VV3")
    private String address;

    @Schema (name = "areaDescr", description = "Description of area", example = "undergrowth")
    private String areaDescr;

    @Schema (name = "country", description = "CountryDtoResp object")
    private CountryDtoResp countryDtoResp;
    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaDescr() {
        return areaDescr;
    }

    public void setAreaDescr(String areaDescr) {
        this.areaDescr = areaDescr;
    }

    public CountryDtoResp getCountryDtoResp() {
        return countryDtoResp;
    }

    public void setCountryDtoResp(CountryDtoResp countryDtoResp) {
        this.countryDtoResp = countryDtoResp;
    }
}

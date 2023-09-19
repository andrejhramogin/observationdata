package birding.observationdata.dto.place.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public class DtoPlaceRq {
    @Schema(name = "latitude", description = "Place latitude", example = "65.11589054716222")
    @NotNull(message = "latitude should not be empty")
    @DecimalMin(value = "-90.0", inclusive = true)
    @DecimalMax(value = "90.0", inclusive = true)
    private BigDecimal latitude;

    @Schema(name = "longitude", description = "Place longitude", example = "-48.76363334487886")
    @NotNull(message = "longitude should not be empty")
    @DecimalMin(value = "-90.0", inclusive = true)
    @DecimalMax(value = "90.0", inclusive = true)
    private BigDecimal longitude;

    @Schema(name = "address", description = "Address or plus-code",
            example = "Grodno, Gaya str, 31 or MRFV+VV3")
    @NotBlank(message = "address should not be blank")
    @Size(min = 1, max = 50, message = "50 characters max")
    private String address;

    @Schema(name = "areaDescr", description = "Description of area", example = "undergrowth")
    @Size(max = 1000, message = "1000 characters max")
    private String areaDescr;

    @Schema(name = "countryId", description = "Country id", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    @NotNull(message = "countryId should not be empty")
    private UUID countryId;

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

    public UUID getCountryId() {
        return countryId;
    }

    public void setCountryId(UUID countryId) {
        this.countryId = countryId;
    }
}

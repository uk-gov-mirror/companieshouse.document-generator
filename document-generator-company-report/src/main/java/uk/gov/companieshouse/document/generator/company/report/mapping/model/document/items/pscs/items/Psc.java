package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.pscs.items;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.common.Address;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.common.DateOfBirth;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Psc {

    @JsonProperty("address")
    private Address address;

    @JsonProperty("country_of_residence")
    private String countryOfResidence;

    @JsonProperty("date_of_birth")
    private DateOfBirth dateOfBirth;

    @JsonProperty("name")
    private String name;

    @JsonProperty("natures_of_control")
    private List<String> naturesOfControl;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("notified_on")
    private LocalDate notifiedOn;

    public Address getAddress() {
        return address;
    }

    public void setAddress(
        Address address) {
        this.address = address;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNaturesOfControl() {
        return naturesOfControl;
    }

    public void setNaturesOfControl(List<String> naturesOfControl) {
        this.naturesOfControl = naturesOfControl;
    }

    public LocalDate getNotifiedOn() {
        return notifiedOn;
    }

    public void setNotifiedOn(LocalDate notifiedOn) {
        this.notifiedOn = notifiedOn;
    }
}

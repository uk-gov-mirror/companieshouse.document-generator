package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.items;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.common.Address;

import java.time.LocalDate;

@JsonInclude(Include.NON_NULL)
public class Practitioners {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("appointed on")
    private LocalDate appointedOn;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("ceased_to_act_on")
    private LocalDate ceasedToActOn;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private Address address;

    public LocalDate getAppointedOn() {
        return appointedOn;
    }

    public void setAppointedOn(LocalDate appointedOn) {
        this.appointedOn = appointedOn;
    }

    public LocalDate getCeasedToActOn() {
        return ceasedToActOn;
    }

    public void setCeasedToActOn(LocalDate ceasedToActOn) {
        this.ceasedToActOn = ceasedToActOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(
        Address address) {
        this.address = address;
    }
}

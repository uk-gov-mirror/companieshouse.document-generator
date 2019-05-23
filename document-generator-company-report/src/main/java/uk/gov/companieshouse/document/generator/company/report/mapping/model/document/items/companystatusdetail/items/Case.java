package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.items;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Case {

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("type")
    private String type;

    @JsonProperty("dates")
    private List<Dates> dates;

    @JsonProperty("practitioners_details")
    private List<Practitioners> practitioners;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Dates> getDates() {
        return dates;
    }

    public void setDates(
        List<Dates> dates) {
        this.dates = dates;
    }

    public List<Practitioners> getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(
        List<Practitioners> practitioners) {
        this.practitioners = practitioners;
    }
}

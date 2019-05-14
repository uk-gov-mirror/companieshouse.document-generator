package uk.gov.companieshouse.document.generator.company.report.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class FilingHistoryDetails {

    @JsonProperty("date")
    private Date date;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private String type;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

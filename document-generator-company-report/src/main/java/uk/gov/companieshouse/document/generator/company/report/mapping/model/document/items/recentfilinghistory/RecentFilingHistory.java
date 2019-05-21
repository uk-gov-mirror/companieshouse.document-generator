package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.recentfilinghistory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonInclude(Include.NON_NULL)
public class RecentFilingHistory {

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("form")
    private String form;

    @JsonProperty("filing_description")
    private String filingDescription;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getFilingDescription() {
        return filingDescription;
    }

    public void setFilingDescription(String filingDescription) {
        this.filingDescription = filingDescription;
    }
}

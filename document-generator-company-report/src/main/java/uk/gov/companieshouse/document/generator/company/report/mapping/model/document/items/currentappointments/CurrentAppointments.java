package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.currentappointments;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.currentappointments.items.CurrentOfficer;

@JsonInclude(Include.NON_NULL)
public class CurrentAppointments {

    @JsonProperty("active_count")
    private Integer numberOfCurrentAppointments;

    @JsonProperty("items")
    private List<CurrentOfficer> currentOfficers;

    public Integer getNumberOfCurrentAppointments() {
        return numberOfCurrentAppointments;
    }

    public void setNumberOfCurrentAppointments(Integer numberOfCurrentAppointments) {
        this.numberOfCurrentAppointments = numberOfCurrentAppointments;
    }

    public List<CurrentOfficer> getCurrentOfficers() {
        return currentOfficers;
    }

    public void setCurrentOfficers(List<CurrentOfficer> currentOfficers) {
        this.currentOfficers = currentOfficers;
    }
}

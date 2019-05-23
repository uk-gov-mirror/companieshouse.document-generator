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

    @JsonProperty("current_officers")
    private List<CurrentOfficer> items;

    public Integer getNumberOfCurrentAppointments() {
        return numberOfCurrentAppointments;
    }

    public void setNumberOfCurrentAppointments(Integer numberOfCurrentAppointments) {
        this.numberOfCurrentAppointments = numberOfCurrentAppointments;
    }

    public List<CurrentOfficer> getItems() {
        return items;
    }

    public void setItems(
        List<CurrentOfficer> items) {
        this.items = items;
    }
}

package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.currentappointments.items;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfficerApi {

    @JsonProperty("appointments")
    private String appointments;

    public String getAppointments() {
        return appointments;
    }

    public void setAppointments(String appointments) {
        this.appointments = appointments;
    }
}

package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.pscs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.pscs.items.Psc;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class CurrentPeopleWithSignificantControl {

    @JsonProperty("active_count")
    private Integer activeCount;

    @JsonProperty("psc")
    private List<Psc> psc;

    public Integer getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(Integer activeCount) {
        this.activeCount = activeCount;
    }

    public List<Psc> getPsc() {
        return psc;
    }

    public void setPsc(
        List<Psc> psc) {
        this.psc = psc;
    }
}

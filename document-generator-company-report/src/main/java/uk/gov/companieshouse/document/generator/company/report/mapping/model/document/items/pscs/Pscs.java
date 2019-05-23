package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.pscs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.pscs.items.Psc;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Pscs {

    @JsonProperty("active_count")
    private Integer activeCount;

    @JsonProperty("psc")
    private List<Psc> items;

    public Integer getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(Integer activeCount) {
        this.activeCount = activeCount;
    }

    public List<Psc> getItems() {
        return items;
    }

    public void setItems(
        List<Psc> items) {
        this.items = items;
    }
}

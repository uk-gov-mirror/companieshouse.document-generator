package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.items.Case;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class CompanyStatusDetail {

    @JsonProperty("status")
    private List<String> status;

    @JsonProperty("cases")
    private List<Case> cases;

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(
        List<Case> cases) {
        this.cases = cases;
    }
}

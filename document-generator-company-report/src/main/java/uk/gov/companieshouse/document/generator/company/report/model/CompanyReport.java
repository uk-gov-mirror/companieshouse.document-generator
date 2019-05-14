package uk.gov.companieshouse.document.generator.company.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import uk.gov.companieshouse.api.model.charges.ChargesApi;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;
import uk.gov.companieshouse.api.model.filinghistory.FilingHistoryApi;
import uk.gov.companieshouse.api.model.insolvency.InsolvencyApi;
import uk.gov.companieshouse.api.model.officers.OfficersApi;
import uk.gov.companieshouse.api.model.psc.PscsApi;
import uk.gov.companieshouse.document.generator.company.report.model.items.FormattedAdditionalInformation;

@JsonTypeName("company_report")
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
public class CompanyReport {

    @JsonProperty("company_profile")
    private CompanyProfileApi companyProfile;

    @JsonProperty("filing_history")
    private FilingHistoryApi filingHistory;

    @JsonProperty("insolvency")
    private InsolvencyApi insolvency;

    @JsonProperty("officers")
    private OfficersApi officers;

    @JsonProperty("charges")
    private ChargesApi charges;

    @JsonProperty("persons_with_significant_control_statements")
    private PscsApi pscsApi;

    @JsonProperty("additional_information")
    private FormattedAdditionalInformation formattedAdditionalInformation;

    public CompanyProfileApi getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfileApi companyProfile) {
        this.companyProfile = companyProfile;
    }

    public FilingHistoryApi getFilingHistory() {
        return filingHistory;
    }

    public void setFilingHistory(
        FilingHistoryApi filingHistory) {
        this.filingHistory = filingHistory;
    }

    public InsolvencyApi getInsolvency() {
        return insolvency;
    }

    public void setInsolvency(InsolvencyApi insolvency) {
        this.insolvency = insolvency;
    }

    public OfficersApi getOfficers() {
        return officers;
    }

    public void setOfficers(OfficersApi officers) {
        this.officers = officers;
    }

    public ChargesApi getCharges() {
        return charges;
    }

    public void setCharges(ChargesApi charges) {
        this.charges = charges;
    }

    public PscsApi getPscsApi() {
        return pscsApi;
    }

    public void setPscsApi(PscsApi pscsApi) {
        this.pscsApi = pscsApi;
    }

    public FormattedAdditionalInformation getFormattedAdditionalInformation() {
        return formattedAdditionalInformation;
    }

    public void setFormattedAdditionalInformation(
        FormattedAdditionalInformation formattedAdditionalInformation) {
        this.formattedAdditionalInformation = formattedAdditionalInformation;
    }
}

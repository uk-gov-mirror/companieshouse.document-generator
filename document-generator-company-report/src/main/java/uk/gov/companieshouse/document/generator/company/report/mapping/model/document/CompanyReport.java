package uk.gov.companieshouse.document.generator.company.report.mapping.model.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.CompanyStatusDetail;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.currentappointments.CurrentAppointments;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.mortgagechargedetails.MortgageChargeDetails;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.pscs.Pscs;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.recentfilinghistory.RecentFilingHistory;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.RegistrationInformation;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.keyfilingdates.KeyFilingDates;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.previousnames.PreviousNames;

import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonTypeName("company_report")
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
public class CompanyReport {

    @JsonProperty("company_registration_information")
    private RegistrationInformation registrationInformation;

    @JsonProperty("previous_names")
    private List<PreviousNames> previousNames;

    @JsonProperty("key_filing_dates")
    private KeyFilingDates keyFilingDates;

    @JsonProperty("recent_filing_history")
    private List<RecentFilingHistory> recentFilingHistory;

    @JsonProperty("company_status_detail")
    private CompanyStatusDetail companyStatusDetail;

    @JsonProperty("current_appointments")
    private CurrentAppointments currentAppointments;

    @JsonProperty("current_people_with_significant_control")
    private Pscs pscs;

    @JsonProperty("mortgage_charge_details")
    private MortgageChargeDetails mortgageChargeDetails;

    public RegistrationInformation getRegistrationInformation() {
        return registrationInformation;
    }

    public void setRegistrationInformation(
        RegistrationInformation registrationInformation) {
        this.registrationInformation = registrationInformation;
    }

    public List<PreviousNames> getPreviousNames() {
        return previousNames;
    }

    public void setPreviousNames(
        List<PreviousNames> previousNames) {
        this.previousNames = previousNames;
    }

    public KeyFilingDates getKeyFilingDates() {
        return keyFilingDates;
    }

    public void setKeyFilingDates(
        KeyFilingDates keyFilingDates) {
        this.keyFilingDates = keyFilingDates;
    }

    public List<RecentFilingHistory> getRecentFilingHistory() {
        return recentFilingHistory;
    }

    public void setRecentFilingHistory(
        List<RecentFilingHistory> recentFilingHistory) {
        this.recentFilingHistory = recentFilingHistory;
    }

    public CompanyStatusDetail getCompanyStatusDetail() {
        return companyStatusDetail;
    }

    public void setCompanyStatusDetail(
        CompanyStatusDetail companyStatusDetail) {
        this.companyStatusDetail = companyStatusDetail;
    }

    public CurrentAppointments getCurrentAppointments() {
        return currentAppointments;
    }

    public void setCurrentAppointments(CurrentAppointments currentAppointments) {
        this.currentAppointments = currentAppointments;
    }

    public Pscs getPscs() {
        return pscs;
    }

    public void setPscs(
        Pscs pscs) {
        this.pscs = pscs;
    }

    public MortgageChargeDetails getMortgageChargeDetails() {
        return mortgageChargeDetails;
    }

    public void setMortgageChargeDetails(MortgageChargeDetails mortgageChargeDetails) {
        this.mortgageChargeDetails = mortgageChargeDetails;
    }
}

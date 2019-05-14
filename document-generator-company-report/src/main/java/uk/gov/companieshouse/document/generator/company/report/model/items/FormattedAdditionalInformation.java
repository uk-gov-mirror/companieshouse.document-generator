package uk.gov.companieshouse.document.generator.company.report.model.items;

import java.util.List;

public class FormattedAdditionalInformation {

    private String companyType;

    private String companySubtype;

    private String companyStatus;

    private String companyStatusDetail;

    private List<String> natureOfBusinessSIC;

    private List<FilingHistoryDetails> fillingHistory;

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanySubtype() {
        return companySubtype;
    }

    public void setCompanySubtype(String companySubtype) {
        this.companySubtype = companySubtype;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyStatusDetail() {
        return companyStatusDetail;
    }

    public void setCompanyStatusDetail(String companyStatusDetail) {
        this.companyStatusDetail = companyStatusDetail;
    }

    public List<String> getNatureOfBusinessSIC() {
        return natureOfBusinessSIC;
    }

    public void setNatureOfBusinessSIC(List<String> natureOfBusinessSIC) {
        this.natureOfBusinessSIC = natureOfBusinessSIC;
    }

    public List<FilingHistoryDetails> getFillingHistory() {
        return fillingHistory;
    }

    public void setFillingHistory(
        List<FilingHistoryDetails> fillingHistory) {
        this.fillingHistory = fillingHistory;
    }
}

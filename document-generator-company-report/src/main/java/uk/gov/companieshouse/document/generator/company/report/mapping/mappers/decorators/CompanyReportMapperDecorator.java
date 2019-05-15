package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.decorators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.ApiToRegistrationInformationMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.CompanyReportMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.CompanyReportApiData;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.CompanyReport;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.RegistrationInformation;

public class CompanyReportMapperDecorator implements CompanyReportMapper {
    
    @Autowired
    @Qualifier("delegate")
    private CompanyReportMapper companyReportMapper;

    @Autowired
    private ApiToRegistrationInformationMapper apiToRegistrationInformationMapper;
    
    @Override
    public CompanyReport mapCompanyReport(CompanyReportApiData companyReportApiData) {
        
        CompanyReport companyReport = companyReportMapper.mapCompanyReport(companyReportApiData);
        
        companyReport.setRegistrationInformation(setRegistrationInformation(companyReportApiData));
        
        return companyReport;
    }

    private RegistrationInformation setRegistrationInformation(CompanyReportApiData companyReportApiData) {
        return apiToRegistrationInformationMapper.apiToRegistrationInformation(companyReportApiData);
    }
}

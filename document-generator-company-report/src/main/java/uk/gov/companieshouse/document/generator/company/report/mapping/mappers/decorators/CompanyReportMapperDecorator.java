package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.decorators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uk.gov.companieshouse.document.generator.company.report.exception.MapperException;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.ApiToRegistrationInformationMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.CompanyReportMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.CompanyReportApiData;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.CompanyReport;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.RegistrationInformation;

import java.io.IOException;

public class CompanyReportMapperDecorator implements CompanyReportMapper {
    
    @Autowired
    @Qualifier("delegate")
    private CompanyReportMapper companyReportMapper;

    @Autowired
    private ApiToRegistrationInformationMapper apiToRegistrationInformationMapper;
    
    @Override
    public CompanyReport mapCompanyReport(CompanyReportApiData companyReportApiData) throws MapperException {
        
        CompanyReport companyReport = companyReportMapper.mapCompanyReport(companyReportApiData);
        
        companyReport.setRegistrationInformation(setRegistrationInformation(companyReportApiData));
        
        return companyReport;
    }

    private RegistrationInformation setRegistrationInformation(CompanyReportApiData companyReportApiData) throws MapperException {
        try {
            return apiToRegistrationInformationMapper.apiToRegistrationInformation(companyReportApiData);
        } catch (IOException e) {
            throw new MapperException("An error occured when mapping to registration information", e);
        }
    }
}

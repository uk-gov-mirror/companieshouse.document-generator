package uk.gov.companieshouse.document.generator.company.report.mapping.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uk.gov.companieshouse.api.model.company.PreviousCompanyNamesApi;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;
import uk.gov.companieshouse.api.model.insolvency.InsolvencyApi;
import uk.gov.companieshouse.api.model.psc.PscsApi;
import uk.gov.companieshouse.document.generator.company.report.exception.MapperException;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail.ApiToCompanyStatusMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.keyfilingdates.ApiToKeyFilingDatesMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.previousnames.ApiToPreviousNamesMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.pscs.ApiToPSCSMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.recentfilinghistory.ApiToRecentFilingHistoryMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.registrationinformation.ApiToRegistrationInformationMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.CompanyReportMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.CompanyReportApiData;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.CompanyReport;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.CompanyStatusDetail;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.keyfilingdates.KeyFilingDates;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.previousnames.PreviousNames;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.pscs.Pscs;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.recentfilinghistory.RecentFilingHistory;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.RegistrationInformation;

import java.io.IOException;
import java.util.List;

public class CompanyReportMapperDecorator implements CompanyReportMapper {
    
    @Autowired
    @Qualifier("delegate")
    private CompanyReportMapper companyReportMapper;

    @Autowired
    private ApiToRegistrationInformationMapper apiToRegistrationInformationMapper;

    @Autowired
    private ApiToPreviousNamesMapper apiToPreviousNamesMapper;

    @Autowired
    private ApiToKeyFilingDatesMapper apiToKeyFilingDatesMapper;

    @Autowired
    private ApiToRecentFilingHistoryMapper apiToRecentFilingHistoryMapper;

    @Autowired
    private ApiToCompanyStatusMapper apiToCompanyStatusMapper;

    @Autowired
    private ApiToPSCSMapper pscsMapper;
    
    @Override
    public CompanyReport mapCompanyReport(CompanyReportApiData companyReportApiData) throws MapperException {
        
        CompanyReport companyReport = companyReportMapper.mapCompanyReport(companyReportApiData);
        
        companyReport.setRegistrationInformation(setRegistrationInformation(companyReportApiData));

        companyReport.setPreviousNames(setPreviousNames(companyReportApiData.getCompanyProfileApi().getPreviousCompanyNames()));

        companyReport.setKeyFilingDates(setKeyFilingDates(companyReportApiData));

        companyReport.setRecentFilingHistory(setRecentFilingHistory(companyReportApiData.getFilingHistoryApi().getItems()));

        companyReport.setCompanyStatusDetail(setCompanyStatusDetails(companyReportApiData.getInsolvencyApi()));

        companyReport.setPscs(setPscs(companyReportApiData.getPscsApi()));
        
        return companyReport;
    }

    private Pscs setPscs(PscsApi pscsApi) {
        return pscsMapper.apiToPSCSMapper(pscsApi);
    }

    private CompanyStatusDetail setCompanyStatusDetails(InsolvencyApi insolvencyApi) {
        return apiToCompanyStatusMapper.apiToCompanyStatusMapper(insolvencyApi);
    }

    private List<RecentFilingHistory> setRecentFilingHistory(List<FilingApi> items) {
        return apiToRecentFilingHistoryMapper.apiToRecentFilingHistoryMapperList(items);
    }

    private KeyFilingDates setKeyFilingDates(CompanyReportApiData companyReportApiData) {
        return apiToKeyFilingDatesMapper.apiToKeyFilingDates(companyReportApiData);
    }

    private List<PreviousNames> setPreviousNames(List<PreviousCompanyNamesApi> previousCompanyNames) {
        return apiToPreviousNamesMapper.apiToPreviousNamesMapper(previousCompanyNames);
    }

    private RegistrationInformation setRegistrationInformation(CompanyReportApiData companyReportApiData) throws MapperException {
        try {
            return apiToRegistrationInformationMapper.apiToRegistrationInformation(companyReportApiData);
        } catch (IOException e) {
            throw new MapperException("An error occurred when mapping to registration information", e);
        }
    }
}

package uk.gov.companieshouse.document.generator.company.report.mapping.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uk.gov.companieshouse.api.model.company.PreviousCompanyNamesApi;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;
import uk.gov.companieshouse.api.model.insolvency.InsolvencyApi;
import uk.gov.companieshouse.api.model.officers.OfficersApi;
import uk.gov.companieshouse.api.model.psc.PscsApi;
import uk.gov.companieshouse.document.generator.company.report.exception.MapperException;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail.ApiToCompanyStatusMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.currentappointments.ApiToCurrentAppointmentsMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.keyfilingdates.ApiToKeyFilingDatesMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.previousnames.ApiToPreviousNamesMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.pscs.ApiToPSCSMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.recentfilinghistory.ApiToRecentFilingHistoryMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.registrationinformation.ApiToRegistrationInformationMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.CompanyReportApiData;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.CompanyReport;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.CompanyStatusDetail;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.currentappointments.CurrentAppointments;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.keyfilingdates.KeyFilingDates;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.previousnames.PreviousNames;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.pscs.Pscs;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.recentfilinghistory.RecentFilingHistory;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.RegistrationInformation;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private ApiToCurrentAppointmentsMapper apiToCurrentAppointmentsMapper;

    @Autowired
    private ApiToPSCSMapper pscsMapper;

    @Override
    public CompanyReport mapCompanyReport(CompanyReportApiData companyReportApiData) throws MapperException {

        CompanyReport companyReport = companyReportMapper.mapCompanyReport(companyReportApiData);

        if (companyReportApiData.getCompanyProfileApi() != null) {

            companyReport.setRegistrationInformation(setRegistrationInformation(companyReportApiData));

            if (companyReportApiData.getCompanyProfileApi().getPreviousCompanyNames() != null) {
                companyReport.setPreviousNames(setPreviousNames(companyReportApiData.getCompanyProfileApi().getPreviousCompanyNames()));
            }

            if (companyReportApiData.getCompanyProfileApi().getAccounts() != null) {
                companyReport.setKeyFilingDates(setKeyFilingDates(companyReportApiData));
            }

            if (companyReportApiData.getFilingHistoryApi() != null) {
                companyReport.setRecentFilingHistory(setRecentFilingHistory(companyReportApiData.getFilingHistoryApi().getItems()));
            }

            if (companyReportApiData.getInsolvencyApi() != null) {
                companyReport.setCompanyStatusDetail(setCompanyStatusDetails(companyReportApiData.getInsolvencyApi()));
            }

            companyReport.setCurrentAppointments(setCurrentAppointments(companyReportApiData.getOfficersApi()));

            companyReport.setCompanyStatusDetail(setCompanyStatusDetails(companyReportApiData.getInsolvencyApi()));

            companyReport.setPscs(setPscs(companyReportApiData.getPscsApi()));
        }

        return companyReport;
    }

    private CurrentAppointments setCurrentAppointments(OfficersApi officersApi) throws MapperException {
        try {
            return apiToCurrentAppointmentsMapper.mapCurrentAppointments(officersApi);
        } catch (MapperException e) {
            throw new MapperException("An error occurred when mapping to current appointments", e);
        }
    }

    private Pscs setPscs(PscsApi pscsApi) {
        return pscsMapper.apiToPSCSMapper(pscsApi);
    }

    private CompanyStatusDetail setCompanyStatusDetails(InsolvencyApi insolvencyApi) {
        return apiToCompanyStatusMapper.apiToCompanyStatusMapper(insolvencyApi);
    }

    private List<RecentFilingHistory> setRecentFilingHistory(List<FilingApi> items) {

        List<RecentFilingHistory> mappedFiling = apiToRecentFilingHistoryMapper.apiToRecentFilingHistoryMapperList(items);

        return mappedFiling.stream()
            .sorted(Comparator.comparing(RecentFilingHistory::getDate, Comparator.nullsLast(Comparator.reverseOrder())))
            .collect(Collectors.toList());
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

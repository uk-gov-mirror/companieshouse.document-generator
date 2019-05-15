package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.decorators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.ApiToRegistrationInformationMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.CompanyReportApiData;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.RegistrationInformation;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.items.CompanyType;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.items.SicCodes;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.items.Status;

import java.util.ArrayList;
import java.util.List;

public class ApiToRegistrationInformationMapperDecorator implements ApiToRegistrationInformationMapper {

    @Autowired
    @Qualifier("delegate")
    private ApiToRegistrationInformationMapper apiToRegistrationInformationMapper;

    @Override
    public RegistrationInformation apiToRegistrationInformation(CompanyReportApiData companyReportApiData) {

        RegistrationInformation registrationInformation =
            apiToRegistrationInformationMapper.apiToRegistrationInformation(companyReportApiData);

        registrationInformation.setCompanyType(
            setCompanyType(companyReportApiData.getCompanyProfileApi().getType(),
            companyReportApiData.getCompanyProfileApi().getSubtype()));

        registrationInformation.setNatureOfBusiness(
            setNatureOfBusiness(companyReportApiData.getCompanyProfileApi().getSicCodes()));

        registrationInformation.setCompanyStatus(
            setCompanyStatus(companyReportApiData.getCompanyProfileApi().getCompanyStatus(),
            companyReportApiData.getCompanyProfileApi().getCompanyStatusDetail()));

        return registrationInformation;
    }

    private Status setCompanyStatus(String companyStatus, String companyStatusDetail) {
        Status status = new Status();

        if (companyStatus != null || !companyStatus.isEmpty()) {

        }

        if (companyStatusDetail != null || !companyStatusDetail.isEmpty()) {

        }

        return status;
    }

    private List<SicCodes> setNatureOfBusiness(String[] sicCodes) {
        List<SicCodes> listNatureOfBusiness = new ArrayList<>();

        return listNatureOfBusiness;
    }

    private CompanyType setCompanyType(String type, String subtype) {
        CompanyType companyType = new CompanyType();

        return companyType;
    }
}

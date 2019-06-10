package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.registrationinformation.decorators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uk.gov.companieshouse.document.generator.company.report.descriptions.RetrieveApiEnumerationDescription;
import uk.gov.companieshouse.document.generator.company.report.descriptions.config.ConstantsApiEnumeration;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.registrationinformation.ApiToRegistrationInformationMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.CompanyReportApiData;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.RegistrationInformation;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.items.CompanyType;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.items.SicCodes;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registrationinformation.items.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiToRegistrationInformationMapperDecorator implements ApiToRegistrationInformationMapper {

    @Autowired
    @Qualifier("delegate")
    private ApiToRegistrationInformationMapper apiToRegistrationInformationMapper;

    @Autowired
    private RetrieveApiEnumerationDescription retrieveApiEnumerationDescription;


    private static final String CONSTANTS = "constants.yml";

    @Override
    public RegistrationInformation apiToRegistrationInformation(CompanyReportApiData companyReportApiData) throws IOException {

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

        if (companyStatus != null && !companyStatus.isEmpty()) {
            status.setCompanyStatus(retrieveApiEnumerationDescription
                .getApiEnumerationDescription(CONSTANTS, "company_status", companyStatus));
        }

        if (companyStatusDetail != null && !companyStatusDetail.isEmpty()) {
            status.setCompanyStatusDetail(retrieveApiEnumerationDescription
                .getApiEnumerationDescription(CONSTANTS, "company_status_detail", companyStatusDetail));
        }

        return status;
    }

    private List<SicCodes> setNatureOfBusiness(String[] sicCodes) {

        List<SicCodes> listNatureOfBusiness = new ArrayList<>();

        if (sicCodes != null) {

            for (String sicCode : sicCodes) {
                SicCodes codes = new SicCodes();
                codes.setSicCodes(sicCode);
                codes.setSicCodesDescription(retrieveApiEnumerationDescription
                        .getApiEnumerationDescription(CONSTANTS, "sic_descriptions", sicCode));
                listNatureOfBusiness.add(codes);
            }

            return listNatureOfBusiness;
        }
    }

    private CompanyType setCompanyType(String type, String subtype) {

        CompanyType companyType = new CompanyType();

        if (type != null && !type.isEmpty()) {
            companyType.setType(retrieveApiEnumerationDescription
                .getApiEnumerationDescription(CONSTANTS, "company_type", type));
        }

        if (subtype != null && !subtype.isEmpty()) {
            companyType.setSubtype(retrieveApiEnumerationDescription
                .getApiEnumerationDescription(CONSTANTS, "company_subtype", subtype));
        }

        return companyType;
    }
}

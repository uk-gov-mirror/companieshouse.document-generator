package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.currentappointments.decorators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.companieshouse.api.model.officers.CompanyOfficerApi;
import uk.gov.companieshouse.api.model.officers.OfficersApi;
import uk.gov.companieshouse.document.generator.company.report.descriptions.RetrieveApiEnumerationDescription;
import uk.gov.companieshouse.document.generator.company.report.exception.MapperException;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.currentappointments.ApiToCurrentAppointmentsMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.currentappointments.ApiToCurrentOfficer;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.currentappointments.CurrentAppointments;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.currentappointments.items.CurrentOfficer;

public class ApiToCurrentAppointmentsMapperDecorator implements ApiToCurrentAppointmentsMapper {

    @Autowired
    private ApiToCurrentAppointmentsMapper apiToCurrentAppointmentsMapper;

    @Autowired
    private ApiToCurrentOfficer apiToCurrentOfficer;

    @Autowired
    private RetrieveApiEnumerationDescription retrieveApiEnumerationDescription;

    private static final String CONSTANTS = "constants.yml";

    @Override
    public CurrentAppointments mapCurrentAppointments(OfficersApi officerApi) throws MapperException {

        CurrentAppointments currentAppointments = apiToCurrentAppointmentsMapper.mapCurrentAppointments(officerApi);

        currentAppointments.setCurrentOfficers(setCurrentOfficer(officerApi.getItems()));

        return currentAppointments;
    }

    private List<CurrentOfficer> setCurrentOfficer(List<CompanyOfficerApi> items) {

        List<CurrentOfficer> currentOfficers = new ArrayList<>();

        for (CompanyOfficerApi companyOfficer : items) {

            CurrentOfficer currentOfficer = apiToCurrentOfficer.apiToCurrentOfficer(companyOfficer);

            currentOfficer.setOfficer_role(retrieveApiEnumerationDescription.getApiEnumerationDescription(CONSTANTS, "officer_role", (currentOfficer.getOfficer_role().toLowerCase())));

            if (companyOfficer.getLinks().getOfficer().getAppointments() != null) {
                currentOfficer.setNumberOfAppointments(10);
            }

            currentOfficers.add(currentOfficer);

        }

        return currentOfficers;
    }
}



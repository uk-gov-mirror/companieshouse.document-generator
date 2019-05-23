package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.currentappointments;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.officers.CompanyOfficerApi;
import uk.gov.companieshouse.document.generator.company.report.descriptions.RetrieveApiEnumerationDescription;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.currentappointments.items.CurrentOfficer;

import java.util.List;

@RequestScope
@Mapper(componentModel = "spring")
public abstract class ApiToCurrentOfficer {

    @Autowired
    private RetrieveApiEnumerationDescription retrieveApiEnumerationDescription;

    private static final String CONSTANTS = "constants.yml";

    @Mappings({
            @Mapping(source = "appointedOn", target = "appointed"),
    })
    public abstract CurrentOfficer apiToCurrentOfficer(CompanyOfficerApi companyOfficerApi);

    public abstract List<CurrentOfficer> apiToCurrentOfficer(List<CompanyOfficerApi> companyOfficerApis);

    @AfterMapping
    protected void convertOfficerRole(CompanyOfficerApi companyOfficerApi, @MappingTarget CurrentOfficer currentOfficer) {
        currentOfficer.setOfficer_role(retrieveApiEnumerationDescription
            .getApiEnumerationDescription(CONSTANTS, "officer_role", (currentOfficer.getOfficer_role().toLowerCase())));
    }

    @AfterMapping
    protected void setOfficerAppointments(CompanyOfficerApi companyOfficerApi, @MappingTarget CurrentOfficer currentOfficer) {
        if (companyOfficerApi.getLinks().getOfficer().getAppointments() != null) {
            //TODO implement call to obtain appointments
            currentOfficer.setNumberOfAppointments(10);
        }
    }
}
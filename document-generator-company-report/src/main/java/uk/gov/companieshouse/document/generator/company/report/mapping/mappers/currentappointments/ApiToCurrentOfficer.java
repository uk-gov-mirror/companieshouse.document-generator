package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.currentappointments;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.officers.CompanyOfficerApi;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.currentappointments.items.CurrentOfficer;

@RequestScope
@Mapper(componentModel = "spring")
public interface ApiToCurrentOfficer {

    @Mappings({
            @Mapping(source = "appointedOn", target = "appointed"),
    })
    CurrentOfficer apiToCurrentOfficer(CompanyOfficerApi companyOfficerApi);

}
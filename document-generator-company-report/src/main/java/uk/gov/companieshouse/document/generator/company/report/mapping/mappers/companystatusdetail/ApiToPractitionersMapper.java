package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail;

import org.mapstruct.Mapper;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.insolvency.PractitionerApi;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.items.Practitioners;

import java.util.List;

@RequestScope
@Mapper(componentModel = "spring")
public interface ApiToPractitionersMapper {

    Practitioners apiToPractitionersMapper(PractitionerApi practitionersApi);

    List<Practitioners> apiToPractitionersMapper(List<PractitionerApi> practitionersApi);
}

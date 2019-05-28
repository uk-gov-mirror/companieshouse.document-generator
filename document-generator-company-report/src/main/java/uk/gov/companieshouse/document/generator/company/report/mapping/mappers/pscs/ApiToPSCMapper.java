package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.pscs;

import org.mapstruct.Mapper;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.psc.PscApi;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.pscs.items.Psc;

import java.util.List;

@RequestScope
@Mapper(componentModel = "spring")
public interface ApiToPSCMapper {


    Psc apiToPSCMapper(PscApi pscApi);

    List<Psc> apiToPSCMapper(List<PscApi> pscApis);
}

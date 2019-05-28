package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.pscs;

import org.mapstruct.Mapper;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.psc.PscsApi;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.pscs.Pscs;

@RequestScope
@Mapper(componentModel = "spring", uses = {ApiToPSCMapper.class})
public interface ApiToPSCSMapper {

    Pscs apiToPSCSMapper(PscsApi pscsApi);
}

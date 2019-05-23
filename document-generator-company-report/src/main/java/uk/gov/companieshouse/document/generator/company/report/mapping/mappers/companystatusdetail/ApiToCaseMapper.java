package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail;

import org.mapstruct.Mapper;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.insolvency.CaseApi;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.items.Case;

import java.util.List;

@RequestScope
@Mapper(componentModel = "spring", uses = {ApiToPractitionersMapper.class, ApiToCaseDatesMapper.class})
public interface ApiToCaseMapper {

    Case ApiToCaseMapper(CaseApi caseApi);

    List<Case> ApiToCaseMapper(List<CaseApi> caseApis);
}

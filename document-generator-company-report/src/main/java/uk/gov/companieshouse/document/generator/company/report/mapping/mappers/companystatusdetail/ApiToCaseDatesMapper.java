package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail;

import org.mapstruct.Mapper;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.insolvency.DatesApi;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.items.CaseDates;

import java.util.List;

@RequestScope
@Mapper(componentModel = "spring")
public interface ApiToCaseDatesMapper {

    CaseDates apiToCaseDatesMapper(DatesApi datesApi);

    List<CaseDates> apiToCaseDatesMapper(List<DatesApi> datesApis);
}

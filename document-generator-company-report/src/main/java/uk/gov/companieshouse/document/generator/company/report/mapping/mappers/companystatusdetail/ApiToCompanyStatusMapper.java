package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.insolvency.DatesApi;
import uk.gov.companieshouse.api.model.insolvency.InsolvencyApi;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail.decorators.ApiToCompanyStatusMapperDecorator;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.CompanyStatusDetail;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.items.CaseDates;

import java.util.List;

@RequestScope
@Mapper(componentModel = "spring")
@DecoratedWith(ApiToCompanyStatusMapperDecorator.class)
public interface ApiToCompanyStatusMapper {

    CompanyStatusDetail apiToCompanyStatusMapper(InsolvencyApi insolvencyApi);


}

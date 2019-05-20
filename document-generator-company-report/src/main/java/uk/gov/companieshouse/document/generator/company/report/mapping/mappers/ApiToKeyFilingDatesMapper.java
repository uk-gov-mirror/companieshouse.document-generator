package uk.gov.companieshouse.document.generator.company.report.mapping.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.CompanyReportApiData;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.keyfilingdates.KeyFilingDates;

@RequestScope
@Mapper(componentModel = "spring")
public interface ApiToKeyFilingDatesMapper {

    @Mappings({
        @Mapping(source = "companyProfileApi.accounts.accountingReferenceDate.day", target = "accountingReferenceDate.day"),
        @Mapping(source = "companyProfileApi.accounts.accountingReferenceDate.month", target = "accountingReferenceDate.month"),
        @Mapping(source = "companyProfileApi.accounts.lastAccounts.periodEndOn", target = "lastAccountsMadeUpTo"),
        @Mapping(source = "companyProfileApi.accounts.lastAccounts.type", target = "accountsType"),
        @Mapping(source = "companyProfileApi.accounts.nextAccounts.dueOn", target = "nextAccountsDue"),
        @Mapping(source = "companyProfileApi.confirmationStatement.lastMadeUpTo", target = "lastConfirmationStatement"),
        @Mapping(source = "companyProfileApi.confirmationStatement.nextDue", target = "nextConfirmationStatement"),
        @Mapping(source = "companyProfileApi.lastFullMembersListDate", target = "lastMembersList")
    })
    KeyFilingDates apiToKeyFilingDates(CompanyReportApiData companyReportApiData);
}

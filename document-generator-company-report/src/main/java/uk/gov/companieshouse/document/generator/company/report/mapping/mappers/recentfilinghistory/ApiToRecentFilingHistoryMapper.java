package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.recentfilinghistory;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.recentfilinghistory.decorators.ApiToRecentFilingHistoryMapperDecorator;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.recentfilinghistory.RecentFilingHistory;

import java.util.List;

@RequestScope
@Mapper(componentModel = "spring")
@DecoratedWith(ApiToRecentFilingHistoryMapperDecorator.class)
public interface ApiToRecentFilingHistoryMapper {

    @Mappings({
        @Mapping(source = "type", target = "form")
    })
    RecentFilingHistory apiToRecentFilingHistoryMapper(FilingApi filingApi);

    List<RecentFilingHistory> apiToRecentFilingHistoryMapper(List<FilingApi> filingApis);
}

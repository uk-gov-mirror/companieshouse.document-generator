package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.recentfilinghistory;

import org.apache.commons.lang.text.StrSubstitutor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;
import uk.gov.companieshouse.document.generator.company.report.descriptions.RetrieveApiEnumerationDescription;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.recentfilinghistory.RecentFilingHistory;

import java.util.List;
import java.util.Map;

@RequestScope
@Mapper(componentModel = "spring")
public abstract class ApiToRecentFilingHistoryMapper {

    @Autowired
    private RetrieveApiEnumerationDescription retrieveApiEnumerationDescription;

    private static final String FILING_HISTORY_DESCRIPTIONS = "filing_history_descriptions.yml";

    @AfterMapping
    protected void convertFilingDescription(FilingApi filingApi, @MappingTarget RecentFilingHistory recentFilingHistory) {

        recentFilingHistory.setFilingDescription(setFilingDescription(filingApi.getDescription(), filingApi.getDescriptionValues()));

    }

    private String setFilingDescription(String description, Map<String, Object> descriptionValues) {

        //TODO refactor for better implementation
        if (description.equals("legacy")) {
            return descriptionValues.get("description").toString();
        }

        String filingDescription = retrieveApiEnumerationDescription
            .getApiEnumerationDescription(FILING_HISTORY_DESCRIPTIONS, "description", description);

        return populateParameters(filingDescription, descriptionValues);

    }

    private String populateParameters(Object description, Map<String, Object> parameters) {
        StrSubstitutor sub = new StrSubstitutor(parameters, "{", "}");
        return sub.replace(description);
    }

    @Mappings({
        @Mapping(source = "type", target = "form")
    })
    public abstract RecentFilingHistory apiToRecentFilingHistoryMapper(FilingApi filingApi);

    public abstract List<RecentFilingHistory> apiToRecentFilingHistoryMapperList(List<FilingApi> filingApis);
}

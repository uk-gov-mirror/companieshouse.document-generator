package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.decorators;

import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;
import uk.gov.companieshouse.document.generator.company.report.descriptions.RetrieveApiEnumerationDescription;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.ApiToRecentFilingHistoryMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.recentfilinghistory.RecentFilingHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiToRecentFilingHistoryMapperDecorator implements ApiToRecentFilingHistoryMapper {

    @Autowired
    @Qualifier("delegate")
    private ApiToRecentFilingHistoryMapper apiToRecentFilingHistoryMapper;

    @Autowired
    private RetrieveApiEnumerationDescription retrieveApiEnumerationDescription;

    private static final String FILING_HISTORY_DESCRIPTIONS = "filing_history_descriptions.yml";

    @Override
    public RecentFilingHistory apiToRecentFilingHistoryMapper(FilingApi filingApi) {
        RecentFilingHistory recentFilingHistory =
            apiToRecentFilingHistoryMapper.apiToRecentFilingHistoryMapper(filingApi);

        recentFilingHistory.setFilingDescription(setFilingDescription(filingApi.getDescription(), filingApi.getDescriptionValues()));

        return recentFilingHistory;
    }

    @Override
    public List<RecentFilingHistory> apiToRecentFilingHistoryMapper(List<FilingApi> filingApis) {

        if ( filingApis == null ) {
            return null;
        }

        List<RecentFilingHistory> list = new ArrayList<>(filingApis.size());
        for ( FilingApi filingApi : filingApis ) {
            list.add(apiToRecentFilingHistoryMapper(filingApi));
        }

        return list;
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
}

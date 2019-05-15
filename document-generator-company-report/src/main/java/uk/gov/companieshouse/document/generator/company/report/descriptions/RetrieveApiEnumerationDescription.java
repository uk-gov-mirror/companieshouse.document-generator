package uk.gov.companieshouse.document.generator.company.report.descriptions;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static uk.gov.companieshouse.document.generator.company.report.CompanyReportDocumentInfoServiceImpl.MODULE_NAME_SPACE;

@Component
public class RetrieveApiEnumerationDescription {

    private static final String FILING_DESCRIPTIONS_PREFIX = "document-generator-api/api-enumerations";

    private static final Logger LOG = LoggerFactory.getLogger(MODULE_NAME_SPACE);

    public String getApiEnumerationDescription(String fileName, String identifier, String descriptionValue) throws IOException {

        Yaml yaml = new Yaml();
        File descriptionsFile = new File(FILING_DESCRIPTIONS_PREFIX + fileName);

        String description = "";

        try (InputStream inputStream = new FileInputStream(descriptionsFile)) {

            Map<String, Object> descriptions = (Map<String, Object>) yaml.load(inputStream);
            Map<String, Object> filteredDescriptions = (Map<String, Object>) getDescriptionsValue(descriptions,
                identifier, fileName);

            if (filteredDescriptions != null) {
                description =  String.valueOf(getDescriptionsValue(filteredDescriptions, descriptionValue, fileName));
            }

        }  catch (FileNotFoundException e) {
            LOG.error("file not found when obtaining api enumeration " +
                "descriptions for file name: " + descriptionsFile, e);
        }

        return description;
    }

    private Object getDescriptionsValue(Map<String, Object> descriptions, String key, String fileName) {

        LOG.info("getting value from the file descriptions file: " + fileName + " using key: " + key);
        return descriptions.entrySet().stream()
            .filter(descriptionsEntrySet -> descriptionsEntrySet.getKey().equals(key))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElseGet(() -> {
                LOG.info("Value not found in file descriptions file: "
                        + fileName + " for key: " + key);
                return null;
            });
    }
}

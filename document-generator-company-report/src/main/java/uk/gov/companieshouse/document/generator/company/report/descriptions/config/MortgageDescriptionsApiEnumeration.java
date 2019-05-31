package uk.gov.companieshouse.document.generator.company.report.descriptions.config;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static uk.gov.companieshouse.document.generator.company.report.CompanyReportDocumentInfoServiceImpl.MODULE_NAME_SPACE;

@Component
public class MortgageDescriptionsApiEnumeration {

    private Map<String, Object> mortgageDescriptions;

    private static final String MORTGAGE_DESC_FILE_LOCATION = "document-generator-api/api-enumerations/mortgage_descriptions.yml";

    private static final Logger LOG = LoggerFactory.getLogger(MODULE_NAME_SPACE);

    @PostConstruct
    public void init() throws IOException {

        Yaml yaml = new Yaml();
        File descriptionsFile = new File(MORTGAGE_DESC_FILE_LOCATION);

        try (InputStream inputStream = new FileInputStream(descriptionsFile)) {

            mortgageDescriptions = (Map<String, Object>) yaml.load(inputStream);

        } catch (FileNotFoundException e) {
            LOG.error("file not found when obtaining api enumeration " +
                "descriptions for file name: " + descriptionsFile, e);
        }
    }

    public Map<String, Object> getMortgageDescriptions() {
        return mortgageDescriptions;
    }

    public void setMortgageDescriptions(
        Map<String, Object> filingHistoryDescriptions) {
        this.mortgageDescriptions = filingHistoryDescriptions;
    }
}

package uk.gov.companieshouse.document.generator.company.report.descriptions;

import org.yaml.snakeyaml.Yaml;

import java.io.File;

public class RetrieveApiEnumerationDescription {

    public String getApiEnumerationDescription(String fileName) {

        Yaml yaml = new Yaml();
        File descriptionsFile = new File(fileName);

        String description = "";

        return description;
    }
}

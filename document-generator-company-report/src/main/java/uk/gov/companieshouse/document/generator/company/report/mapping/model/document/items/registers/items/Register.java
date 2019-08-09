package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registers.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Register {

    @JsonProperty("register_type")
    private String registerType;

    @JsonProperty("items")
    private List<RegisterItems> items;

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public List<RegisterItems> getItems() {
        return items;
    }

    public void setItems(List<RegisterItems> items) {
        this.items = items;
    }
}

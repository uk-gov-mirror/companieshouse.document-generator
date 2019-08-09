package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registers.items;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterItems {

    @JsonProperty("register_move_to")
    private String registerMoveTo;

    @JsonProperty("move_on")
    private String moveOn;

    public String getRegisterMoveTo() {
        return registerMoveTo;
    }

    public void setRegisterMoveTo(String registerMoveTo) {
        this.registerMoveTo = registerMoveTo;
    }

    public String getMoveOn() {
        return moveOn;
    }

    public void setMoveOn(String moveOn) {
        this.moveOn = moveOn;
    }
}

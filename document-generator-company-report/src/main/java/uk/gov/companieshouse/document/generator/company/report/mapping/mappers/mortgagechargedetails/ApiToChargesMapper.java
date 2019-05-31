package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.mortgagechargedetails;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.charges.ChargeApi;
import uk.gov.companieshouse.document.generator.company.report.descriptions.RetrieveApiEnumerationDescription;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.mortgagechargedetails.items.Charge;

import java.util.List;

@RequestScope
@Mapper(componentModel = "spring", uses = {ApiToPersonsEntitledMapper.class})
public abstract class ApiToChargesMapper {

    @Autowired
    private RetrieveApiEnumerationDescription retrieveApiEnumerationDescription;

    private static final String MORTGAGE_DESCRIPTIONS = "mortgage_descriptions.yml";
    private static final String SECURED_DETAILS_DESCRIPTION = "secured-details-description";

    @Mappings({
        @Mapping(source = "classification.description", target = "description"),
        @Mapping(source = "createdOn", target = "createdDate"),
        @Mapping(source = "deliveredOn", target = "delivered"),
        @Mapping(source = "acquiredOn", target = "acquisitionDate"),
        @Mapping(source = "assetsCeasedReleased", target = "assetsCeased"),
        @Mapping(source = "securedDetails.description", target = "securedDetailsDescription"),
        @Mapping(source = "particulars.type", target = "type"),
        @Mapping(source = "particulars.description", target = "particularsDescription"),
        @Mapping(source = "particulars.chargorActingAsBareTrustee", target = "chargorActingAsBareTrustee"),
        @Mapping(source = "particulars.containsFixedCharge", target = "containsFixedCharge"),
        @Mapping(source = "particulars.containsFloatingCharge", target = "containsFloatingCharge"),
        @Mapping(source = "particulars.containsNegativeCharge", target = "containsNegativePledge"),
        @Mapping(source = "particulars.floatingChargeCoversAll", target = "floatingChargeCoversAll"),
    })
    public abstract Charge apiToCharge(ChargeApi chargeApi);

    public abstract List<Charge> apiToCharge(List<ChargeApi> chargeApi);

    @AfterMapping
    protected void convertSecuredDetails(ChargeApi chargeApi, @MappingTarget Charge charge) {

        if (hasType(chargeApi)) {

            charge.setSecuredDetailsType(
                retrieveApiEnumerationDescription.getApiEnumerationDescription(
                    MORTGAGE_DESCRIPTIONS, SECURED_DETAILS_DESCRIPTION,
                    chargeApi.getSecuredDetails().getType().getType()));
        }
    }

    private boolean hasType(ChargeApi chargeApi) {
        return chargeApi.getSecuredDetails() != null &&
            chargeApi.getSecuredDetails().getType() != null &&
            chargeApi.getSecuredDetails().getType().getType() != null;
    }
}

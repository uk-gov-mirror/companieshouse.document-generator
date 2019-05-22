package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail.decorators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uk.gov.companieshouse.api.model.insolvency.CaseApi;
import uk.gov.companieshouse.api.model.insolvency.InsolvencyApi;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail.ApiToCaseDatesMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail.ApiToCompanyStatusMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.companystatusdetail.ApiToPractitionersMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.CompanyStatusDetail;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.companystatusdetail.items.Case;

import java.util.ArrayList;
import java.util.List;

public class ApiToCompanyStatusMapperDecorator implements ApiToCompanyStatusMapper {

    @Autowired
    @Qualifier("delegate")
    private ApiToCompanyStatusMapper apiToCompanyStatusMapper;

    @Autowired
    private ApiToCaseDatesMapper apiToCaseDatesMapper;

    @Autowired
    private ApiToPractitionersMapper apiToPractitionersMapper;

    @Override
    public CompanyStatusDetail apiToCompanyStatusMapper(InsolvencyApi insolvencyApi) {

        CompanyStatusDetail companyStatusDetail = apiToCompanyStatusMapper.apiToCompanyStatusMapper(insolvencyApi);

        companyStatusDetail.setCases(setCases(insolvencyApi));

        return companyStatusDetail;
    }

    private List<Case> setCases(InsolvencyApi insolvencyApi) {

        List<Case> cases = new ArrayList<>();
        for(CaseApi caseApi : insolvencyApi.getCases()) {

            Case caseDetails = new Case();
            caseDetails.setNumber(caseApi.getNumber());
            caseDetails.setType(caseApi.getType().getType());
            caseDetails.setCaseDates(apiToCaseDatesMapper.apiToCaseDatesMapper(caseApi.getDates()));
            caseDetails.setPractitioners(apiToPractitionersMapper.apiToPractitionersMapper(caseApi.getPractitioners()));
            cases.add(caseDetails);
        }
        return cases;
    }
}

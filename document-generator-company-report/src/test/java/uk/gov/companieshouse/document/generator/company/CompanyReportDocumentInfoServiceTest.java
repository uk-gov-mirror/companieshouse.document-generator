package uk.gov.companieshouse.document.generator.company;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.companieshouse.document.generator.company.report.CompanyReportDocumentInfoServiceImpl;
import uk.gov.companieshouse.document.generator.company.report.handler.CompanyReportDataHandler;
import uk.gov.companieshouse.document.generator.interfaces.exception.DocumentInfoException;
import uk.gov.companieshouse.document.generator.interfaces.model.DocumentInfoRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompanyReportDocumentInfoServiceTest {

    @InjectMocks
    CompanyReportDataHandler companyReportDataHandler;

    @Test
    @DisplayName("Test regex gets company number containing letters from url")
    void testRegexGetsCompanyNumberContainingCharacters() {

        String result = companyReportDataHandler.getCompanyNumberFromUri("/company-number/CV2234554");

        assertEquals("CV2234554",result);
    }

    @Test
    @DisplayName("Test regex gets company number containing numeric only from url")
    void testRegexGetsCompanyNumberContainingNumericOnly() {

        String result = companyReportDataHandler.getCompanyNumberFromUri("/company-number/112234554");

        assertEquals("112234554",result);
    }
}

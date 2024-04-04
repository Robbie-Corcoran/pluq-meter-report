package com.meterreport.pluqmeterreport.unit;

import com.meterreport.pluqmeterreport.models.MeterReport;
import com.meterreport.pluqmeterreport.models.MeterValue;
import com.meterreport.pluqmeterreport.models.location.Connector;
import com.meterreport.pluqmeterreport.models.location.Evse;
import com.meterreport.pluqmeterreport.models.location.Location;
import com.meterreport.pluqmeterreport.services.LocationService;
import com.meterreport.pluqmeterreport.services.MeterReportService;
import com.meterreport.pluqmeterreport.services.MeterValueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MeterReportServiceTest {

    @Mock
    private LocationService locationService;

    @Mock
    private MeterValueService meterValueService;

    @InjectMocks
    private MeterReportService meterReportService;

    Location location = new Location();

    @BeforeEach
    void setup(){
        location.setId("00000000");
        location.setName("Test Location");
        location.setAddress("123 Test St");
        location.setCity("Test City");
        location.setCountry("Test Country");
    }

    @Test
    public void testGenerateMeterReportByLocationId_whenEvsesReturnEmptyList_generatesIncompleteReport() {
        // Arrange
        location.setEvses(Collections.emptyList());
        when(locationService.getLocationById(location.getId())).thenReturn(location);

        // Act
        MeterReport meterReport = meterReportService.generateMeterReportByLocationId(location.getId());

        // Assert
        assertFalse(meterReport.isReportComplete());
    }

    @Test
    public void testCalculateTotalKWhCharged() {
        // Arrange
        Evse evse1 = new Evse();
        evse1.setUid("uid1");
        List<MeterValue> meterValues1 = Arrays.asList(
                new MeterValue("transactionId1", "2024-04-01", "2024-04-01T10:00:00", "uid1", 1000.0),
                new MeterValue("transactionId2", "2024-04-02", "2024-04-02T10:00:00", "uid1", 2000.0)
        );

        Evse evse2 = new Evse();
        evse2.setUid("uid2");
        List<MeterValue> meterValues2 = Arrays.asList(
                new MeterValue("transactionId3", "2024-04-01", "2024-04-01T10:00:00", "uid2", 3000.0),
                new MeterValue("transactionId4", "2024-04-02", "2024-04-02T10:00:00", "uid2", 4000.0)
        );

        when(meterValueService.getMeterValuesByPhysicalReference(evse1.getUid())).thenReturn(meterValues1);
        when(meterValueService.getMeterValuesByPhysicalReference(evse2.getUid())).thenReturn(meterValues2);

        List<Evse> evseList = Arrays.asList(evse1, evse2);

        // Act
        double totalKWhCharged = meterReportService.calculateTotalKWhCharged(evseList);

        // Assert
        assertEquals(6000.0, totalKWhCharged);
    }
}

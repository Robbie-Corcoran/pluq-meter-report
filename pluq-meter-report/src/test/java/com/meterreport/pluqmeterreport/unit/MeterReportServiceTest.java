package com.meterreport.pluqmeterreport.unit;

import com.meterreport.pluqmeterreport.models.MeterReport;
import com.meterreport.pluqmeterreport.models.MeterValue;
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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MeterReportServiceTest {

    Location location = new Location();
    Evse evse1 = new Evse();
    Evse evse2 = new Evse();
    List<MeterValue> meterValues1 = new ArrayList<>();
    List<MeterValue> meterValues2 = new ArrayList<>();
    List<Evse> evseList = new ArrayList<>();
    @Mock
    private LocationService locationService;
    @Mock
    private MeterValueService meterValueService;
    @InjectMocks
    private MeterReportService meterReportService;

    @BeforeEach
    void setup() {
        location.setId("00000000");
        location.setName("Test Location");
        location.setAddress("123 Test St");
        location.setCity("Test City");
        location.setCountry("Test Country");
        evse1.setUid("uid1");
        meterValues1 = Arrays.asList(
                new MeterValue("transactionId1", "2024-04-01T00:00:00", "2024-04-01T10:00:00", "uid1", 1000.0),
                new MeterValue("transactionId2", "2024-04-02T00:00:00", "2024-04-02T10:00:00", "uid1", 2000.0)
        );

        evse2.setUid("uid2");
        meterValues2 = Arrays.asList(
                new MeterValue("transactionId3", "2024-04-01T00:00:00", "2024-04-01T10:00:00", "uid2", 3000.0),
                new MeterValue("transactionId4", "2024-04-02T00:00:00", "2024-04-02T10:00:00", "uid2", 4000.0)
        );
        evseList = Arrays.asList(evse1, evse2);
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
    public void testCalculateTotalKWhCharged_whenGivenCorrectData_returnsCorrectTotal() {
        // Arrange
        when(meterValueService.getMeterValuesByPhysicalReference(evse1.getUid())).thenReturn(meterValues1);
        when(meterValueService.getMeterValuesByPhysicalReference(evse2.getUid())).thenReturn(meterValues2);

        // Act
        double totalKWhCharged = meterReportService.calculateTotalKWhCharged(evseList);

        // Assert
        assertEquals(6000.0, totalKWhCharged);
    }

    @Test
    public void testCalculateNumberOfChargingSessions_whenGivenCorrectData_returnsCorrectTotal() {
        // Arrange
        when(meterValueService.getMeterValuesByPhysicalReference(evse1.getUid())).thenReturn(meterValues1);
        when(meterValueService.getMeterValuesByPhysicalReference(evse2.getUid())).thenReturn(meterValues2);

        // Act
        int numberOfChargingSessions = meterReportService.calculateNumberOfChargingSessions(evseList);

        // Assert
        assertEquals(4, numberOfChargingSessions);
    }

    @Test
    public void testCalculateKWhPerDayPerSocket_whenGivenCorrectData_returnsCorrectTotal() {
        // Arrange
        when(meterValueService.getMeterValuesByPhysicalReference(evse1.getUid())).thenReturn(meterValues1);
        when(meterValueService.getMeterValuesByPhysicalReference(evse2.getUid())).thenReturn(meterValues2);

        // Act
        double kWhPerDayPerSocket = meterReportService.calculateKWhPerDayPerSocket(evseList);

        // Assert
        assertEquals(1750.0, kWhPerDayPerSocket);
    }

    @Test
    public void testGroupMeterValuesByDay() {
        // Arrange
        when(meterValueService.getMeterValuesByPhysicalReference(evse1.getUid())).thenReturn(meterValues1);
        when(meterValueService.getMeterValuesByPhysicalReference(evse2.getUid())).thenReturn(meterValues2);

        // Act
        Map<String, List<MeterValue>> meterValuesByDay = meterReportService.groupMeterValuesByDay(evseList);

        // Assert
        assertEquals(2, meterValuesByDay.size());
        assertEquals(2, meterValuesByDay.get("2024-04-01").size());
        assertEquals(2, meterValuesByDay.get("2024-04-02").size());
    }
}

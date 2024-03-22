package ca.mcmaster.se2aa4.island.team109;  

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
    public void testConstructorWithTwoParameters() {
        String result = "TestResult";
        String distance = "10";
        Information info = new Information(result, distance);

        assertEquals(result, info.getResult());
        assertEquals(distance, info.getDistance());
    }

    @Test
    public void testConstructorWithOneParameter() {
        String result = "TestResult";
        Information info = new Information(result);

        assertEquals(result, info.getResult());
        assertNull(info.getDistance(), "Distance should be null for one-parameter constructor");
    }

    @Test
    public void testGetResult() {
        Information info = new Information("TestResult");
        assertEquals("TestResult", info.getResult());
    }

    @Test
    public void testGetDistance() {
        Information info = new Information("TestResult", "10");
        assertEquals("10", info.getDistance());
    }

    @Test
    public void testDefaultDistanceValue() {
        Information info = new Information("TestResult");
        assertEquals("unknown", info.getDistance(), "Default distance should be 'unknown'");
    }

    @Test
    public void testDefResults(){
        Information info = new Information();
        assertEquals("unknown", info.getResult());
    }


    @Test
    public void testConstructorWithOneParameter() {
        String result = "TestResult";
        Information info = new Information(result);

        assertEquals(result, info.getResult());
        assertNull(info.getDistance(), "Distance should be null for one-parameter constructor");
    }

    @Test
    public void testGetResult() {
        Information info = new Information("TestResult");
        assertEquals("TestResult", info.getResult());
    }

    @Test
    public void testGetDistance() {
        Information info = new Information("TestResult", "10");
        assertEquals("10", info.getDistance());
    }

    @Test
    public void testDefaultDistanceValue() {
        Information info = new Information("TestResult");
        assertEquals("unknown", info.getDistance(), "Default distance should be 'unknown'");
    }


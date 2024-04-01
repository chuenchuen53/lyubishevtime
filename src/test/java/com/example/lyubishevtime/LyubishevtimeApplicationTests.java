package com.example.lyubishevtime;

import com.example.lyubishevtime.hi.SimpleDestination;
import com.example.lyubishevtime.hi.SimpleSource;
import com.example.lyubishevtime.hi.SimpleSourceDestinationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LyubishevtimeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    SimpleSourceDestinationMapper simpleSourceDestinationMapper;

    @Test
    public void givenSourceToDestination_whenMaps_thenCorrect() {
        SimpleSource simpleSource = new SimpleSource();
        simpleSource.setName("SourceName");
        simpleSource.setDescription("SourceDescription");

        SimpleDestination destination = simpleSourceDestinationMapper.sourceToDestination(simpleSource);

        assertEquals(simpleSource.getName(), destination.getName());
        assertEquals(simpleSource.getDescription(), destination.getDescription());
    }

    @Test
    public void givenDestinationToSource_whenMaps_thenCorrect() {
        SimpleDestination destination = new SimpleDestination();
        destination.setName("DestinationName");
        destination.setDescription("DestinationDescription");

        SimpleSource source = simpleSourceDestinationMapper.destinationToSource(destination);

        assertEquals(destination.getName(), source.getName());
        assertEquals(destination.getDescription(), source.getDescription());
    }

    @Test
    public void hi() {
        SimpleSource simpleSource = new SimpleSource();
        simpleSource.setId(123);
        simpleSource.setName("my name");
        simpleSource.setDescription("my description");

        System.out.println("simpleSource = " + simpleSource);
        SimpleDestination destination = simpleSourceDestinationMapper.sourceToDestination(simpleSource);
        System.out.println("destination = " + destination);
    }
}

package com.example.testfile.bootstrap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class InitClass implements CommandLineRunner {

    @Value("classpath:properties.txt")
    private Resource injectedPath;

    @Value("${test.props.path}")
    private Resource injectedAtProperties;

    @Override
    public void run(String... args) throws Exception {

        //automatically load the class loader - FULL PATH
        Resource manualResource = new ClassPathResource("properties.txt");

        //indicating the class loader - FULL PATH
        Resource anotherWayTogGet = new ClassPathResource("properties.txt", this.getClass().getClassLoader());

        //indicating the relative path - RELATIVE PATH
        Resource relativeWayResource =
                new ClassPathResource("../../../../properties.txt", this.getClass());

        //from Resource we can jump to Java io File or InputStream
        System.out.println("manualResource path: " + manualResource.getFile().getAbsolutePath());

        System.out.println("anotherWayToGet path: " + anotherWayTogGet.getFile().getAbsolutePath());

        System.out.println("relativeWayResource path: " + relativeWayResource.getFile().getAbsolutePath());

        System.out.println("injected path: " + injectedPath.getFile().getAbsolutePath());

        System.out.println("injected at properties path: " + injectedAtProperties.getFile().getAbsolutePath());

        //FILE - Dealing on file system
        System.out.println("Manual resource: "  + new String(Files.readAllBytes(manualResource.getFile().toPath())));
        System.out.println("Another way to get resource: "  + new String(Files.readAllBytes(anotherWayTogGet.getFile().toPath())));
        System.out.println("Relative way to get resource: "  + new String(Files.readAllBytes(relativeWayResource.getFile().toPath())));
        System.out.println("Injected resource: "  + new String(Files.readAllBytes(injectedPath.getFile().toPath())));
        System.out.println("Injected at properties resource: "  + new String(Files.readAllBytes(injectedAtProperties.getFile().toPath())));

        //Convert manually
        byte[] bytesFromFile = Files.readAllBytes(manualResource.getFile().toPath());

        String convertedBytes = new String(bytesFromFile, StandardCharsets.UTF_8);
        System.out.println("Byte convert: " + convertedBytes);

        //INPUT STREAM - Dealing with jar
        InputStream manualInputStream = manualResource.getInputStream();
        InputStream anotherWayInputStream = anotherWayTogGet.getInputStream();
        InputStream relativeWayInputStream = relativeWayResource.getInputStream();
        InputStream injectedInputStream = injectedPath.getInputStream();
        InputStream injectedPropertiesInputStream = injectedAtProperties.getInputStream();

        //BufferedReader manualReader = new BufferedReader(new InputStreamReader(manualInputStream));
        //String manualJoined = manualReader.lines().collect(Collectors.joining("\n"));
        //System.out.println("Manual joined: " + manualJoined);

        BufferedReader anotherWayReader = new BufferedReader(new InputStreamReader(anotherWayInputStream));
        String anotherWayJoined = anotherWayReader.lines().collect(Collectors.joining("\n aa"));
        System.out.println("Another way joined: " + anotherWayJoined);

        BufferedReader relativeWayReader = new BufferedReader(new InputStreamReader(relativeWayInputStream));
        String relativeWayJoined = relativeWayReader.lines().collect(Collectors.joining());
        System.out.println("Relative way joined: " + relativeWayJoined);

        BufferedReader injectedReader = new BufferedReader(new InputStreamReader(injectedInputStream));
        String injectedReaderJoined = injectedReader.lines().collect(Collectors.joining("\n injected: "));
        System.out.println("Injected joined: " + injectedReaderJoined);

        BufferedReader injectedAtPropertiesReader = new BufferedReader(new InputStreamReader(injectedPropertiesInputStream));
        String injectedAtPropertiesReaderJoined = injectedAtPropertiesReader.lines().collect(Collectors.joining("\n injected prop: "));
        System.out.println("Injected at properties joined: " + injectedAtPropertiesReaderJoined);

        //Reading each line of file
        String line;
        BufferedReader manualReaderEachLine = new BufferedReader(new InputStreamReader(manualInputStream));
        while ((line = manualReaderEachLine.readLine()) != null) {
            System.out.println(line);
        }

    }
}

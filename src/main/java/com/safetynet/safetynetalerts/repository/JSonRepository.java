package com.safetynet.safetynetalerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Data
@Slf4j
@Repository
public class JSonRepository {
    /**
     * Property object with path to data source.
     */
    private final DataPathProperties dataPathProperties;
    /**
     * Path to JSON file.
     */
    private final String             datasource;
    /**
     * JSON file.
     */
    private final File jsonFile;
    /**
     * Object mapper.
     */
    ObjectMapper mapper = new ObjectMapper();

    public JSonRepository(DataPathProperties dataPathProperties) {
        this.dataPathProperties = dataPathProperties;
        this.datasource = dataPathProperties.getDatasource();
        this.jsonFile = new File(datasource);
    }


    /**
     * Reads all data from Json file.
     *
     * @return a json node
     */
    public JsonNode readJsonFile() {
        // prefer returning a NullNode object instead of a null value
        JsonNode completeDataFromJsonAsNode = NullNode.getInstance();
        log.debug("Data source path: {}", datasource);
        log.debug("Reading JSON file {}", jsonFile);

        try {
            completeDataFromJsonAsNode = mapper.readTree(jsonFile);
            log.info("Resulting JsonNode read from file: {}",
                     completeDataFromJsonAsNode);
            return completeDataFromJsonAsNode;
        } catch (IOException ioException) {
            log.error("Error while reading JSON file: ", ioException);
        }
        return completeDataFromJsonAsNode;
    }

    /**
     * Writes new node to Json file.
     *
     * @param nodeToAdd New data to add to JSON file
     *
     * @return true if no error occurred
     */
    public boolean writeJsonFile(JsonNode nodeToAdd) {
        log.debug("Writing data {} into JSON file {}", nodeToAdd, jsonFile);
        nodeToAdd = mapper.valueToTree(nodeToAdd);
        ArrayNode completeData = (ArrayNode) readJsonFile();
        completeData.add(nodeToAdd);

        try (FileWriter fw = new FileWriter(jsonFile)) {
            fw.write(completeData.toPrettyString());
            return true;
        } catch (IOException ioException) {
            log.error("Failed to write data {} into file {}: {}",
                      nodeToAdd.toString(), jsonFile, ioException);
            return false;
        }
    }
}
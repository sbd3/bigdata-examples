package com.example.apex.operators;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datatorrent.api.AutoMetric;
import com.datatorrent.api.Context.PortContext;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;

public class ClassifierOperator extends BaseOperator {

  private static final Logger logger = LoggerFactory.getLogger(ClassifierOperator.class);

  private static final String FILE_DELIMITER = "|";

  protected static final String[] metadataJsonAttributes = {"col1","col2"};;

  @AutoMetric
  private long tuplesCount;
  @AutoMetric
  private long errorTuples;

  public final transient DefaultOutputPort<String> strTupleOutPort = new DefaultOutputPort<>();
  public final transient DefaultOutputPort<Map<String, String>> mapTupleOutPort = new DefaultOutputPort<>();
  public final transient DefaultOutputPort<Object> errorPort = new DefaultOutputPort<>();

  // @InputPortFieldAnnotation(schemaRequired = true)
  public final transient DefaultInputPort<Object> input = new DefaultInputPort<Object>() {

    private ObjectMapper jsonParser;

    private final String NL = System.lineSeparator();
    private final Charset CS = StandardCharsets.UTF_8;

    public void setup(PortContext context) {
      jsonParser = new ObjectMapper();
    }

    @Override
    public void process(Object t) {
      String s = new String((byte[]) t, CS);
      processTuple(s);
    }

    private void processTuple(Object t) {
      try {
        if (isJson(t)) {
          processJson(t);
        } else {
          errorPort.emit(t);
          errorTuples++;
        }
      } catch (Exception ex) {
        logger.error("Error in expression eval: {}", ex.getMessage());
        logger.debug("Exception: ", ex);
        errorPort.emit(t);
        errorTuples++;
      }
    }

    private void processJson(Object t) throws IOException, JsonProcessingException {
      JsonNode rootNode = jsonParser.readTree(t.toString());
      Map<String, String> tuple = getMetadata(rootNode);
      strTupleOutPort.emit(StringUtils.join(tuple.values(), FILE_DELIMITER) + NL);
      mapTupleOutPort.emit(tuple);
      tuplesCount++;
    }

    public Map<String, String> getMetadata(JsonNode node) {
      Map<String, String> tuple = new HashMap<>();
      for (String attribute : metadataJsonAttributes) {
        String value = node.path(attribute).getTextValue();
        tuple.put(attribute, value);
      }
      return tuple;
    }
  };

  private boolean isJson(Object t) {
    return t.toString().trim().startsWith("{");
  }

  @Override
  public void beginWindow(long windowId) {
    super.beginWindow(windowId);
    errorTuples = tuplesCount = 0;
  }

}

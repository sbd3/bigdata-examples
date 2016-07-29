package com.example.apex.operators;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datatorrent.contrib.hbase.AbstractHBaseWindowPutOutputOperator;
import com.datatorrent.netlet.util.DTThrowable;

public class HBasePutOperator extends AbstractHBaseWindowPutOutputOperator<Map<String, String>> {

  private static final transient Logger logger = LoggerFactory.getLogger(HBasePutOperator.class);
  
  private static final String ROW_KEY = "c1";
  private static final String COL_FAM = "cf1";

  @Override
  public Put operationPut(Map<String, String> map) {
    if (logger.isDebugEnabled()) {
      logger.debug("Got row :: {}", map);
    }
    try {
      return parseMap(map);
    } catch (Exception e) {
      DTThrowable.rethrow(e);
      return null;
    }
  }

  public Put parseMap(Map<String, String> map) {
    logger.debug("Input Map: {}", map);
    Put put = new Put(map.get(ROW_KEY).getBytes());
    for (Entry<String, String> entry : map.entrySet()) {
      put.addColumn(COL_FAM.getBytes(), entry.getKey().getBytes(), entry.getValue().getBytes());
    }
    return put;
  }

}

package java8.pro.probs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java8.pro.probs.GnisId.GNIS_ID;

public class BuildTree {
    
    public static HashMap<Long, GnisId> objMap;

    public static void main(String[] args) throws IOException {
        BuildTree obj = new BuildTree();
        objMap = obj.parseRelationData(args[0]);
        System.out.println(objMap);
        Tree tree = obj.parseSQLRelationData(args[1]);
        System.out.println(tree.getRoot());
    }
    
    private Tree parseSQLRelationData(String filePath) throws FileNotFoundException, IOException {
        Reader file = new FileReader(new File(filePath));
        try (CSVParser parser = new CSVParser(file, CSVFormat.EXCEL)) {
            Tree rootNode = null;
            Node tempNode = null, prevNode = null;
            boolean first = true;
            long count = 0;
            HashMap<Long, Integer> ids = new HashMap<>();
            for (CSVRecord csvRecord : parser) {
                List<Node> children = null;
                long pValue = Long.parseLong(csvRecord.get(0));
                if(first) {
                    first = false;
                    rootNode = new Tree(pValue);
                    children = parseChildren(ids, csvRecord);
                    rootNode.getRoot().setChildren(children);
                    prevNode = rootNode.getRoot();
                } else {
                    for (Node child : prevNode.getChildren()) {
                        if(child.getData() == pValue) {
                            tempNode = child;
                        }
                    }
                    if(tempNode != null) {
                        children = parseChildren(ids, csvRecord);
                        tempNode.setChildren(children);
                        prevNode = tempNode;
                    } else {
                        count++;
                        System.out.println("Forest found!!!");
                    }
                    tempNode = null;
                }
                ids.put(prevNode.getData(), 0);
            }
            System.out.println(count);
            return rootNode;
            //System.out.println(rootNode.getRoot());
        }
    }

    private List<Node> parseChildren(HashMap<Long, Integer> ids, CSVRecord csvRecord) {
        List<Node> children = new ArrayList<>();
        for (int i = 1; i < csvRecord.size(); i++) {
            Node node = new Node(Long.parseLong(csvRecord.get(i)));
            if(ids.containsKey(node.getData())) {
                System.out.println(node.getData());
                throw new RuntimeException("Tree loop found");
            }
            children.add(node);
        }
        return children;
    }

    private HashMap<Long, GnisId> parseRelationData(String filePath) throws FileNotFoundException, IOException {
        Reader file = new FileReader(new File(filePath));
        try (CSVParser parser = new CSVParser(file, CSVFormat.EXCEL.withHeader(GNIS_ID.class).withTrim())) {
            HashMap<Long, GnisId> objMap = new HashMap<>();
            for (CSVRecord csvRecord : parser) {
                GnisId gnisIdRecord = parseGnisIdRecord(csvRecord);
                objMap.put(gnisIdRecord.getId(), gnisIdRecord);
            }
            return objMap;
        }
    }
    
    private GnisId parseGnisIdRecord(CSVRecord csvRecord) {
        long id = Long.parseLong(csvRecord.get(GNIS_ID.ID));
        String device_type = csvRecord.get(GNIS_ID.DEVICE_TYPE);
        String description = csvRecord.get(GNIS_ID.DESCRIPTION);
        String gnis_table_name = csvRecord.get(GNIS_ID.GNIS_TABLE_NAME);
        long gnis_id = Long.parseLong(csvRecord.get(GNIS_ID.GNIS_ID));
        double latitude = StringUtils.isEmpty(csvRecord.get(GNIS_ID.LATITUDE)) ? 0
                : Double.parseDouble(csvRecord.get(GNIS_ID.LATITUDE));
        double longitude = StringUtils.isEmpty(csvRecord.get(GNIS_ID.LONGITUDE)) ? 0
                : Double.parseDouble(csvRecord.get(GNIS_ID.LONGITUDE));
        long house_id = StringUtils.isEmpty(csvRecord.get(GNIS_ID.HOUSE_ID)) || StringUtils.equals(csvRecord.get(GNIS_ID.HOUSE_ID), "No House ID") ? 0
                : Long.parseLong(csvRecord.get(GNIS_ID.HOUSE_ID));
        long power_supply_name = 0;
        try {
            power_supply_name = Long.parseLong(csvRecord.get(GNIS_ID.POWER_SUPPLY_NAME));
        } catch(Exception e) {
            
        }
        long node_id = Long.parseLong(csvRecord.get(GNIS_ID.NODE_ID));
        long upstream_device_id = StringUtils.isEmpty(csvRecord.get(GNIS_ID.UPSTREAM_DEVICE_ID)) ? 0
                : Long.parseLong(csvRecord.get(GNIS_ID.UPSTREAM_DEVICE_ID));
        return new GnisId(id, device_type, description, gnis_table_name, gnis_id, latitude, longitude, house_id,
                power_supply_name, node_id, upstream_device_id);
    }

    /*private GnisId parseGnisIdRecord(CSVRecord csvRecord) {
        long id = Long.parseLong(csvRecord.get(GNIS_ID.ID));
        String device_type = csvRecord.get(GNIS_ID.DEVICE_TYPE);
        String description = csvRecord.get(GNIS_ID.DESCRIPTION);
        String gnis_table_name = csvRecord.get(GNIS_ID.GNIS_TABLE_NAME);
        long gnis_id = Long.parseLong(csvRecord.get(GNIS_ID.GNIS_ID));
        double latitude = Double.parseDouble(csvRecord.get(GNIS_ID.LATITUDE));
        double longitude = Double.parseDouble(csvRecord.get(GNIS_ID.LONGITUDE));
        long house_id = StringUtils.isEmpty(csvRecord.get(GNIS_ID.HOUSE_ID)) ? 0
                : Long.parseLong(csvRecord.get(GNIS_ID.HOUSE_ID));
        long power_supply_name = StringUtils.isEmpty(csvRecord.get(GNIS_ID.POWER_SUPPLY_NAME)) ? 0
                : Long.parseLong(csvRecord.get(GNIS_ID.POWER_SUPPLY_NAME));
        long node_id = Long.parseLong(csvRecord.get(GNIS_ID.NODE_ID));
        long upstream_device_id = Long.parseLong(csvRecord.get(GNIS_ID.UPSTREAM_DEVICE_ID));
        return new GnisId(id, device_type, description, gnis_table_name, gnis_id, latitude, longitude,
                house_id, power_supply_name, node_id, upstream_device_id);
    }*/

}

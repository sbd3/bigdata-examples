package java8.pro.probs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedSubgraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.CrossComponentIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java8.pro.probs.GnisId.GNIS_ID;

public class JGraphTest {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        JGraphTest test = new JGraphTest();
        HashMap<Long, GnisId> objMap = test.parseRelationData(args[0]);
        DirectedGraph<Long, DefaultEdge> directedGraph = test.createGraph(objMap);
        DirectedSubgraph<Long, DefaultEdge> subgraph = new DirectedSubgraph<>(directedGraph);
        
        //test.topologicalOrdering(directedGraph);
        //test.traverseBreadth(directedGraph);
        //test.traverseDepth(directedGraph);
        //test.findCycles(directedGraph);
        //test.findConnectedComponents(directedGraph);
        //test.findStronglyConnectedComponents(directedGraph);
        //test.findShortestPath(directedGraph);
        //System.out.println(directedGraph.vertexSet().size());
    }

    private void topologicalOrdering(DirectedGraph<Long, DefaultEdge> directedGraph) {
        TopologicalOrderIterator<Long, DefaultEdge> iter = new TopologicalOrderIterator<>(directedGraph);
        System.out.println(iter.getGraph());
    }

    private void traverseBreadth(DirectedGraph<Long, DefaultEdge> directedGraph) {
        GraphIterator<Long, DefaultEdge> iterator = new BreadthFirstIterator<Long, DefaultEdge>(directedGraph);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private void traverseDepth(DirectedGraph<Long, DefaultEdge> directedGraph) {
        GraphIterator<Long, DefaultEdge> iterator = new DepthFirstIterator<Long, DefaultEdge>(directedGraph);
        while (iterator.hasNext()) {
            System.out.println( iterator.next() );
        }
    }

    private DirectedGraph<Long, DefaultEdge> createGraph(HashMap<Long, GnisId> objMap) {
        DirectedGraph<Long, DefaultEdge> directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        for (Entry<Long, GnisId> entry : objMap.entrySet()) {
            directedGraph.addVertex(entry.getValue().getId());
            directedGraph.addVertex(entry.getValue().getUpstream_device_id());
            directedGraph.addEdge(entry.getValue().getId(), entry.getValue().getUpstream_device_id());
        }
        return directedGraph;
    }

    private void findCycles(DirectedGraph<Long, DefaultEdge> directedGraph) {
        CycleDetector<Long, DefaultEdge> detector = new CycleDetector<>(directedGraph);
        System.out.println(detector.findCycles().size());
    }

    /**
     * Print all connected components for graph
     * @param directedGraph
     */
    private void findConnectedComponents(DirectedGraph<Long, DefaultEdge> directedGraph) {
        ConnectivityInspector<Long, DefaultEdge> insp = new ConnectivityInspector<>(directedGraph);
        for(Set<Long> component : insp.connectedSets()) {
            System.out.println(component);
        }
        System.out.println("No. of connected components: "+insp.connectedSets().size());
    }

    /**
     * Prints the shortest path from vertex i to vertex c.
     * @param directedGraph
     */
    private void findShortestPath(DirectedGraph<Long, DefaultEdge> directedGraph) {
        System.out.println("Shortest path from i to c:");
        DijkstraShortestPath<Long, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(directedGraph);
        SingleSourcePaths<Long, DefaultEdge> iPaths = dijkstraAlg.getPaths(543149177L);
        System.out.println(iPaths.getPath(543149165L) + "\n");
    }

    /**
     * Computes all the strongly connected components of the directed graph
     * @param directedGraph
     */
    private void findStronglyConnectedComponents(DirectedGraph<Long, DefaultEdge> directedGraph) {
        StrongConnectivityAlgorithm<Long, DefaultEdge> scAlg = new KosarajuStrongConnectivityInspector<>(directedGraph);
        List<DirectedSubgraph<Long, DefaultEdge>> stronglyConnectedSubgraphs = scAlg.stronglyConnectedSubgraphs();

        // prints the strongly connected components
        System.out.println("Strongly connected components:");
        for (int i = 0; i < stronglyConnectedSubgraphs.size(); i++) {
            System.out.println(stronglyConnectedSubgraphs.get(i));
        }
    }

    private HashMap<Long, GnisId> parseRelationData(String filePath) throws FileNotFoundException, IOException {
        Reader file = new FileReader(new File(filePath));
        try (CSVParser parser = new CSVParser(file, CSVFormat.EXCEL.withHeader(GNIS_ID.class).withTrim())) {
            HashMap<Long, GnisId> objMap = new HashMap<>();
            int count = 0;
            for (CSVRecord csvRecord : parser) {
                if(count < 2) {
                    count++;
                    continue;
                }
                GnisId gnisIdRecord = parseGnisIdRecord(csvRecord);
                objMap.put(gnisIdRecord.getId(), gnisIdRecord);
            }
            return objMap;
        }
    }
    
    private static int COUNTER = 0;

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
        long upstream_device_id = csvRecord.get(GNIS_ID.UPSTREAM_DEVICE_ID).length() == 0 ? --COUNTER
                : Long.parseLong(csvRecord.get(GNIS_ID.UPSTREAM_DEVICE_ID));
        return new GnisId(id, device_type, description, gnis_table_name, gnis_id, latitude, longitude, house_id,
                power_supply_name, node_id, upstream_device_id);
    }
}

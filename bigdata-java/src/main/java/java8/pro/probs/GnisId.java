package java8.pro.probs;

public class GnisId {
    
    public enum GNIS_ID {
        ID, DEVICE_TYPE, DESCRIPTION, GNIS_TABLE_NAME, GNIS_ID, LATITUDE, LONGITUDE, HOUSE_ID, POWER_SUPPLY_NAME, NODE_ID, UPSTREAM_DEVICE_ID
    }

    private long id;
    private String device_type;
    private String description;
    private String gnis_table_name;
    private long gnis_id;
    private double latitude;
    private double longitude;
    private long house_id;
    private long power_supply_name;
    private long node_id;
    private long upstream_device_id;
    
    public GnisId(long id, String device_type, String description, String gnis_table_name, long gnis_id, double latitude,
            double longitude, long house_id, long power_supply_name, long node_id, long upstream_device_id) {
        super();
        this.id = id;
        this.device_type = device_type;
        this.description = description;
        this.gnis_table_name = gnis_table_name;
        this.gnis_id = gnis_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.house_id = house_id;
        this.power_supply_name = power_supply_name;
        this.node_id = node_id;
        this.upstream_device_id = upstream_device_id;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDevice_type() {
        return device_type;
    }
    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getGnis_table_name() {
        return gnis_table_name;
    }
    public void setGnis_table_name(String gnis_table_name) {
        this.gnis_table_name = gnis_table_name;
    }
    public long getGnis_id() {
        return gnis_id;
    }
    public void setGnis_id(long gnis_id) {
        this.gnis_id = gnis_id;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public long getHouse_id() {
        return house_id;
    }
    public void setHouse_id(long house_id) {
        this.house_id = house_id;
    }
    public long getPower_supply_name() {
        return power_supply_name;
    }
    public void setPower_supply_name(long power_supply_name) {
        this.power_supply_name = power_supply_name;
    }
    public long getNode_id() {
        return node_id;
    }
    public void setNode_id(long node_id) {
        this.node_id = node_id;
    }
    public long getUpstream_device_id() {
        return upstream_device_id;
    }
    public void setUpstream_device_id(long upstream_device_id) {
        this.upstream_device_id = upstream_device_id;
    }
    @Override
    public String toString() {
        return "{\"id\" : " + id + ", \"device_type\" : \"" + device_type + "\", \"description\" : \"" + description
                + "\", \"gnis_table_name\" : \"" + gnis_table_name + "\", \"gnis_id\" : " + gnis_id + ", \"latitude\" : " + latitude
                + ", \"longitude\" : " + longitude + ", \"house_id\" : " + house_id + ", \"power_supply_name\" : " + power_supply_name
                + ", \"node_id\" : " + node_id + ", \"upstream_device_id\" : " + upstream_device_id + "}";
    }
    
}

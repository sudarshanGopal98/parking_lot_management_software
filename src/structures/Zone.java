package structures;

import java.awt.*;

/**
 * Created by Sudarshan on 2/22/2017.
 */
public class Zone {
    int zoneID;
    Polygon shape;
    String name;
    int startIndex;
    int endIndex;

    public Zone(int zoneID, Polygon shape, String name, int startIndex, int endIndex) {
        this.shape = shape;
        this.name = name;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getZoneID() {
        return zoneID;
    }

    public Polygon getShape() {
        return shape;
    }

    public String getName() {
        return name;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}

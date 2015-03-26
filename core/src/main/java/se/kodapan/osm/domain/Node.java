package se.kodapan.osm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kalle
 * @since 2013-05-01 15:42
 */
public class Node extends OsmObject implements Serializable {

  private static final long serialVersionUID = 1l;

  @Override
  public <R> R accept(OsmObjectVisitor<R> visitor) {
    return visitor.visit(this);
  }

  public Node() {
  }

  public Node(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude= longitude;
  }

  public Node(Long id, double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
    setId(id);
  }

  public Node(double latitude, double longitude, String... tags) {
    this((Long)null, latitude, longitude, tags);
  }

  public Node(Long id, double latitude, double longitude, String... tags) {
    this.latitude = latitude;
    this.longitude = longitude;
    setId(id);
    for (int i=0; i<tags.length; i+=2) {
      setTag(tags[i], tags[i+1]);
    }
  }

  private double latitude;
  private double longitude;

  private List<Way> waysMemberships;

  public void addWayMembership(Way way) {
    if (waysMemberships == null) {
      waysMemberships = new ArrayList<Way>(5);
    } else {
      // don't add membership to the same way twice
      // this happens for instance when this is the start and stop in a polygon.
      for (Way wayMembership : waysMemberships) {
        if (way.equals(wayMembership)) {
          return;
        }
      }
    }
    waysMemberships.add(way);
  }

  public List<Way> getWaysMemberships() {
    return waysMemberships;
  }

  public void setWaysMemberships(List<Way> waysMemberships) {
    this.waysMemberships = waysMemberships;
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

  public double getY() {
    return getLatitude();
  }

  public void setY(double latitude) {
    setLatitude(latitude);
  }

  public double getX() {
    return getLongitude();
  }

  public void setX(double longitude) {
    setLongitude(longitude);
  }


  @Override
  public String toString() {
    return "Node{" +
        super.toString() +
        "latitude=" + latitude +
        ", longitude=" + longitude +
        ", waysMemberships.size=" + (waysMemberships == null ? "null" : waysMemberships.size()) +
        '}';
  }
}

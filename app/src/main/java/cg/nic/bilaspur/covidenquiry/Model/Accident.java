package cg.nic.bilaspur.covidenquiry.Model;

public class Accident {


    public String address;
    public String landmark;
    public String type;
    public String description;
    public String location;

    public String CreatedAt;
    public String Status;


    public byte[] photo;


    public Accident() {

    }

    public Accident(String address, String landmark, String type, String description, String location, String createdAt, String status, byte[] photo) {
        this.address = address;
        this.landmark = landmark;
        this.type = type;
        this.description = description;
        this.location = location;
        CreatedAt = createdAt;
        Status = status;
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

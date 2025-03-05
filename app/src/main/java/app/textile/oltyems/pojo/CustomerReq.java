package app.textile.oltyems.pojo;

public class CustomerReq {
    String party_name, email, phone, country, destination_port,
    address;

    public CustomerReq(String party_name, String email, String phone, String country, String destination_port, String address) {
        this.party_name = party_name;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.destination_port = destination_port;
        this.address = address;
    }

    public String getParty_name() {
        return party_name;
    }

    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDestination_port() {
        return destination_port;
    }

    public void setDestination_port(String destination_port) {
        this.destination_port = destination_port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

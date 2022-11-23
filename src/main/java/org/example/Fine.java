package org.example;

import java.util.Objects;

public class Fine {
    private final String protocol;
    private final float sum;
    private final String type;
    private final String city;
    private final String description;

    public Fine(String protocol, float sum, String type, String city, String description) {
        this.protocol = protocol;
        this.sum = sum;
        this.type = type;
        this.city = city;
        this.description = description;
    }

    protected String getProtocol() {
        return protocol;
    }

    protected String getType() {
        return type;
    }

    protected String getCity() {
        return city;
    }

    protected String fineShow() {
        String formattedSum = String.format("%.02f", sum);
        return "\tProtocol " + protocol + ", sum: " + formattedSum + ", type: " + type +
                ", city: " + city + ",\n\tDescription: " + description;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "protocol='" + protocol + '\'' +
                ", sum=" + sum +
                ", type='" + type + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fine fine = (Fine) o;

        if (Float.compare(fine.sum, sum) != 0) return false;
        if (!protocol.equals(fine.protocol)) return false;
        if (!Objects.equals(type, fine.type)) return false;
        if (!Objects.equals(city, fine.city)) return false;
        return Objects.equals(description, fine.description);
    }

    @Override
    public int hashCode() {
        int result = protocol.hashCode();
        result = 31 * result + (sum != 0.0f ? Float.floatToIntBits(sum) : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}

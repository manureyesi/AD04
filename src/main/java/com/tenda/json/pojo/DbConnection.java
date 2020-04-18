
package com.tenda.json.pojo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DbConnection implements Serializable
{

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("port")
    @Expose
    private String port;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("password")
    @Expose
    private String password;
    private final static long serialVersionUID = -1302630522686328617L;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("address", address).append("port", port).append("name", name).append("user", user).append("password", password).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(password).append(address).append(port).append(user).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DbConnection) == false) {
            return false;
        }
        DbConnection rhs = ((DbConnection) other);
        return new EqualsBuilder().append(name, rhs.name).append(password, rhs.password).append(address, rhs.address).append(port, rhs.port).append(user, rhs.user).isEquals();
    }

}

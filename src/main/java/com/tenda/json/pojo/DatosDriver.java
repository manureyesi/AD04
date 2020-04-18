
package com.tenda.json.pojo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DatosDriver implements Serializable
{

    @SerializedName("dbConnection")
    @Expose
    private DbConnection dbConnection;
    @SerializedName("hibernate")
    @Expose
    private Hibernate hibernate;
    private final static long serialVersionUID = 7631466114958525965L;

    public DbConnection getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Hibernate getHibernate() {
        return hibernate;
    }

    public void setHibernate(Hibernate hibernate) {
        this.hibernate = hibernate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dbConnection", dbConnection).append("hibernate", hibernate).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(hibernate).append(dbConnection).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DatosDriver) == false) {
            return false;
        }
        DatosDriver rhs = ((DatosDriver) other);
        return new EqualsBuilder().append(hibernate, rhs.hibernate).append(dbConnection, rhs.dbConnection).isEquals();
    }

}


package com.tenda.json.pojo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Hibernate implements Serializable
{

    @SerializedName("driver")
    @Expose
    private String driver;
    @SerializedName("dialect")
    @Expose
    private String dialect;
    @SerializedName("HBM2DDL_AUTO")
    @Expose
    private String hBM2DDLAUTO;
    @SerializedName("SHOW_SQL")
    @Expose
    private Boolean sHOWSQL;
    private final static long serialVersionUID = -6096396637206640794L;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getHBM2DDLAUTO() {
        return hBM2DDLAUTO;
    }

    public void setHBM2DDLAUTO(String hBM2DDLAUTO) {
        this.hBM2DDLAUTO = hBM2DDLAUTO;
    }

    public Boolean getSHOWSQL() {
        return sHOWSQL;
    }

    public void setSHOWSQL(Boolean sHOWSQL) {
        this.sHOWSQL = sHOWSQL;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("driver", driver).append("dialect", dialect).append("hBM2DDLAUTO", hBM2DDLAUTO).append("sHOWSQL", sHOWSQL).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sHOWSQL).append(dialect).append(hBM2DDLAUTO).append(driver).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Hibernate) == false) {
            return false;
        }
        Hibernate rhs = ((Hibernate) other);
        return new EqualsBuilder().append(sHOWSQL, rhs.sHOWSQL).append(dialect, rhs.dialect).append(hBM2DDLAUTO, rhs.hBM2DDLAUTO).append(driver, rhs.driver).isEquals();
    }

}

package com.aggarwalh.pgstarter.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Simple Business POJO (Non-nested)
 */
public class FlatObject {

    private Long id;
    private String strAttr1;
    private String strAttr2;
    private Integer intAttr1;
    private Integer intAttr2;
    private Long longAttr1;
    private Long longAttr2;
    private Double doubleAttr1;
    private Double doubleAttr2;
    private BigDecimal bigDecimalAttr1;
    private BigDecimal bigDecimalAttr2;
    private LocalDate dateAttr1;     // stores only date part
    private LocalDateTime dateAttr2; // stores date and time

    public FlatObject() {
    }

    /**
     * Special handling for {@link BigDecimal} type
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlatObject that = (FlatObject) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (strAttr1 != null ? !strAttr1.equals(that.strAttr1) : that.strAttr1 != null) return false;
        if (strAttr2 != null ? !strAttr2.equals(that.strAttr2) : that.strAttr2 != null) return false;
        if (intAttr1 != null ? !intAttr1.equals(that.intAttr1) : that.intAttr1 != null) return false;
        if (intAttr2 != null ? !intAttr2.equals(that.intAttr2) : that.intAttr2 != null) return false;
        if (longAttr1 != null ? !longAttr1.equals(that.longAttr1) : that.longAttr1 != null) return false;
        if (longAttr2 != null ? !longAttr2.equals(that.longAttr2) : that.longAttr2 != null) return false;
        if (doubleAttr1 != null ? !doubleAttr1.equals(that.doubleAttr1) : that.doubleAttr1 != null) return false;
        if (doubleAttr2 != null ? !doubleAttr2.equals(that.doubleAttr2) : that.doubleAttr2 != null) return false;
        if (dateAttr1 != null ? !dateAttr1.equals(that.dateAttr1) : that.dateAttr1 != null) return false;
        if (dateAttr2 != null ? !dateAttr2.equals(that.dateAttr2) : that.dateAttr2 != null) return false;
        // non trivial logic for BigDecimal.
        if (bigDecimalAttr1 != null ? !(bigDecimalAttr1.compareTo(that.bigDecimalAttr1) == 0) : that.bigDecimalAttr1 != null)
            return false;
        if (bigDecimalAttr2 != null ? !(bigDecimalAttr2.compareTo(that.bigDecimalAttr2) == 0) : that.bigDecimalAttr2 != null)
            return false;

        return true;
    }

    /**
     * Special handling for {@link BigDecimal} type. Another option is to not include this attribute in hashcode.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (strAttr1 != null ? strAttr1.hashCode() : 0);
        result = 31 * result + (strAttr2 != null ? strAttr2.hashCode() : 0);
        result = 31 * result + (intAttr1 != null ? intAttr1.hashCode() : 0);
        result = 31 * result + (intAttr2 != null ? intAttr2.hashCode() : 0);
        result = 31 * result + (longAttr1 != null ? longAttr1.hashCode() : 0);
        result = 31 * result + (longAttr2 != null ? longAttr2.hashCode() : 0);
        result = 31 * result + (doubleAttr1 != null ? doubleAttr1.hashCode() : 0);
        result = 31 * result + (doubleAttr2 != null ? doubleAttr2.hashCode() : 0);
        result = 31 * result + (dateAttr1 != null ? dateAttr1.hashCode() : 0);
        result = 31 * result + (dateAttr2 != null ? dateAttr2.hashCode() : 0);
        result = 31 * result + (bigDecimalAttr1 != null ? (new Double(bigDecimalAttr1.doubleValue())).hashCode() : 0);
        result = 31 * result + (bigDecimalAttr2 != null ? (new Double(bigDecimalAttr2.doubleValue())).hashCode()  : 0);

        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrAttr1() {
        return strAttr1;
    }

    public void setStrAttr1(String strAttr1) {
        this.strAttr1 = strAttr1;
    }

    public String getStrAttr2() {
        return strAttr2;
    }

    public void setStrAttr2(String strAttr2) {
        this.strAttr2 = strAttr2;
    }

    public Integer getIntAttr1() {
        return intAttr1;
    }

    public void setIntAttr1(Integer intAttr1) {
        this.intAttr1 = intAttr1;
    }

    public Integer getIntAttr2() {
        return intAttr2;
    }

    public void setIntAttr2(Integer intAttr2) {
        this.intAttr2 = intAttr2;
    }

    public Long getLongAttr1() {
        return longAttr1;
    }

    public void setLongAttr1(Long longAttr1) {
        this.longAttr1 = longAttr1;
    }

    public Long getLongAttr2() {
        return longAttr2;
    }

    public void setLongAttr2(Long longAttr2) {
        this.longAttr2 = longAttr2;
    }

    public Double getDoubleAttr1() {
        return doubleAttr1;
    }

    public void setDoubleAttr1(Double doubleAttr1) {
        this.doubleAttr1 = doubleAttr1;
    }

    public Double getDoubleAttr2() {
        return doubleAttr2;
    }

    public void setDoubleAttr2(Double doubleAttr2) {
        this.doubleAttr2 = doubleAttr2;
    }

    public BigDecimal getBigDecimalAttr1() {
        return bigDecimalAttr1;
    }

    public void setBigDecimalAttr1(BigDecimal bigDecimalAttr1) {
        this.bigDecimalAttr1 = bigDecimalAttr1;
    }

    public BigDecimal getBigDecimalAttr2() {
        return bigDecimalAttr2;
    }

    public void setBigDecimalAttr2(BigDecimal bigDecimalAttr2) {
        this.bigDecimalAttr2 = bigDecimalAttr2;
    }

    public LocalDate getDateAttr1() {
        return dateAttr1;
    }

    public void setDateAttr1(LocalDate dateAttr1) {
        this.dateAttr1 = dateAttr1;
    }

    public LocalDateTime getDateAttr2() {
        return dateAttr2;
    }

    public void setDateAttr2(LocalDateTime dateAttr2) {
        this.dateAttr2 = dateAttr2;
    }

}
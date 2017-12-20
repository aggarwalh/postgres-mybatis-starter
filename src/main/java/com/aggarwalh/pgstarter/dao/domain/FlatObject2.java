package com.aggarwalh.pgstarter.dao.domain;

import com.aggarwalh.pgstarter.dao.dao.json.AppLayerParsedConcretePOJOMapper;
import com.aggarwalh.pgstarter.dao.dao.typehandlers.JsonToFlatObjectTypeHandler;

import java.util.Date;

/**
 * This is dupe of {@link FlatObject}. {@link FlatObject} is bound by custom {@link org.apache.ibatis.type.TypeHandler},
 * i.e. {@link JsonToFlatObjectTypeHandler}, thus needed a different object to
 * demonstrate different db row to java pojo mapping via {@link AppLayerParsedConcretePOJOMapper}
 */
public class FlatObject2 {

    private Long id;
    private String strAttr1;
    private String strAttr2;
    private Integer intAttr1;
    private Integer intAttr2;
    private Long longAttr1;
    private Long longAttr2;
    private Double doubleAttr1;
    private Double doubleAttr2;
    private Date dateAttr1;
    private Date dateAttr2;

    public FlatObject2() {
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

    public Date getDateAttr1() {
        return dateAttr1;
    }

    public void setDateAttr1(Date dateAttr1) {
        this.dateAttr1 = dateAttr1;
    }

    public Date getDateAttr2() {
        return dateAttr2;
    }

    public void setDateAttr2(Date dateAttr2) {
        this.dateAttr2 = dateAttr2;
    }
}
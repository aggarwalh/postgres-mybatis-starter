package com.aggarwalh.pgstarter.dao.domain;

import java.util.Map;

/**
 * Wrapper for modelling flat json (non-nested) to Map based representation.
 */
public class FlatJsonWrapper {

    Map<String, Object> refAttrMap;

    public FlatJsonWrapper() {
    }

    public FlatJsonWrapper(Map<String, Object> refAttrMap) {
        this.refAttrMap = refAttrMap;
    }

    public Map<String, Object> getRefAttrMap() {
        return refAttrMap;
    }

    public void setRefAttrMap(Map<String, Object> refAttrMap) {
        this.refAttrMap = refAttrMap;
    }
}

package it.algo.theharvestar.domain;

import org.cloudtm.framework.ispn.IspnTxManager;
import org.cloudtm.framework.ispn.collections.bplustree.BPlusTree;

public abstract class Agent_Base extends org.cloudtm.framework.ispn.AbstractDomainObject {
    
    public  Agent_Base() {
        
    }
    
    public java.lang.String getStatus() {
        Object obj = IspnTxManager.cacheGet(getOid() + ":status");
        if (obj == null || obj instanceof NullClass) return null;
        return (java.lang.String)obj;
    }
    
    public void setStatus(java.lang.String status) {
        IspnTxManager.cachePut(getOid() + ":status", (status == null ? NULL_OBJECT : status));
    }
    
    public java.lang.String getUser() {
        Object obj = IspnTxManager.cacheGet(getOid() + ":user");
        if (obj == null || obj instanceof NullClass) return null;
        return (java.lang.String)obj;
    }
    
    public void setUser(java.lang.String user) {
        IspnTxManager.cachePut(getOid() + ":user", (user == null ? NULL_OBJECT : user));
    }
    
}

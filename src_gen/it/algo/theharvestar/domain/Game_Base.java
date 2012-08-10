package it.algo.theharvestar.domain;

import org.cloudtm.framework.ispn.IspnTxManager;
import org.cloudtm.framework.ispn.collections.bplustree.BPlusTree;

public abstract class Game_Base extends org.cloudtm.framework.ispn.AbstractDomainObject {
    public static dml.runtime.Role<it.algo.theharvestar.domain.Game,it.algo.theharvestar.domain.Root> role$$root = new dml.runtime.Role<it.algo.theharvestar.domain.Game,it.algo.theharvestar.domain.Root>() {
        public void add(it.algo.theharvestar.domain.Game o1, it.algo.theharvestar.domain.Root o2, dml.runtime.Relation<it.algo.theharvestar.domain.Game,it.algo.theharvestar.domain.Root> relation) {
            if (o1 != null) {
                it.algo.theharvestar.domain.Root old2 = o1.getRoot();
                if (o2 != old2) {
                    relation.remove(o1, old2);
                    o1.setRoot$unidirectional(o2);
                }
            }
        }
        public void remove(it.algo.theharvestar.domain.Game o1, it.algo.theharvestar.domain.Root o2) {
            if (o1 != null) {
                o1.setRoot$unidirectional(null);
            }
        }
        public dml.runtime.Role<it.algo.theharvestar.domain.Root,it.algo.theharvestar.domain.Game> getInverseRole() {
            return it.algo.theharvestar.domain.Root.role$$games;
        }
        
    };
    public static dml.runtime.DirectRelation<it.algo.theharvestar.domain.Game,it.algo.theharvestar.domain.Root> RootHasGames = new dml.runtime.DirectRelation<it.algo.theharvestar.domain.Game,it.algo.theharvestar.domain.Root>(role$$root);
    static {
        it.algo.theharvestar.domain.Root.RootHasGames = RootHasGames.getInverseRelation();
    }
    
    public  Game_Base() {
        
    }
    
    public java.lang.String getState() {
        Object obj = IspnTxManager.cacheGet(getOid() + ":state");
        if (obj == null || obj instanceof NullClass) return null;
        return (java.lang.String)obj;
    }
    
    public void setState(java.lang.String state) {
        IspnTxManager.cachePut(getOid() + ":state", (state == null ? NULL_OBJECT : state));
    }
    
    public java.lang.String getName() {
        Object obj = IspnTxManager.cacheGet(getOid() + ":name");
        if (obj == null || obj instanceof NullClass) return null;
        return (java.lang.String)obj;
    }
    
    public void setName(java.lang.String name) {
        IspnTxManager.cachePut(getOid() + ":name", (name == null ? NULL_OBJECT : name));
    }
    
    public java.lang.String getFormat() {
        Object obj = IspnTxManager.cacheGet(getOid() + ":format");
        if (obj == null || obj instanceof NullClass) return null;
        return (java.lang.String)obj;
    }
    
    public void setFormat(java.lang.String format) {
        IspnTxManager.cachePut(getOid() + ":format", (format == null ? NULL_OBJECT : format));
    }
    
    public java.lang.Integer getVersion() {
        Object obj = IspnTxManager.cacheGet(getOid() + ":version");
        if (obj == null || obj instanceof NullClass) return null;
        return (java.lang.Integer)obj;
    }
    
    public void setVersion(java.lang.Integer version) {
        IspnTxManager.cachePut(getOid() + ":version", (version == null ? NULL_OBJECT : version));
    }
    
    public java.lang.Integer getSocialCount() {
        Object obj = IspnTxManager.cacheGet(getOid() + ":socialCount");
        if (obj == null || obj instanceof NullClass) return null;
        return (java.lang.Integer)obj;
    }
    
    public void setSocialCount(java.lang.Integer socialCount) {
        IspnTxManager.cachePut(getOid() + ":socialCount", (socialCount == null ? NULL_OBJECT : socialCount));
    }
    
    public java.lang.Integer getTransportCount() {
        Object obj = IspnTxManager.cacheGet(getOid() + ":transportCount");
        if (obj == null || obj instanceof NullClass) return null;
        return (java.lang.Integer)obj;
    }
    
    public void setTransportCount(java.lang.Integer transportCount) {
        IspnTxManager.cachePut(getOid() + ":transportCount", (transportCount == null ? NULL_OBJECT : transportCount));
    }
    
    public it.algo.theharvestar.domain.Root getRoot() {
        Object oid = IspnTxManager.cacheGet(getOid() + ":root");
        return (oid == null || oid instanceof NullClass ? null : (it.algo.theharvestar.domain.Root)fromOid((String)oid));
    }
    
    public void setRoot(it.algo.theharvestar.domain.Root root) {
        RootHasGames.add((it.algo.theharvestar.domain.Game)this, root);
    }
    
    public boolean hasRoot() {
        return (getRoot() != null);
    }
    
    public void removeRoot() {
        setRoot(null);
    }
    
    public void setRoot$unidirectional(it.algo.theharvestar.domain.Root root) {
        IspnTxManager.cachePut(getOid() + ":root", (root == null ? NULL_OBJECT : root.getOid()));
    }
    
}

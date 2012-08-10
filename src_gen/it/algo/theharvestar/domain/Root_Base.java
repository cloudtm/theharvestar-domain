package it.algo.theharvestar.domain;

import org.cloudtm.framework.ispn.IspnTxManager;
import org.cloudtm.framework.ispn.collections.bplustree.BPlusTree;

public abstract class Root_Base extends org.cloudtm.framework.ispn.AbstractDomainObject {
    public static dml.runtime.RoleMany<it.algo.theharvestar.domain.Root,it.algo.theharvestar.domain.Game> role$$games = new dml.runtime.RoleMany<it.algo.theharvestar.domain.Root,it.algo.theharvestar.domain.Game>() {
        public dml.runtime.RelationBaseSet<it.algo.theharvestar.domain.Game> getSet(it.algo.theharvestar.domain.Root o1) {
            return (org.cloudtm.framework.ispn.RelationSet<it.algo.theharvestar.domain.Root,it.algo.theharvestar.domain.Game>)o1.getGames();
        }
        public dml.runtime.Role<it.algo.theharvestar.domain.Game,it.algo.theharvestar.domain.Root> getInverseRole() {
            return it.algo.theharvestar.domain.Game.role$$root;
        }
        
    };
    public static dml.runtime.Relation<it.algo.theharvestar.domain.Root,it.algo.theharvestar.domain.Game> RootHasGames;
    
    public  Root_Base() {
        
    }
    
    public boolean getLoaded() {
        Object obj = IspnTxManager.cacheGet(getOid() + ":loaded");
        if (obj == null || obj instanceof NullClass) return false;
        return (Boolean)obj;
    }
    
    public void setLoaded(boolean loaded) {
        IspnTxManager.cachePut(getOid() + ":loaded", loaded);
    }
    
    public java.lang.Integer getNumGameIds() {
        Object obj = IspnTxManager.cacheGet(getOid() + ":numGameIds");
        if (obj == null || obj instanceof NullClass) return null;
        return (java.lang.Integer)obj;
    }
    
    public void setNumGameIds(java.lang.Integer numGameIds) {
        IspnTxManager.cachePut(getOid() + ":numGameIds", (numGameIds == null ? NULL_OBJECT : numGameIds));
    }
    
    public java.util.Set<it.algo.theharvestar.domain.Game> getGames() {
        BPlusTree<it.algo.theharvestar.domain.Game> internalSet;
        Object oid = IspnTxManager.cacheGet(getOid() + ":games");
        if (oid == null || oid instanceof NullClass) {
            internalSet = new BPlusTree<it.algo.theharvestar.domain.Game>();
            internalSet.initRoot();
            IspnTxManager.staticSave(internalSet);
            IspnTxManager.cachePut(getOid() + ":games", internalSet.getOid());
        } else {
            internalSet = (BPlusTree<it.algo.theharvestar.domain.Game>)fromOid((String)oid);
            // no need to test for null.  The entry must exist for sure.
        }
        return new org.cloudtm.framework.ispn.RelationSet(this, RootHasGames, internalSet);
    }
    
    public void addGames(it.algo.theharvestar.domain.Game games) {
        RootHasGames.add((it.algo.theharvestar.domain.Root)this, games);
    }
    
    public void removeGames(it.algo.theharvestar.domain.Game games) {
        RootHasGames.remove((it.algo.theharvestar.domain.Root)this, games);
    }
    
    public java.util.Set<it.algo.theharvestar.domain.Game> getGamesSet() {
        return getGames();
    }
    
    public int getGamesCount() {
        return getGames().size();
    }
    
    public boolean hasAnyGames() {
        return (getGames().size() != 0);
    }
    
    public boolean hasGames(it.algo.theharvestar.domain.Game games) {
        return getGames().contains(games);
    }
    
    public java.util.Iterator<it.algo.theharvestar.domain.Game> getGamesIterator() {
        return getGames().iterator();
    }
    
}

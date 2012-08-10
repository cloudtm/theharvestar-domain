package it.algo.theharvestar.domain;

import org.cloudtm.framework.TxSystem;

public class Root extends Root_Base {
    
    public  Root() {
        super();
    }

    public static Root createNewRoot() {
    Root r = new Root();
    if (TxSystem.getManager() != null) {
      TxSystem.getManager().setRootIdIfNeeded(r);
    }
    return r;
  }
    
}

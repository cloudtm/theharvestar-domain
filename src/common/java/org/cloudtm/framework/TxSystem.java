package org.cloudtm.framework;

import pt.ist.fenixframework.Config;
//import org.cloudtm.framework.ogm.HibOgmTxManager;

public abstract class TxSystem {
  private static final String TX_MGR_FF = "org.cloudtm.framework.fenix.FFTxManager";
  private static final String TX_MGR_HIB = "org.cloudtm.framework.ogm.HibOgmTxManager";
  private static final String TX_MGR_ISPN = "org.cloudtm.framework.ispn.IspnTxManager";
  private static final String[] TX_MGR = new String[]{TX_MGR_FF, TX_MGR_HIB, TX_MGR_ISPN};
  private static Config currentConfig = null;
  private static CloudtmConfig.Framework currentFramework = null;

  private static TxManager createTxManagerInstance(Config config, CloudtmConfig.Framework framework) {
    String txMgrClassname = null;
    switch (framework) {
      case FENIX:
        txMgrClassname = TX_MGR_FF;
        break;
      case OGM:
        txMgrClassname = TX_MGR_HIB;
        break;
      case ISPN:
        txMgrClassname = TX_MGR_ISPN;
        break;
      default:
        throw new IllegalArgumentException("Framework " + framework + " does not exists!");
    }

    System.out.println("Trying to find TxManager class: " + txMgrClassname);
    try {
      Class<TxManager> txMgr = (Class<TxManager>) Class.forName(txMgrClassname);
      System.out.println("Found TxManager class: " + txMgrClassname);
      TxManager txInstance = txMgr.newInstance();
      //TxManager txInstance = new HibOgmTxManager();
      txInstance.configure(config);
      System.out.println("Configured TxManager of type: " + txMgrClassname);
      return txInstance;
      
    } catch (ClassNotFoundException cnfe) {
      System.out.println("Could not find: " + txMgrClassname);
    } catch (InstantiationException ie) {
      System.out.println("ERROR: Could not instantiate: " + txMgrClassname);
    } catch (IllegalAccessException iae) {
      System.out.println("ERROR: Could not access: " + txMgrClassname);
    } catch (IllegalArgumentException iare) {
      System.out.println("ERROR: Illegal argument config: " + txMgrClassname);
    } catch (ExceptionInInitializerError eiie) {
      System.out.println("ERROR: ExceptionInInitializerError: " + txMgrClassname + " - ex: " + eiie.getCause());
      eiie.printStackTrace();
    }

    System.out.printf("ERROR: Couldn't find any suitable TxManager");
    System.exit(1);
    return null;
  }

  private static TxManager txManager = null;

  public static void initialize(Config config, CloudtmConfig.Framework framework) {
    if(currentConfig == null) {
      currentConfig = config;
      currentFramework = framework;
    }
    txManager = createTxManagerInstance(config, framework);
  }

  public static TxManager getManager() {
    if(txManager == null) {
      System.out.println("The tx manager is null!!!");
      initialize(currentConfig, currentFramework); 
    }
    return txManager;
  }
}

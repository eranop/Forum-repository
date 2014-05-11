package project_tests.acceptanceTest;

import Bridge.ProxyBridge;
import Bridge.RealBridge;



public abstract class Driver {
    public static ProxyBridge getBridge(){
        ProxyBridge bridge = new ProxyBridge();

	bridge.real = new RealBridge();
        return bridge;
    }
}

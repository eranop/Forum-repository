package project_tests.acceptanceTest;

import project_tests.Bridge.ProxyBridge;
import project_tests.Bridge.RealBridge;



public abstract class Driver {
	public static ProxyBridge getBridge(){
		ProxyBridge bridge = new ProxyBridge();

		bridge.real = new RealBridge();
		return bridge;
	}
}

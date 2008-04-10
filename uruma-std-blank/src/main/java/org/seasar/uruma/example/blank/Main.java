package org.seasar.uruma.example.blank;

import org.seasar.uruma.core.StandAloneUrumaStarter;

public class Main {
	public static void main(final String[] args) {
		StandAloneUrumaStarter uruma = StandAloneUrumaStarter.getInstance();
		uruma.openWindow("views/main.xml");
	}
}

package org.mevenk.webservices.logger;

import org.apache.logging.log4j.Level;

/*
 * OFF 0 | FATAL 100 | ERROR 200 | WARN 300 | INFO 400 | POLLING 470 | TRIGGER
 * 490 | DEBUG 500 | CONFIG 590 | TRACE 600 | ALL Integer.MAX_VALUE
 */

enum LogLevel {

	DEBUG {
		@Override
		Level getLevel() {
			return Level.DEBUG;
		}
	},
	ERROR {
		@Override
		Level getLevel() {
			return Level.ERROR;
		}
	},
	FATAL {
		@Override
		Level getLevel() {
			return Level.FATAL;
		}
	},
	INFO {
		@Override
		Level getLevel() {
			return Level.INFO;
		}
	},
	TRACE {
		@Override
		Level getLevel() {
			return Level.TRACE;
		}
	},
	WARN {
		@Override
		Level getLevel() {
			return Level.WARN;
		}
	},
	POLLING {
		@Override
		Level getLevel() {
			return Level.forName("POLLING", 470);
		}
	},
	TRIGGER {
		@Override
		Level getLevel() {
			return Level.forName("TRIGGER", 490);
		}
	},
	CONFIG {
		@Override
		Level getLevel() {
			return Level.forName("CONFIG", 590);
		}
	};

	abstract Level getLevel();

}
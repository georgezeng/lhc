package lhc.util;

import java.util.List;
import java.util.concurrent.Future;

public class CommonUtil {
	public static void sleep(List<Future<Exception>> futures, long time) throws Exception {
		while (true) {
			int count = 0;
			for (Future<Exception> f : futures) {
				if (f.isDone()) {
					if (f.get() != null) {
						throw f.get();
					}
					count++;
				}
			}
			if (count == futures.size()) {
				break;
			}
			if (time > 0) {
				Thread.sleep(time);
			}
		}
	}
}

package farto.cleva.guilherme.vo;

import java.security.SecureRandom;

public class AbstractVO {

	protected long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static long generateGUID() {
		long id = 0L;

		try {
			id = Math.abs(SecureRandom.getInstance("SHA1PRNG").nextLong());
		} catch (Exception e) {
		}

		return id;
	}

}

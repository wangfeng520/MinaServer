package com.xy.common.struct;

public abstract class IdBean extends Bean {
	private static final long serialVersionUID = 1L;
	 
	public abstract long getId();

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof IdBean)) return false;

		IdBean bean = (IdBean) obj;
		return this.getClass() == bean.getClass() && this.getId() == bean.getId();
	}

	public int hashCode() {
		return String.valueOf(getId()).hashCode();
	}
}

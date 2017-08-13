package lhc.enums;

public enum Color {
	Red(1), Yellow(2), Green(3);

	private int pos;

	private Color(int pos) {
		this.pos = pos;
	}

	public int getPos() {
		return this.pos;
	}
}

package lhc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape=Shape.OBJECT)
public enum SX {
	Shu("鼠"), Niu("牛"), Hu("虎"), Tu("兔"), Long("龙"), She("蛇"), Ma("马"), Yang("羊"), Hou("猴"), Ji("鸡"), Gou("狗"), Zhu("猪");
	
	private String text;

	private SX(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public String getName() {
		return name();
	}

	public static SX textOf(String text) {
		for (SX sx : values()) {
			if (sx.text.equals(text)) {
				return sx;
			}
		}
		return null;
	}

	public boolean isShu() {
		return Shu.equals(this);
	}

	public boolean isNiu() {
		return Niu.equals(this);
	}

	public boolean isHu() {
		return Hu.equals(this);
	}

	public boolean isTu() {
		return Tu.equals(this);
	}

	public boolean isLong() {
		return Long.equals(this);
	}

	public boolean isShe() {
		return She.equals(this);
	}

	public boolean isMa() {
		return Ma.equals(this);
	}

	public boolean isYang() {
		return Yang.equals(this);
	}

	public boolean isHou() {
		return Hou.equals(this);
	}

	public boolean isJi() {
		return Ji.equals(this);
	}

	public boolean isGou() {
		return Gou.equals(this);
	}

	public boolean isZhu() {
		return Zhu.equals(this);
	}
}

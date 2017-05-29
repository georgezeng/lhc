package lhc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum SX {
	Shu(1, "鼠"), Niu(2, "牛"), Hu(3, "虎"), Tu(4, "兔"), Long(5, "龙"), She(6, "蛇"), Ma(7, "马"), Yang(8, "羊"), Hou(9, "猴"), Ji(10, "鸡"), Gou(11, "狗"), Zhu(12, "猪");

	private String text;

	private int pos;
	
	private SX(int pos, String text) {
		this.text = text;
		this.pos = pos;
	}

	public int getPos() {
		return pos;
	}

	public String getText() {
		return this.text;
	}

	public String getName() {
		return name();
	}

	public static SX[] seq() {
		return new SX[] { Shu, Niu, Hu, Tu, Long, She, Ma, Yang, Hou, Ji, Gou, Zhu };
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

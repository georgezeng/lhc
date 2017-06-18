package lhc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum SX {
	Shu(1, "鼠", 2, true, true), Niu(2, "牛", 3, false, true), Hu(3, "虎", 4, true, true), Tu(4, "兔", 3, false, true), Long(
			5, "龙", 4, true,
			true), She(6, "蛇", 5, false, true), Ma(7, "马", 4, true, false), Yang(8, "羊", 5, false, false), Hou(9, "猴", 6,
					true, false), Ji(10, "鸡", 5, false, false), Gou(11, "狗", 6, true, false), Zhu(12, "猪", 7, false, false);

	private String text;

	private int pos;

	private int sector;

	private boolean single;

	private boolean small;

	private SX(int pos, String text, int sector, boolean single, boolean small) {
		this.text = text;
		this.pos = pos;
		this.sector = sector;
		this.single = single;
		this.small = small;
	}

	public boolean isSmall() {
		return small;
	}

	public boolean isSingle() {
		return single;
	}

	public int getSector() {
		return sector;
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

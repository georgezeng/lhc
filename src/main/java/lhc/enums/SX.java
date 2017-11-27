package lhc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum SX {
	Shu(1, "鼠", 2, true, true, 1), Niu(2, "牛", 3, false, true, 2), Hu(3, "虎", 4, true, true, 3), Tu(4, "兔", 3, false,
			true, 1), Long(5, "龙", 4, true, true, 2), She(6, "蛇", 5, false, true, 3), Ma(7, "马", 4, true, false, 1), Yang(8,
					"羊", 5, false, false, 2), Hou(9, "猴", 6, true, false,
							3), Ji(10, "鸡", 5, false, false, 1), Gou(11, "狗", 6, true, false, 2), Zhu(12, "猪", 7, false, false, 3);

	private String text;

	private int pos;

	private int sector;

	private boolean single;

	private boolean small;

	private int sx3qSector;

	private SX(int pos, String text, int sector, boolean single, boolean small, int sx3qSector) {
		this.text = text;
		this.pos = pos;
		this.sector = sector;
		this.single = single;
		this.small = small;
		this.sx3qSector = sx3qSector;
	}

	public int getSx3qSector() {
		return sx3qSector;
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

	public static String[] names() {
		return new String[] { Shu.name(), Niu.name(), Hu.name(), Tu.name(), Long.name(), She.name(), Ma.name(), Yang.name(),
				Hou.name(), Ji.name(), Gou.name(), Zhu.name() };
	}

	public static SX textOf(String text) {
		for (SX sx : values()) {
			if (sx.text.equals(text)) {
				return sx;
			}
		}
		return null;
	}
	
	public static SX posOf(int pos) {
		for (SX sx : values()) {
			if (sx.pos == pos) {
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

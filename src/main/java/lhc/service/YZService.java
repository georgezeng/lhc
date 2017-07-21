package lhc.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import lhc.domain.BsYz;
import lhc.domain.DsYz;
import lhc.domain.KaiJiang;
import lhc.domain.LhYz;
import lhc.domain.MwYz;
import lhc.domain.QqYz;
import lhc.domain.QwYz;
import lhc.domain.SqYz;
import lhc.domain.SwYz;
import lhc.domain.SxYz;
import lhc.domain.SxZfYz;
import lhc.domain.TmFdYz;
import lhc.domain.TmYz;
import lhc.enums.SX;
import lhc.repository.jpa.api.BsYzRepository;
import lhc.repository.jpa.api.DsYzRepository;
import lhc.repository.jpa.api.KaiJiangRepository;
import lhc.repository.jpa.api.LhYzRepository;
import lhc.repository.jpa.api.MwYzRepository;
import lhc.repository.jpa.api.QqYzRepository;
import lhc.repository.jpa.api.QwYzRepository;
import lhc.repository.jpa.api.SqYzRepository;
import lhc.repository.jpa.api.SwYzRepository;
import lhc.repository.jpa.api.SxYzRepository;
import lhc.repository.jpa.api.SxZfYzRepository;
import lhc.repository.jpa.api.TmFdYzRepository;
import lhc.repository.jpa.api.TmYzRepository;

@Service
public class YZService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private KaiJiangRepository kaiJiangRepository;

	@Autowired
	private SxYzRepository sxyzRepository;

	@Autowired
	private SxZfYzRepository sxzfyzRepository;

	@Autowired
	private QwYzRepository qwyzRepository;

	@Autowired
	private SwYzRepository swyzRepository;

	@Autowired
	private MwYzRepository mwyzRepository;

	@Autowired
	private LhYzRepository lhyzRepository;

	@Autowired
	private QqYzRepository qqyzRepository;

	@Autowired
	private BsYzRepository bsyzRepository;

	@Autowired
	private SqYzRepository sqyzRepository;

	@Autowired
	private DsYzRepository dsyzRepository;

	@Autowired
	private TmYzRepository tmyzRepository;

	@Autowired
	private TmFdYzRepository tmfdyzRepository;

	@Async
	public Future<Integer> calSX() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			SxYz lastYZ = null;
			do {
				result = kaiJiangRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						SxYz yz = sxyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new SxYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						Method method = SxYz.class.getDeclaredMethod("set" + data.getSpecialSx().name(), Integer.class);
						method.invoke(yz, 0);
						yz.setCurrentSx(data.getSpecialSx());
						if (lastYZ != null) {
							method = SxYz.class.getDeclaredMethod("get" + data.getSpecialSx().name());
							Integer lastValue = (Integer) method.invoke(lastYZ);
							yz.setLastYz(lastValue);
							for (SX sx : SX.seq()) {
								method = SX.class.getDeclaredMethod("is" + sx.name());
								Boolean isCurrentSX = (Boolean) method.invoke(data.getSpecialSx());
								if (!isCurrentSX) {
									method = SxYz.class.getDeclaredMethod("get" + sx.name());
									lastValue = (Integer) method.invoke(lastYZ);
									if (lastValue != null) {
										method = SxYz.class.getDeclaredMethod("set" + sx.name(), Integer.class);
										method.invoke(yz, lastValue + 1);
									}
								}
							}
						}

						Integer total = 0;
						for (SX sx : SX.seq()) {
							method = SxYz.class.getDeclaredMethod("get" + sx.name());
							Integer value = (Integer) method.invoke(yz);
							if (value != null) {
								total += value;
							}
						}
						yz.setTotal(total);

						if (lastYZ != null) {
							yz.setDelta(total - lastYZ.getTotal());
						} else {
							yz.setDelta(total);
						}

						sxyzRepository.save(yz);
						lastYZ = yz;
					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		calSXZF();

		return new AsyncResult<Integer>(1);
	}

	public void calSXZF() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<SxYz> result = null;
			SxYz lastYZ = null;
			SxZfYz lastZFYZ = null;
			do {
				result = sxyzRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (SxYz data : result.getContent()) {
						SxZfYz zfYz = sxzfyzRepository.findByDate(data.getDate());
						if (zfYz == null) {
							zfYz = new SxZfYz();
							zfYz.setYear(data.getYear());
							zfYz.setPhase(data.getPhase());
							zfYz.setDate(data.getDate());
						}

						Integer pos = null;
						if (lastYZ != null) {
							pos = new BigDecimal(data.getCurrentSx().getPos() - lastYZ.getCurrentSx().getPos()).abs().intValue();
							Method m = SxZfYz.class.getDeclaredMethod("setZf" + pos, Integer.class);
							m.invoke(zfYz, 0);
						}
						zfYz.setCurrentPos(pos);

						if (lastZFYZ != null && pos != null) {
							Method m = SxZfYz.class.getDeclaredMethod("getZf" + pos);
							Integer lastValue = (Integer) m.invoke(lastZFYZ);
							zfYz.setLastYz(lastValue);
							for (int i = 0; i < 12; i++) {
								if (i != pos) {
									m = SxZfYz.class.getDeclaredMethod("getZf" + i);
									lastValue = (Integer) m.invoke(lastZFYZ);
									if (lastValue != null) {
										m = SxZfYz.class.getDeclaredMethod("setZf" + i, Integer.class);
										m.invoke(zfYz, lastValue + 1);
									}
								}
							}
						}

						Integer total = 0;
						for (int i = 0; i < 12; i++) {
							Method m = SxZfYz.class.getDeclaredMethod("getZf" + i);
							Integer value = (Integer) m.invoke(zfYz);
							if (value != null) {
								total += value;
							}
						}
						zfYz.setTotal(total);

						if (lastZFYZ != null) {
							zfYz.setDelta(total - lastZFYZ.getTotal());
						} else {
							zfYz.setDelta(total);
						}

						sxzfyzRepository.save(zfYz);
						lastYZ = data;
						lastZFYZ = zfYz;
					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	@Async
	public Future<Integer> calHMQWYZ() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			QwYz lastYz = null;
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						QwYz yz = qwyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new QwYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						for (int i = 1; i < 7; i++) {
							gm = KaiJiang.class.getDeclaredMethod("getNum" + i);
							Integer num = (Integer) gm.invoke(data);
							String numStr = num.toString();
							if (numStr.length() > 1) {
								numStr = numStr.substring(numStr.length() - 1);
								num = Integer.valueOf(numStr);
							}
							gm = QwYz.class.getDeclaredMethod("getW" + num);
							Integer value = (Integer) gm.invoke(yz);
							if (value == null) {
								value = 0;
							}
							sm = QwYz.class.getDeclaredMethod("setW" + num, Integer.class);
							sm.invoke(yz, 0);
						}
						Integer num = data.getSpecialNum();
						String numStr = num.toString();
						if (numStr.length() > 1) {
							numStr = numStr.substring(numStr.length() - 1);
							num = Integer.valueOf(numStr);
						}
						sm = QwYz.class.getDeclaredMethod("setW" + num, Integer.class);
						sm.invoke(yz, 0);

						if (lastYz != null) {
							for (int j = 0; j < 10; j++) {
								gm = QwYz.class.getDeclaredMethod("getW" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = QwYz.class.getDeclaredMethod("setW" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
						}

						int total = 0;
						int currentYz = 0;
						for (int j = 0; j < 10; j++) {
							gm = QwYz.class.getDeclaredMethod("getW" + j);
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								if (currentYz < value) {
									currentYz = value;
								}
								total += value;
							}
						}
						yz.setCurrentYz(currentYz);
						yz.setTotal(total);

						qwyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return new AsyncResult<Integer>(1);
	}

	@Async
	public Future<Integer> calSWYZ() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			SwYz lastYz = null;
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						SwYz yz = swyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new SwYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						Integer num = data.getSpecialNum();
						String numStr = num.toString();
						if (numStr.length() > 1) {
							numStr = numStr.substring(0, numStr.length() - 1);
							num = Integer.valueOf(numStr);
						} else {
							num = 0;
						}
						sm = SwYz.class.getDeclaredMethod("setW" + num, Integer.class);
						sm.invoke(yz, 0);

						if (lastYz != null) {
							for (int j = 0; j < 5; j++) {
								gm = SwYz.class.getDeclaredMethod("getW" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = SwYz.class.getDeclaredMethod("setW" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
							gm = SwYz.class.getDeclaredMethod("getW" + num);
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						int total = 0;
						for (int j = 0; j < 5; j++) {
							gm = SwYz.class.getDeclaredMethod("getW" + j);
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								total += value;
							}
						}
						yz.setTotal(total);

						if (lastYz != null) {
							yz.setDelta(total - lastYz.getTotal());
						}

						swyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return new AsyncResult<Integer>(1);
	}

	@Async
	public Future<Integer> calMWYZ() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			MwYz lastYz = null;
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						MwYz yz = mwyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new MwYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						Integer num = data.getSpecialNum();
						String numStr = num.toString();
						if (numStr.length() > 1) {
							numStr = numStr.substring(numStr.length() - 1);
							num = Integer.valueOf(numStr);
						}
						sm = MwYz.class.getDeclaredMethod("setW" + num, Integer.class);
						sm.invoke(yz, 0);

						if (lastYz != null) {
							for (int j = 0; j < 10; j++) {
								gm = MwYz.class.getDeclaredMethod("getW" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = MwYz.class.getDeclaredMethod("setW" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
							gm = MwYz.class.getDeclaredMethod("getW" + num);
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						int total = 0;
						for (int j = 0; j < 10; j++) {
							gm = MwYz.class.getDeclaredMethod("getW" + j);
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								total += value;
							}
						}
						yz.setTotal(total);

						if (lastYz != null) {
							yz.setDelta(total - lastYz.getTotal());
						}

						mwyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new AsyncResult<Integer>(1);
	}

	@Async
	public Future<Integer> calLHYZ() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			LhYz lastYz = null;
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						LhYz yz = lhyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new LhYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						Integer num = data.getSpecialNum();
						String numStr = num.toString();
						if (numStr.length() > 1) {
							String num1 = numStr.substring(0, 1);
							String num2 = numStr.substring(1);
							num = Integer.valueOf(num1) + Integer.valueOf(num2);
							numStr = num.toString();
							if (numStr.length() > 1) {
								numStr = numStr.substring(1);
								num = Integer.valueOf(numStr);
							}
						}
						sm = LhYz.class.getDeclaredMethod("setW" + num, Integer.class);
						sm.invoke(yz, 0);

						if (lastYz != null) {
							for (int j = 0; j < 10; j++) {
								gm = LhYz.class.getDeclaredMethod("getW" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = LhYz.class.getDeclaredMethod("setW" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
							gm = LhYz.class.getDeclaredMethod("getW" + num);
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						int total = 0;
						for (int j = 0; j < 10; j++) {
							gm = LhYz.class.getDeclaredMethod("getW" + j);
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								total += value;
							}
						}
						yz.setTotal(total);

						if (lastYz != null) {
							yz.setDelta(total - lastYz.getTotal());
						}

						lhyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return new AsyncResult<Integer>(1);
	}

	@Async
	public Future<Integer> calQQYZ() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			QqYz lastYz = null;
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						QqYz yz = qqyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new QqYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						Integer num = data.getSpecialNum();
						if (num < 8) {
							num = 1;
						} else if (num > 7 && num < 15) {
							num = 2;
						} else if (num > 14 && num < 22) {
							num = 3;
						} else if (num > 21 && num < 29) {
							num = 4;
						} else if (num > 28 && num < 36) {
							num = 5;
						} else if (num > 35 && num < 43) {
							num = 6;
						} else {
							num = 7;
						}
						sm = QqYz.class.getDeclaredMethod("setW" + num, Integer.class);
						sm.invoke(yz, 0);

						if (lastYz != null) {
							for (int j = 1; j < 8; j++) {
								gm = QqYz.class.getDeclaredMethod("getW" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = QqYz.class.getDeclaredMethod("setW" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
							gm = QqYz.class.getDeclaredMethod("getW" + num);
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						int total = 0;
						for (int j = 1; j < 8; j++) {
							gm = QqYz.class.getDeclaredMethod("getW" + j);
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								total += value;
							}
						}
						yz.setTotal(total);

						if (lastYz != null) {
							yz.setDelta(total - lastYz.getTotal());
						}

						qqyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new AsyncResult<Integer>(1);
	}

	private static final List<Integer> RED_NUMS = new ArrayList<Integer>();
	private static final List<Integer> GREEN_NUMS = new ArrayList<Integer>();
	private static final List<Integer> BLUE_NUMS = new ArrayList<Integer>();
	static {
		RED_NUMS.add(1);
		RED_NUMS.add(2);
		RED_NUMS.add(7);
		RED_NUMS.add(8);
		RED_NUMS.add(12);
		RED_NUMS.add(13);
		RED_NUMS.add(18);
		RED_NUMS.add(19);
		RED_NUMS.add(23);
		RED_NUMS.add(24);
		RED_NUMS.add(29);
		RED_NUMS.add(30);
		RED_NUMS.add(34);
		RED_NUMS.add(35);
		RED_NUMS.add(40);
		RED_NUMS.add(45);
		RED_NUMS.add(46);

		GREEN_NUMS.add(5);
		GREEN_NUMS.add(6);
		GREEN_NUMS.add(11);
		GREEN_NUMS.add(16);
		GREEN_NUMS.add(17);
		GREEN_NUMS.add(21);
		GREEN_NUMS.add(22);
		GREEN_NUMS.add(27);
		GREEN_NUMS.add(28);
		GREEN_NUMS.add(32);
		GREEN_NUMS.add(33);
		GREEN_NUMS.add(38);
		GREEN_NUMS.add(39);
		GREEN_NUMS.add(43);
		GREEN_NUMS.add(44);
		GREEN_NUMS.add(49);

		BLUE_NUMS.add(3);
		BLUE_NUMS.add(4);
		BLUE_NUMS.add(9);
		BLUE_NUMS.add(10);
		BLUE_NUMS.add(14);
		BLUE_NUMS.add(15);
		BLUE_NUMS.add(20);
		BLUE_NUMS.add(25);
		BLUE_NUMS.add(26);
		BLUE_NUMS.add(31);
		BLUE_NUMS.add(36);
		BLUE_NUMS.add(37);
		BLUE_NUMS.add(41);
		BLUE_NUMS.add(42);
		BLUE_NUMS.add(47);
		BLUE_NUMS.add(48);

	}

	@Async
	public Future<Integer> calBSYZ() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			BsYz lastYz = null;
			String[] colors = { "Red", "Green", "Blue" };
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						BsYz yz = bsyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new BsYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						Integer num = data.getSpecialNum();
						String zeroColor = null;
						if (RED_NUMS.contains(num)) {
							yz.setRed(0);
							zeroColor = "Red";
						} else if (GREEN_NUMS.contains(num)) {
							yz.setGreen(0);
							zeroColor = "Green";
						} else {
							yz.setBlue(0);
							zeroColor = "Blue";
						}

						if (lastYz != null) {
							for (int j = 0; j < colors.length; j++) {
								String color = colors[j];
								gm = BsYz.class.getDeclaredMethod("get" + color);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = BsYz.class.getDeclaredMethod("set" + color, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
							gm = BsYz.class.getDeclaredMethod("get" + zeroColor);
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						int total = 0;
						for (int j = 0; j < colors.length; j++) {
							String color = colors[j];
							gm = BsYz.class.getDeclaredMethod("get" + color);
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								total += value;
							}
						}
						yz.setTotal(total);

						if (lastYz != null) {
							yz.setDelta(total - lastYz.getTotal());
						}

						bsyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new AsyncResult<Integer>(1);
	}

	@Async
	public Future<Integer> calSQYZ() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			SqYz lastYz = null;
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						SqYz yz = sqyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new SqYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						SX sx = data.getSpecialSx();
						sm = SqYz.class.getDeclaredMethod("setW" + sx.getSector(), Integer.class);
						sm.invoke(yz, 0);

						if (lastYz != null) {
							for (int j = 2; j < 8; j++) {
								gm = SqYz.class.getDeclaredMethod("getW" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = SqYz.class.getDeclaredMethod("setW" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
							gm = SqYz.class.getDeclaredMethod("getW" + sx.getSector());
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						int total = 0;
						for (int j = 2; j < 8; j++) {
							gm = SqYz.class.getDeclaredMethod("getW" + j);
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								total += value;
							}
						}
						yz.setTotal(total);

						if (lastYz != null) {
							yz.setDelta(total - lastYz.getTotal());
						}

						sqyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new AsyncResult<Integer>(1);
	}

	@Async
	public Future<Integer> calDSYZ() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			DsYz lastYz = null;
			String[] arr = { "SxSmall", "SxLarge", "SxSingle", "SxEven", "HmSmall", "HmLarge", "HmSingle", "HmEven" };
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						DsYz yz = dsyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new DsYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						SX sx = data.getSpecialSx();
						boolean isSmall = sx.isSmall();
						boolean isSingle = sx.isSingle();
						if (isSmall) {
							yz.setSxSmall(0);
							if (lastYz != null) {
								yz.setLastSxDxYz(lastYz.getSxSmall());
							}
						} else {
							yz.setSxLarge(0);
							if (lastYz != null) {
								yz.setLastSxDxYz(lastYz.getSxLarge());
							}
						}
						if (isSingle) {
							yz.setSxSingle(0);
							if (lastYz != null) {
								yz.setLastSxDsYz(lastYz.getSxSingle());
							}
						} else {
							yz.setSxEven(0);
							if (lastYz != null) {
								yz.setLastSxDsYz(lastYz.getSxEven());
							}
						}
						Integer num = data.getSpecialNum();
						isSmall = num < 26;
						isSingle = num % 2 != 0;
						if (isSmall) {
							yz.setHmSmall(0);
							if (lastYz != null) {
								yz.setLastHmDxYz(lastYz.getHmSmall());
							}
						} else {
							yz.setHmLarge(0);
							if (lastYz != null) {
								yz.setLastHmDxYz(lastYz.getHmLarge());
							}
						}
						if (isSingle) {
							yz.setHmSingle(0);
							if (lastYz != null) {
								yz.setLastHmDsYz(lastYz.getHmSingle());
							}
						} else {
							yz.setHmEven(0);
							if (lastYz != null) {
								yz.setLastHmDsYz(lastYz.getHmEven());
							}
						}

						if (lastYz != null) {
							for (int j = 0; j < arr.length; j++) {
								String suffix = arr[j];
								gm = DsYz.class.getDeclaredMethod("get" + suffix);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = DsYz.class.getDeclaredMethod("set" + suffix, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
						}

						dsyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new AsyncResult<Integer>(1);
	}

	@Async
	public Future<Integer> calTMYZ() {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			TmYz lastYz = null;
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						TmYz yz = tmyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new TmYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						Integer num = data.getSpecialNum();
						sm = TmYz.class.getDeclaredMethod("setHm" + num, Integer.class);
						sm.invoke(yz, 0);

						if (lastYz != null) {
							for (int j = 1; j < 50; j++) {
								gm = TmYz.class.getDeclaredMethod("getHm" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = TmYz.class.getDeclaredMethod("setHm" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
							gm = TmYz.class.getDeclaredMethod("getHm" + num);
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						int total = 0;
						int max = 0;
						for (int j = 1; j < 50; j++) {
							gm = TmYz.class.getDeclaredMethod("getHm" + j);
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								if (value > max) {
									max = value;
								}
								total += value;
							}
						}
						yz.setMaxYz(max);
						yz.setTotal(total);

						if (lastYz != null) {
							yz.setDelta(total - lastYz.getTotal());
						}

						tmyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

			calTMFDYZ();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return new AsyncResult<Integer>(1);
	}

	private void calTMFDYZ() throws Exception {
		Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
		Page<TmYz> result = null;
		TmFdYz lastYz = null;
		do {
			result = tmyzRepository.findAll(request);
			Method gm = null;
			Method sm = null;
			if (result != null && result.hasContent()) {
				for (TmYz data : result.getContent()) {
					TmFdYz yz = tmfdyzRepository.findByDate(data.getDate());
					if (yz == null) {
						yz = new TmFdYz();
						yz.setYear(data.getYear());
						yz.setPhase(data.getPhase());
						yz.setDate(data.getDate());
					}

					List<TmYzInfo> infos = new ArrayList<TmYzInfo>();
					for (int i = 1; i < 50; i++) {
						gm = TmYz.class.getDeclaredMethod("getHm" + i);
						Integer value = (Integer) gm.invoke(data);
						infos.add(new TmYzInfo(i, value));
					}
					Collections.sort(infos, new Comparator<TmYzInfo>() {

						@Override
						public int compare(TmYzInfo o1, TmYzInfo o2) {
							if (o1.yz != null && o2.yz != null) {
								if (data.getLastYz() != null) {
									if (o1.yz == 0) {
										o1.yz = data.getLastYz();
									}
									if (o2.yz == 0) {
										o2.yz = data.getLastYz();
									}
								}
								return o1.yz < o2.yz ? -1 : (o1.yz == o2.yz ? 0 : 1);
							} else if (o1.yz == null && o2.yz != null) {
								return -1;
							} else if (o1.yz != null && o2.yz == null) {
								return 1;
							} else {
								return 0;
							}
						}
					});

					int num = 1;
					for (TmYzInfo info : infos) {
						if (info.tm) {
							break;
						}
						num++;
					}
					if (num < 49) {
						num = num / 7 + 1;
					} else {
						num = 7;
					}
					sm = TmFdYz.class.getDeclaredMethod("setFd" + num, Integer.class);
					sm.invoke(yz, 0);

					if (lastYz != null) {
						for (int j = 1; j < 8; j++) {
							gm = TmFdYz.class.getDeclaredMethod("getFd" + j);
							Integer lastValue = (Integer) gm.invoke(lastYz);
							if (lastValue != null) {
								Integer value = (Integer) gm.invoke(yz);
								if (value == null || value > 0) {
									sm = TmFdYz.class.getDeclaredMethod("setFd" + j, Integer.class);
									sm.invoke(yz, lastValue + 1);
								}
							}
						}
						gm = TmFdYz.class.getDeclaredMethod("getFd" + num);
						yz.setLastYz((Integer) gm.invoke(lastYz));
					}

					int total = 0;
					int max = 0;
					for (int j = 1; j < 8; j++) {
						gm = TmFdYz.class.getDeclaredMethod("getFd" + j);
						Integer value = (Integer) gm.invoke(yz);
						if (value != null) {
							if (value > max) {
								max = value;
							}
							total += value;
						}
					}
					yz.setMaxYz(max);
					yz.setTotal(total);

					if (lastYz != null) {
						yz.setDelta(total - lastYz.getTotal());
					}

					tmfdyzRepository.save(yz);
					lastYz = yz;

				}
			}
			request = result.nextPageable();
		} while (result != null && result.hasNext());
	}
}

class TmYzInfo {
	Integer num;
	Integer yz;
	boolean tm;

	public TmYzInfo(Integer num, Integer yz) {
		this.num = num;
		this.yz = yz;
		tm = yz != null && yz == 0;
	}

}
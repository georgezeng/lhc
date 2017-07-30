package lhc.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lhc.constants.BsNums;
import lhc.constants.ZsNums;
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
import lhc.domain.SxZfYz2;
import lhc.domain.TmFdYz;
import lhc.domain.TmYz;
import lhc.domain.ZsYz;
import lhc.dto.TmYzInfo;
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
import lhc.repository.jpa.api.SxZfYz2Repository;
import lhc.repository.jpa.api.SxZfYzRepository;
import lhc.repository.jpa.api.TmFdYzRepository;
import lhc.repository.jpa.api.TmYzRepository;
import lhc.repository.jpa.api.ZsYzRepository;

@Service
@Transactional
public class YZService {

	@Autowired
	private KaiJiangRepository kaiJiangRepository;

	@Autowired
	private SxYzRepository sxyzRepository;

	@Autowired
	private SxZfYzRepository sxzfyzRepository;

	@Autowired
	private SxZfYz2Repository sxzfyz2Repository;

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
	private ZsYzRepository zsyzRepository;

	@Autowired
	private SqYzRepository sqyzRepository;

	@Autowired
	private DsYzRepository dsyzRepository;

	@Autowired
	private TmYzRepository tmyzRepository;

	@Autowired
	private TmFdYzRepository tmfdyzRepository;

	@Async
	public Future<Exception> calSX() {
		Exception t = null;
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
							Integer maxValue = 0;
							for (SX sx : SX.seq()) {
								method = SX.class.getDeclaredMethod("is" + sx.name());
								Boolean isCurrentSX = (Boolean) method.invoke(data.getSpecialSx());
								if (!isCurrentSX) {
									method = SxYz.class.getDeclaredMethod("get" + sx.name());
									lastValue = (Integer) method.invoke(lastYZ);
									if (lastValue != null) {
										lastValue++;
										method = SxYz.class.getDeclaredMethod("set" + sx.name(), Integer.class);
										method.invoke(yz, lastValue);
										if (maxValue < lastValue) {
											maxValue = lastValue;
										}
									}
								}
							}
							if (maxValue > 0) {
								yz.setMax(maxValue);
							}

							if (yz.getLastYz() != null) {
								for (int k = 0; k < 7; k++) {
									Method sm = yz.getClass().getSuperclass().getDeclaredMethod("setMin" + k, Integer.class);
									if (yz.getLastYz() == k) {
										sm.invoke(yz, 0);
									} else {
										Method gm = yz.getClass().getSuperclass().getDeclaredMethod("getMin" + k);
										Integer minValue = (Integer) gm.invoke(lastYZ);
										if (minValue != null) {
											sm.invoke(yz, minValue + 1);
										}
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

			calSXZF();
			calSXZF2();

//			parallelYzService.calAvg(sxyzRepository);
		} catch (Exception e) {
			t = e;
		}

		return new AsyncResult<Exception>(t);
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

	public void calSXZF2() {
		int sxLength = SX.values().length;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<SxYz> result = null;
			SxYz lastYZ = null;
			SxZfYz2 lastZFYZ = null;
			do {
				result = sxyzRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (SxYz data : result.getContent()) {
						SxZfYz2 zfYz = sxzfyz2Repository.findByDate(data.getDate());
						if (zfYz == null) {
							zfYz = new SxZfYz2();
							zfYz.setYear(data.getYear());
							zfYz.setPhase(data.getPhase());
							zfYz.setDate(data.getDate());
						}

						Integer pos = null;
						if (lastYZ != null) {
							pos = sxLength - lastYZ.getCurrentSx().getPos() + data.getCurrentSx().getPos() - 1;
							if (pos > 11) {
								pos -= 12;
							}
							Method m = SxZfYz2.class.getDeclaredMethod("setZf" + pos, Integer.class);
							m.invoke(zfYz, 0);
						}
						zfYz.setCurrentPos(pos);

						if (lastZFYZ != null) {
							if (pos != null) {
								Method m = SxZfYz2.class.getDeclaredMethod("getZf" + pos);
								Integer lastValue = (Integer) m.invoke(lastZFYZ);
								zfYz.setLastYz(lastValue);
								Integer maxValue = 0;
								for (int i = 0; i < 12; i++) {
									if (i != pos) {
										m = SxZfYz2.class.getDeclaredMethod("getZf" + i);
										lastValue = (Integer) m.invoke(lastZFYZ);
										if (lastValue != null) {
											lastValue++;
											m = SxZfYz2.class.getDeclaredMethod("setZf" + i, Integer.class);
											m.invoke(zfYz, lastValue);
										}
									}
									if (lastValue != null && maxValue < lastValue) {
										maxValue = lastValue;
									}
								}
								if (maxValue > 0) {
									zfYz.setMax(maxValue);
								}
							}
						}

						if (zfYz.getLastYz() != null) {
							for (int k = 0; k < 7; k++) {
								Method sm = zfYz.getClass().getSuperclass().getDeclaredMethod("setMin" + k, Integer.class);
								if (zfYz.getLastYz() == k) {
									sm.invoke(zfYz, 0);
								} else {
									Method gm = zfYz.getClass().getSuperclass().getDeclaredMethod("getMin" + k);
									Integer minValue = (Integer) gm.invoke(lastZFYZ);
									if (minValue != null) {
										sm.invoke(zfYz, minValue + 1);
									}
								}
							}
						}

						Integer total = 0;
						for (int i = 0; i < 12; i++) {
							Method m = SxZfYz2.class.getDeclaredMethod("getZf" + i);
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

						sxzfyz2Repository.save(zfYz);
						lastYZ = data;
						lastZFYZ = zfYz;
					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

//			parallelYzService.calAvg(sxzfyz2Repository);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	@Async
	public Future<Exception> calHMQWYZ() {
		Exception t = null;
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
			t = e;
		}

		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calSWYZ() {
		Exception t = null;
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
			t = e;
		}

		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calMWYZ() {
		Exception t = null;
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
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calLHYZ() {
		Exception t = null;
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
			t = e;
		}

		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calQQYZ() {
		Exception t = null;
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
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calBSYZ() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			BsYz lastYz = null;
			String[] colors = { "RedOdd", "RedEven", "BlueOdd", "BlueEven", "GreenOdd", "GreenEven" };
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
						if (BsNums.RED.contains(num)) {
							if (num % 2 == 0) {
								yz.setRedEven(0);
								zeroColor = "RedEven";
							} else {
								yz.setRedOdd(0);
								zeroColor = "RedOdd";
							}
						} else if (BsNums.GREEN.contains(num)) {
							if (num % 2 == 0) {
								yz.setGreenEven(0);
								zeroColor = "GreenEven";
							} else {
								yz.setGreenOdd(0);
								zeroColor = "GreenOdd";
							}
						} else {
							if (num % 2 == 0) {
								yz.setBlueEven(0);
								zeroColor = "BlueEven";
							} else {
								yz.setBlueOdd(0);
								zeroColor = "BlueOdd";
							}
						}

						if (lastYz != null) {
							Integer maxValue = 0;
							for (int j = 0; j < colors.length; j++) {
								String color = colors[j];
								gm = BsYz.class.getDeclaredMethod("get" + color);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									lastValue++;
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = BsYz.class.getDeclaredMethod("set" + color, Integer.class);
										sm.invoke(yz, lastValue);
										if (maxValue < lastValue) {
											maxValue = lastValue;
										}
									}
								}
							}
							if (maxValue > 0) {
								yz.setMax(maxValue);
							}
							gm = BsYz.class.getDeclaredMethod("get" + zeroColor);
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						if (yz.getLastYz() != null) {
							for (int k = 0; k < 7; k++) {
								sm = yz.getClass().getSuperclass().getDeclaredMethod("setMin" + k, Integer.class);
								if (yz.getLastYz() == k) {
									sm.invoke(yz, 0);
								} else {
									gm = yz.getClass().getSuperclass().getDeclaredMethod("getMin" + k);
									Integer minValue = (Integer) gm.invoke(lastYz);
									if (minValue != null) {
										sm.invoke(yz, minValue + 1);
									}
								}
							}
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

//			parallelYzService.calAvg(bsyzRepository);
		} catch (Exception e) {
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calSQYZ() {
		Exception t = null;
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
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calDSYZ() {
		Exception t = null;
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
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calTMYZ() {
		Exception t = null;
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
			t = e;
		}

		return new AsyncResult<Exception>(t);
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

					List<TmYzInfo> infos = getTMFDList(data);

					int num = 1;
					for (TmYzInfo info : infos) {
						if (info.isTm()) {
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

	public List<TmYzInfo> getTMFDList(TmYz data) {
		try {
			List<TmYzInfo> infos = new ArrayList<TmYzInfo>();
			for (int i = 1; i < 50; i++) {
				Method gm = TmYz.class.getDeclaredMethod("getHm" + i);
				Integer value = (Integer) gm.invoke(data);
				infos.add(new TmYzInfo(i, value));
			}
			Collections.sort(infos, new Comparator<TmYzInfo>() {

				@Override
				public int compare(TmYzInfo o1, TmYzInfo o2) {
					if (o1.getYz() != null && o2.getYz() != null) {
						if (data.getLastYz() != null) {
							if (o1.getYz() == 0) {
								o1.setYz(data.getLastYz());
							}
							if (o2.getYz() == 0) {
								o2.setYz(data.getLastYz());
							}
						}
						return o1.getYz() < o2.getYz() ? -1 : (o1.getYz() == o2.getYz() ? 0 : 1);
					} else if (o1.getYz() == null && o2.getYz() != null) {
						return -1;
					} else if (o1.getYz() != null && o2.getYz() == null) {
						return 1;
					} else {
						return 0;
					}
				}
			});
			return infos;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Async
	public Future<Exception> calZSYZ() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			ZsYz lastYz = null;
			Class<ZsYz> clazz = ZsYz.class;
			String[] fds = { "Fd1", "Fd2", "Fd3", "Fd4", "Fd5", "Fd6", "Fd7", "Fd8" };
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						ZsYz yz = zsyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new ZsYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						Integer num = data.getSpecialNum();
						String zeroFd = null;
						for (String fd : fds) {
							Field f = ZsNums.class.getDeclaredField(fd.toUpperCase());
							List<Integer> nums = (List<Integer>) f.get(null);
							if (nums.contains(num)) {
								sm = clazz.getDeclaredMethod("set" + fd, Integer.class);
								sm.invoke(yz, 0);
								zeroFd = fd;
								break;
							}
						}

						if (lastYz != null) {
							Integer maxValue = 0;
							for (String fd : fds) {
								gm = clazz.getDeclaredMethod("get" + fd);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									lastValue++;
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = clazz.getDeclaredMethod("set" + fd, Integer.class);
										sm.invoke(yz, lastValue);
										if (maxValue < lastValue) {
											maxValue = lastValue;
										}
									}
								}
							}
							if (maxValue > 0) {
								yz.setMax(maxValue);
							}
							gm = clazz.getDeclaredMethod("get" + zeroFd);
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						if (yz.getLastYz() != null) {
							for (int k = 0; k < 7; k++) {
								sm = clazz.getSuperclass().getDeclaredMethod("setMin" + k, Integer.class);
								if (yz.getLastYz() == k) {
									sm.invoke(yz, 0);
								} else {
									gm = clazz.getSuperclass().getDeclaredMethod("getMin" + k);
									Integer minValue = (Integer) gm.invoke(lastYz);
									if (minValue != null) {
										sm.invoke(yz, minValue + 1);
									}
								}
							}
						}

						int total = 0;
						for (String fd : fds) {
							gm = clazz.getDeclaredMethod("get" + fd);
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								total += value;
							}
						}
						yz.setTotal(total);

						if (lastYz != null) {
							yz.setDelta(total - lastYz.getTotal());
						}

						zsyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

//			parallelYzService.calAvg(zsyzRepository);
		} catch (Exception e) {
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}
}

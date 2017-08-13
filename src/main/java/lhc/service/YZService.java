package lhc.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.transaction.annotation.Transactional;

import lhc.constants.BsNums;
import lhc.constants.DsNums;
import lhc.constants.LhNums;
import lhc.constants.MwNums;
import lhc.constants.QqNums;
import lhc.constants.SwNums;
import lhc.constants.WxNums;
import lhc.constants.ZsNums;
import lhc.domain.Avg;
import lhc.domain.BaseYz;
import lhc.domain.BsYz;
import lhc.domain.BsZfYz;
import lhc.domain.DsYz;
import lhc.domain.DsZfYz;
import lhc.domain.KaiJiang;
import lhc.domain.LhYz;
import lhc.domain.LhZfYz;
import lhc.domain.MwYz;
import lhc.domain.MwZfYz;
import lhc.domain.PtYz;
import lhc.domain.QqYz;
import lhc.domain.QqZfYz;
import lhc.domain.QwYz;
import lhc.domain.SqYz;
import lhc.domain.SwYz;
import lhc.domain.SwZfYz;
import lhc.domain.SxCsYz;
import lhc.domain.SxDsYz;
import lhc.domain.SxLrYz;
import lhc.domain.SxYz;
import lhc.domain.SxZfYz;
import lhc.domain.SxZfYz2;
import lhc.domain.TmFdYz;
import lhc.domain.TmYz;
import lhc.domain.WxYz;
import lhc.domain.WxZfYz;
import lhc.domain.ZsYz;
import lhc.domain.ZsZfYz;
import lhc.dto.TmYzInfo;
import lhc.enums.Color;
import lhc.enums.SX;
import lhc.repository.jpa.BaseYzRepository;
import lhc.repository.jpa.api.BsYzRepository;
import lhc.repository.jpa.api.BsZfYzRepository;
import lhc.repository.jpa.api.DsYzRepository;
import lhc.repository.jpa.api.DsZfYzRepository;
import lhc.repository.jpa.api.KaiJiangRepository;
import lhc.repository.jpa.api.LhYzRepository;
import lhc.repository.jpa.api.LhZfYzRepository;
import lhc.repository.jpa.api.MwYzRepository;
import lhc.repository.jpa.api.MwZfYzRepository;
import lhc.repository.jpa.api.PtYzRepository;
import lhc.repository.jpa.api.QqYzRepository;
import lhc.repository.jpa.api.QqZfYzRepository;
import lhc.repository.jpa.api.QwYzRepository;
import lhc.repository.jpa.api.SqYzRepository;
import lhc.repository.jpa.api.SwYzRepository;
import lhc.repository.jpa.api.SwZfYzRepository;
import lhc.repository.jpa.api.SxCsYzRepository;
import lhc.repository.jpa.api.SxDsYzRepository;
import lhc.repository.jpa.api.SxLrYzRepository;
import lhc.repository.jpa.api.SxYzRepository;
import lhc.repository.jpa.api.SxZfYz2Repository;
import lhc.repository.jpa.api.SxZfYzRepository;
import lhc.repository.jpa.api.TmFdYzRepository;
import lhc.repository.jpa.api.TmYzRepository;
import lhc.repository.jpa.api.WxYzRepository;
import lhc.repository.jpa.api.WxZfYzRepository;
import lhc.repository.jpa.api.ZsYzRepository;
import lhc.repository.jpa.api.ZsZfYzRepository;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class YZService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private KaiJiangRepository kaiJiangRepository;

	@Autowired
	private SxYzRepository sxyzRepository;

	@Autowired
	private SxLrYzRepository sxlryzRepository;

	@Autowired
	private SxZfYzRepository sxzfyzRepository;

	@Autowired
	private SxZfYz2Repository sxzfyz2Repository;

	@Autowired
	private QwYzRepository qwyzRepository;

	@Autowired
	private SwYzRepository swyzRepository;

	@Autowired
	private SwZfYzRepository swzfyzRepository;

	@Autowired
	private MwZfYzRepository mwzfyzRepository;

	@Autowired
	private DsZfYzRepository dszfyzRepository;

	@Autowired
	private MwYzRepository mwyzRepository;

	@Autowired
	private LhYzRepository lhyzRepository;

	@Autowired
	private QqYzRepository qqyzRepository;

	@Autowired
	private QqZfYzRepository qqzfyzRepository;

	@Autowired
	private BsYzRepository bsyzRepository;

	@Autowired
	private ZsYzRepository zsyzRepository;

	@Autowired
	private DsYzRepository dsyzRepository;

	@Autowired
	private LhZfYzRepository lhzfyzRepository;

	@Autowired
	private SqYzRepository sqyzRepository;

	@Autowired
	private SxDsYzRepository sxdsyzRepository;

	@Autowired
	private TmYzRepository tmyzRepository;

	@Autowired
	private PtYzRepository ptyzRepository;

	@Autowired
	private TmFdYzRepository tmfdyzRepository;

	@Autowired
	private ZsZfYzRepository zszfyzRepository;

	@Autowired
	private BsZfYzRepository bszfyzRepository;

	@Autowired
	private WxYzRepository wxyzRepository;

	@Autowired
	private WxZfYzRepository wxzfyzRepository;

	@Autowired
	private SxCsYzRepository sxcsyzRepository;

	@Async
	public Future<Exception> calSX() {
		return calYZ(SxYz.class, sxyzRepository, kaiJiangRepository, new YzHandler<SxYz, KaiJiang>() {

			@Override
			public List<Integer> process(SxYz yz, SxYz lastYZ, KaiJiang data, KaiJiang lastData) throws Exception {
				Method method = SxYz.class.getDeclaredMethod("set" + data.getSpecialSx().name(), Integer.class);
				method.invoke(yz, 0);
				yz.setCurrentSx(data.getSpecialSx());
				if (lastYZ != null) {

					Class<SxYz> clazz = SxYz.class;
					method = clazz.getDeclaredMethod("get" + data.getSpecialSx().name());
					Integer lastValue = (Integer) method.invoke(lastYZ);
					yz.setLastYz(lastValue);
					List<Integer> topValues = new ArrayList<Integer>();
					List<SxLrInfo> lastInfos = new ArrayList<SxLrInfo>();
					for (SX sx : SX.seq()) {
						method = SX.class.getDeclaredMethod("is" + sx.name());
						Boolean isCurrentSX = (Boolean) method.invoke(data.getSpecialSx());
						if (!isCurrentSX) {
							method = clazz.getDeclaredMethod("get" + sx.name());
							lastValue = (Integer) method.invoke(lastYZ);
							SxLrInfo info = new SxLrInfo();
							info.value = lastValue;
							info.special = false;
							lastInfos.add(info);
							if (lastValue != null) {
								lastValue++;
								method = clazz.getDeclaredMethod("set" + sx.name(), Integer.class);
								method.invoke(yz, lastValue);
								topValues.add(lastValue);
							}
						} else {
							SxLrInfo info = new SxLrInfo();
							info.value = yz.getLastYz();
							info.special = true;
							lastInfos.add(info);
						}
					}
					Collections.sort(lastInfos, new Comparator<SxLrInfo>() {

						@Override
						public int compare(SxLrInfo o1, SxLrInfo o2) {
							if (o1.value == null && o2.value == null) {
								return 0;
							} else if (o1.value == null && o2.value != null) {
								return -1;
							} else if (o1.value != null && o2.value == null) {
								return 1;
							} else {
								return o1.value.compareTo(o2.value);
							}
						}

					});
					int pos = 0;
					for (SxLrInfo info : lastInfos) {
						if (info.special) {
							break;
						}
						pos++;
					}
					if (pos < 4) {
						yz.setLastYzColor(Color.Red);
					} else if (pos > 7) {
						yz.setLastYzColor(Color.Green);
					} else {
						yz.setLastYzColor(Color.Yellow);
					}
					return topValues;
				}
				return null;
			}

			@Override
			public int calTotal(SxYz yz) throws Exception {
				Integer total = 0;
				for (SX sx : SX.seq()) {
					Method method = SxYz.class.getDeclaredMethod("get" + sx.name());
					Integer value = (Integer) method.invoke(yz);
					if (value != null) {
						total += value;
					}
				}
				yz.setTotal(total);
				return total;
			}

			@Override
			public void afterProcess() throws Exception {
				calSXZF();
				calZF(SX.values().length, SxYz.class, SxZfYz2.class, sxyzRepository, sxzfyz2Repository,
						new ZFPosHandler<SxZfYz2, SxYz>() {

							@Override
							Integer process(SxZfYz2 zfYz, SxZfYz2 lastZFYZ, SxYz data, SxYz lastYZ, Class<SxYz> yzClazz,
									Class<SxZfYz2> yzzfClazz, int zfLength) throws Exception {
								Integer pos = null;
								if (lastYZ != null) {
									pos = zfLength - lastYZ.getCurrentSx().getPos() + data.getCurrentSx().getPos();
									if (pos >= zfLength) {
										pos -= zfLength;
									}
									Method m = SxZfYz2.class.getDeclaredMethod("setZf" + pos, Integer.class);
									m.invoke(zfYz, 0);
								}
								zfYz.setCurrentPos(pos);
								return pos;
							}

						});
				logger.info("End of calSXYZ...");
			}
		});

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

			logger.info("End of calHMQWYZ...");
		} catch (Exception e) {
			t = e;
		}

		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calSWYZ() {
		try {
			return calYZ(SwYz.class, swyzRepository, kaiJiangRepository,
					new FDYZHandler<SwYz>(SwYz.class, SwNums.class, new ZFHandler() {

						@Override
						public void process() {
							calZF(SwNums.FDS.length, SwYz.class, SwZfYz.class, swyzRepository, swzfyzRepository,
									new GetSuffixHandler<SwZfYz, SwYz>() {

										@Override
										public String process(int index) {
											return SwNums.FDS[index];
										}

									});
							logger.info("End of calSWYZ...");
						}
					}) {
						@Override
						public List<Integer> process(SwYz yz, SwYz lastYZ, KaiJiang data, KaiJiang lastData) throws Exception {
							List<Integer> result = super.process(yz, lastYZ, data, lastData);
							for (int pos = 0; pos < SwNums.FDS.length; pos++) {
								Method m = SwYz.class.getDeclaredMethod("getW" + pos);
								Integer value = (Integer) m.invoke(yz);
								if (value != null && value == 0) {
									yz.setPos(pos + 1);
									break;
								}
							}
							return result;
						}
					});
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	@Async
	public Future<Exception> calMWYZ() {
		return calFDYZ(MwYz.class, MwNums.class, mwyzRepository, new ZFHandler() {

			@Override
			public void process() {
				calZF(MwNums.FDS.length, MwYz.class, MwZfYz.class, mwyzRepository, mwzfyzRepository,
						new GetSuffixHandler<MwZfYz, MwYz>() {

							@Override
							public String process(int index) {
								return MwNums.FDS[index];
							}

						});
				logger.info("End of calMWYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calLHYZ() {
		return calFDYZ(LhYz.class, LhNums.class, lhyzRepository, new ZFHandler() {

			@Override
			public void process() {
				calZF(LhNums.FDS.length, LhYz.class, LhZfYz.class, lhyzRepository, lhzfyzRepository,
						new GetSuffixHandler<LhZfYz, LhYz>() {

							@Override
							public String process(int index) {
								return LhNums.FDS[index];
							}

						});
				logger.info("End of calLHYZ...");
			}

		});

	}

	@Async
	public Future<Exception> calQQYZ() {
		try {
			return calYZ(QqYz.class, qqyzRepository, kaiJiangRepository,
					new FDYZHandler<QqYz>(QqYz.class, QqNums.class, new ZFHandler() {

						@Override
						public void process() {
							calZF(QqNums.FDS.length, QqYz.class, QqZfYz.class, qqyzRepository, qqzfyzRepository,
									new GetSuffixHandler<QqZfYz, QqYz>() {

										@Override
										public String process(int index) {
											return QqNums.FDS[index];
										}

									});
							logger.info("End of calQQYZ...");
						}
					}) {
						@Override
						public List<Integer> process(QqYz yz, QqYz lastYZ, KaiJiang data, KaiJiang lastData) throws Exception {
							List<Integer> result = super.process(yz, lastYZ, data, lastData);
							for (int pos = 1; pos <= QqNums.FDS.length; pos++) {
								Method m = QqYz.class.getDeclaredMethod("getW" + pos);
								Integer value = (Integer) m.invoke(yz);
								if (value != null && value == 0) {
									yz.setPos(pos);
									break;
								}
							}
							return result;
						}
					});
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	@Async
	public Future<Exception> calBSYZ() {
		return calFDYZ(BsYz.class, BsNums.class, bsyzRepository, new ZFHandler() {

			@Override
			public void process() {
				calZF(BsNums.FDS.length, BsYz.class, BsZfYz.class, bsyzRepository, bszfyzRepository,
						new GetSuffixHandler<BsZfYz, BsYz>() {

							@Override
							public String process(int index) {
								return BsNums.FDS[index];
							}

						});
				logger.info("End of calBSYZ...");
			}

		});
	}

	@Async
	public Future<Exception> calWXYZ() {
		return calFDYZ(WxYz.class, WxNums.class, wxyzRepository, new ZFHandler() {

			@Override
			public void process() {
				calZF(WxNums.FDS.length, WxYz.class, WxZfYz.class, wxyzRepository, wxzfyzRepository,
						new GetSuffixHandler<WxZfYz, WxYz>() {

							@Override
							public String process(int index) {
								return WxNums.FDS[index];
							}

						});
				logger.info("End of calWXYZ...");
			}

		});
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

			logger.info("End of calSQYZ...");
		} catch (Exception e) {
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calSXDSYZ() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			SxDsYz lastYz = null;
			String[] arr = { "SxSmall", "SxLarge", "SxSingle", "SxEven", "HmSmall", "HmLarge", "HmSingle", "HmEven" };
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						SxDsYz yz = sxdsyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new SxDsYz();
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
								gm = SxDsYz.class.getDeclaredMethod("get" + suffix);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = SxDsYz.class.getDeclaredMethod("set" + suffix, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
						}

						sxdsyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

			logger.info("End of calSXDSYZ...");
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

			logger.info("End of calTMYZ...");
		} catch (Exception e) {
			t = e;
		}

		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calPTYZ() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			PtYz lastYz = null;
			Class<PtYz> clazz = PtYz.class;
			do {
				result = kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						PtYz yz = ptyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new PtYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						Integer num = data.getSpecialNum();
						sm = clazz.getDeclaredMethod("setHm" + num, Integer.class);
						sm.invoke(yz, 0);
						yz.setSpecialNum(num);
						for (int i = 1; i <= 6; i++) {
							num = (Integer) KaiJiang.class.getDeclaredMethod("getNum" + i).invoke(data);
							sm = clazz.getDeclaredMethod("setHm" + num, Integer.class);
							sm.invoke(yz, 0);
						}

						if (lastYz != null) {
							int add1 = 0;
							int min1 = 0;
							for (int j = 1; j < 50; j++) {
								gm = clazz.getDeclaredMethod("getHm" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = clazz.getDeclaredMethod("setHm" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}

									if (lastValue == 0) {
										int prevNum = j - 1;
										int nextNum = j + 1;
										if (prevNum > 0) {
											gm = clazz.getDeclaredMethod("getHm" + prevNum);
											value = (Integer) gm.invoke(yz);
											if (value != null && value == 0) {
												min1++;
											}
										}
										if (nextNum < 50) {
											gm = clazz.getDeclaredMethod("getHm" + nextNum);
											value = (Integer) gm.invoke(yz);
											if (value != null && value == 0) {
												add1++;
											}
										}
									}
								}
							}
							yz.setAdd1(add1);
							yz.setMin1(min1);

							for (int i = 0; i < 7; i++) {
								sm = clazz.getDeclaredMethod("setJg" + i, Integer.class);
								sm.invoke(yz, 0);
							}
							yz.setJg6Plus(0);

							num = data.getSpecialNum();
							gm = clazz.getDeclaredMethod("getHm" + num);
							Integer lastValue = (Integer) gm.invoke(lastYz);
							if (lastValue != null) {
								if (lastValue < 7) {
									gm = clazz.getDeclaredMethod("getJg" + lastValue);
									Integer value = (Integer) gm.invoke(yz);
									sm = clazz.getDeclaredMethod("setJg" + lastValue, Integer.class);
									sm.invoke(yz, value + 1);
								} else {
									Integer value = yz.getJg6Plus();
									yz.setJg6Plus(value + 1);
								}
							}
							for (int i = 1; i <= 6; i++) {
								num = (Integer) KaiJiang.class.getDeclaredMethod("getNum" + i).invoke(data);
								gm = clazz.getDeclaredMethod("getHm" + num);
								lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									if (lastValue < 7) {
										gm = clazz.getDeclaredMethod("getJg" + lastValue);
										Integer value = (Integer) gm.invoke(yz);
										sm = clazz.getDeclaredMethod("setJg" + lastValue, Integer.class);
										sm.invoke(yz, value + 1);
									} else {
										Integer value = yz.getJg6Plus();
										yz.setJg6Plus(value + 1);
									}
								}
							}
						}

						ptyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

			logger.info("End of calPTYZ...");
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

	@Async
	public Future<Exception> calZSYZ() {
		return calFDYZ(ZsYz.class, ZsNums.class, zsyzRepository, new ZFHandler() {

			@Override
			public void process() {
				calZF(ZsNums.FDS.length, ZsYz.class, ZsZfYz.class, zsyzRepository, zszfyzRepository,
						new GetSuffixHandler<ZsZfYz, ZsYz>() {

							@Override
							public String process(int index) {
								return ZsNums.FDS[index];
							}

						});
				logger.info("End of calZSYZ...");
			}

		});
	}

	@Async
	public Future<Exception> calDSYZ() {
		return calFDYZ(DsYz.class, DsNums.class, dsyzRepository, new ZFHandler() {

			@Override
			public void process() {
				calZF(DsNums.FDS.length, DsYz.class, DsZfYz.class, dsyzRepository, dszfyzRepository,
						new GetSuffixHandler<DsZfYz, DsYz>() {

							@Override
							public String process(int index) {
								return DsNums.FDS[index];
							}

						});
				logger.info("End of calDSYZ...");
			}

		});
	}

	private static abstract class GetSuffixHandler<T extends Avg, R extends Avg> extends ZFPosHandler<T, R> {

		abstract String process(int index) throws Exception;

		Integer process(T zfYz, T lastZFYZ, R data, R lastYZ, Class<R> yzClazz, Class<T> yzzfClazz, int zfLength)
				throws Exception {
			Integer pos = null;
			if (lastZFYZ != null) {
				int lastPos = 0;
				for (int i = 0; i < zfLength; i++) {
					Method gm = yzClazz.getDeclaredMethod("get" + process(i));
					Integer value = (Integer) gm.invoke(lastYZ);
					if (value != null && value == 0) {
						lastPos = i + 1;
						break;
					}
				}
				int currentPos = 0;
				for (int i = 0; i < zfLength; i++) {
					Method gm = yzClazz.getDeclaredMethod("get" + process(i));
					Integer value = (Integer) gm.invoke(data);
					if (value != null && value == 0) {
						currentPos = i + 1;
						break;
					}
				}
				pos = zfLength - lastPos + currentPos;
				if (pos >= zfLength) {
					pos -= zfLength;
				}
				Method m = yzzfClazz.getDeclaredMethod("setZf" + pos, Integer.class);
				m.invoke(zfYz, 0);
			}
			yzzfClazz.getDeclaredMethod("setCurrentPos", Integer.class).invoke(zfYz, pos);
			return pos;
		}
	}

	private static abstract class ZFPosHandler<T extends Avg, R extends Avg> {

		abstract Integer process(T zfYz, T lastZFYZ, R data, R lastYZ, Class<R> yzClazz, Class<T> yzzfClazz, int zfLength)
				throws Exception;
	}

	private <T extends Avg, R extends Avg> void calZF(int zfLength, Class<R> yzClazz, Class<T> yzzfClazz,
			BaseYzRepository<R> yzRepository, BaseYzRepository<T> zfRepository, ZFPosHandler<T, R> handler) {
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<R> result = null;
			R lastYZ = null;
			T lastZFYZ = null;
			do {
				result = yzRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (R data : result.getContent()) {
						T zfYz = zfRepository.findByDate(data.getDate());
						if (zfYz == null) {
							zfYz = yzzfClazz.newInstance();
							zfYz.setYear(data.getYear());
							zfYz.setPhase(data.getPhase());
							zfYz.setDate(data.getDate());
						}

						Integer pos = handler.process(zfYz, lastZFYZ, data, lastYZ, yzClazz, yzzfClazz, zfLength);

						if (lastZFYZ != null) {
							if (pos != null) {
								List<Integer> topValues = new ArrayList<Integer>();
								Method m = yzzfClazz.getDeclaredMethod("getZf" + pos);
								Integer lastValue = (Integer) m.invoke(lastZFYZ);
								zfYz.setLastYz(lastValue);
								for (int i = 0; i < zfLength; i++) {
									lastValue = null;
									if (i != pos) {
										m = yzzfClazz.getDeclaredMethod("getZf" + i);
										lastValue = (Integer) m.invoke(lastZFYZ);
										if (lastValue != null) {
											lastValue++;
											m = yzzfClazz.getDeclaredMethod("setZf" + i, Integer.class);
											m.invoke(zfYz, lastValue);
											topValues.add(lastValue);
										}
									}
								}

								if (!topValues.isEmpty()) {
									Collections.sort(topValues, new Comparator<Integer>() {

										@Override
										public int compare(Integer o1, Integer o2) {
											return o2.compareTo(o1);
										}

									});
									if (topValues.size() > 5) {
										topValues = topValues.subList(0, 5);
									}
									for (int i = 0; i < topValues.size(); i++) {
										m = Avg.class.getDeclaredMethod("setTop" + i, Integer.class);
										m.invoke(zfYz, topValues.get(i));
									}
								}
							}
						}

						if (zfYz.getLastYz() != null) {
							for (int k = 0; k < 20; k++) {
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
						for (int i = 0; i < zfLength; i++) {
							Method m = yzzfClazz.getDeclaredMethod("getZf" + i);
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

						zfRepository.save(zfYz);
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

	private static interface ZFHandler {
		void process();
	}

	private static class FDYZHandler<T extends Avg> implements YzHandler<T, KaiJiang> {
		final ZFHandler handler;
		final String[] fds;
		final Class<?> numsClass;
		final Class<T> clazz;

		FDYZHandler(Class<T> clazz, Class<?> numsClass, ZFHandler handler) throws Exception {
			this.handler = handler;
			this.numsClass = numsClass;
			this.clazz = clazz;
			this.fds = (String[]) numsClass.getDeclaredField("FDS").get(null);
		}

		@Override
		public List<Integer> process(T yz, T lastYZ, KaiJiang data, KaiJiang lastData) throws Exception {
			Integer num = data.getSpecialNum();
			String zeroFd = null;
			for (String fd : fds) {
				Field f = numsClass.getDeclaredField(fd.toUpperCase());
				List<Integer> nums = (List<Integer>) f.get(null);
				if (nums.contains(num)) {
					Method sm = clazz.getDeclaredMethod("set" + fd, Integer.class);
					sm.invoke(yz, 0);
					zeroFd = fd;
					break;
				}
			}
			if (lastYZ != null) {
				Method gm = null;
				Method sm = null;
				List<Integer> topValues = new ArrayList<Integer>();
				for (String fd : fds) {
					if (!fd.equals(zeroFd)) {
						gm = clazz.getDeclaredMethod("get" + fd);
						Integer lastValue = (Integer) gm.invoke(lastYZ);
						if (lastValue != null) {
							lastValue++;
							Integer value = (Integer) gm.invoke(yz);
							if (value == null || value > 0) {
								sm = clazz.getDeclaredMethod("set" + fd, Integer.class);
								sm.invoke(yz, lastValue);
								topValues.add(lastValue);
							}
						}
					}
				}
				gm = clazz.getDeclaredMethod("get" + zeroFd);
				yz.setLastYz((Integer) gm.invoke(lastYZ));
				return topValues;
			}
			return null;
		}

		@Override
		public int calTotal(T yz) throws Exception {
			int total = 0;
			Method gm = null;
			for (String fd : fds) {
				gm = clazz.getDeclaredMethod("get" + fd);
				Integer value = (Integer) gm.invoke(yz);
				if (value != null) {
					total += value;
				}
			}
			yz.setTotal(total);
			return total;
		}

		@Override
		public void afterProcess() throws Exception {
			handler.process();
		}

	}

	private <T extends Avg> Future<Exception> calFDYZ(final Class<T> clazz, final Class<?> numsClass,
			final BaseYzRepository<T> repository, final ZFHandler handler) {
		try {
			return calYZ(clazz, repository, kaiJiangRepository, new FDYZHandler<T>(clazz, numsClass, handler));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static interface YzHandler<T extends Avg, R extends BaseYz> {

		List<Integer> process(T yz, T lastYZ, R data, R lastData) throws Exception;

		int calTotal(T yz) throws Exception;

		void afterProcess() throws Exception;
	}

	private <T extends Avg, R extends BaseYz> Future<Exception> calYZ(Class<T> clazz, BaseYzRepository<T> repository,
			BaseYzRepository<R> scanRepository, YzHandler<T, R> yzHandler) {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<R> result = null;
			T lastYZ = null;
			R lastData = null;
			Class<Avg> avgClass = Avg.class;
			do {
				result = scanRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (R data : result.getContent()) {
						T yz = repository.findByDate(data.getDate());
						if (yz == null) {
							yz = clazz.newInstance();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						List<Integer> topValues = yzHandler.process(yz, lastYZ, data, lastData);
						if (topValues != null) {
							Collections.sort(topValues, new Comparator<Integer>() {

								@Override
								public int compare(Integer o1, Integer o2) {
									return o2.compareTo(o1);
								}

							});
							if (topValues.size() > 5) {
								topValues = topValues.subList(0, 5);
							}
							for (int i = 0; i < topValues.size(); i++) {
								Method m = avgClass.getDeclaredMethod("setTop" + i, Integer.class);
								m.invoke(yz, topValues.get(i));
							}
						}

						if (yz.getLastYz() != null) {
							for (int k = 0; k < 20; k++) {
								Method sm = avgClass.getDeclaredMethod("setMin" + k, Integer.class);
								if (yz.getLastYz() == k) {
									sm.invoke(yz, 0);
								} else {
									Method gm = avgClass.getDeclaredMethod("getMin" + k);
									Integer minValue = (Integer) gm.invoke(lastYZ);
									if (minValue != null) {
										sm.invoke(yz, minValue + 1);
									}
								}
							}
						}

						int total = yzHandler.calTotal(yz);

						if (lastYZ != null) {
							yz.setDelta(total - lastYZ.getTotal());
						} else {
							yz.setDelta(total);
						}

						repository.save(yz);
						lastYZ = yz;
						lastData = data;
					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

			yzHandler.afterProcess();

		} catch (Exception e) {
			t = e;
		}

		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calSXCSYZ() {
		Exception t = null;
		int length = SX.values().length;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<SxYz> result = null;
			Class<SxCsYz> clazz = SxCsYz.class;
			Class<SxYz> yzClazz = SxYz.class;
			SxCsYz lastYZ = new SxCsYz();
			for (SX sx : SX.seq()) {
				Method m = clazz.getDeclaredMethod("set" + sx.name(), Integer.class);
				m.invoke(lastYZ, 0);
			}
			do {
				result = sxyzRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (SxYz data : result.getContent()) {
						SxCsYz yz = sxcsyzRepository.findByDate(data.getDate());
						if (yz == null) {
							yz = new SxCsYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
							yz.setDate(data.getDate());
						}

						for (SX sx : SX.seq()) {
							Method sm = clazz.getDeclaredMethod("set" + sx.name(), Integer.class);
							Method gm = clazz.getDeclaredMethod("get" + sx.name());
							sm.invoke(yz, (Integer) gm.invoke(lastYZ));
						}

						Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
						int currentValue = 0;
						for (SX sx : SX.seq()) {
							Method gm = yzClazz.getDeclaredMethod("get" + sx.name());
							Integer value = (Integer) gm.invoke(data);
							gm = clazz.getDeclaredMethod("get" + sx.name());
							Method sm = clazz.getDeclaredMethod("set" + sx.name(), Integer.class);
							if (value != null && value == 0) {
								currentValue = 1 + (Integer) gm.invoke(yz);
								sm.invoke(yz, currentValue);
								yz.setCurrentSx(sx);
							}

							value = (Integer) gm.invoke(yz);
							if (value > 0) {
								Integer count = counts.get(value);
								if (count == null) {
									count = 0;
								}
								counts.put(value, count + 1);
							}
							sm.invoke(yz, value);
						}

						int pairs = 0;
						for (Integer count : counts.values()) {
							pairs += count - 1;
						}
						yz.setPairs(pairs);

						int total = 0;
						for (SX sx : SX.seq()) {
							Method gm = clazz.getDeclaredMethod("get" + sx.name());
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								total += value;
							}
						}
						yz.setTotal(total);
						yz.setAvg(new BigDecimal(1d * total / length));

						int large = 0;
						int small = 0;
						for (SX sx : SX.seq()) {
							Method gm = clazz.getDeclaredMethod("get" + sx.name());
							Integer value = (Integer) gm.invoke(yz);
							if (value != null) {
								if (new BigDecimal(value).compareTo(yz.getAvg()) >= 0) {
									large++;
								} else if (new BigDecimal(value).compareTo(yz.getAvg()) < 0) {
									small++;
								}
							}
						}
						yz.setLarge(large);
						yz.setSmall(small);

						sxcsyzRepository.save(yz);
						lastYZ = yz;
					}

					request = result.nextPageable();
				}
			} while (result != null && result.hasNext());

			logger.info("End of calSXCSYZ...");
		} catch (Exception e) {
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calSXLRYZ() {
		return calYZ(SxLrYz.class, sxlryzRepository, sxyzRepository, new YzHandler<SxLrYz, SxYz>() {

			@Override
			public List<Integer> process(SxLrYz yz, SxLrYz lastYZ, SxYz data, SxYz lastData) throws Exception {
				Class<SxLrYz> clazz = SxLrYz.class;
				String colorSet = null;
				if (lastData != null) {
					Color lastColor = lastData.getLastYzColor();
					Color currentColor = data.getLastYzColor();
					if (lastColor != null && currentColor != null) {
						colorSet = lastColor.name() + currentColor.name();
						Method m = clazz.getDeclaredMethod("set" + colorSet, Integer.class);
						m.invoke(yz, 0);
						yz.setPos(lastColor.getPos());
					}
				}

				if (lastYZ != null && colorSet != null) {
					List<Integer> topValues = new ArrayList<Integer>();
					for (Color lastColor : Color.values()) {
						for (Color currentColor : Color.values()) {
							String set = lastColor.name() + currentColor.name();
							Method gm = clazz.getDeclaredMethod("get" + set);
							Integer lastValue = (Integer) gm.invoke(lastYZ);
							if (!set.equals(colorSet)) {
								if (lastValue != null) {
									lastValue++;
									Method sm = clazz.getDeclaredMethod("set" + set, Integer.class);
									sm.invoke(yz, lastValue);
									topValues.add(lastValue);
								}
							} else {
								yz.setLastYz(lastValue);
							}
						}
					}

					return topValues;
				}

				return null;
			}

			@Override
			public int calTotal(SxLrYz yz) throws Exception {
				int total = 0;
				for (Color lastColor : Color.values()) {
					for (Color currentColor : Color.values()) {
						String set = lastColor.name() + currentColor.name();
						Method gm = SxLrYz.class.getDeclaredMethod("get" + set);
						Integer value = (Integer) gm.invoke(yz);
						if (value != null) {
							total += value;
						}
					}
				}
				yz.setTotal(total);
				return total;
			}

			@Override
			public void afterProcess() throws Exception {
				logger.info("End of calSXLRYZ...");
			}
		});

	}
}

class SxLrInfo {
	Integer value;
	boolean special;
}

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
import org.springframework.util.ReflectionUtils;

import lhc.constants.BsNums;
import lhc.constants.DsNums;
import lhc.constants.LhNums;
import lhc.constants.MwNums;
import lhc.constants.QqNums;
import lhc.constants.SwNums;
import lhc.constants.TwelveNums;
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
import lhc.domain.LrSet;
import lhc.domain.MwLrYz;
import lhc.domain.MwYz;
import lhc.domain.MwZfYz;
import lhc.domain.PosAvg;
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
import lhc.domain.TwelveLrYz;
import lhc.domain.TwelveYz;
import lhc.domain.TwelveZfYz;
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
import lhc.repository.jpa.api.MwLrYzRepository;
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
import lhc.repository.jpa.api.TwelveLrYzRepository;
import lhc.repository.jpa.api.TwelveYzRepository;
import lhc.repository.jpa.api.TwelveZfYzRepository;
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
	private MwLrYzRepository mwlryzRepository;

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
	private TwelveYzRepository twelveyzRepository;

	@Autowired
	private TwelveLrYzRepository twelvelryzRepository;

	@Autowired
	private TwelveZfYzRepository twelvezfyzRepository;

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
				Method method = ReflectionUtils.findMethod(SxYz.class, "set" + data.getSpecialSx().name(), Integer.class);
				method.invoke(yz, 0);
				yz.setCurrentSx(data.getSpecialSx());
				if (lastYZ != null) {

					Class<SxYz> clazz = SxYz.class;
					method = ReflectionUtils.findMethod(clazz, "get" + data.getSpecialSx().name());
					Integer lastValue = (Integer) method.invoke(lastYZ);
					yz.setLastYz(lastValue);
					List<Integer> topValues = new ArrayList<Integer>();
					List<LrInfo> lastInfos = new ArrayList<LrInfo>();
					for (SX sx : SX.seq()) {
						method = ReflectionUtils.findMethod(SX.class, "is" + sx.name());
						Boolean isCurrentSX = (Boolean) method.invoke(data.getSpecialSx());
						if (!isCurrentSX) {
							method = ReflectionUtils.findMethod(clazz, "get" + sx.name());
							lastValue = (Integer) method.invoke(lastYZ);
							LrInfo info = new LrInfo();
							info.value = lastValue;
							info.special = false;
							lastInfos.add(info);
							if (lastValue != null) {
								lastValue++;
								method = ReflectionUtils.findMethod(clazz, "set" + sx.name(), Integer.class);
								method.invoke(yz, lastValue);
								topValues.add(lastValue);
							}
						} else {
							LrInfo info = new LrInfo();
							info.value = yz.getLastYz();
							info.special = true;
							lastInfos.add(info);
						}
					}
					Collections.sort(lastInfos, new Comparator<LrInfo>() {

						@Override
						public int compare(LrInfo o1, LrInfo o2) {
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
					for (LrInfo info : lastInfos) {
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
					Method method = ReflectionUtils.findMethod(SxYz.class, "get" + sx.name());
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
									Method m = ReflectionUtils.findMethod(SxZfYz2.class, "setZf" + pos, Integer.class);
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
							Method m = ReflectionUtils.findMethod(SxZfYz.class, "setZf" + pos, Integer.class);
							m.invoke(zfYz, 0);
						}
						zfYz.setCurrentPos(pos);

						if (lastZFYZ != null && pos != null) {
							Method m = ReflectionUtils.findMethod(SxZfYz.class, "getZf" + pos);
							Integer lastValue = (Integer) m.invoke(lastZFYZ);
							zfYz.setLastYz(lastValue);
							for (int i = 0; i < 12; i++) {
								if (i != pos) {
									m = ReflectionUtils.findMethod(SxZfYz.class, "getZf" + i);
									lastValue = (Integer) m.invoke(lastZFYZ);
									if (lastValue != null) {
										m = ReflectionUtils.findMethod(SxZfYz.class, "setZf" + i, Integer.class);
										m.invoke(zfYz, lastValue + 1);
									}
								}
							}
						}

						Integer total = 0;
						for (int i = 0; i < 12; i++) {
							Method m = ReflectionUtils.findMethod(SxZfYz.class, "getZf" + i);
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
							gm = ReflectionUtils.findMethod(KaiJiang.class, "getNum" + i);
							Integer num = (Integer) gm.invoke(data);
							String numStr = num.toString();
							if (numStr.length() > 1) {
								numStr = numStr.substring(numStr.length() - 1);
								num = Integer.valueOf(numStr);
							}
							gm = ReflectionUtils.findMethod(QwYz.class, "getW" + num);
							Integer value = (Integer) gm.invoke(yz);
							if (value == null) {
								value = 0;
							}
							sm = ReflectionUtils.findMethod(QwYz.class, "setW" + num, Integer.class);
							sm.invoke(yz, 0);
						}
						Integer num = data.getSpecialNum();
						String numStr = num.toString();
						if (numStr.length() > 1) {
							numStr = numStr.substring(numStr.length() - 1);
							num = Integer.valueOf(numStr);
						}
						sm = ReflectionUtils.findMethod(QwYz.class, "setW" + num, Integer.class);
						sm.invoke(yz, 0);

						if (lastYz != null) {
							for (int j = 0; j < 10; j++) {
								gm = ReflectionUtils.findMethod(QwYz.class, "getW" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = ReflectionUtils.findMethod(QwYz.class, "setW" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
						}

						int total = 0;
						int currentYz = 0;
						for (int j = 0; j < 10; j++) {
							gm = ReflectionUtils.findMethod(QwYz.class, "getW" + j);
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
		return calPosFDYZ(SwYz.class, SwNums.class, swyzRepository, new CommonHandler() {

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
		}, 0, 5);

	}

	@Async
	public Future<Exception> calMWYZ() {
		return calFDYZ(MwYz.class, MwNums.class, mwyzRepository, new CommonHandler() {

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
		}, new LrHandler() {

			@Override
			public int getSmall() {
				return 3;
			}

			@Override
			public int getLarge() {
				return 5;
			}
		});

	}

	@Async
	public Future<Exception> calLHYZ() {
		return calFDYZ(LhYz.class, LhNums.class, lhyzRepository, new CommonHandler() {

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
		return calPosFDYZ(QqYz.class, QqNums.class, qqyzRepository, new CommonHandler() {

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
		}, 1, 8);
	}

	@Async
	public Future<Exception> calBSYZ() {
		return calFDYZ(BsYz.class, BsNums.class, bsyzRepository, new CommonHandler() {

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
		return calFDYZ(WxYz.class, WxNums.class, wxyzRepository, new CommonHandler() {

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
						sm = ReflectionUtils.findMethod(SqYz.class, "setW" + sx.getSector(), Integer.class);
						sm.invoke(yz, 0);

						if (lastYz != null) {
							for (int j = 2; j < 8; j++) {
								gm = ReflectionUtils.findMethod(SqYz.class, "getW" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = ReflectionUtils.findMethod(SqYz.class, "setW" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
							gm = ReflectionUtils.findMethod(SqYz.class, "getW" + sx.getSector());
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						int total = 0;
						for (int j = 2; j < 8; j++) {
							gm = ReflectionUtils.findMethod(SqYz.class, "getW" + j);
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
								gm = ReflectionUtils.findMethod(SxDsYz.class, "get" + suffix);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = ReflectionUtils.findMethod(SxDsYz.class, "set" + suffix, Integer.class);
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
						sm = ReflectionUtils.findMethod(TmYz.class, "setHm" + num, Integer.class);
						sm.invoke(yz, 0);

						if (lastYz != null) {
							for (int j = 1; j < 50; j++) {
								gm = ReflectionUtils.findMethod(TmYz.class, "getHm" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = ReflectionUtils.findMethod(TmYz.class, "setHm" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
							gm = ReflectionUtils.findMethod(TmYz.class, "getHm" + num);
							yz.setLastYz((Integer) gm.invoke(lastYz));
						}

						int total = 0;
						int max = 0;
						for (int j = 1; j < 50; j++) {
							gm = ReflectionUtils.findMethod(TmYz.class, "getHm" + j);
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
						sm = ReflectionUtils.findMethod(clazz, "setHm" + num, Integer.class);
						sm.invoke(yz, 0);
						yz.setSpecialNum(num);
						for (int i = 1; i <= 6; i++) {
							num = (Integer) ReflectionUtils.findMethod(KaiJiang.class, "getNum" + i).invoke(data);
							sm = ReflectionUtils.findMethod(clazz, "setHm" + num, Integer.class);
							sm.invoke(yz, 0);
						}

						if (lastYz != null) {
							int add1 = 0;
							int min1 = 0;
							for (int j = 1; j < 50; j++) {
								gm = ReflectionUtils.findMethod(clazz, "getHm" + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = ReflectionUtils.findMethod(clazz, "setHm" + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}

									if (lastValue == 0) {
										int prevNum = j - 1;
										int nextNum = j + 1;
										if (prevNum > 0) {
											gm = ReflectionUtils.findMethod(clazz, "getHm" + prevNum);
											value = (Integer) gm.invoke(yz);
											if (value != null && value == 0) {
												min1++;
											}
										}
										if (nextNum < 50) {
											gm = ReflectionUtils.findMethod(clazz, "getHm" + nextNum);
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
								sm = ReflectionUtils.findMethod(clazz, "setJg" + i, Integer.class);
								sm.invoke(yz, 0);
							}
							yz.setJg6Plus(0);

							num = data.getSpecialNum();
							gm = ReflectionUtils.findMethod(clazz, "getHm" + num);
							Integer lastValue = (Integer) gm.invoke(lastYz);
							if (lastValue != null) {
								if (lastValue < 7) {
									gm = ReflectionUtils.findMethod(clazz, "getJg" + lastValue);
									Integer value = (Integer) gm.invoke(yz);
									sm = ReflectionUtils.findMethod(clazz, "setJg" + lastValue, Integer.class);
									sm.invoke(yz, value + 1);
								} else {
									Integer value = yz.getJg6Plus();
									yz.setJg6Plus(value + 1);
								}
							}
							for (int i = 1; i <= 6; i++) {
								num = (Integer) ReflectionUtils.findMethod(KaiJiang.class, "getNum" + i).invoke(data);
								gm = ReflectionUtils.findMethod(clazz, "getHm" + num);
								lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									if (lastValue < 7) {
										gm = ReflectionUtils.findMethod(clazz, "getJg" + lastValue);
										Integer value = (Integer) gm.invoke(yz);
										sm = ReflectionUtils.findMethod(clazz, "setJg" + lastValue, Integer.class);
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
					sm = ReflectionUtils.findMethod(TmFdYz.class, "setFd" + num, Integer.class);
					sm.invoke(yz, 0);

					if (lastYz != null) {
						for (int j = 1; j < 8; j++) {
							gm = ReflectionUtils.findMethod(TmFdYz.class, "getFd" + j);
							Integer lastValue = (Integer) gm.invoke(lastYz);
							if (lastValue != null) {
								Integer value = (Integer) gm.invoke(yz);
								if (value == null || value > 0) {
									sm = ReflectionUtils.findMethod(TmFdYz.class, "setFd" + j, Integer.class);
									sm.invoke(yz, lastValue + 1);
								}
							}
						}
						gm = ReflectionUtils.findMethod(TmFdYz.class, "getFd" + num);
						yz.setLastYz((Integer) gm.invoke(lastYz));
					}

					int total = 0;
					int max = 0;
					for (int j = 1; j < 8; j++) {
						gm = ReflectionUtils.findMethod(TmFdYz.class, "getFd" + j);
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
				Method gm = ReflectionUtils.findMethod(TmYz.class, "getHm" + i);
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
		return calFDYZ(ZsYz.class, ZsNums.class, zsyzRepository, new CommonHandler() {

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
		return calFDYZ(DsYz.class, DsNums.class, dsyzRepository, new CommonHandler() {

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

	@Async
	public Future<Exception> calTwelveYZ() {

		return calPosFDYZ(TwelveYz.class, TwelveNums.class, twelveyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(TwelveNums.FDS.length, TwelveYz.class, TwelveZfYz.class, twelveyzRepository, twelvezfyzRepository,
						new GetSuffixHandler<TwelveZfYz, TwelveYz>() {

							@Override
							public String process(int index) {
								return TwelveNums.FDS[index];
							}

						});
				logger.info("End of calTwelveYZ...");
			}
		}, 1, 13, new LrHandler() {

			@Override
			public int getSmall() {
				return 4;
			}

			@Override
			public int getLarge() {
				return 7;
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
					Method gm = ReflectionUtils.findMethod(yzClazz, "get" + process(i));
					Integer value = (Integer) gm.invoke(lastYZ);
					if (value != null && value == 0) {
						lastPos = i + 1;
						break;
					}
				}
				int currentPos = 0;
				for (int i = 0; i < zfLength; i++) {
					Method gm = ReflectionUtils.findMethod(yzClazz, "get" + process(i));
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
				Method m = ReflectionUtils.findMethod(yzzfClazz, "setZf" + pos, Integer.class);
				m.invoke(zfYz, 0);
			}
			ReflectionUtils.findMethod(yzzfClazz, "setCurrentPos", Integer.class).invoke(zfYz, pos);
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
								Method m = ReflectionUtils.findMethod(yzzfClazz, "getZf" + pos);
								Integer lastValue = (Integer) m.invoke(lastZFYZ);
								zfYz.setLastYz(lastValue);
								for (int i = 0; i < zfLength; i++) {
									lastValue = null;
									if (i != pos) {
										m = ReflectionUtils.findMethod(yzzfClazz, "getZf" + i);
										lastValue = (Integer) m.invoke(lastZFYZ);
										if (lastValue != null) {
											lastValue++;
											m = ReflectionUtils.findMethod(yzzfClazz, "setZf" + i, Integer.class);
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
										m = ReflectionUtils.findMethod(Avg.class, "setTop" + i, Integer.class);
										m.invoke(zfYz, topValues.get(i));
									}
								}
							}
						}

						if (zfYz.getLastYz() != null) {
							for (int k = 0; k < 20; k++) {
								Method sm = ReflectionUtils.findMethod(zfYz.getClass(), "setMin" + k, Integer.class);
								if (zfYz.getLastYz() == k) {
									sm.invoke(zfYz, 0);
								} else {
									Method gm = ReflectionUtils.findMethod(zfYz.getClass(), "getMin" + k);
									Integer minValue = (Integer) gm.invoke(lastZFYZ);
									if (minValue != null) {
										sm.invoke(zfYz, minValue + 1);
									}
								}
							}
						}

						Integer total = 0;
						for (int i = 0; i < zfLength; i++) {
							Method m = ReflectionUtils.findMethod(yzzfClazz, "getZf" + i);
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

	private static interface CommonHandler {
		void process();
	}

	private static interface LrHandler {
		int getSmall();

		int getLarge();
	}

	private <T extends PosAvg> Future<Exception> calPosFDYZ(final Class<T> clazz, final Class<?> numsClass,
			final BaseYzRepository<T> repository, final CommonHandler handler, int startPos, int endPos) {
		return calPosFDYZ(clazz, numsClass, repository, handler, startPos, endPos, null);
	}

	private <T extends PosAvg> Future<Exception> calPosFDYZ(final Class<T> clazz, final Class<?> numsClass,
			final BaseYzRepository<T> repository, final CommonHandler handler, int startPos, int endPos,
			final LrHandler lrHandler) {
		try {
			return calYZ(clazz, repository, kaiJiangRepository,
					new PosFDYZHandler<T>(clazz, numsClass, startPos, endPos, handler, lrHandler));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	private static class PosFDYZHandler<T extends PosAvg> extends FDYZHandler<T> {

		final int startPos;
		final int endPos;

		PosFDYZHandler(Class<T> clazz, Class<?> numsClass, int startPos, int endPos, CommonHandler handler)
				throws Exception {
			super(clazz, numsClass, handler);
			this.startPos = startPos;
			this.endPos = endPos;
		}

		PosFDYZHandler(Class<T> clazz, Class<?> numsClass, int startPos, int endPos, CommonHandler handler,
				LrHandler lrHandler) throws Exception {
			super(clazz, numsClass, handler, lrHandler);
			this.startPos = startPos;
			this.endPos = endPos;
		}

		protected String getPrefix() {
			return "W";
		}

		@Override
		public List<Integer> process(T yz, T lastYZ, KaiJiang data, KaiJiang lastData) throws Exception {
			List<Integer> result = super.process(yz, lastYZ, data, lastData);
			for (int pos = startPos; pos < endPos; pos++) {
				Method m = ReflectionUtils.findMethod(clazz, "get" + getPrefix() + pos);
				Integer value = (Integer) m.invoke(yz);
				if (value != null && value == 0) {
					yz.setPos(pos);
					break;
				}
			}
			return result;
		}

	}

	private static class FDYZHandler<T extends Avg> implements YzHandler<T, KaiJiang> {
		final CommonHandler handler;
		final LrHandler lrHandler;
		final String[] fds;
		final Class<?> numsClass;
		final Class<T> clazz;

		FDYZHandler(Class<T> clazz, Class<?> numsClass, CommonHandler handler) throws Exception {
			this(clazz, numsClass, handler, null);
		}

		FDYZHandler(Class<T> clazz, Class<?> numsClass, CommonHandler handler, LrHandler lrHandler) throws Exception {
			this.lrHandler = lrHandler;
			this.handler = handler;
			this.numsClass = numsClass;
			this.clazz = clazz;
			this.fds = (String[]) ReflectionUtils.findField(numsClass, "FDS").get(null);
		}

		@Override
		public List<Integer> process(T yz, T lastYZ, KaiJiang data, KaiJiang lastData) throws Exception {
			Integer num = data.getSpecialNum();
			String zeroFd = null;
			for (String fd : fds) {
				Field f = ReflectionUtils.findField(numsClass, fd.toUpperCase());
				List<Integer> nums = (List<Integer>) f.get(null);
				if (nums.contains(num)) {
					Method sm = ReflectionUtils.findMethod(clazz, "set" + fd, Integer.class);
					sm.invoke(yz, 0);
					zeroFd = fd;
					break;
				}
			}
			if (lastYZ != null) {
				Method gm = null;
				Method sm = null;
				List<LrInfo> lastInfos = new ArrayList<LrInfo>();
				List<Integer> topValues = new ArrayList<Integer>();
				for (String fd : fds) {
					gm = ReflectionUtils.findMethod(clazz, "get" + fd);
					Integer lastValue = (Integer) gm.invoke(lastYZ);
					if (!fd.equals(zeroFd)) {
						LrInfo info = new LrInfo();
						info.value = lastValue;
						info.special = false;
						lastInfos.add(info);
						if (lastValue != null) {
							lastValue++;
							Integer value = (Integer) gm.invoke(yz);
							if (value == null || value > 0) {
								sm = ReflectionUtils.findMethod(clazz, "set" + fd, Integer.class);
								sm.invoke(yz, lastValue);
								topValues.add(lastValue);
							}
						}
					} else {
						LrInfo info = new LrInfo();
						info.value = lastValue;
						info.special = true;
						lastInfos.add(info);
					}
				}
				gm = ReflectionUtils.findMethod(clazz, "get" + zeroFd);
				yz.setLastYz((Integer) gm.invoke(lastYZ));

				if (lrHandler != null) {
					sm = ReflectionUtils.findMethod(clazz, "setLastYzColor", Color.class);
					Collections.sort(lastInfos, new Comparator<LrInfo>() {

						@Override
						public int compare(LrInfo o1, LrInfo o2) {
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
					for (LrInfo info : lastInfos) {
						if (info.special) {
							break;
						}
						pos++;
					}
					if (pos < lrHandler.getSmall()) {
						sm.invoke(yz, Color.Red);
					} else if (pos > lrHandler.getLarge()) {
						sm.invoke(yz, Color.Green);
					} else {
						sm.invoke(yz, Color.Yellow);
					}
				}

				return topValues;
			}
			return null;
		}

		@Override
		public int calTotal(T yz) throws Exception {
			int total = 0;
			Method gm = null;
			for (String fd : fds) {
				gm = ReflectionUtils.findMethod(clazz, "get" + fd);
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
			final BaseYzRepository<T> repository, final CommonHandler handler) {
		try {
			return calYZ(clazz, repository, kaiJiangRepository, new FDYZHandler<T>(clazz, numsClass, handler));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private <T extends Avg> Future<Exception> calFDYZ(final Class<T> clazz, final Class<?> numsClass,
			final BaseYzRepository<T> repository, final CommonHandler handler, LrHandler lrHandler) {
		try {
			return calYZ(clazz, repository, kaiJiangRepository, new FDYZHandler<T>(clazz, numsClass, handler, lrHandler));
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
								Method m = ReflectionUtils.findMethod(avgClass, "setTop" + i, Integer.class);
								m.invoke(yz, topValues.get(i));
							}
						}

						if (yz.getLastYz() != null) {
							for (int k = 0; k < 20; k++) {
								Method sm = ReflectionUtils.findMethod(avgClass, "setMin" + k, Integer.class);
								if (yz.getLastYz() == k) {
									sm.invoke(yz, 0);
								} else {
									Method gm = ReflectionUtils.findMethod(avgClass, "getMin" + k);
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
				Method m = ReflectionUtils.findMethod(clazz, "set" + sx.name(), Integer.class);
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
							Method sm = ReflectionUtils.findMethod(clazz, "set" + sx.name(), Integer.class);
							Method gm = ReflectionUtils.findMethod(clazz, "get" + sx.name());
							sm.invoke(yz, (Integer) gm.invoke(lastYZ));
						}

						Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
						int currentValue = 0;
						for (SX sx : SX.seq()) {
							Method gm = ReflectionUtils.findMethod(yzClazz, "get" + sx.name());
							Integer value = (Integer) gm.invoke(data);
							gm = ReflectionUtils.findMethod(clazz, "get" + sx.name());
							Method sm = ReflectionUtils.findMethod(clazz, "set" + sx.name(), Integer.class);
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
							Method gm = ReflectionUtils.findMethod(clazz, "get" + sx.name());
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
							Method gm = ReflectionUtils.findMethod(clazz, "get" + sx.name());
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

	private <T extends LrSet, R extends BaseYz> Future<Exception> calLRYZ(final Class<T> lrClazz,
			BaseYzRepository<T> lrRepository, BaseYzRepository<R> yzRepository, CommonHandler handler) {
		return calYZ(lrClazz, lrRepository, yzRepository, new YzHandler<T, R>() {

			@Override
			public List<Integer> process(T yz, T lastYZ, R data, R lastData) throws Exception {
				Class<?> clazz = lrClazz.getSuperclass();
				Class<?> yzClazz = data.getClass().getSuperclass();

				Method m = ReflectionUtils.findMethod(yzClazz, "getLastYzColor");
				Color currentColor = (Color) m.invoke(data);
				if (currentColor != null) {
					m = ReflectionUtils.findMethod(yzClazz, "set" + currentColor.name(), Integer.class);
					m.invoke(data, 0);
				}

				String colorSet = null;
				if (lastData != null) {
					if (currentColor != null) {
						Integer max = 0;
						Integer total = 0;
						String[] fds = { "Red", "Yellow", "Green" };
						for (String fd : fds) {
							m = ReflectionUtils.findMethod(yzClazz, "get" + fd);
							Integer value = (Integer) m.invoke(data);
							if (value == null || value > 0) {
								Integer lastValue = (Integer) m.invoke(lastData);
								if (lastValue != null) {
									lastValue++;
									m = ReflectionUtils.findMethod(yzClazz, "set" + fd, Integer.class);
									m.invoke(data, lastValue);
									if (max < lastValue) {
										max = lastValue;
									}
									total += lastValue;
								}
							}
						}
						if (max > 0) {
							m = ReflectionUtils.findMethod(yzClazz, "setColorMax", Integer.class);
							m.invoke(data, max);
						}
						if (total > 0) {
							m = ReflectionUtils.findMethod(yzClazz, "setColorTotal", Integer.class);
							m.invoke(data, total);
						}
						m = ReflectionUtils.findMethod(yzClazz, "getLastYzColor");
						Color lastColor = (Color) m.invoke(lastData);
						if (lastColor != null) {
							colorSet = lastColor.name() + currentColor.name();
							m = ReflectionUtils.findMethod(clazz, "set" + colorSet, Integer.class);
							m.invoke(yz, 0);
							if (lastColor.equals(currentColor)) {
								Method gm = ReflectionUtils.findMethod(yzClazz, "getColorCount");
								Integer count = (Integer) gm.invoke(lastData);
								m = ReflectionUtils.findMethod(yzClazz, "setColorCount", int.class);
								m.invoke(data, count + 1);
							}
							yz.setPos(lastColor.getPos());
						}
					}
				}

				yzRepository.save(data);

				if (lastYZ != null && colorSet != null) {
					List<Integer> topValues = new ArrayList<Integer>();
					for (Color lastColor : Color.values()) {
						for (Color color : Color.values()) {
							String set = lastColor.name() + color.name();
							Method gm = ReflectionUtils.findMethod(clazz, "get" + set);
							Integer lastValue = (Integer) gm.invoke(lastYZ);
							if (!set.equals(colorSet)) {
								if (lastValue != null) {
									lastValue++;
									Method sm = ReflectionUtils.findMethod(clazz, "set" + set, Integer.class);
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
			public int calTotal(T yz) throws Exception {
				int total = 0;
				for (Color lastColor : Color.values()) {
					for (Color currentColor : Color.values()) {
						String set = lastColor.name() + currentColor.name();
						Method gm = ReflectionUtils.findMethod(lrClazz, "get" + set);
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
				handler.process();
			}
		});

	}

	@Async
	public Future<Exception> calSXLRYZ() {
		return calLRYZ(SxLrYz.class, sxlryzRepository, sxyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calSXLRYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calTwelveLRYZ() {
		return calLRYZ(TwelveLrYz.class, twelvelryzRepository, twelveyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calTwelveLRYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calMWLRYZ() {
		return calLRYZ(MwLrYz.class, mwlryzRepository, mwyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calMWLRYZ...");
			}
		});

	}
}

class LrInfo {
	Integer value;
	boolean special;
}

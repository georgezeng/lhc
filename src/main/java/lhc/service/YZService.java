package lhc.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import lhc.constants.Bs9qNums;
import lhc.constants.BsNums;
import lhc.constants.DsNums;
import lhc.constants.LhNums;
import lhc.constants.MwNums;
import lhc.constants.PdNums;
import lhc.constants.QiwNums;
import lhc.constants.QqNums;
import lhc.constants.SlqNums;
import lhc.constants.SwNums;
import lhc.constants.SxNums;
import lhc.constants.TwelveNums;
import lhc.constants.WxDsNums;
import lhc.constants.WxNums;
import lhc.constants.ZsNums;
import lhc.domain.Avg;
import lhc.domain.BaseCsYz;
import lhc.domain.BaseYz;
import lhc.domain.Bs9qLrYz;
import lhc.domain.Bs9qYz;
import lhc.domain.Bs9qZfYz;
import lhc.domain.BsYz;
import lhc.domain.BsZfYz;
import lhc.domain.DsLrYz;
import lhc.domain.DsYz;
import lhc.domain.DsZfYz;
import lhc.domain.HmDsYz;
import lhc.domain.KaiJiang;
import lhc.domain.LhLrYz;
import lhc.domain.LhYz;
import lhc.domain.LhZfYz;
import lhc.domain.LrSet;
import lhc.domain.MwCsYz;
import lhc.domain.MwLrYz;
import lhc.domain.MwYz;
import lhc.domain.MwZfYz;
import lhc.domain.PdLrYz;
import lhc.domain.PdYz;
import lhc.domain.PdZfYz;
import lhc.domain.PosAvg;
import lhc.domain.PtYz;
import lhc.domain.QiwYz;
import lhc.domain.QiwZfYz;
import lhc.domain.QqYz;
import lhc.domain.QqZfYz;
import lhc.domain.QwYz;
import lhc.domain.SlqLrYz;
import lhc.domain.SlqYz;
import lhc.domain.SlqZfYz;
import lhc.domain.SqYz;
import lhc.domain.SwYz;
import lhc.domain.SwZfYz;
import lhc.domain.SxCsYz;
import lhc.domain.SxDsYz;
import lhc.domain.SxLrYz;
import lhc.domain.SxYz;
import lhc.domain.SxZfYz;
import lhc.domain.SxZfYz2;
import lhc.domain.Tm12FdLrYz;
import lhc.domain.Tm12FdYz;
import lhc.domain.Tm12FdZfYz;
import lhc.domain.TmFdYz;
import lhc.domain.TmYz;
import lhc.domain.TwelveLrYz;
import lhc.domain.TwelveYz;
import lhc.domain.TwelveZfYz;
import lhc.domain.WxYz;
import lhc.domain.WxZfYz;
import lhc.domain.WxdsLrYz;
import lhc.domain.WxdsYz;
import lhc.domain.WxdsZfYz;
import lhc.domain.ZsLrYz;
import lhc.domain.ZsYz;
import lhc.domain.ZsZfYz;
import lhc.dto.D1Yz;
import lhc.dto.J0Yz;
import lhc.dto.TmYzInfo;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.enums.Color;
import lhc.enums.SX;
import lhc.repository.jpa.BaseYzRepository;
import lhc.repository.jpa.Repositories;
import lhc.util.DateUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class YZService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Repositories repositories;

	@Async
	public Future<Exception> calSX() {
		return calYZ(SxYz.class, repositories.sxyzRepository, repositories.kaiJiangRepository,
				new YzHandler<SxYz, KaiJiang>() {

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
						calZF(SX.values().length, SxYz.class, SxZfYz2.class, repositories.sxyzRepository,
								repositories.sxzfyz2Repository, new ZFPosHandler<SxZfYz2, SxYz>() {

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
				result = repositories.sxyzRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (SxYz data : result.getContent()) {
						SxZfYz zfYz = repositories.sxzfyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
						if (zfYz == null) {
							zfYz = new SxZfYz();
							zfYz.setYear(data.getYear());
							zfYz.setPhase(data.getPhase());
						}
						zfYz.setDate(data.getDate());

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

						repositories.sxzfyzRepository.save(zfYz);
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
				result = repositories.kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						QwYz yz = repositories.qwyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
						if (yz == null) {
							yz = new QwYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
						}
						yz.setDate(data.getDate());

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

						repositories.qwyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

			logger.info("End of calHMQWYZ...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}

		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calSWYZ() {
		return calPosFDYZ(SwYz.class, SwNums.class, repositories.swyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(SwNums.FDS.length, SwYz.class, SwZfYz.class, repositories.swyzRepository, repositories.swzfyzRepository,
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
		return calFDYZ(MwYz.class, MwNums.class, repositories.mwyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(MwNums.FDS.length, MwYz.class, MwZfYz.class, repositories.mwyzRepository, repositories.mwzfyzRepository,
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
	public Future<Exception> calBS9QYZ() {
		return calFDYZ(Bs9qYz.class, Bs9qNums.class, repositories.bs9qyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(Bs9qNums.FDS.length, Bs9qYz.class, Bs9qZfYz.class, repositories.bs9qyzRepository,
						repositories.bs9qzfyzRepository, new GetSuffixHandler<Bs9qZfYz, Bs9qYz>() {

							@Override
							public String process(int index) {
								return Bs9qNums.FDS[index];
							}

						});
				logger.info("End of calBS9QYZ...");
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
		return calFDYZ(LhYz.class, LhNums.class, repositories.lhyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(LhNums.FDS.length, LhYz.class, LhZfYz.class, repositories.lhyzRepository, repositories.lhzfyzRepository,
						new GetSuffixHandler<LhZfYz, LhYz>() {

							@Override
							public String process(int index) {
								return LhNums.FDS[index];
							}

						});
				logger.info("End of calLHYZ...");
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
	public Future<Exception> calQQYZ() {
		return calPosFDYZ(QqYz.class, QqNums.class, repositories.qqyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(QqNums.FDS.length, QqYz.class, QqZfYz.class, repositories.qqyzRepository, repositories.qqzfyzRepository,
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
	public Future<Exception> calQIWYZ() {
		return calPosFDYZ(QiwYz.class, QiwNums.class, repositories.qiwYzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(QiwNums.FDS.length, QiwYz.class, QiwZfYz.class, repositories.qiwYzRepository,
						repositories.qiwzfYzRepository, new GetSuffixHandler<QiwZfYz, QiwYz>() {

							@Override
							public String process(int index) {
								return QiwNums.FDS[index];
							}

						});
				logger.info("End of calQIWYZ...");
			}
		}, 1, 8);
	}

	@Async
	public Future<Exception> calBSYZ() {
		return calFDYZ(BsYz.class, BsNums.class, repositories.bsyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(BsNums.FDS.length, BsYz.class, BsZfYz.class, repositories.bsyzRepository, repositories.bszfyzRepository,
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
	public Future<Exception> calSLQYZ() {
		return calFDYZ(SlqYz.class, SlqNums.class, repositories.slqyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(SlqNums.FDS.length, SlqYz.class, SlqZfYz.class, repositories.slqyzRepository,
						repositories.slqzfyzRepository, new GetSuffixHandler<SlqZfYz, SlqYz>() {

							@Override
							public String process(int index) {
								return SlqNums.FDS[index];
							}

						});
				logger.info("End of calSLQYZ...");
			}

		}, new LrHandler() {

			@Override
			public int getSmall() {
				return 5;
			}

			@Override
			public int getLarge() {
				return 9;
			}
		});
	}

	@Async
	public Future<Exception> calPDYZ() {
		return calFDYZ(PdYz.class, PdNums.class, repositories.pdyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(PdNums.FDS.length, PdYz.class, PdZfYz.class, repositories.pdyzRepository, repositories.pdzfyzRepository,
						new GetSuffixHandler<PdZfYz, PdYz>() {

							@Override
							public String process(int index) {
								return PdNums.FDS[index];
							}

						});
				logger.info("End of calPDYZ...");
			}

		}, new LrHandler() {

			@Override
			public int getSmall() {
				return 3;
			}

			@Override
			public int getLarge() {
				return 7;
			}
		});
	}

	@Async
	public Future<Exception> calWXYZ() {
		return calFDYZ(WxYz.class, WxNums.class, repositories.wxyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(WxNums.FDS.length, WxYz.class, WxZfYz.class, repositories.wxyzRepository, repositories.wxzfyzRepository,
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
	public Future<Exception> calWXDSYZ() {
		return calFDYZ(WxdsYz.class, WxDsNums.class, repositories.wxdsyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(WxDsNums.FDS.length, WxdsYz.class, WxdsZfYz.class, repositories.wxdsyzRepository,
						repositories.wxdszfyzRepository, new GetSuffixHandler<WxdsZfYz, WxdsYz>() {

							@Override
							public String process(int index) {
								return WxDsNums.FDS[index];
							}

						});
				logger.info("End of calWXDSYZ...");
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
	public Future<Exception> calSQYZ() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			SqYz lastYz = null;
			do {
				result = repositories.kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						SqYz yz = repositories.sqyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
						if (yz == null) {
							yz = new SqYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
						}
						yz.setDate(data.getDate());

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

						repositories.sqyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

			logger.info("End of calSQYZ...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
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
				result = repositories.kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						SxDsYz yz = repositories.sxdsyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
						if (yz == null) {
							yz = new SxDsYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
						}
						yz.setDate(data.getDate());

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

						repositories.sxdsyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

			logger.info("End of calSXDSYZ...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
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
				result = repositories.kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						TmYz yz = repositories.tmyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
						if (yz == null) {
							yz = new TmYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
						}
						yz.setDate(data.getDate());

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

						repositories.tmyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

			calTMFDYZ(7, 7, "Fd", repositories.tmfdyzRepository, TmFdYz.class, false, null);

			logger.info("End of calTMYZ...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
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
				result = repositories.kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						PtYz yz = repositories.ptyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
						if (yz == null) {
							yz = new PtYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
						}
						yz.setDate(data.getDate());

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

						repositories.ptyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

			logger.info("End of calPTYZ...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}

		return new AsyncResult<Exception>(t);
	}

	private <T extends BaseYz> void calTMFDYZ(int range, int length, String prefix, BaseYzRepository<T> repository,
			Class<T> clazz, boolean calTopAndMin, LrHandler lrHandler) throws Exception {
		Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
		Page<TmYz> result = null;
		T lastYz = null;
		do {
			result = repositories.tmyzRepository.findAll(request);
			Method gm = null;
			Method sm = null;
			if (result != null && result.hasContent()) {
				for (TmYz data : result.getContent()) {
					T yz = repository.findByYearAndPhase(data.getYear(), data.getPhase());
					if (yz == null) {
						yz = clazz.newInstance();
						yz.setYear(data.getYear());
						yz.setPhase(data.getPhase());
					}
					yz.setDate(data.getDate());

					List<TmYzInfo> infos = getTMFDList(data, true);
					Method m = ReflectionUtils.findMethod(clazz, "setTm", Integer.class);
					int num = 1;
					for (TmYzInfo info : infos) {
						if (info.isTm()) {
							if (m != null) {
								m.invoke(yz, info.getNum());
							}
							break;
						}
						num++;
					}
					if (num % range == 0) {
						num = num / range;
					} else {
						num = num / range + 1;
					}
					if (num > length) {
						num = length;
					}
					sm = ReflectionUtils.findMethod(clazz, "set" + prefix + num, Integer.class);
					sm.invoke(yz, 0);

					if (lastYz != null) {
						if (calTopAndMin) {
							List<Integer> topValues = new ArrayList<Integer>();
							List<LrInfo> lastInfos = new ArrayList<LrInfo>();
							for (int j = 1; j <= length; j++) {
								gm = ReflectionUtils.findMethod(clazz, "get" + prefix + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (j != num) {
									LrInfo info = new LrInfo();
									info.value = lastValue;
									info.special = false;
									lastInfos.add(info);
									if (lastValue != null) {
										lastValue++;
										Integer value = (Integer) gm.invoke(yz);
										if (value == null || value > 0) {
											sm = ReflectionUtils.findMethod(clazz, "set" + prefix + j, Integer.class);
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
							}
							gm = ReflectionUtils.findMethod(clazz, "get" + prefix + num);
							m = ReflectionUtils.findMethod(clazz, "setLastYz", Integer.class);
							m.invoke(yz, gm.invoke(lastYz));
							m = ReflectionUtils.findMethod(clazz, "getTm");
							if (m != null) {
								sm = ReflectionUtils.findMethod(clazz, "setPrevDelta", Integer.class);
								Integer value = (Integer) m.invoke(yz) - (Integer) m.invoke(lastYz);
								sm.invoke(yz, value);
							}

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
									m = ReflectionUtils.findMethod(clazz, "setTop" + i, Integer.class);
									m.invoke(yz, topValues.get(i));
								}
							}
							m = ReflectionUtils.findMethod(clazz, "getLastYz");
							Integer lastYzValue = (Integer) m.invoke(yz);
							if (lastYzValue != null) {
								for (int k = 0; k < 20; k++) {
									sm = ReflectionUtils.findMethod(clazz, "setMin" + k, Integer.class);
									if (lastYzValue == k) {
										sm.invoke(yz, 0);
									} else {
										gm = ReflectionUtils.findMethod(clazz, "getMin" + k);
										Integer minValue = (Integer) gm.invoke(lastYz);
										if (minValue != null) {
											sm.invoke(yz, minValue + 1);
										}
									}
								}
							}
						} else {
							for (int j = 1; j <= length; j++) {
								gm = ReflectionUtils.findMethod(clazz, "get" + prefix + j);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = ReflectionUtils.findMethod(clazz, "set" + prefix + j, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
							gm = ReflectionUtils.findMethod(clazz, "get" + prefix + num);
							m = ReflectionUtils.findMethod(clazz, "setLastYz", Integer.class);
							m.invoke(yz, gm.invoke(lastYz));
							m = ReflectionUtils.findMethod(clazz, "getTm");
							if (m != null) {
								sm = ReflectionUtils.findMethod(clazz, "setPrevDelta", Integer.class);
								Integer value = (Integer) m.invoke(yz) - (Integer) m.invoke(lastYz);
								sm.invoke(yz, value);
							}
						}
					}

					int total = 0;
					int max = 0;
					for (int j = 1; j <= length; j++) {
						gm = ReflectionUtils.findMethod(clazz, "get" + prefix + j);
						Integer value = (Integer) gm.invoke(yz);
						if (value != null) {
							if (value > max) {
								max = value;
							}
							total += value;
						}
					}
					if (!calTopAndMin) {
						m = ReflectionUtils.findMethod(clazz, "setMaxYz", Integer.class);
						if (m != null) {
							m.invoke(yz, max);
						}
					}
					m = ReflectionUtils.findMethod(clazz, "setTotal", Integer.class);
					m.invoke(yz, total);

					if (lastYz != null) {
						m = ReflectionUtils.findMethod(clazz, "getTotal");
						Integer delta = total - (Integer) m.invoke(lastYz);
						m = ReflectionUtils.findMethod(clazz, "setDelta", Integer.class);
						m.invoke(yz, delta);
					}

					repository.save(yz);
					lastYz = yz;

				}
			}
			request = result.nextPageable();
		} while (result != null && result.hasNext());
	}

	public List<TmYzInfo> getTMFDList(BaseYz data, boolean treatAsLastYz) {
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
						Method m = ReflectionUtils.findMethod(data.getClass(), "getLastYz");
						Integer value = null;
						try {
							value = (Integer) m.invoke(data);
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
						if (treatAsLastYz && value != null) {
							if (o1.getYz() == 0) {
								o1.setYz(value);
							}
							if (o2.getYz() == 0) {
								o2.setYz(value);
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
	public Future<Exception> calTM12FDYZ() {
		Exception t = null;
		try {
			calTMFDYZ(4, 12, "W", repositories.tm12fdyzRepository, Tm12FdYz.class, true, new LrHandler() {

				@Override
				public int getSmall() {
					return 4;
				}

				@Override
				public int getLarge() {
					return 7;
				}

			});
			calZF(12, Tm12FdYz.class, Tm12FdZfYz.class, repositories.tm12fdyzRepository, repositories.tm12fdzfyzRepository,
					new GetSuffixHandler<Tm12FdZfYz, Tm12FdYz>() {

						@Override
						public String process(int index) {
							return "W" + (index + 1);
						}

					});
			logger.info("End of calTM12FDYZ...");
		} catch (Exception e) {
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calHMDSYZ() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			HmDsYz lastYz = null;
			String[] arr = { "Small", "Large", "Odd", "Even", "SmallOdd", "SmallEven", "LargeOdd", "LargeEven" };
			do {
				result = repositories.kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						HmDsYz yz = repositories.hmdsyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
						if (yz == null) {
							yz = new HmDsYz();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
						}
						yz.setDate(data.getDate());

						Integer num = data.getSpecialNum();
						boolean isSmallOdd = num < 26 && num % 2 != 0;
						boolean isSmallEven = num < 26 && num % 2 == 0;
						boolean isLargeOdd = num > 25 && num % 2 != 0;
						boolean isLargeEven = num > 25 && num % 2 == 0;
						boolean isSmall = num < 26;
						boolean isOdd = num % 2 != 0;

						if (isSmallOdd) {
							yz.setSmallOdd(0);
							if (lastYz != null) {
								yz.setLastSLOEYz(lastYz.getSmallOdd());
							}
						}
						if (isSmallEven) {
							yz.setSmallEven(0);
							if (lastYz != null) {
								yz.setLastSLOEYz(lastYz.getSmallEven());
							}
						}
						if (isLargeOdd) {
							yz.setLargeOdd(0);
							if (lastYz != null) {
								yz.setLastSLOEYz(lastYz.getLargeOdd());
							}
						}
						if (isLargeEven) {
							yz.setLargeEven(0);
							if (lastYz != null) {
								yz.setLastSLOEYz(lastYz.getLargeEven());
							}
						}
						if (isSmall) {
							yz.setSmall(0);
							if (lastYz != null) {
								yz.setLastSLYz(lastYz.getSmall());
							}
						} else {
							yz.setLarge(0);
							if (lastYz != null) {
								yz.setLastSLYz(lastYz.getLarge());
							}
						}
						if (isOdd) {
							yz.setOdd(0);
							if (lastYz != null) {
								yz.setLastOEYz(lastYz.getOdd());
							}
						} else {
							yz.setEven(0);
							if (lastYz != null) {
								yz.setLastOEYz(lastYz.getEven());
							}
						}

						if (lastYz != null) {
							for (int j = 0; j < arr.length; j++) {
								String suffix = arr[j];
								gm = ReflectionUtils.findMethod(HmDsYz.class, "get" + suffix);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = ReflectionUtils.findMethod(HmDsYz.class, "set" + suffix, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
						}

						repositories.hmdsyzRepository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());

			logger.info("End of calHMDSYZ...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calZSYZ() {
		return calFDYZ(ZsYz.class, ZsNums.class, repositories.zsyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(ZsNums.FDS.length, ZsYz.class, ZsZfYz.class, repositories.zsyzRepository, repositories.zszfyzRepository,
						new GetSuffixHandler<ZsZfYz, ZsYz>() {

							@Override
							public String process(int index) {
								return ZsNums.FDS[index];
							}

						});
				logger.info("End of calZSYZ...");
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
	public Future<Exception> calDSYZ() {
		return calFDYZ(DsYz.class, DsNums.class, repositories.dsyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(DsNums.FDS.length, DsYz.class, DsZfYz.class, repositories.dsyzRepository, repositories.dszfyzRepository,
						new GetSuffixHandler<DsZfYz, DsYz>() {

							@Override
							public String process(int index) {
								return DsNums.FDS[index];
							}

						});
				logger.info("End of calDSYZ...");
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
	public Future<Exception> calTwelveYZ() {

		return calPosFDYZ(TwelveYz.class, TwelveNums.class, repositories.twelveyzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(TwelveNums.FDS.length, TwelveYz.class, TwelveZfYz.class, repositories.twelveyzRepository,
						repositories.twelvezfyzRepository, new GetSuffixHandler<TwelveZfYz, TwelveYz>() {

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
						T zfYz = zfRepository.findByYearAndPhase(data.getYear(), data.getPhase());
						if (zfYz == null) {
							zfYz = yzzfClazz.newInstance();
							zfYz.setYear(data.getYear());
							zfYz.setPhase(data.getPhase());
						}
						zfYz.setDate(data.getDate());

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
			return calYZ(clazz, repository, repositories.kaiJiangRepository,
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
					if (startPos == 0) {
						pos++;
					}
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
			return calYZ(clazz, repository, repositories.kaiJiangRepository, new FDYZHandler<T>(clazz, numsClass, handler));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private <T extends Avg> Future<Exception> calFDYZ(final Class<T> clazz, final Class<?> numsClass,
			final BaseYzRepository<T> repository, final CommonHandler handler, LrHandler lrHandler) {
		try {
			return calYZ(clazz, repository, repositories.kaiJiangRepository,
					new FDYZHandler<T>(clazz, numsClass, handler, lrHandler));
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
						T yz = repository.findByYearAndPhase(data.getYear(), data.getPhase());
						if (yz == null) {
							yz = clazz.newInstance();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
						}
						yz.setDate(data.getDate());

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
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}

		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calSXCSYZ() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<SxYz> result = null;
			SxCsYz lastYz = null;
			do {
				result = repositories.sxyzRepository.findAll(request);
				if (result != null && result.hasContent()) {
					PageResult<SxYz> pResult = new PageResult<SxYz>();
					pResult.setList(result.getContent());
					pResult.setTotal(result.getTotalElements());
					Map<String, Object> map = calSXCSYZ(null, pResult, repositories.sxcsyzRepository, lastYz);
					lastYz = (SxCsYz) map.get("lastYz");
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
			logger.info("End of calSXCSYZ...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	public Map<String, Object> calSXCSYZ(PageInfo page, PageResult<SxYz> result, BaseYzRepository<SxCsYz> repository,
			SxCsYz lastYZ) throws Exception {
		return calCSYZ(page, result, repository, lastYZ, SX.names(), SxCsYz.class, SxYz.class, new CSHandler<SxCsYz>() {

			@Override
			public void doExtra(SxCsYz yz, String fd) {
				yz.setCurrentSx(SX.valueOf(fd));
			}

		});
	}

	public Map<String, Object> calMWCSYZ(PageInfo page, PageResult<MwYz> result, BaseYzRepository<MwCsYz> repository,
			MwCsYz lastYZ) throws Exception {
		return calCSYZ(page, result, repository, lastYZ, MwNums.FDS, MwCsYz.class, MwYz.class, new CSHandler<MwCsYz>() {

			@Override
			public void doExtra(MwCsYz yz, String fd) {
				yz.setCurrentPos(fd.toLowerCase());
			}

		});
	}

	private static interface CSHandler<C> {
		void doExtra(C yz, String fd);
	}

	public <T extends BaseYz, C extends BaseCsYz> Map<String, Object> calCSYZ(PageInfo page, PageResult<T> result,
			BaseYzRepository<C> repository, C lastYZ, String[] fds, Class<C> clazz, Class<T> yzClazz, CSHandler<C> handler)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<C> pResult = null;
		if (result != null && result.getTotal() > 0) {
			List<C> list = new ArrayList<C>();
			int length = fds.length;
			if (lastYZ == null) {
				lastYZ = clazz.newInstance();
				for (String fd : fds) {
					Method m = ReflectionUtils.findMethod(clazz, "set" + fd, Integer.class);
					m.invoke(lastYZ, 0);
				}
			}
			for (T data : result.getList()) {
				C yz = null;
				if (repository != null) {
					yz = repository.findByYearAndPhase(data.getYear(), data.getPhase());
				}
				if (yz == null) {
					yz = clazz.newInstance();
					yz.setYear(data.getYear());
					yz.setPhase(data.getPhase());
				}
				yz.setDate(data.getDate());

				for (String fd : fds) {
					Method sm = ReflectionUtils.findMethod(clazz, "set" + fd, Integer.class);
					Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
					sm.invoke(yz, (Integer) gm.invoke(lastYZ));
				}

				Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
				int currentValue = 0;
				for (String fd : fds) {
					Method gm = ReflectionUtils.findMethod(yzClazz, "get" + fd);
					Integer value = (Integer) gm.invoke(data);
					gm = ReflectionUtils.findMethod(clazz, "get" + fd);
					Method sm = ReflectionUtils.findMethod(clazz, "set" + fd, Integer.class);
					if (value != null && value == 0) {
						currentValue = 1 + (Integer) gm.invoke(yz);
						sm.invoke(yz, currentValue);
						if (handler != null) {
							handler.doExtra(yz, fd);
						}
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
				for (String fd : fds) {
					Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
					Integer value = (Integer) gm.invoke(yz);
					if (value != null) {
						total += value;
					}
				}
				yz.setTotal(total);
				yz.setAvg(new BigDecimal(1d * total / length).setScale(2, RoundingMode.HALF_UP));

				int large = 0;
				int small = 0;
				for (String fd : fds) {
					Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
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

				if (repository != null) {
					repository.save(yz);
				} else {
					yz.setId(1l);
				}
				list.add(yz);
				lastYZ = yz;

				if (page != null) {
					pResult = new PageResult<C>(list, list.size(), page);
					map.put("result", pResult);
				}
				map.put("lastYz", lastYZ);
			}
		}

		return map;
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
		return calLRYZ(SxLrYz.class, repositories.sxlryzRepository, repositories.sxyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calSXLRYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calTwelveLRYZ() {
		return calLRYZ(TwelveLrYz.class, repositories.twelvelryzRepository, repositories.twelveyzRepository,
				new CommonHandler() {

					@Override
					public void process() {
						logger.info("End of calTwelveLRYZ...");
					}
				});

	}

	@Async
	public Future<Exception> calSLQLRYZ() {
		return calLRYZ(SlqLrYz.class, repositories.slqlryzRepository, repositories.slqyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calSLQLRYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calPDLRYZ() {
		return calLRYZ(PdLrYz.class, repositories.pdlryzRepository, repositories.pdyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calPDLRYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calTM12FDLRYZ() {
		return calLRYZ(Tm12FdLrYz.class, repositories.tm12fdlryzRepository, repositories.tm12fdyzRepository,
				new CommonHandler() {

					@Override
					public void process() {
						logger.info("End of calTM12FDLRYZ...");
					}
				});

	}

	@Async
	public Future<Exception> calMWLRYZ() {
		return calLRYZ(MwLrYz.class, repositories.mwlryzRepository, repositories.mwyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calMWLRYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calDSLRYZ() {
		return calLRYZ(DsLrYz.class, repositories.dslrYzRepository, repositories.dsyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calDSLRYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calLHLRYZ() {
		return calLRYZ(LhLrYz.class, repositories.lhlryzRepository, repositories.lhyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calLHLRYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZSLRYZ() {
		return calLRYZ(ZsLrYz.class, repositories.zslryzRepository, repositories.zsyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZSLRYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calBS9QLRYZ() {
		return calLRYZ(Bs9qLrYz.class, repositories.bs9qlryzRepository, repositories.bs9qyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calBS9QLRYZ...");
			}
		});

	}

	@Async
	public Future<Exception> calWXDSLRYZ() {
		return calLRYZ(WxdsLrYz.class, repositories.wxdslryzRepository, repositories.wxdsyzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calWXDSLRYZ...");
			}
		});

	}

	public PageResult<D1Yz> getD1List(QueryInfo<SxYz> queryInfo) throws Exception {
		PageResult<D1Yz> result = calSxForD1(queryInfo);
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(calSxZfForD1(queryInfo, result));
		futures.add(calDsForD1(queryInfo, result));
		futures.add(calDsZfForD1(queryInfo, result));
		futures.add(calSwForD1(queryInfo, result));
		futures.add(calSwZfForD1(queryInfo, result));
		futures.add(calMwForD1(queryInfo, result));
		futures.add(calMwZfForD1(queryInfo, result));
		futures.add(calLhForD1(queryInfo, result));
		futures.add(calLhZfForD1(queryInfo, result));
		futures.add(calBsForD1(queryInfo, result));
		futures.add(calBsZfForD1(queryInfo, result));
		futures.add(calZsForD1(queryInfo, result));
		futures.add(calZsZfForD1(queryInfo, result));
		futures.add(calWxForD1(queryInfo, result));
		futures.add(calWxZfForD1(queryInfo, result));
		futures.add(calWxdsForD1(queryInfo, result));
		futures.add(calWxdsZfForD1(queryInfo, result));
		futures.add(calPdForD1(queryInfo, result));
		futures.add(calPdZfForD1(queryInfo, result));
		futures.add(calFdForD1(queryInfo, result));
		futures.add(calFdZfForD1(queryInfo, result));
		futures.add(calQqForD1(queryInfo, result));
		futures.add(calQqZfForD1(queryInfo, result));
		futures.add(calQiwForD1(queryInfo, result));
		futures.add(calQiwZfForD1(queryInfo, result));
		futures.add(calTwelveForD1(queryInfo, result));
		futures.add(calTwelveZfForD1(queryInfo, result));
		futures.add(calSlqForD1(queryInfo, result));
		futures.add(calSlqZfForD1(queryInfo, result));
		sleep(futures, 100);
		return result;
	}

	private PageResult<D1Yz> calSxForD1(QueryInfo<SxYz> queryInfo) throws Exception {
		PageResult<SxYz> dataResult = repositories.sxYzDao.query(queryInfo);
		PageResult<D1Yz> result = new PageResult<D1Yz>();
		if (dataResult != null && dataResult.getTotal() > 0) {
			List<D1Yz> list = new ArrayList<D1Yz>();
			Integer lastMax = null;
			for (SxYz data : dataResult.getList()) {
				D1Yz yz = new D1Yz();
				yz.setDate(data.getDate());
				yz.setYear(data.getYear());
				yz.setPhase(data.getPhase());
				int max = 0;
				for (SX sx : SX.seq()) {
					Method m = ReflectionUtils.findMethod(SxYz.class, "get" + sx.name());
					Integer value = (Integer) m.invoke(data);
					if (value != null && value > max) {
						max = value;
					}
				}
				if (lastMax != null && lastMax >= max) {
					yz.setRedForsxd1(true);
				}
				lastMax = max;
				yz.setSxd1(max);
				list.add(yz);
			}
			result = new PageResult<D1Yz>(list, dataResult.getTotal(), queryInfo.getPageInfo());
		} else {
			result.setList(new ArrayList<D1Yz>());
		}
		return result;
	}

	@Async
	public Future<Exception> calSxZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<SxZfYz2> dataQueryInfo = new QueryInfo<SxZfYz2>();
			SxZfYz2 condition = new SxZfYz2();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<SxZfYz2> dataResult = repositories.sxZfYz2Dao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (SxZfYz2 data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < SX.seq().length; j++) {
						Method m = ReflectionUtils.findMethod(SxZfYz2.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForsxzfd1(true);
					}
					lastMax = max;
					yz.setSxzfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calDsForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<DsYz> dataQueryInfo = new QueryInfo<DsYz>();
			DsYz condition = new DsYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<DsYz> dataResult = repositories.dsYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (DsYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : DsNums.FDS) {
						Method m = ReflectionUtils.findMethod(DsYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedFordsd1(true);
					}
					lastMax = max;
					yz.setDsd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calDsZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<DsZfYz> dataQueryInfo = new QueryInfo<DsZfYz>();
			DsZfYz condition = new DsZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<DsZfYz> dataResult = repositories.dszfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (DsZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < DsNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(DsZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedFordszfd1(true);
					}
					lastMax = max;
					yz.setDszfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calSwForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<SwYz> dataQueryInfo = new QueryInfo<SwYz>();
			SwYz condition = new SwYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<SwYz> dataResult = repositories.swYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (SwYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : SwNums.FDS) {
						Method m = ReflectionUtils.findMethod(SwYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForswd1(true);
					}
					lastMax = max;
					yz.setSwd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calSwZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<SwZfYz> dataQueryInfo = new QueryInfo<SwZfYz>();
			SwZfYz condition = new SwZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<SwZfYz> dataResult = repositories.swZfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (SwZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < SwNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(SwZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForswzfd1(true);
					}
					lastMax = max;
					yz.setSwzfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calMwForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<MwYz> dataQueryInfo = new QueryInfo<MwYz>();
			MwYz condition = new MwYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<MwYz> dataResult = repositories.mwYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (MwYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : MwNums.FDS) {
						Method m = ReflectionUtils.findMethod(MwYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedFormwd1(true);
					}
					lastMax = max;
					yz.setMwd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calMwZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<MwZfYz> dataQueryInfo = new QueryInfo<MwZfYz>();
			MwZfYz condition = new MwZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<MwZfYz> dataResult = repositories.mwZfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (MwZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < MwNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(MwZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedFormwzfd1(true);
					}
					lastMax = max;
					yz.setMwzfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calLhForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<LhYz> dataQueryInfo = new QueryInfo<LhYz>();
			LhYz condition = new LhYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<LhYz> dataResult = repositories.lhYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (LhYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : LhNums.FDS) {
						Method m = ReflectionUtils.findMethod(LhYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForlhd1(true);
					}
					lastMax = max;
					yz.setLhd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calLhZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<LhZfYz> dataQueryInfo = new QueryInfo<LhZfYz>();
			LhZfYz condition = new LhZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<LhZfYz> dataResult = repositories.lhZfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (LhZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < LhNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(LhZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForlhzfd1(true);
					}
					lastMax = max;
					yz.setLhzfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calBsForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<Bs9qYz> dataQueryInfo = new QueryInfo<Bs9qYz>();
			Bs9qYz condition = new Bs9qYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<Bs9qYz> dataResult = repositories.bs9qYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (Bs9qYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : Bs9qNums.FDS) {
						Method m = ReflectionUtils.findMethod(Bs9qYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForbsd1(true);
					}
					lastMax = max;
					yz.setBsd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calBsZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<Bs9qZfYz> dataQueryInfo = new QueryInfo<Bs9qZfYz>();
			Bs9qZfYz condition = new Bs9qZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<Bs9qZfYz> dataResult = repositories.bs9qZfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (Bs9qZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < Bs9qNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(Bs9qZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForbszfd1(true);
					}
					lastMax = max;
					yz.setBszfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calZsForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<ZsYz> dataQueryInfo = new QueryInfo<ZsYz>();
			ZsYz condition = new ZsYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<ZsYz> dataResult = repositories.zsYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (ZsYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : ZsNums.FDS) {
						Method m = ReflectionUtils.findMethod(ZsYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForzsd1(true);
					}
					lastMax = max;
					yz.setZsd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calZsZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<ZsZfYz> dataQueryInfo = new QueryInfo<ZsZfYz>();
			ZsZfYz condition = new ZsZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<ZsZfYz> dataResult = repositories.zszfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (ZsZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < ZsNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(ZsZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForzszfd1(true);
					}
					lastMax = max;
					yz.setZszfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calWxForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<WxYz> dataQueryInfo = new QueryInfo<WxYz>();
			WxYz condition = new WxYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<WxYz> dataResult = repositories.wxYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (WxYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : WxNums.FDS) {
						Method m = ReflectionUtils.findMethod(WxYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForwxd1(true);
					}
					lastMax = max;
					yz.setWxd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calWxZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<WxZfYz> dataQueryInfo = new QueryInfo<WxZfYz>();
			WxZfYz condition = new WxZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<WxZfYz> dataResult = repositories.wxzfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (WxZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < WxNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(WxZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForwxzfd1(true);
					}
					lastMax = max;
					yz.setWxzfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calWxdsForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<WxdsYz> dataQueryInfo = new QueryInfo<WxdsYz>();
			WxdsYz condition = new WxdsYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<WxdsYz> dataResult = repositories.wxdsYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (WxdsYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : WxDsNums.FDS) {
						Method m = ReflectionUtils.findMethod(WxdsYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForwxdsd1(true);
					}
					lastMax = max;
					yz.setWxdsd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calWxdsZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<WxdsZfYz> dataQueryInfo = new QueryInfo<WxdsZfYz>();
			WxdsZfYz condition = new WxdsZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<WxdsZfYz> dataResult = repositories.wxdsZfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (WxdsZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < WxDsNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(WxdsZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForwxdszfd1(true);
					}
					lastMax = max;
					yz.setWxdszfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calPdForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<PdYz> dataQueryInfo = new QueryInfo<PdYz>();
			PdYz condition = new PdYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<PdYz> dataResult = repositories.pdYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (PdYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : PdNums.FDS) {
						Method m = ReflectionUtils.findMethod(PdYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForpdd1(true);
					}
					lastMax = max;
					yz.setPdd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calPdZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<PdZfYz> dataQueryInfo = new QueryInfo<PdZfYz>();
			PdZfYz condition = new PdZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<PdZfYz> dataResult = repositories.pdzfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (PdZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < PdNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(PdZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForpdzfd1(true);
					}
					lastMax = max;
					yz.setPdzfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calFdForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<Tm12FdYz> dataQueryInfo = new QueryInfo<Tm12FdYz>();
			Tm12FdYz condition = new Tm12FdYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<Tm12FdYz> dataResult = repositories.tm12fdYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (Tm12FdYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 1; j < 13; j++) {
						Method m = ReflectionUtils.findMethod(Tm12FdYz.class, "getW" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForfdd1(true);
					}
					lastMax = max;
					yz.setFdd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calFdZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<Tm12FdZfYz> dataQueryInfo = new QueryInfo<Tm12FdZfYz>();
			Tm12FdZfYz condition = new Tm12FdZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<Tm12FdZfYz> dataResult = repositories.tm12fdzfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (Tm12FdZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < 12; j++) {
						Method m = ReflectionUtils.findMethod(Tm12FdZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForfdzfd1(true);
					}
					lastMax = max;
					yz.setFdzfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calQqForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<QqYz> dataQueryInfo = new QueryInfo<QqYz>();
			QqYz condition = new QqYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<QqYz> dataResult = repositories.qqYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (QqYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : QqNums.FDS) {
						Method m = ReflectionUtils.findMethod(QqYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForqqd1(true);
					}
					lastMax = max;
					yz.setQqd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calQqZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<QqZfYz> dataQueryInfo = new QueryInfo<QqZfYz>();
			QqZfYz condition = new QqZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<QqZfYz> dataResult = repositories.qqZfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (QqZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < QqNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(QqZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForqqzfd1(true);
					}
					lastMax = max;
					yz.setQqzfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calQiwForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<QiwYz> dataQueryInfo = new QueryInfo<QiwYz>();
			QiwYz condition = new QiwYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<QiwYz> dataResult = repositories.qiwYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (QiwYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : QiwNums.FDS) {
						Method m = ReflectionUtils.findMethod(QiwYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForqiwd1(true);
					}
					lastMax = max;
					yz.setQiwd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calQiwZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<QiwZfYz> dataQueryInfo = new QueryInfo<QiwZfYz>();
			QiwZfYz condition = new QiwZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<QiwZfYz> dataResult = repositories.qiwzfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (QiwZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < QiwNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(QiwZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForqiwzfd1(true);
					}
					lastMax = max;
					yz.setQiwzfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calTwelveForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<TwelveYz> dataQueryInfo = new QueryInfo<TwelveYz>();
			TwelveYz condition = new TwelveYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<TwelveYz> dataResult = repositories.twelveYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (TwelveYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : TwelveNums.FDS) {
						Method m = ReflectionUtils.findMethod(TwelveYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedFortwelved1(true);
					}
					lastMax = max;
					yz.setTwelved1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calTwelveZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<TwelveZfYz> dataQueryInfo = new QueryInfo<TwelveZfYz>();
			TwelveZfYz condition = new TwelveZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<TwelveZfYz> dataResult = repositories.twelvezfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (TwelveZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < TwelveNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(TwelveZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedFortwelvezfd1(true);
					}
					lastMax = max;
					yz.setTwelvezfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calSlqForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<SlqYz> dataQueryInfo = new QueryInfo<SlqYz>();
			SlqYz condition = new SlqYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<SlqYz> dataResult = repositories.slqYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (SlqYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (String fd : SlqNums.FDS) {
						Method m = ReflectionUtils.findMethod(SlqYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForslqd1(true);
					}
					lastMax = max;
					yz.setSlqd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calSlqZfForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<SlqZfYz> dataQueryInfo = new QueryInfo<SlqZfYz>();
			SlqZfYz condition = new SlqZfYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<SlqZfYz> dataResult = repositories.slqzfYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				Integer lastMax = null;
				for (SlqZfYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					int max = 0;
					for (int j = 0; j < SlqNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(SlqZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null && value > max) {
							max = value;
						}
					}
					if (lastMax != null && lastMax >= max) {
						yz.setRedForslqzfd1(true);
					}
					lastMax = max;
					yz.setSlqzfd1(max);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	public PageResult<J0Yz> getJ0List(QueryInfo<J0Yz> queryInfo) throws Exception {
		PageResult<J0Yz> result = repositories.commonDao.getTop0List(queryInfo);
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		for (J0Yz yz : result.getList()) {
			futures.add(repositories.yzService.calForJ0(yz));
		}
		sleep(futures, 100);
		return result;
	}

	public void sleep(List<Future<Exception>> futures, long time) throws Exception {
		while (true) {
			int count = 0;
			for (Future<Exception> f : futures) {
				if (f.isDone()) {
					if (f.get() != null) {
						throw f.get();
					}
					count++;
				}
			}
			if (count == futures.size()) {
				break;
			}
			if (time > 0) {
				Thread.sleep(time);
			}
		}
	}

	@Async
	public Future<Exception> calForJ0(J0Yz yz) {
		try {
			calSxForJ0(yz);
			calDsForJ0(yz);
			calSwForJ0(yz);
			calMwForJ0(yz);
			calLhForJ0(yz);
			calBsForJ0(yz);
			calZsForJ0(yz);
			calWxForJ0(yz);
			calWxdsForJ0(yz);
			calPdForJ0(yz);
			calFdForJ0(yz);
			calQqForJ0(yz);
			calQiwForJ0(yz);
			calTwelveForJ0(yz);
			calSlqForJ0(yz);
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	private void calSxForJ0(J0Yz yz) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		SxYz sxYz = repositories.sxyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		c.setTime(sdf.parse(sxYz.getDate()));
		SX bmnSX = DateUtil.getYear(c.get(Calendar.YEAR));
		yz.setSxNums(getSxNums(bmnSX, sxYz.getCurrentSx()));

		SxZfYz2 sxzfYz = repositories.sxzfyz2Repository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = sxYz.getCurrentSx().getPos() + sxzfYz.getCurrentPos();
		if (pos >= SX.values().length) {
			pos = pos - SX.values().length;
		}
		SX nextSX = SX.seq()[pos];
		yz.setSxzfNums(getSxNums(bmnSX, nextSX));
	}

	private List<Integer> getSxNums(SX bmnSX, SX sx) {
		int delta = sx.getPos() - bmnSX.getPos();
		if (delta < 0) {
			delta = 12 + delta;
		}
		return SxNums.NUMS[delta];
	}

	private void calDsForJ0(J0Yz yz) throws Exception {
		DsYz dsYz = repositories.dsyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		for (int i = 0; i < 5; i++) {
			Method m = DsYz.class.getDeclaredMethod("getDs" + i + "Odd");
			if ((Integer) m.invoke(dsYz) == 0) {
				Field f = DsNums.class.getDeclaredField("DS" + i + "ODD");
				yz.setDsNums((List<Integer>) f.get(null));
				break;
			}
			currentPos++;
			m = DsYz.class.getDeclaredMethod("getDs" + i + "Even");
			if ((Integer) m.invoke(dsYz) == 0) {
				Field f = DsNums.class.getDeclaredField("DS" + i + "EVEN");
				yz.setDsNums((List<Integer>) f.get(null));
				break;
			}
			currentPos++;
		}

		DsZfYz dszfYz = repositories.dszfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + dszfYz.getCurrentPos();
		if (pos >= DsNums.FDS.length) {
			pos = pos - DsNums.FDS.length;
		}
		yz.setDszfNums(DsNums.NUMS[pos]);
	}

	private void calSwForJ0(J0Yz yz) throws Exception {
		SwYz swYz = repositories.swyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		for (int i = 0; i < 5; i++) {
			currentPos = i;
			Method m = SwYz.class.getDeclaredMethod("getW" + i);
			if ((Integer) m.invoke(swYz) == 0) {
				Field f = SwNums.class.getDeclaredField("W" + i);
				yz.setSwNums((List<Integer>) f.get(null));
				break;
			}
		}

		SwZfYz swzfYz = repositories.swzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + swzfYz.getCurrentPos();
		if (pos >= SwNums.FDS.length) {
			pos = pos - SwNums.FDS.length;
		}
		yz.setSwzfNums(SwNums.NUMS[pos]);
	}

	private void calMwForJ0(J0Yz yz) throws Exception {
		MwYz mwYz = repositories.mwyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		for (int i = 0; i < 10; i++) {
			currentPos = i;
			Method m = MwYz.class.getDeclaredMethod("getW" + i);
			if ((Integer) m.invoke(mwYz) == 0) {
				Field f = MwNums.class.getDeclaredField("W" + i);
				yz.setMwNums((List<Integer>) f.get(null));
				break;
			}
		}

		MwZfYz mwzfYz = repositories.mwzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + mwzfYz.getCurrentPos();
		if (pos >= MwNums.FDS.length) {
			pos = pos - MwNums.FDS.length;
		}
		yz.setMwzfNums(MwNums.NUMS[pos]);
	}

	private void calLhForJ0(J0Yz yz) throws Exception {
		LhYz lhYz = repositories.lhyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		for (int i = 0; i < 10; i++) {
			currentPos = i;
			Method m = LhYz.class.getDeclaredMethod("getW" + i);
			if ((Integer) m.invoke(lhYz) == 0) {
				Field f = LhNums.class.getDeclaredField("W" + i);
				yz.setLhNums((List<Integer>) f.get(null));
				break;
			}
		}

		LhZfYz lhzfYz = repositories.lhzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + lhzfYz.getCurrentPos();
		if (pos >= LhNums.FDS.length) {
			pos = pos - LhNums.FDS.length;
		}
		yz.setLhzfNums(LhNums.NUMS[pos]);
	}

	private void calBsForJ0(J0Yz yz) throws Exception {
		Bs9qYz bsYz = repositories.bs9qyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		String[] colors = new String[] { "Red", "Blue", "Green" };
		int currentPos = 0;
		for (String color : colors) {
			for (int i = 1; i < 4; i++) {
				Method m = Bs9qYz.class.getDeclaredMethod("get" + color + i);
				if ((Integer) m.invoke(bsYz) == 0) {
					Field f = Bs9qNums.class.getDeclaredField(color.toUpperCase() + i);
					yz.setBsNums((List<Integer>) f.get(null));
					break;
				}
				currentPos++;
			}
		}

		Bs9qZfYz bszfYz = repositories.bs9qzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + bszfYz.getCurrentPos();
		if (pos >= Bs9qNums.FDS.length) {
			pos = pos - Bs9qNums.FDS.length;
		}
		yz.setBszfNums(Bs9qNums.NUMS[pos]);
	}

	private void calZsForJ0(J0Yz yz) throws Exception {
		ZsYz zsYz = repositories.zsyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		for (int i = 1; i < 10; i++) {
			currentPos = i - 1;
			Method m = ZsYz.class.getDeclaredMethod("getFd" + i);
			if ((Integer) m.invoke(zsYz) == 0) {
				Field f = ZsNums.class.getDeclaredField("FD" + i);
				yz.setZsNums((List<Integer>) f.get(null));
				break;
			}
		}

		ZsZfYz zszfYz = repositories.zszfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zszfYz.getCurrentPos();
		if (pos >= ZsNums.FDS.length) {
			pos = pos - ZsNums.FDS.length;
		}
		yz.setZszfNums(ZsNums.NUMS[pos]);
	}

	private void calWxForJ0(J0Yz yz) throws Exception {
		WxYz wxYz = repositories.wxyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		String[] arr = new String[] { "Jin", "Mu", "Shui", "Huo", "Tu" };
		for (int i = 0; i < arr.length; i++) {
			currentPos = i;
			Method m = WxYz.class.getDeclaredMethod("get" + arr[i]);
			if ((Integer) m.invoke(wxYz) == 0) {
				Field f = WxNums.class.getDeclaredField(arr[i].toUpperCase());
				yz.setWxNums((List<Integer>) f.get(null));
				break;
			}
		}

		WxZfYz wxzfYz = repositories.wxzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + wxzfYz.getCurrentPos();
		if (pos >= WxNums.FDS.length) {
			pos = pos - WxNums.FDS.length;
		}
		yz.setWxzfNums(WxNums.NUMS[pos]);
	}

	private void calWxdsForJ0(J0Yz yz) throws Exception {
		WxdsYz wxdsYz = repositories.wxdsyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		String[] arr = new String[] { "JinOdd", "JinEven", "MuOdd", "MuEven", "ShuiOdd", "ShuiEven", "HuoOdd", "HuoEven",
				"TuOdd", "TuEven" };
		for (int i = 0; i < arr.length; i++) {
			currentPos = i;
			Method m = WxdsYz.class.getDeclaredMethod("get" + arr[i]);
			if ((Integer) m.invoke(wxdsYz) == 0) {
				Field f = WxDsNums.class.getDeclaredField(arr[i].toUpperCase());
				yz.setWxdsNums((List<Integer>) f.get(null));
				break;
			}
		}

		WxdsZfYz wxdszfYz = repositories.wxdszfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + wxdszfYz.getCurrentPos();
		if (pos >= WxDsNums.FDS.length) {
			pos = pos - WxDsNums.FDS.length;
		}
		yz.setWxdszfNums(WxDsNums.NUMS[pos]);
	}

	private void calPdForJ0(J0Yz yz) throws Exception {
		PdYz yzData = repositories.pdyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		for (int i = 1; i < 13; i++) {
			currentPos = i - 1;
			Method m = PdYz.class.getDeclaredMethod("getW" + i);
			if ((Integer) m.invoke(yzData) == 0) {
				Field f = PdNums.class.getDeclaredField("W" + i);
				yz.setPdNums((List<Integer>) f.get(null));
				break;
			}
		}

		PdZfYz zfData = repositories.pdzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= PdNums.FDS.length) {
			pos = pos - PdNums.FDS.length;
		}
		yz.setPdzfNums(PdNums.NUMS[pos]);
	}

	private void calFdForJ0(J0Yz yz) throws Exception {
		TmYz tmData = repositories.tmyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		List<TmYzInfo> infos = getTMFDList(tmData, true);
		int currentPos = 1;
		for (TmYzInfo info : infos) {
			if (info.isTm()) {
				break;
			}
			currentPos++;
		}
		int range = 4;
		int length = 12;
		if (currentPos % range == 0) {
			currentPos = currentPos / range;
		} else {
			currentPos = currentPos / range + 1;
		}
		int maxLength = 4;
		if (currentPos >= length) {
			currentPos = length;
			maxLength = 5;
		}
		currentPos = currentPos - 1;
		int startPos = currentPos * range;
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = startPos; i < startPos + maxLength; i++) {
			nums.add(infos.get(i).getNum());
		}
		yz.setFdNums(nums);

		Tm12FdZfYz zfData = repositories.tm12fdzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= 12) {
			pos = pos - 12;
		}
		maxLength = 4;
		if (pos == 11) {
			maxLength = 5;
		}
		startPos = pos * range;
		nums = new ArrayList<Integer>();
		for (int i = startPos; i < startPos + maxLength; i++) {
			nums.add(infos.get(i).getNum());
		}
		yz.setFdzfNums(nums);
	}

	private void calQqForJ0(J0Yz yz) throws Exception {
		QqYz yzData = repositories.qqyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		for (int i = 1; i < 8; i++) {
			currentPos = i - 1;
			Method m = QqYz.class.getDeclaredMethod("getW" + i);
			if ((Integer) m.invoke(yzData) == 0) {
				Field f = QqNums.class.getDeclaredField("W" + i);
				yz.setQqNums((List<Integer>) f.get(null));
				break;
			}
		}

		QqZfYz zfData = repositories.qqzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= QqNums.FDS.length) {
			pos = pos - QqNums.FDS.length;
		}
		yz.setQqzfNums(QqNums.NUMS[pos]);
	}

	private void calQiwForJ0(J0Yz yz) throws Exception {
		QiwYz yzData = repositories.qiwYzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		for (int i = 1; i < 8; i++) {
			currentPos = i - 1;
			Method m = QiwYz.class.getDeclaredMethod("getW" + i);
			if ((Integer) m.invoke(yzData) == 0) {
				Field f = QiwNums.class.getDeclaredField("W" + i);
				yz.setQiwNums((List<Integer>) f.get(null));
				break;
			}
		}

		QiwZfYz zfData = repositories.qiwzfYzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= QiwNums.FDS.length) {
			pos = pos - QiwNums.FDS.length;
		}
		yz.setQiwzfNums(QiwNums.NUMS[pos]);
	}

	private void calTwelveForJ0(J0Yz yz) throws Exception {
		TwelveYz yzData = repositories.twelveyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		for (int i = 1; i < 13; i++) {
			currentPos = i - 1;
			Method m = TwelveYz.class.getDeclaredMethod("getW" + i);
			if ((Integer) m.invoke(yzData) == 0) {
				Field f = TwelveNums.class.getDeclaredField("W" + i);
				yz.setTwelveNums((List<Integer>) f.get(null));
				break;
			}
		}

		TwelveZfYz zfData = repositories.twelvezfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= TwelveNums.FDS.length) {
			pos = pos - TwelveNums.FDS.length;
		}
		yz.setTwelvezfNums(TwelveNums.NUMS[pos]);
	}

	private void calSlqForJ0(J0Yz yz) throws Exception {
		SlqYz yzData = repositories.slqyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int currentPos = 0;
		for (int i = 1; i < 17; i++) {
			currentPos = i - 1;
			Method m = SlqYz.class.getDeclaredMethod("getW" + i);
			if ((Integer) m.invoke(yzData) == 0) {
				Field f = SlqNums.class.getDeclaredField("W" + i);
				yz.setSlqNums((List<Integer>) f.get(null));
				break;
			}
		}

		SlqZfYz zfData = repositories.slqzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= SlqNums.FDS.length) {
			pos = pos - SlqNums.FDS.length;
		}
		yz.setSlqzfNums(SlqNums.NUMS[pos]);
	}

	@Async
	public Future<Exception> calSXCSYZForMax(QueryInfo<SxYz> queryInfo, SxCsYz data) {
		try {
			Integer max = 0;
			PageResult<SxYz> subSxResult = repositories.sxYzDao.query(queryInfo);
			Map<String, Object> subMap = calSXCSYZ(queryInfo.getPageInfo(), subSxResult, null, null);
			PageResult<SxCsYz> subResult = (PageResult<SxCsYz>) subMap.get("result");
			if (subResult != null && subResult.getTotal() > 0) {
				SX[] sxlist = SX.seq();
				for (SxCsYz currentData : subResult.getList()) {
					for (SX sx : sxlist) {
						Method m = ReflectionUtils.findMethod(SxCsYz.class, "get" + sx.name());
						Integer value = (Integer) m.invoke(currentData);
						if (value > max) {
							max = value;
						}
					}
				}
			}
			data.setMax(max);
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}
	
}

class LrInfo {
	Integer value;
	boolean special;
}

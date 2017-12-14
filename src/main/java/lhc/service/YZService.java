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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import lhc.constants.Zx10Nums;
import lhc.constants.Zx1Nums;
import lhc.constants.Zx2Nums;
import lhc.constants.Zx3Nums;
import lhc.constants.Zx4Nums;
import lhc.constants.Zx5Nums;
import lhc.constants.Zx6Nums;
import lhc.constants.Zx7Nums;
import lhc.constants.Zx8Nums;
import lhc.constants.Zx9Nums;
import lhc.domain.Avg;
import lhc.domain.BaseCsYz;
import lhc.domain.BaseDsYz;
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
import lhc.domain.LhDsYz;
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
import lhc.domain.ZfAvg;
import lhc.domain.ZsLrYz;
import lhc.domain.ZsYz;
import lhc.domain.ZsZfYz;
import lhc.domain.Zx10Yz;
import lhc.domain.Zx1Yz;
import lhc.domain.Zx2Yz;
import lhc.domain.Zx3Yz;
import lhc.domain.Zx4Yz;
import lhc.domain.Zx5Yz;
import lhc.domain.Zx6Yz;
import lhc.domain.Zx7Yz;
import lhc.domain.Zx8Yz;
import lhc.domain.Zx9Yz;
import lhc.dto.D1Yz;
import lhc.dto.J0Yz;
import lhc.dto.TmYzInfo;
import lhc.dto.XbwJY;
import lhc.dto.XbwJY2;
import lhc.dto.XbwJY2Sub;
import lhc.dto.XbwJYCondition;
import lhc.dto.XbwJYSub;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.enums.Color;
import lhc.enums.SX;
import lhc.repository.jpa.BaseYzRepository;
import lhc.repository.jpa.Repositories;
import lhc.util.DateUtil;

//@Service
@Transactional
@SuppressWarnings("unchecked")
public class YZService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected Repositories repositories;

	@Async
	public Future<Exception> calSX() {
		return calYZ(SxYz.class, repositories.sxyzRepository, repositories.kaiJiangRepository,
				new YzHandler<SxYz, KaiJiang>() {

					@Override
					public List<Integer> process(SxYz yz, SxYz lastYZ, KaiJiang data, KaiJiang lastData) throws Exception {
						Method method = ReflectionUtils.findMethod(SxYz.class, "set" + data.getSpecialSx().name(), Integer.class);
						method.invoke(yz, 0);
						yz.setCurrentSx(data.getSpecialSx());

						QueryInfo<KaiJiang> queryInfo = new QueryInfo<KaiJiang>();
						queryInfo.setObject(data);
						queryInfo.setPageInfo(new PageInfo(1, 10));
						PageResult<KaiJiang> prevDatas = repositories.kaiJiangDao.queryForPM(queryInfo);
						if (prevDatas != null) {
							int[] counts = new int[12];
							for (KaiJiang prevData : prevDatas.getList()) {
								counts[prevData.getSpecialSx().getPos() - 1]++;
							}
							yz.setNonZhCount(0);
							yz.setZhCount(0);
							for (int i = 0; i < counts.length; i++) {
								if (counts[i] == 0) {
									yz.setNonZhCount(yz.getNonZhCount() + 1);
								} else if (counts[i] - 1 > 0) {
									yz.setZhCount(yz.getZhCount() + counts[i] - 1);
								}
							}
						}

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
	public Future<Exception> calZX1YZ() {
		return calFDYZ(Zx1Yz.class, Zx1Nums.class, repositories.zx1yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX1YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX2YZ() {
		return calFDYZ(Zx2Yz.class, Zx2Nums.class, repositories.zx2yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX2YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX3YZ() {
		return calFDYZ(Zx3Yz.class, Zx3Nums.class, repositories.zx3yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX3YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX4YZ() {
		return calFDYZ(Zx4Yz.class, Zx4Nums.class, repositories.zx4yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX4YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX5YZ() {
		return calFDYZ(Zx5Yz.class, Zx5Nums.class, repositories.zx5yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX5YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX6YZ() {
		return calFDYZ(Zx6Yz.class, Zx6Nums.class, repositories.zx6yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX6YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX7YZ() {
		return calFDYZ(Zx7Yz.class, Zx7Nums.class, repositories.zx7yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX7YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX8YZ() {
		return calFDYZ(Zx8Yz.class, Zx8Nums.class, repositories.zx8yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX8YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX9YZ() {
		return calFDYZ(Zx9Yz.class, Zx9Nums.class, repositories.zx9yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX9YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX10YZ() {
		return calFDYZ(Zx10Yz.class, Zx10Nums.class, repositories.zx10yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX10YZ...");
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
		return calDSYZ(new DSHandler() {

			@Override
			public Integer getNumber(KaiJiang data) {
				return data.getSpecialNum();
			}

			@Override
			public boolean isSmall(KaiJiang data, Integer num) {
				SX sx = data.getSpecialSx();
				return sx.isSmall();
			}

			@Override
			public boolean isOdd(KaiJiang data, Integer num) {
				SX sx = data.getSpecialSx();
				return sx.isSingle();
			}
		}, SxDsYz.class, repositories.sxdsyzRepository, "calSXDSYZ");
	}

	@Async
	public Future<Exception> calLHDSYZ() {
		return calDSYZ(new DSHandler() {

			@Override
			public Integer getNumber(KaiJiang data) {
				Integer num = data.getSpecialNum();
				if (num > 9) {
					String numStr = data.getSpecialNum().toString();
					num = Integer.valueOf(numStr.substring(0, 1)) + Integer.valueOf(numStr.substring(1));
					if (num > 9) {
						num = Integer.valueOf(num.toString().substring(1));
					}
				}
				return num;
			}

			@Override
			public boolean isSmall(KaiJiang data, Integer num) {
				return num < 5;
			}

			@Override
			public boolean isOdd(KaiJiang data, Integer num) {
				return num % 2 != 0;
			}
		}, LhDsYz.class, repositories.lhdsyzRepository, "calLHDSYZ");
	}

	protected interface DSHandler {
		Integer getNumber(KaiJiang data);

		boolean isSmall(KaiJiang data, Integer num);

		boolean isOdd(KaiJiang data, Integer num);
	}

	protected <T extends BaseDsYz> Future<Exception> calDSYZ(DSHandler handler, Class<T> clazz,
			BaseYzRepository<T> repository, String msg) {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			T lastYz = null;
			String[] arr = { "Small", "Large", "Odd", "Even", "SmallOdd", "SmallEven", "LargeOdd", "LargeEven" };
			do {
				result = repositories.kaiJiangRepository.findAll(request);
				Method gm = null;
				Method sm = null;
				if (result != null && result.hasContent()) {
					for (KaiJiang data : result.getContent()) {
						T yz = repository.findByYearAndPhase(data.getYear(), data.getPhase());
						if (yz == null) {
							yz = clazz.newInstance();
							yz.setYear(data.getYear());
							yz.setPhase(data.getPhase());
						}
						yz.setDate(data.getDate());

						Integer num = handler.getNumber(data);
						boolean isSmall = handler.isSmall(data, num);
						boolean isOdd = handler.isOdd(data, num);
						if (isSmall) {
							yz.setSmall(0);
							if (lastYz != null) {
								yz.setLastDxYz(lastYz.getSmall());
							}
						} else {
							yz.setLarge(0);
							if (lastYz != null) {
								yz.setLastDxYz(lastYz.getLarge());
							}
						}
						if (isOdd) {
							yz.setOdd(0);
							if (lastYz != null) {
								yz.setLastDsYz(lastYz.getOdd());
							}
						} else {
							yz.setEven(0);
							if (lastYz != null) {
								yz.setLastDsYz(lastYz.getEven());
							}
						}
						if (isSmall) {
							if (isOdd) {
								yz.setSmallOdd(0);
								if (lastYz != null) {
									yz.setLastDxDsYz(lastYz.getSmallOdd());
								}
							} else {
								yz.setSmallEven(0);
								if (lastYz != null) {
									yz.setLastDxDsYz(lastYz.getSmallEven());
								}
							}
						} else {
							if (isOdd) {
								yz.setLargeOdd(0);
								if (lastYz != null) {
									yz.setLastDxDsYz(lastYz.getLargeOdd());
								}
							} else {
								yz.setLargeEven(0);
								if (lastYz != null) {
									yz.setLastDxDsYz(lastYz.getLargeEven());
								}
							}
						}

						if (lastYz != null) {
							for (int j = 0; j < arr.length; j++) {
								String suffix = arr[j];
								gm = ReflectionUtils.findMethod(clazz, "get" + suffix);
								Integer lastValue = (Integer) gm.invoke(lastYz);
								if (lastValue != null) {
									Integer value = (Integer) gm.invoke(yz);
									if (value == null || value > 0) {
										sm = ReflectionUtils.findMethod(clazz, "set" + suffix, Integer.class);
										sm.invoke(yz, lastValue + 1);
									}
								}
							}
						}

						repository.save(yz);
						lastYz = yz;

					}
				}
				request = result.nextPageable();
			} while (result != null && result.hasNext());
			logger.info("End of " + msg + "...");
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

	protected <T extends BaseYz> void calTMFDYZ(int range, int length, String prefix, BaseYzRepository<T> repository,
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
				if (gm != null) {
					Integer value = (Integer) gm.invoke(data);
					infos.add(new TmYzInfo(i, value));
				}
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
		return calDSYZ(new DSHandler() {

			@Override
			public Integer getNumber(KaiJiang data) {
				return data.getSpecialNum();
			}

			@Override
			public boolean isSmall(KaiJiang data, Integer num) {
				return num < 26;
			}

			@Override
			public boolean isOdd(KaiJiang data, Integer num) {
				return num % 2 != 0;
			}
		}, HmDsYz.class, repositories.hmdsyzRepository, "calHMDSYZ");
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

	protected static abstract class GetSuffixHandler<T extends Avg, R extends Avg> extends ZFPosHandler<T, R> {

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

	protected static abstract class ZFPosHandler<T extends Avg, R extends Avg> {

		abstract Integer process(T zfYz, T lastZFYZ, R data, R lastYZ, Class<R> yzClazz, Class<T> yzzfClazz, int zfLength)
				throws Exception;
	}

	protected <T extends Avg, R extends Avg> void calZF(int zfLength, Class<R> yzClazz, Class<T> yzzfClazz,
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

	protected static interface CommonHandler {
		void process();
	}

	protected static interface LrHandler {
		int getSmall();

		int getLarge();
	}

	protected <T extends PosAvg> Future<Exception> calPosFDYZ(final Class<T> clazz, final Class<?> numsClass,
			final BaseYzRepository<T> repository, final CommonHandler handler, int startPos, int endPos) {
		return calPosFDYZ(clazz, numsClass, repository, handler, startPos, endPos, null);
	}

	protected <T extends PosAvg> Future<Exception> calPosFDYZ(final Class<T> clazz, final Class<?> numsClass,
			final BaseYzRepository<T> repository, final CommonHandler handler, int startPos, int endPos,
			final LrHandler lrHandler) {
		try {
			return calYZ(clazz, repository, repositories.kaiJiangRepository,
					new PosFDYZHandler<T>(clazz, numsClass, startPos, endPos, handler, lrHandler));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	protected static class PosFDYZHandler<T extends PosAvg> extends FDYZHandler<T> {

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

	protected static class FDYZHandler<T extends Avg> implements YzHandler<T, KaiJiang> {
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

	protected <T extends Avg> Future<Exception> calFDYZ(final Class<T> clazz, final Class<?> numsClass,
			final BaseYzRepository<T> repository, final CommonHandler handler) {
		try {
			return calYZ(clazz, repository, repositories.kaiJiangRepository, new FDYZHandler<T>(clazz, numsClass, handler));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	protected <T extends Avg> Future<Exception> calFDYZ(final Class<T> clazz, final Class<?> numsClass,
			final BaseYzRepository<T> repository, final CommonHandler handler, LrHandler lrHandler) {
		try {
			return calYZ(clazz, repository, repositories.kaiJiangRepository,
					new FDYZHandler<T>(clazz, numsClass, handler, lrHandler));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	protected static interface YzHandler<T extends Avg, R extends BaseYz> {

		List<Integer> process(T yz, T lastYZ, R data, R lastData) throws Exception;

		int calTotal(T yz) throws Exception;

		void afterProcess() throws Exception;
	}

	protected <T extends Avg, R extends BaseYz> Future<Exception> calYZ(Class<T> clazz, BaseYzRepository<T> repository,
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

	protected static interface CSHandler<C> {
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

	protected <T extends LrSet, R extends BaseYz> Future<Exception> calLRYZ(final Class<T> lrClazz,
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
		futures.add(calSxlrForD1(queryInfo, result));
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

	protected PageResult<D1Yz> calSxForD1(QueryInfo<SxYz> queryInfo) throws Exception {
		PageResult<SxYz> dataResult = repositories.sxYzDao.query(queryInfo);
		PageResult<D1Yz> result = new PageResult<D1Yz>();
		if (dataResult != null && dataResult.getTotal() > 0) {
			List<D1Yz> list = new ArrayList<D1Yz>();
			Integer lastMax = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			for (SxYz data : dataResult.getList()) {
				D1Yz yz = new D1Yz();
				yz.setDate(data.getDate());
				yz.setYear(data.getYear());
				yz.setPhase(data.getPhase());
				KaiJiang kj = repositories.kaiJiangRepository.findByYearAndPhase(data.getYear(), data.getPhase());
				yz.setSpecialNum(kj.getSpecialNum());
				int max = 0;
				SX maxSX = null;
				int total = 0;
				for (SX sx : SX.seq()) {
					Method m = ReflectionUtils.findMethod(SxYz.class, "get" + sx.name());
					Integer value = (Integer) m.invoke(data);
					if (value != null) {
						total += value;
						if (value > max) {
							max = value;
							maxSX = sx;
						}
					}
				}
				yz.setSxTotal(total);
				if (maxSX != null) {
					calSxForD1(yz, maxSX);
				}
				if (lastMax != null && lastMax >= max) {
					yz.setRedForsxd1(true);
					c.setTime(sdf.parse(data.getDate()));
					SX bmnSX = DateUtil.getSxByYear(c.get(Calendar.YEAR));
					yz.setSxNums(getSxNums(bmnSX, data.getCurrentSx()));

					int pos = data.getCurrentSx().getPos()
							+ repositories.sxzfyz2Repository.findByYearAndPhase(data.getYear(), data.getPhase()).getCurrentPos();
					if (pos >= SX.values().length) {
						pos = pos - SX.values().length;
					}
					SX nextSX = SX.seq()[pos];
					yz.setSxzfNums(getSxNums(bmnSX, nextSX));
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

	protected void calSxForD1(D1Yz yz, SX maxSX) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(yz.getDate()));
		SX bmnSX = DateUtil.getSxByYear(c.get(Calendar.YEAR));
		yz.setSxNums(getSxNums(bmnSX, maxSX));
	}

	@Async
	public Future<Exception> calSxlrForD1(QueryInfo<SxYz> queryInfo, PageResult<D1Yz> result) throws Exception {
		try {
			QueryInfo<SxLrYz> dataQueryInfo = new QueryInfo<SxLrYz>();
			SxLrYz condition = new SxLrYz();
			condition.setYear(queryInfo.getObject().getYear());
			condition.setPhase(queryInfo.getObject().getPhase());
			dataQueryInfo.setObject(condition);
			dataQueryInfo.setPageInfo(queryInfo.getPageInfo());
			dataQueryInfo.setToReverse(queryInfo.isToReverse());
			PageResult<SxLrYz> dataResult = repositories.sxlrYzDao.query(dataQueryInfo);
			if (dataResult != null && dataResult.getTotal() > 0) {
				List<D1Yz> list = result.getList();
				int i = 0;
				for (SxLrYz data : dataResult.getList()) {
					D1Yz yz = list.get(i++);
					yz.setSxlrTotal(data.getTotal());
					yz.setSxlrd1(data.getTop0());
					yz.setSxlrPos(data.getPos());
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
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
					SX maxSX = null;
					int total = 0;
					for (int j = 0; j < SX.seq().length; j++) {
						Method m = ReflectionUtils.findMethod(SxZfYz2.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxSX = SX.seq()[j];
							}
						}
					}
					yz.setSxzfTotal(total);
					if (maxSX != null) {
						calSxForD1(yz, maxSX, data);
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

	protected void calSxForD1(D1Yz yz, SX maxSX, SxZfYz2 sxzfYz) throws Exception {
		int pos = maxSX.getPos() + sxzfYz.getCurrentPos();
		if (pos >= SX.values().length) {
			pos = pos - SX.values().length;
		}
		SX nextSX = SX.seq()[pos];
		yz.setSxzfNums(getSxNums(maxSX, nextSX));
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : DsNums.FDS) {
						Method m = ReflectionUtils.findMethod(DsYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setDsTotal(total);
					if (maxPos != null) {
						yz.setDsNums(DsNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < DsNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(DsZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setDszfTotal(total);
					if (maxPos != null) {
						calDsForD1(yz, maxPos, data);
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

	protected void calDsForD1(D1Yz yz, Integer currentPos, DsZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= DsNums.FDS.length) {
			pos = pos - DsNums.FDS.length;
		}
		yz.setDszfNums(DsNums.NUMS[pos]);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : SwNums.FDS) {
						Method m = ReflectionUtils.findMethod(SwYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setSwTotal(total);
					yz.setSwPos(data.getPos());
					if (maxPos != null) {
						yz.setSwNums(SwNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < SwNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(SwZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setSwzfTotal(total);
					if (maxPos != null) {
						calSwForD1(yz, maxPos, data);
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

	protected void calSwForD1(D1Yz yz, Integer currentPos, SwZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= SwNums.FDS.length) {
			pos = pos - SwNums.FDS.length;
		}
		yz.setSwzfNums(SwNums.NUMS[pos]);
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
					int j = 0;
					int total = 0;
					Integer maxPos = null;
					for (String fd : MwNums.FDS) {
						Method m = ReflectionUtils.findMethod(MwYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setMwTotal(total);
					if (maxPos != null) {
						yz.setMwNums(MwNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < MwNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(MwZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setMwzfTotal(total);
					if (maxPos != null) {
						calMwForD1(yz, maxPos, data);
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

	protected void calMwForD1(D1Yz yz, Integer currentPos, MwZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= MwNums.FDS.length) {
			pos = pos - MwNums.FDS.length;
		}
		yz.setMwzfNums(MwNums.NUMS[pos]);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : LhNums.FDS) {
						Method m = ReflectionUtils.findMethod(LhYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setLhTotal(total);
					if (maxPos != null) {
						yz.setLhNums(LhNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < LhNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(LhZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setLhzfTotal(total);
					if (maxPos != null) {
						calLhForD1(yz, maxPos, data);
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

	protected void calLhForD1(D1Yz yz, Integer currentPos, LhZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= LhNums.FDS.length) {
			pos = pos - LhNums.FDS.length;
		}
		yz.setLhzfNums(LhNums.NUMS[pos]);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : Bs9qNums.FDS) {
						Method m = ReflectionUtils.findMethod(Bs9qYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setBsTotal(total);
					yz.setBsd3(data.getTop2());
					yz.setBsd4(data.getTop3());
					yz.setBsd5(data.getTop4());
					if (maxPos != null) {
						yz.setBsNums(Bs9qNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < Bs9qNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(Bs9qZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setBszfTotal(total);
					yz.setBszfd3(data.getTop2());
					yz.setBszfd4(data.getTop3());
					yz.setBszfd5(data.getTop4());
					if (maxPos != null) {
						calBsForD1(yz, maxPos, data);
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

	protected void calBsForD1(D1Yz yz, Integer currentPos, Bs9qZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= Bs9qNums.FDS.length) {
			pos = pos - Bs9qNums.FDS.length;
		}
		yz.setBszfNums(Bs9qNums.NUMS[pos]);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : ZsNums.FDS) {
						Method m = ReflectionUtils.findMethod(ZsYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setZsTotal(total);
					if (maxPos != null) {
						yz.setZsNums(ZsNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < ZsNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(ZsZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setZszfTotal(total);
					if (maxPos != null) {
						calZsForD1(yz, maxPos, data);
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

	protected void calZsForD1(D1Yz yz, Integer currentPos, ZsZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= ZsNums.FDS.length) {
			pos = pos - ZsNums.FDS.length;
		}
		yz.setZszfNums(ZsNums.NUMS[pos]);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : WxNums.FDS) {
						Method m = ReflectionUtils.findMethod(WxYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setWxTotal(total);
					yz.setWxd3(data.getTop2());
					yz.setWxd4(data.getTop3());
					yz.setWxd5(data.getTop4());
					if (maxPos != null) {
						yz.setWxNums(WxNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < WxNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(WxZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setWxzfTotal(total);
					yz.setWxzfd3(data.getTop2());
					yz.setWxzfd4(data.getTop3());
					yz.setWxzfd5(data.getTop4());
					if (maxPos != null) {
						calWxForD1(yz, maxPos, data);
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

	protected void calWxForD1(D1Yz yz, Integer currentPos, WxZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= WxNums.FDS.length) {
			pos = pos - WxNums.FDS.length;
		}
		yz.setWxzfNums(WxNums.NUMS[pos]);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : WxDsNums.FDS) {
						Method m = ReflectionUtils.findMethod(WxdsYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setWxdsTotal(total);
					if (maxPos != null) {
						yz.setWxdsNums(WxDsNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < WxDsNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(WxdsZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setWxdszfTotal(total);
					if (maxPos != null) {
						calWxdsForD1(yz, maxPos, data);
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

	protected void calWxdsForD1(D1Yz yz, Integer currentPos, WxdsZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= WxDsNums.FDS.length) {
			pos = pos - WxDsNums.FDS.length;
		}
		yz.setWxdszfNums(WxDsNums.NUMS[pos]);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : PdNums.FDS) {
						Method m = ReflectionUtils.findMethod(PdYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setPdTotal(total);
					if (maxPos != null) {
						yz.setPdNums(PdNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < PdNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(PdZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setPdzfTotal(total);
					if (maxPos != null) {
						calPdForD1(yz, maxPos, data);
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

	protected void calPdForD1(D1Yz yz, Integer currentPos, PdZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= PdNums.FDS.length) {
			pos = pos - PdNums.FDS.length;
		}
		yz.setPdzfNums(PdNums.NUMS[pos]);
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
					int total = 0;
					for (int j = 1; j < 13; j++) {
						Method m = ReflectionUtils.findMethod(Tm12FdYz.class, "getW" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
							}
						}
					}
					yz.setFdTotal(total);
					if (lastMax != null && lastMax >= max) {
						yz.setRedForfdd1(true);
					}
					lastMax = max;
					yz.setFdd1(max);
					calFdForD1(yz);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	protected void calFdForD1(D1Yz yz) throws Exception {
		TmYz tmData = repositories.tmyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		List<TmYzInfo> infos = getTMFDList(tmData, false);
		int currentPos = infos.size() - 1;
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
					int total = 0;
					for (int j = 0; j < 12; j++) {
						Method m = ReflectionUtils.findMethod(Tm12FdZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
							}
						}
					}
					yz.setFdzfTotal(total);
					if (lastMax != null && lastMax >= max) {
						yz.setRedForfdzfd1(true);
					}
					lastMax = max;
					yz.setFdzfd1(max);
					calFdForD1(yz, data);
				}
			}
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	protected void calFdForD1(D1Yz yz, Tm12FdZfYz zfData) throws Exception {
		TmYz tmData = repositories.tmyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		List<TmYzInfo> infos = getTMFDList(tmData, false);
		int currentPos = infos.size() - 1;
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

		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= 12) {
			pos = pos - 12;
		}
		maxLength = 4;
		if (pos == 11) {
			maxLength = 5;
		}
		int startPos = pos * range;
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = startPos; i < startPos + maxLength; i++) {
			nums.add(infos.get(i).getNum());
		}
		yz.setFdzfNums(nums);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : QqNums.FDS) {
						Method m = ReflectionUtils.findMethod(QqYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setQqTotal(total);
					yz.setQqPos(data.getPos());
					if (maxPos != null) {
						yz.setQqNums(QqNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < QqNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(QqZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setQqzfTotal(total);
					if (maxPos != null) {
						calQqForD1(yz, maxPos, data);
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

	protected void calQqForD1(D1Yz yz, Integer currentPos, QqZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= QqNums.FDS.length) {
			pos = pos - QqNums.FDS.length;
		}
		yz.setQqzfNums(QqNums.NUMS[pos]);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : QiwNums.FDS) {
						Method m = ReflectionUtils.findMethod(QiwYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setQiwTotal(total);
					yz.setQiwPos(data.getPos());
					if (maxPos != null) {
						yz.setQiwNums(QiwNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < QiwNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(QiwZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setQiwzfTotal(total);
					if (maxPos != null) {
						calQiwForD1(yz, maxPos, data);
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

	protected void calQiwForD1(D1Yz yz, Integer currentPos, QiwZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= QiwNums.FDS.length) {
			pos = pos - QiwNums.FDS.length;
		}
		yz.setQiwzfNums(QiwNums.NUMS[pos]);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : TwelveNums.FDS) {
						Method m = ReflectionUtils.findMethod(TwelveYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setTwelveTotal(total);
					yz.setTwelvePos(data.getPos());
					if (maxPos != null) {
						yz.setTwelveNums(TwelveNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < TwelveNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(TwelveZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setTwelvezfTotal(total);
					if (maxPos != null) {
						calTwelveForD1(yz, maxPos, data);
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

	protected void calTwelveForD1(D1Yz yz, Integer currentPos, TwelveZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= TwelveNums.FDS.length) {
			pos = pos - TwelveNums.FDS.length;
		}
		yz.setTwelvezfNums(TwelveNums.NUMS[pos]);
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
					Integer maxPos = null;
					int j = 0;
					int total = 0;
					for (String fd : SlqNums.FDS) {
						Method m = ReflectionUtils.findMethod(SlqYz.class, "get" + fd);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
						j++;
					}
					yz.setSlqTotal(total);
					if (maxPos != null) {
						yz.setSlqNums(SlqNums.NUMS[maxPos]);
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
					Integer maxPos = null;
					int total = 0;
					for (int j = 0; j < SlqNums.FDS.length; j++) {
						Method m = ReflectionUtils.findMethod(SlqZfYz.class, "getZf" + j);
						Integer value = (Integer) m.invoke(data);
						if (value != null) {
							total += value;
							if (value > max) {
								max = value;
								maxPos = j;
							}
						}
					}
					yz.setSlqzfTotal(total);
					if (maxPos != null) {
						calSlqForD1(yz, maxPos, data);
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

	protected void calSlqForD1(D1Yz yz, Integer currentPos, SlqZfYz data) throws Exception {
		int pos = currentPos + data.getCurrentPos();
		if (pos >= SlqNums.FDS.length) {
			pos = pos - SlqNums.FDS.length;
		}
		yz.setSlqzfNums(SlqNums.NUMS[pos]);
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

	protected void calSxForJ0(J0Yz yz) throws Exception {
		SxYz sxYz = repositories.sxyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(sxYz.getDate()));
		SX bmnSX = DateUtil.getSxByYear(c.get(Calendar.YEAR));
		yz.setSxNums(getSxNums(bmnSX, sxYz.getCurrentSx()));

		SxZfYz2 zfData = repositories.sxzfyz2Repository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = sxYz.getCurrentSx().getPos() + zfData.getCurrentPos();
		if (pos >= SX.values().length) {
			pos = pos - SX.values().length;
		}
		SX nextSX = SX.seq()[pos];
		yz.setSxzfNums(getSxNums(bmnSX, nextSX));
	}

	protected List<Integer> getSxNums(SX bmnSX, SX sx) {
		int delta = sx.getPos() - bmnSX.getPos();
		if (delta < 0) {
			delta = 12 + delta;
		}
		return SxNums.NUMS[delta];
	}

	protected void calDsForJ0(J0Yz yz) throws Exception {
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

		DsZfYz zfData = repositories.dszfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= DsNums.FDS.length) {
			pos = pos - DsNums.FDS.length;
		}
		yz.setDszfNums(DsNums.NUMS[pos]);
	}

	protected void calSwForJ0(J0Yz yz) throws Exception {
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

	protected void calMwForJ0(J0Yz yz) throws Exception {
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

		MwZfYz zfData = repositories.mwzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= MwNums.FDS.length) {
			pos = pos - MwNums.FDS.length;
		}
		yz.setMwzfNums(MwNums.NUMS[pos]);
	}

	protected void calLhForJ0(J0Yz yz) throws Exception {
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

		LhZfYz zfData = repositories.lhzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= LhNums.FDS.length) {
			pos = pos - LhNums.FDS.length;
		}
		yz.setLhzfNums(LhNums.NUMS[pos]);
	}

	protected void calBsForJ0(J0Yz yz) throws Exception {
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

		Bs9qZfYz zfData = repositories.bs9qzfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= Bs9qNums.FDS.length) {
			pos = pos - Bs9qNums.FDS.length;
		}
		yz.setBszfNums(Bs9qNums.NUMS[pos]);
	}

	protected void calZsForJ0(J0Yz yz) throws Exception {
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

		ZsZfYz zfData = repositories.zszfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= ZsNums.FDS.length) {
			pos = pos - ZsNums.FDS.length;
		}
		yz.setZszfNums(ZsNums.NUMS[pos]);
	}

	protected void calWxForJ0(J0Yz yz) throws Exception {
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

	protected void calWxdsForJ0(J0Yz yz) throws Exception {
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

		WxdsZfYz zfData = repositories.wxdszfyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
		int pos = currentPos + zfData.getCurrentPos();
		if (pos >= WxDsNums.FDS.length) {
			pos = pos - WxDsNums.FDS.length;
		}
		yz.setWxdszfNums(WxDsNums.NUMS[pos]);
	}

	protected void calPdForJ0(J0Yz yz) throws Exception {
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

	protected void calFdForJ0(J0Yz yz) throws Exception {
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

	protected void calQqForJ0(J0Yz yz) throws Exception {
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

	protected void calQiwForJ0(J0Yz yz) throws Exception {
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

	protected void calTwelveForJ0(J0Yz yz) throws Exception {
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

	protected void calSlqForJ0(J0Yz yz) throws Exception {
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

	public PageResult<XbwJY> calXBWJY(QueryInfo<XbwJYCondition> queryInfo) throws Exception {
		Method m = ReflectionUtils.findMethod(YZService.class, "calXBWJY" + queryInfo.getObject().getType(),
				QueryInfo.class);
		if (m != null) {
			return (PageResult<XbwJY>) m.invoke(this, queryInfo);
		}
		return new PageResult<XbwJY>(new ArrayList<XbwJY>(), 0, queryInfo.getPageInfo());
	}

	public PageResult<XbwJY> calXBWJY0(QueryInfo<XbwJYCondition> queryInfo) throws Exception {
		QueryInfo<J0Yz> j0QueryInfo = new QueryInfo<J0Yz>();
		J0Yz j0Condition = new J0Yz();
		j0Condition.setYear(queryInfo.getObject().getYear());
		j0Condition.setPhase(queryInfo.getObject().getPhase());
		j0QueryInfo.setObject(j0Condition);
		j0QueryInfo.setPageInfo(queryInfo.getPageInfo());
		PageResult<J0Yz> result = getJ0List(j0QueryInfo);
		List<XbwJY> list = new ArrayList<XbwJY>();
		if (result != null && result.getTotal() > 0) {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			for (J0Yz yz : result.getList()) {
				futures.add(calXbwJY0(yz, list));
			}
			sleep(futures, 10);
		}
		return new PageResult<XbwJY>(list, list.size(), queryInfo.getPageInfo());
	}

	public PageResult<XbwJY> calXBWJY1(QueryInfo<XbwJYCondition> queryInfo) throws Exception {
		QueryInfo<SxYz> d1QueryInfo = new QueryInfo<SxYz>();
		SxYz d1Condition = new SxYz();
		d1Condition.setYear(queryInfo.getObject().getYear());
		d1Condition.setPhase(queryInfo.getObject().getPhase());
		d1QueryInfo.setObject(d1Condition);
		d1QueryInfo.setPageInfo(queryInfo.getPageInfo());
		PageResult<D1Yz> d1Result = getD1List(d1QueryInfo);

		List<XbwJY> list = new ArrayList<XbwJY>();
		if (d1Result != null && d1Result.getTotal() > 0) {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			for (int i = 0; i < d1Result.getList().size(); i++) {
				futures.add(calXbwJY1(d1Result.getList().get(i), list));
			}
			sleep(futures, 10);
		}
		return new PageResult<XbwJY>(list, list.size(), d1QueryInfo.getPageInfo());
	}

	public PageResult<XbwJY> calXBWJY2(QueryInfo<XbwJYCondition> queryInfo) throws Exception {
		QueryInfo<J0Yz> j0QueryInfo = new QueryInfo<J0Yz>();
		J0Yz j0Condition = new J0Yz();
		j0Condition.setYear(queryInfo.getObject().getYear());
		j0Condition.setPhase(queryInfo.getObject().getPhase());
		j0QueryInfo.setObject(j0Condition);
		j0QueryInfo.setPageInfo(queryInfo.getPageInfo());
		PageResult<J0Yz> j0Result = getJ0List(j0QueryInfo);

		QueryInfo<SxYz> d1QueryInfo = new QueryInfo<SxYz>();
		SxYz d1Condition = new SxYz();
		d1Condition.setYear(queryInfo.getObject().getYear());
		d1Condition.setPhase(queryInfo.getObject().getPhase());
		d1QueryInfo.setObject(d1Condition);
		d1QueryInfo.setPageInfo(queryInfo.getPageInfo());
		PageResult<D1Yz> d1Result = getD1List(d1QueryInfo);

		List<XbwJY> list = new ArrayList<XbwJY>();
		if (j0Result != null && d1Result != null && j0Result.getTotal() > 0 && d1Result.getTotal() > 0) {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			for (int i = 0; i < j0Result.getList().size(); i++) {
				futures.add(calXbwJY2(j0Result.getList().get(i), d1Result.getList().get(i), list));
			}
			sleep(futures, 10);
		}
		return new PageResult<XbwJY>(list, list.size(), j0QueryInfo.getPageInfo());
	}

	public PageResult<XbwJY> calXBWJY3(QueryInfo<XbwJYCondition> queryInfo) throws Exception {
		QueryInfo<J0Yz> j0QueryInfo = new QueryInfo<J0Yz>();
		J0Yz j0Condition = new J0Yz();
		j0Condition.setYear(queryInfo.getObject().getYear());
		j0Condition.setPhase(queryInfo.getObject().getPhase());
		j0QueryInfo.setObject(j0Condition);
		j0QueryInfo.setPageInfo(queryInfo.getPageInfo());
		PageResult<J0Yz> j0Result = getJ0List(j0QueryInfo);

		QueryInfo<SxYz> d1QueryInfo = new QueryInfo<SxYz>();
		SxYz d1Condition = new SxYz();
		d1Condition.setYear(queryInfo.getObject().getYear());
		d1Condition.setPhase(queryInfo.getObject().getPhase());
		d1QueryInfo.setObject(d1Condition);
		d1QueryInfo.setPageInfo(queryInfo.getPageInfo());
		PageResult<D1Yz> d1Result = getD1List(d1QueryInfo);

		List<XbwJY> list = new ArrayList<XbwJY>();
		if (j0Result != null && d1Result != null && j0Result.getTotal() > 0 && d1Result.getTotal() > 0) {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			for (int i = 0; i < j0Result.getList().size(); i++) {
				futures.add(calXbwJY3(j0Result.getList().get(i), d1Result.getList().get(i), list));
			}
			sleep(futures, 10);
		}
		return new PageResult<XbwJY>(list, list.size(), j0QueryInfo.getPageInfo());
	}

	@Async
	public Future<Exception> calXbwJY0(J0Yz yz, List<XbwJY> list) {
		try {
			Set<Integer> A = new HashSet<Integer>();
			addNumsToConditionList(A, yz.getSxNums());
			addNumsToConditionList(A, yz.getTwelveNums());
			addNumsToConditionList(A, yz.getSlqNums());
			addNumsToConditionList(A, yz.getDsNums());

			Set<Integer> B = new HashSet<Integer>();
			addNumsToConditionList(B, yz.getLhNums());
			addNumsToConditionList(B, yz.getMwNums());
			addNumsToConditionList(B, yz.getFdNums());
			addNumsToConditionList(B, yz.getPdNums());

			Set<Integer> C = new HashSet<Integer>();
			addNumsToConditionList(C, yz.getZsNums());
			addNumsToConditionList(C, yz.getWxdsNums());
			addNumsToConditionList(C, yz.getBsNums());

			Set<Integer> D = new HashSet<Integer>();
			addNumsToConditionList(D, yz.getSwNums());
			addNumsToConditionList(D, yz.getQiwNums());

			Set<Integer> E = new HashSet<Integer>();
			addNumsToConditionList(E, yz.getSxzfNums());
			addNumsToConditionList(E, yz.getTwelvezfNums());
			addNumsToConditionList(E, yz.getSlqzfNums());
			addNumsToConditionList(E, yz.getDszfNums());

			Set<Integer> F = new HashSet<Integer>();
			addNumsToConditionList(F, yz.getLhzfNums());
			addNumsToConditionList(F, yz.getMwzfNums());
			addNumsToConditionList(F, yz.getFdzfNums());
			addNumsToConditionList(F, yz.getPdzfNums());

			Set<Integer> G = new HashSet<Integer>();
			addNumsToConditionList(G, yz.getZszfNums());
			addNumsToConditionList(G, yz.getWxdszfNums());
			addNumsToConditionList(G, yz.getBszfNums());

			Set<Integer> H = new HashSet<Integer>();
			addNumsToConditionList(H, yz.getSwzfNums());
			addNumsToConditionList(H, yz.getQiwzfNums());

			XbwJY data = new XbwJY();
			data.setYear(yz.getYear());
			data.setPhase(yz.getPhase());
			appendXbwJY(data, list, A, B, C, D, E, F, G, H);
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	protected void appendXbwJY(XbwJY data, List<XbwJY> list, Set<Integer> A, Set<Integer> B, Set<Integer> C, Set<Integer> D,
			Set<Integer> E, Set<Integer> F, Set<Integer> G, Set<Integer> H) throws Exception {
		Set<Integer>[] AE = combineConditionList(A, E);
		Set<Integer>[] BF = combineConditionList(B, F);
		Set<Integer>[] CG = combineConditionList(C, G);
		Set<Integer>[] DH = combineConditionList(D, H);

		KaiJiang kj = repositories.kaiJiangRepository.findByYearAndPhase(data.getYear(), data.getPhase());
		data.setSpecialNum(kj.getSpecialNum());

		Set<Integer> ABCD = combineConditionList(A, B, C, D);
		Set<Integer> EFGH = combineConditionList(E, F, G, H);

		Set<Integer> EBCD = combineConditionList(AE[1], B, C, D);
		Set<Integer> AFCD = combineConditionList(A, BF[1], C, D);
		Set<Integer> ABGD = combineConditionList(A, B, CG[1], D);
		Set<Integer> ABCH = combineConditionList(A, B, C, DH[1]);

		Set<Integer> AFGH = combineConditionList(AE[0], F, G, H);
		Set<Integer> EBGH = combineConditionList(E, BF[0], G, H);
		Set<Integer> EFCH = combineConditionList(E, F, CG[0], H);
		Set<Integer> EFGD = combineConditionList(E, F, G, DH[0]);

		Set<Integer>[] arr = new Set[] { ABCD, EFGH, EBCD, AFCD, ABGD, ABCH, AFGH, EBGH, EFCH, EFGD };
		List<XbwJYSub> all = new ArrayList<XbwJYSub>();
		for (int i = 0; i < arr.length; i++) {
			for (int num : arr[i]) {
				boolean found = false;
				for (XbwJYSub sub : all) {
					if (num == sub.getNum()) {
						sub.setCount(sub.getCount() + 1);
						found = true;
						break;
					}
				}
				if (!found) {
					XbwJYSub sub = new XbwJYSub();
					sub.setNum(num);
					sub.setCount(1);
					all.add(sub);
				}
			}
		}
		for (XbwJYSub sub : all) {
			Method m = ReflectionUtils.findMethod(XbwJY.class, "getC" + sub.getCount());
			List<Integer> values = (List<Integer>) m.invoke(data);
			values.add(sub.getNum());
		}

		list.add(data);
	}

	@Async
	public Future<Exception> calXbwJY1(D1Yz d1yz, List<XbwJY> list) {
		try {
			Set<Integer> A = new HashSet<Integer>();
			addNumsToConditionList(A, d1yz.getSxNums());
			addNumsToConditionList(A, d1yz.getTwelveNums());
			addNumsToConditionList(A, d1yz.getSlqNums());
			addNumsToConditionList(A, d1yz.getDsNums());

			Set<Integer> B = new HashSet<Integer>();
			addNumsToConditionList(B, d1yz.getLhNums());
			addNumsToConditionList(B, d1yz.getMwNums());
			addNumsToConditionList(B, d1yz.getFdNums());
			addNumsToConditionList(B, d1yz.getPdNums());

			Set<Integer> C = new HashSet<Integer>();
			addNumsToConditionList(C, d1yz.getZsNums());
			addNumsToConditionList(C, d1yz.getWxdsNums());
			addNumsToConditionList(C, d1yz.getBsNums());

			Set<Integer> D = new HashSet<Integer>();
			addNumsToConditionList(D, d1yz.getSwNums());
			addNumsToConditionList(D, d1yz.getQiwNums());

			Set<Integer> E = new HashSet<Integer>();
			addNumsToConditionList(E, d1yz.getSxzfNums());
			addNumsToConditionList(E, d1yz.getTwelvezfNums());
			addNumsToConditionList(E, d1yz.getSlqzfNums());
			addNumsToConditionList(E, d1yz.getDszfNums());

			Set<Integer> F = new HashSet<Integer>();
			addNumsToConditionList(F, d1yz.getLhzfNums());
			addNumsToConditionList(F, d1yz.getMwzfNums());
			addNumsToConditionList(F, d1yz.getFdzfNums());
			addNumsToConditionList(F, d1yz.getPdzfNums());

			Set<Integer> G = new HashSet<Integer>();
			addNumsToConditionList(G, d1yz.getZszfNums());
			addNumsToConditionList(G, d1yz.getWxdszfNums());
			addNumsToConditionList(G, d1yz.getBszfNums());

			Set<Integer> H = new HashSet<Integer>();
			addNumsToConditionList(H, d1yz.getSwzfNums());
			addNumsToConditionList(H, d1yz.getQiwzfNums());

			XbwJY data = new XbwJY();
			data.setYear(d1yz.getYear());
			data.setPhase(d1yz.getPhase());
			appendXbwJY(data, list, A, B, C, D, E, F, G, H);
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calXbwJY2(J0Yz j0yz, D1Yz d1yz, List<XbwJY> list) {
		try {
			Set<Integer> A = new HashSet<Integer>();
			addNumsToConditionList(A, j0yz.getSxNums());
			addNumsToConditionList(A, j0yz.getTwelveNums());
			addNumsToConditionList(A, j0yz.getSlqNums());
			addNumsToConditionList(A, j0yz.getDsNums());

			Set<Integer> B = new HashSet<Integer>();
			addNumsToConditionList(B, j0yz.getLhNums());
			addNumsToConditionList(B, j0yz.getMwNums());
			addNumsToConditionList(B, j0yz.getFdNums());
			addNumsToConditionList(B, j0yz.getPdNums());

			Set<Integer> C = new HashSet<Integer>();
			addNumsToConditionList(C, j0yz.getZsNums());
			addNumsToConditionList(C, j0yz.getWxdsNums());
			addNumsToConditionList(C, j0yz.getBsNums());

			Set<Integer> D = new HashSet<Integer>();
			addNumsToConditionList(D, j0yz.getSwNums());
			addNumsToConditionList(D, j0yz.getQiwNums());

			Set<Integer> E = new HashSet<Integer>();
			addNumsToConditionList(E, d1yz.getSxNums());
			addNumsToConditionList(E, d1yz.getTwelveNums());
			addNumsToConditionList(E, d1yz.getSlqNums());
			addNumsToConditionList(E, d1yz.getDsNums());

			Set<Integer> F = new HashSet<Integer>();
			addNumsToConditionList(F, d1yz.getLhNums());
			addNumsToConditionList(F, d1yz.getMwNums());
			addNumsToConditionList(F, d1yz.getFdNums());
			addNumsToConditionList(F, d1yz.getPdNums());

			Set<Integer> G = new HashSet<Integer>();
			addNumsToConditionList(G, d1yz.getZsNums());
			addNumsToConditionList(G, d1yz.getWxdsNums());
			addNumsToConditionList(G, d1yz.getBsNums());

			Set<Integer> H = new HashSet<Integer>();
			addNumsToConditionList(H, d1yz.getSwNums());
			addNumsToConditionList(H, d1yz.getQiwNums());

			XbwJY data = new XbwJY();
			data.setYear(j0yz.getYear());
			data.setPhase(j0yz.getPhase());
			appendXbwJY(data, list, A, B, C, D, E, F, G, H);
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	@Async
	public Future<Exception> calXbwJY3(J0Yz j0yz, D1Yz d1yz, List<XbwJY> list) {
		try {
			Set<Integer> A = new HashSet<Integer>();
			addNumsToConditionList(A, j0yz.getSxNums());
			addNumsToConditionList(A, j0yz.getTwelveNums());
			addNumsToConditionList(A, j0yz.getSlqNums());
			addNumsToConditionList(A, j0yz.getDsNums());

			Set<Integer> B = new HashSet<Integer>();
			addNumsToConditionList(B, j0yz.getLhNums());
			addNumsToConditionList(B, j0yz.getMwNums());
			addNumsToConditionList(B, j0yz.getFdNums());
			addNumsToConditionList(B, j0yz.getPdNums());

			Set<Integer> C = new HashSet<Integer>();
			addNumsToConditionList(C, j0yz.getZsNums());
			addNumsToConditionList(C, j0yz.getWxdsNums());
			addNumsToConditionList(C, j0yz.getBsNums());

			Set<Integer> D = new HashSet<Integer>();
			addNumsToConditionList(D, j0yz.getSwNums());
			addNumsToConditionList(D, j0yz.getQiwNums());

			Set<Integer> E = new HashSet<Integer>();
			addNumsToConditionList(E, d1yz.getSxzfNums());
			addNumsToConditionList(E, d1yz.getTwelvezfNums());
			addNumsToConditionList(E, d1yz.getSlqzfNums());
			addNumsToConditionList(E, d1yz.getDszfNums());

			Set<Integer> F = new HashSet<Integer>();
			addNumsToConditionList(F, d1yz.getLhzfNums());
			addNumsToConditionList(F, d1yz.getMwzfNums());
			addNumsToConditionList(F, d1yz.getFdzfNums());
			addNumsToConditionList(F, d1yz.getPdzfNums());

			Set<Integer> G = new HashSet<Integer>();
			addNumsToConditionList(G, d1yz.getZszfNums());
			addNumsToConditionList(G, d1yz.getWxdszfNums());
			addNumsToConditionList(G, d1yz.getBszfNums());

			Set<Integer> H = new HashSet<Integer>();
			addNumsToConditionList(H, d1yz.getSwzfNums());
			addNumsToConditionList(H, d1yz.getQiwzfNums());

			XbwJY data = new XbwJY();
			data.setYear(j0yz.getYear());
			data.setPhase(j0yz.getPhase());
			appendXbwJY(data, list, A, B, C, D, E, F, G, H);
			return new AsyncResult<Exception>(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AsyncResult<Exception>(e);
		}
	}

	protected void addNumsToConditionList(Set<Integer> conditionSet, List<Integer> nums) {
		if (nums != null && !nums.isEmpty()) {
			for (Integer num : nums) {
				conditionSet.add(num);
			}
		}
	}

	protected void addNumsToConditionList(Set<Integer> conditionSet, Set<Integer> nums) {
		addNumsToConditionList(conditionSet, new ArrayList<Integer>(nums));
	}

	protected Set<Integer> combineConditionList(Set<Integer> a1, Set<Integer> a2, Set<Integer> a3, Set<Integer> a4) {
		Set<Integer> arr = new HashSet<Integer>(a1);
		addNumsToConditionList(arr, a2);
		addNumsToConditionList(arr, a3);
		addNumsToConditionList(arr, a4);
		Set<Integer> reversed = new HashSet<Integer>();
		for (int i = 1; i < 50; i++) {
			if (!arr.contains(i)) {
				reversed.add(i);
			}
		}
		return reversed;
	}

	protected Set<Integer>[] combineConditionList(Set<Integer> a1, Set<Integer> a2) {
		Set<Integer> a1Excluded = new HashSet<Integer>();
		Set<Integer> a2Excluded = new HashSet<Integer>();
		for (Integer num : a1) {
			if (!a2.contains(num)) {
				a1Excluded.add(num);
			}
		}
		for (Integer num : a2) {
			if (!a1.contains(num)) {
				a2Excluded.add(num);
			}
		}
		return new Set[] { a1Excluded, a2Excluded };
	}

	public XbwJY2 getXbwJY2(XbwJYCondition condition) throws Exception {
		XbwJY2 data = new XbwJY2();
		String[] methodSuffix = new String[] { "A", "B", "C", "D", "E", "F", "G", "H" };
		for (String suffix : methodSuffix) {
			String ms = "calXbwOption" + suffix + "ForType" + condition.getType();
			Method m = ReflectionUtils.findMethod(this.getClass(), ms, XbwJYCondition.class);
			Object values = m.invoke(this, condition);
			m = ReflectionUtils.findMethod(XbwJY2.class, "set" + suffix, List.class);
			m.invoke(data, values);
		}
		return data;
	}

	List<XbwJY2Sub> calXbwOptionAForType0(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxYz("A", condition, new XbwOptionYzHandlerForGetInfos<SxYz>()));
		nums.add(calXbwOptionForYz("合数", "A", condition, LhYz.class, LhNums.class, repositories.lhyzRepository));
		nums.add(calXbwOptionForYz("末位", "A", condition, MwYz.class, MwNums.class, repositories.mwyzRepository));
		nums.add(calXbwOptionForDsYz("A", condition, new XbwOptionYzHandlerForGetInfos<DsYz>()));
		nums.add(calXbwOptionForTm12FdYz("A", condition, new XbwOptionYzHandlerForGetInfos<Tm12FdYz>()));
		return nums;
	}

	protected XbwJY2Sub calXbwOptionForSxYz(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetInfos<SxYz> infoHandler) throws Exception {
		return calXbwOptionForYz("生肖", fdRange, condition, SxYz.class, SxNums.class, repositories.sxyzRepository,
				new XbwOptionYzHandler<SxYz>() {

					@Override
					protected List<XbwInfo> getInfos(SxYz current, Class<SxYz> clazz, Class<?> numsClass, String textPrefix)
							throws Exception {
						List<XbwInfo> infos = new ArrayList<XbwInfo>();
						for (SX sx : SX.seq()) {
							Method m = ReflectionUtils.findMethod(SxYz.class, "get" + sx.name());
							infos.add(new XbwInfo(textPrefix + "-" + sx.getText(), sx, (Integer) m.invoke(current)));
						}
						return infos;
					}

					@Override
					protected XbwJY2Sub getNums(XbwInfo info, String range) {
						return new XbwJY2Sub(info.fd, range, getSxNums(DateUtil.getSxByYear(condition.getYear()), (SX) info.obj));
					}
				}, infoHandler);
	}

	protected XbwJY2Sub calXbwOptionForDsYz(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetInfos<DsYz> infoHandler) throws Exception {
		return calXbwOptionForYz("单双", fdRange, condition, DsYz.class, DsNums.class, repositories.dsyzRepository,
				new XbwOptionYzHandler<DsYz>() {
					@Override
					protected String getText(String textPrefix, String fd) {
						if (fd.equalsIgnoreCase("Ds0Odd")) {
							return textPrefix + "-0单";
						}
						if (fd.equalsIgnoreCase("Ds0Even")) {
							return textPrefix + "-0双";
						}
						if (fd.equalsIgnoreCase("Ds1Odd")) {
							return textPrefix + "-1单";
						}
						if (fd.equalsIgnoreCase("Ds1Even")) {
							return textPrefix + "-1双";
						}
						if (fd.equalsIgnoreCase("Ds2Odd")) {
							return textPrefix + "-2单";
						}
						if (fd.equalsIgnoreCase("Ds2Even")) {
							return textPrefix + "-2双";
						}
						if (fd.equalsIgnoreCase("Ds3Odd")) {
							return textPrefix + "-3单";
						}
						if (fd.equalsIgnoreCase("Ds3Even")) {
							return textPrefix + "-3双";
						}
						if (fd.equalsIgnoreCase("Ds4Odd")) {
							return textPrefix + "-4单";
						}
						if (fd.equalsIgnoreCase("Ds4Even")) {
							return textPrefix + "-4双";
						}
						return "";
					}
				}, infoHandler);
	}

	protected XbwJY2Sub calXbwOptionForTm12FdYz(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetInfos<Tm12FdYz> infoHandler) throws Exception {
		TmYz currentTm = repositories.tmyzRepository.findByYearAndPhase(condition.getYear(), condition.getPhase());
		List<TmYzInfo> currentTmInfos = getTMFDList(currentTm, false);
		List<List<Integer>> nums = new ArrayList<List<Integer>>();
		int length = 12;
		int range = 4;
		for (int i = 1; i <= length; i++) {
			List<Integer> subNums = new ArrayList<Integer>();
			int num = 0;
			int pos = 1;
			for (TmYzInfo info : currentTmInfos) {
				if (pos % range == 0) {
					num = pos / range;
				} else {
					num = pos / range + 1;
				}
				if (num > length) {
					num = length;
				}
				if (num == i) {
					subNums.add(info.getNum());
				}
				pos++;
			}
			nums.add(subNums);
		}
		return calXbwOptionForYz("分段", fdRange, condition, null, null, repositories.tm12fdyzRepository,
				new XbwOptionYzHandler<Tm12FdYz>() {
					@Override
					protected List<XbwInfo> getInfos(Tm12FdYz current, Class<Tm12FdYz> clazz, Class<?> numsClass,
							String textPrefix) throws Exception {
						List<XbwInfo> infos = new ArrayList<XbwInfo>();
						for (int i = 0; i < nums.size(); i++) {
							Method m = ReflectionUtils.findMethod(Tm12FdYz.class, "getW" + (i + 1));
							infos.add(new XbwInfo("分段-位" + (i + 1), nums.get(i), (Integer) m.invoke(current)));
						}
						return infos;
					}

					@Override
					protected int getLength(Class<?> numsClass) throws Exception {
						return length;
					}
				}, infoHandler);
	}

	List<XbwJY2Sub> calXbwOptionBForType0(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("质数", "B", condition, ZsYz.class, ZsNums.class, repositories.zsyzRepository));
		nums.add(calXbwOptionForWxYz("B", condition, new XbwOptionYzHandlerForGetInfos<WxdsYz>()));
		nums.add(calXbwOptionForBsYz("B", condition, new XbwOptionYzHandlerForGetInfos<Bs9qYz>()));
		nums.add(
				calXbwOptionForYz("十二区", "B", condition, TwelveYz.class, TwelveNums.class, repositories.twelveyzRepository));
		nums.add(calXbwOptionForYz("十六区", "B", condition, SlqYz.class, SlqNums.class, repositories.slqyzRepository));
		return nums;
	}

	protected XbwJY2Sub calXbwOptionForWxYz(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetInfos<WxdsYz> infoHandler) throws Exception {
		return calXbwOptionForYz("五行单双", fdRange, condition, WxdsYz.class, WxDsNums.class, repositories.wxdsyzRepository,
				new XbwOptionYzHandler<WxdsYz>() {
					@Override
					protected String getText(String textPrefix, String fd) {
						if (fd.equalsIgnoreCase("JinOdd")) {
							return textPrefix + "-金单";
						}
						if (fd.equalsIgnoreCase("JinEven")) {
							return textPrefix + "-金双";
						}
						if (fd.equalsIgnoreCase("MuOdd")) {
							return textPrefix + "-木单";
						}
						if (fd.equalsIgnoreCase("MuEven")) {
							return textPrefix + "-木双";
						}
						if (fd.equalsIgnoreCase("ShuiOdd")) {
							return textPrefix + "-水单";
						}
						if (fd.equalsIgnoreCase("ShuiEven")) {
							return textPrefix + "-水双";
						}
						if (fd.equalsIgnoreCase("HuoOdd")) {
							return textPrefix + "-火单";
						}
						if (fd.equalsIgnoreCase("HuoEven")) {
							return textPrefix + "-火双";
						}
						if (fd.equalsIgnoreCase("TuOdd")) {
							return textPrefix + "-土单";
						}
						if (fd.equalsIgnoreCase("TuEven")) {
							return textPrefix + "-土双";
						}
						return "";
					}
				}, infoHandler);
	}

	protected XbwJY2Sub calXbwOptionForBsYz(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetInfos<Bs9qYz> infoHandler) throws Exception {
		return calXbwOptionForYz("波色", fdRange, condition, Bs9qYz.class, Bs9qNums.class, repositories.bs9qyzRepository,
				new XbwOptionYzHandler<Bs9qYz>() {
					@Override
					protected String getText(String textPrefix, String fd) {
						if (fd.equalsIgnoreCase("Red1")) {
							return textPrefix + "-红1";
						}
						if (fd.equalsIgnoreCase("Red2")) {
							return textPrefix + "-红2";
						}
						if (fd.equalsIgnoreCase("Red3")) {
							return textPrefix + "-红3";
						}
						if (fd.equalsIgnoreCase("Blue1")) {
							return textPrefix + "-蓝1";
						}
						if (fd.equalsIgnoreCase("Blue2")) {
							return textPrefix + "-蓝2";
						}
						if (fd.equalsIgnoreCase("Blue3")) {
							return textPrefix + "-蓝3";
						}
						if (fd.equalsIgnoreCase("Green1")) {
							return textPrefix + "-绿1";
						}
						if (fd.equalsIgnoreCase("Green2")) {
							return textPrefix + "-绿2";
						}
						if (fd.equalsIgnoreCase("Green3")) {
							return textPrefix + "-绿3";
						}
						return "";
					}
				}, infoHandler);
	}

	List<XbwJY2Sub> calXbwOptionCForType0(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("配对", "C", condition, PdYz.class, PdNums.class, repositories.pdyzRepository));
		nums.add(calXbwOptionForYz("七位", "C", condition, QiwYz.class, QiwNums.class, repositories.qiwYzRepository));
		nums.add(calXbwOptionForYz("杂项1", "C", condition, Zx1Yz.class, Zx1Nums.class, repositories.zx1yzRepository));
		nums.add(calXbwOptionForYz("杂项2", "C", condition, Zx2Yz.class, Zx2Nums.class, repositories.zx2yzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionDForType0(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("七区", "D", condition, QqYz.class, QqNums.class, repositories.qqyzRepository));
		nums.add(calXbwOptionForYz("杂项4", "D", condition, Zx4Yz.class, Zx4Nums.class, repositories.zx4yzRepository));
		nums.add(calXbwOptionForYz("杂项7", "D", condition, Zx7Yz.class, Zx7Nums.class, repositories.zx7yzRepository));
		nums.add(calXbwOptionForYz("杂项9", "D", condition, Zx9Yz.class, Zx9Nums.class, repositories.zx9yzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionEForType0(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxzf("E", condition, new XbwOptionYzHandlerForGetPos<SxZfYz2>()));
		nums.add(calXbwOptionForLhzf("E", condition, new XbwOptionYzHandlerForGetPos<LhZfYz>()));
		nums.add(calXbwOptionForMwzf("E", condition, new XbwOptionYzHandlerForGetPos<MwZfYz>()));
		nums.add(calXbwOptionForDszf("E", condition, new XbwOptionYzHandlerForGetPos<DsZfYz>()));
		nums.add(calXbwOptionForFdzf("E", condition, new XbwOptionYzHandlerForGetPos<Tm12FdZfYz>()));
		return nums;
	}

	protected XbwJY2Sub calXbwOptionForSxzf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<SxZfYz2> posHandler) throws Exception {
		return calXbwOptionForZf(condition, SxZfYz2.class, repositories.sxzfyz2Repository,
				new XbwOptionZfHandler<SxYz, SxZfYz2>() {

					@Override
					protected XbwJY2Sub getNums(int pos, SxZfYz2 current) throws Exception {
						int currentPos = pos;
						SxYz data = repositories.sxyzRepository.findByYearAndPhase(current.getYear(), current.getPhase());
						pos += data.getCurrentSx().getPos();
						int len = getLength();
						if (pos > len) {
							pos -= len;
						}
						SX sx = SX.posOf(pos);
						return new XbwJY2Sub("生肖-振幅" + currentPos + "-" + sx.getText(), fdRange,
								getSxNums(DateUtil.getSxByYear(condition.getYear()), sx));
					}

					@Override
					protected int getLength() {
						return 12;
					}

					@Override
					protected BaseYzRepository<SxYz> getRepository() {
						return null;
					}

					@Override
					protected String[] getFds() {
						return null;
					}

					@Override
					protected Class<SxYz> getClazz() {
						return null;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						return null;
					}

				}, posHandler);
	}

	protected XbwJY2Sub calXbwOptionForLhzf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<LhZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, LhZfYz.class, repositories.lhzfyzRepository,
				new XbwOptionZfHandler<LhYz, LhZfYz>() {

					@Override
					protected int getLength() {
						return 10;
					}

					@Override
					protected BaseYzRepository<LhYz> getRepository() {
						return repositories.lhyzRepository;
					}

					@Override
					protected String[] getFds() {
						return LhNums.FDS;
					}

					@Override
					protected Class<LhYz> getClazz() {
						return LhYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						return new XbwJY2Sub("合数-振幅" + currentPos + "-位" + pos, fdRange, LhNums.NUMS[pos]);
					}

				}, posHandler);
	}

	protected XbwJY2Sub calXbwOptionForMwzf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<MwZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, MwZfYz.class, repositories.mwzfyzRepository,
				new XbwOptionZfHandler<MwYz, MwZfYz>() {

					@Override
					protected int getLength() {
						return 10;
					}

					@Override
					protected BaseYzRepository<MwYz> getRepository() {
						return repositories.mwyzRepository;
					}

					@Override
					protected String[] getFds() {
						return MwNums.FDS;
					}

					@Override
					protected Class<MwYz> getClazz() {
						return MwYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						return new XbwJY2Sub("末位-振幅" + currentPos + "-位" + pos, fdRange, MwNums.NUMS[pos]);
					}

				}, posHandler);
	}

	protected XbwJY2Sub calXbwOptionForDszf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<DsZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, DsZfYz.class, repositories.dszfyzRepository,
				new XbwOptionZfHandler<DsYz, DsZfYz>() {

					@Override
					protected int getLength() {
						return 10;
					}

					@Override
					protected BaseYzRepository<DsYz> getRepository() {
						return repositories.dsyzRepository;
					}

					@Override
					protected String[] getFds() {
						return DsNums.FDS;
					}

					@Override
					protected Class<DsYz> getClazz() {
						return DsYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						String text = null;
						switch (pos) {
						case 0:
							text = "0单";
							break;
						case 1:
							text = "0双";
							break;
						case 2:
							text = "1单";
							break;
						case 3:
							text = "1双";
							break;
						case 4:
							text = "2单";
							break;
						case 5:
							text = "2双";
							break;
						case 6:
							text = "3单";
							break;
						case 7:
							text = "3双";
							break;
						case 8:
							text = "4单";
							break;
						case 9:
							text = "4双";
							break;
						}
						return new XbwJY2Sub("单双-振幅" + currentPos + "-" + text, fdRange, DsNums.NUMS[pos]);
					}

				}, posHandler);
	}

	protected XbwJY2Sub calXbwOptionForFdzf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<Tm12FdZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, Tm12FdZfYz.class, repositories.tm12fdzfyzRepository,
				new XbwOptionZfHandler<Tm12FdYz, Tm12FdZfYz>() {

					@Override
					protected XbwJY2Sub getNums(int pos, Tm12FdZfYz current) throws Exception {
						TmYz tmData = repositories.tmyzRepository.findByYearAndPhase(current.getYear(), current.getPhase());
						List<TmYzInfo> infos = getTMFDList(tmData, false);
						List<List<Integer>> nums = new ArrayList<List<Integer>>();
						int length = 12;
						int range = 4;
						for (int i = 1; i <= length; i++) {
							List<Integer> subNums = new ArrayList<Integer>();
							int num = 0;
							int currentPos = 1;
							for (TmYzInfo info : infos) {
								if (currentPos % range == 0) {
									num = currentPos / range;
								} else {
									num = currentPos / range + 1;
								}
								if (num > length) {
									num = length;
								}
								if (num == i) {
									subNums.add(info.getNum());
								}
								currentPos++;
							}
							nums.add(subNums);
						}
						int currentPos = pos;
						Tm12FdYz data = repositories.tm12fdyzRepository.findByYearAndPhase(current.getYear(), current.getPhase());
						for (int i = 1; i < 13; i++) {
							Method m = ReflectionUtils.findMethod(Tm12FdYz.class, "getW" + i);
							Integer value = (Integer) m.invoke(data);
							if (value != null && value == 0) {
								pos += i;
								break;
							}
						}
						int len = getLength();
						if (pos > len) {
							pos -= len;
						}
						return new XbwJY2Sub("分段-振幅" + currentPos + "-位" + pos, fdRange, nums.get(pos - 1));
					}

					@Override
					protected int getLength() {
						return 12;
					}

					@Override
					protected BaseYzRepository<Tm12FdYz> getRepository() {
						return null;
					}

					@Override
					protected String[] getFds() {
						return null;
					}

					@Override
					protected Class<Tm12FdYz> getClazz() {
						return null;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						return null;
					}

				}, posHandler);
	}

	List<XbwJY2Sub> calXbwOptionFForType0(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForZszf("F", condition, new XbwOptionYzHandlerForGetPos<ZsZfYz>()));
		nums.add(calXbwOptionForWxdszf("F", condition, new XbwOptionYzHandlerForGetPos<WxdsZfYz>()));
		nums.add(calXbwOptionForBszf("F", condition, new XbwOptionYzHandlerForGetPos<Bs9qZfYz>()));
		nums.add(calXbwOptionForTwelvezf("F", condition, new XbwOptionYzHandlerForGetPos<TwelveZfYz>()));
		nums.add(calXbwOptionForSlqzf("F", condition, new XbwOptionYzHandlerForGetPos<SlqZfYz>()));
		return nums;
	}

	protected XbwJY2Sub calXbwOptionForZszf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<ZsZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, ZsZfYz.class, repositories.zszfyzRepository,
				new XbwOptionZfHandler<ZsYz, ZsZfYz>() {

					@Override
					protected int getLength() {
						return 9;
					}

					@Override
					protected BaseYzRepository<ZsYz> getRepository() {
						return repositories.zsyzRepository;
					}

					@Override
					protected String[] getFds() {
						return ZsNums.FDS;
					}

					@Override
					protected Class<ZsYz> getClazz() {
						return ZsYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						return new XbwJY2Sub("质数-振幅" + currentPos + "-位" + (pos + 1), fdRange, ZsNums.NUMS[pos]);
					}

				}, posHandler);
	}

	protected XbwJY2Sub calXbwOptionForWxdszf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<WxdsZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, WxdsZfYz.class, repositories.wxdszfyzRepository,
				new XbwOptionZfHandler<WxdsYz, WxdsZfYz>() {

					@Override
					protected int getLength() {
						return 9;
					}

					@Override
					protected BaseYzRepository<WxdsYz> getRepository() {
						return repositories.wxdsyzRepository;
					}

					@Override
					protected String[] getFds() {
						return WxDsNums.FDS;
					}

					@Override
					protected Class<WxdsYz> getClazz() {
						return WxdsYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						String text = null;
						switch (pos) {
						case 0:
							text = "金单";
							break;
						case 1:
							text = "金双";
							break;
						case 2:
							text = "木单";
							break;
						case 3:
							text = "木双";
							break;
						case 4:
							text = "水单";
							break;
						case 5:
							text = "水双";
							break;
						case 6:
							text = "火单";
							break;
						case 7:
							text = "火双";
							break;
						case 8:
							text = "土单";
							break;
						case 9:
							text = "土双";
							break;
						}
						return new XbwJY2Sub("五行单双-振幅" + currentPos + "-" + text, fdRange, WxDsNums.NUMS[pos]);
					}

				}, posHandler);
	}

	protected XbwJY2Sub calXbwOptionForBszf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<Bs9qZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, Bs9qZfYz.class, repositories.bs9qzfyzRepository,
				new XbwOptionZfHandler<Bs9qYz, Bs9qZfYz>() {

					@Override
					protected int getLength() {
						return 9;
					}

					@Override
					protected BaseYzRepository<Bs9qYz> getRepository() {
						return repositories.bs9qyzRepository;
					}

					@Override
					protected String[] getFds() {
						return Bs9qNums.FDS;
					}

					@Override
					protected Class<Bs9qYz> getClazz() {
						return Bs9qYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						String text = null;
						switch (pos) {
						case 0:
							text = "红1";
							break;
						case 1:
							text = "红2";
							break;
						case 2:
							text = "红3";
							break;
						case 3:
							text = "蓝1";
							break;
						case 4:
							text = "蓝2";
							break;
						case 5:
							text = "蓝3";
							break;
						case 6:
							text = "绿1";
							break;
						case 7:
							text = "绿2";
							break;
						case 8:
							text = "绿3";
							break;
						}
						return new XbwJY2Sub("波色-振幅" + currentPos + "-" + text, fdRange, Bs9qNums.NUMS[pos]);
					}

				}, posHandler);
	}

	protected XbwJY2Sub calXbwOptionForTwelvezf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<TwelveZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, TwelveZfYz.class, repositories.twelvezfyzRepository,
				new XbwOptionZfHandler<TwelveYz, TwelveZfYz>() {

					@Override
					protected int getLength() {
						return 12;
					}

					@Override
					protected BaseYzRepository<TwelveYz> getRepository() {
						return repositories.twelveyzRepository;
					}

					@Override
					protected String[] getFds() {
						return TwelveNums.FDS;
					}

					@Override
					protected Class<TwelveYz> getClazz() {
						return TwelveYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						return new XbwJY2Sub("十二区-振幅" + currentPos + "-位" + (pos + 1), fdRange, TwelveNums.NUMS[pos]);
					}

				}, posHandler);
	}

	protected XbwJY2Sub calXbwOptionForSlqzf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<SlqZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, SlqZfYz.class, repositories.slqzfyzRepository,
				new XbwOptionZfHandler<SlqYz, SlqZfYz>() {

					@Override
					protected int getLength() {
						return 16;
					}

					@Override
					protected BaseYzRepository<SlqYz> getRepository() {
						return repositories.slqyzRepository;
					}

					@Override
					protected String[] getFds() {
						return SlqNums.FDS;
					}

					@Override
					protected Class<SlqYz> getClazz() {
						return SlqYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						return new XbwJY2Sub("十六区-振幅" + currentPos + "-位" + pos, fdRange, SlqNums.NUMS[pos]);
					}

				}, posHandler);
	}

	List<XbwJY2Sub> calXbwOptionGForType0(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForPdzf("G", condition, new XbwOptionYzHandlerForGetPos<PdZfYz>()));
		nums.add(calXbwOptionForQiwzf("G", condition, new XbwOptionYzHandlerForGetPos<QiwZfYz>()));
		nums.add(calXbwOptionForYz("杂项3", "G", condition, Zx3Yz.class, Zx3Nums.class, repositories.zx3yzRepository));
		nums.add(calXbwOptionForYz("杂项5", "G", condition, Zx5Yz.class, Zx5Nums.class, repositories.zx5yzRepository));
		return nums;
	}

	protected XbwJY2Sub calXbwOptionForPdzf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<PdZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, PdZfYz.class, repositories.pdzfyzRepository,
				new XbwOptionZfHandler<PdYz, PdZfYz>() {

					@Override
					protected int getLength() {
						return 12;
					}

					@Override
					protected BaseYzRepository<PdYz> getRepository() {
						return repositories.pdyzRepository;
					}

					@Override
					protected String[] getFds() {
						return PdNums.FDS;
					}

					@Override
					protected Class<PdYz> getClazz() {
						return PdYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						return new XbwJY2Sub("配对-振幅" + currentPos + "-位" + (pos + 1), fdRange, PdNums.NUMS[pos]);
					}

				}, posHandler);
	}

	protected XbwJY2Sub calXbwOptionForQiwzf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<QiwZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, QiwZfYz.class, repositories.qiwzfYzRepository,
				new XbwOptionZfHandler<QiwYz, QiwZfYz>() {

					@Override
					protected int getLength() {
						return 7;
					}

					@Override
					protected BaseYzRepository<QiwYz> getRepository() {
						return repositories.qiwYzRepository;
					}

					@Override
					protected String[] getFds() {
						return QiwNums.FDS;
					}

					@Override
					protected Class<QiwYz> getClazz() {
						return QiwYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						return new XbwJY2Sub("七位-振幅" + currentPos + "-位" + (pos + 1), fdRange, QiwNums.NUMS[pos]);
					}

				}, posHandler);
	}

	List<XbwJY2Sub> calXbwOptionHForType0(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForQqzf("H", condition, new XbwOptionYzHandlerForGetPos<QqZfYz>()));
		nums.add(calXbwOptionForYz("杂项6", "H", condition, Zx6Yz.class, Zx6Nums.class, repositories.zx6yzRepository));
		nums.add(calXbwOptionForYz("杂项8", "H", condition, Zx8Yz.class, Zx8Nums.class, repositories.zx8yzRepository));
		nums.add(calXbwOptionForYz("杂项10", "H", condition, Zx10Yz.class, Zx10Nums.class, repositories.zx10yzRepository));
		return nums;
	}

	protected XbwJY2Sub calXbwOptionForQqzf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<QqZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, QqZfYz.class, repositories.qqzfyzRepository,
				new XbwOptionZfHandler<QqYz, QqZfYz>() {

					@Override
					protected int getLength() {
						return 7;
					}

					@Override
					protected BaseYzRepository<QqYz> getRepository() {
						return repositories.qqyzRepository;
					}

					@Override
					protected String[] getFds() {
						return QqNums.FDS;
					}

					@Override
					protected Class<QqYz> getClazz() {
						return QqYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						return new XbwJY2Sub("七区-振幅" + currentPos + "-位" + (pos + 1), fdRange, QqNums.NUMS[pos]);
					}

				}, posHandler);
	}

	protected static class XbwOptionYzHandlerForGetInfos<T extends Avg> {
		protected XbwInfo getInfo(T current, T last, Class<T> clazz, Class<?> numsClass, List<XbwInfo> currentInfos,
				int len) throws Exception {
			XbwInfo info = null;
			if (current.getTotal() >= last.getTotal()) {
				if (new BigDecimal(current.getTotal()).compareTo(current.getTotalAvg()) >= 0) {
					info = currentInfos.get(len - 2);
				} else {
					info = currentInfos.get(len - 1);
				}
			} else {
				if (new BigDecimal(current.getTotal()).compareTo(current.getTotalAvg()) >= 0) {
					info = currentInfos.get(0);
				} else {
					info = currentInfos.get(1);
				}
			}
			return info;
		}
	}

	protected static class XbwOptionYzHandler<T extends Avg> {
		protected List<XbwInfo> getInfos(T current, Class<T> clazz, Class<?> numsClass, String textPrefix)
				throws Exception {
			List<XbwInfo> infos = new ArrayList<XbwInfo>();
			for (String fd : (String[]) numsClass.getDeclaredField("FDS").get(null)) {
				Method m = ReflectionUtils.findMethod(clazz, "get" + fd);
				infos.add(new XbwInfo(getText(textPrefix, fd), ReflectionUtils.findField(numsClass, fd.toUpperCase()).get(null),
						(Integer) m.invoke(current)));
			}
			return infos;
		}

		protected String getText(String textPrefix, String fd) {
			return fd.replace("W", textPrefix + "-位").replace("Fd", textPrefix + "-位");
		}

		protected XbwJY2Sub getNums(XbwInfo info, String range) {
			return new XbwJY2Sub(info.fd, range, (List<Integer>) info.obj);
		}

		protected int getLength(Class<?> numsClass) throws Exception {
			return ((List[]) numsClass.getDeclaredField("NUMS").get(null)).length;
		}
	}

	protected <T extends Avg> XbwJY2Sub calXbwOptionForYz(String textPrefix, String range, XbwJYCondition condition,
			Class<T> clazz, Class<?> numsClass, BaseYzRepository<T> repository) throws Exception {
		return calXbwOptionForYz(textPrefix, range, condition, clazz, numsClass, repository, new XbwOptionYzHandler<T>(),
				new XbwOptionYzHandlerForGetInfos<T>());
	}

	protected <T extends Avg> XbwJY2Sub calXbwOptionForYz(String textPrefix, String range, XbwJYCondition condition,
			Class<T> clazz, Class<?> numsClass, BaseYzRepository<T> repository, XbwOptionYzHandler<T> handler,
			XbwOptionYzHandlerForGetInfos<T> infoHandler) throws Exception {
		T current = repository.findByYearAndPhase(condition.getYear(), condition.getPhase());
		T last = repository.findByYearAndPhase(condition.getYear(), condition.getPhase() - 1);
		List<XbwInfo> currentInfos = handler.getInfos(current, clazz, numsClass, textPrefix);
		Collections.sort(currentInfos, new Comparator<XbwInfo>() {

			@Override
			public int compare(XbwInfo o1, XbwInfo o2) {
				return o1.yz.compareTo(o2.yz);
			}

		});
		return handler.getNums(
				infoHandler.getInfo(current, last, clazz, numsClass, currentInfos, handler.getLength(numsClass)), range);
	}

	protected static class XbwOptionYzHandlerForGetPos<Z extends Avg> {
		protected Integer getPos(Z current, Z last, List<XbwInfo> infos, int len) {
			Integer pos = null;
			if (current.getTotal() >= last.getTotal()) {
				if (new BigDecimal(current.getTotal()).compareTo(current.getTotalAvg()) >= 0) {
					pos = (Integer) infos.get(len - 2).obj;
				} else {
					pos = (Integer) infos.get(len - 1).obj;
				}
			} else {
				if (new BigDecimal(current.getTotal()).compareTo(current.getTotalAvg()) >= 0) {
					pos = (Integer) infos.get(0).obj;
				} else {
					pos = (Integer) infos.get(1).obj;
				}
			}
			return pos;
		}
	}

	protected static abstract class XbwOptionZfHandler<T extends Avg, Z extends Avg> {

		protected abstract BaseYzRepository<T> getRepository();

		protected abstract String[] getFds();

		protected abstract Class<T> getClazz();

		protected abstract XbwJY2Sub getNums(int currentPos, int pos);

		protected XbwJY2Sub getNums(int pos, Z current) throws Exception {
			int currentPos = pos;
			int len = getLength();
			T data = getRepository().findByYearAndPhase(current.getYear(), current.getPhase());
			int i = 0;
			for (String fd : getFds()) {
				Method m = ReflectionUtils.findMethod(getClazz(), "get" + fd);
				Integer value = (Integer) m.invoke(data);
				if (value != null && value == 0) {
					pos += i;
					break;
				}
				i++;
			}
			if (pos >= len) {
				pos -= len;
			}
			return getNums(currentPos, pos);
		}

		protected abstract int getLength();

	}

	protected <T extends Avg, Z extends ZfAvg> XbwJY2Sub calXbwOptionForZf(XbwJYCondition condition, Class<Z> clazz,
			BaseYzRepository<Z> repository, XbwOptionZfHandler<T, Z> handler, XbwOptionYzHandlerForGetPos<Z> posHandler)
			throws Exception {
		Z current = repository.findByYearAndPhase(condition.getYear(), condition.getPhase());
		Z last = repository.findByYearAndPhase(condition.getYear(), condition.getPhase() - 1);
		List<XbwInfo> infos = new ArrayList<XbwInfo>();
		int len = handler.getLength();
		for (int i = 0; i < len; i++) {
			Method m = ReflectionUtils.findMethod(clazz, "getZf" + i);
			infos.add(new XbwInfo(null, i, (Integer) m.invoke(current)));
		}
		Collections.sort(infos, new Comparator<XbwInfo>() {

			@Override
			public int compare(XbwInfo o1, XbwInfo o2) {
				return o1.yz.compareTo(o2.yz);
			}

		});
		return handler.getNums(posHandler.getPos(current, last, infos, handler.getLength()), current);
	}

	List<XbwJY2Sub> calXbwOptionAForType1(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxYz("A", condition, new XbwOptionYzHandlerForGetInfos<SxYz>()));
		nums.add(calXbwOptionForYz("合数", "A", condition, LhYz.class, LhNums.class, repositories.lhyzRepository));
		nums.add(calXbwOptionForYz("末位", "A", condition, MwYz.class, MwNums.class, repositories.mwyzRepository));
		nums.add(calXbwOptionForYz("杂项1", "A", condition, Zx1Yz.class, Zx1Nums.class, repositories.zx1yzRepository));
		nums.add(calXbwOptionForTm12FdYz("A", condition, new XbwOptionYzHandlerForGetInfos<Tm12FdYz>()));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionAForType2(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxYz("A", condition, new XbwOptionYzHandlerForGetInfos<SxYz>()));
		nums.add(calXbwOptionForYz("合数", "A", condition, LhYz.class, LhNums.class, repositories.lhyzRepository));
		nums.add(calXbwOptionForYz("末位", "A", condition, MwYz.class, MwNums.class, repositories.mwyzRepository));
		nums.add(calXbwOptionForTm12FdYz("A", condition, new XbwOptionYzHandlerForGetInfos<Tm12FdYz>()));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionAForType3(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxYz("A", condition, new XbwOptionYzHandlerForGetInfos<SxYz>() {
			@Override
			protected XbwInfo getInfo(SxYz current, SxYz last, Class<SxYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		nums.add(calXbwOptionForYz("合数", "A", condition, LhYz.class, LhNums.class, repositories.lhyzRepository,
				new XbwOptionYzHandler<LhYz>(), new XbwOptionYzHandlerForGetInfos<LhYz>() {
					@Override
					protected XbwInfo getInfo(LhYz current, LhYz last, Class<LhYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("末位", "A", condition, MwYz.class, MwNums.class, repositories.mwyzRepository,
				new XbwOptionYzHandler<MwYz>(), new XbwOptionYzHandlerForGetInfos<MwYz>() {
					@Override
					protected XbwInfo getInfo(MwYz current, MwYz last, Class<MwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForDsYz("A", condition, new XbwOptionYzHandlerForGetInfos<DsYz>() {
			@Override
			protected XbwInfo getInfo(DsYz current, DsYz last, Class<DsYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		nums.add(calXbwOptionForTm12FdYz("A", condition, new XbwOptionYzHandlerForGetInfos<Tm12FdYz>() {
			@Override
			protected XbwInfo getInfo(Tm12FdYz current, Tm12FdYz last, Class<Tm12FdYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionAForType4(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxYz("A", condition, new XbwOptionYzHandlerForGetInfos<SxYz>() {
			@Override
			protected XbwInfo getInfo(SxYz current, SxYz last, Class<SxYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		nums.add(calXbwOptionForYz("合数", "A", condition, LhYz.class, LhNums.class, repositories.lhyzRepository,
				new XbwOptionYzHandler<LhYz>(), new XbwOptionYzHandlerForGetInfos<LhYz>() {
					@Override
					protected XbwInfo getInfo(LhYz current, LhYz last, Class<LhYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("末位", "A", condition, MwYz.class, MwNums.class, repositories.mwyzRepository,
				new XbwOptionYzHandler<MwYz>(), new XbwOptionYzHandlerForGetInfos<MwYz>() {
					@Override
					protected XbwInfo getInfo(MwYz current, MwYz last, Class<MwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项1", "A", condition, Zx1Yz.class, Zx1Nums.class, repositories.zx1yzRepository,
				new XbwOptionYzHandler<Zx1Yz>(), new XbwOptionYzHandlerForGetInfos<Zx1Yz>() {
					@Override
					protected XbwInfo getInfo(Zx1Yz current, Zx1Yz last, Class<Zx1Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForTm12FdYz("A", condition, new XbwOptionYzHandlerForGetInfos<Tm12FdYz>() {
			@Override
			protected XbwInfo getInfo(Tm12FdYz current, Tm12FdYz last, Class<Tm12FdYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionAForType5(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxYz("A", condition, new XbwOptionYzHandlerForGetInfos<SxYz>() {
			@Override
			protected XbwInfo getInfo(SxYz current, SxYz last, Class<SxYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		nums.add(calXbwOptionForYz("合数", "A", condition, LhYz.class, LhNums.class, repositories.lhyzRepository,
				new XbwOptionYzHandler<LhYz>(), new XbwOptionYzHandlerForGetInfos<LhYz>() {
					@Override
					protected XbwInfo getInfo(LhYz current, LhYz last, Class<LhYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("末位", "A", condition, MwYz.class, MwNums.class, repositories.mwyzRepository,
				new XbwOptionYzHandler<MwYz>(), new XbwOptionYzHandlerForGetInfos<MwYz>() {
					@Override
					protected XbwInfo getInfo(MwYz current, MwYz last, Class<MwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForTm12FdYz("A", condition, new XbwOptionYzHandlerForGetInfos<Tm12FdYz>() {
			@Override
			protected XbwInfo getInfo(Tm12FdYz current, Tm12FdYz last, Class<Tm12FdYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionAForType6(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxYz("A", condition, new XbwOptionYzHandlerForGetInfos<SxYz>() {
			@Override
			protected XbwInfo getInfo(SxYz current, SxYz last, Class<SxYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		nums.add(calXbwOptionForYz("合数", "A", condition, LhYz.class, LhNums.class, repositories.lhyzRepository,
				new XbwOptionYzHandler<LhYz>(), new XbwOptionYzHandlerForGetInfos<LhYz>() {
					@Override
					protected XbwInfo getInfo(LhYz current, LhYz last, Class<LhYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("末位", "A", condition, MwYz.class, MwNums.class, repositories.mwyzRepository,
				new XbwOptionYzHandler<MwYz>(), new XbwOptionYzHandlerForGetInfos<MwYz>() {
					@Override
					protected XbwInfo getInfo(MwYz current, MwYz last, Class<MwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForDsYz("A", condition, new XbwOptionYzHandlerForGetInfos<DsYz>() {
			@Override
			protected XbwInfo getInfo(DsYz current, DsYz last, Class<DsYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		nums.add(calXbwOptionForTm12FdYz("A", condition, new XbwOptionYzHandlerForGetInfos<Tm12FdYz>() {
			@Override
			protected XbwInfo getInfo(Tm12FdYz current, Tm12FdYz last, Class<Tm12FdYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionAForType7(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxYz("A", condition, new XbwOptionYzHandlerForGetInfos<SxYz>() {
			@Override
			protected XbwInfo getInfo(SxYz current, SxYz last, Class<SxYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		nums.add(calXbwOptionForYz("合数", "A", condition, LhYz.class, LhNums.class, repositories.lhyzRepository,
				new XbwOptionYzHandler<LhYz>(), new XbwOptionYzHandlerForGetInfos<LhYz>() {
					@Override
					protected XbwInfo getInfo(LhYz current, LhYz last, Class<LhYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("末位", "A", condition, MwYz.class, MwNums.class, repositories.mwyzRepository,
				new XbwOptionYzHandler<MwYz>(), new XbwOptionYzHandlerForGetInfos<MwYz>() {
					@Override
					protected XbwInfo getInfo(MwYz current, MwYz last, Class<MwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项1", "A", condition, Zx1Yz.class, Zx1Nums.class, repositories.zx1yzRepository,
				new XbwOptionYzHandler<Zx1Yz>(), new XbwOptionYzHandlerForGetInfos<Zx1Yz>() {
					@Override
					protected XbwInfo getInfo(Zx1Yz current, Zx1Yz last, Class<Zx1Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForTm12FdYz("A", condition, new XbwOptionYzHandlerForGetInfos<Tm12FdYz>() {
			@Override
			protected XbwInfo getInfo(Tm12FdYz current, Tm12FdYz last, Class<Tm12FdYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionAForType8(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxYz("A", condition, new XbwOptionYzHandlerForGetInfos<SxYz>() {
			@Override
			protected XbwInfo getInfo(SxYz current, SxYz last, Class<SxYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		nums.add(calXbwOptionForYz("合数", "A", condition, LhYz.class, LhNums.class, repositories.lhyzRepository,
				new XbwOptionYzHandler<LhYz>(), new XbwOptionYzHandlerForGetInfos<LhYz>() {
					@Override
					protected XbwInfo getInfo(LhYz current, LhYz last, Class<LhYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("末位", "A", condition, MwYz.class, MwNums.class, repositories.mwyzRepository,
				new XbwOptionYzHandler<MwYz>(), new XbwOptionYzHandlerForGetInfos<MwYz>() {
					@Override
					protected XbwInfo getInfo(MwYz current, MwYz last, Class<MwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForTm12FdYz("A", condition, new XbwOptionYzHandlerForGetInfos<Tm12FdYz>() {
			@Override
			protected XbwInfo getInfo(Tm12FdYz current, Tm12FdYz last, Class<Tm12FdYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionBForType1(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("质数", "B", condition, ZsYz.class, ZsNums.class, repositories.zsyzRepository));
		nums.add(calXbwOptionForYz("杂项2", "B", condition, Zx2Yz.class, Zx2Nums.class, repositories.zx2yzRepository));
		nums.add(calXbwOptionForBsYz("B", condition, new XbwOptionYzHandlerForGetInfos<Bs9qYz>()));
		nums.add(
				calXbwOptionForYz("十二区", "B", condition, TwelveYz.class, TwelveNums.class, repositories.twelveyzRepository));
		nums.add(calXbwOptionForYz("十六区", "B", condition, SlqYz.class, SlqNums.class, repositories.slqyzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionBForType2(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("质数", "B", condition, ZsYz.class, ZsNums.class, repositories.zsyzRepository));
		nums.add(calXbwOptionForBsYz("B", condition, new XbwOptionYzHandlerForGetInfos<Bs9qYz>()));
		nums.add(
				calXbwOptionForYz("十二区", "B", condition, TwelveYz.class, TwelveNums.class, repositories.twelveyzRepository));
		nums.add(calXbwOptionForYz("十六区", "B", condition, SlqYz.class, SlqNums.class, repositories.slqyzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionBForType3(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("质数", "B", condition, ZsYz.class, ZsNums.class, repositories.zsyzRepository,
				new XbwOptionYzHandler<ZsYz>(), new XbwOptionYzHandlerForGetInfos<ZsYz>() {
					@Override
					protected XbwInfo getInfo(ZsYz current, ZsYz last, Class<ZsYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForWxYz("B", condition, new XbwOptionYzHandlerForGetInfos<WxdsYz>() {
			@Override
			protected XbwInfo getInfo(WxdsYz current, WxdsYz last, Class<WxdsYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		nums.add(calXbwOptionForBsYz("B", condition, new XbwOptionYzHandlerForGetInfos<Bs9qYz>() {
			@Override
			protected XbwInfo getInfo(Bs9qYz current, Bs9qYz last, Class<Bs9qYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		nums.add(calXbwOptionForYz("十二区", "B", condition, TwelveYz.class, TwelveNums.class, repositories.twelveyzRepository,
				new XbwOptionYzHandler<TwelveYz>(), new XbwOptionYzHandlerForGetInfos<TwelveYz>() {
					@Override
					protected XbwInfo getInfo(TwelveYz current, TwelveYz last, Class<TwelveYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("十六区", "B", condition, SlqYz.class, SlqNums.class, repositories.slqyzRepository,
				new XbwOptionYzHandler<SlqYz>(), new XbwOptionYzHandlerForGetInfos<SlqYz>() {
					@Override
					protected XbwInfo getInfo(SlqYz current, SlqYz last, Class<SlqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionBForType4(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("质数", "B", condition, ZsYz.class, ZsNums.class, repositories.zsyzRepository,
				new XbwOptionYzHandler<ZsYz>(), new XbwOptionYzHandlerForGetInfos<ZsYz>() {
					@Override
					protected XbwInfo getInfo(ZsYz current, ZsYz last, Class<ZsYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项2", "B", condition, Zx2Yz.class, Zx2Nums.class, repositories.zx2yzRepository,
				new XbwOptionYzHandler<Zx2Yz>(), new XbwOptionYzHandlerForGetInfos<Zx2Yz>() {
					@Override
					protected XbwInfo getInfo(Zx2Yz current, Zx2Yz last, Class<Zx2Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForBsYz("B", condition, new XbwOptionYzHandlerForGetInfos<Bs9qYz>() {
			@Override
			protected XbwInfo getInfo(Bs9qYz current, Bs9qYz last, Class<Bs9qYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		nums.add(calXbwOptionForYz("十二区", "B", condition, TwelveYz.class, TwelveNums.class, repositories.twelveyzRepository,
				new XbwOptionYzHandler<TwelveYz>(), new XbwOptionYzHandlerForGetInfos<TwelveYz>() {
					@Override
					protected XbwInfo getInfo(TwelveYz current, TwelveYz last, Class<TwelveYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("十六区", "B", condition, SlqYz.class, SlqNums.class, repositories.slqyzRepository,
				new XbwOptionYzHandler<SlqYz>(), new XbwOptionYzHandlerForGetInfos<SlqYz>() {
					@Override
					protected XbwInfo getInfo(SlqYz current, SlqYz last, Class<SlqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionBForType5(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("质数", "B", condition, ZsYz.class, ZsNums.class, repositories.zsyzRepository,
				new XbwOptionYzHandler<ZsYz>(), new XbwOptionYzHandlerForGetInfos<ZsYz>() {
					@Override
					protected XbwInfo getInfo(ZsYz current, ZsYz last, Class<ZsYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForBsYz("B", condition, new XbwOptionYzHandlerForGetInfos<Bs9qYz>() {
			@Override
			protected XbwInfo getInfo(Bs9qYz current, Bs9qYz last, Class<Bs9qYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(0);
			}
		}));
		nums.add(calXbwOptionForYz("十二区", "B", condition, TwelveYz.class, TwelveNums.class, repositories.twelveyzRepository,
				new XbwOptionYzHandler<TwelveYz>(), new XbwOptionYzHandlerForGetInfos<TwelveYz>() {
					@Override
					protected XbwInfo getInfo(TwelveYz current, TwelveYz last, Class<TwelveYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("十六区", "B", condition, SlqYz.class, SlqNums.class, repositories.slqyzRepository,
				new XbwOptionYzHandler<SlqYz>(), new XbwOptionYzHandlerForGetInfos<SlqYz>() {
					@Override
					protected XbwInfo getInfo(SlqYz current, SlqYz last, Class<SlqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionBForType6(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("质数", "B", condition, ZsYz.class, ZsNums.class, repositories.zsyzRepository,
				new XbwOptionYzHandler<ZsYz>(), new XbwOptionYzHandlerForGetInfos<ZsYz>() {
					@Override
					protected XbwInfo getInfo(ZsYz current, ZsYz last, Class<ZsYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForWxYz("B", condition, new XbwOptionYzHandlerForGetInfos<WxdsYz>() {
			@Override
			protected XbwInfo getInfo(WxdsYz current, WxdsYz last, Class<WxdsYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		nums.add(calXbwOptionForBsYz("B", condition, new XbwOptionYzHandlerForGetInfos<Bs9qYz>() {
			@Override
			protected XbwInfo getInfo(Bs9qYz current, Bs9qYz last, Class<Bs9qYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		nums.add(calXbwOptionForYz("十二区", "B", condition, TwelveYz.class, TwelveNums.class, repositories.twelveyzRepository,
				new XbwOptionYzHandler<TwelveYz>(), new XbwOptionYzHandlerForGetInfos<TwelveYz>() {
					@Override
					protected XbwInfo getInfo(TwelveYz current, TwelveYz last, Class<TwelveYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("十六区", "B", condition, SlqYz.class, SlqNums.class, repositories.slqyzRepository,
				new XbwOptionYzHandler<SlqYz>(), new XbwOptionYzHandlerForGetInfos<SlqYz>() {
					@Override
					protected XbwInfo getInfo(SlqYz current, SlqYz last, Class<SlqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionBForType7(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("质数", "B", condition, ZsYz.class, ZsNums.class, repositories.zsyzRepository,
				new XbwOptionYzHandler<ZsYz>(), new XbwOptionYzHandlerForGetInfos<ZsYz>() {
					@Override
					protected XbwInfo getInfo(ZsYz current, ZsYz last, Class<ZsYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项2", "B", condition, Zx2Yz.class, Zx2Nums.class, repositories.zx2yzRepository,
				new XbwOptionYzHandler<Zx2Yz>(), new XbwOptionYzHandlerForGetInfos<Zx2Yz>() {
					@Override
					protected XbwInfo getInfo(Zx2Yz current, Zx2Yz last, Class<Zx2Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForBsYz("B", condition, new XbwOptionYzHandlerForGetInfos<Bs9qYz>() {
			@Override
			protected XbwInfo getInfo(Bs9qYz current, Bs9qYz last, Class<Bs9qYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		nums.add(calXbwOptionForYz("十二区", "B", condition, TwelveYz.class, TwelveNums.class, repositories.twelveyzRepository,
				new XbwOptionYzHandler<TwelveYz>(), new XbwOptionYzHandlerForGetInfos<TwelveYz>() {
					@Override
					protected XbwInfo getInfo(TwelveYz current, TwelveYz last, Class<TwelveYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("十六区", "B", condition, SlqYz.class, SlqNums.class, repositories.slqyzRepository,
				new XbwOptionYzHandler<SlqYz>(), new XbwOptionYzHandlerForGetInfos<SlqYz>() {
					@Override
					protected XbwInfo getInfo(SlqYz current, SlqYz last, Class<SlqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionBForType8(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("质数", "B", condition, ZsYz.class, ZsNums.class, repositories.zsyzRepository,
				new XbwOptionYzHandler<ZsYz>(), new XbwOptionYzHandlerForGetInfos<ZsYz>() {
					@Override
					protected XbwInfo getInfo(ZsYz current, ZsYz last, Class<ZsYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForBsYz("B", condition, new XbwOptionYzHandlerForGetInfos<Bs9qYz>() {
			@Override
			protected XbwInfo getInfo(Bs9qYz current, Bs9qYz last, Class<Bs9qYz> clazz, Class<?> numsClass,
					List<XbwInfo> currentInfos, int len) throws Exception {
				return currentInfos.get(currentInfos.size() - 1);
			}
		}));
		nums.add(calXbwOptionForYz("十二区", "B", condition, TwelveYz.class, TwelveNums.class, repositories.twelveyzRepository,
				new XbwOptionYzHandler<TwelveYz>(), new XbwOptionYzHandlerForGetInfos<TwelveYz>() {
					@Override
					protected XbwInfo getInfo(TwelveYz current, TwelveYz last, Class<TwelveYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("十六区", "B", condition, SlqYz.class, SlqNums.class, repositories.slqyzRepository,
				new XbwOptionYzHandler<SlqYz>(), new XbwOptionYzHandlerForGetInfos<SlqYz>() {
					@Override
					protected XbwInfo getInfo(SlqYz current, SlqYz last, Class<SlqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionCForType1(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("配对", "C", condition, PdYz.class, PdNums.class, repositories.pdyzRepository));
		nums.add(calXbwOptionForYz("七区", "C", condition, QqYz.class, QqNums.class, repositories.qqyzRepository));
		nums.add(calXbwOptionForYz("七位", "C", condition, QiwYz.class, QiwNums.class, repositories.qiwYzRepository));
		nums.add(calXbwOptionForYz("杂项7", "C", condition, Zx7Yz.class, Zx7Nums.class, repositories.zx7yzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionCForType2(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("配对", "C", condition, PdYz.class, PdNums.class, repositories.pdyzRepository));
		nums.add(calXbwOptionForYz("七区", "C", condition, QqYz.class, QqNums.class, repositories.qqyzRepository));
		nums.add(calXbwOptionForYz("杂项7", "C", condition, Zx7Yz.class, Zx7Nums.class, repositories.zx7yzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionCForType3(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("配对", "C", condition, PdYz.class, PdNums.class, repositories.pdyzRepository,
				new XbwOptionYzHandler<PdYz>(), new XbwOptionYzHandlerForGetInfos<PdYz>() {
					@Override
					protected XbwInfo getInfo(PdYz current, PdYz last, Class<PdYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("七位", "C", condition, QiwYz.class, QiwNums.class, repositories.qiwYzRepository,
				new XbwOptionYzHandler<QiwYz>(), new XbwOptionYzHandlerForGetInfos<QiwYz>() {
					@Override
					protected XbwInfo getInfo(QiwYz current, QiwYz last, Class<QiwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项1", "C", condition, Zx1Yz.class, Zx1Nums.class, repositories.zx1yzRepository,
				new XbwOptionYzHandler<Zx1Yz>(), new XbwOptionYzHandlerForGetInfos<Zx1Yz>() {
					@Override
					protected XbwInfo getInfo(Zx1Yz current, Zx1Yz last, Class<Zx1Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项2", "C", condition, Zx2Yz.class, Zx2Nums.class, repositories.zx2yzRepository,
				new XbwOptionYzHandler<Zx2Yz>(), new XbwOptionYzHandlerForGetInfos<Zx2Yz>() {
					@Override
					protected XbwInfo getInfo(Zx2Yz current, Zx2Yz last, Class<Zx2Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionCForType4(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("配对", "C", condition, PdYz.class, PdNums.class, repositories.pdyzRepository,
				new XbwOptionYzHandler<PdYz>(), new XbwOptionYzHandlerForGetInfos<PdYz>() {
					@Override
					protected XbwInfo getInfo(PdYz current, PdYz last, Class<PdYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("七区", "C", condition, QqYz.class, QqNums.class, repositories.qqyzRepository,
				new XbwOptionYzHandler<QqYz>(), new XbwOptionYzHandlerForGetInfos<QqYz>() {
					@Override
					protected XbwInfo getInfo(QqYz current, QqYz last, Class<QqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("七位", "C", condition, QiwYz.class, QiwNums.class, repositories.qiwYzRepository,
				new XbwOptionYzHandler<QiwYz>(), new XbwOptionYzHandlerForGetInfos<QiwYz>() {
					@Override
					protected XbwInfo getInfo(QiwYz current, QiwYz last, Class<QiwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项7", "C", condition, Zx7Yz.class, Zx7Nums.class, repositories.zx7yzRepository,
				new XbwOptionYzHandler<Zx7Yz>(), new XbwOptionYzHandlerForGetInfos<Zx7Yz>() {
					@Override
					protected XbwInfo getInfo(Zx7Yz current, Zx7Yz last, Class<Zx7Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionCForType5(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("配对", "C", condition, PdYz.class, PdNums.class, repositories.pdyzRepository,
				new XbwOptionYzHandler<PdYz>(), new XbwOptionYzHandlerForGetInfos<PdYz>() {
					@Override
					protected XbwInfo getInfo(PdYz current, PdYz last, Class<PdYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("七区", "C", condition, QqYz.class, QqNums.class, repositories.qqyzRepository,
				new XbwOptionYzHandler<QqYz>(), new XbwOptionYzHandlerForGetInfos<QqYz>() {
					@Override
					protected XbwInfo getInfo(QqYz current, QqYz last, Class<QqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项7", "C", condition, Zx7Yz.class, Zx7Nums.class, repositories.zx7yzRepository,
				new XbwOptionYzHandler<Zx7Yz>(), new XbwOptionYzHandlerForGetInfos<Zx7Yz>() {
					@Override
					protected XbwInfo getInfo(Zx7Yz current, Zx7Yz last, Class<Zx7Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionCForType6(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("配对", "C", condition, PdYz.class, PdNums.class, repositories.pdyzRepository,
				new XbwOptionYzHandler<PdYz>(), new XbwOptionYzHandlerForGetInfos<PdYz>() {
					@Override
					protected XbwInfo getInfo(PdYz current, PdYz last, Class<PdYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("七位", "C", condition, QiwYz.class, QiwNums.class, repositories.qiwYzRepository,
				new XbwOptionYzHandler<QiwYz>(), new XbwOptionYzHandlerForGetInfos<QiwYz>() {
					@Override
					protected XbwInfo getInfo(QiwYz current, QiwYz last, Class<QiwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项1", "C", condition, Zx1Yz.class, Zx1Nums.class, repositories.zx1yzRepository,
				new XbwOptionYzHandler<Zx1Yz>(), new XbwOptionYzHandlerForGetInfos<Zx1Yz>() {
					@Override
					protected XbwInfo getInfo(Zx1Yz current, Zx1Yz last, Class<Zx1Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项2", "C", condition, Zx2Yz.class, Zx2Nums.class, repositories.zx2yzRepository,
				new XbwOptionYzHandler<Zx2Yz>(), new XbwOptionYzHandlerForGetInfos<Zx2Yz>() {
					@Override
					protected XbwInfo getInfo(Zx2Yz current, Zx2Yz last, Class<Zx2Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionCForType7(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("配对", "C", condition, PdYz.class, PdNums.class, repositories.pdyzRepository,
				new XbwOptionYzHandler<PdYz>(), new XbwOptionYzHandlerForGetInfos<PdYz>() {
					@Override
					protected XbwInfo getInfo(PdYz current, PdYz last, Class<PdYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("七区", "C", condition, QqYz.class, QqNums.class, repositories.qqyzRepository,
				new XbwOptionYzHandler<QqYz>(), new XbwOptionYzHandlerForGetInfos<QqYz>() {
					@Override
					protected XbwInfo getInfo(QqYz current, QqYz last, Class<QqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("七位", "C", condition, QiwYz.class, QiwNums.class, repositories.qiwYzRepository,
				new XbwOptionYzHandler<QiwYz>(), new XbwOptionYzHandlerForGetInfos<QiwYz>() {
					@Override
					protected XbwInfo getInfo(QiwYz current, QiwYz last, Class<QiwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项7", "C", condition, Zx7Yz.class, Zx7Nums.class, repositories.zx7yzRepository,
				new XbwOptionYzHandler<Zx7Yz>(), new XbwOptionYzHandlerForGetInfos<Zx7Yz>() {
					@Override
					protected XbwInfo getInfo(Zx7Yz current, Zx7Yz last, Class<Zx7Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionCForType8(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("配对", "C", condition, PdYz.class, PdNums.class, repositories.pdyzRepository,
				new XbwOptionYzHandler<PdYz>(), new XbwOptionYzHandlerForGetInfos<PdYz>() {
					@Override
					protected XbwInfo getInfo(PdYz current, PdYz last, Class<PdYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("七区", "C", condition, QqYz.class, QqNums.class, repositories.qqyzRepository,
				new XbwOptionYzHandler<QqYz>(), new XbwOptionYzHandlerForGetInfos<QqYz>() {
					@Override
					protected XbwInfo getInfo(QqYz current, QqYz last, Class<QqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项7", "C", condition, Zx7Yz.class, Zx7Nums.class, repositories.zx7yzRepository,
				new XbwOptionYzHandler<Zx7Yz>(), new XbwOptionYzHandlerForGetInfos<Zx7Yz>() {
					@Override
					protected XbwInfo getInfo(Zx7Yz current, Zx7Yz last, Class<Zx7Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionDForType1(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("首位", "D", condition, SwYz.class, SwNums.class, repositories.swyzRepository));
		nums.add(calXbwOptionForYz("杂项4", "D", condition, Zx4Yz.class, Zx4Nums.class, repositories.zx4yzRepository));
		nums.add(calXbwOptionForYz("杂项9", "D", condition, Zx9Yz.class, Zx9Nums.class, repositories.zx9yzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionDForType2(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("首位", "D", condition, SwYz.class, SwNums.class, repositories.swyzRepository));
		nums.add(calXbwOptionForYz("杂项1", "D", condition, Zx1Yz.class, Zx1Nums.class, repositories.zx1yzRepository));
		nums.add(calXbwOptionForYz("杂项9", "D", condition, Zx9Yz.class, Zx9Nums.class, repositories.zx9yzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionDForType3(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("七区", "D", condition, QqYz.class, QqNums.class, repositories.qqyzRepository,
				new XbwOptionYzHandler<QqYz>(), new XbwOptionYzHandlerForGetInfos<QqYz>() {
					@Override
					protected XbwInfo getInfo(QqYz current, QqYz last, Class<QqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项4", "D", condition, Zx4Yz.class, Zx4Nums.class, repositories.zx4yzRepository,
				new XbwOptionYzHandler<Zx4Yz>(), new XbwOptionYzHandlerForGetInfos<Zx4Yz>() {
					@Override
					protected XbwInfo getInfo(Zx4Yz current, Zx4Yz last, Class<Zx4Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项7", "D", condition, Zx7Yz.class, Zx7Nums.class, repositories.zx7yzRepository,
				new XbwOptionYzHandler<Zx7Yz>(), new XbwOptionYzHandlerForGetInfos<Zx7Yz>() {
					@Override
					protected XbwInfo getInfo(Zx7Yz current, Zx7Yz last, Class<Zx7Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项9", "D", condition, Zx9Yz.class, Zx9Nums.class, repositories.zx9yzRepository,
				new XbwOptionYzHandler<Zx9Yz>(), new XbwOptionYzHandlerForGetInfos<Zx9Yz>() {
					@Override
					protected XbwInfo getInfo(Zx9Yz current, Zx9Yz last, Class<Zx9Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionDForType4(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("首位", "D", condition, SwYz.class, SwNums.class, repositories.swyzRepository,
				new XbwOptionYzHandler<SwYz>(), new XbwOptionYzHandlerForGetInfos<SwYz>() {
					@Override
					protected XbwInfo getInfo(SwYz current, SwYz last, Class<SwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项4", "D", condition, Zx4Yz.class, Zx4Nums.class, repositories.zx4yzRepository,
				new XbwOptionYzHandler<Zx4Yz>(), new XbwOptionYzHandlerForGetInfos<Zx4Yz>() {
					@Override
					protected XbwInfo getInfo(Zx4Yz current, Zx4Yz last, Class<Zx4Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项9", "D", condition, Zx9Yz.class, Zx9Nums.class, repositories.zx9yzRepository,
				new XbwOptionYzHandler<Zx9Yz>(), new XbwOptionYzHandlerForGetInfos<Zx9Yz>() {
					@Override
					protected XbwInfo getInfo(Zx9Yz current, Zx9Yz last, Class<Zx9Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionDForType5(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("首位", "D", condition, SwYz.class, SwNums.class, repositories.swyzRepository,
				new XbwOptionYzHandler<SwYz>(), new XbwOptionYzHandlerForGetInfos<SwYz>() {
					@Override
					protected XbwInfo getInfo(SwYz current, SwYz last, Class<SwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项1", "D", condition, Zx1Yz.class, Zx1Nums.class, repositories.zx1yzRepository,
				new XbwOptionYzHandler<Zx1Yz>(), new XbwOptionYzHandlerForGetInfos<Zx1Yz>() {
					@Override
					protected XbwInfo getInfo(Zx1Yz current, Zx1Yz last, Class<Zx1Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项9", "D", condition, Zx9Yz.class, Zx9Nums.class, repositories.zx9yzRepository,
				new XbwOptionYzHandler<Zx9Yz>(), new XbwOptionYzHandlerForGetInfos<Zx9Yz>() {
					@Override
					protected XbwInfo getInfo(Zx9Yz current, Zx9Yz last, Class<Zx9Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionDForType6(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("七区", "D", condition, QqYz.class, QqNums.class, repositories.qqyzRepository,
				new XbwOptionYzHandler<QqYz>(), new XbwOptionYzHandlerForGetInfos<QqYz>() {
					@Override
					protected XbwInfo getInfo(QqYz current, QqYz last, Class<QqYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项4", "D", condition, Zx4Yz.class, Zx4Nums.class, repositories.zx4yzRepository,
				new XbwOptionYzHandler<Zx4Yz>(), new XbwOptionYzHandlerForGetInfos<Zx4Yz>() {
					@Override
					protected XbwInfo getInfo(Zx4Yz current, Zx4Yz last, Class<Zx4Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项7", "D", condition, Zx7Yz.class, Zx7Nums.class, repositories.zx7yzRepository,
				new XbwOptionYzHandler<Zx7Yz>(), new XbwOptionYzHandlerForGetInfos<Zx7Yz>() {
					@Override
					protected XbwInfo getInfo(Zx7Yz current, Zx7Yz last, Class<Zx7Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项9", "D", condition, Zx9Yz.class, Zx9Nums.class, repositories.zx9yzRepository,
				new XbwOptionYzHandler<Zx9Yz>(), new XbwOptionYzHandlerForGetInfos<Zx9Yz>() {
					@Override
					protected XbwInfo getInfo(Zx9Yz current, Zx9Yz last, Class<Zx9Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionDForType7(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("首位", "D", condition, SwYz.class, SwNums.class, repositories.swyzRepository,
				new XbwOptionYzHandler<SwYz>(), new XbwOptionYzHandlerForGetInfos<SwYz>() {
					@Override
					protected XbwInfo getInfo(SwYz current, SwYz last, Class<SwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项4", "D", condition, Zx4Yz.class, Zx4Nums.class, repositories.zx4yzRepository,
				new XbwOptionYzHandler<Zx4Yz>(), new XbwOptionYzHandlerForGetInfos<Zx4Yz>() {
					@Override
					protected XbwInfo getInfo(Zx4Yz current, Zx4Yz last, Class<Zx4Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项9", "D", condition, Zx9Yz.class, Zx9Nums.class, repositories.zx9yzRepository,
				new XbwOptionYzHandler<Zx9Yz>(), new XbwOptionYzHandlerForGetInfos<Zx9Yz>() {
					@Override
					protected XbwInfo getInfo(Zx9Yz current, Zx9Yz last, Class<Zx9Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionDForType8(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForYz("首位", "D", condition, SwYz.class, SwNums.class, repositories.swyzRepository,
				new XbwOptionYzHandler<SwYz>(), new XbwOptionYzHandlerForGetInfos<SwYz>() {
					@Override
					protected XbwInfo getInfo(SwYz current, SwYz last, Class<SwYz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项1", "D", condition, Zx1Yz.class, Zx1Nums.class, repositories.zx1yzRepository,
				new XbwOptionYzHandler<Zx1Yz>(), new XbwOptionYzHandlerForGetInfos<Zx1Yz>() {
					@Override
					protected XbwInfo getInfo(Zx1Yz current, Zx1Yz last, Class<Zx1Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项9", "D", condition, Zx9Yz.class, Zx9Nums.class, repositories.zx9yzRepository,
				new XbwOptionYzHandler<Zx9Yz>(), new XbwOptionYzHandlerForGetInfos<Zx9Yz>() {
					@Override
					protected XbwInfo getInfo(Zx9Yz current, Zx9Yz last, Class<Zx9Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionEForType1(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxzf("E", condition, new XbwOptionYzHandlerForGetPos<SxZfYz2>()));
		nums.add(calXbwOptionForLhzf("E", condition, new XbwOptionYzHandlerForGetPos<LhZfYz>()));
		nums.add(calXbwOptionForMwzf("E", condition, new XbwOptionYzHandlerForGetPos<MwZfYz>()));
		nums.add(calXbwOptionForYz("杂项3", "E", condition, Zx3Yz.class, Zx3Nums.class, repositories.zx3yzRepository));
		nums.add(calXbwOptionForFdzf("E", condition, new XbwOptionYzHandlerForGetPos<Tm12FdZfYz>()));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionEForType2(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxzf("E", condition, new XbwOptionYzHandlerForGetPos<SxZfYz2>()));
		nums.add(calXbwOptionForLhzf("E", condition, new XbwOptionYzHandlerForGetPos<LhZfYz>()));
		nums.add(calXbwOptionForMwzf("E", condition, new XbwOptionYzHandlerForGetPos<MwZfYz>()));
		nums.add(calXbwOptionForFdzf("E", condition, new XbwOptionYzHandlerForGetPos<Tm12FdZfYz>()));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionEForType3(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxzf("E", condition, new XbwOptionYzHandlerForGetPos<SxZfYz2>() {
			@Override
			protected Integer getPos(SxZfYz2 current, SxZfYz2 last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForLhzf("E", condition, new XbwOptionYzHandlerForGetPos<LhZfYz>() {
			@Override
			protected Integer getPos(LhZfYz current, LhZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForMwzf("E", condition, new XbwOptionYzHandlerForGetPos<MwZfYz>() {
			@Override
			protected Integer getPos(MwZfYz current, MwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForDszf("E", condition, new XbwOptionYzHandlerForGetPos<DsZfYz>() {
			@Override
			protected Integer getPos(DsZfYz current, DsZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForFdzf("E", condition, new XbwOptionYzHandlerForGetPos<Tm12FdZfYz>() {
			@Override
			protected Integer getPos(Tm12FdZfYz current, Tm12FdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionEForType4(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxzf("E", condition, new XbwOptionYzHandlerForGetPos<SxZfYz2>() {
			@Override
			protected Integer getPos(SxZfYz2 current, SxZfYz2 last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForLhzf("E", condition, new XbwOptionYzHandlerForGetPos<LhZfYz>() {
			@Override
			protected Integer getPos(LhZfYz current, LhZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForMwzf("E", condition, new XbwOptionYzHandlerForGetPos<MwZfYz>() {
			@Override
			protected Integer getPos(MwZfYz current, MwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项3", "E", condition, Zx3Yz.class, Zx3Nums.class, repositories.zx3yzRepository,
				new XbwOptionYzHandler<Zx3Yz>(), new XbwOptionYzHandlerForGetInfos<Zx3Yz>() {
					@Override
					protected XbwInfo getInfo(Zx3Yz current, Zx3Yz last, Class<Zx3Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForFdzf("E", condition, new XbwOptionYzHandlerForGetPos<Tm12FdZfYz>() {
			@Override
			protected Integer getPos(Tm12FdZfYz current, Tm12FdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionEForType5(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxzf("E", condition, new XbwOptionYzHandlerForGetPos<SxZfYz2>() {
			@Override
			protected Integer getPos(SxZfYz2 current, SxZfYz2 last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForLhzf("E", condition, new XbwOptionYzHandlerForGetPos<LhZfYz>() {
			@Override
			protected Integer getPos(LhZfYz current, LhZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForMwzf("E", condition, new XbwOptionYzHandlerForGetPos<MwZfYz>() {
			@Override
			protected Integer getPos(MwZfYz current, MwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForFdzf("E", condition, new XbwOptionYzHandlerForGetPos<Tm12FdZfYz>() {
			@Override
			protected Integer getPos(Tm12FdZfYz current, Tm12FdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionEForType6(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxzf("E", condition, new XbwOptionYzHandlerForGetPos<SxZfYz2>() {
			@Override
			protected Integer getPos(SxZfYz2 current, SxZfYz2 last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForLhzf("E", condition, new XbwOptionYzHandlerForGetPos<LhZfYz>() {
			@Override
			protected Integer getPos(LhZfYz current, LhZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForMwzf("E", condition, new XbwOptionYzHandlerForGetPos<MwZfYz>() {
			@Override
			protected Integer getPos(MwZfYz current, MwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForDszf("E", condition, new XbwOptionYzHandlerForGetPos<DsZfYz>() {
			@Override
			protected Integer getPos(DsZfYz current, DsZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForFdzf("E", condition, new XbwOptionYzHandlerForGetPos<Tm12FdZfYz>() {
			@Override
			protected Integer getPos(Tm12FdZfYz current, Tm12FdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionEForType7(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxzf("E", condition, new XbwOptionYzHandlerForGetPos<SxZfYz2>() {
			@Override
			protected Integer getPos(SxZfYz2 current, SxZfYz2 last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 10).obj;
			}
		}));
		nums.add(calXbwOptionForLhzf("E", condition, new XbwOptionYzHandlerForGetPos<LhZfYz>() {
			@Override
			protected Integer getPos(LhZfYz current, LhZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForMwzf("E", condition, new XbwOptionYzHandlerForGetPos<MwZfYz>() {
			@Override
			protected Integer getPos(MwZfYz current, MwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项3", "E", condition, Zx3Yz.class, Zx3Nums.class, repositories.zx3yzRepository,
				new XbwOptionYzHandler<Zx3Yz>(), new XbwOptionYzHandlerForGetInfos<Zx3Yz>() {
					@Override
					protected XbwInfo getInfo(Zx3Yz current, Zx3Yz last, Class<Zx3Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForFdzf("E", condition, new XbwOptionYzHandlerForGetPos<Tm12FdZfYz>() {
			@Override
			protected Integer getPos(Tm12FdZfYz current, Tm12FdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionEForType8(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForSxzf("E", condition, new XbwOptionYzHandlerForGetPos<SxZfYz2>() {
			@Override
			protected Integer getPos(SxZfYz2 current, SxZfYz2 last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForLhzf("E", condition, new XbwOptionYzHandlerForGetPos<LhZfYz>() {
			@Override
			protected Integer getPos(LhZfYz current, LhZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForMwzf("E", condition, new XbwOptionYzHandlerForGetPos<MwZfYz>() {
			@Override
			protected Integer getPos(MwZfYz current, MwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForFdzf("E", condition, new XbwOptionYzHandlerForGetPos<Tm12FdZfYz>() {
			@Override
			protected Integer getPos(Tm12FdZfYz current, Tm12FdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionFForType1(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForZszf("F", condition, new XbwOptionYzHandlerForGetPos<ZsZfYz>()));
		nums.add(calXbwOptionForYz("杂项5", "F", condition, Zx5Yz.class, Zx5Nums.class, repositories.zx5yzRepository));
		nums.add(calXbwOptionForBszf("F", condition, new XbwOptionYzHandlerForGetPos<Bs9qZfYz>()));
		nums.add(calXbwOptionForTwelvezf("F", condition, new XbwOptionYzHandlerForGetPos<TwelveZfYz>()));
		nums.add(calXbwOptionForSlqzf("F", condition, new XbwOptionYzHandlerForGetPos<SlqZfYz>()));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionFForType2(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForZszf("F", condition, new XbwOptionYzHandlerForGetPos<ZsZfYz>()));
		nums.add(calXbwOptionForBszf("F", condition, new XbwOptionYzHandlerForGetPos<Bs9qZfYz>()));
		nums.add(calXbwOptionForTwelvezf("F", condition, new XbwOptionYzHandlerForGetPos<TwelveZfYz>()));
		nums.add(calXbwOptionForSlqzf("F", condition, new XbwOptionYzHandlerForGetPos<SlqZfYz>()));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionFForType3(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForZszf("F", condition, new XbwOptionYzHandlerForGetPos<ZsZfYz>() {
			@Override
			protected Integer getPos(ZsZfYz current, ZsZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForWxdszf("F", condition, new XbwOptionYzHandlerForGetPos<WxdsZfYz>() {
			@Override
			protected Integer getPos(WxdsZfYz current, WxdsZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForBszf("F", condition, new XbwOptionYzHandlerForGetPos<Bs9qZfYz>() {
			@Override
			protected Integer getPos(Bs9qZfYz current, Bs9qZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForTwelvezf("F", condition, new XbwOptionYzHandlerForGetPos<TwelveZfYz>() {
			@Override
			protected Integer getPos(TwelveZfYz current, TwelveZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForSlqzf("F", condition, new XbwOptionYzHandlerForGetPos<SlqZfYz>() {
			@Override
			protected Integer getPos(SlqZfYz current, SlqZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionFForType4(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForZszf("F", condition, new XbwOptionYzHandlerForGetPos<ZsZfYz>() {
			@Override
			protected Integer getPos(ZsZfYz current, ZsZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项5", "F", condition, Zx5Yz.class, Zx5Nums.class, repositories.zx5yzRepository,
				new XbwOptionYzHandler<Zx5Yz>(), new XbwOptionYzHandlerForGetInfos<Zx5Yz>() {
					@Override
					protected XbwInfo getInfo(Zx5Yz current, Zx5Yz last, Class<Zx5Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForBszf("F", condition, new XbwOptionYzHandlerForGetPos<Bs9qZfYz>() {
			@Override
			protected Integer getPos(Bs9qZfYz current, Bs9qZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForTwelvezf("F", condition, new XbwOptionYzHandlerForGetPos<TwelveZfYz>() {
			@Override
			protected Integer getPos(TwelveZfYz current, TwelveZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForSlqzf("F", condition, new XbwOptionYzHandlerForGetPos<SlqZfYz>() {
			@Override
			protected Integer getPos(SlqZfYz current, SlqZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionFForType5(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForZszf("F", condition, new XbwOptionYzHandlerForGetPos<ZsZfYz>() {
			@Override
			protected Integer getPos(ZsZfYz current, ZsZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForBszf("F", condition, new XbwOptionYzHandlerForGetPos<Bs9qZfYz>() {
			@Override
			protected Integer getPos(Bs9qZfYz current, Bs9qZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForTwelvezf("F", condition, new XbwOptionYzHandlerForGetPos<TwelveZfYz>() {
			@Override
			protected Integer getPos(TwelveZfYz current, TwelveZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForSlqzf("F", condition, new XbwOptionYzHandlerForGetPos<SlqZfYz>() {
			@Override
			protected Integer getPos(SlqZfYz current, SlqZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionFForType6(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForZszf("F", condition, new XbwOptionYzHandlerForGetPos<ZsZfYz>() {
			@Override
			protected Integer getPos(ZsZfYz current, ZsZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForWxdszf("F", condition, new XbwOptionYzHandlerForGetPos<WxdsZfYz>() {
			@Override
			protected Integer getPos(WxdsZfYz current, WxdsZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForBszf("F", condition, new XbwOptionYzHandlerForGetPos<Bs9qZfYz>() {
			@Override
			protected Integer getPos(Bs9qZfYz current, Bs9qZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForTwelvezf("F", condition, new XbwOptionYzHandlerForGetPos<TwelveZfYz>() {
			@Override
			protected Integer getPos(TwelveZfYz current, TwelveZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForSlqzf("F", condition, new XbwOptionYzHandlerForGetPos<SlqZfYz>() {
			@Override
			protected Integer getPos(SlqZfYz current, SlqZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionFForType7(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForZszf("F", condition, new XbwOptionYzHandlerForGetPos<ZsZfYz>() {
			@Override
			protected Integer getPos(ZsZfYz current, ZsZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项5", "F", condition, Zx5Yz.class, Zx5Nums.class, repositories.zx5yzRepository,
				new XbwOptionYzHandler<Zx5Yz>(), new XbwOptionYzHandlerForGetInfos<Zx5Yz>() {
					@Override
					protected XbwInfo getInfo(Zx5Yz current, Zx5Yz last, Class<Zx5Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForBszf("F", condition, new XbwOptionYzHandlerForGetPos<Bs9qZfYz>() {
			@Override
			protected Integer getPos(Bs9qZfYz current, Bs9qZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForTwelvezf("F", condition, new XbwOptionYzHandlerForGetPos<TwelveZfYz>() {
			@Override
			protected Integer getPos(TwelveZfYz current, TwelveZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForSlqzf("F", condition, new XbwOptionYzHandlerForGetPos<SlqZfYz>() {
			@Override
			protected Integer getPos(SlqZfYz current, SlqZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionFForType8(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForZszf("F", condition, new XbwOptionYzHandlerForGetPos<ZsZfYz>() {
			@Override
			protected Integer getPos(ZsZfYz current, ZsZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForBszf("F", condition, new XbwOptionYzHandlerForGetPos<Bs9qZfYz>() {
			@Override
			protected Integer getPos(Bs9qZfYz current, Bs9qZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForTwelvezf("F", condition, new XbwOptionYzHandlerForGetPos<TwelveZfYz>() {
			@Override
			protected Integer getPos(TwelveZfYz current, TwelveZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForSlqzf("F", condition, new XbwOptionYzHandlerForGetPos<SlqZfYz>() {
			@Override
			protected Integer getPos(SlqZfYz current, SlqZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionGForType1(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForPdzf("G", condition, new XbwOptionYzHandlerForGetPos<PdZfYz>()));
		nums.add(calXbwOptionForQqzf("G", condition, new XbwOptionYzHandlerForGetPos<QqZfYz>()));
		nums.add(calXbwOptionForQiwzf("G", condition, new XbwOptionYzHandlerForGetPos<QiwZfYz>()));
		nums.add(calXbwOptionForYz("杂项8", "G", condition, Zx8Yz.class, Zx8Nums.class, repositories.zx8yzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionGForType2(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForPdzf("G", condition, new XbwOptionYzHandlerForGetPos<PdZfYz>()));
		nums.add(calXbwOptionForQiwzf("G", condition, new XbwOptionYzHandlerForGetPos<QiwZfYz>()));
		nums.add(calXbwOptionForYz("杂项8", "G", condition, Zx8Yz.class, Zx8Nums.class, repositories.zx8yzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionGForType3(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForPdzf("G", condition, new XbwOptionYzHandlerForGetPos<PdZfYz>() {
			@Override
			protected Integer getPos(PdZfYz current, PdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForQiwzf("G", condition, new XbwOptionYzHandlerForGetPos<QiwZfYz>() {
			@Override
			protected Integer getPos(QiwZfYz current, QiwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项3", "G", condition, Zx3Yz.class, Zx3Nums.class, repositories.zx3yzRepository,
				new XbwOptionYzHandler<Zx3Yz>(), new XbwOptionYzHandlerForGetInfos<Zx3Yz>() {
					@Override
					protected XbwInfo getInfo(Zx3Yz current, Zx3Yz last, Class<Zx3Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项5", "G", condition, Zx5Yz.class, Zx5Nums.class, repositories.zx5yzRepository,
				new XbwOptionYzHandler<Zx5Yz>(), new XbwOptionYzHandlerForGetInfos<Zx5Yz>() {
					@Override
					protected XbwInfo getInfo(Zx5Yz current, Zx5Yz last, Class<Zx5Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionGForType4(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForPdzf("G", condition, new XbwOptionYzHandlerForGetPos<PdZfYz>() {
			@Override
			protected Integer getPos(PdZfYz current, PdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForQqzf("G", condition, new XbwOptionYzHandlerForGetPos<QqZfYz>() {
			@Override
			protected Integer getPos(QqZfYz current, QqZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForQiwzf("G", condition, new XbwOptionYzHandlerForGetPos<QiwZfYz>() {
			@Override
			protected Integer getPos(QiwZfYz current, QiwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项8", "G", condition, Zx8Yz.class, Zx8Nums.class, repositories.zx8yzRepository,
				new XbwOptionYzHandler<Zx8Yz>(), new XbwOptionYzHandlerForGetInfos<Zx8Yz>() {
					@Override
					protected XbwInfo getInfo(Zx8Yz current, Zx8Yz last, Class<Zx8Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionGForType5(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForPdzf("G", condition, new XbwOptionYzHandlerForGetPos<PdZfYz>() {
			@Override
			protected Integer getPos(PdZfYz current, PdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForQiwzf("G", condition, new XbwOptionYzHandlerForGetPos<QiwZfYz>() {
			@Override
			protected Integer getPos(QiwZfYz current, QiwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项8", "G", condition, Zx8Yz.class, Zx8Nums.class, repositories.zx8yzRepository,
				new XbwOptionYzHandler<Zx8Yz>(), new XbwOptionYzHandlerForGetInfos<Zx8Yz>() {
					@Override
					protected XbwInfo getInfo(Zx8Yz current, Zx8Yz last, Class<Zx8Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionGForType6(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForPdzf("G", condition, new XbwOptionYzHandlerForGetPos<PdZfYz>() {
			@Override
			protected Integer getPos(PdZfYz current, PdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForQiwzf("G", condition, new XbwOptionYzHandlerForGetPos<QiwZfYz>() {
			@Override
			protected Integer getPos(QiwZfYz current, QiwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项3", "G", condition, Zx3Yz.class, Zx3Nums.class, repositories.zx3yzRepository,
				new XbwOptionYzHandler<Zx3Yz>(), new XbwOptionYzHandlerForGetInfos<Zx3Yz>() {
					@Override
					protected XbwInfo getInfo(Zx3Yz current, Zx3Yz last, Class<Zx3Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项5", "G", condition, Zx5Yz.class, Zx5Nums.class, repositories.zx5yzRepository,
				new XbwOptionYzHandler<Zx5Yz>(), new XbwOptionYzHandlerForGetInfos<Zx5Yz>() {
					@Override
					protected XbwInfo getInfo(Zx5Yz current, Zx5Yz last, Class<Zx5Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionGForType7(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForPdzf("G", condition, new XbwOptionYzHandlerForGetPos<PdZfYz>() {
			@Override
			protected Integer getPos(PdZfYz current, PdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForQqzf("G", condition, new XbwOptionYzHandlerForGetPos<QqZfYz>() {
			@Override
			protected Integer getPos(QqZfYz current, QqZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForQiwzf("G", condition, new XbwOptionYzHandlerForGetPos<QiwZfYz>() {
			@Override
			protected Integer getPos(QiwZfYz current, QiwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项8", "G", condition, Zx8Yz.class, Zx8Nums.class, repositories.zx8yzRepository,
				new XbwOptionYzHandler<Zx8Yz>(), new XbwOptionYzHandlerForGetInfos<Zx8Yz>() {
					@Override
					protected XbwInfo getInfo(Zx8Yz current, Zx8Yz last, Class<Zx8Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionGForType8(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForPdzf("G", condition, new XbwOptionYzHandlerForGetPos<PdZfYz>() {
			@Override
			protected Integer getPos(PdZfYz current, PdZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForQiwzf("G", condition, new XbwOptionYzHandlerForGetPos<QiwZfYz>() {
			@Override
			protected Integer getPos(QiwZfYz current, QiwZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项8", "G", condition, Zx8Yz.class, Zx8Nums.class, repositories.zx8yzRepository,
				new XbwOptionYzHandler<Zx8Yz>(), new XbwOptionYzHandlerForGetInfos<Zx8Yz>() {
					@Override
					protected XbwInfo getInfo(Zx8Yz current, Zx8Yz last, Class<Zx8Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionHForType1(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForWxzf("H", condition, new XbwOptionYzHandlerForGetPos<WxZfYz>()));
		nums.add(calXbwOptionForYz("杂项6", "H", condition, Zx6Yz.class, Zx6Nums.class, repositories.zx6yzRepository));
		nums.add(calXbwOptionForYz("杂项10", "H", condition, Zx10Yz.class, Zx10Nums.class, repositories.zx10yzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionHForType2(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForWxzf("H", condition, new XbwOptionYzHandlerForGetPos<WxZfYz>()));
		nums.add(calXbwOptionForYz("杂项5", "H", condition, Zx5Yz.class, Zx5Nums.class, repositories.zx5yzRepository));
		nums.add(calXbwOptionForYz("杂项10", "H", condition, Zx10Yz.class, Zx10Nums.class, repositories.zx10yzRepository));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionHForType3(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForQqzf("H", condition, new XbwOptionYzHandlerForGetPos<QqZfYz>() {
			@Override
			protected Integer getPos(QqZfYz current, QqZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项6", "H", condition, Zx6Yz.class, Zx6Nums.class, repositories.zx6yzRepository,
				new XbwOptionYzHandler<Zx6Yz>(), new XbwOptionYzHandlerForGetInfos<Zx6Yz>() {
					@Override
					protected XbwInfo getInfo(Zx6Yz current, Zx6Yz last, Class<Zx6Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项8", "H", condition, Zx8Yz.class, Zx8Nums.class, repositories.zx8yzRepository,
				new XbwOptionYzHandler<Zx8Yz>(), new XbwOptionYzHandlerForGetInfos<Zx8Yz>() {
					@Override
					protected XbwInfo getInfo(Zx8Yz current, Zx8Yz last, Class<Zx8Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项10", "H", condition, Zx10Yz.class, Zx10Nums.class, repositories.zx10yzRepository,
				new XbwOptionYzHandler<Zx10Yz>(), new XbwOptionYzHandlerForGetInfos<Zx10Yz>() {
					@Override
					protected XbwInfo getInfo(Zx10Yz current, Zx10Yz last, Class<Zx10Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionHForType4(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForWxzf("H", condition, new XbwOptionYzHandlerForGetPos<WxZfYz>() {
			@Override
			protected Integer getPos(WxZfYz current, WxZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项6", "H", condition, Zx6Yz.class, Zx6Nums.class, repositories.zx6yzRepository,
				new XbwOptionYzHandler<Zx6Yz>(), new XbwOptionYzHandlerForGetInfos<Zx6Yz>() {
					@Override
					protected XbwInfo getInfo(Zx6Yz current, Zx6Yz last, Class<Zx6Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项10", "H", condition, Zx10Yz.class, Zx10Nums.class, repositories.zx10yzRepository,
				new XbwOptionYzHandler<Zx10Yz>(), new XbwOptionYzHandlerForGetInfos<Zx10Yz>() {
					@Override
					protected XbwInfo getInfo(Zx10Yz current, Zx10Yz last, Class<Zx10Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionHForType5(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForWxzf("H", condition, new XbwOptionYzHandlerForGetPos<WxZfYz>() {
			@Override
			protected Integer getPos(WxZfYz current, WxZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(0).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项5", "H", condition, Zx5Yz.class, Zx5Nums.class, repositories.zx5yzRepository,
				new XbwOptionYzHandler<Zx5Yz>(), new XbwOptionYzHandlerForGetInfos<Zx5Yz>() {
					@Override
					protected XbwInfo getInfo(Zx5Yz current, Zx5Yz last, Class<Zx5Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		nums.add(calXbwOptionForYz("杂项10", "H", condition, Zx10Yz.class, Zx10Nums.class, repositories.zx10yzRepository,
				new XbwOptionYzHandler<Zx10Yz>(), new XbwOptionYzHandlerForGetInfos<Zx10Yz>() {
					@Override
					protected XbwInfo getInfo(Zx10Yz current, Zx10Yz last, Class<Zx10Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(0);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionHForType6(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForQqzf("H", condition, new XbwOptionYzHandlerForGetPos<QqZfYz>() {
			@Override
			protected Integer getPos(QqZfYz current, QqZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项6", "H", condition, Zx6Yz.class, Zx6Nums.class, repositories.zx6yzRepository,
				new XbwOptionYzHandler<Zx6Yz>(), new XbwOptionYzHandlerForGetInfos<Zx6Yz>() {
					@Override
					protected XbwInfo getInfo(Zx6Yz current, Zx6Yz last, Class<Zx6Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项8", "H", condition, Zx8Yz.class, Zx8Nums.class, repositories.zx8yzRepository,
				new XbwOptionYzHandler<Zx8Yz>(), new XbwOptionYzHandlerForGetInfos<Zx8Yz>() {
					@Override
					protected XbwInfo getInfo(Zx8Yz current, Zx8Yz last, Class<Zx8Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项10", "H", condition, Zx10Yz.class, Zx10Nums.class, repositories.zx10yzRepository,
				new XbwOptionYzHandler<Zx10Yz>(), new XbwOptionYzHandlerForGetInfos<Zx10Yz>() {
					@Override
					protected XbwInfo getInfo(Zx10Yz current, Zx10Yz last, Class<Zx10Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionHForType7(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForWxzf("H", condition, new XbwOptionYzHandlerForGetPos<WxZfYz>() {
			@Override
			protected Integer getPos(WxZfYz current, WxZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项6", "H", condition, Zx6Yz.class, Zx6Nums.class, repositories.zx6yzRepository,
				new XbwOptionYzHandler<Zx6Yz>(), new XbwOptionYzHandlerForGetInfos<Zx6Yz>() {
					@Override
					protected XbwInfo getInfo(Zx6Yz current, Zx6Yz last, Class<Zx6Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项10", "H", condition, Zx10Yz.class, Zx10Nums.class, repositories.zx10yzRepository,
				new XbwOptionYzHandler<Zx10Yz>(), new XbwOptionYzHandlerForGetInfos<Zx10Yz>() {
					@Override
					protected XbwInfo getInfo(Zx10Yz current, Zx10Yz last, Class<Zx10Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	List<XbwJY2Sub> calXbwOptionHForType8(XbwJYCondition condition) throws Exception {
		List<XbwJY2Sub> nums = new ArrayList<XbwJY2Sub>();
		nums.add(calXbwOptionForWxzf("H", condition, new XbwOptionYzHandlerForGetPos<WxZfYz>() {
			@Override
			protected Integer getPos(WxZfYz current, WxZfYz last, List<XbwInfo> infos, int len) {
				return (Integer) infos.get(infos.size() - 1).obj;
			}
		}));
		nums.add(calXbwOptionForYz("杂项5", "H", condition, Zx5Yz.class, Zx5Nums.class, repositories.zx5yzRepository,
				new XbwOptionYzHandler<Zx5Yz>(), new XbwOptionYzHandlerForGetInfos<Zx5Yz>() {
					@Override
					protected XbwInfo getInfo(Zx5Yz current, Zx5Yz last, Class<Zx5Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		nums.add(calXbwOptionForYz("杂项10", "H", condition, Zx10Yz.class, Zx10Nums.class, repositories.zx10yzRepository,
				new XbwOptionYzHandler<Zx10Yz>(), new XbwOptionYzHandlerForGetInfos<Zx10Yz>() {
					@Override
					protected XbwInfo getInfo(Zx10Yz current, Zx10Yz last, Class<Zx10Yz> clazz, Class<?> numsClass,
							List<XbwInfo> currentInfos, int len) throws Exception {
						return currentInfos.get(currentInfos.size() - 1);
					}
				}));
		return nums;
	}

	protected XbwJY2Sub calXbwOptionForWxzf(String fdRange, XbwJYCondition condition,
			XbwOptionYzHandlerForGetPos<WxZfYz> posHandler) throws Exception {
		return calXbwOptionForZf(condition, WxZfYz.class, repositories.wxzfyzRepository,
				new XbwOptionZfHandler<WxYz, WxZfYz>() {

					@Override
					protected int getLength() {
						return 5;
					}

					@Override
					protected BaseYzRepository<WxYz> getRepository() {
						return repositories.wxyzRepository;
					}

					@Override
					protected String[] getFds() {
						return WxNums.FDS;
					}

					@Override
					protected Class<WxYz> getClazz() {
						return WxYz.class;
					}

					@Override
					protected XbwJY2Sub getNums(int currentPos, int pos) {
						String text = null;
						switch (pos) {
						case 0:
							text = "金";
							break;
						case 1:
							text = "木";
							break;
						case 2:
							text = "水";
							break;
						case 3:
							text = "火";
							break;
						case 4:
							text = "土";
							break;
						}
						return new XbwJY2Sub("五行-振幅" + currentPos + "-" + text, fdRange, WxNums.NUMS[pos]);
					}

				}, posHandler);
	}
}

class LrInfo {
	Integer value;
	boolean special;
}

class XbwInfo {
	String fd;
	Object obj;
	Integer yz;

	public XbwInfo(String fd, Object obj, Integer yz) {
		this.fd = fd;
		this.obj = obj;
		this.yz = yz;
	}
}

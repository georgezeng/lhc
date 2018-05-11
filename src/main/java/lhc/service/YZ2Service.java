package lhc.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.ReflectionUtils;

import com.google.common.base.Joiner;

import lhc.constants.Bs9qNums;
import lhc.constants.DsNums;
import lhc.constants.LhNums;
import lhc.constants.MwNums;
import lhc.constants.PdNums;
import lhc.constants.QiwNums;
import lhc.constants.QqNums;
import lhc.constants.SlqNums;
import lhc.constants.SwNums;
import lhc.constants.TwelveNums;
import lhc.constants.WxDsNums;
import lhc.constants.WxNums;
import lhc.constants.ZsNums;
import lhc.constants.Zx10Nums;
import lhc.constants.Zx11Nums;
import lhc.constants.Zx12Nums;
import lhc.constants.Zx13Nums;
import lhc.constants.Zx14Nums;
import lhc.constants.Zx15Nums;
import lhc.constants.Zx16Nums;
import lhc.constants.Zx17Nums;
import lhc.constants.Zx18Nums;
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
import lhc.domain.Bs9qYz;
import lhc.domain.Bs9qZfYz;
import lhc.domain.DsYz;
import lhc.domain.DsZfYz;
import lhc.domain.FxSw;
import lhc.domain.FxSw1;
import lhc.domain.FxSw10;
import lhc.domain.FxSw11;
import lhc.domain.FxSw12;
import lhc.domain.FxSw2;
import lhc.domain.FxSw3;
import lhc.domain.FxSw4;
import lhc.domain.FxSw5;
import lhc.domain.FxSw6;
import lhc.domain.FxSw7;
import lhc.domain.FxSw8;
import lhc.domain.FxSw9;
import lhc.domain.LhYz;
import lhc.domain.LhZfYz;
import lhc.domain.MwYz;
import lhc.domain.MwZfYz;
import lhc.domain.PdYz;
import lhc.domain.PdZfYz;
import lhc.domain.QiwYz;
import lhc.domain.QiwZfYz;
import lhc.domain.QqYz;
import lhc.domain.QqZfYz;
import lhc.domain.SlqYz;
import lhc.domain.SlqZfYz;
import lhc.domain.SwYz;
import lhc.domain.SwZfYz;
import lhc.domain.SxYz;
import lhc.domain.SxZfYz2;
import lhc.domain.Tm12FdYz;
import lhc.domain.Tm12FdZfYz;
import lhc.domain.TmYz;
import lhc.domain.TwelveYz;
import lhc.domain.TwelveZfYz;
import lhc.domain.WxYz;
import lhc.domain.WxZfYz;
import lhc.domain.WxdsYz;
import lhc.domain.WxdsZfYz;
import lhc.domain.ZfAvg;
import lhc.domain.ZsYz;
import lhc.domain.ZsZfYz;
import lhc.domain.Zx10Yz;
import lhc.domain.Zx10ZfYz;
import lhc.domain.Zx11Yz;
import lhc.domain.Zx11ZfYz;
import lhc.domain.Zx12Yz;
import lhc.domain.Zx12ZfYz;
import lhc.domain.Zx13Yz;
import lhc.domain.Zx13ZfYz;
import lhc.domain.Zx14Yz;
import lhc.domain.Zx14ZfYz;
import lhc.domain.Zx15Yz;
import lhc.domain.Zx15ZfYz;
import lhc.domain.Zx16Yz;
import lhc.domain.Zx16ZfYz;
import lhc.domain.Zx17Yz;
import lhc.domain.Zx17ZfYz;
import lhc.domain.Zx18Yz;
import lhc.domain.Zx18ZfYz;
import lhc.domain.Zx1Yz;
import lhc.domain.Zx1ZfYz;
import lhc.domain.Zx2Yz;
import lhc.domain.Zx2ZfYz;
import lhc.domain.Zx3Yz;
import lhc.domain.Zx3ZfYz;
import lhc.domain.Zx4Yz;
import lhc.domain.Zx4ZfYz;
import lhc.domain.Zx5Yz;
import lhc.domain.Zx5ZfYz;
import lhc.domain.Zx6Yz;
import lhc.domain.Zx6ZfYz;
import lhc.domain.Zx7Yz;
import lhc.domain.Zx7ZfYz;
import lhc.domain.Zx8Yz;
import lhc.domain.Zx8ZfYz;
import lhc.domain.Zx9Yz;
import lhc.domain.Zx9ZfYz;
import lhc.dto.TmYzInfo;
import lhc.enums.SX;
import lhc.repository.jpa.BaseFxSwDao;
import lhc.repository.jpa.BaseYzRepository;
import lhc.util.CommonUtil;
import lhc.util.DateUtil;

@SuppressWarnings("unchecked")
public abstract class YZ2Service extends YZService {
	@Async
	public Future<Exception> calZX11YZ() {
		return calFDYZ(Zx11Yz.class, Zx11Nums.class, repositories.zx11yzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(Zx11Nums.FDS.length, Zx11Yz.class, Zx11ZfYz.class, repositories.zx11yzRepository,
						repositories.zx11zfyzRepository, new GetSuffixHandler<Zx11ZfYz, Zx11Yz>() {

							@Override
							public String process(int index) {
								return Zx11Nums.FDS[index];
							}

						});
				logger.info("End of calZX11YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX12YZ() {
		return calFDYZ(Zx12Yz.class, Zx12Nums.class, repositories.zx12yzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(Zx12Nums.FDS.length, Zx12Yz.class, Zx12ZfYz.class, repositories.zx12yzRepository,
						repositories.zx12zfyzRepository, new GetSuffixHandler<Zx12ZfYz, Zx12Yz>() {

							@Override
							public String process(int index) {
								return Zx12Nums.FDS[index];
							}

						});
				logger.info("End of calZX12YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX13YZ() {
		return calFDYZ(Zx13Yz.class, Zx13Nums.class, repositories.zx13yzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(Zx13Nums.FDS.length, Zx13Yz.class, Zx13ZfYz.class, repositories.zx13yzRepository,
						repositories.zx13zfyzRepository, new GetSuffixHandler<Zx13ZfYz, Zx13Yz>() {

							@Override
							public String process(int index) {
								return Zx13Nums.FDS[index];
							}

						});
				logger.info("End of calZX13YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX14YZ() {
		return calFDYZ(Zx14Yz.class, Zx14Nums.class, repositories.zx14yzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(Zx14Nums.FDS.length, Zx14Yz.class, Zx14ZfYz.class, repositories.zx14yzRepository,
						repositories.zx14zfyzRepository, new GetSuffixHandler<Zx14ZfYz, Zx14Yz>() {

							@Override
							public String process(int index) {
								return Zx14Nums.FDS[index];
							}

						});
				logger.info("End of calZX14YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX15YZ() {
		return calFDYZ(Zx15Yz.class, Zx15Nums.class, repositories.zx15yzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(Zx15Nums.FDS.length, Zx15Yz.class, Zx15ZfYz.class, repositories.zx15yzRepository,
						repositories.zx15zfyzRepository, new GetSuffixHandler<Zx15ZfYz, Zx15Yz>() {

							@Override
							public String process(int index) {
								return Zx15Nums.FDS[index];
							}

						});
				logger.info("End of calZX15YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX16YZ() {
		return calFDYZ(Zx16Yz.class, Zx16Nums.class, repositories.zx16yzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(Zx16Nums.FDS.length, Zx16Yz.class, Zx16ZfYz.class, repositories.zx16yzRepository,
						repositories.zx16zfyzRepository, new GetSuffixHandler<Zx16ZfYz, Zx16Yz>() {

							@Override
							public String process(int index) {
								return Zx16Nums.FDS[index];
							}

						});
				logger.info("End of calZX16YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX17YZ() {
		return calFDYZ(Zx17Yz.class, Zx17Nums.class, repositories.zx17yzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(Zx17Nums.FDS.length, Zx17Yz.class, Zx17ZfYz.class, repositories.zx17yzRepository,
						repositories.zx17zfyzRepository, new GetSuffixHandler<Zx17ZfYz, Zx17Yz>() {

							@Override
							public String process(int index) {
								return Zx17Nums.FDS[index];
							}

						});
				logger.info("End of calZX17YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX18YZ() {
		return calFDYZ(Zx18Yz.class, Zx18Nums.class, repositories.zx18yzRepository, new CommonHandler() {

			@Override
			public void process() {
				calZF(Zx18Nums.FDS.length, Zx18Yz.class, Zx18ZfYz.class, repositories.zx18yzRepository,
						repositories.zx18zfyzRepository, new GetSuffixHandler<Zx18ZfYz, Zx18Yz>() {

							@Override
							public String process(int index) {
								return Zx18Nums.FDS[index];
							}

						});
				logger.info("End of calZX18YZ...");
			}
		});

	}

	static class FxComparator implements Comparator<XbwInfo> {

		@Override
		public int compare(XbwInfo o1, XbwInfo o2) {
			if (o1.yz == null && o2.yz == null) {
				return 0;
			} else if (o1.yz == null) {
				return -1;
			} else if (o2.yz == null) {
				return 1;
			}
			return o1.yz.compareTo(o2.yz);
		}
	}

	private Comparator<XbwInfo> fxComparator = new FxComparator();
	private Map<String, FxSw> fxswMap = new LinkedHashMap<String, FxSw>();

	static class FxHandler {
		public void getData(List<XbwInfo> list, Class<?> clazz, Class<?> numsClass, Object yz) throws Exception {
			String[] fds = (String[]) ReflectionUtils.findField(numsClass, "FDS").get(null);
			for (String fd : fds) {
				Method m = ReflectionUtils.findMethod(clazz, "get" + fd);
				Integer value = (Integer) m.invoke(yz);
				list.add(new XbwInfo(fd, fd.replace("W", "").replace("Fd", ""), value));
			}
		}

		public void setData(int pos, XbwInfo info, List<XbwInfo> list, List<XbwInfo> lastList, FxSw data, FxSw lastData,
				String prefix, Class<?> numsClass) throws Exception {
			Method sm = ReflectionUtils.findMethod(data.getClass(), "set" + prefix, Integer.class);
			XbwInfo firstInfo = list.get(0);
			XbwInfo lastInfo = lastList.get(pos - 1);
			String lastDW = (String) lastInfo.obj;
			String firstDW = (String) firstInfo.obj;
			if (lastDW.equals(firstDW)) {
				sm.invoke(data, 0);
			}
			Method gm = ReflectionUtils.findMethod(data.getClass(), "get" + prefix);
			if (gm.invoke(lastData) != null) {
				if (gm.invoke(data) == null) {
					Integer value = (Integer) gm.invoke(lastData);
					sm.invoke(data, value + 1);
				}
				Method m = ReflectionUtils.findMethod(data.getClass(), "set" + prefix + "DW", String.class);
				Map<String, String> map = (Map<String, String>) ReflectionUtils.findField(numsClass, "TXT_MAP").get(null);
				m.invoke(data, map.get(info.fd));
				Field f = ReflectionUtils.findField(numsClass, info.fd.toUpperCase());
				m = ReflectionUtils.findMethod(data.getClass(), "set" + prefix + "Nums", String.class);
				m.invoke(data, Joiner.on(",").join((List<Integer>) f.get(null)));
			}
		}
	}

	static class FxZfHandler<T extends Avg> {
		public int getLength(String[] fds) {
			return fds.length;
		}

		public void setData(Integer zf, int pos, FxSw data, String prefix, Class<?> numsClass,
				BaseYzRepository<T> yzRepository) throws Exception {
			T yz = yzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
			String[] fds = (String[]) ReflectionUtils.findField(numsClass, "FDS").get(null);
			int yzpos = 1;
			for (String fd : fds) {
				Integer value = (Integer) ReflectionUtils.findMethod(yz.getClass(), "get" + fd).invoke(yz);
				if (value != null && value == 0) {
					break;
				}
				yzpos++;
			}
			yzpos = yzpos + zf;
			int len = getLength(fds);
			if (zf == len) {
				yzpos++;
			}
			if (yzpos > len) {
				yzpos -= len;
			}
			Method m = ReflectionUtils.findMethod(data.getClass(), "set" + prefix + "zfNums", String.class);
			List<Integer>[] nums = (List<Integer>[]) ReflectionUtils.findField(numsClass, "NUMS").get(null);
			m.invoke(data, Joiner.on(",").join(nums[yzpos - 1]));
		}
	}

	public <T extends Avg, R extends FxSw> void calFx(int pos, Class<T> clazz, Class<?> numsClass, Class<R> swClass,
			BaseYzRepository<T> repository, BaseYzRepository<R> swRepository, FxHandler handler, String prefix)
			throws Exception {
		Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
		Page<T> result = null;
		T last = null;
		List<XbwInfo> lastList = null;
		do {
			result = repository.findAll(request);
			if (result != null && result.hasContent()) {
				for (T yz : result.getContent()) {
					List<XbwInfo> list = new ArrayList<XbwInfo>();
					handler.getData(list, clazz, numsClass, yz);
					Collections.sort(list, fxComparator);
					R data = null;
					synchronized (this) {
						data = (R) fxswMap.get(yz.getDate() + "_" + pos);
						// data = swRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
						if (data == null) {
							data = swRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
							if (data == null) {
								data = swClass.newInstance();
								data.setDate(yz.getDate());
								data.setYear(yz.getYear());
								data.setPhase(yz.getPhase());
							}
							fxswMap.put(yz.getDate() + "_" + pos, data);
						}
					}
					if (last != null) {
						XbwInfo info = list.get(pos - 1);
						R lastData = (R) fxswMap.get(last.getDate() + "_" + pos);
						if (lastData == null) {
							lastData = swRepository.findByYearAndPhase(last.getYear(), last.getPhase());
						}
						handler.setData(pos, info, list, lastList, data, lastData, prefix, numsClass);
						// swRepository.save(data);
					}
					last = yz;
					lastList = list;
				}
			}
			request = request.next();
		} while (result != null && result.hasNext());
	}

	public <T extends FxSw> void calFxSx(int pos, Class<T> swClass, BaseYzRepository<T> swRepository) throws Exception {
		calFx(pos, SxYz.class, null, swClass, repositories.sxyzRepository, swRepository, new FxHandler() {
			@Override
			public void getData(List<XbwInfo> list, Class<?> clazz, Class<?> numsClass, Object yz) throws Exception {
				for (SX sx : SX.seq()) {
					Method m = ReflectionUtils.findMethod(clazz, "get" + sx.name());
					Integer value = (Integer) m.invoke(yz);
					list.add(new XbwInfo(sx.name(), sx, value));
				}
			}

			@Override
			public void setData(int pos, XbwInfo info, List<XbwInfo> list, List<XbwInfo> lastList, FxSw data, FxSw lastData,
					String prefix, Class<?> numsClass) throws Exception {
				SX sx = (SX) info.obj;
				XbwInfo firstInfo = list.get(0);
				SX firstSX = (SX) firstInfo.obj;
				XbwInfo lastInfo = lastList.get(pos - 1);
				SX lastSX = (SX) lastInfo.obj;
				if (lastSX.equals(firstSX)) {
					data.setSx(0);
				}
				if (lastData.getSx() != null) {
					if (data.getSx() == null) {
						data.setSx(lastData.getSx() + 1);
					}
				}
				data.setSxDW(sx.getText());
				SX bmnSX = DateUtil.getSxByYear(data.getDate());
				data.setSxNums(Joiner.on(",").join(getSxNums(bmnSX, sx)));
			}
		}, null);
	}

	public <T extends FxSw> void calFxFd(int pos, Class<T> swClass, BaseYzRepository<T> swRepository) throws Exception {
		calFx(pos, Tm12FdYz.class, null, swClass, repositories.tm12fdyzRepository, swRepository, new FxHandler() {
			@Override
			public void getData(List<XbwInfo> list, Class<?> clazz, Class<?> numsClass, Object yz) throws Exception {
				for (int i = 1; i < 13; i++) {
					Method m = ReflectionUtils.findMethod(clazz, "getW" + i);
					Integer value = (Integer) m.invoke(yz);
					list.add(new XbwInfo("" + i, i, value));
				}
			}

			@Override
			public void setData(int pos, XbwInfo info, List<XbwInfo> list, List<XbwInfo> lastList, FxSw data, FxSw lastData,
					String prefix, Class<?> numsClass) throws Exception {
				String dw = (String) info.fd;
				XbwInfo firstInfo = list.get(0);
				String firstDW = (String) firstInfo.fd;
				XbwInfo lastInfo = lastList.get(pos - 1);
				String lastDW = (String) lastInfo.fd;
				if (lastDW.equals(firstDW)) {
					data.setFd(0);
				}

				if (lastData.getFd() != null) {
					if (data.getFd() == null) {
						data.setFd(lastData.getFd() + 1);
					}
					data.setFdDW("位" + dw);
					TmYz tmData = repositories.tmyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
					List<TmYzInfo> infos = getTMFDList(tmData, true);
					int range = 4;
					int length = 12;
					int currentPos = (Integer) info.obj;
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
					data.setFdNums(Joiner.on(",").join(nums));
				}

			}
		}, "Fd");
	}

	public <A extends Avg, T extends ZfAvg, R extends FxSw> void calFxZf(int pos, Class<T> clazz, Class<A> yzClass,
			Class<R> swClass, Class<?> numsClass, BaseYzRepository<T> repository, BaseYzRepository<A> yzRepository,
			BaseYzRepository<R> swRepository, String prefix, FxZfHandler<A> handler) throws Exception {
		Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
		Page<T> result = null;
		T last = null;
		List<XbwInfo> lastList = null;
		String[] fds = null;
		if (numsClass != null) {
			fds = (String[]) ReflectionUtils.findField(numsClass, "FDS").get(null);
		}
		Method sm = ReflectionUtils.findMethod(swClass, "set" + prefix + "zf", Integer.class);
		Method gm = ReflectionUtils.findMethod(swClass, "get" + prefix + "zf");
		do {
			result = repository.findAll(request);
			if (result != null && result.hasContent()) {
				for (T yz : result.getContent()) {
					List<XbwInfo> list = new ArrayList<XbwInfo>();
					for (int i = 0; i < handler.getLength(fds); i++) {
						Method m = ReflectionUtils.findMethod(clazz, "getZf" + i);
						Integer value = (Integer) m.invoke(yz);
						list.add(new XbwInfo("zf" + i, i, value));
					}
					Collections.sort(list, fxComparator);
					if (last != null) {
						R data = (R) fxswMap.get(yz.getDate() + "_" + pos);
						// R data = swRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
						XbwInfo info = list.get(pos - 1);
						Integer zf = (Integer) info.obj;
						XbwInfo firstInfo = list.get(0);
						Integer firstZf = (Integer) firstInfo.obj;
						XbwInfo lastInfo = lastList.get(pos - 1);
						Integer lastZf = (Integer) lastInfo.obj;
						if (lastZf.equals(firstZf)) {
							sm.invoke(data, 0);
						}
						R lastData = (R) fxswMap.get(last.getDate() + "_" + pos);
						if (gm.invoke(lastData) != null) {
							if (gm.invoke(data) == null) {
								Integer value = (Integer) gm.invoke(lastData);
								sm.invoke(data, value + 1);
							}
							Method m = ReflectionUtils.findMethod(swClass, "set" + prefix + "zfDW", String.class);
							m.invoke(data, "振幅" + zf);
							handler.setData(zf, pos, data, prefix, numsClass, yzRepository);
						}
						// swRepository.save(data);
					}
					last = yz;
					lastList = list;
				}
			}
			request = request.next();
		} while (result != null && result.hasNext());
	}

	public <T extends FxSw> void calFxSxZf(int pos, Class<T> swClass, BaseYzRepository<T> swRepository) throws Exception {
		calFxZf(pos, SxZfYz2.class, SxYz.class, swClass, null, repositories.sxzfyz2Repository, repositories.sxyzRepository,
				swRepository, "Sx", new FxZfHandler<SxYz>() {
					public int getLength(String[] fds) {
						return SX.seq().length;
					}

					@Override
					public void setData(Integer zf, int pos, FxSw data, String prefix, Class<?> numsClass,
							BaseYzRepository<SxYz> yzRepository) throws Exception {
						SxYz sxyz = yzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
						int sxpos = sxyz.getCurrentSx().getPos() + zf;
						int len = SX.seq().length;
						if (zf == len) {
							sxpos++;
						}
						if (sxpos > len) {
							sxpos -= len;
						}
						SX bmnSX = DateUtil.getSxByYear(data.getDate());
						data.setSxzfNums(Joiner.on(",").join(getSxNums(bmnSX, SX.posOf(sxpos))));
					}
				});
	}

	public <T extends FxSw> void calFxFdZf(int pos, Class<T> swClass, BaseYzRepository<T> swRepository) throws Exception {
		calFxZf(pos, Tm12FdZfYz.class, Tm12FdYz.class, swClass, null, repositories.tm12fdzfyzRepository,
				repositories.tm12fdyzRepository, swRepository, "Fd", new FxZfHandler<Tm12FdYz>() {
					public int getLength(String[] fds) {
						return 12;
					}

					@Override
					public void setData(Integer zf, int pos, FxSw data, String prefix, Class<?> numsClass,
							BaseYzRepository<Tm12FdYz> yzRepository) throws Exception {
						TmYz tmData = repositories.tmyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
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

						int fdpos = currentPos + zf;
						int len = 12;
						if (zf == len) {
							fdpos++;
						}
						if (fdpos >= len) {
							fdpos -= len;
						}
						if (fdpos == 11) {
							maxLength = 5;
						}
						int startPos = fdpos * range;
						List<Integer> nums = new ArrayList<Integer>();
						for (int i = startPos; i < startPos + maxLength; i++) {
							nums.add(infos.get(i).getNum());
						}
						Method m = ReflectionUtils.findMethod(data.getClass(), "set" + prefix + "zfNums", String.class);
						m.invoke(data, Joiner.on(",").join(nums));
					}
				});
	}

	@Async
	public Future<Exception> calFxSxSw1() {
		Exception t = null;
		try {
			calFxSx(1, FxSw1.class, repositories.fxsw1Repository);
			calFxSxZf(1, FxSw1.class, repositories.fxsw1Repository);
			logger.info("End of calFxSxSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw2() {
		Exception t = null;
		try {
			calFxSx(2, FxSw2.class, repositories.fxsw2Repository);
			calFxSxZf(2, FxSw2.class, repositories.fxsw2Repository);
			logger.info("End of calFxSxSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw3() {
		Exception t = null;
		try {
			calFxSx(3, FxSw3.class, repositories.fxsw3Repository);
			calFxSxZf(3, FxSw3.class, repositories.fxsw3Repository);
			logger.info("End of calFxSxSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw4() {
		Exception t = null;
		try {
			calFxSx(4, FxSw4.class, repositories.fxsw4Repository);
			calFxSxZf(4, FxSw4.class, repositories.fxsw4Repository);
			logger.info("End of calFxSxSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw5() {
		Exception t = null;
		try {
			calFxSx(5, FxSw5.class, repositories.fxsw5Repository);
			calFxSxZf(5, FxSw5.class, repositories.fxsw5Repository);
			logger.info("End of calFxSxSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw6() {
		Exception t = null;
		try {
			calFxSx(6, FxSw6.class, repositories.fxsw6Repository);
			calFxSxZf(6, FxSw6.class, repositories.fxsw6Repository);
			logger.info("End of calFxSxSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw7() {
		Exception t = null;
		try {
			calFxSx(7, FxSw7.class, repositories.fxsw7Repository);
			calFxSxZf(7, FxSw7.class, repositories.fxsw7Repository);
			logger.info("End of calFxSxSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw8() {
		Exception t = null;
		try {
			calFxSx(8, FxSw8.class, repositories.fxsw8Repository);
			calFxSxZf(8, FxSw8.class, repositories.fxsw8Repository);
			logger.info("End of calFxSxSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw9() {
		Exception t = null;
		try {
			calFxSx(9, FxSw9.class, repositories.fxsw9Repository);
			calFxSxZf(9, FxSw9.class, repositories.fxsw9Repository);
			logger.info("End of calFxSxSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw10() {
		Exception t = null;
		try {
			calFxSx(10, FxSw10.class, repositories.fxsw10Repository);
			calFxSxZf(10, FxSw10.class, repositories.fxsw10Repository);
			logger.info("End of calFxSxSw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw11() {
		Exception t = null;
		try {
			calFxSx(11, FxSw11.class, repositories.fxsw11Repository);
			calFxSxZf(11, FxSw11.class, repositories.fxsw11Repository);
			logger.info("End of calFxSxSw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSxSw12() {
		Exception t = null;
		try {
			calFxSx(12, FxSw12.class, repositories.fxsw12Repository);
			calFxSxZf(12, FxSw12.class, repositories.fxsw12Repository);
			logger.info("End of calFxSxSw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxDsSw1() {
		Exception t = null;
		try {
			calFx(1, DsYz.class, DsNums.class, FxSw1.class, repositories.dsyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Ds");
			calFxZf(1, DsZfYz.class, DsYz.class, FxSw1.class, DsNums.class, repositories.dszfyzRepository,
					repositories.dsyzRepository, repositories.fxsw1Repository, "Ds", new FxZfHandler<DsYz>());
			logger.info("End of calFxDsSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxDsSw2() {
		Exception t = null;
		try {
			calFx(2, DsYz.class, DsNums.class, FxSw2.class, repositories.dsyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Ds");
			calFxZf(2, DsZfYz.class, DsYz.class, FxSw2.class, DsNums.class, repositories.dszfyzRepository,
					repositories.dsyzRepository, repositories.fxsw2Repository, "Ds", new FxZfHandler<DsYz>());
			logger.info("End of calFxDsSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxDsSw3() {
		Exception t = null;
		try {
			calFx(3, DsYz.class, DsNums.class, FxSw3.class, repositories.dsyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Ds");
			calFxZf(3, DsZfYz.class, DsYz.class, FxSw3.class, DsNums.class, repositories.dszfyzRepository,
					repositories.dsyzRepository, repositories.fxsw3Repository, "Ds", new FxZfHandler<DsYz>());
			logger.info("End of calFxDsSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxDsSw4() {
		Exception t = null;
		try {
			calFx(4, DsYz.class, DsNums.class, FxSw4.class, repositories.dsyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Ds");
			calFxZf(4, DsZfYz.class, DsYz.class, FxSw4.class, DsNums.class, repositories.dszfyzRepository,
					repositories.dsyzRepository, repositories.fxsw4Repository, "Ds", new FxZfHandler<DsYz>());
			logger.info("End of calFxDsSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxDsSw5() {
		Exception t = null;
		try {
			calFx(5, DsYz.class, DsNums.class, FxSw5.class, repositories.dsyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Ds");
			calFxZf(5, DsZfYz.class, DsYz.class, FxSw5.class, DsNums.class, repositories.dszfyzRepository,
					repositories.dsyzRepository, repositories.fxsw5Repository, "Ds", new FxZfHandler<DsYz>());
			logger.info("End of calFxDsSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxDsSw6() {
		Exception t = null;
		try {
			calFx(6, DsYz.class, DsNums.class, FxSw6.class, repositories.dsyzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Ds");
			calFxZf(6, DsZfYz.class, DsYz.class, FxSw6.class, DsNums.class, repositories.dszfyzRepository,
					repositories.dsyzRepository, repositories.fxsw6Repository, "Ds", new FxZfHandler<DsYz>());
			logger.info("End of calFxDsSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxDsSw7() {
		Exception t = null;
		try {
			calFx(7, DsYz.class, DsNums.class, FxSw7.class, repositories.dsyzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Ds");
			calFxZf(7, DsZfYz.class, DsYz.class, FxSw7.class, DsNums.class, repositories.dszfyzRepository,
					repositories.dsyzRepository, repositories.fxsw7Repository, "Ds", new FxZfHandler<DsYz>());
			logger.info("End of calFxDsSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxDsSw8() {
		Exception t = null;
		try {
			calFx(8, DsYz.class, DsNums.class, FxSw8.class, repositories.dsyzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Ds");
			calFxZf(8, DsZfYz.class, DsYz.class, FxSw8.class, DsNums.class, repositories.dszfyzRepository,
					repositories.dsyzRepository, repositories.fxsw8Repository, "Ds", new FxZfHandler<DsYz>());
			logger.info("End of calFxDsSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxDsSw9() {
		Exception t = null;
		try {
			calFx(9, DsYz.class, DsNums.class, FxSw9.class, repositories.dsyzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Ds");
			calFxZf(9, DsZfYz.class, DsYz.class, FxSw9.class, DsNums.class, repositories.dszfyzRepository,
					repositories.dsyzRepository, repositories.fxsw9Repository, "Ds", new FxZfHandler<DsYz>());
			logger.info("End of calFxDsSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxDsSw10() {
		Exception t = null;
		try {
			calFx(10, DsYz.class, DsNums.class, FxSw10.class, repositories.dsyzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Ds");
			calFxZf(10, DsZfYz.class, DsYz.class, FxSw10.class, DsNums.class, repositories.dszfyzRepository,
					repositories.dsyzRepository, repositories.fxsw10Repository, "Ds", new FxZfHandler<DsYz>());
			logger.info("End of calFxDsSw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSwSw1() {
		Exception t = null;
		try {
			calFx(1, SwYz.class, SwNums.class, FxSw1.class, repositories.swyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Sw");
			calFxZf(1, SwZfYz.class, SwYz.class, FxSw1.class, SwNums.class, repositories.swzfyzRepository,
					repositories.swyzRepository, repositories.fxsw1Repository, "Sw", new FxZfHandler<SwYz>());
			logger.info("End of calFxSwSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSwSw2() {
		Exception t = null;
		try {
			calFx(2, SwYz.class, SwNums.class, FxSw2.class, repositories.swyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Sw");
			calFxZf(2, SwZfYz.class, SwYz.class, FxSw2.class, SwNums.class, repositories.swzfyzRepository,
					repositories.swyzRepository, repositories.fxsw2Repository, "Sw", new FxZfHandler<SwYz>());
			logger.info("End of calFxSwSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSwSw3() {
		Exception t = null;
		try {
			calFx(3, SwYz.class, SwNums.class, FxSw3.class, repositories.swyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Sw");
			calFxZf(3, SwZfYz.class, SwYz.class, FxSw3.class, SwNums.class, repositories.swzfyzRepository,
					repositories.swyzRepository, repositories.fxsw3Repository, "Sw", new FxZfHandler<SwYz>());
			logger.info("End of calFxSwSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSwSw4() {
		Exception t = null;
		try {
			calFx(4, SwYz.class, SwNums.class, FxSw4.class, repositories.swyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Sw");
			calFxZf(4, SwZfYz.class, SwYz.class, FxSw4.class, SwNums.class, repositories.swzfyzRepository,
					repositories.swyzRepository, repositories.fxsw4Repository, "Sw", new FxZfHandler<SwYz>());
			logger.info("End of calFxSwSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSwSw5() {
		Exception t = null;
		try {
			calFx(5, SwYz.class, SwNums.class, FxSw5.class, repositories.swyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Sw");
			calFxZf(5, SwZfYz.class, SwYz.class, FxSw5.class, SwNums.class, repositories.swzfyzRepository,
					repositories.swyzRepository, repositories.fxsw5Repository, "Sw", new FxZfHandler<SwYz>());
			logger.info("End of calFxSwSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxMwSw1() {
		Exception t = null;
		try {
			calFx(1, MwYz.class, MwNums.class, FxSw1.class, repositories.mwyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Mw");
			calFxZf(1, MwZfYz.class, MwYz.class, FxSw1.class, MwNums.class, repositories.mwzfyzRepository,
					repositories.mwyzRepository, repositories.fxsw1Repository, "Mw", new FxZfHandler<MwYz>());
			logger.info("End of calFxMwSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxMwSw2() {
		Exception t = null;
		try {
			calFx(2, MwYz.class, MwNums.class, FxSw2.class, repositories.mwyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Mw");
			calFxZf(2, MwZfYz.class, MwYz.class, FxSw2.class, MwNums.class, repositories.mwzfyzRepository,
					repositories.mwyzRepository, repositories.fxsw2Repository, "Mw", new FxZfHandler<MwYz>());
			logger.info("End of calFxMwSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxMwSw3() {
		Exception t = null;
		try {
			calFx(3, MwYz.class, MwNums.class, FxSw3.class, repositories.mwyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Mw");
			calFxZf(3, MwZfYz.class, MwYz.class, FxSw3.class, MwNums.class, repositories.mwzfyzRepository,
					repositories.mwyzRepository, repositories.fxsw3Repository, "Mw", new FxZfHandler<MwYz>());
			logger.info("End of calFxMwSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxMwSw4() {
		Exception t = null;
		try {
			calFx(4, MwYz.class, MwNums.class, FxSw4.class, repositories.mwyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Mw");
			calFxZf(4, MwZfYz.class, MwYz.class, FxSw4.class, MwNums.class, repositories.mwzfyzRepository,
					repositories.mwyzRepository, repositories.fxsw4Repository, "Mw", new FxZfHandler<MwYz>());
			logger.info("End of calFxMwSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxMwSw5() {
		Exception t = null;
		try {
			calFx(5, MwYz.class, MwNums.class, FxSw5.class, repositories.mwyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Mw");
			calFxZf(5, MwZfYz.class, MwYz.class, FxSw5.class, MwNums.class, repositories.mwzfyzRepository,
					repositories.mwyzRepository, repositories.fxsw5Repository, "Mw", new FxZfHandler<MwYz>());
			logger.info("End of calFxMwSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxMwSw6() {
		Exception t = null;
		try {
			calFx(6, MwYz.class, MwNums.class, FxSw6.class, repositories.mwyzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Mw");
			calFxZf(6, MwZfYz.class, MwYz.class, FxSw6.class, MwNums.class, repositories.mwzfyzRepository,
					repositories.mwyzRepository, repositories.fxsw6Repository, "Mw", new FxZfHandler<MwYz>());
			logger.info("End of calFxMwSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxMwSw7() {
		Exception t = null;
		try {
			calFx(7, MwYz.class, MwNums.class, FxSw7.class, repositories.mwyzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Mw");
			calFxZf(7, MwZfYz.class, MwYz.class, FxSw7.class, MwNums.class, repositories.mwzfyzRepository,
					repositories.mwyzRepository, repositories.fxsw7Repository, "Mw", new FxZfHandler<MwYz>());
			logger.info("End of calFxMwSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxMwSw8() {
		Exception t = null;
		try {
			calFx(8, MwYz.class, MwNums.class, FxSw8.class, repositories.mwyzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Mw");
			calFxZf(8, MwZfYz.class, MwYz.class, FxSw8.class, MwNums.class, repositories.mwzfyzRepository,
					repositories.mwyzRepository, repositories.fxsw8Repository, "Mw", new FxZfHandler<MwYz>());
			logger.info("End of calFxMwSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxMwSw9() {
		Exception t = null;
		try {
			calFx(9, MwYz.class, MwNums.class, FxSw9.class, repositories.mwyzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Mw");
			calFxZf(9, MwZfYz.class, MwYz.class, FxSw9.class, MwNums.class, repositories.mwzfyzRepository,
					repositories.mwyzRepository, repositories.fxsw9Repository, "Mw", new FxZfHandler<MwYz>());
			logger.info("End of calFxMwSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxMwSw10() {
		Exception t = null;
		try {
			calFx(10, MwYz.class, MwNums.class, FxSw10.class, repositories.mwyzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Mw");
			calFxZf(10, MwZfYz.class, MwYz.class, FxSw10.class, MwNums.class, repositories.mwzfyzRepository,
					repositories.mwyzRepository, repositories.fxsw10Repository, "Mw", new FxZfHandler<MwYz>());
			logger.info("End of calFxMwSw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxLhSw1() {
		Exception t = null;
		try {
			calFx(1, LhYz.class, LhNums.class, FxSw1.class, repositories.lhyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Lh");
			calFxZf(1, LhZfYz.class, LhYz.class, FxSw1.class, LhNums.class, repositories.lhzfyzRepository,
					repositories.lhyzRepository, repositories.fxsw1Repository, "Lh", new FxZfHandler<LhYz>());
			logger.info("End of calFxLhSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxLhSw2() {
		Exception t = null;
		try {
			calFx(2, LhYz.class, LhNums.class, FxSw2.class, repositories.lhyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Lh");
			calFxZf(2, LhZfYz.class, LhYz.class, FxSw2.class, LhNums.class, repositories.lhzfyzRepository,
					repositories.lhyzRepository, repositories.fxsw2Repository, "Lh", new FxZfHandler<LhYz>());
			logger.info("End of calFxLhSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxLhSw3() {
		Exception t = null;
		try {
			calFx(3, LhYz.class, LhNums.class, FxSw3.class, repositories.lhyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Lh");
			calFxZf(3, LhZfYz.class, LhYz.class, FxSw3.class, LhNums.class, repositories.lhzfyzRepository,
					repositories.lhyzRepository, repositories.fxsw3Repository, "Lh", new FxZfHandler<LhYz>());
			logger.info("End of calFxLhSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxLhSw4() {
		Exception t = null;
		try {
			calFx(4, LhYz.class, LhNums.class, FxSw4.class, repositories.lhyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Lh");
			calFxZf(4, LhZfYz.class, LhYz.class, FxSw4.class, LhNums.class, repositories.lhzfyzRepository,
					repositories.lhyzRepository, repositories.fxsw4Repository, "Lh", new FxZfHandler<LhYz>());
			logger.info("End of calFxLhSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxLhSw5() {
		Exception t = null;
		try {
			calFx(5, LhYz.class, LhNums.class, FxSw5.class, repositories.lhyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Lh");
			calFxZf(5, LhZfYz.class, LhYz.class, FxSw5.class, LhNums.class, repositories.lhzfyzRepository,
					repositories.lhyzRepository, repositories.fxsw5Repository, "Lh", new FxZfHandler<LhYz>());
			logger.info("End of calFxLhSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxLhSw6() {
		Exception t = null;
		try {
			calFx(6, LhYz.class, LhNums.class, FxSw6.class, repositories.lhyzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Lh");
			calFxZf(6, LhZfYz.class, LhYz.class, FxSw6.class, LhNums.class, repositories.lhzfyzRepository,
					repositories.lhyzRepository, repositories.fxsw6Repository, "Lh", new FxZfHandler<LhYz>());
			logger.info("End of calFxLhSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxLhSw7() {
		Exception t = null;
		try {
			calFx(7, LhYz.class, LhNums.class, FxSw7.class, repositories.lhyzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Lh");
			calFxZf(7, LhZfYz.class, LhYz.class, FxSw7.class, LhNums.class, repositories.lhzfyzRepository,
					repositories.lhyzRepository, repositories.fxsw7Repository, "Lh", new FxZfHandler<LhYz>());
			logger.info("End of calFxLhSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxLhSw8() {
		Exception t = null;
		try {
			calFx(8, LhYz.class, LhNums.class, FxSw8.class, repositories.lhyzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Lh");
			calFxZf(8, LhZfYz.class, LhYz.class, FxSw8.class, LhNums.class, repositories.lhzfyzRepository,
					repositories.lhyzRepository, repositories.fxsw8Repository, "Lh", new FxZfHandler<LhYz>());
			logger.info("End of calFxLhSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxLhSw9() {
		Exception t = null;
		try {
			calFx(9, LhYz.class, LhNums.class, FxSw9.class, repositories.lhyzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Lh");
			calFxZf(9, LhZfYz.class, LhYz.class, FxSw9.class, LhNums.class, repositories.lhzfyzRepository,
					repositories.lhyzRepository, repositories.fxsw9Repository, "Lh", new FxZfHandler<LhYz>());
			logger.info("End of calFxLhSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxLhSw10() {
		Exception t = null;
		try {
			calFx(10, LhYz.class, LhNums.class, FxSw10.class, repositories.lhyzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Lh");
			calFxZf(10, LhZfYz.class, LhYz.class, FxSw10.class, LhNums.class, repositories.lhzfyzRepository,
					repositories.lhyzRepository, repositories.fxsw10Repository, "Lh", new FxZfHandler<LhYz>());
			logger.info("End of calFxLhSw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxBsSw1() {
		Exception t = null;
		try {
			calFx(1, Bs9qYz.class, Bs9qNums.class, FxSw1.class, repositories.bs9qyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Bs");
			calFxZf(1, Bs9qZfYz.class, Bs9qYz.class, FxSw1.class, Bs9qNums.class, repositories.bs9qzfyzRepository,
					repositories.bs9qyzRepository, repositories.fxsw1Repository, "Bs", new FxZfHandler<Bs9qYz>());
			logger.info("End of calFxBsSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxBsSw2() {
		Exception t = null;
		try {
			calFx(2, Bs9qYz.class, Bs9qNums.class, FxSw2.class, repositories.bs9qyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Bs");
			calFxZf(2, Bs9qZfYz.class, Bs9qYz.class, FxSw2.class, Bs9qNums.class, repositories.bs9qzfyzRepository,
					repositories.bs9qyzRepository, repositories.fxsw2Repository, "Bs", new FxZfHandler<Bs9qYz>());
			logger.info("End of calFxBsSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxBsSw3() {
		Exception t = null;
		try {
			calFx(3, Bs9qYz.class, Bs9qNums.class, FxSw3.class, repositories.bs9qyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Bs");
			calFxZf(3, Bs9qZfYz.class, Bs9qYz.class, FxSw3.class, Bs9qNums.class, repositories.bs9qzfyzRepository,
					repositories.bs9qyzRepository, repositories.fxsw3Repository, "Bs", new FxZfHandler<Bs9qYz>());
			logger.info("End of calFxBsSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxBsSw4() {
		Exception t = null;
		try {
			calFx(4, Bs9qYz.class, Bs9qNums.class, FxSw4.class, repositories.bs9qyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Bs");
			calFxZf(4, Bs9qZfYz.class, Bs9qYz.class, FxSw4.class, Bs9qNums.class, repositories.bs9qzfyzRepository,
					repositories.bs9qyzRepository, repositories.fxsw4Repository, "Bs", new FxZfHandler<Bs9qYz>());
			logger.info("End of calFxBsSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxBsSw5() {
		Exception t = null;
		try {
			calFx(5, Bs9qYz.class, Bs9qNums.class, FxSw5.class, repositories.bs9qyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Bs");
			calFxZf(5, Bs9qZfYz.class, Bs9qYz.class, FxSw5.class, Bs9qNums.class, repositories.bs9qzfyzRepository,
					repositories.bs9qyzRepository, repositories.fxsw5Repository, "Bs", new FxZfHandler<Bs9qYz>());
			logger.info("End of calFxBsSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxBsSw6() {
		Exception t = null;
		try {
			calFx(6, Bs9qYz.class, Bs9qNums.class, FxSw6.class, repositories.bs9qyzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Bs");
			calFxZf(6, Bs9qZfYz.class, Bs9qYz.class, FxSw6.class, Bs9qNums.class, repositories.bs9qzfyzRepository,
					repositories.bs9qyzRepository, repositories.fxsw6Repository, "Bs", new FxZfHandler<Bs9qYz>());
			logger.info("End of calFxBsSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxBsSw7() {
		Exception t = null;
		try {
			calFx(7, Bs9qYz.class, Bs9qNums.class, FxSw7.class, repositories.bs9qyzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Bs");
			calFxZf(7, Bs9qZfYz.class, Bs9qYz.class, FxSw7.class, Bs9qNums.class, repositories.bs9qzfyzRepository,
					repositories.bs9qyzRepository, repositories.fxsw7Repository, "Bs", new FxZfHandler<Bs9qYz>());
			logger.info("End of calFxBsSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxBsSw8() {
		Exception t = null;
		try {
			calFx(8, Bs9qYz.class, Bs9qNums.class, FxSw8.class, repositories.bs9qyzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Bs");
			calFxZf(8, Bs9qZfYz.class, Bs9qYz.class, FxSw8.class, Bs9qNums.class, repositories.bs9qzfyzRepository,
					repositories.bs9qyzRepository, repositories.fxsw8Repository, "Bs", new FxZfHandler<Bs9qYz>());
			logger.info("End of calFxBsSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxBsSw9() {
		Exception t = null;
		try {
			calFx(9, Bs9qYz.class, Bs9qNums.class, FxSw9.class, repositories.bs9qyzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Bs");
			calFxZf(9, Bs9qZfYz.class, Bs9qYz.class, FxSw9.class, Bs9qNums.class, repositories.bs9qzfyzRepository,
					repositories.bs9qyzRepository, repositories.fxsw9Repository, "Bs", new FxZfHandler<Bs9qYz>());
			logger.info("End of calFxBsSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZsSw1() {
		Exception t = null;
		try {
			calFx(1, ZsYz.class, ZsNums.class, FxSw1.class, repositories.zsyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zs");
			calFxZf(1, ZsZfYz.class, ZsYz.class, FxSw1.class, ZsNums.class, repositories.zszfyzRepository,
					repositories.zsyzRepository, repositories.fxsw1Repository, "Zs", new FxZfHandler<ZsYz>());
			logger.info("End of calFxZsSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZsSw2() {
		Exception t = null;
		try {
			calFx(2, ZsYz.class, ZsNums.class, FxSw2.class, repositories.zsyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zs");
			calFxZf(2, ZsZfYz.class, ZsYz.class, FxSw2.class, ZsNums.class, repositories.zszfyzRepository,
					repositories.zsyzRepository, repositories.fxsw2Repository, "Zs", new FxZfHandler<ZsYz>());
			logger.info("End of calFxZsSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZsSw3() {
		Exception t = null;
		try {
			calFx(3, ZsYz.class, ZsNums.class, FxSw3.class, repositories.zsyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zs");
			calFxZf(3, ZsZfYz.class, ZsYz.class, FxSw3.class, ZsNums.class, repositories.zszfyzRepository,
					repositories.zsyzRepository, repositories.fxsw3Repository, "Zs", new FxZfHandler<ZsYz>());
			logger.info("End of calFxZsSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZsSw4() {
		Exception t = null;
		try {
			calFx(4, ZsYz.class, ZsNums.class, FxSw4.class, repositories.zsyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zs");
			calFxZf(4, ZsZfYz.class, ZsYz.class, FxSw4.class, ZsNums.class, repositories.zszfyzRepository,
					repositories.zsyzRepository, repositories.fxsw4Repository, "Zs", new FxZfHandler<ZsYz>());
			logger.info("End of calFxZsSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZsSw5() {
		Exception t = null;
		try {
			calFx(5, ZsYz.class, ZsNums.class, FxSw5.class, repositories.zsyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zs");
			calFxZf(5, ZsZfYz.class, ZsYz.class, FxSw5.class, ZsNums.class, repositories.zszfyzRepository,
					repositories.zsyzRepository, repositories.fxsw5Repository, "Zs", new FxZfHandler<ZsYz>());
			logger.info("End of calFxZsSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZsSw6() {
		Exception t = null;
		try {
			calFx(6, ZsYz.class, ZsNums.class, FxSw6.class, repositories.zsyzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zs");
			calFxZf(6, ZsZfYz.class, ZsYz.class, FxSw6.class, ZsNums.class, repositories.zszfyzRepository,
					repositories.zsyzRepository, repositories.fxsw6Repository, "Zs", new FxZfHandler<ZsYz>());
			logger.info("End of calFxZsSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZsSw7() {
		Exception t = null;
		try {
			calFx(7, ZsYz.class, ZsNums.class, FxSw7.class, repositories.zsyzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zs");
			calFxZf(7, ZsZfYz.class, ZsYz.class, FxSw7.class, ZsNums.class, repositories.zszfyzRepository,
					repositories.zsyzRepository, repositories.fxsw7Repository, "Zs", new FxZfHandler<ZsYz>());
			logger.info("End of calFxZsSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZsSw8() {
		Exception t = null;
		try {
			calFx(8, ZsYz.class, ZsNums.class, FxSw8.class, repositories.zsyzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zs");
			calFxZf(8, ZsZfYz.class, ZsYz.class, FxSw8.class, ZsNums.class, repositories.zszfyzRepository,
					repositories.zsyzRepository, repositories.fxsw8Repository, "Zs", new FxZfHandler<ZsYz>());
			logger.info("End of calFxZsSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZsSw9() {
		Exception t = null;
		try {
			calFx(9, ZsYz.class, ZsNums.class, FxSw9.class, repositories.zsyzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zs");
			calFxZf(9, ZsZfYz.class, ZsYz.class, FxSw9.class, ZsNums.class, repositories.zszfyzRepository,
					repositories.zsyzRepository, repositories.fxsw9Repository, "Zs", new FxZfHandler<ZsYz>());
			logger.info("End of calFxZsSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxSw1() {
		Exception t = null;
		try {
			calFx(1, WxYz.class, WxNums.class, FxSw1.class, repositories.wxyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Wx");
			calFxZf(1, WxZfYz.class, WxYz.class, FxSw1.class, WxNums.class, repositories.wxzfyzRepository,
					repositories.wxyzRepository, repositories.fxsw1Repository, "Wx", new FxZfHandler<WxYz>());
			logger.info("End of calFxWxSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxSw2() {
		Exception t = null;
		try {
			calFx(2, WxYz.class, WxNums.class, FxSw2.class, repositories.wxyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Wx");
			calFxZf(2, WxZfYz.class, WxYz.class, FxSw2.class, WxNums.class, repositories.wxzfyzRepository,
					repositories.wxyzRepository, repositories.fxsw2Repository, "Wx", new FxZfHandler<WxYz>());
			logger.info("End of calFxWxSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxSw3() {
		Exception t = null;
		try {
			calFx(3, WxYz.class, WxNums.class, FxSw3.class, repositories.wxyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Wx");
			calFxZf(3, WxZfYz.class, WxYz.class, FxSw3.class, WxNums.class, repositories.wxzfyzRepository,
					repositories.wxyzRepository, repositories.fxsw3Repository, "Wx", new FxZfHandler<WxYz>());
			logger.info("End of calFxWxSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxSw4() {
		Exception t = null;
		try {
			calFx(4, WxYz.class, WxNums.class, FxSw4.class, repositories.wxyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Wx");
			calFxZf(4, WxZfYz.class, WxYz.class, FxSw4.class, WxNums.class, repositories.wxzfyzRepository,
					repositories.wxyzRepository, repositories.fxsw4Repository, "Wx", new FxZfHandler<WxYz>());
			logger.info("End of calFxWxSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxSw5() {
		Exception t = null;
		try {
			calFx(5, WxYz.class, WxNums.class, FxSw5.class, repositories.wxyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Wx");
			calFxZf(5, WxZfYz.class, WxYz.class, FxSw5.class, WxNums.class, repositories.wxzfyzRepository,
					repositories.wxyzRepository, repositories.fxsw5Repository, "Wx", new FxZfHandler<WxYz>());
			logger.info("End of calFxWxSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxdsSw1() {
		Exception t = null;
		try {
			calFx(1, WxdsYz.class, WxDsNums.class, FxSw1.class, repositories.wxdsyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Wxds");
			calFxZf(1, WxdsZfYz.class, WxdsYz.class, FxSw1.class, WxDsNums.class, repositories.wxdszfyzRepository,
					repositories.wxdsyzRepository, repositories.fxsw1Repository, "Wxds", new FxZfHandler<WxdsYz>());
			logger.info("End of calFxWxdsSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxdsSw2() {
		Exception t = null;
		try {
			calFx(2, WxdsYz.class, WxDsNums.class, FxSw2.class, repositories.wxdsyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Wxds");
			calFxZf(2, WxdsZfYz.class, WxdsYz.class, FxSw2.class, WxDsNums.class, repositories.wxdszfyzRepository,
					repositories.wxdsyzRepository, repositories.fxsw2Repository, "Wxds", new FxZfHandler<WxdsYz>());
			logger.info("End of calFxWxdsSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxdsSw3() {
		Exception t = null;
		try {
			calFx(3, WxdsYz.class, WxDsNums.class, FxSw3.class, repositories.wxdsyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Wxds");
			calFxZf(3, WxdsZfYz.class, WxdsYz.class, FxSw3.class, WxDsNums.class, repositories.wxdszfyzRepository,
					repositories.wxdsyzRepository, repositories.fxsw3Repository, "Wxds", new FxZfHandler<WxdsYz>());
			logger.info("End of calFxWxdsSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxdsSw4() {
		Exception t = null;
		try {
			calFx(4, WxdsYz.class, WxDsNums.class, FxSw4.class, repositories.wxdsyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Wxds");
			calFxZf(4, WxdsZfYz.class, WxdsYz.class, FxSw4.class, WxDsNums.class, repositories.wxdszfyzRepository,
					repositories.wxdsyzRepository, repositories.fxsw4Repository, "Wxds", new FxZfHandler<WxdsYz>());
			logger.info("End of calFxWxdsSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxdsSw5() {
		Exception t = null;
		try {
			calFx(5, WxdsYz.class, WxDsNums.class, FxSw5.class, repositories.wxdsyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Wxds");
			calFxZf(5, WxdsZfYz.class, WxdsYz.class, FxSw5.class, WxDsNums.class, repositories.wxdszfyzRepository,
					repositories.wxdsyzRepository, repositories.fxsw5Repository, "Wxds", new FxZfHandler<WxdsYz>());
			logger.info("End of calFxWxdsSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxdsSw6() {
		Exception t = null;
		try {
			calFx(6, WxdsYz.class, WxDsNums.class, FxSw6.class, repositories.wxdsyzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Wxds");
			calFxZf(6, WxdsZfYz.class, WxdsYz.class, FxSw6.class, WxDsNums.class, repositories.wxdszfyzRepository,
					repositories.wxdsyzRepository, repositories.fxsw6Repository, "Wxds", new FxZfHandler<WxdsYz>());
			logger.info("End of calFxWxdsSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxdsSw7() {
		Exception t = null;
		try {
			calFx(7, WxdsYz.class, WxDsNums.class, FxSw7.class, repositories.wxdsyzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Wxds");
			calFxZf(7, WxdsZfYz.class, WxdsYz.class, FxSw7.class, WxDsNums.class, repositories.wxdszfyzRepository,
					repositories.wxdsyzRepository, repositories.fxsw7Repository, "Wxds", new FxZfHandler<WxdsYz>());
			logger.info("End of calFxWxdsSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxdsSw8() {
		Exception t = null;
		try {
			calFx(8, WxdsYz.class, WxDsNums.class, FxSw8.class, repositories.wxdsyzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Wxds");
			calFxZf(8, WxdsZfYz.class, WxdsYz.class, FxSw8.class, WxDsNums.class, repositories.wxdszfyzRepository,
					repositories.wxdsyzRepository, repositories.fxsw8Repository, "Wxds", new FxZfHandler<WxdsYz>());
			logger.info("End of calFxWxdsSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxdsSw9() {
		Exception t = null;
		try {
			calFx(9, WxdsYz.class, WxDsNums.class, FxSw9.class, repositories.wxdsyzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Wxds");
			calFxZf(9, WxdsZfYz.class, WxdsYz.class, FxSw9.class, WxDsNums.class, repositories.wxdszfyzRepository,
					repositories.wxdsyzRepository, repositories.fxsw9Repository, "Wxds", new FxZfHandler<WxdsYz>());
			logger.info("End of calFxWxdsSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxWxdsSw10() {
		Exception t = null;
		try {
			calFx(10, WxdsYz.class, WxDsNums.class, FxSw10.class, repositories.wxdsyzRepository,
					repositories.fxsw10Repository, new FxHandler(), "Wxds");
			calFxZf(10, WxdsZfYz.class, WxdsYz.class, FxSw10.class, WxDsNums.class, repositories.wxdszfyzRepository,
					repositories.wxdsyzRepository, repositories.fxsw10Repository, "Wxds", new FxZfHandler<WxdsYz>());
			logger.info("End of calFxWxdsSw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw1() {
		Exception t = null;
		try {
			calFx(1, PdYz.class, PdNums.class, FxSw1.class, repositories.pdyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Pd");
			calFxZf(1, PdZfYz.class, PdYz.class, FxSw1.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw1Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw2() {
		Exception t = null;
		try {
			calFx(2, PdYz.class, PdNums.class, FxSw2.class, repositories.pdyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Pd");
			calFxZf(2, PdZfYz.class, PdYz.class, FxSw2.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw2Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw3() {
		Exception t = null;
		try {
			calFx(3, PdYz.class, PdNums.class, FxSw3.class, repositories.pdyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Pd");
			calFxZf(3, PdZfYz.class, PdYz.class, FxSw3.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw3Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw4() {
		Exception t = null;
		try {
			calFx(4, PdYz.class, PdNums.class, FxSw4.class, repositories.pdyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Pd");
			calFxZf(4, PdZfYz.class, PdYz.class, FxSw4.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw4Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw5() {
		Exception t = null;
		try {
			calFx(5, PdYz.class, PdNums.class, FxSw5.class, repositories.pdyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Pd");
			calFxZf(5, PdZfYz.class, PdYz.class, FxSw5.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw5Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw6() {
		Exception t = null;
		try {
			calFx(6, PdYz.class, PdNums.class, FxSw6.class, repositories.pdyzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Pd");
			calFxZf(6, PdZfYz.class, PdYz.class, FxSw6.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw6Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw7() {
		Exception t = null;
		try {
			calFx(7, PdYz.class, PdNums.class, FxSw7.class, repositories.pdyzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Pd");
			calFxZf(7, PdZfYz.class, PdYz.class, FxSw7.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw7Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw8() {
		Exception t = null;
		try {
			calFx(8, PdYz.class, PdNums.class, FxSw8.class, repositories.pdyzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Pd");
			calFxZf(8, PdZfYz.class, PdYz.class, FxSw8.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw8Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw9() {
		Exception t = null;
		try {
			calFx(9, PdYz.class, PdNums.class, FxSw9.class, repositories.pdyzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Pd");
			calFxZf(9, PdZfYz.class, PdYz.class, FxSw9.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw9Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw10() {
		Exception t = null;
		try {
			calFx(10, PdYz.class, PdNums.class, FxSw10.class, repositories.pdyzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Pd");
			calFxZf(10, PdZfYz.class, PdYz.class, FxSw10.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw10Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw11() {
		Exception t = null;
		try {
			calFx(11, PdYz.class, PdNums.class, FxSw11.class, repositories.pdyzRepository, repositories.fxsw11Repository,
					new FxHandler(), "Pd");
			calFxZf(11, PdZfYz.class, PdYz.class, FxSw11.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw11Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxPdSw12() {
		Exception t = null;
		try {
			calFx(12, PdYz.class, PdNums.class, FxSw12.class, repositories.pdyzRepository, repositories.fxsw12Repository,
					new FxHandler(), "Pd");
			calFxZf(12, PdZfYz.class, PdYz.class, FxSw12.class, PdNums.class, repositories.pdzfyzRepository,
					repositories.pdyzRepository, repositories.fxsw12Repository, "Pd", new FxZfHandler<PdYz>());
			logger.info("End of calFxPdSw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw1() {
		Exception t = null;
		try {
			calFxFd(1, FxSw1.class, repositories.fxsw1Repository);
			calFxFdZf(1, FxSw1.class, repositories.fxsw1Repository);
			logger.info("End of calFxFdSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw2() {
		Exception t = null;
		try {
			calFxFd(2, FxSw2.class, repositories.fxsw2Repository);
			calFxFdZf(2, FxSw2.class, repositories.fxsw2Repository);
			logger.info("End of calFxFdSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw3() {
		Exception t = null;
		try {
			calFxFd(3, FxSw3.class, repositories.fxsw3Repository);
			calFxFdZf(3, FxSw3.class, repositories.fxsw3Repository);
			logger.info("End of calFxFdSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw4() {
		Exception t = null;
		try {
			calFxFd(4, FxSw4.class, repositories.fxsw4Repository);
			calFxFdZf(4, FxSw4.class, repositories.fxsw4Repository);
			logger.info("End of calFxFdSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw5() {
		Exception t = null;
		try {
			calFxFd(5, FxSw5.class, repositories.fxsw5Repository);
			calFxFdZf(5, FxSw5.class, repositories.fxsw5Repository);
			logger.info("End of calFxFdSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw6() {
		Exception t = null;
		try {
			calFxFd(6, FxSw6.class, repositories.fxsw6Repository);
			calFxFdZf(6, FxSw6.class, repositories.fxsw6Repository);
			logger.info("End of calFxFdSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw7() {
		Exception t = null;
		try {
			calFxFd(7, FxSw7.class, repositories.fxsw7Repository);
			calFxFdZf(7, FxSw7.class, repositories.fxsw7Repository);
			logger.info("End of calFxFdSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw8() {
		Exception t = null;
		try {
			calFxFd(8, FxSw8.class, repositories.fxsw8Repository);
			calFxFdZf(8, FxSw8.class, repositories.fxsw8Repository);
			logger.info("End of calFxFdSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw9() {
		Exception t = null;
		try {
			calFxFd(9, FxSw9.class, repositories.fxsw9Repository);
			calFxFdZf(9, FxSw9.class, repositories.fxsw9Repository);
			logger.info("End of calFxFdSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw10() {
		Exception t = null;
		try {
			calFxFd(10, FxSw10.class, repositories.fxsw10Repository);
			calFxFdZf(10, FxSw10.class, repositories.fxsw10Repository);
			logger.info("End of calFxFdSw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw11() {
		Exception t = null;
		try {
			calFxFd(11, FxSw11.class, repositories.fxsw11Repository);
			calFxFdZf(11, FxSw11.class, repositories.fxsw11Repository);
			logger.info("End of calFxFdSw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxFdSw12() {
		Exception t = null;
		try {
			calFxFd(12, FxSw12.class, repositories.fxsw12Repository);
			calFxFdZf(12, FxSw12.class, repositories.fxsw12Repository);
			logger.info("End of calFxFdSw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQqSw1() {
		Exception t = null;
		try {
			calFx(1, QqYz.class, QqNums.class, FxSw1.class, repositories.qqyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Qq");
			calFxZf(1, QqZfYz.class, QqYz.class, FxSw1.class, QqNums.class, repositories.qqzfyzRepository,
					repositories.qqyzRepository, repositories.fxsw1Repository, "Qq", new FxZfHandler<QqYz>());
			logger.info("End of calFxQqSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQqSw2() {
		Exception t = null;
		try {
			calFx(2, QqYz.class, QqNums.class, FxSw2.class, repositories.qqyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Qq");
			calFxZf(2, QqZfYz.class, QqYz.class, FxSw2.class, QqNums.class, repositories.qqzfyzRepository,
					repositories.qqyzRepository, repositories.fxsw2Repository, "Qq", new FxZfHandler<QqYz>());
			logger.info("End of calFxQqSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQqSw3() {
		Exception t = null;
		try {
			calFx(3, QqYz.class, QqNums.class, FxSw3.class, repositories.qqyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Qq");
			calFxZf(3, QqZfYz.class, QqYz.class, FxSw3.class, QqNums.class, repositories.qqzfyzRepository,
					repositories.qqyzRepository, repositories.fxsw3Repository, "Qq", new FxZfHandler<QqYz>());
			logger.info("End of calFxQqSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQqSw4() {
		Exception t = null;
		try {
			calFx(4, QqYz.class, QqNums.class, FxSw4.class, repositories.qqyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Qq");
			calFxZf(4, QqZfYz.class, QqYz.class, FxSw4.class, QqNums.class, repositories.qqzfyzRepository,
					repositories.qqyzRepository, repositories.fxsw4Repository, "Qq", new FxZfHandler<QqYz>());
			logger.info("End of calFxQqSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQqSw5() {
		Exception t = null;
		try {
			calFx(5, QqYz.class, QqNums.class, FxSw5.class, repositories.qqyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Qq");
			calFxZf(5, QqZfYz.class, QqYz.class, FxSw5.class, QqNums.class, repositories.qqzfyzRepository,
					repositories.qqyzRepository, repositories.fxsw5Repository, "Qq", new FxZfHandler<QqYz>());
			logger.info("End of calFxQqSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQqSw6() {
		Exception t = null;
		try {
			calFx(6, QqYz.class, QqNums.class, FxSw6.class, repositories.qqyzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Qq");
			calFxZf(6, QqZfYz.class, QqYz.class, FxSw6.class, QqNums.class, repositories.qqzfyzRepository,
					repositories.qqyzRepository, repositories.fxsw6Repository, "Qq", new FxZfHandler<QqYz>());
			logger.info("End of calFxQqSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQqSw7() {
		Exception t = null;
		try {
			calFx(7, QqYz.class, QqNums.class, FxSw7.class, repositories.qqyzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Qq");
			calFxZf(7, QqZfYz.class, QqYz.class, FxSw7.class, QqNums.class, repositories.qqzfyzRepository,
					repositories.qqyzRepository, repositories.fxsw7Repository, "Qq", new FxZfHandler<QqYz>());
			logger.info("End of calFxQqSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQiwSw1() {
		Exception t = null;
		try {
			calFx(1, QiwYz.class, QiwNums.class, FxSw1.class, repositories.qiwYzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Qiw");
			calFxZf(1, QiwZfYz.class, QiwYz.class, FxSw1.class, QiwNums.class, repositories.qiwzfYzRepository,
					repositories.qiwYzRepository, repositories.fxsw1Repository, "Qiw", new FxZfHandler<QiwYz>());
			logger.info("End of calFxQiwSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQiwSw2() {
		Exception t = null;
		try {
			calFx(2, QiwYz.class, QiwNums.class, FxSw2.class, repositories.qiwYzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Qiw");
			calFxZf(2, QiwZfYz.class, QiwYz.class, FxSw2.class, QiwNums.class, repositories.qiwzfYzRepository,
					repositories.qiwYzRepository, repositories.fxsw2Repository, "Qiw", new FxZfHandler<QiwYz>());
			logger.info("End of calFxQiwSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQiwSw3() {
		Exception t = null;
		try {
			calFx(3, QiwYz.class, QiwNums.class, FxSw3.class, repositories.qiwYzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Qiw");
			calFxZf(3, QiwZfYz.class, QiwYz.class, FxSw3.class, QiwNums.class, repositories.qiwzfYzRepository,
					repositories.qiwYzRepository, repositories.fxsw3Repository, "Qiw", new FxZfHandler<QiwYz>());
			logger.info("End of calFxQiwSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQiwSw4() {
		Exception t = null;
		try {
			calFx(4, QiwYz.class, QiwNums.class, FxSw4.class, repositories.qiwYzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Qiw");
			calFxZf(4, QiwZfYz.class, QiwYz.class, FxSw4.class, QiwNums.class, repositories.qiwzfYzRepository,
					repositories.qiwYzRepository, repositories.fxsw4Repository, "Qiw", new FxZfHandler<QiwYz>());
			logger.info("End of calFxQiwSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQiwSw5() {
		Exception t = null;
		try {
			calFx(5, QiwYz.class, QiwNums.class, FxSw5.class, repositories.qiwYzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Qiw");
			calFxZf(5, QiwZfYz.class, QiwYz.class, FxSw5.class, QiwNums.class, repositories.qiwzfYzRepository,
					repositories.qiwYzRepository, repositories.fxsw5Repository, "Qiw", new FxZfHandler<QiwYz>());
			logger.info("End of calFxQiwSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQiwSw6() {
		Exception t = null;
		try {
			calFx(6, QiwYz.class, QiwNums.class, FxSw6.class, repositories.qiwYzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Qiw");
			calFxZf(6, QiwZfYz.class, QiwYz.class, FxSw6.class, QiwNums.class, repositories.qiwzfYzRepository,
					repositories.qiwYzRepository, repositories.fxsw6Repository, "Qiw", new FxZfHandler<QiwYz>());
			logger.info("End of calFxQiwSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxQiwSw7() {
		Exception t = null;
		try {
			calFx(7, QiwYz.class, QiwNums.class, FxSw7.class, repositories.qiwYzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Qiw");
			calFxZf(7, QiwZfYz.class, QiwYz.class, FxSw7.class, QiwNums.class, repositories.qiwzfYzRepository,
					repositories.qiwYzRepository, repositories.fxsw7Repository, "Qiw", new FxZfHandler<QiwYz>());
			logger.info("End of calFxQiwSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw1() {
		Exception t = null;
		try {
			calFx(1, TwelveYz.class, TwelveNums.class, FxSw1.class, repositories.twelveyzRepository,
					repositories.fxsw1Repository, new FxHandler(), "Twelve");
			calFxZf(1, TwelveZfYz.class, TwelveYz.class, FxSw1.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw1Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw2() {
		Exception t = null;
		try {
			calFx(2, TwelveYz.class, TwelveNums.class, FxSw2.class, repositories.twelveyzRepository,
					repositories.fxsw2Repository, new FxHandler(), "Twelve");
			calFxZf(2, TwelveZfYz.class, TwelveYz.class, FxSw2.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw2Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw3() {
		Exception t = null;
		try {
			calFx(3, TwelveYz.class, TwelveNums.class, FxSw3.class, repositories.twelveyzRepository,
					repositories.fxsw3Repository, new FxHandler(), "Twelve");
			calFxZf(3, TwelveZfYz.class, TwelveYz.class, FxSw3.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw3Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw4() {
		Exception t = null;
		try {
			calFx(4, TwelveYz.class, TwelveNums.class, FxSw4.class, repositories.twelveyzRepository,
					repositories.fxsw4Repository, new FxHandler(), "Twelve");
			calFxZf(4, TwelveZfYz.class, TwelveYz.class, FxSw4.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw4Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw5() {
		Exception t = null;
		try {
			calFx(5, TwelveYz.class, TwelveNums.class, FxSw5.class, repositories.twelveyzRepository,
					repositories.fxsw5Repository, new FxHandler(), "Twelve");
			calFxZf(5, TwelveZfYz.class, TwelveYz.class, FxSw5.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw5Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw6() {
		Exception t = null;
		try {
			calFx(6, TwelveYz.class, TwelveNums.class, FxSw6.class, repositories.twelveyzRepository,
					repositories.fxsw6Repository, new FxHandler(), "Twelve");
			calFxZf(6, TwelveZfYz.class, TwelveYz.class, FxSw6.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw6Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw7() {
		Exception t = null;
		try {
			calFx(7, TwelveYz.class, TwelveNums.class, FxSw7.class, repositories.twelveyzRepository,
					repositories.fxsw7Repository, new FxHandler(), "Twelve");
			calFxZf(7, TwelveZfYz.class, TwelveYz.class, FxSw7.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw7Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw8() {
		Exception t = null;
		try {
			calFx(8, TwelveYz.class, TwelveNums.class, FxSw8.class, repositories.twelveyzRepository,
					repositories.fxsw8Repository, new FxHandler(), "Twelve");
			calFxZf(8, TwelveZfYz.class, TwelveYz.class, FxSw8.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw8Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw9() {
		Exception t = null;
		try {
			calFx(9, TwelveYz.class, TwelveNums.class, FxSw9.class, repositories.twelveyzRepository,
					repositories.fxsw9Repository, new FxHandler(), "Twelve");
			calFxZf(9, TwelveZfYz.class, TwelveYz.class, FxSw9.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw9Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw10() {
		Exception t = null;
		try {
			calFx(10, TwelveYz.class, TwelveNums.class, FxSw10.class, repositories.twelveyzRepository,
					repositories.fxsw10Repository, new FxHandler(), "Twelve");
			calFxZf(10, TwelveZfYz.class, TwelveYz.class, FxSw10.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw10Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw11() {
		Exception t = null;
		try {
			calFx(11, TwelveYz.class, TwelveNums.class, FxSw11.class, repositories.twelveyzRepository,
					repositories.fxsw11Repository, new FxHandler(), "Twelve");
			calFxZf(11, TwelveZfYz.class, TwelveYz.class, FxSw11.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw11Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxTwelveSw12() {
		Exception t = null;
		try {
			calFx(12, TwelveYz.class, TwelveNums.class, FxSw12.class, repositories.twelveyzRepository,
					repositories.fxsw12Repository, new FxHandler(), "Twelve");
			calFxZf(12, TwelveZfYz.class, TwelveYz.class, FxSw12.class, TwelveNums.class, repositories.twelvezfyzRepository,
					repositories.twelveyzRepository, repositories.fxsw12Repository, "Twelve", new FxZfHandler<TwelveYz>());
			logger.info("End of calFxTwelveSw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw1() {
		Exception t = null;
		try {
			calFx(1, SlqYz.class, SlqNums.class, FxSw1.class, repositories.slqyzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Slq");
			calFxZf(1, SlqZfYz.class, SlqYz.class, FxSw1.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw1Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw2() {
		Exception t = null;
		try {
			calFx(2, SlqYz.class, SlqNums.class, FxSw2.class, repositories.slqyzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Slq");
			calFxZf(2, SlqZfYz.class, SlqYz.class, FxSw2.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw2Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw3() {
		Exception t = null;
		try {
			calFx(3, SlqYz.class, SlqNums.class, FxSw3.class, repositories.slqyzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Slq");
			calFxZf(3, SlqZfYz.class, SlqYz.class, FxSw3.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw3Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw4() {
		Exception t = null;
		try {
			calFx(4, SlqYz.class, SlqNums.class, FxSw4.class, repositories.slqyzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Slq");
			calFxZf(4, SlqZfYz.class, SlqYz.class, FxSw4.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw4Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw5() {
		Exception t = null;
		try {
			calFx(5, SlqYz.class, SlqNums.class, FxSw5.class, repositories.slqyzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Slq");
			calFxZf(5, SlqZfYz.class, SlqYz.class, FxSw5.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw5Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw6() {
		Exception t = null;
		try {
			calFx(6, SlqYz.class, SlqNums.class, FxSw6.class, repositories.slqyzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Slq");
			calFxZf(6, SlqZfYz.class, SlqYz.class, FxSw6.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw6Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw7() {
		Exception t = null;
		try {
			calFx(7, SlqYz.class, SlqNums.class, FxSw7.class, repositories.slqyzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Slq");
			calFxZf(7, SlqZfYz.class, SlqYz.class, FxSw7.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw7Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw8() {
		Exception t = null;
		try {
			calFx(8, SlqYz.class, SlqNums.class, FxSw8.class, repositories.slqyzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Slq");
			calFxZf(8, SlqZfYz.class, SlqYz.class, FxSw8.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw8Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw9() {
		Exception t = null;
		try {
			calFx(9, SlqYz.class, SlqNums.class, FxSw9.class, repositories.slqyzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Slq");
			calFxZf(9, SlqZfYz.class, SlqYz.class, FxSw9.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw9Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw10() {
		Exception t = null;
		try {
			calFx(10, SlqYz.class, SlqNums.class, FxSw10.class, repositories.slqyzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Slq");
			calFxZf(10, SlqZfYz.class, SlqYz.class, FxSw10.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw10Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw11() {
		Exception t = null;
		try {
			calFx(11, SlqYz.class, SlqNums.class, FxSw11.class, repositories.slqyzRepository, repositories.fxsw11Repository,
					new FxHandler(), "Slq");
			calFxZf(11, SlqZfYz.class, SlqYz.class, FxSw11.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw11Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSlqSw12() {
		Exception t = null;
		try {
			calFx(12, SlqYz.class, SlqNums.class, FxSw12.class, repositories.slqyzRepository, repositories.fxsw12Repository,
					new FxHandler(), "Slq");
			calFxZf(12, SlqZfYz.class, SlqYz.class, FxSw12.class, SlqNums.class, repositories.slqzfyzRepository,
					repositories.slqyzRepository, repositories.fxsw12Repository, "Slq", new FxZfHandler<SlqYz>());
			logger.info("End of calFxSlqSw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx1Yz.class, Zx1Nums.class, FxSw1.class, repositories.zx1yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx1");
			calFxZf(1, Zx1ZfYz.class, Zx1Yz.class, FxSw1.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw1Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx1Yz.class, Zx1Nums.class, FxSw2.class, repositories.zx1yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx1");
			calFxZf(2, Zx1ZfYz.class, Zx1Yz.class, FxSw2.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw2Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx1Yz.class, Zx1Nums.class, FxSw3.class, repositories.zx1yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx1");
			calFxZf(3, Zx1ZfYz.class, Zx1Yz.class, FxSw3.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw3Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx1Yz.class, Zx1Nums.class, FxSw4.class, repositories.zx1yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx1");
			calFxZf(4, Zx1ZfYz.class, Zx1Yz.class, FxSw4.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw4Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx1Yz.class, Zx1Nums.class, FxSw5.class, repositories.zx1yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx1");
			calFxZf(5, Zx1ZfYz.class, Zx1Yz.class, FxSw5.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw5Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx1Yz.class, Zx1Nums.class, FxSw6.class, repositories.zx1yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx1");
			calFxZf(6, Zx1ZfYz.class, Zx1Yz.class, FxSw6.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw6Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx1Yz.class, Zx1Nums.class, FxSw7.class, repositories.zx1yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx1");
			calFxZf(7, Zx1ZfYz.class, Zx1Yz.class, FxSw7.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw7Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx1Yz.class, Zx1Nums.class, FxSw8.class, repositories.zx1yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx1");
			calFxZf(8, Zx1ZfYz.class, Zx1Yz.class, FxSw8.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw8Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx1Yz.class, Zx1Nums.class, FxSw9.class, repositories.zx1yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx1");
			calFxZf(9, Zx1ZfYz.class, Zx1Yz.class, FxSw9.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw9Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx1Yz.class, Zx1Nums.class, FxSw10.class, repositories.zx1yzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Zx1");
			calFxZf(10, Zx1ZfYz.class, Zx1Yz.class, FxSw10.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw10Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx1Yz.class, Zx1Nums.class, FxSw11.class, repositories.zx1yzRepository, repositories.fxsw11Repository,
					new FxHandler(), "Zx1");
			calFxZf(11, Zx1ZfYz.class, Zx1Yz.class, FxSw11.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw11Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx1Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx1Yz.class, Zx1Nums.class, FxSw12.class, repositories.zx1yzRepository, repositories.fxsw12Repository,
					new FxHandler(), "Zx1");
			calFxZf(12, Zx1ZfYz.class, Zx1Yz.class, FxSw12.class, Zx1Nums.class, repositories.zx1zfyzRepository,
					repositories.zx1yzRepository, repositories.fxsw12Repository, "Zx1", new FxZfHandler<Zx1Yz>());
			logger.info("End of calFxZx1Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx2Yz.class, Zx2Nums.class, FxSw1.class, repositories.zx2yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx2");
			calFxZf(1, Zx2ZfYz.class, Zx2Yz.class, FxSw1.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw1Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx2Yz.class, Zx2Nums.class, FxSw2.class, repositories.zx2yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx2");
			calFxZf(2, Zx2ZfYz.class, Zx2Yz.class, FxSw2.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw2Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx2Yz.class, Zx2Nums.class, FxSw3.class, repositories.zx2yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx2");
			calFxZf(3, Zx2ZfYz.class, Zx2Yz.class, FxSw3.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw3Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx2Yz.class, Zx2Nums.class, FxSw4.class, repositories.zx2yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx2");
			calFxZf(4, Zx2ZfYz.class, Zx2Yz.class, FxSw4.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw4Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx2Yz.class, Zx2Nums.class, FxSw5.class, repositories.zx2yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx2");
			calFxZf(5, Zx2ZfYz.class, Zx2Yz.class, FxSw5.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw5Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx2Yz.class, Zx2Nums.class, FxSw6.class, repositories.zx2yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx2");
			calFxZf(6, Zx2ZfYz.class, Zx2Yz.class, FxSw6.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw6Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx2Yz.class, Zx2Nums.class, FxSw7.class, repositories.zx2yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx2");
			calFxZf(7, Zx2ZfYz.class, Zx2Yz.class, FxSw7.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw7Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx2Yz.class, Zx2Nums.class, FxSw8.class, repositories.zx2yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx2");
			calFxZf(8, Zx2ZfYz.class, Zx2Yz.class, FxSw8.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw8Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx2Yz.class, Zx2Nums.class, FxSw9.class, repositories.zx2yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx2");
			calFxZf(9, Zx2ZfYz.class, Zx2Yz.class, FxSw9.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw9Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx2Yz.class, Zx2Nums.class, FxSw10.class, repositories.zx2yzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Zx2");
			calFxZf(10, Zx2ZfYz.class, Zx2Yz.class, FxSw10.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw10Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx2Yz.class, Zx2Nums.class, FxSw11.class, repositories.zx2yzRepository, repositories.fxsw11Repository,
					new FxHandler(), "Zx2");
			calFxZf(11, Zx2ZfYz.class, Zx2Yz.class, FxSw11.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw11Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx2Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx2Yz.class, Zx2Nums.class, FxSw12.class, repositories.zx2yzRepository, repositories.fxsw12Repository,
					new FxHandler(), "Zx2");
			calFxZf(12, Zx2ZfYz.class, Zx2Yz.class, FxSw12.class, Zx2Nums.class, repositories.zx2zfyzRepository,
					repositories.zx2yzRepository, repositories.fxsw12Repository, "Zx2", new FxZfHandler<Zx2Yz>());
			logger.info("End of calFxZx2Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx3Yz.class, Zx3Nums.class, FxSw1.class, repositories.zx3yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx3");
			calFxZf(1, Zx3ZfYz.class, Zx3Yz.class, FxSw1.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw1Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx3Yz.class, Zx3Nums.class, FxSw2.class, repositories.zx3yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx3");
			calFxZf(2, Zx3ZfYz.class, Zx3Yz.class, FxSw2.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw2Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx3Yz.class, Zx3Nums.class, FxSw3.class, repositories.zx3yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx3");
			calFxZf(3, Zx3ZfYz.class, Zx3Yz.class, FxSw3.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw3Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx3Yz.class, Zx3Nums.class, FxSw4.class, repositories.zx3yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx3");
			calFxZf(4, Zx3ZfYz.class, Zx3Yz.class, FxSw4.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw4Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx3Yz.class, Zx3Nums.class, FxSw5.class, repositories.zx3yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx3");
			calFxZf(5, Zx3ZfYz.class, Zx3Yz.class, FxSw5.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw5Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx3Yz.class, Zx3Nums.class, FxSw6.class, repositories.zx3yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx3");
			calFxZf(6, Zx3ZfYz.class, Zx3Yz.class, FxSw6.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw6Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx3Yz.class, Zx3Nums.class, FxSw7.class, repositories.zx3yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx3");
			calFxZf(7, Zx3ZfYz.class, Zx3Yz.class, FxSw7.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw7Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx3Yz.class, Zx3Nums.class, FxSw8.class, repositories.zx3yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx3");
			calFxZf(8, Zx3ZfYz.class, Zx3Yz.class, FxSw8.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw8Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx3Yz.class, Zx3Nums.class, FxSw9.class, repositories.zx3yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx3");
			calFxZf(9, Zx3ZfYz.class, Zx3Yz.class, FxSw9.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw9Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx3Yz.class, Zx3Nums.class, FxSw10.class, repositories.zx3yzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Zx3");
			calFxZf(10, Zx3ZfYz.class, Zx3Yz.class, FxSw10.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw10Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx3Yz.class, Zx3Nums.class, FxSw11.class, repositories.zx3yzRepository, repositories.fxsw11Repository,
					new FxHandler(), "Zx3");
			calFxZf(11, Zx3ZfYz.class, Zx3Yz.class, FxSw11.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw11Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx3Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx3Yz.class, Zx3Nums.class, FxSw12.class, repositories.zx3yzRepository, repositories.fxsw12Repository,
					new FxHandler(), "Zx3");
			calFxZf(12, Zx3ZfYz.class, Zx3Yz.class, FxSw12.class, Zx3Nums.class, repositories.zx3zfyzRepository,
					repositories.zx3yzRepository, repositories.fxsw12Repository, "Zx3", new FxZfHandler<Zx3Yz>());
			logger.info("End of calFxZx3Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx4Yz.class, Zx4Nums.class, FxSw1.class, repositories.zx4yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx4");
			calFxZf(1, Zx4ZfYz.class, Zx4Yz.class, FxSw1.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw1Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx4Yz.class, Zx4Nums.class, FxSw2.class, repositories.zx4yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx4");
			calFxZf(2, Zx4ZfYz.class, Zx4Yz.class, FxSw2.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw2Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx4Yz.class, Zx4Nums.class, FxSw3.class, repositories.zx4yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx4");
			calFxZf(3, Zx4ZfYz.class, Zx4Yz.class, FxSw3.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw3Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx4Yz.class, Zx4Nums.class, FxSw4.class, repositories.zx4yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx4");
			calFxZf(4, Zx4ZfYz.class, Zx4Yz.class, FxSw4.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw4Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx4Yz.class, Zx4Nums.class, FxSw5.class, repositories.zx4yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx4");
			calFxZf(5, Zx4ZfYz.class, Zx4Yz.class, FxSw5.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw5Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx4Yz.class, Zx4Nums.class, FxSw6.class, repositories.zx4yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx4");
			calFxZf(6, Zx4ZfYz.class, Zx4Yz.class, FxSw6.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw6Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx4Yz.class, Zx4Nums.class, FxSw7.class, repositories.zx4yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx4");
			calFxZf(7, Zx4ZfYz.class, Zx4Yz.class, FxSw7.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw7Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx4Yz.class, Zx4Nums.class, FxSw8.class, repositories.zx4yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx4");
			calFxZf(8, Zx4ZfYz.class, Zx4Yz.class, FxSw8.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw8Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx4Yz.class, Zx4Nums.class, FxSw9.class, repositories.zx4yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx4");
			calFxZf(9, Zx4ZfYz.class, Zx4Yz.class, FxSw9.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw9Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx4Yz.class, Zx4Nums.class, FxSw10.class, repositories.zx4yzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Zx4");
			calFxZf(10, Zx4ZfYz.class, Zx4Yz.class, FxSw10.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw10Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx4Yz.class, Zx4Nums.class, FxSw11.class, repositories.zx4yzRepository, repositories.fxsw11Repository,
					new FxHandler(), "Zx4");
			calFxZf(11, Zx4ZfYz.class, Zx4Yz.class, FxSw11.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw11Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx4Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx4Yz.class, Zx4Nums.class, FxSw12.class, repositories.zx4yzRepository, repositories.fxsw12Repository,
					new FxHandler(), "Zx4");
			calFxZf(12, Zx4ZfYz.class, Zx4Yz.class, FxSw12.class, Zx4Nums.class, repositories.zx4zfyzRepository,
					repositories.zx4yzRepository, repositories.fxsw12Repository, "Zx4", new FxZfHandler<Zx4Yz>());
			logger.info("End of calFxZx4Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx5Yz.class, Zx5Nums.class, FxSw1.class, repositories.zx5yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx5");
			calFxZf(1, Zx5ZfYz.class, Zx5Yz.class, FxSw1.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw1Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx5Yz.class, Zx5Nums.class, FxSw2.class, repositories.zx5yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx5");
			calFxZf(2, Zx5ZfYz.class, Zx5Yz.class, FxSw2.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw2Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx5Yz.class, Zx5Nums.class, FxSw3.class, repositories.zx5yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx5");
			calFxZf(3, Zx5ZfYz.class, Zx5Yz.class, FxSw3.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw3Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx5Yz.class, Zx5Nums.class, FxSw4.class, repositories.zx5yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx5");
			calFxZf(4, Zx5ZfYz.class, Zx5Yz.class, FxSw4.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw4Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx5Yz.class, Zx5Nums.class, FxSw5.class, repositories.zx5yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx5");
			calFxZf(5, Zx5ZfYz.class, Zx5Yz.class, FxSw5.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw5Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx5Yz.class, Zx5Nums.class, FxSw6.class, repositories.zx5yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx5");
			calFxZf(6, Zx5ZfYz.class, Zx5Yz.class, FxSw6.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw6Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx5Yz.class, Zx5Nums.class, FxSw7.class, repositories.zx5yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx5");
			calFxZf(7, Zx5ZfYz.class, Zx5Yz.class, FxSw7.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw7Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx5Yz.class, Zx5Nums.class, FxSw8.class, repositories.zx5yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx5");
			calFxZf(8, Zx5ZfYz.class, Zx5Yz.class, FxSw8.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw8Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx5Yz.class, Zx5Nums.class, FxSw9.class, repositories.zx5yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx5");
			calFxZf(9, Zx5ZfYz.class, Zx5Yz.class, FxSw9.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw9Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx5Yz.class, Zx5Nums.class, FxSw10.class, repositories.zx5yzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Zx5");
			calFxZf(10, Zx5ZfYz.class, Zx5Yz.class, FxSw10.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw10Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx5Yz.class, Zx5Nums.class, FxSw11.class, repositories.zx5yzRepository, repositories.fxsw11Repository,
					new FxHandler(), "Zx5");
			calFxZf(11, Zx5ZfYz.class, Zx5Yz.class, FxSw11.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw11Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx5Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx5Yz.class, Zx5Nums.class, FxSw12.class, repositories.zx5yzRepository, repositories.fxsw12Repository,
					new FxHandler(), "Zx5");
			calFxZf(12, Zx5ZfYz.class, Zx5Yz.class, FxSw12.class, Zx5Nums.class, repositories.zx5zfyzRepository,
					repositories.zx5yzRepository, repositories.fxsw12Repository, "Zx5", new FxZfHandler<Zx5Yz>());
			logger.info("End of calFxZx5Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx6Yz.class, Zx6Nums.class, FxSw1.class, repositories.zx6yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx6");
			calFxZf(1, Zx6ZfYz.class, Zx6Yz.class, FxSw1.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw1Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx6Yz.class, Zx6Nums.class, FxSw2.class, repositories.zx6yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx6");
			calFxZf(2, Zx6ZfYz.class, Zx6Yz.class, FxSw2.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw2Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx6Yz.class, Zx6Nums.class, FxSw3.class, repositories.zx6yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx6");
			calFxZf(3, Zx6ZfYz.class, Zx6Yz.class, FxSw3.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw3Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx6Yz.class, Zx6Nums.class, FxSw4.class, repositories.zx6yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx6");
			calFxZf(4, Zx6ZfYz.class, Zx6Yz.class, FxSw4.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw4Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx6Yz.class, Zx6Nums.class, FxSw5.class, repositories.zx6yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx6");
			calFxZf(5, Zx6ZfYz.class, Zx6Yz.class, FxSw5.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw5Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx6Yz.class, Zx6Nums.class, FxSw6.class, repositories.zx6yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx6");
			calFxZf(6, Zx6ZfYz.class, Zx6Yz.class, FxSw6.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw6Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx6Yz.class, Zx6Nums.class, FxSw7.class, repositories.zx6yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx6");
			calFxZf(7, Zx6ZfYz.class, Zx6Yz.class, FxSw7.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw7Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx6Yz.class, Zx6Nums.class, FxSw8.class, repositories.zx6yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx6");
			calFxZf(8, Zx6ZfYz.class, Zx6Yz.class, FxSw8.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw8Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx6Yz.class, Zx6Nums.class, FxSw9.class, repositories.zx6yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx6");
			calFxZf(9, Zx6ZfYz.class, Zx6Yz.class, FxSw9.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw9Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx6Yz.class, Zx6Nums.class, FxSw10.class, repositories.zx6yzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Zx6");
			calFxZf(10, Zx6ZfYz.class, Zx6Yz.class, FxSw10.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw10Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx6Yz.class, Zx6Nums.class, FxSw11.class, repositories.zx6yzRepository, repositories.fxsw11Repository,
					new FxHandler(), "Zx6");
			calFxZf(11, Zx6ZfYz.class, Zx6Yz.class, FxSw11.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw11Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx6Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx6Yz.class, Zx6Nums.class, FxSw12.class, repositories.zx6yzRepository, repositories.fxsw12Repository,
					new FxHandler(), "Zx6");
			calFxZf(12, Zx6ZfYz.class, Zx6Yz.class, FxSw12.class, Zx6Nums.class, repositories.zx6zfyzRepository,
					repositories.zx6yzRepository, repositories.fxsw12Repository, "Zx6", new FxZfHandler<Zx6Yz>());
			logger.info("End of calFxZx6Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx7Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx7Yz.class, Zx7Nums.class, FxSw1.class, repositories.zx7yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx7");
			calFxZf(1, Zx7ZfYz.class, Zx7Yz.class, FxSw1.class, Zx7Nums.class, repositories.zx7zfyzRepository,
					repositories.zx7yzRepository, repositories.fxsw1Repository, "Zx7", new FxZfHandler<Zx7Yz>());
			logger.info("End of calFxZx7Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx7Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx7Yz.class, Zx7Nums.class, FxSw2.class, repositories.zx7yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx7");
			calFxZf(2, Zx7ZfYz.class, Zx7Yz.class, FxSw2.class, Zx7Nums.class, repositories.zx7zfyzRepository,
					repositories.zx7yzRepository, repositories.fxsw2Repository, "Zx7", new FxZfHandler<Zx7Yz>());
			logger.info("End of calFxZx7Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx7Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx7Yz.class, Zx7Nums.class, FxSw3.class, repositories.zx7yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx7");
			calFxZf(3, Zx7ZfYz.class, Zx7Yz.class, FxSw3.class, Zx7Nums.class, repositories.zx7zfyzRepository,
					repositories.zx7yzRepository, repositories.fxsw3Repository, "Zx7", new FxZfHandler<Zx7Yz>());
			logger.info("End of calFxZx7Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx7Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx7Yz.class, Zx7Nums.class, FxSw4.class, repositories.zx7yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx7");
			calFxZf(4, Zx7ZfYz.class, Zx7Yz.class, FxSw4.class, Zx7Nums.class, repositories.zx7zfyzRepository,
					repositories.zx7yzRepository, repositories.fxsw4Repository, "Zx7", new FxZfHandler<Zx7Yz>());
			logger.info("End of calFxZx7Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx7Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx7Yz.class, Zx7Nums.class, FxSw5.class, repositories.zx7yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx7");
			calFxZf(5, Zx7ZfYz.class, Zx7Yz.class, FxSw5.class, Zx7Nums.class, repositories.zx7zfyzRepository,
					repositories.zx7yzRepository, repositories.fxsw5Repository, "Zx7", new FxZfHandler<Zx7Yz>());
			logger.info("End of calFxZx7Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx7Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx7Yz.class, Zx7Nums.class, FxSw6.class, repositories.zx7yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx7");
			calFxZf(6, Zx7ZfYz.class, Zx7Yz.class, FxSw6.class, Zx7Nums.class, repositories.zx7zfyzRepository,
					repositories.zx7yzRepository, repositories.fxsw6Repository, "Zx7", new FxZfHandler<Zx7Yz>());
			logger.info("End of calFxZx7Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx7Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx7Yz.class, Zx7Nums.class, FxSw7.class, repositories.zx7yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx7");
			calFxZf(7, Zx7ZfYz.class, Zx7Yz.class, FxSw7.class, Zx7Nums.class, repositories.zx7zfyzRepository,
					repositories.zx7yzRepository, repositories.fxsw7Repository, "Zx7", new FxZfHandler<Zx7Yz>());
			logger.info("End of calFxZx7Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx7Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx7Yz.class, Zx7Nums.class, FxSw8.class, repositories.zx7yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx7");
			calFxZf(8, Zx7ZfYz.class, Zx7Yz.class, FxSw8.class, Zx7Nums.class, repositories.zx7zfyzRepository,
					repositories.zx7yzRepository, repositories.fxsw8Repository, "Zx7", new FxZfHandler<Zx7Yz>());
			logger.info("End of calFxZx7Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx7Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx7Yz.class, Zx7Nums.class, FxSw9.class, repositories.zx7yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx7");
			calFxZf(9, Zx7ZfYz.class, Zx7Yz.class, FxSw9.class, Zx7Nums.class, repositories.zx7zfyzRepository,
					repositories.zx7yzRepository, repositories.fxsw9Repository, "Zx7", new FxZfHandler<Zx7Yz>());
			logger.info("End of calFxZx7Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx7Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx7Yz.class, Zx7Nums.class, FxSw10.class, repositories.zx7yzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Zx7");
			calFxZf(10, Zx7ZfYz.class, Zx7Yz.class, FxSw10.class, Zx7Nums.class, repositories.zx7zfyzRepository,
					repositories.zx7yzRepository, repositories.fxsw10Repository, "Zx7", new FxZfHandler<Zx7Yz>());
			logger.info("End of calFxZx7Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx8Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx8Yz.class, Zx8Nums.class, FxSw1.class, repositories.zx8yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx8");
			calFxZf(1, Zx8ZfYz.class, Zx8Yz.class, FxSw1.class, Zx8Nums.class, repositories.zx8zfyzRepository,
					repositories.zx8yzRepository, repositories.fxsw1Repository, "Zx8", new FxZfHandler<Zx8Yz>());
			logger.info("End of calFxZx8Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx8Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx8Yz.class, Zx8Nums.class, FxSw2.class, repositories.zx8yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx8");
			calFxZf(2, Zx8ZfYz.class, Zx8Yz.class, FxSw2.class, Zx8Nums.class, repositories.zx8zfyzRepository,
					repositories.zx8yzRepository, repositories.fxsw2Repository, "Zx8", new FxZfHandler<Zx8Yz>());
			logger.info("End of calFxZx8Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx8Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx8Yz.class, Zx8Nums.class, FxSw3.class, repositories.zx8yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx8");
			calFxZf(3, Zx8ZfYz.class, Zx8Yz.class, FxSw3.class, Zx8Nums.class, repositories.zx8zfyzRepository,
					repositories.zx8yzRepository, repositories.fxsw3Repository, "Zx8", new FxZfHandler<Zx8Yz>());
			logger.info("End of calFxZx8Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx8Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx8Yz.class, Zx8Nums.class, FxSw4.class, repositories.zx8yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx8");
			calFxZf(4, Zx8ZfYz.class, Zx8Yz.class, FxSw4.class, Zx8Nums.class, repositories.zx8zfyzRepository,
					repositories.zx8yzRepository, repositories.fxsw4Repository, "Zx8", new FxZfHandler<Zx8Yz>());
			logger.info("End of calFxZx8Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx8Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx8Yz.class, Zx8Nums.class, FxSw5.class, repositories.zx8yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx8");
			calFxZf(5, Zx8ZfYz.class, Zx8Yz.class, FxSw5.class, Zx8Nums.class, repositories.zx8zfyzRepository,
					repositories.zx8yzRepository, repositories.fxsw5Repository, "Zx8", new FxZfHandler<Zx8Yz>());
			logger.info("End of calFxZx8Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx8Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx8Yz.class, Zx8Nums.class, FxSw6.class, repositories.zx8yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx8");
			calFxZf(6, Zx8ZfYz.class, Zx8Yz.class, FxSw6.class, Zx8Nums.class, repositories.zx8zfyzRepository,
					repositories.zx8yzRepository, repositories.fxsw6Repository, "Zx8", new FxZfHandler<Zx8Yz>());
			logger.info("End of calFxZx8Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx8Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx8Yz.class, Zx8Nums.class, FxSw7.class, repositories.zx8yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx8");
			calFxZf(7, Zx8ZfYz.class, Zx8Yz.class, FxSw7.class, Zx8Nums.class, repositories.zx8zfyzRepository,
					repositories.zx8yzRepository, repositories.fxsw7Repository, "Zx8", new FxZfHandler<Zx8Yz>());
			logger.info("End of calFxZx8Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx8Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx8Yz.class, Zx8Nums.class, FxSw8.class, repositories.zx8yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx8");
			calFxZf(8, Zx8ZfYz.class, Zx8Yz.class, FxSw8.class, Zx8Nums.class, repositories.zx8zfyzRepository,
					repositories.zx8yzRepository, repositories.fxsw8Repository, "Zx8", new FxZfHandler<Zx8Yz>());
			logger.info("End of calFxZx8Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx8Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx8Yz.class, Zx8Nums.class, FxSw9.class, repositories.zx8yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx8");
			calFxZf(9, Zx8ZfYz.class, Zx8Yz.class, FxSw9.class, Zx8Nums.class, repositories.zx8zfyzRepository,
					repositories.zx8yzRepository, repositories.fxsw9Repository, "Zx8", new FxZfHandler<Zx8Yz>());
			logger.info("End of calFxZx8Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx8Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx8Yz.class, Zx8Nums.class, FxSw10.class, repositories.zx8yzRepository, repositories.fxsw10Repository,
					new FxHandler(), "Zx8");
			calFxZf(10, Zx8ZfYz.class, Zx8Yz.class, FxSw10.class, Zx8Nums.class, repositories.zx8zfyzRepository,
					repositories.zx8yzRepository, repositories.fxsw10Repository, "Zx8", new FxZfHandler<Zx8Yz>());
			logger.info("End of calFxZx8Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx9Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx9Yz.class, Zx9Nums.class, FxSw1.class, repositories.zx9yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx9");
			calFxZf(1, Zx9ZfYz.class, Zx9Yz.class, FxSw1.class, Zx9Nums.class, repositories.zx9zfyzRepository,
					repositories.zx9yzRepository, repositories.fxsw1Repository, "Zx9", new FxZfHandler<Zx9Yz>());
			logger.info("End of calFxZx9Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx9Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx9Yz.class, Zx9Nums.class, FxSw2.class, repositories.zx9yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx9");
			calFxZf(2, Zx9ZfYz.class, Zx9Yz.class, FxSw2.class, Zx9Nums.class, repositories.zx9zfyzRepository,
					repositories.zx9yzRepository, repositories.fxsw2Repository, "Zx9", new FxZfHandler<Zx9Yz>());
			logger.info("End of calFxZx9Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx9Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx9Yz.class, Zx9Nums.class, FxSw3.class, repositories.zx9yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx9");
			calFxZf(3, Zx9ZfYz.class, Zx9Yz.class, FxSw3.class, Zx9Nums.class, repositories.zx9zfyzRepository,
					repositories.zx9yzRepository, repositories.fxsw3Repository, "Zx9", new FxZfHandler<Zx9Yz>());
			logger.info("End of calFxZx9Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx9Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx9Yz.class, Zx9Nums.class, FxSw4.class, repositories.zx9yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx9");
			calFxZf(4, Zx9ZfYz.class, Zx9Yz.class, FxSw4.class, Zx9Nums.class, repositories.zx9zfyzRepository,
					repositories.zx9yzRepository, repositories.fxsw4Repository, "Zx9", new FxZfHandler<Zx9Yz>());
			logger.info("End of calFxZx9Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx9Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx9Yz.class, Zx9Nums.class, FxSw5.class, repositories.zx9yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx9");
			calFxZf(5, Zx9ZfYz.class, Zx9Yz.class, FxSw5.class, Zx9Nums.class, repositories.zx9zfyzRepository,
					repositories.zx9yzRepository, repositories.fxsw5Repository, "Zx9", new FxZfHandler<Zx9Yz>());
			logger.info("End of calFxZx9Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx10Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx10Yz.class, Zx10Nums.class, FxSw1.class, repositories.zx10yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx10");
			calFxZf(1, Zx10ZfYz.class, Zx10Yz.class, FxSw1.class, Zx10Nums.class, repositories.zx10zfyzRepository,
					repositories.zx10yzRepository, repositories.fxsw1Repository, "Zx10", new FxZfHandler<Zx10Yz>());
			logger.info("End of calFxZx10Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx10Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx10Yz.class, Zx10Nums.class, FxSw2.class, repositories.zx10yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx10");
			calFxZf(2, Zx10ZfYz.class, Zx10Yz.class, FxSw2.class, Zx10Nums.class, repositories.zx10zfyzRepository,
					repositories.zx10yzRepository, repositories.fxsw2Repository, "Zx10", new FxZfHandler<Zx10Yz>());
			logger.info("End of calFxZx10Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx10Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx10Yz.class, Zx10Nums.class, FxSw3.class, repositories.zx10yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx10");
			calFxZf(3, Zx10ZfYz.class, Zx10Yz.class, FxSw3.class, Zx10Nums.class, repositories.zx10zfyzRepository,
					repositories.zx10yzRepository, repositories.fxsw3Repository, "Zx10", new FxZfHandler<Zx10Yz>());
			logger.info("End of calFxZx10Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx10Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx10Yz.class, Zx10Nums.class, FxSw4.class, repositories.zx10yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx10");
			calFxZf(4, Zx10ZfYz.class, Zx10Yz.class, FxSw4.class, Zx10Nums.class, repositories.zx10zfyzRepository,
					repositories.zx10yzRepository, repositories.fxsw4Repository, "Zx10", new FxZfHandler<Zx10Yz>());
			logger.info("End of calFxZx10Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx10Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx10Yz.class, Zx10Nums.class, FxSw5.class, repositories.zx10yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx10");
			calFxZf(5, Zx10ZfYz.class, Zx10Yz.class, FxSw5.class, Zx10Nums.class, repositories.zx10zfyzRepository,
					repositories.zx10yzRepository, repositories.fxsw5Repository, "Zx10", new FxZfHandler<Zx10Yz>());
			logger.info("End of calFxZx10Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx10Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx10Yz.class, Zx10Nums.class, FxSw6.class, repositories.zx10yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx10");
			calFxZf(6, Zx10ZfYz.class, Zx10Yz.class, FxSw6.class, Zx10Nums.class, repositories.zx10zfyzRepository,
					repositories.zx10yzRepository, repositories.fxsw6Repository, "Zx10", new FxZfHandler<Zx10Yz>());
			logger.info("End of calFxZx10Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx10Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx10Yz.class, Zx10Nums.class, FxSw7.class, repositories.zx10yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx10");
			calFxZf(7, Zx10ZfYz.class, Zx10Yz.class, FxSw7.class, Zx10Nums.class, repositories.zx10zfyzRepository,
					repositories.zx10yzRepository, repositories.fxsw7Repository, "Zx10", new FxZfHandler<Zx10Yz>());
			logger.info("End of calFxZx10Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx11Yz.class, Zx11Nums.class, FxSw1.class, repositories.zx11yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx11");
			calFxZf(1, Zx11ZfYz.class, Zx11Yz.class, FxSw1.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw1Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx11Yz.class, Zx11Nums.class, FxSw2.class, repositories.zx11yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx11");
			calFxZf(2, Zx11ZfYz.class, Zx11Yz.class, FxSw2.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw2Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx11Yz.class, Zx11Nums.class, FxSw3.class, repositories.zx11yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx11");
			calFxZf(3, Zx11ZfYz.class, Zx11Yz.class, FxSw3.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw3Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx11Yz.class, Zx11Nums.class, FxSw4.class, repositories.zx11yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx11");
			calFxZf(4, Zx11ZfYz.class, Zx11Yz.class, FxSw4.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw4Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx11Yz.class, Zx11Nums.class, FxSw5.class, repositories.zx11yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx11");
			calFxZf(5, Zx11ZfYz.class, Zx11Yz.class, FxSw5.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw5Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx11Yz.class, Zx11Nums.class, FxSw6.class, repositories.zx11yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx11");
			calFxZf(6, Zx11ZfYz.class, Zx11Yz.class, FxSw6.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw6Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx11Yz.class, Zx11Nums.class, FxSw7.class, repositories.zx11yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx11");
			calFxZf(7, Zx11ZfYz.class, Zx11Yz.class, FxSw7.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw7Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx11Yz.class, Zx11Nums.class, FxSw8.class, repositories.zx11yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx11");
			calFxZf(8, Zx11ZfYz.class, Zx11Yz.class, FxSw8.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw8Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx11Yz.class, Zx11Nums.class, FxSw9.class, repositories.zx11yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx11");
			calFxZf(9, Zx11ZfYz.class, Zx11Yz.class, FxSw9.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw9Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx11Yz.class, Zx11Nums.class, FxSw10.class, repositories.zx11yzRepository,
					repositories.fxsw10Repository, new FxHandler(), "Zx11");
			calFxZf(10, Zx11ZfYz.class, Zx11Yz.class, FxSw10.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw10Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx11Yz.class, Zx11Nums.class, FxSw11.class, repositories.zx11yzRepository,
					repositories.fxsw11Repository, new FxHandler(), "Zx11");
			calFxZf(11, Zx11ZfYz.class, Zx11Yz.class, FxSw11.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw11Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx11Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx11Yz.class, Zx11Nums.class, FxSw12.class, repositories.zx11yzRepository,
					repositories.fxsw12Repository, new FxHandler(), "Zx11");
			calFxZf(12, Zx11ZfYz.class, Zx11Yz.class, FxSw12.class, Zx11Nums.class, repositories.zx11zfyzRepository,
					repositories.zx11yzRepository, repositories.fxsw12Repository, "Zx11", new FxZfHandler<Zx11Yz>());
			logger.info("End of calFxZx11Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx12Yz.class, Zx12Nums.class, FxSw1.class, repositories.zx12yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx12");
			calFxZf(1, Zx12ZfYz.class, Zx12Yz.class, FxSw1.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw1Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx12Yz.class, Zx12Nums.class, FxSw2.class, repositories.zx12yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx12");
			calFxZf(2, Zx12ZfYz.class, Zx12Yz.class, FxSw2.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw2Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx12Yz.class, Zx12Nums.class, FxSw3.class, repositories.zx12yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx12");
			calFxZf(3, Zx12ZfYz.class, Zx12Yz.class, FxSw3.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw3Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx12Yz.class, Zx12Nums.class, FxSw4.class, repositories.zx12yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx12");
			calFxZf(4, Zx12ZfYz.class, Zx12Yz.class, FxSw4.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw4Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx12Yz.class, Zx12Nums.class, FxSw5.class, repositories.zx12yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx12");
			calFxZf(5, Zx12ZfYz.class, Zx12Yz.class, FxSw5.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw5Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx12Yz.class, Zx12Nums.class, FxSw6.class, repositories.zx12yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx12");
			calFxZf(6, Zx12ZfYz.class, Zx12Yz.class, FxSw6.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw6Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx12Yz.class, Zx12Nums.class, FxSw7.class, repositories.zx12yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx12");
			calFxZf(7, Zx12ZfYz.class, Zx12Yz.class, FxSw7.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw7Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx12Yz.class, Zx12Nums.class, FxSw8.class, repositories.zx12yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx12");
			calFxZf(8, Zx12ZfYz.class, Zx12Yz.class, FxSw8.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw8Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx12Yz.class, Zx12Nums.class, FxSw9.class, repositories.zx12yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx12");
			calFxZf(9, Zx12ZfYz.class, Zx12Yz.class, FxSw9.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw9Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx12Yz.class, Zx12Nums.class, FxSw10.class, repositories.zx12yzRepository,
					repositories.fxsw10Repository, new FxHandler(), "Zx12");
			calFxZf(10, Zx12ZfYz.class, Zx12Yz.class, FxSw10.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw10Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx12Yz.class, Zx12Nums.class, FxSw11.class, repositories.zx12yzRepository,
					repositories.fxsw11Repository, new FxHandler(), "Zx12");
			calFxZf(11, Zx12ZfYz.class, Zx12Yz.class, FxSw11.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw11Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx12Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx12Yz.class, Zx12Nums.class, FxSw12.class, repositories.zx12yzRepository,
					repositories.fxsw12Repository, new FxHandler(), "Zx12");
			calFxZf(12, Zx12ZfYz.class, Zx12Yz.class, FxSw12.class, Zx12Nums.class, repositories.zx12zfyzRepository,
					repositories.zx12yzRepository, repositories.fxsw12Repository, "Zx12", new FxZfHandler<Zx12Yz>());
			logger.info("End of calFxZx12Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx13Yz.class, Zx13Nums.class, FxSw1.class, repositories.zx13yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx13");
			calFxZf(1, Zx13ZfYz.class, Zx13Yz.class, FxSw1.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw1Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx13Yz.class, Zx13Nums.class, FxSw2.class, repositories.zx13yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx13");
			calFxZf(2, Zx13ZfYz.class, Zx13Yz.class, FxSw2.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw2Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx13Yz.class, Zx13Nums.class, FxSw3.class, repositories.zx13yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx13");
			calFxZf(3, Zx13ZfYz.class, Zx13Yz.class, FxSw3.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw3Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx13Yz.class, Zx13Nums.class, FxSw4.class, repositories.zx13yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx13");
			calFxZf(4, Zx13ZfYz.class, Zx13Yz.class, FxSw4.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw4Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx13Yz.class, Zx13Nums.class, FxSw5.class, repositories.zx13yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx13");
			calFxZf(5, Zx13ZfYz.class, Zx13Yz.class, FxSw5.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw5Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx13Yz.class, Zx13Nums.class, FxSw6.class, repositories.zx13yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx13");
			calFxZf(6, Zx13ZfYz.class, Zx13Yz.class, FxSw6.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw6Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx13Yz.class, Zx13Nums.class, FxSw7.class, repositories.zx13yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx13");
			calFxZf(7, Zx13ZfYz.class, Zx13Yz.class, FxSw7.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw7Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx13Yz.class, Zx13Nums.class, FxSw8.class, repositories.zx13yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx13");
			calFxZf(8, Zx13ZfYz.class, Zx13Yz.class, FxSw8.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw8Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx13Yz.class, Zx13Nums.class, FxSw9.class, repositories.zx13yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx13");
			calFxZf(9, Zx13ZfYz.class, Zx13Yz.class, FxSw9.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw9Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx13Yz.class, Zx13Nums.class, FxSw10.class, repositories.zx13yzRepository,
					repositories.fxsw10Repository, new FxHandler(), "Zx13");
			calFxZf(10, Zx13ZfYz.class, Zx13Yz.class, FxSw10.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw10Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx13Yz.class, Zx13Nums.class, FxSw11.class, repositories.zx13yzRepository,
					repositories.fxsw11Repository, new FxHandler(), "Zx13");
			calFxZf(11, Zx13ZfYz.class, Zx13Yz.class, FxSw11.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw11Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx13Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx13Yz.class, Zx13Nums.class, FxSw12.class, repositories.zx13yzRepository,
					repositories.fxsw12Repository, new FxHandler(), "Zx13");
			calFxZf(12, Zx13ZfYz.class, Zx13Yz.class, FxSw12.class, Zx13Nums.class, repositories.zx13zfyzRepository,
					repositories.zx13yzRepository, repositories.fxsw12Repository, "Zx13", new FxZfHandler<Zx13Yz>());
			logger.info("End of calFxZx13Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx14Yz.class, Zx14Nums.class, FxSw1.class, repositories.zx14yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx14");
			calFxZf(1, Zx14ZfYz.class, Zx14Yz.class, FxSw1.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw1Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx14Yz.class, Zx14Nums.class, FxSw2.class, repositories.zx14yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx14");
			calFxZf(2, Zx14ZfYz.class, Zx14Yz.class, FxSw2.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw2Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx14Yz.class, Zx14Nums.class, FxSw3.class, repositories.zx14yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx14");
			calFxZf(3, Zx14ZfYz.class, Zx14Yz.class, FxSw3.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw3Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx14Yz.class, Zx14Nums.class, FxSw4.class, repositories.zx14yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx14");
			calFxZf(4, Zx14ZfYz.class, Zx14Yz.class, FxSw4.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw4Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx14Yz.class, Zx14Nums.class, FxSw5.class, repositories.zx14yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx14");
			calFxZf(5, Zx14ZfYz.class, Zx14Yz.class, FxSw5.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw5Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx14Yz.class, Zx14Nums.class, FxSw6.class, repositories.zx14yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx14");
			calFxZf(6, Zx14ZfYz.class, Zx14Yz.class, FxSw6.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw6Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx14Yz.class, Zx14Nums.class, FxSw7.class, repositories.zx14yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx14");
			calFxZf(7, Zx14ZfYz.class, Zx14Yz.class, FxSw7.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw7Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx14Yz.class, Zx14Nums.class, FxSw8.class, repositories.zx14yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx14");
			calFxZf(8, Zx14ZfYz.class, Zx14Yz.class, FxSw8.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw8Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx14Yz.class, Zx14Nums.class, FxSw9.class, repositories.zx14yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx14");
			calFxZf(9, Zx14ZfYz.class, Zx14Yz.class, FxSw9.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw9Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx14Yz.class, Zx14Nums.class, FxSw10.class, repositories.zx14yzRepository,
					repositories.fxsw10Repository, new FxHandler(), "Zx14");
			calFxZf(10, Zx14ZfYz.class, Zx14Yz.class, FxSw10.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw10Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx14Yz.class, Zx14Nums.class, FxSw11.class, repositories.zx14yzRepository,
					repositories.fxsw11Repository, new FxHandler(), "Zx14");
			calFxZf(11, Zx14ZfYz.class, Zx14Yz.class, FxSw11.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw11Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx14Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx14Yz.class, Zx14Nums.class, FxSw12.class, repositories.zx14yzRepository,
					repositories.fxsw12Repository, new FxHandler(), "Zx14");
			calFxZf(12, Zx14ZfYz.class, Zx14Yz.class, FxSw12.class, Zx14Nums.class, repositories.zx14zfyzRepository,
					repositories.zx14yzRepository, repositories.fxsw12Repository, "Zx14", new FxZfHandler<Zx14Yz>());
			logger.info("End of calFxZx14Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx15Yz.class, Zx15Nums.class, FxSw1.class, repositories.zx15yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx15");
			calFxZf(1, Zx15ZfYz.class, Zx15Yz.class, FxSw1.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw1Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx15Yz.class, Zx15Nums.class, FxSw2.class, repositories.zx15yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx15");
			calFxZf(2, Zx15ZfYz.class, Zx15Yz.class, FxSw2.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw2Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx15Yz.class, Zx15Nums.class, FxSw3.class, repositories.zx15yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx15");
			calFxZf(3, Zx15ZfYz.class, Zx15Yz.class, FxSw3.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw3Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx15Yz.class, Zx15Nums.class, FxSw4.class, repositories.zx15yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx15");
			calFxZf(4, Zx15ZfYz.class, Zx15Yz.class, FxSw4.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw4Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx15Yz.class, Zx15Nums.class, FxSw5.class, repositories.zx15yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx15");
			calFxZf(5, Zx15ZfYz.class, Zx15Yz.class, FxSw5.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw5Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx15Yz.class, Zx15Nums.class, FxSw6.class, repositories.zx15yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx15");
			calFxZf(6, Zx15ZfYz.class, Zx15Yz.class, FxSw6.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw6Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx15Yz.class, Zx15Nums.class, FxSw7.class, repositories.zx15yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx15");
			calFxZf(7, Zx15ZfYz.class, Zx15Yz.class, FxSw7.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw7Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx15Yz.class, Zx15Nums.class, FxSw8.class, repositories.zx15yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx15");
			calFxZf(8, Zx15ZfYz.class, Zx15Yz.class, FxSw8.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw8Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx15Yz.class, Zx15Nums.class, FxSw9.class, repositories.zx15yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx15");
			calFxZf(9, Zx15ZfYz.class, Zx15Yz.class, FxSw9.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw9Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx15Yz.class, Zx15Nums.class, FxSw10.class, repositories.zx15yzRepository,
					repositories.fxsw10Repository, new FxHandler(), "Zx15");
			calFxZf(10, Zx15ZfYz.class, Zx15Yz.class, FxSw10.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw10Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx15Yz.class, Zx15Nums.class, FxSw11.class, repositories.zx15yzRepository,
					repositories.fxsw11Repository, new FxHandler(), "Zx15");
			calFxZf(11, Zx15ZfYz.class, Zx15Yz.class, FxSw11.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw11Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx15Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx15Yz.class, Zx15Nums.class, FxSw12.class, repositories.zx15yzRepository,
					repositories.fxsw12Repository, new FxHandler(), "Zx15");
			calFxZf(12, Zx15ZfYz.class, Zx15Yz.class, FxSw12.class, Zx15Nums.class, repositories.zx15zfyzRepository,
					repositories.zx15yzRepository, repositories.fxsw12Repository, "Zx15", new FxZfHandler<Zx15Yz>());
			logger.info("End of calFxZx15Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx16Yz.class, Zx16Nums.class, FxSw1.class, repositories.zx16yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx16");
			calFxZf(1, Zx16ZfYz.class, Zx16Yz.class, FxSw1.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw1Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx16Yz.class, Zx16Nums.class, FxSw2.class, repositories.zx16yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx16");
			calFxZf(2, Zx16ZfYz.class, Zx16Yz.class, FxSw2.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw2Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx16Yz.class, Zx16Nums.class, FxSw3.class, repositories.zx16yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx16");
			calFxZf(3, Zx16ZfYz.class, Zx16Yz.class, FxSw3.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw3Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx16Yz.class, Zx16Nums.class, FxSw4.class, repositories.zx16yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx16");
			calFxZf(4, Zx16ZfYz.class, Zx16Yz.class, FxSw4.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw4Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx16Yz.class, Zx16Nums.class, FxSw5.class, repositories.zx16yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx16");
			calFxZf(5, Zx16ZfYz.class, Zx16Yz.class, FxSw5.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw5Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx16Yz.class, Zx16Nums.class, FxSw6.class, repositories.zx16yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx16");
			calFxZf(6, Zx16ZfYz.class, Zx16Yz.class, FxSw6.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw6Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx16Yz.class, Zx16Nums.class, FxSw7.class, repositories.zx16yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx16");
			calFxZf(7, Zx16ZfYz.class, Zx16Yz.class, FxSw7.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw7Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx16Yz.class, Zx16Nums.class, FxSw8.class, repositories.zx16yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx16");
			calFxZf(8, Zx16ZfYz.class, Zx16Yz.class, FxSw8.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw8Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx16Yz.class, Zx16Nums.class, FxSw9.class, repositories.zx16yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx16");
			calFxZf(9, Zx16ZfYz.class, Zx16Yz.class, FxSw9.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw9Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx16Yz.class, Zx16Nums.class, FxSw10.class, repositories.zx16yzRepository,
					repositories.fxsw10Repository, new FxHandler(), "Zx16");
			calFxZf(10, Zx16ZfYz.class, Zx16Yz.class, FxSw10.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw10Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx16Yz.class, Zx16Nums.class, FxSw11.class, repositories.zx16yzRepository,
					repositories.fxsw11Repository, new FxHandler(), "Zx16");
			calFxZf(11, Zx16ZfYz.class, Zx16Yz.class, FxSw11.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw11Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx16Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx16Yz.class, Zx16Nums.class, FxSw12.class, repositories.zx16yzRepository,
					repositories.fxsw12Repository, new FxHandler(), "Zx16");
			calFxZf(12, Zx16ZfYz.class, Zx16Yz.class, FxSw12.class, Zx16Nums.class, repositories.zx16zfyzRepository,
					repositories.zx16yzRepository, repositories.fxsw12Repository, "Zx16", new FxZfHandler<Zx16Yz>());
			logger.info("End of calFxZx16Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx17Yz.class, Zx17Nums.class, FxSw1.class, repositories.zx17yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx17");
			calFxZf(1, Zx17ZfYz.class, Zx17Yz.class, FxSw1.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw1Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx17Yz.class, Zx17Nums.class, FxSw2.class, repositories.zx17yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx17");
			calFxZf(2, Zx17ZfYz.class, Zx17Yz.class, FxSw2.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw2Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx17Yz.class, Zx17Nums.class, FxSw3.class, repositories.zx17yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx17");
			calFxZf(3, Zx17ZfYz.class, Zx17Yz.class, FxSw3.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw3Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx17Yz.class, Zx17Nums.class, FxSw4.class, repositories.zx17yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx17");
			calFxZf(4, Zx17ZfYz.class, Zx17Yz.class, FxSw4.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw4Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx17Yz.class, Zx17Nums.class, FxSw5.class, repositories.zx17yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx17");
			calFxZf(5, Zx17ZfYz.class, Zx17Yz.class, FxSw5.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw5Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx17Yz.class, Zx17Nums.class, FxSw6.class, repositories.zx17yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx17");
			calFxZf(6, Zx17ZfYz.class, Zx17Yz.class, FxSw6.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw6Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx17Yz.class, Zx17Nums.class, FxSw7.class, repositories.zx17yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx17");
			calFxZf(7, Zx17ZfYz.class, Zx17Yz.class, FxSw7.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw7Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx17Yz.class, Zx17Nums.class, FxSw8.class, repositories.zx17yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx17");
			calFxZf(8, Zx17ZfYz.class, Zx17Yz.class, FxSw8.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw8Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx17Yz.class, Zx17Nums.class, FxSw9.class, repositories.zx17yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx17");
			calFxZf(9, Zx17ZfYz.class, Zx17Yz.class, FxSw9.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw9Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx17Yz.class, Zx17Nums.class, FxSw10.class, repositories.zx17yzRepository,
					repositories.fxsw10Repository, new FxHandler(), "Zx17");
			calFxZf(10, Zx17ZfYz.class, Zx17Yz.class, FxSw10.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw10Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx17Yz.class, Zx17Nums.class, FxSw11.class, repositories.zx17yzRepository,
					repositories.fxsw11Repository, new FxHandler(), "Zx17");
			calFxZf(11, Zx17ZfYz.class, Zx17Yz.class, FxSw11.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw11Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx17Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx17Yz.class, Zx17Nums.class, FxSw12.class, repositories.zx17yzRepository,
					repositories.fxsw12Repository, new FxHandler(), "Zx17");
			calFxZf(12, Zx17ZfYz.class, Zx17Yz.class, FxSw12.class, Zx17Nums.class, repositories.zx17zfyzRepository,
					repositories.zx17yzRepository, repositories.fxsw12Repository, "Zx17", new FxZfHandler<Zx17Yz>());
			logger.info("End of calFxZx17Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw1() {
		Exception t = null;
		try {
			calFx(1, Zx18Yz.class, Zx18Nums.class, FxSw1.class, repositories.zx18yzRepository, repositories.fxsw1Repository,
					new FxHandler(), "Zx18");
			calFxZf(1, Zx18ZfYz.class, Zx18Yz.class, FxSw1.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw1Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw1...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw2() {
		Exception t = null;
		try {
			calFx(2, Zx18Yz.class, Zx18Nums.class, FxSw2.class, repositories.zx18yzRepository, repositories.fxsw2Repository,
					new FxHandler(), "Zx18");
			calFxZf(2, Zx18ZfYz.class, Zx18Yz.class, FxSw2.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw2Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw2...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw3() {
		Exception t = null;
		try {
			calFx(3, Zx18Yz.class, Zx18Nums.class, FxSw3.class, repositories.zx18yzRepository, repositories.fxsw3Repository,
					new FxHandler(), "Zx18");
			calFxZf(3, Zx18ZfYz.class, Zx18Yz.class, FxSw3.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw3Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw3...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw4() {
		Exception t = null;
		try {
			calFx(4, Zx18Yz.class, Zx18Nums.class, FxSw4.class, repositories.zx18yzRepository, repositories.fxsw4Repository,
					new FxHandler(), "Zx18");
			calFxZf(4, Zx18ZfYz.class, Zx18Yz.class, FxSw4.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw4Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw4...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw5() {
		Exception t = null;
		try {
			calFx(5, Zx18Yz.class, Zx18Nums.class, FxSw5.class, repositories.zx18yzRepository, repositories.fxsw5Repository,
					new FxHandler(), "Zx18");
			calFxZf(5, Zx18ZfYz.class, Zx18Yz.class, FxSw5.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw5Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw5...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw6() {
		Exception t = null;
		try {
			calFx(6, Zx18Yz.class, Zx18Nums.class, FxSw6.class, repositories.zx18yzRepository, repositories.fxsw6Repository,
					new FxHandler(), "Zx18");
			calFxZf(6, Zx18ZfYz.class, Zx18Yz.class, FxSw6.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw6Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw6...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw7() {
		Exception t = null;
		try {
			calFx(7, Zx18Yz.class, Zx18Nums.class, FxSw7.class, repositories.zx18yzRepository, repositories.fxsw7Repository,
					new FxHandler(), "Zx18");
			calFxZf(7, Zx18ZfYz.class, Zx18Yz.class, FxSw7.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw7Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw7...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw8() {
		Exception t = null;
		try {
			calFx(8, Zx18Yz.class, Zx18Nums.class, FxSw8.class, repositories.zx18yzRepository, repositories.fxsw8Repository,
					new FxHandler(), "Zx18");
			calFxZf(8, Zx18ZfYz.class, Zx18Yz.class, FxSw8.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw8Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw8...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw9() {
		Exception t = null;
		try {
			calFx(9, Zx18Yz.class, Zx18Nums.class, FxSw9.class, repositories.zx18yzRepository, repositories.fxsw9Repository,
					new FxHandler(), "Zx18");
			calFxZf(9, Zx18ZfYz.class, Zx18Yz.class, FxSw9.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw9Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw9...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw10() {
		Exception t = null;
		try {
			calFx(10, Zx18Yz.class, Zx18Nums.class, FxSw10.class, repositories.zx18yzRepository,
					repositories.fxsw10Repository, new FxHandler(), "Zx18");
			calFxZf(10, Zx18ZfYz.class, Zx18Yz.class, FxSw10.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw10Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw10...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw11() {
		Exception t = null;
		try {
			calFx(11, Zx18Yz.class, Zx18Nums.class, FxSw11.class, repositories.zx18yzRepository,
					repositories.fxsw11Repository, new FxHandler(), "Zx18");
			calFxZf(11, Zx18ZfYz.class, Zx18Yz.class, FxSw11.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw11Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw11...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxZx18Sw12() {
		Exception t = null;
		try {
			calFx(12, Zx18Yz.class, Zx18Nums.class, FxSw12.class, repositories.zx18yzRepository,
					repositories.fxsw12Repository, new FxHandler(), "Zx18");
			calFxZf(12, Zx18ZfYz.class, Zx18Yz.class, FxSw12.class, Zx18Nums.class, repositories.zx18zfyzRepository,
					repositories.zx18yzRepository, repositories.fxsw12Repository, "Zx18", new FxZfHandler<Zx18Yz>());
			logger.info("End of calFxZx18Sw12...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw1() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw1());
			futures.add(repositories.yzService.calFxDsSw1());
			futures.add(repositories.yzService.calFxSwSw1());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxMwSw1());
			futures.add(repositories.yzService.calFxLhSw1());
			futures.add(repositories.yzService.calFxBsSw1());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZsSw1());
			futures.add(repositories.yzService.calFxWxSw1());
			futures.add(repositories.yzService.calFxWxdsSw1());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxPdSw1());
			futures.add(repositories.yzService.calFxFdSw1());
			futures.add(repositories.yzService.calFxQqSw1());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxQiwSw1());
			futures.add(repositories.yzService.calFxTwelveSw1());
			futures.add(repositories.yzService.calFxSlqSw1());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx1Sw1());
			futures.add(repositories.yzService.calFxZx2Sw1());
			futures.add(repositories.yzService.calFxZx3Sw1());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx4Sw1());
			futures.add(repositories.yzService.calFxZx5Sw1());
			futures.add(repositories.yzService.calFxZx6Sw1());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx7Sw1());
			futures.add(repositories.yzService.calFxZx8Sw1());
			futures.add(repositories.yzService.calFxZx9Sw1());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx10Sw1());
			futures.add(repositories.yzService.calFxZx11Sw1());
			futures.add(repositories.yzService.calFxZx12Sw1());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx13Sw1());
			futures.add(repositories.yzService.calFxZx14Sw1());
			futures.add(repositories.yzService.calFxZx15Sw1());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx16Sw1());
			futures.add(repositories.yzService.calFxZx17Sw1());
			futures.add(repositories.yzService.calFxZx18Sw1());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw2() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw2());
			futures.add(repositories.yzService.calFxDsSw2());
			futures.add(repositories.yzService.calFxSwSw2());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxMwSw2());
			futures.add(repositories.yzService.calFxLhSw2());
			futures.add(repositories.yzService.calFxBsSw2());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZsSw2());
			futures.add(repositories.yzService.calFxWxSw2());
			futures.add(repositories.yzService.calFxWxdsSw2());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxPdSw2());
			futures.add(repositories.yzService.calFxFdSw2());
			futures.add(repositories.yzService.calFxQqSw2());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxQiwSw2());
			futures.add(repositories.yzService.calFxTwelveSw2());
			futures.add(repositories.yzService.calFxSlqSw2());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx1Sw2());
			futures.add(repositories.yzService.calFxZx2Sw2());
			futures.add(repositories.yzService.calFxZx3Sw2());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx4Sw2());
			futures.add(repositories.yzService.calFxZx5Sw2());
			futures.add(repositories.yzService.calFxZx6Sw2());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx7Sw2());
			futures.add(repositories.yzService.calFxZx8Sw2());
			futures.add(repositories.yzService.calFxZx9Sw2());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx10Sw2());
			futures.add(repositories.yzService.calFxZx11Sw2());
			futures.add(repositories.yzService.calFxZx12Sw2());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx13Sw2());
			futures.add(repositories.yzService.calFxZx14Sw2());
			futures.add(repositories.yzService.calFxZx15Sw2());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx16Sw2());
			futures.add(repositories.yzService.calFxZx17Sw2());
			futures.add(repositories.yzService.calFxZx18Sw2());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw3() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw3());
			futures.add(repositories.yzService.calFxDsSw3());
			futures.add(repositories.yzService.calFxSwSw3());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxMwSw3());
			futures.add(repositories.yzService.calFxLhSw3());
			futures.add(repositories.yzService.calFxBsSw3());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZsSw3());
			futures.add(repositories.yzService.calFxWxSw3());
			futures.add(repositories.yzService.calFxWxdsSw3());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxPdSw3());
			futures.add(repositories.yzService.calFxFdSw3());
			futures.add(repositories.yzService.calFxQqSw3());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxQiwSw3());
			futures.add(repositories.yzService.calFxTwelveSw3());
			futures.add(repositories.yzService.calFxSlqSw3());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx1Sw3());
			futures.add(repositories.yzService.calFxZx2Sw3());
			futures.add(repositories.yzService.calFxZx3Sw3());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx4Sw3());
			futures.add(repositories.yzService.calFxZx5Sw3());
			futures.add(repositories.yzService.calFxZx6Sw3());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx7Sw3());
			futures.add(repositories.yzService.calFxZx8Sw3());
			futures.add(repositories.yzService.calFxZx9Sw3());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx10Sw3());
			futures.add(repositories.yzService.calFxZx11Sw3());
			futures.add(repositories.yzService.calFxZx12Sw3());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx13Sw3());
			futures.add(repositories.yzService.calFxZx14Sw3());
			futures.add(repositories.yzService.calFxZx15Sw3());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx16Sw3());
			futures.add(repositories.yzService.calFxZx17Sw3());
			futures.add(repositories.yzService.calFxZx18Sw3());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw4() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw4());
			futures.add(repositories.yzService.calFxDsSw4());
			futures.add(repositories.yzService.calFxSwSw4());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxMwSw4());
			futures.add(repositories.yzService.calFxLhSw4());
			futures.add(repositories.yzService.calFxBsSw4());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZsSw4());
			futures.add(repositories.yzService.calFxWxSw4());
			futures.add(repositories.yzService.calFxWxdsSw4());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxPdSw4());
			futures.add(repositories.yzService.calFxFdSw4());
			futures.add(repositories.yzService.calFxQqSw4());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxQiwSw4());
			futures.add(repositories.yzService.calFxTwelveSw4());
			futures.add(repositories.yzService.calFxSlqSw4());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx1Sw4());
			futures.add(repositories.yzService.calFxZx2Sw4());
			futures.add(repositories.yzService.calFxZx3Sw4());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx4Sw4());
			futures.add(repositories.yzService.calFxZx5Sw4());
			futures.add(repositories.yzService.calFxZx6Sw4());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx7Sw4());
			futures.add(repositories.yzService.calFxZx8Sw4());
			futures.add(repositories.yzService.calFxZx9Sw4());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx10Sw4());
			futures.add(repositories.yzService.calFxZx11Sw4());
			futures.add(repositories.yzService.calFxZx12Sw4());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx13Sw4());
			futures.add(repositories.yzService.calFxZx14Sw4());
			futures.add(repositories.yzService.calFxZx15Sw4());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx16Sw4());
			futures.add(repositories.yzService.calFxZx17Sw4());
			futures.add(repositories.yzService.calFxZx18Sw4());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw5() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw5());
			futures.add(repositories.yzService.calFxDsSw5());
			futures.add(repositories.yzService.calFxSwSw5());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxMwSw5());
			futures.add(repositories.yzService.calFxLhSw5());
			futures.add(repositories.yzService.calFxBsSw5());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZsSw5());
			futures.add(repositories.yzService.calFxWxSw5());
			futures.add(repositories.yzService.calFxWxdsSw5());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxPdSw5());
			futures.add(repositories.yzService.calFxFdSw5());
			futures.add(repositories.yzService.calFxQqSw5());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxQiwSw5());
			futures.add(repositories.yzService.calFxTwelveSw5());
			futures.add(repositories.yzService.calFxSlqSw5());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx1Sw5());
			futures.add(repositories.yzService.calFxZx2Sw5());
			futures.add(repositories.yzService.calFxZx3Sw5());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx4Sw5());
			futures.add(repositories.yzService.calFxZx5Sw5());
			futures.add(repositories.yzService.calFxZx6Sw5());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx7Sw5());
			futures.add(repositories.yzService.calFxZx8Sw5());
			futures.add(repositories.yzService.calFxZx9Sw5());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx10Sw5());
			futures.add(repositories.yzService.calFxZx11Sw5());
			futures.add(repositories.yzService.calFxZx12Sw5());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx13Sw5());
			futures.add(repositories.yzService.calFxZx14Sw5());
			futures.add(repositories.yzService.calFxZx15Sw5());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx16Sw5());
			futures.add(repositories.yzService.calFxZx17Sw5());
			futures.add(repositories.yzService.calFxZx18Sw5());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw6() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw6());
			futures.add(repositories.yzService.calFxDsSw6());
			futures.add(repositories.yzService.calFxMwSw6());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxLhSw6());
			futures.add(repositories.yzService.calFxBsSw6());
			futures.add(repositories.yzService.calFxZsSw6());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxWxdsSw6());
			futures.add(repositories.yzService.calFxPdSw6());
			futures.add(repositories.yzService.calFxFdSw6());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxQqSw6());
			futures.add(repositories.yzService.calFxQiwSw6());
			futures.add(repositories.yzService.calFxTwelveSw6());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxSlqSw6());
			futures.add(repositories.yzService.calFxZx1Sw6());
			futures.add(repositories.yzService.calFxZx2Sw6());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx3Sw6());
			futures.add(repositories.yzService.calFxZx4Sw6());
			futures.add(repositories.yzService.calFxZx5Sw6());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx6Sw6());
			futures.add(repositories.yzService.calFxZx7Sw6());
			futures.add(repositories.yzService.calFxZx8Sw6());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx10Sw6());
			futures.add(repositories.yzService.calFxZx11Sw6());
			futures.add(repositories.yzService.calFxZx12Sw6());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx13Sw6());
			futures.add(repositories.yzService.calFxZx14Sw6());
			futures.add(repositories.yzService.calFxZx15Sw6());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx16Sw6());
			futures.add(repositories.yzService.calFxZx17Sw6());
			futures.add(repositories.yzService.calFxZx18Sw6());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw7() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw7());
			futures.add(repositories.yzService.calFxDsSw7());
			futures.add(repositories.yzService.calFxMwSw7());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxLhSw7());
			futures.add(repositories.yzService.calFxBsSw7());
			futures.add(repositories.yzService.calFxZsSw7());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxWxdsSw7());
			futures.add(repositories.yzService.calFxPdSw7());
			futures.add(repositories.yzService.calFxFdSw7());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxQqSw7());
			futures.add(repositories.yzService.calFxQiwSw7());
			futures.add(repositories.yzService.calFxTwelveSw7());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxSlqSw7());
			futures.add(repositories.yzService.calFxZx1Sw7());
			futures.add(repositories.yzService.calFxZx2Sw7());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx3Sw7());
			futures.add(repositories.yzService.calFxZx4Sw7());
			futures.add(repositories.yzService.calFxZx5Sw7());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx6Sw7());
			futures.add(repositories.yzService.calFxZx7Sw7());
			futures.add(repositories.yzService.calFxZx8Sw7());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx10Sw7());
			futures.add(repositories.yzService.calFxZx11Sw7());
			futures.add(repositories.yzService.calFxZx12Sw7());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx13Sw7());
			futures.add(repositories.yzService.calFxZx14Sw7());
			futures.add(repositories.yzService.calFxZx15Sw7());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx16Sw7());
			futures.add(repositories.yzService.calFxZx17Sw7());
			futures.add(repositories.yzService.calFxZx18Sw7());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw8() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw8());
			futures.add(repositories.yzService.calFxDsSw8());
			futures.add(repositories.yzService.calFxMwSw8());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxLhSw8());
			futures.add(repositories.yzService.calFxBsSw8());
			futures.add(repositories.yzService.calFxZsSw8());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxWxdsSw8());
			futures.add(repositories.yzService.calFxPdSw8());
			futures.add(repositories.yzService.calFxFdSw8());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxTwelveSw8());
			futures.add(repositories.yzService.calFxSlqSw8());
			futures.add(repositories.yzService.calFxZx1Sw8());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx2Sw8());
			futures.add(repositories.yzService.calFxZx3Sw8());
			futures.add(repositories.yzService.calFxZx4Sw8());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx5Sw8());
			futures.add(repositories.yzService.calFxZx6Sw8());
			futures.add(repositories.yzService.calFxZx7Sw8());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx8Sw8());
			futures.add(repositories.yzService.calFxZx11Sw8());
			futures.add(repositories.yzService.calFxZx12Sw8());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx13Sw8());
			futures.add(repositories.yzService.calFxZx14Sw8());
			futures.add(repositories.yzService.calFxZx15Sw8());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx16Sw8());
			futures.add(repositories.yzService.calFxZx17Sw8());
			futures.add(repositories.yzService.calFxZx18Sw8());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw9() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw9());
			futures.add(repositories.yzService.calFxDsSw9());
			futures.add(repositories.yzService.calFxMwSw9());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxLhSw9());
			futures.add(repositories.yzService.calFxBsSw9());
			futures.add(repositories.yzService.calFxZsSw9());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxWxdsSw9());
			futures.add(repositories.yzService.calFxPdSw9());
			futures.add(repositories.yzService.calFxFdSw9());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxTwelveSw9());
			futures.add(repositories.yzService.calFxSlqSw9());
			futures.add(repositories.yzService.calFxZx1Sw9());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx2Sw9());
			futures.add(repositories.yzService.calFxZx3Sw9());
			futures.add(repositories.yzService.calFxZx4Sw9());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx5Sw9());
			futures.add(repositories.yzService.calFxZx6Sw9());
			futures.add(repositories.yzService.calFxZx7Sw9());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx8Sw9());
			futures.add(repositories.yzService.calFxZx11Sw9());
			futures.add(repositories.yzService.calFxZx12Sw9());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx13Sw9());
			futures.add(repositories.yzService.calFxZx14Sw9());
			futures.add(repositories.yzService.calFxZx15Sw9());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx16Sw9());
			futures.add(repositories.yzService.calFxZx17Sw9());
			futures.add(repositories.yzService.calFxZx18Sw9());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw10() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw10());
			futures.add(repositories.yzService.calFxDsSw10());
			futures.add(repositories.yzService.calFxMwSw10());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxLhSw10());
			futures.add(repositories.yzService.calFxWxdsSw10());
			futures.add(repositories.yzService.calFxPdSw10());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxFdSw10());
			futures.add(repositories.yzService.calFxTwelveSw10());
			futures.add(repositories.yzService.calFxSlqSw10());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx1Sw10());
			futures.add(repositories.yzService.calFxZx2Sw10());
			futures.add(repositories.yzService.calFxZx3Sw10());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx4Sw10());
			futures.add(repositories.yzService.calFxZx5Sw10());
			futures.add(repositories.yzService.calFxZx6Sw10());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx7Sw10());
			futures.add(repositories.yzService.calFxZx8Sw10());
			futures.add(repositories.yzService.calFxZx11Sw10());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx12Sw10());
			futures.add(repositories.yzService.calFxZx13Sw10());
			futures.add(repositories.yzService.calFxZx14Sw10());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx15Sw10());
			futures.add(repositories.yzService.calFxZx16Sw10());
			futures.add(repositories.yzService.calFxZx17Sw10());
			futures.add(repositories.yzService.calFxZx18Sw10());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw11() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw11());
			futures.add(repositories.yzService.calFxPdSw11());
			futures.add(repositories.yzService.calFxFdSw11());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxTwelveSw11());
			futures.add(repositories.yzService.calFxSlqSw11());
			futures.add(repositories.yzService.calFxZx1Sw11());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx2Sw11());
			futures.add(repositories.yzService.calFxZx3Sw11());
			futures.add(repositories.yzService.calFxZx4Sw11());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx5Sw11());
			futures.add(repositories.yzService.calFxZx6Sw11());
			futures.add(repositories.yzService.calFxZx11Sw11());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx12Sw11());
			futures.add(repositories.yzService.calFxZx13Sw11());
			futures.add(repositories.yzService.calFxZx14Sw11());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx15Sw11());
			futures.add(repositories.yzService.calFxZx16Sw11());
			futures.add(repositories.yzService.calFxZx17Sw11());
			futures.add(repositories.yzService.calFxZx18Sw11());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Exception> calFxSw12() {
		Exception t = null;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw12());
			futures.add(repositories.yzService.calFxPdSw12());
			futures.add(repositories.yzService.calFxFdSw12());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxTwelveSw12());
			futures.add(repositories.yzService.calFxSlqSw12());
			futures.add(repositories.yzService.calFxZx1Sw12());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx2Sw12());
			futures.add(repositories.yzService.calFxZx3Sw12());
			futures.add(repositories.yzService.calFxZx4Sw12());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx5Sw12());
			futures.add(repositories.yzService.calFxZx6Sw12());
			futures.add(repositories.yzService.calFxZx11Sw12());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx12Sw12());
			futures.add(repositories.yzService.calFxZx13Sw12());
			futures.add(repositories.yzService.calFxZx14Sw12());
			CommonUtil.sleep(futures, 100);
			futures.clear();
			futures.add(repositories.yzService.calFxZx15Sw12());
			futures.add(repositories.yzService.calFxZx16Sw12());
			futures.add(repositories.yzService.calFxZx17Sw12());
			futures.add(repositories.yzService.calFxZx18Sw12());
			CommonUtil.sleep(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	public void clearFxSwData() {
		fxswMap.clear();
	}

	@PersistenceContext
	private EntityManager em;

	
	public void saveFxSwData() {
		for (FxSw data : fxswMap.values()) {
			repositories.yzService.saveFxSwData(data);
		}
	}
	
	@Transactional
	public void saveFxSwData(FxSw data) {
		if (data.getId() != null) {
			em.merge(data);
		} else {
			em.persist(data);
		}
	}

	public List<Future<Exception>> calFxSwRedCounts() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		List<Future<Exception>> subFutures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw1Dao, repositories.fxsw1Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw2Dao, repositories.fxsw2Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw3Dao, repositories.fxsw3Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw4Dao, repositories.fxsw4Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw5Dao, repositories.fxsw5Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw6Dao, repositories.fxsw6Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw7Dao, repositories.fxsw7Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw8Dao, repositories.fxsw8Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw9Dao, repositories.fxsw9Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw10Dao, repositories.fxsw10Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw11Dao, repositories.fxsw11Repository));
		futures.add(repositories.yzService.calFxSwRedCountsForSw(subFutures, repositories.fxsw12Dao, repositories.fxsw12Repository));
		CommonUtil.sleep(futures, 100);
		logger.info("Counting calFxSwRedCounts, total sub tasks: " + subFutures.size());
		return subFutures;
	}

	@Async
	public <T extends FxSw> Future<Exception> calFxSwRedCountsForSw(List<Future<Exception>> futures, BaseFxSwDao<T> dao, BaseYzRepository<T> repository) {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 500, new Sort(Direction.ASC, "date"));
			Page<T> result = null;
			do {
				result = repository.findAll(request);
				if (result != null && result.hasContent()) {
					for (T yz : result.getContent()) {
						dao.countReds(yz.getYear(), yz.getPhase(), 100);
					}
				}
				request = request.next();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

}

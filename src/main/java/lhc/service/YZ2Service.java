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
import lhc.repository.jpa.BaseYzRepository;
import lhc.util.DateUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class YZ2Service extends YZService {
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
			String dw = (String) info.obj;
			Method sm = ReflectionUtils.findMethod(data.getClass(), "set" + prefix, Integer.class);
			for (int i = 0; i < lastList.size(); i++) {
				XbwInfo lastInfo = lastList.get(i);
				String lastDW = (String) lastInfo.obj;
				if (lastDW.equals(dw)) {
					if (i == pos - 1) {
						sm.invoke(data, 0);
					}
					break;
				}
			}
			Method gm = ReflectionUtils.findMethod(data.getClass(), "get" + prefix);
			if (gm.invoke(lastData) != null) {
				if (gm.invoke(data) == null) {
					Integer value = (Integer) gm.invoke(lastData);
					sm.invoke(data, value + 1);
				}
				Method m = ReflectionUtils.findMethod(data.getClass(), "set" + prefix + "DW", String.class);
				m.invoke(data, dw);
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
						data = (R) fxswMap.get(yz.getDate());
						// data = swRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
						if (data == null) {
							data = swRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
							if (data == null) {
								data = swClass.newInstance();
								data.setDate(yz.getDate());
								data.setYear(yz.getYear());
								data.setPhase(yz.getPhase());
							}
							fxswMap.put(yz.getDate(), data);
						}
					}
					if (last != null) {
						XbwInfo info = list.get(pos - 1);
						R lastData = (R) fxswMap.get(last.getDate());
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
				for (int i = 0; i < lastList.size(); i++) {
					XbwInfo lastInfo = lastList.get(i);
					SX lastSX = (SX) lastInfo.obj;
					if (lastSX.equals(sx)) {
						if (i == pos - 1) {
							data.setSx(0);
						}
						break;
					}
				}
				if (lastData.getSx() != null) {
					if (data.getSx() == null) {
						data.setSx(lastData.getSx() + 1);
					}
					data.setSxDW(sx.getText());
					SX bmnSX = DateUtil.getSxByYear(Integer.valueOf(data.getYear()));
					data.setSxNums(Joiner.on(",").join(getSxNums(bmnSX, sx)));
				}
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
					list.add(new XbwInfo("W" + i, i, value));
				}
			}

			@Override
			public void setData(int pos, XbwInfo info, List<XbwInfo> list, List<XbwInfo> lastList, FxSw data, FxSw lastData,
					String prefix, Class<?> numsClass) throws Exception {
				String dw = (String) info.fd;
				for (int i = 0; i < lastList.size(); i++) {
					XbwInfo lastInfo = lastList.get(i);
					String lastDW = (String) lastInfo.fd;
					if (lastDW.equals(dw)) {
						if (i == pos - 1) {
							data.setFd(0);
						}
						break;
					}
				}

				if (lastData.getFd() != null) {
					if (data.getFd() == null) {
						data.setFd(lastData.getFd() + 1);
					}
					data.setFdDW(dw);
					TmYz tmData = repositories.tmyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
					List<TmYzInfo> infos = getTMFDList(tmData, true);
					int range = 4;
					int length = 12;
					int currentPos = (Integer) info.obj;
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
						R data = (R) fxswMap.get(yz.getDate());
						// R data = swRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
						XbwInfo info = list.get(pos - 1);
						Integer zf = (Integer) info.obj;
						for (int i = 0; i < lastList.size(); i++) {
							XbwInfo lastInfo = lastList.get(i);
							Integer lastZf = (Integer) lastInfo.obj;
							if (lastZf.equals(zf)) {
								if (i == pos - 1) {
									sm.invoke(data, 0);
								}
								break;
							}
						}
						R lastData = (R) fxswMap.get(last.getDate());
						if (gm.invoke(lastData) != null) {
							if (gm.invoke(data) == null) {
								Integer value = (Integer) gm.invoke(lastData);
								sm.invoke(data, value + 1);
							}
							Method m = ReflectionUtils.findMethod(swClass, "set" + prefix + "zfDW", String.class);
							m.invoke(data, zf + "");
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
						if (sxpos > len) {
							sxpos -= len;
						}
						SX bmnSX = DateUtil.getSxByYear(Integer.valueOf(data.getYear()));
						data.setSxNums(Joiner.on(",").join(getSxNums(bmnSX, SX.posOf(sxpos))));
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
						int fdpos = currentPos + zf;
						int len = 12;
						if (fdpos > len) {
							fdpos -= len;
						}
						int maxLength = 4;
						int range = 4;
						if (fdpos == 11) {
							maxLength = 5;
						}
						int startPos = pos * range;
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
	public Future<Exception> calFxSw1() {
		Exception t = null;
		try {
			fxswMap.clear();
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			futures.add(repositories.yzService.calFxSxSw1());
			futures.add(repositories.yzService.calFxDsSw1());
			futures.add(repositories.yzService.calFxSwSw1());
			futures.add(repositories.yzService.calFxMwSw1());
			futures.add(repositories.yzService.calFxLhSw1());
			futures.add(repositories.yzService.calFxBsSw1());
			futures.add(repositories.yzService.calFxZsSw1());
			futures.add(repositories.yzService.calFxWxSw1());
			futures.add(repositories.yzService.calFxWxdsSw1());
			futures.add(repositories.yzService.calFxPdSw1());
			futures.add(repositories.yzService.calFxFdSw1());
			futures.add(repositories.yzService.calFxQqSw1());
			futures.add(repositories.yzService.calFxQiwSw1());
			futures.add(repositories.yzService.calFxTwelveSw1());
			futures.add(repositories.yzService.calFxSlqSw1());
			futures.add(repositories.yzService.calFxZx1Sw1());
			futures.add(repositories.yzService.calFxZx2Sw1());
			futures.add(repositories.yzService.calFxZx3Sw1());
			futures.add(repositories.yzService.calFxZx4Sw1());
			futures.add(repositories.yzService.calFxZx5Sw1());
			futures.add(repositories.yzService.calFxZx6Sw1());
			futures.add(repositories.yzService.calFxZx7Sw1());
			futures.add(repositories.yzService.calFxZx8Sw1());
			futures.add(repositories.yzService.calFxZx9Sw1());
			futures.add(repositories.yzService.calFxZx10Sw1());
			futures.add(repositories.yzService.calFxZx11Sw1());
			futures.add(repositories.yzService.calFxZx12Sw1());
			futures.add(repositories.yzService.calFxZx13Sw1());
			futures.add(repositories.yzService.calFxZx14Sw1());
			futures.add(repositories.yzService.calFxZx15Sw1());
			futures.add(repositories.yzService.calFxZx16Sw1());
			futures.add(repositories.yzService.calFxZx17Sw1());
			futures.add(repositories.yzService.calFxZx18Sw1());
			sleep(futures, 10000);
			for (FxSw data : fxswMap.values()) {
				repositories.fxsw1Repository.save((FxSw1) data);
			}
			fxswMap.clear();
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}
}

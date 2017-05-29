package lhc.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lhc.domain.KaiJiang;
import lhc.domain.SxYz;
import lhc.domain.SxZfYz;
import lhc.enums.SX;
import lhc.repository.jpa.api.KaiJiangRepository;
import lhc.repository.jpa.api.SxYzRepository;
import lhc.repository.jpa.api.SxZfYzRepository;

@Service
public class YZService {

	@Autowired
	private KaiJiangRepository kaiJiangRepository;

	@Autowired
	private SxYzRepository sxyzRepository;

	@Autowired
	private SxZfYzRepository sxzfyzRepository;

	public void calSX() {
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
			throw new RuntimeException(e.getMessage(), e);
		}

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

						Integer zf = null;
						if (lastYZ != null) {
							zf = new BigDecimal(data.getCurrentSx().getPos() - lastYZ.getCurrentSx().getPos()).abs().intValue();
							Method m = SxZfYz.class.getDeclaredMethod("setZf" + zf, Integer.class);
							m.invoke(zfYz, 0);
						}

						if (lastZFYZ != null && zf != null) {
							Method m = SxZfYz.class.getDeclaredMethod("getZf" + zf);
							Integer lastValue = (Integer) m.invoke(lastZFYZ);
							zfYz.setLastYz(lastValue);
							for (int i = 0; i < 12; i++) {
								if (i != zf) {
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
}

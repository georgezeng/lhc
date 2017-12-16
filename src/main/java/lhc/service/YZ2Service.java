package lhc.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lhc.constants.Zx11Nums;
import lhc.constants.Zx12Nums;
import lhc.constants.Zx13Nums;
import lhc.constants.Zx14Nums;
import lhc.constants.Zx15Nums;
import lhc.constants.Zx16Nums;
import lhc.constants.Zx17Nums;
import lhc.constants.Zx18Nums;
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

@Service
@Transactional
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
}

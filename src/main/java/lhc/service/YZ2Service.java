package lhc.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lhc.constants.Zx11Nums;
import lhc.constants.Zx12Nums;
import lhc.constants.Zx13Nums;
import lhc.constants.Zx14Nums;
import lhc.domain.Zx11Yz;
import lhc.domain.Zx12Yz;
import lhc.domain.Zx13Yz;
import lhc.domain.Zx14Yz;

@Service
@Transactional
public class YZ2Service extends YZService {
	@Async
	public Future<Exception> calZX11YZ() {
		return calFDYZ(Zx11Yz.class, Zx11Nums.class, repositories.zx11yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX11YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX12YZ() {
		return calFDYZ(Zx12Yz.class, Zx12Nums.class, repositories.zx12yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX12YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX13YZ() {
		return calFDYZ(Zx13Yz.class, Zx13Nums.class, repositories.zx13yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX13YZ...");
			}
		});

	}

	@Async
	public Future<Exception> calZX14YZ() {
		return calFDYZ(Zx14Yz.class, Zx14Nums.class, repositories.zx14yzRepository, new CommonHandler() {

			@Override
			public void process() {
				logger.info("End of calZX14YZ...");
			}
		});

	}
}

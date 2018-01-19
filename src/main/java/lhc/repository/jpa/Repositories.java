package lhc.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhc.repository.jpa.api.*;
import lhc.repository.jpa.dao.*;
import lhc.service.YZ3Service;

@Service
public class Repositories {

	@Autowired
	public SxYzDao sxYzDao;

	@Autowired
	public SxLrYzDao sxlrYzDao;

	@Autowired
	public DsLrYzDao dslrYzDao;

	@Autowired
	public DsLrYzRepository dslrYzRepository;

	@Autowired
	public QiwYzRepository qiwYzRepository;

	@Autowired
	public QiwZfYzRepository qiwzfYzRepository;

	@Autowired
	public QiwYzDao qiwYzDao;

	@Autowired
	public QiwZfYzDao qiwzfYzDao;

	@Autowired
	public LhLrYzDao lhlrYzDao;

	@Autowired
	public Bs9qLrYzDao bs9qlrYzDao;

	@Autowired
	public Bs9qYzDao bs9qYzDao;

	@Autowired
	public Bs9qZfYzDao bs9qZfYzDao;

	@Autowired
	public WxdsLrYzDao wxdslrYzDao;

	@Autowired
	public WxdsYzDao wxdsYzDao;

	@Autowired
	public WxdsZfYzDao wxdsZfYzDao;

	@Autowired
	public ZsLrYzDao zslrYzDao;

	@Autowired
	public TwelveLrYzDao twelvelrYzDao;

	@Autowired
	public SlqLrYzDao slqlrYzDao;

	@Autowired
	public MwLrYzDao mwlrYzDao;

	@Autowired
	public SxZfYzDao sxZfYzDao;

	@Autowired
	public SxZfYz2Dao sxZfYz2Dao;

	@Autowired
	public SwZfYzDao swZfYzDao;

	@Autowired
	public MwZfYzDao mwZfYzDao;

	@Autowired
	public LhZfYzDao lhZfYzDao;

	@Autowired
	public QqZfYzDao qqZfYzDao;

	@Autowired
	public KaiJiangDao kaiJiangDao;

	@Autowired
	public QwYzDao qwYzDao;

	@Autowired
	public HmDsYzDao hmdsYzDao;

	@Autowired
	public SwYzDao swYzDao;

	@Autowired
	public MwYzDao mwYzDao;

	@Autowired
	public LhYzDao lhYzDao;

	@Autowired
	public QqYzDao qqYzDao;

	@Autowired
	public BsYzDao bsYzDao;

	@Autowired
	public BsZfYzDao bszfYzDao;

	@Autowired
	public WxYzDao wxYzDao;

	@Autowired
	public WxZfYzDao wxzfYzDao;

	@Autowired
	public ZsYzDao zsYzDao;

	@Autowired
	public SxCsYzDao sxcsYzDao;

	@Autowired
	public ZsZfYzDao zszfYzDao;

	@Autowired
	public DsYzDao dsYzDao;

	@Autowired
	public SqYzDao sqYzDao;

	@Autowired
	public SxDsYzDao sxdsYzDao;

	@Autowired
	public LhDsYzDao lhdsYzDao;

	@Autowired
	public TmYzDao tmYzDao;

	@Autowired
	public PtYzDao ptYzDao;

	@Autowired
	public TmFdYzDao tmfdYzDao;

	@Autowired
	public DsZfYzDao dszfYzDao;

	@Autowired
	public TwelveYzDao twelveYzDao;

	@Autowired
	public TwelveZfYzDao twelvezfYzDao;

	@Autowired
	public SlqYzDao slqYzDao;

	@Autowired
	public SlqZfYzDao slqzfYzDao;

	@Autowired
	public PdYzDao pdYzDao;

	@Autowired
	public PdZfYzDao pdzfYzDao;

	@Autowired
	public PdLrYzDao pdlrYzDao;

	@Autowired
	public Tm12FdYzDao tm12fdYzDao;

	@Autowired
	public Tm12FdZfYzDao tm12fdzfYzDao;

	@Autowired
	public Tm12FdLrYzDao tm12fdlrYzDao;

	@Autowired
	public KaiJiangRepository kaiJiangRepository;

	@Autowired
	public SxYzRepository sxyzRepository;

	@Autowired
	public SxLrYzRepository sxlryzRepository;

	@Autowired
	public LhLrYzRepository lhlryzRepository;

	@Autowired
	public ZsLrYzRepository zslryzRepository;

	@Autowired
	public MwLrYzRepository mwlryzRepository;

	@Autowired
	public SxZfYzRepository sxzfyzRepository;

	@Autowired
	public SxZfYz2Repository sxzfyz2Repository;

	@Autowired
	public QwYzRepository qwyzRepository;

	@Autowired
	public SwYzRepository swyzRepository;

	@Autowired
	public SwZfYzRepository swzfyzRepository;

	@Autowired
	public MwZfYzRepository mwzfyzRepository;

	@Autowired
	public DsZfYzRepository dszfyzRepository;

	@Autowired
	public MwYzRepository mwyzRepository;

	@Autowired
	public Zx1YzRepository zx1yzRepository;

	@Autowired
	public Zx1ZfYzRepository zx1zfyzRepository;

	@Autowired
	public Zx2ZfYzRepository zx2zfyzRepository;

	@Autowired
	public Zx3ZfYzRepository zx3zfyzRepository;

	@Autowired
	public Zx4ZfYzRepository zx4zfyzRepository;

	@Autowired
	public Zx5ZfYzRepository zx5zfyzRepository;

	@Autowired
	public Zx6ZfYzRepository zx6zfyzRepository;

	@Autowired
	public Zx7ZfYzRepository zx7zfyzRepository;

	@Autowired
	public Zx8ZfYzRepository zx8zfyzRepository;

	@Autowired
	public Zx9ZfYzRepository zx9zfyzRepository;

	@Autowired
	public Zx10ZfYzRepository zx10zfyzRepository;

	@Autowired
	public Zx11ZfYzRepository zx11zfyzRepository;

	@Autowired
	public Zx12ZfYzRepository zx12zfyzRepository;

	@Autowired
	public Zx13ZfYzRepository zx13zfyzRepository;

	@Autowired
	public Zx14ZfYzRepository zx14zfyzRepository;

	@Autowired
	public Zx15ZfYzRepository zx15zfyzRepository;

	@Autowired
	public Zx16ZfYzRepository zx16zfyzRepository;

	@Autowired
	public Zx17ZfYzRepository zx17zfyzRepository;

	@Autowired
	public Zx18ZfYzRepository zx18zfyzRepository;

	@Autowired
	public Zx2YzRepository zx2yzRepository;

	@Autowired
	public Zx3YzRepository zx3yzRepository;

	@Autowired
	public Zx4YzRepository zx4yzRepository;

	@Autowired
	public Zx5YzRepository zx5yzRepository;

	@Autowired
	public Zx6YzRepository zx6yzRepository;

	@Autowired
	public Zx7YzRepository zx7yzRepository;

	@Autowired
	public Zx8YzRepository zx8yzRepository;

	@Autowired
	public Zx9YzRepository zx9yzRepository;

	@Autowired
	public Zx10YzRepository zx10yzRepository;

	@Autowired
	public Zx11YzRepository zx11yzRepository;

	@Autowired
	public Zx12YzRepository zx12yzRepository;

	@Autowired
	public Zx13YzRepository zx13yzRepository;

	@Autowired
	public Zx14YzRepository zx14yzRepository;

	@Autowired
	public Zx15YzRepository zx15yzRepository;

	@Autowired
	public Zx16YzRepository zx16yzRepository;

	@Autowired
	public Zx17YzRepository zx17yzRepository;

	@Autowired
	public Zx18YzRepository zx18yzRepository;

	@Autowired
	public Zx1YzDao zx1yzDao;

	@Autowired
	public Zx1ZfYzDao zx1zfyzDao;

	@Autowired
	public Zx2ZfYzDao zx2zfyzDao;

	@Autowired
	public Zx3ZfYzDao zx3zfyzDao;

	@Autowired
	public Zx4ZfYzDao zx4zfyzDao;

	@Autowired
	public Zx5ZfYzDao zx5zfyzDao;

	@Autowired
	public Zx6ZfYzDao zx6zfyzDao;

	@Autowired
	public Zx7ZfYzDao zx7zfyzDao;

	@Autowired
	public Zx8ZfYzDao zx8zfyzDao;

	@Autowired
	public Zx9ZfYzDao zx9zfyzDao;

	@Autowired
	public Zx10ZfYzDao zx10zfyzDao;

	@Autowired
	public Zx11ZfYzDao zx11zfyzDao;

	@Autowired
	public Zx12ZfYzDao zx12zfyzDao;

	@Autowired
	public Zx13ZfYzDao zx13zfyzDao;

	@Autowired
	public Zx14ZfYzDao zx14zfyzDao;

	@Autowired
	public Zx15ZfYzDao zx15zfyzDao;

	@Autowired
	public Zx16ZfYzDao zx16zfyzDao;

	@Autowired
	public Zx17ZfYzDao zx17zfyzDao;

	@Autowired
	public Zx18ZfYzDao zx18zfyzDao;

	@Autowired
	public Zx2YzDao zx2yzDao;

	@Autowired
	public Zx3YzDao zx3yzDao;

	@Autowired
	public Zx4YzDao zx4yzDao;

	@Autowired
	public Zx5YzDao zx5yzDao;

	@Autowired
	public Zx6YzDao zx6yzDao;

	@Autowired
	public Zx7YzDao zx7yzDao;

	@Autowired
	public Zx8YzDao zx8yzDao;

	@Autowired
	public Zx9YzDao zx9yzDao;

	@Autowired
	public Zx10YzDao zx10yzDao;

	@Autowired
	public Zx11YzDao zx11yzDao;

	@Autowired
	public Zx12YzDao zx12yzDao;

	@Autowired
	public Zx13YzDao zx13yzDao;

	@Autowired
	public Zx14YzDao zx14yzDao;

	@Autowired
	public Zx15YzDao zx15yzDao;

	@Autowired
	public Zx16YzDao zx16yzDao;

	@Autowired
	public Zx17YzDao zx17yzDao;

	@Autowired
	public Zx18YzDao zx18yzDao;

	@Autowired
	public Bs9qYzRepository bs9qyzRepository;

	@Autowired
	public Bs9qZfYzRepository bs9qzfyzRepository;

	@Autowired
	public Bs9qLrYzRepository bs9qlryzRepository;

	@Autowired
	public WxdsYzRepository wxdsyzRepository;

	@Autowired
	public WxdsZfYzRepository wxdszfyzRepository;

	@Autowired
	public WxdsLrYzRepository wxdslryzRepository;

	@Autowired
	public LhYzRepository lhyzRepository;

	@Autowired
	public QqYzRepository qqyzRepository;

	@Autowired
	public QqZfYzRepository qqzfyzRepository;

	@Autowired
	public SlqYzRepository slqyzRepository;

	@Autowired
	public SlqZfYzRepository slqzfyzRepository;

	@Autowired
	public BsYzRepository bsyzRepository;

	@Autowired
	public ZsYzRepository zsyzRepository;

	@Autowired
	public DsYzRepository dsyzRepository;

	@Autowired
	public TwelveYzRepository twelveyzRepository;

	@Autowired
	public SlqLrYzRepository slqlryzRepository;

	@Autowired
	public TwelveLrYzRepository twelvelryzRepository;

	@Autowired
	public TwelveZfYzRepository twelvezfyzRepository;

	@Autowired
	public LhZfYzRepository lhzfyzRepository;

	@Autowired
	public SqYzRepository sqyzRepository;

	@Autowired
	public SxDsYzRepository sxdsyzRepository;

	@Autowired
	public LhDsYzRepository lhdsyzRepository;

	@Autowired
	public HmDsYzRepository hmdsyzRepository;

	@Autowired
	public TmYzRepository tmyzRepository;

	@Autowired
	public PtYzRepository ptyzRepository;

	@Autowired
	public TmFdYzRepository tmfdyzRepository;

	@Autowired
	public ZsZfYzRepository zszfyzRepository;

	@Autowired
	public BsZfYzRepository bszfyzRepository;

	@Autowired
	public WxYzRepository wxyzRepository;

	@Autowired
	public WxZfYzRepository wxzfyzRepository;

	@Autowired
	public SxCsYzRepository sxcsyzRepository;

	@Autowired
	public PdYzRepository pdyzRepository;

	@Autowired
	public PdZfYzRepository pdzfyzRepository;

	@Autowired
	public PdLrYzRepository pdlryzRepository;

	@Autowired
	public Tm12FdYzRepository tm12fdyzRepository;

	@Autowired
	public Tm12FdZfYzRepository tm12fdzfyzRepository;

	@Autowired
	public Tm12FdLrYzRepository tm12fdlryzRepository;

	@Autowired
	public FxSw1Repository fxsw1Repository;

	@Autowired
	public FxSw1Dao fxsw1Dao;
	
	@Autowired
	public FxSw2Repository fxsw2Repository;
	
	@Autowired
	public FxSw2Dao fxsw2Dao;
	
	@Autowired
	public FxSw3Repository fxsw3Repository;
	
	@Autowired
	public FxSw3Dao fxsw3Dao;
	
	@Autowired
	public FxSw4Repository fxsw4Repository;
	
	@Autowired
	public FxSw4Dao fxsw4Dao;
	
	@Autowired
	public FxSw5Repository fxsw5Repository;
	
	@Autowired
	public FxSw5Dao fxsw5Dao;
	
	@Autowired
	public FxSw6Repository fxsw6Repository;
	
	@Autowired
	public FxSw6Dao fxsw6Dao;
	
	@Autowired
	public FxSw7Repository fxsw7Repository;
	
	@Autowired
	public FxSw7Dao fxsw7Dao;
	
	@Autowired
	public FxSw8Repository fxsw8Repository;
	
	@Autowired
	public FxSw8Dao fxsw8Dao;
	
	@Autowired
	public FxSw9Repository fxsw9Repository;
	
	@Autowired
	public FxSw9Dao fxsw9Dao;
	
	@Autowired
	public FxSw10Repository fxsw10Repository;
	
	@Autowired
	public FxSw10Dao fxsw10Dao;
	
	@Autowired
	public FxSw11Repository fxsw11Repository;
	
	@Autowired
	public FxSw11Dao fxsw11Dao;
	
	@Autowired
	public FxSw12Repository fxsw12Repository;
	
	@Autowired
	public FxSw12Dao fxsw12Dao;
	
	@Autowired
	public DsxMinJyJQBRepository dsxMinJyJQBRepository;
	
	@Autowired
	public DsxMinJYJQBDao dsxMinJyJQBDao;
	
	@Autowired
	public DsxMinJyJHBRepository dsxMinJyJHBRepository;
	
	@Autowired
	public DsxMinJYJHBDao dsxMinJyJHBDao;
	
	@Autowired
	public DsxMinJyJDBRepository dsxMinJyJDBRepository;
	
	@Autowired
	public DsxMinJYJDBDao dsxMinJyJDBDao;
	
	@Autowired
	public DsxMaxJyJQBRepository dsxMaxJyJQBRepository;
	
	@Autowired
	public DsxMaxJYJQBDao dsxMaxJyJQBDao;
	
	@Autowired
	public DsxMaxJyJHBRepository dsxMaxJyJHBRepository;
	
	@Autowired
	public DsxMaxJYJHBDao dsxMaxJyJHBDao;
	
	@Autowired
	public DsxMaxJyJDBRepository dsxMaxJyJDBRepository;
	
	@Autowired
	public DsxMaxJYJDBDao dsxMaxJyJDBDao;
	
	@Autowired
	public FxSwADao fxSwADao;
	
	@Autowired
	public FxSwARepository fxSwARepository;

	@Autowired
	public CommonDao commonDao;

	@Autowired
	public YZ3Service yzService;
}

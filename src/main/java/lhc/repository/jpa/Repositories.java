package lhc.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhc.repository.jpa.api.Bs9qLrYzRepository;
import lhc.repository.jpa.api.Bs9qYzRepository;
import lhc.repository.jpa.api.Bs9qZfYzRepository;
import lhc.repository.jpa.api.BsYzRepository;
import lhc.repository.jpa.api.BsZfYzRepository;
import lhc.repository.jpa.api.DsLrYzRepository;
import lhc.repository.jpa.api.DsYzRepository;
import lhc.repository.jpa.api.DsZfYzRepository;
import lhc.repository.jpa.api.FxSw1Repository;
import lhc.repository.jpa.api.HmDsYzRepository;
import lhc.repository.jpa.api.KaiJiangRepository;
import lhc.repository.jpa.api.LhDsYzRepository;
import lhc.repository.jpa.api.LhLrYzRepository;
import lhc.repository.jpa.api.LhYzRepository;
import lhc.repository.jpa.api.LhZfYzRepository;
import lhc.repository.jpa.api.MwLrYzRepository;
import lhc.repository.jpa.api.MwYzRepository;
import lhc.repository.jpa.api.MwZfYzRepository;
import lhc.repository.jpa.api.PdLrYzRepository;
import lhc.repository.jpa.api.PdYzRepository;
import lhc.repository.jpa.api.PdZfYzRepository;
import lhc.repository.jpa.api.PtYzRepository;
import lhc.repository.jpa.api.QiwYzRepository;
import lhc.repository.jpa.api.QiwZfYzRepository;
import lhc.repository.jpa.api.QqYzRepository;
import lhc.repository.jpa.api.QqZfYzRepository;
import lhc.repository.jpa.api.QwYzRepository;
import lhc.repository.jpa.api.SlqLrYzRepository;
import lhc.repository.jpa.api.SlqYzRepository;
import lhc.repository.jpa.api.SlqZfYzRepository;
import lhc.repository.jpa.api.SqYzRepository;
import lhc.repository.jpa.api.SwYzRepository;
import lhc.repository.jpa.api.SwZfYzRepository;
import lhc.repository.jpa.api.SxCsYzRepository;
import lhc.repository.jpa.api.SxDsYzRepository;
import lhc.repository.jpa.api.SxLrYzRepository;
import lhc.repository.jpa.api.SxYzRepository;
import lhc.repository.jpa.api.SxZfYz2Repository;
import lhc.repository.jpa.api.SxZfYzRepository;
import lhc.repository.jpa.api.Tm12FdLrYzRepository;
import lhc.repository.jpa.api.Tm12FdYzRepository;
import lhc.repository.jpa.api.Tm12FdZfYzRepository;
import lhc.repository.jpa.api.TmFdYzRepository;
import lhc.repository.jpa.api.TmYzRepository;
import lhc.repository.jpa.api.TwelveLrYzRepository;
import lhc.repository.jpa.api.TwelveYzRepository;
import lhc.repository.jpa.api.TwelveZfYzRepository;
import lhc.repository.jpa.api.WxYzRepository;
import lhc.repository.jpa.api.WxZfYzRepository;
import lhc.repository.jpa.api.WxdsLrYzRepository;
import lhc.repository.jpa.api.WxdsYzRepository;
import lhc.repository.jpa.api.WxdsZfYzRepository;
import lhc.repository.jpa.api.ZsLrYzRepository;
import lhc.repository.jpa.api.ZsYzRepository;
import lhc.repository.jpa.api.ZsZfYzRepository;
import lhc.repository.jpa.api.Zx10YzRepository;
import lhc.repository.jpa.api.Zx10ZfYzRepository;
import lhc.repository.jpa.api.Zx11YzRepository;
import lhc.repository.jpa.api.Zx11ZfYzRepository;
import lhc.repository.jpa.api.Zx12YzRepository;
import lhc.repository.jpa.api.Zx12ZfYzRepository;
import lhc.repository.jpa.api.Zx13YzRepository;
import lhc.repository.jpa.api.Zx13ZfYzRepository;
import lhc.repository.jpa.api.Zx14YzRepository;
import lhc.repository.jpa.api.Zx14ZfYzRepository;
import lhc.repository.jpa.api.Zx15YzRepository;
import lhc.repository.jpa.api.Zx15ZfYzRepository;
import lhc.repository.jpa.api.Zx16YzRepository;
import lhc.repository.jpa.api.Zx16ZfYzRepository;
import lhc.repository.jpa.api.Zx17YzRepository;
import lhc.repository.jpa.api.Zx17ZfYzRepository;
import lhc.repository.jpa.api.Zx18YzRepository;
import lhc.repository.jpa.api.Zx18ZfYzRepository;
import lhc.repository.jpa.api.Zx1YzRepository;
import lhc.repository.jpa.api.Zx1ZfYzRepository;
import lhc.repository.jpa.api.Zx2YzRepository;
import lhc.repository.jpa.api.Zx2ZfYzRepository;
import lhc.repository.jpa.api.Zx3YzRepository;
import lhc.repository.jpa.api.Zx3ZfYzRepository;
import lhc.repository.jpa.api.Zx4YzRepository;
import lhc.repository.jpa.api.Zx4ZfYzRepository;
import lhc.repository.jpa.api.Zx5YzRepository;
import lhc.repository.jpa.api.Zx5ZfYzRepository;
import lhc.repository.jpa.api.Zx6YzRepository;
import lhc.repository.jpa.api.Zx6ZfYzRepository;
import lhc.repository.jpa.api.Zx7YzRepository;
import lhc.repository.jpa.api.Zx7ZfYzRepository;
import lhc.repository.jpa.api.Zx8YzRepository;
import lhc.repository.jpa.api.Zx8ZfYzRepository;
import lhc.repository.jpa.api.Zx9YzRepository;
import lhc.repository.jpa.api.Zx9ZfYzRepository;
import lhc.repository.jpa.dao.Bs9qLrYzDao;
import lhc.repository.jpa.dao.Bs9qYzDao;
import lhc.repository.jpa.dao.Bs9qZfYzDao;
import lhc.repository.jpa.dao.BsYzDao;
import lhc.repository.jpa.dao.BsZfYzDao;
import lhc.repository.jpa.dao.CommonDao;
import lhc.repository.jpa.dao.DsLrYzDao;
import lhc.repository.jpa.dao.DsYzDao;
import lhc.repository.jpa.dao.DsZfYzDao;
import lhc.repository.jpa.dao.FxSw1Dao;
import lhc.repository.jpa.dao.HmDsYzDao;
import lhc.repository.jpa.dao.KaiJiangDao;
import lhc.repository.jpa.dao.LhDsYzDao;
import lhc.repository.jpa.dao.LhLrYzDao;
import lhc.repository.jpa.dao.LhYzDao;
import lhc.repository.jpa.dao.LhZfYzDao;
import lhc.repository.jpa.dao.MwLrYzDao;
import lhc.repository.jpa.dao.MwYzDao;
import lhc.repository.jpa.dao.MwZfYzDao;
import lhc.repository.jpa.dao.PdLrYzDao;
import lhc.repository.jpa.dao.PdYzDao;
import lhc.repository.jpa.dao.PdZfYzDao;
import lhc.repository.jpa.dao.PtYzDao;
import lhc.repository.jpa.dao.QiwYzDao;
import lhc.repository.jpa.dao.QiwZfYzDao;
import lhc.repository.jpa.dao.QqYzDao;
import lhc.repository.jpa.dao.QqZfYzDao;
import lhc.repository.jpa.dao.QwYzDao;
import lhc.repository.jpa.dao.SlqLrYzDao;
import lhc.repository.jpa.dao.SlqYzDao;
import lhc.repository.jpa.dao.SlqZfYzDao;
import lhc.repository.jpa.dao.SqYzDao;
import lhc.repository.jpa.dao.SwYzDao;
import lhc.repository.jpa.dao.SwZfYzDao;
import lhc.repository.jpa.dao.SxCsYzDao;
import lhc.repository.jpa.dao.SxDsYzDao;
import lhc.repository.jpa.dao.SxLrYzDao;
import lhc.repository.jpa.dao.SxYzDao;
import lhc.repository.jpa.dao.SxZfYz2Dao;
import lhc.repository.jpa.dao.SxZfYzDao;
import lhc.repository.jpa.dao.Tm12FdLrYzDao;
import lhc.repository.jpa.dao.Tm12FdYzDao;
import lhc.repository.jpa.dao.Tm12FdZfYzDao;
import lhc.repository.jpa.dao.TmFdYzDao;
import lhc.repository.jpa.dao.TmYzDao;
import lhc.repository.jpa.dao.TwelveLrYzDao;
import lhc.repository.jpa.dao.TwelveYzDao;
import lhc.repository.jpa.dao.TwelveZfYzDao;
import lhc.repository.jpa.dao.WxYzDao;
import lhc.repository.jpa.dao.WxZfYzDao;
import lhc.repository.jpa.dao.WxdsLrYzDao;
import lhc.repository.jpa.dao.WxdsYzDao;
import lhc.repository.jpa.dao.WxdsZfYzDao;
import lhc.repository.jpa.dao.ZsLrYzDao;
import lhc.repository.jpa.dao.ZsYzDao;
import lhc.repository.jpa.dao.ZsZfYzDao;
import lhc.repository.jpa.dao.Zx10YzDao;
import lhc.repository.jpa.dao.Zx10ZfYzDao;
import lhc.repository.jpa.dao.Zx11YzDao;
import lhc.repository.jpa.dao.Zx11ZfYzDao;
import lhc.repository.jpa.dao.Zx12YzDao;
import lhc.repository.jpa.dao.Zx12ZfYzDao;
import lhc.repository.jpa.dao.Zx13YzDao;
import lhc.repository.jpa.dao.Zx13ZfYzDao;
import lhc.repository.jpa.dao.Zx14YzDao;
import lhc.repository.jpa.dao.Zx14ZfYzDao;
import lhc.repository.jpa.dao.Zx15YzDao;
import lhc.repository.jpa.dao.Zx15ZfYzDao;
import lhc.repository.jpa.dao.Zx16YzDao;
import lhc.repository.jpa.dao.Zx16ZfYzDao;
import lhc.repository.jpa.dao.Zx17YzDao;
import lhc.repository.jpa.dao.Zx17ZfYzDao;
import lhc.repository.jpa.dao.Zx18YzDao;
import lhc.repository.jpa.dao.Zx18ZfYzDao;
import lhc.repository.jpa.dao.Zx1YzDao;
import lhc.repository.jpa.dao.Zx1ZfYzDao;
import lhc.repository.jpa.dao.Zx2YzDao;
import lhc.repository.jpa.dao.Zx2ZfYzDao;
import lhc.repository.jpa.dao.Zx3YzDao;
import lhc.repository.jpa.dao.Zx3ZfYzDao;
import lhc.repository.jpa.dao.Zx4YzDao;
import lhc.repository.jpa.dao.Zx4ZfYzDao;
import lhc.repository.jpa.dao.Zx5YzDao;
import lhc.repository.jpa.dao.Zx5ZfYzDao;
import lhc.repository.jpa.dao.Zx6YzDao;
import lhc.repository.jpa.dao.Zx6ZfYzDao;
import lhc.repository.jpa.dao.Zx7YzDao;
import lhc.repository.jpa.dao.Zx7ZfYzDao;
import lhc.repository.jpa.dao.Zx8YzDao;
import lhc.repository.jpa.dao.Zx8ZfYzDao;
import lhc.repository.jpa.dao.Zx9YzDao;
import lhc.repository.jpa.dao.Zx9ZfYzDao;
import lhc.service.YZ2Service;

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
	public CommonDao commonDao;

	@Autowired
	public YZ2Service yzService;
}

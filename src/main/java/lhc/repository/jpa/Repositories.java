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
import lhc.repository.jpa.api.Zx11YzRepository;
import lhc.repository.jpa.api.Zx12YzRepository;
import lhc.repository.jpa.api.Zx13YzRepository;
import lhc.repository.jpa.api.Zx14YzRepository;
import lhc.repository.jpa.api.Zx1YzRepository;
import lhc.repository.jpa.api.Zx2YzRepository;
import lhc.repository.jpa.api.Zx3YzRepository;
import lhc.repository.jpa.api.Zx4YzRepository;
import lhc.repository.jpa.api.Zx5YzRepository;
import lhc.repository.jpa.api.Zx6YzRepository;
import lhc.repository.jpa.api.Zx7YzRepository;
import lhc.repository.jpa.api.Zx8YzRepository;
import lhc.repository.jpa.api.Zx9YzRepository;
import lhc.repository.jpa.dao.Bs9qLrYzDao;
import lhc.repository.jpa.dao.Bs9qYzDao;
import lhc.repository.jpa.dao.Bs9qZfYzDao;
import lhc.repository.jpa.dao.BsYzDao;
import lhc.repository.jpa.dao.BsZfYzDao;
import lhc.repository.jpa.dao.CommonDao;
import lhc.repository.jpa.dao.DsLrYzDao;
import lhc.repository.jpa.dao.DsYzDao;
import lhc.repository.jpa.dao.DsZfYzDao;
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
import lhc.repository.jpa.dao.Zx11YzDao;
import lhc.repository.jpa.dao.Zx12YzDao;
import lhc.repository.jpa.dao.Zx13YzDao;
import lhc.repository.jpa.dao.Zx14YzDao;
import lhc.repository.jpa.dao.Zx1YzDao;
import lhc.repository.jpa.dao.Zx2YzDao;
import lhc.repository.jpa.dao.Zx3YzDao;
import lhc.repository.jpa.dao.Zx4YzDao;
import lhc.repository.jpa.dao.Zx5YzDao;
import lhc.repository.jpa.dao.Zx6YzDao;
import lhc.repository.jpa.dao.Zx7YzDao;
import lhc.repository.jpa.dao.Zx8YzDao;
import lhc.repository.jpa.dao.Zx9YzDao;
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
	public Zx1YzDao zx1yzDao;
	
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
	public CommonDao commonDao;

	@Autowired
	public YZ2Service yzService;
}

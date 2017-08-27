package lhc.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhc.repository.jpa.api.BsYzRepository;
import lhc.repository.jpa.api.BsZfYzRepository;
import lhc.repository.jpa.api.DsYzRepository;
import lhc.repository.jpa.api.DsZfYzRepository;
import lhc.repository.jpa.api.KaiJiangRepository;
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
import lhc.repository.jpa.api.ZsYzRepository;
import lhc.repository.jpa.api.ZsZfYzRepository;
import lhc.repository.jpa.dao.BsYzDao;
import lhc.repository.jpa.dao.BsZfYzDao;
import lhc.repository.jpa.dao.DsYzDao;
import lhc.repository.jpa.dao.DsZfYzDao;
import lhc.repository.jpa.dao.KaiJiangDao;
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
import lhc.repository.jpa.dao.ZsYzDao;
import lhc.repository.jpa.dao.ZsZfYzDao;

@Service
public class Repositories {

	@Autowired
	public SxYzDao sxYzDao;

	@Autowired
	public SxLrYzDao sxlrYzDao;

	@Autowired
	public LhLrYzDao lhlrYzDao;

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
}

package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.WxZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class WxZfYzDao extends BaseYzDao<WxZfYz> {

	@Override
	protected String getTableName() {
		return "wx_zf_yz";
	}

}

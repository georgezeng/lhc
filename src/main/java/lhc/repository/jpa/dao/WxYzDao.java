package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.WxYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class WxYzDao extends BaseYzDao<WxYz> {

	@Override
	protected String getTableName() {
		return "wx_yz";
	}

}

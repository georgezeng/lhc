package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.WxdsZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class WxdsZfYzDao extends BaseYzDao<WxdsZfYz> {

	@Override
	protected String getTableName() {
		return "wxds_zf_yz";
	}

}

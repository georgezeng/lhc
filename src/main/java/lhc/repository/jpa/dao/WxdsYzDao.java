package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.WxdsYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class WxdsYzDao extends BaseYzDao<WxdsYz> {

	@Override
	protected String getTableName() {
		return "wxds_yz";
	}
}

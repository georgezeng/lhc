package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.WxdsLrYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class WxdsLrYzDao extends BaseYzDao<WxdsLrYz> {

	@Override
	protected String getTableName() {
		return "wxds_lr_yz";
	}

}

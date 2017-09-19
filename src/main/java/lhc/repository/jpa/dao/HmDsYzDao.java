package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.HmDsYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class HmDsYzDao extends BaseYzDao<HmDsYz> {

	@Override
	protected String getTableName() {
		return "hm_ds_yz";
	}
}

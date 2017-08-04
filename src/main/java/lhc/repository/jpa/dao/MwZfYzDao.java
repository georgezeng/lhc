package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.MwZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class MwZfYzDao extends BaseYzDao<MwZfYz> {

	@Override
	protected String getTableName() {
		return "mw_zf_yz";
	}
}

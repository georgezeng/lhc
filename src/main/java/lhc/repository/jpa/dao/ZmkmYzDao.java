package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.ZmkmYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class ZmkmYzDao extends BaseYzDao<ZmkmYz> {

	@Override
	protected String getTableName() {
		return "zmkm_yz";
	}
}

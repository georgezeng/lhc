package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SxZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SxZfYzDao extends BaseYzDao<SxZfYz> {

	@Override
	protected String getTableName() {
		return "sx_zf_yz";
	}
}

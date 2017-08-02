package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SxZfYz2;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SxZfYz2Dao extends BaseYzDao<SxZfYz2> {

	@Override
	protected String getTableName() {
		return "sx_zf_yz2";
	}
}

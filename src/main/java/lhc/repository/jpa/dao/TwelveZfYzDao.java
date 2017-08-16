package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.TwelveZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class TwelveZfYzDao extends BaseYzDao<TwelveZfYz> {

	@Override
	protected String getTableName() {
		return "twelve_zf_yz";
	}

}

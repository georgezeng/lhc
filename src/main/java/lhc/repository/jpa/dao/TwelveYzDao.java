package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.TwelveYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class TwelveYzDao extends BaseYzDao<TwelveYz> {

	@Override
	protected String getTableName() {
		return "twelve_yz";
	}
}

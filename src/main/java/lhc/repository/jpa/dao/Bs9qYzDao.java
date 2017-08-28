package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Bs9qYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Bs9qYzDao extends BaseYzDao<Bs9qYz> {

	@Override
	protected String getTableName() {
		return "bs9q_yz";
	}
}

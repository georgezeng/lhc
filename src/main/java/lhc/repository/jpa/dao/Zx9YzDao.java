package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx9Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx9YzDao extends BaseYzDao<Zx9Yz> {

	@Override
	protected String getTableName() {
		return "zx9_yz";
	}
}

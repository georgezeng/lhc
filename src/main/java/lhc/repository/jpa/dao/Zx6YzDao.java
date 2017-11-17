package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx6Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx6YzDao extends BaseYzDao<Zx6Yz> {

	@Override
	protected String getTableName() {
		return "zx6_yz";
	}
}

package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx3Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx3YzDao extends BaseYzDao<Zx3Yz> {

	@Override
	protected String getTableName() {
		return "zx3_yz";
	}
}

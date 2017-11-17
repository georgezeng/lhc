package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx10Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx10YzDao extends BaseYzDao<Zx10Yz> {

	@Override
	protected String getTableName() {
		return "zx10_yz";
	}
}

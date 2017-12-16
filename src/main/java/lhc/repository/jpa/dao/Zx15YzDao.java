package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx15Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx15YzDao extends BaseYzDao<Zx15Yz> {

	@Override
	protected String getTableName() {
		return "zx15_yz";
	}
}

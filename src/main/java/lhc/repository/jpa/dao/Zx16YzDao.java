package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx16Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx16YzDao extends BaseYzDao<Zx16Yz> {

	@Override
	protected String getTableName() {
		return "zx16_yz";
	}
}

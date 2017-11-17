package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx5Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx5YzDao extends BaseYzDao<Zx5Yz> {

	@Override
	protected String getTableName() {
		return "zx5_yz";
	}
}

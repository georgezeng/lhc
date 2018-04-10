package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.MyD1Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class MyD1Dao extends BaseYzDao<MyD1Yz> {

	@Override
	protected String getTableName() {
		return "my_d1_yz";
	}
}

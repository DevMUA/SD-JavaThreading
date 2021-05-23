package sharedRegions.Repository;

import state.SPilot;
import state.SHostess;
import state.SPassenger;

public interface IRepository {
	public int update(SPilot state);
	public int update(SHostess state);
	public int update(int passengerID, SHostess state);
	public int update(int passengerID, SPassenger state);

	public int updateInq(int inq);
	public int updateInf(int inf);
	public int updatePtal(int ptal);

	public int writeSumUp();
}
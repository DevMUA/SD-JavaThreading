package sharedRegions.Repository;

import state.SPilot;
import state.SHostess;
import state.SPassenger;

public interface IRepository {
	public void update(SPilot state);
	public void update(SHostess state);
	public void update(int passenger_no, SHostess state);
	public void update(int passenger_no, SPassenger state);

	public void updateInq(int inq);
	public void updateInf(int inf);
	public void updatePtal(int ptal);

	public void writeSumUp();
}
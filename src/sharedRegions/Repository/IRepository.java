package sharedRegions.Repository;

import state.SPilot;
import state.SHostess;
import state.SPassenger;

/**
 * The interface Repository.
 */
public interface IRepository {
	/**
	 * Update int.
	 *
	 * @param state the state
	 * @return the int
	 */
	public int update(SPilot state);

	/**
	 * Update int.
	 *
	 * @param state the state
	 * @return the int
	 */
	public int update(SHostess state);

	/**
	 * Update int.
	 *
	 * @param passengerID the passenger id
	 * @param state       the state
	 * @return the int
	 */
	public int update(int passengerID, SHostess state);

	/**
	 * Update int.
	 *
	 * @param passengerID the passenger id
	 * @param state       the state
	 * @return the int
	 */
	public int update(int passengerID, SPassenger state);

	/**
	 * Update inq int.
	 *
	 * @param inq the inq
	 * @return the int
	 */
	public int updateInq(int inq);

	/**
	 * Update inf int.
	 *
	 * @param inf the inf
	 * @return the int
	 */
	public int updateInf(int inf);

	/**
	 * Update ptal int.
	 *
	 * @param ptal the ptal
	 * @return the int
	 */
	public int updatePtal(int ptal);

	/**
	 * Write sum up int.
	 *
	 * @return the int
	 */
	public int writeSumUp();
}
package cn.shineiot.base.bean;

/**
 * @author GF63
 * 积分
 */
public class Coin {

	/**
	 * coinCount : 420
	 * level : 5
	 * rank : 548
	 * userId : 1963
	 * username : 请**用户名
	 */

	private int coinCount;
	private int level;
	private int rank;
	private int userId;
	private String username;

	public int getCoinCount() {
		return coinCount;
	}

	public void setCoinCount(int coinCount) {
		this.coinCount = coinCount;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
